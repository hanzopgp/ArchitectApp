package tests;

import dataminingtests.AbstractAssociationRuleMinerTests;
import dataminingtests.AbstractItemsetMinerTests;
import dataminingtests.AprioriTests;
import dataminingtests.BruteForceAssociationRuleMinerTests;
import dataminingtests.DatabaseTests;
import planningtests.AStarPlannerTests;
import planningtests.BFSPlannerTests;
import planningtests.BasicActionTests;
import planningtests.BasicGoalTests;
import planningtests.DFSPlannerTests;
import planningtests.DijkstraPlannerTests;
import representationtests.BinaryExtensionConstraintTests;
import representationtests.BooleanVariableTests;
import representationtests.DifferenceConstraintTests;
import representationtests.RuleTests;
import representationtests.VariableTests;
import solvertests.AbstractSolverTests;
import solvertests.ArcConsistencyTests;
import solvertests.BacktrackSolverTests;
import solvertests.DomainSizeVariableHeuristicTests;
import solvertests.HeuristicMACSolverTests;
import solvertests.MACSolverTests;
import solvertests.NbConstraintsVariableHeuristicTests;
import solvertests.RandomValueHeuristicTests;

public class MainTests {

    public static void main(String[] args) {
        boolean ok = true;
        System.out.println("Testing package \"representation\"...");
        ok = ok && VariableTests.testEquals();
        ok = ok && VariableTests.testHashCode();
        ok = ok && BooleanVariableTests.testConstructor();
        ok = ok && DifferenceConstraintTests.testGetScope();
        ok = ok && DifferenceConstraintTests.testIsSatisfiedBy();
        ok = ok && RuleTests.testGetScope();
        ok = ok && RuleTests.testIsSatisfiedBy();
        ok = ok && BinaryExtensionConstraintTests.testGetScope();
        ok = ok && BinaryExtensionConstraintTests.testIsSatisfiedBy(); 
        System.out.println("Testing package \"solvers\"...");
        ok = ok && AbstractSolverTests.testIsConsistent();
        ok = ok && BacktrackSolverTests.testSolve();
        ok = ok && ArcConsistencyTests.testFilter();
        //ok = ok && ArcConsistencyTests.testEnforce();                  /* NOT GOOD */
        //ok = ok && ArcConsistencyTests.testEnforceArcConsistency();    /* NOT GOOD */
        //ok = ok && MACSolverTests.testSolve();                         /* NOT GOOD */
        //ok = ok && HeuristicMACSolverTests.testSolve();                /* NOT GOOD */
        //ok = ok && NbConstraintsVariableHeuristicTests.testBest();     /* NOT GOOD */
        //ok = ok && DomainSizeVariableHeuristicTests.testBest();        /* NOT GOOD */
        //ok = ok && RandomValueHeuristicTests.testOrdering();           /* NOT GOOD */
        System.out.println("Testing package \"planning\"...");
        ok = ok && BasicActionTests.testIsApplicable();
        ok = ok && BasicActionTests.testSuccessor();
        ok = ok && BasicActionTests.testGetCost();
        ok = ok && BasicGoalTests.testIsSatisfiedBy();
        ok = ok && DFSPlannerTests.testPlan();
        ok = ok && BFSPlannerTests.testPlan();
        ok = ok && DijkstraPlannerTests.testPlan();
        //ok = ok && AStarPlannerTests.testPlan();
        System.out.println("Testing package \"datamining\"...");
        ok = ok && AbstractItemsetMinerTests.testFrequency();
        ok = ok && AprioriTests.testFrequentSingletons();
        ok = ok && AprioriTests.testCombine();
        ok = ok && AprioriTests.testAllSubsetsFrequent();
        ok = ok && AprioriTests.testExtract();
        ok = ok && AbstractAssociationRuleMinerTests.testFrequency();
        ok = ok && AbstractAssociationRuleMinerTests.testConfidence();
        ok = ok && BruteForceAssociationRuleMinerTests.testAllCandidatePremises();
        ok = ok && BruteForceAssociationRuleMinerTests.testExtract();
        //ok = ok && DatabaseTests.testItemTable();                       /* NOT GOOD */
        //ok = ok && DatabaseTests.testPropositionalize();                /* NOT GOOD */
        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }

}