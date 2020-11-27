package solvers;

import solvertests.*;

public class MainSolvers {
	public static void main (String[] args){
		boolean ok = true;
		ok = ok && AbstractSolverTests.testIsConsistent();
		ok = ok && BacktrackSolverTests.testSolve();
		ok = ok && ArcConsistencyTests.testFilter();
		//ok = ok && ArcConsistencyTests.testEnforce();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}
}
