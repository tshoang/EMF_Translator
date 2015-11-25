/*******************************************************************************
 *  Copyright (c) 2015 University of Southampton.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *  Contributors:
 *  University of Southampton - Initial implementation
 *******************************************************************************/
package ac.soton.eventb.emf.diagrams.importExport.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.eventb.emf.core.EventBElement;
import org.eventb.emf.core.EventBNamed;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "ac.soton.eventb.emf.diagrams.importExport.impl.messages"; //$NON-NLS-1$
	public static String TRANSLATOR_MSG_00;
	public static String TRANSLATOR_MSG_01;
	public static String TRANSLATOR_MSG_01(Object object){
		return bind(TRANSLATOR_MSG_01,object);
	}
	public static String TRANSLATOR_MSG_02;
	public static String TRANSLATOR_MSG_02(Object object){
		return bind(TRANSLATOR_MSG_02,object);
	}
	public static String TRANSLATOR_MSG_03;
	public static String TRANSLATOR_MSG_04;
	public static String TRANSLATOR_MSG_05;
	public static String TRANSLATOR_MSG_06;
	public static String TRANSLATOR_MSG_07;
	public static String TRANSLATOR_MSG_08;
	public static String TRANSLATOR_MSG_09;
	public static String TRANSLATOR_MSG_10;
	public static String TRANSLATOR_MSG_11;
	public static String TRANSLATOR_MSG_12;
	public static String TRANSLATOR_MSG_12(EventBElement element){
		return bind(TRANSLATOR_MSG_12,
				element.eClass().getName(),
				element instanceof EventBNamed ? ((EventBNamed)element).getName() : element.toString()
				);
	}
	public static String TRANSLATOR_MSG_13;
	public static String TRANSLATOR_MSG_13(EObject element){
		return bind(TRANSLATOR_MSG_13,
				element.eClass().getName(),
				element instanceof EventBNamed ? ((EventBNamed)element).getName() : element.toString()
				);
	}
	public static String TRANSLATOR_MSG_14;
	public static String TRANSLATOR_MSG_15;
	public static String TRANSLATOR_MSG_16;
	public static String TRANSLATOR_MSG_17;
	public static String TRANSLATOR_MSG_17(EventBElement element){
		return bind(TRANSLATOR_MSG_17,
				element.eClass().getName(),
				element instanceof EventBNamed ? ((EventBNamed)element).getName() : element.toString()
				);
	}
	public static String TRANSLATOR_MSG_18;
	public static String TRANSLATOR_MSG_18(Object object){
		return bind(TRANSLATOR_MSG_18,object);
	}
	public static String TRANSLATOR_MSG_19;
	public static String TRANSLATOR_MSG_20;
	public static String TRANSLATOR_MSG_21;
	public static String TRANSLATOR_MSG_21(Object object, EStructuralFeature feature){
		return bind(TRANSLATOR_MSG_21,
				object,
				feature.getName()
				);
	}
	public static String TRANSLATOR_MSG_22;
	public static String TRANSLATOR_MSG_22(Object parent, EStructuralFeature feature){
		return bind(TRANSLATOR_MSG_22,
				parent instanceof EventBNamed ? ((EventBNamed)parent).getName() : parent.toString(),
				feature == null? "<null>" : feature.getName()
				);
	}
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
