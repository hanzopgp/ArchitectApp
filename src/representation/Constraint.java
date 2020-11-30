package representation;

import java.util.*;

public interface Constraint{
	
	Set<Variable> getScope();
	
	boolean isSatisfiedBy(Map<Variable, Object> tab);
	
}
