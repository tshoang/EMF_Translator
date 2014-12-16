/*
* generated by Xtext
*/
grammar InternalDsl;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package ac.soton.uk.iumlb.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package ac.soton.uk.iumlb.xtext.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import ac.soton.uk.iumlb.xtext.services.DslGrammarAccess;

}

@parser::members {

 	private DslGrammarAccess grammarAccess;
 	
    public InternalDslParser(TokenStream input, DslGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Statemachine";	
   	}
   	
   	@Override
   	protected DslGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleStatemachine
entryRuleStatemachine returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStatemachineRule()); }
	 iv_ruleStatemachine=ruleStatemachine 
	 { $current=$iv_ruleStatemachine.current; } 
	 EOF 
;

// Rule Statemachine
ruleStatemachine returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='Statemachine' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getStatemachineAccess().getStatemachineKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getNameEStringParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStatemachineRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2='elaborates' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getStatemachineAccess().getElaboratesKeyword_2_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getStatemachineRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getElaboratesEventBNamedCrossReference_2_1_0()); 
	    }
		ruleEString		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))?(	otherlv_4='refines' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getStatemachineAccess().getRefinesKeyword_3_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getStatemachineRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getRefinesStatemachineCrossReference_3_1_0()); 
	    }
		ruleEString		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))?(	otherlv_6='nodes' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getStatemachineAccess().getNodesKeyword_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getNodesAbstractNodeParserRuleCall_4_1_0()); 
	    }
		lv_nodes_7_0=ruleAbstractNode		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStatemachineRule());
	        }
       		add(
       			$current, 
       			"nodes",
        		lv_nodes_7_0, 
        		"AbstractNode");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_8=',' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getStatemachineAccess().getCommaKeyword_4_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getNodesAbstractNodeParserRuleCall_4_2_1_0()); 
	    }
		lv_nodes_9_0=ruleAbstractNode		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStatemachineRule());
	        }
       		add(
       			$current, 
       			"nodes",
        		lv_nodes_9_0, 
        		"AbstractNode");
	        afterParserOrEnumRuleCall();
	    }

)
))*)?(	otherlv_10='transitions' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getStatemachineAccess().getTransitionsKeyword_5_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getTransitionsTransitionParserRuleCall_5_1_0()); 
	    }
		lv_transitions_11_0=ruleTransition		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStatemachineRule());
	        }
       		add(
       			$current, 
       			"transitions",
        		lv_transitions_11_0, 
        		"Transition");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_12=',' 
    {
    	newLeafNode(otherlv_12, grammarAccess.getStatemachineAccess().getCommaKeyword_5_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStatemachineAccess().getTransitionsTransitionParserRuleCall_5_2_1_0()); 
	    }
		lv_transitions_13_0=ruleTransition		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStatemachineRule());
	        }
       		add(
       			$current, 
       			"transitions",
        		lv_transitions_13_0, 
        		"Transition");
	        afterParserOrEnumRuleCall();
	    }

)
))*)?)
;





// Entry rule entryRuleAbstractNode
entryRuleAbstractNode returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAbstractNodeRule()); }
	 iv_ruleAbstractNode=ruleAbstractNode 
	 { $current=$iv_ruleAbstractNode.current; } 
	 EOF 
;

// Rule AbstractNode
ruleAbstractNode returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAbstractNodeAccess().getStateParserRuleCall_0()); 
    }
    this_State_0=ruleState
    { 
        $current = $this_State_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getAbstractNodeAccess().getInitialParserRuleCall_1()); 
    }
    this_Initial_1=ruleInitial
    { 
        $current = $this_Initial_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getAbstractNodeAccess().getFinalParserRuleCall_2()); 
    }
    this_Final_2=ruleFinal
    { 
        $current = $this_Final_2.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getAbstractNodeAccess().getAnyParserRuleCall_3()); 
    }
    this_Any_3=ruleAny
    { 
        $current = $this_Any_3.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getAbstractNodeAccess().getJunctionParserRuleCall_4()); 
    }
    this_Junction_4=ruleJunction
    { 
        $current = $this_Junction_4.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getAbstractNodeAccess().getForkParserRuleCall_5()); 
    }
    this_Fork_5=ruleFork
    { 
        $current = $this_Fork_5.current; 
        afterParserOrEnumRuleCall();
    }
)
;







// Entry rule entryRuleEString
entryRuleEString returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getEStringRule()); } 
	 iv_ruleEString=ruleEString 
	 { $current=$iv_ruleEString.current.getText(); }  
	 EOF 
;

// Rule EString
ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_STRING_0=RULE_STRING    {
		$current.merge(this_STRING_0);
    }

    { 
    newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
    }

    |    this_ID_1=RULE_ID    {
		$current.merge(this_ID_1);
    }

    { 
    newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
    }
)
    ;





// Entry rule entryRuleTransition
entryRuleTransition returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getTransitionRule()); }
	 iv_ruleTransition=ruleTransition 
	 { $current=$iv_ruleTransition.current; } 
	 EOF 
;

// Rule Transition
ruleTransition returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		lv_extended_0_0=	'extended' 
    {
        newLeafNode(lv_extended_0_0, grammarAccess.getTransitionAccess().getExtendedExtendedKeyword_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTransitionRule());
	        }
       		setWithLastConsumed($current, "extended", true, "extended");
	    }

)
)?	otherlv_1='Transition' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getTransitionAccess().getTransitionKeyword_1());
    }
(	otherlv_2='comment' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getTransitionAccess().getCommentKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getTransitionAccess().getCommentEStringParserRuleCall_2_1_0()); 
	    }
		lv_comment_3_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTransitionRule());
	        }
       		set(
       			$current, 
       			"comment",
        		lv_comment_3_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?(	otherlv_4='elaborates' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getTransitionAccess().getElaboratesKeyword_3_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTransitionRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTransitionAccess().getElaboratesEventCrossReference_3_1_0()); 
	    }
		ruleEString		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_6=',' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getTransitionAccess().getCommaKeyword_3_2_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTransitionRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTransitionAccess().getElaboratesEventCrossReference_3_2_1_0()); 
	    }
		ruleEString		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))*)?	otherlv_8='target' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getTransitionAccess().getTargetKeyword_4());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTransitionRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTransitionAccess().getTargetAbstractNodeCrossReference_5_0()); 
	    }
		ruleEString		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_10='source' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getTransitionAccess().getSourceKeyword_6());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTransitionRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTransitionAccess().getSourceAbstractNodeCrossReference_7_0()); 
	    }
		ruleEString		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))
;







// Entry rule entryRuleState
entryRuleState returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStateRule()); }
	 iv_ruleState=ruleState 
	 { $current=$iv_ruleState.current; } 
	 EOF 
;

// Rule State
ruleState returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getStateAccess().getStateAction_0(),
            $current);
    }
)	otherlv_1='State' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getStateAccess().getStateKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStateAccess().getNameEStringParserRuleCall_2_0()); 
	    }
		lv_name_2_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStateRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_3='statemachines' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getStateAccess().getStatemachinesKeyword_3_0());
    }
	otherlv_4='{' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getStateAccess().getLeftCurlyBracketKeyword_3_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStateAccess().getStatemachinesStatemachineParserRuleCall_3_2_0()); 
	    }
		lv_statemachines_5_0=ruleStatemachine		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStateRule());
	        }
       		add(
       			$current, 
       			"statemachines",
        		lv_statemachines_5_0, 
        		"Statemachine");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_6=',' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getStateAccess().getCommaKeyword_3_3_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStateAccess().getStatemachinesStatemachineParserRuleCall_3_3_1_0()); 
	    }
		lv_statemachines_7_0=ruleStatemachine		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStateRule());
	        }
       		add(
       			$current, 
       			"statemachines",
        		lv_statemachines_7_0, 
        		"Statemachine");
	        afterParserOrEnumRuleCall();
	    }

)
))*	otherlv_8='}' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getStateAccess().getRightCurlyBracketKeyword_3_4());
    }
)?(	otherlv_9='invariants' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getStateAccess().getInvariantsKeyword_4_0());
    }
	otherlv_10='{' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getStateAccess().getLeftCurlyBracketKeyword_4_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStateAccess().getInvariantsInvariantParserRuleCall_4_2_0()); 
	    }
		lv_invariants_11_0=ruleInvariant		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStateRule());
	        }
       		add(
       			$current, 
       			"invariants",
        		lv_invariants_11_0, 
        		"Invariant");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_12=',' 
    {
    	newLeafNode(otherlv_12, grammarAccess.getStateAccess().getCommaKeyword_4_3_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getStateAccess().getInvariantsInvariantParserRuleCall_4_3_1_0()); 
	    }
		lv_invariants_13_0=ruleInvariant		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStateRule());
	        }
       		add(
       			$current, 
       			"invariants",
        		lv_invariants_13_0, 
        		"Invariant");
	        afterParserOrEnumRuleCall();
	    }

)
))*	otherlv_14='}' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getStateAccess().getRightCurlyBracketKeyword_4_4());
    }
)?)
;





// Entry rule entryRuleInitial
entryRuleInitial returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getInitialRule()); }
	 iv_ruleInitial=ruleInitial 
	 { $current=$iv_ruleInitial.current; } 
	 EOF 
;

// Rule Initial
ruleInitial returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getInitialAccess().getInitialAction_0(),
            $current);
    }
)	otherlv_1='Initial' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getInitialAccess().getInitialKeyword_1());
    }
(	otherlv_2='internalId' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getInitialAccess().getInternalIdKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getInitialAccess().getInternalIdEStringParserRuleCall_2_1_0()); 
	    }
		lv_internalId_3_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getInitialRule());
	        }
       		set(
       			$current, 
       			"internalId",
        		lv_internalId_3_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleFinal
entryRuleFinal returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getFinalRule()); }
	 iv_ruleFinal=ruleFinal 
	 { $current=$iv_ruleFinal.current; } 
	 EOF 
;

// Rule Final
ruleFinal returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getFinalAccess().getFinalAction_0(),
            $current);
    }
)	otherlv_1='Final' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getFinalAccess().getFinalKeyword_1());
    }
(	otherlv_2='internalId' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getFinalAccess().getInternalIdKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getFinalAccess().getInternalIdEStringParserRuleCall_2_1_0()); 
	    }
		lv_internalId_3_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getFinalRule());
	        }
       		set(
       			$current, 
       			"internalId",
        		lv_internalId_3_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleAny
entryRuleAny returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAnyRule()); }
	 iv_ruleAny=ruleAny 
	 { $current=$iv_ruleAny.current; } 
	 EOF 
;

// Rule Any
ruleAny returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getAnyAccess().getAnyAction_0(),
            $current);
    }
)	otherlv_1='Any' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getAnyAccess().getAnyKeyword_1());
    }
(	otherlv_2='internalId' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAnyAccess().getInternalIdKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAnyAccess().getInternalIdEStringParserRuleCall_2_1_0()); 
	    }
		lv_internalId_3_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAnyRule());
	        }
       		set(
       			$current, 
       			"internalId",
        		lv_internalId_3_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleJunction
entryRuleJunction returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getJunctionRule()); }
	 iv_ruleJunction=ruleJunction 
	 { $current=$iv_ruleJunction.current; } 
	 EOF 
;

// Rule Junction
ruleJunction returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getJunctionAccess().getJunctionAction_0(),
            $current);
    }
)	otherlv_1='Junction' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getJunctionAccess().getJunctionKeyword_1());
    }
(	otherlv_2='internalId' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getJunctionAccess().getInternalIdKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getJunctionAccess().getInternalIdEStringParserRuleCall_2_1_0()); 
	    }
		lv_internalId_3_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getJunctionRule());
	        }
       		set(
       			$current, 
       			"internalId",
        		lv_internalId_3_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleFork
entryRuleFork returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getForkRule()); }
	 iv_ruleFork=ruleFork 
	 { $current=$iv_ruleFork.current; } 
	 EOF 
;

// Rule Fork
ruleFork returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getForkAccess().getForkAction_0(),
            $current);
    }
)	otherlv_1='Fork' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getForkAccess().getForkKeyword_1());
    }
(	otherlv_2='internalId' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getForkAccess().getInternalIdKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getForkAccess().getInternalIdEStringParserRuleCall_2_1_0()); 
	    }
		lv_internalId_3_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getForkRule());
	        }
       		set(
       			$current, 
       			"internalId",
        		lv_internalId_3_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleInvariant
entryRuleInvariant returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getInvariantRule()); }
	 iv_ruleInvariant=ruleInvariant 
	 { $current=$iv_ruleInvariant.current; } 
	 EOF 
;

// Rule Invariant
ruleInvariant returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='Invariant' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getInvariantAccess().getInvariantKeyword_0());
    }
	otherlv_1='@' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getInvariantAccess().getCommercialAtKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getInvariantAccess().getNameEStringParserRuleCall_2_0()); 
	    }
		lv_name_2_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getInvariantRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		lv_theorem_3_0=	'theorem' 
    {
        newLeafNode(lv_theorem_3_0, grammarAccess.getInvariantAccess().getTheoremTheoremKeyword_3_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getInvariantRule());
	        }
       		setWithLastConsumed($current, "theorem", true, "theorem");
	    }

)
)?(	otherlv_4='comment' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getInvariantAccess().getCommentKeyword_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getInvariantAccess().getCommentEStringParserRuleCall_4_1_0()); 
	    }
		lv_comment_5_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getInvariantRule());
	        }
       		set(
       			$current, 
       			"comment",
        		lv_comment_5_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))?	otherlv_6='predicate' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getInvariantAccess().getPredicateKeyword_5());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getInvariantAccess().getPredicateEStringParserRuleCall_6_0()); 
	    }
		lv_predicate_7_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getInvariantRule());
	        }
       		set(
       			$current, 
       			"predicate",
        		lv_predicate_7_0, 
        		"EString");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;

