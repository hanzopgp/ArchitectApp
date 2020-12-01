package representation;

import representationtests.*;

/**
 * Classe de test du package representation
 */
public class MainRepresentation {

	public static void main (String[] args){
		boolean ok = true;
		ok = ok && VariableTests.testEquals();
		ok = ok && VariableTests.testHashCode();
		ok = ok && BooleanVariableTests.testConstructor();
		ok = ok && RuleTests.testGetScope();
		ok = ok && RuleTests.testIsSatisfiedBy();
		ok = ok && DifferenceConstraintTests.testGetScope();
		ok = ok && DifferenceConstraintTests.testIsSatisfiedBy();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}

}
