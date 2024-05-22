package uk.ac.hw.macs.search;
/**
 * @author mohammedfaiziqbal
 */

public class GridState implements State {

	// Integer variable for heuristic value
	private int heuristicValue;

	// Integer variables for the y and x positions
	private int x, y;

	// Integer variables for height and width of the grid.
	private int gridHeight, gridWidth;

	// Initialize and set the cost to 1, which is the default cost for each square.
	// Grey squares have a cost of 3, which is defined in the "main file.
	private int cost = 1;

	// Boolean variable to indicate if this node is the goal node.
	private boolean isGoal;

	// Initialize and declare a reference to a Node object; this can be used for
	// linking nodes
	private Node node;

	// Constructor for a GridState representing a goal node
	public GridState(int x, int y, boolean isGoal, int gridWidth, int gridHeight) {
		this.isGoal = isGoal;
		this.x = x;
		this.y = y;
		this.gridHeight = gridHeight;
		this.gridWidth = gridWidth;

		// Initialize cost based on node type
		if (isGoal) {
			this.cost = 0; // Or any other appropriate value
		} else {
			this.cost = 1;
		}
	}
	
	// Constructor for a GridState representing a non-goal node
	public GridState(int x, int y, int gridWidth, int gridHeight)
	{
		this(x, y, false, gridWidth, gridHeight);
	}

	// Check if the current position is valid within the grid
	public boolean isValid()
	{
		boolean isXValid = (x >= 0) && (x <= gridWidth - 1);
	    boolean isYValid = (y >= 0) && (y <= gridHeight - 1);
	    return isXValid && isYValid;
	}
	
	// Get the x-coordinate of the position
	public int getX() {
		return x;
	}
	
	// Get the y-coordinate of the position
	public int getY() {
		return y;
	}
	
	// Get the cost associated with the node
	public int getCost() {
		return cost;
	}
	
	// Set the cost associated with the node
	public void setCost(int setCost) {
		this.cost = setCost;
	}
	
	// Get the Node object associated with this GridState
	public Node getNode() {
		return node;
	}
	
	// Set the Node object associated with this GridState
	public void setNode (Node n) {
		this.node = n;
	}

	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return this.isGoal;
	}

	@Override
	public int getHeuristic() {
		// TODO Auto-generated method stub
		return heuristicValue;
	}
	
	public void setHeuristic(int h){
		this.heuristicValue = h;
	}
	
	public String toString() {
	    return "Grid [Position: ["+x+","+y+"], Goal: " + isGoal + "]";
	}


}
