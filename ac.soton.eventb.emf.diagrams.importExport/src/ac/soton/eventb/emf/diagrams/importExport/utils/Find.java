/**
 * Copyright (c) 2015 - University of Southampton.
 * All rights reserved. This program and the accompanying materials  are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 *
 * $Id$
 */
package ac.soton.eventb.emf.diagrams.importExport.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eventb.emf.core.EventBElement;
import org.eventb.emf.core.EventBNamed;
import org.eventb.emf.core.Project;

import ac.soton.eventb.emf.core.extension.coreextension.EventBLabeled;
import ac.soton.eventb.emf.diagrams.importExport.TranslationDescriptor;


/**
 * Convenience methods for finding things needed in Translator Rules
 * 
 * @author cfs
 *
 */
public class Find {
		
/**
 * Find, by name or label and matching parent and feature, an element in a translation descriptor from the given collection.
 * The element may be the object in value field of a translationDescriptor or any child transitively contained within it.
 * (parent and/or feature may be null if not required to be matched)
 * 
 * @param translatedElements
 * @param parent (or null)
 * @param feature (or null)
 * @param eClass (or null)
 * @param identifier
 * @return
 */
	public static Object translatedElement(List<TranslationDescriptor> translatedElements, EObject parent, EStructuralFeature feature, EClass eClass, String identifier) {
		for (TranslationDescriptor translatedElement : translatedElements){
			if ((parent == null || translatedElement.parent == parent) && 
				(feature == null || translatedElement.feature== feature) &&
				(eClass == null || (translatedElement.value instanceof EObject  && eClass.isSuperTypeOf(((EObject)translatedElement.value).eClass())))){
				if (matches(translatedElement.value,identifier)) {
					return translatedElement.value;
				}
			}
			if (translatedElement.value instanceof EObject){
				Object child =  element(((EObject)translatedElement.value), parent, feature, eClass, identifier);
				if (child!=null) return child;
			}
		}
		return null;
	}
	
	/**
	 * Find, by name or label and matching parent and feature, an element in the transitive contents of the given root object.
	 * (the root object is expected to be an eObject for parent, eClass or feature to be matched and for contents to be matched)
	 * (parent, eClass and/or feature may be null if not required to be matched)
	 * 
	 * @param translatedElements
	 * @param parent (or null)
	 * @param feature (or null)
	 * @param eClass (or null)
	 * @param identifier
	 * @return
	 */
	public static Object element(Object object, EObject parent, EStructuralFeature feature, EClass eClass, String identifier){
		if (!(object instanceof EObject) || (
				(parent == null || ((EObject)object).eContainer() == parent) &&
				(eClass == null || eClass.isSuperTypeOf(((EObject)object).eClass())) &&
				(feature==null || feature == ((EObject)object).eContainingFeature()) 
			) &&
			matches(object, identifier)){
				return object;
		}
		if (object instanceof EObject){
			for (EObject child : ((EObject)object).eContents()){
				Object findElement = element(child, parent, feature, eClass, identifier);
				if (findElement!=null) return findElement;
			}
		}
		return null;
	}
	
////	public static Object translatedElement(List<TranslationDescriptor> translatedElements, EObject parent, EStructuralFeature feature, EClass eClass, String identifier) {
////		for (TranslationDescriptor translatedElement : translatedElements){
////			if ((parent == null || translatedElement.parent == parent) && 
////				(feature == null || translatedElement.feature== feature) &&
////				(eClass == null || (translatedElement.value instanceof EObject  && eClass.isSuperTypeOf(((EObject)translatedElement.value).eClass())))){
////				if (matches(translatedElement.value,identifier)) {
////					return translatedElement.value;
////				}
////			}
////			if (translatedElement.value instanceof EObject){
////				Object child =  element(((EObject)translatedElement.value), feature, eClass, identifier);
////				if (child!=null) return child;
////			}
////		}
////		return null;
////	}
////	
////	/public static Object element(EObject parent, EStructuralFeature feature, EClass eClass, String identifier){
////		EList<EObject> contents = parent.eContents();
////		for (EObject child : contents){
////			if ((eClass == null || eClass.isSuperTypeOf(child.eClass())) &&
////				(feature==null || feature == child.eContainingFeature()) &&
////				matches(child, identifier)){
////				return child;
////			}
////			Object find = element(child, feature, eClass, identifier);
////			if (find!=null) return find;
////		}
////		return null;
////	}

	private static boolean matches(Object object, String identifier){
		if (object instanceof EObject)
			return matches((EObject) object, identifier);
		if (object instanceof String && ((String)object).equals(identifier)){
			return true;
		}
		return false;
	}
	
	private static boolean matches(EObject object, String identifier){
		if ((object instanceof EventBNamed && ((EventBNamed)object).getName().equals(identifier))
		|| (object instanceof EventBLabeled && ((EventBLabeled)object).getLabel().equals(identifier))
		 ){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Find, by name or label and matching parent and feature, a translation descriptor from the given collection
	 * (parent and/or feature may be null if not required to be matched)
	 * 
	 * @param translatedElements
	 * @param parent
	 * @param feature
	 * @param identifier
	 * @return
	 */
	public static Object translatedElement(
			List<TranslationDescriptor> translatedElements, EventBElement parent,
			EStructuralFeature feature, String identifier) {
		return Find.translatedElement(translatedElements, parent, feature, null, identifier);
	}

	/**
	 * Find all translated elements that are being placed in the given feature
	 * @param translatedElements
	 * @param in
	 * @param elaborates
	 * @param event
	 * @return
	 */
	public static List<EObject> translatedElements(
			List<TranslationDescriptor> translatedElements, EventBElement parent,
			EStructuralFeature feature, EClass eClass) {

		List<EObject> ret = new ArrayList<EObject>();
		for (TranslationDescriptor translatedElement : translatedElements){
			if ((parent == null || translatedElement.parent == parent) && 
				(feature == null || translatedElement.feature== feature) &&
				(eClass == null || (translatedElement.value instanceof EObject  && eClass.isSuperTypeOf(((EObject)translatedElement.value).eClass())))){
				ret.add((EObject) translatedElement.value);
			}
		}	
		return ret;
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
	public static Project project(EObject sourceElement) throws IOException {
//		URI eventBelementUri = eventBelement.getURI();
//		URI projectUri = eventBelementUri.trimFragment().trimSegments(1);
//		ProjectResource projectResource = new ProjectResource();
//		projectResource.setURI(eventBelement.getURI());
//		projectResource.load(null);
//		for (EObject eObject : projectResource.getContents()){
//			if (eObject instanceof Project){
//				return (Project)eObject;
//			}
//		}
		return null;
	}
	
	/**
	 * finds nearest container of type or null if none
	 * @param type
	 * @param eObject
	 * @return
	 */
	public static EObject containing(EClass type, EObject eObject) {
		EObject res = eObject;
		while (!(res.eClass().equals(type)) && res.eContainer()!=null){
			res = res.eContainer();
		}
		return res.eClass().equals(type)? res:null;
	}

	public static List<EObject> eAllContents(EObject source,EClassifier filter){
		  final TreeIterator<EObject> contentIterator=source.eAllContents();
		  final List<EObject> result=new ArrayList<EObject>();
		  while (contentIterator.hasNext()) {
		    final EObject next=contentIterator.next();
		    if (filter == null || filter.isInstance(next)) {
		      result.add(next);
		    }
		  }
		  return result;
		}

}