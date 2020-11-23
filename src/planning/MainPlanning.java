package planning;
import planningtests.*;

public class MainPlanning {
    public static void main(String[] args){
        boolean ok = true;
        ok = ok && BasicActionTests.testIsApplicable();
        //ok = ok && BasicActionTests.testSuccessor();
        //ok = ok && BasicActionTests.testGetCost();
        //ok = ok && BasicGoalTests.testIsSatisfiedBy();
        //ok = ok && DFSPlannerTests.testPlan();
        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}
