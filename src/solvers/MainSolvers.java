package solvers;

import solvertests.*;

/**
 * Classe de tests
 */
public class MainSolvers {
	public static void main (String[] args){
		boolean ok = true;
		ok = ok && AbstractSolverTests.testIsConsistent();
		ok = ok && BacktrackSolverTests.testSolve();
		ok = ok && ArcConsistencyTests.testFilter();
		ok = ok && ArcConsistencyTests.testEnforce();
		ok = ok && ArcConsistencyTests.testEnforceArcConsistency();
		ok = ok && MACSolverTests.testSolve();
		ok = ok && HeuristicMACSolverTests.testSolve();
		ok = ok && NbConstraintsVariableHeuristicTests.testBest();
		ok = ok && DomainSizeVariableHeuristicTests.testBest();
		ok = ok && RandomValueHeuristicTests.testOrdering();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}
}
