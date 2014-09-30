package ac.soton.codin.codegen.quickPrint;

import java.util.ArrayList;
import java.util.List;

import org.eventb.codegen.il1.VariableDecl;

import ac.soton.codin.codegen.basic.VHDL_TranslationData;
import ac.soton.eventb.emf.components.Connector;
import ac.soton.eventb.statemachines.Statemachine;

public class QuickPrintInfo {
	private static List<Connector> connectorList = new ArrayList<>();
	private static List<VariableDecl> signalDeclsList = new ArrayList<>();
	private VHDL_TranslationData translationData;
	
	
	
	public QuickPrintInfo(VHDL_TranslationData vhdl_translationData) {
		this.translationData = vhdl_translationData;
	}

	public List<Connector> getConnectorList(){
		return connectorList;
	}
	
	// variable decls to be implemented as signals
	public List<VariableDecl> getSignalsList(){
		return signalDeclsList; 
	}
	
	// connectors to be implemented as signals
	public List<String> getConnectorNameList() {
		List<Connector> connectorList = getConnectorList();
		List<String> connectorNamesList = new ArrayList<>();
		for (Connector c : connectorList) {
			connectorNamesList.add(c.getName().toLowerCase());
		}
		return connectorNamesList;
	}

	// return the names of the signals from the connector and
	// variablesAsSignals lists.
	public List<String> getSignalNamesList(){
		List<Connector> connectorList = getConnectorList();
		List<String> signalNamesList = new ArrayList<>();
		for (Connector c : connectorList) {
			// we find the lower case of the connector - but not the variable below
			String connectorName = c.getName().toLowerCase();
			if(!signalNamesList.contains(connectorName)){signalNamesList.add(connectorName);}
		}
		for (VariableDecl v : signalDeclsList) {
			String varName = v.getIdentifier();
			if(!signalNamesList.contains(varName)){signalNamesList.add(varName);}
		}
		return signalNamesList;
	}
	
	public List<String> getSynchSMNamesList() {
		ArrayList<String> returnList = new ArrayList<>();
		for(Statemachine s: translationData.synchSMList){
			returnList.add(s.getName());
		}
		return returnList;
	}
}