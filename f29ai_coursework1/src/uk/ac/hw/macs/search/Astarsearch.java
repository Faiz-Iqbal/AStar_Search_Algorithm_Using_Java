package uk.ac.hw.macs.search;
/**
 * @author mohammedfaiziqbal
 */

import java.util.List;
import java.util.Set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

//This class implements the A* search
public class Astarsearch implements SearchOrder {
	@Override
	public void addToFringe(List<FringeNode> frontier, FringeNode parent, Set<ChildWithCost> children) {
		// Iterate through the set of children with their associated costs
		Iterator<ChildWithCost> iterator = children.iterator();
		while (iterator.hasNext()) {
			ChildWithCost child = iterator.next();
			
			// Create a new FringeNode with the child node, parent node, and the cost to explore the child
			frontier.add(new FringeNode(child.node, parent, child.cost));
		}
		// Sort the frontier based on a custom comparator (Fringenodecompare)
		frontier.sort(new Fringenodecompare());
	}

	// Class for sorting the FringeNode based on their f-values. f(n) = g(n)+h(n) 
	public class Fringenodecompare implements Comparator<FringeNode> {
		@Override
		// Calculate the f-values for each node, which is the sum of the heuristic value and the cost to reach the node
		public int compare(FringeNode node1, FringeNode node2) {
			int fValue1 = (int) node1.node.getValue().getHeuristic() + node1.gValue; 
			int fValue2 = (int) node2.node.getValue().getHeuristic() + node2.gValue; 
			// Compare the f-values and return the result to order nodes in the frontier
			return fValue1 - fValue2;
		}
	}

	
}
