package solvers;

import solvertests.*;

public class MainSolvers {
	public static void main (String[] args){
		boolean ok = true;
		ok = ok && AbstractSolverTests.testIsConsistent();
		ok = ok && BacktrackSolverTests.testSolve();
		ok = ok && ArcConsistencyTests.testFilter();
		ok = ok && ArcConsistencyTests.testEnforce();
		//ok = ok && ArcConsistencyTests.testEnforceArcConsistency();
		//ok = ok && MACSolverTests.testSolve();                         /* NOT GOOD */
		//ok = ok && HeuristicMACSolverTests.testSolve();                /* NOT GOOD */
		ok = ok && NbConstraintsVariableHeuristicTests.testBest();     /* NOT GOOD */
		//ok = ok && DomainSizeVariableHeuristicTests.testBest();        /* NOT GOOD */
		//ok = ok && RandomValueHeuristicTests.testOrdering();           /* NOT GOOD */
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}
}
