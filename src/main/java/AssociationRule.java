import java.util.LinkedList;

public class AssociationRule {
	
	String [] preconditions = null;
	String [] postconditions = null;
	
	public AssociationRule(LinkedList<String> preconditions, LinkedList<String> postconditions) {
		this.preconditions = (String [])preconditions.stream().map((itemStr) -> new String(itemStr)).toArray();
		this.postconditions = (String [])postconditions.stream().map((itemStr) -> new String(itemStr)).toArray();
	}

}
