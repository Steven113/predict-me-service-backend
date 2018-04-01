import java.util.HashSet;

public class AssociationRuleItemSet {
	
	private HashSet<String> items = new HashSet<String>();
	
	public AssociationRuleItemSet(HashSet<String> items) {
		this.items = new HashSet<>(items);
	}
	
	public boolean equals(AssociationRuleItemSet other) {
		return items.containsAll(other.items);
	}
	
	@Override
	public int hashCode() {
		return items.toString().hashCode();
	}
	
	public HashSet<String> getItems() {
		return new HashSet<>(items);
	}
	
	public static AssociationRuleItemSet getIntersectionOfTwoItemSets(AssociationRuleItemSet itemSetA, AssociationRuleItemSet itemSetB) {
		HashSet<String> mergedSet = new HashSet<>();
		
		mergedSet.addAll(itemSetA.items);
		mergedSet.addAll(itemSetB.items);
		
		return new AssociationRuleItemSet(mergedSet);
	}

	

}
