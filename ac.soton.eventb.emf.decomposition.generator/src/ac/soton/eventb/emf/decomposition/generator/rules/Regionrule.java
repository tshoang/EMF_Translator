package ac.soton.eventb.emf.decomposition.generator.rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eventb.core.IMachineRoot;
import org.eventb.core.ast.Type;
import org.eventb.emf.core.AbstractExtension;
import org.eventb.emf.core.CorePackage;
import org.eventb.emf.core.Project;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Invariant;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachinePackage;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.persistence.EventBEMFUtils;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.eventb.decomposition.Region;
import ac.soton.eventb.emf.core.extension.navigator.refiner.AbstractElementRefiner;
import ac.soton.eventb.emf.core.extension.navigator.refiner.ElementRefinerRegistry;
import ac.soton.eventb.emf.decomposition.generator.Make;
import ch.ethz.eventb.utils.EventBSCUtils;

public class Regionrule extends AbstractRule implements IRule {

	protected static final EReference components = CorePackage.Literals.PROJECT__COMPONENTS;

	@Override
	public boolean enabled(final EObject sourceElement) throws Exception  {
		return ((Region)sourceElement).isReady();
	}

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Region region = (Region)sourceElement;
		String projectName = region.getProjectName();
		Machine sourceMachine = (Machine) region.getContaining(MachinePackage.Literals.MACHINE);
		Machine decomposedMachine = Make.machine(region.getName(), "generated by decomposition from region: "+region.getName());
		IMachineRoot sourceMachineRoot = EventBEMFUtils.getRoot(sourceMachine);
		
		if (projectName != null && projectName.length()>0){
			Project project = Make.project(region.getProjectName(), "generated by decomposition from region: "+region.getName());
			//FIXME: add support for project descriptor in translator (currently all machines go in same project as source)
			ret.add(Make.descriptor(null, null , project, 1));
			project.getComponents().add(decomposedMachine);
		}else{
			ret.add(Make.descriptor(findProject(region), components , decomposedMachine, 1));
		}
		for (String seesName : sourceMachine.getSeesNames()){
			decomposedMachine.getSeesNames().add(seesName);
		}
		
		for (Variable v : region.getAllocatedVariables()){
			AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(v);
			Map<EObject,EObject> copy = refiner.cloneAndExtractFromRefinementChain(v, decomposedMachine, null);
			Variable clone = (Variable) copy.get(v);
			decomposedMachine.getVariables().add(clone);

			//make sure there is a typing theorem for each variable
			Type type = EventBSCUtils.getVariableType(sourceMachineRoot, v.getName());
			String predicate = v.getName()+" \u2208 "+(type==null? "<SC doesn't known Type>" : type.toString());
			Invariant typeInv = (Invariant) Make.invariant("typing_"+v.getName(), true, predicate, "generated by decomposition");
			decomposedMachine.getInvariants().add(0,typeInv);
		}
		
		for (Invariant inv : getAllInvariants(sourceMachine) ){
			if (relevantInvariant(sourceMachine, region, inv.getPredicate())){
				AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(inv);
				Map<EObject,EObject> copy = refiner.cloneAndExtractFromRefinementChain(inv, decomposedMachine,null);
				Invariant clone = (Invariant) copy.get(inv);
				String containingMachineName = ((Machine)inv.getContaining(MachinePackage.Literals.MACHINE)).getName();
				clone.setName(containingMachineName+"_"+clone.getName());
				decomposedMachine.getInvariants().add(clone);
			}
		}
		
		EList<Event> events = decomposedMachine.getEvents();
		
//		Event initEvent = (Event)Make.event("INITIALISATION");
//		events.add(initEvent);
//		for (Action act : getInitialisationActions(sourceMachine) ){
//			if (relevant(sourceMachine, region, act.getAction())){
//				AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(act);
//				Map<EObject,EObject> copy = refiner.clone(act, decomposedMachine);
//				Action clone = (Action) copy.get(act);
//				initEvent.getActions().add(clone);
//			}
//		}
		
		for (Event event : sourceMachine.getEvents()) {
			Event newEvent = (Event)Make.event(event.getName());
			for (Guard gd : getExtendedGuards(sourceMachine, event.getName())){
				if (relevant(sourceMachine, region, gd.getPredicate())){
					AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(gd);
					Map<EObject,EObject> copy = refiner.cloneAndExtractFromRefinementChain(gd, decomposedMachine,null);
					Guard clone = (Guard) copy.get(gd);
					newEvent.getGuards().add(clone);
				}
			}
			for (Action act :  getExtendedActions(sourceMachine, event.getName())){
				if (relevant(sourceMachine, region, act.getAction())){
					AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(act);
					Map<EObject,EObject> copy = refiner.cloneAndExtractFromRefinementChain(act, decomposedMachine, null);
					Action clone = (Action) copy.get(act);
					newEvent.getActions().add(clone);
				}
			}
			
			if (newEvent.getGuards().size()>0 || newEvent.getActions().size()>0 ){
				
				//Add parameters and relevant parameter guards (only if guards or actions already added)
				List<Guard> guardsToClone = new ArrayList<Guard>();
				//nextP: 
				for (Parameter p : getExtendedParameters(sourceMachine, event.getName())){
					Parameter newP = (Parameter)Make.parameter(p.getName());
					newEvent.getParameters().add(newP);

					for (Guard gd : getExtendedGuards(sourceMachine, event.getName())){
						if (!guardsToClone.contains(gd) && 
								refers(gd.getPredicate(), p.getName()) && 
								neutral(sourceMachine, region, gd.getPredicate())){	
							if (gd.getName().contains("type")){
								guardsToClone.add(gd);	//order will reverse again below
							}else{
								guardsToClone.add(0, gd);	//order will reverse again below
							}
						}
					}
					
					
				}
				for (Guard gd : guardsToClone){
					AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(gd);
					Map<EObject,EObject> copy = refiner.cloneAndExtractFromRefinementChain(gd, decomposedMachine,null);
					Guard clone = (Guard) copy.get(gd);
					newEvent.getGuards().add(0,clone); 	//add these at front as they tend to be type
				}
				
				//make sure there is a typing theorem for each parameter
				for (Parameter p : getExtendedParameters(sourceMachine, event.getName())){
					Type ptype = SCUtils.getParameterType(sourceMachineRoot, event.getName(), p.getName());
					if (ptype!=null){
						String predicate = p.getName()+" \u2208 "+ptype.toString();
						Guard typeGd = (Guard) Make.guard("typing_"+p.getName(), true, predicate, "generated by decomposition");
						newEvent.getGuards().add(0,typeGd);
					}
				}
					
				//add the event
				events.add(newEvent);
			}
		}
		
		//get any allocated extensions
		EList<AbstractExtension> extensions = region.getAllocatedExtensions();
		
		// also promote any nested regions (they are also extensions)
		extensions.addAll(region.getExtensions());
		
		//add clones of all the extensions
		for (AbstractExtension ext : extensions){
			AbstractElementRefiner refiner = ElementRefinerRegistry.getRegistry().getRefiner(ext);
			if (refiner!=null) {
				Map<EObject,EObject> copy = refiner.cloneAndExtractFromRefinementChain(ext, decomposedMachine,null);
				AbstractExtension clone = (AbstractExtension) copy.get(ext);
				decomposedMachine.getExtensions().add(clone);
			}
		}

		return ret;
	}
	
	/*
	 * checks whether the given string contains the target identifier as a
	 * delimited whole word
	 * 
	 * @param string
	 * @param target
	 * @return
	 */
	private boolean refers(String string, String target) {
		String[] tokens = string.split("\\W");
		for (String tok : tokens){
			if (tok.equals(target)){
				return true;
			}
		}
		return false;
	}

	/*
	 * gets the parameters from the named event in the source machine
	 * including those from extended abstract events
	 * @param sourceMachine
	 * @return
	 */
	private List<Parameter> getExtendedParameters(Machine machine, String eventName) {
		if (machine==null) return Collections.emptyList();
		List<Parameter> ret = new ArrayList<Parameter>();
		for (Event event : machine.getEvents()){
			if  (eventName.equals(event.getName())){
				ret.addAll(event.getParameters());
				if (event.isExtended() && machine.getRefines().size()>0){
					ret.addAll(getExtendedParameters(machine.getRefines().get(0), eventName));
				}
			}
		}
		return ret;
	}
	
	/*
	 * gets the guards from the named event in the source machine
	 * including those from extended abstract events
	 * @param sourceMachine
	 * @return
	 */
	private List<Guard> getExtendedGuards(Machine machine, String eventName) {
		if (machine==null) return Collections.emptyList();
		List<Guard> ret = new ArrayList<Guard>();
		for (Event event : machine.getEvents()){
			if  (eventName.equals(event.getName())){
				ret.addAll(event.getGuards());
				if (event.isExtended() && machine.getRefines().size()>0){
					ret.addAll(getExtendedGuards(machine.getRefines().get(0), eventName));
				}
			}
		}
		return ret;
	}
	
	/*
	 * gets the actions from the named event in the source machine
	 * including those from extended abstract events
	 * @param sourceMachine
	 * @return
	 */
	private List<Action> getExtendedActions(Machine machine, String eventName) {
		if (machine==null) return Collections.emptyList();
		List<Action> ret = new ArrayList<Action>();
		for (Event event : machine.getEvents()){
			if  (eventName.equals(event.getName())){
				ret.addAll(event.getActions());
				if (event.isExtended() && machine.getRefines().size()>0){
					ret.addAll(getExtendedActions(machine.getRefines().get(0), eventName));
				}
			}
		}
		return ret;
	}

	/*
	 * @param sourceMachine
	 * @return
	 */
	private List<Invariant> getAllInvariants(Machine machine) {
		if (machine==null) return Collections.emptyList();
		List<Invariant> ret = new ArrayList<Invariant>();
		if (machine.getRefines().size()>0){
			ret.addAll(getAllInvariants(machine.getRefines().get(0)));
		}
		ret.addAll(machine.getInvariants());
		return ret;
	}
	
	/*
	 * @param sourceMachine
	 * @return
	 */
	private List<Variable> getAllVariables(Machine machine) {
		if (machine==null) return Collections.emptyList();
		List<Variable> ret = new ArrayList<Variable>();
		ret.addAll(machine.getVariables());
		if (machine.getRefines().size()>0){
			ret.addAll(getAllVariables(machine.getRefines().get(0)));
		}
		return ret;
	}

//	/*
//	 * checks whether the string refers to any variables in the given region and
//	 *  no variables that are not in the given region
//	 */
//	private boolean relevant (Machine sourceMachine, Region region, String string) {
//		for (AbstractExtension ext : sourceMachine.getExtensions()){
//			if (ext instanceof Region && relevant2(sourceMachine,region,string))
//				//	(ext == region && !relevant(region, string) || ext != region && relevant((Region)ext, string)) 
//			{
//				return false;
//			}
//		}
//		return true;
//	}
	
//	/*
//	 * checks whether the string refers to any variables in the given region
//	 * @param predicate
//	 * @return
//	 */
//	private boolean relevant(Region region, String string) {
//		String[] tokens = string.split("\\W");
//		for (String tok : tokens){
//			for (Variable v : region.getAllocatedVariables()){
//				if (tok.equals(v.getName())){
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	/*
	 * returns true if string contains at least one variable allocated to the region and none that are not
	 * @param predicate
	 * @return
	 */
	private boolean relevant(Machine sourceMachine, Region region, String string) {
		String[] tokens = string.split("\\W");
		boolean relevant = false;
		for (String tok : tokens){
			for (Variable v : sourceMachine.getVariables()){
				if (tok.equals(v.getName())){
					if (region.getAllocatedVariables().contains(v))
						relevant = true;
					else
						return false;
				}
			}
		}
		return relevant;
	}
	
	/*
	 * returns true if string contains no references to variables
	 * @param predicate
	 * @return
	 */
	private boolean neutral(Machine sourceMachine, Region region, String string) {
		String[] tokens = string.split("\\W");
		for (String tok : tokens){
			for (Variable v : sourceMachine.getVariables()){
				if (tok.equals(v.getName())){
					return false;
				}
			}
		}
		return true;
	}
	

	/*
	 * returns true if string contains at least one variable allocated to the region and none that are not
	 * @param predicate
	 * @return
	 */
	private boolean relevantInvariant(Machine sourceMachine, Region region, String string) {
		String[] tokens = string.split("\\W");
		boolean relevant = false;
		for (String tok : tokens){
			for (Variable v : getAllVariables(sourceMachine)){
				if (tok.equals(v.getName())){	//token refers to a variable of the source machine
					if (isAllocatedVariable(tok, region))
						relevant = true;
					else
						return false;
				}
			}
		}
		return relevant;
	}
	
	/**
	 * @param tok
	 * @param region 
	 * @return
	 */
	private boolean isAllocatedVariable(String varName, Region region) {
		for (Variable av : region.getAllocatedVariables()){
			if (varName.equals(av.getName())) return true;
		}
		return false;
	}

	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> translatedElements) throws Exception  {
		return true;
	}
	
	@Override
	public boolean fireLate() {
		return false;
	}
	
	
	/**
	 * find the containing Project for this element
	 * 
	 * CURRENTLY RETURNS NULL
	 * 
	 * @param machine
	 * @return
	 * @throws IOException
	 */
		private static Project findProject(EObject sourceElement) throws IOException {
//			URI eventBelementUri = eventBelement.getURI();
//			URI projectUri = eventBelementUri.trimFragment().trimSegments(1);
//			ProjectResource projectResource = new ProjectResource();
//			projectResource.setURI(eventBelement.getURI());
//			projectResource.load(null);
//			for (EObject eObject : projectResource.getContents()){
//				if (eObject instanceof Project){
//					return (Project)eObject;
//				}
//			}
			return null;
		}
		

		
}
