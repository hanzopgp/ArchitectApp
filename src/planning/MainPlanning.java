package planning;
import planningtests.*;

public class Main {
    public static void main(String[] args){
        boolean ok = true;
        ok = ok && BasicActionTests.testIsApplicable();
        ok = ok && BasicActionTests.testSuccessor();
        ok = ok && BasicActionTests.testGetCost();
        ok = ok && BasicGoalTests.testIsSatisfiedBy();
        //ok = ok && DFSPlannerTests.testPlan();
        ok = ok && BFSPlannerTests.testPlan();
        //ok = ok && AStarPlannerTests.testPlan()
        ok = ok && DijkstraPlannerTests.testPlan();
        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}






// javac -cp ".:planningtests.jar" -d build planning/*.java
// java -cp "build:planningtests.jar" planning.Main