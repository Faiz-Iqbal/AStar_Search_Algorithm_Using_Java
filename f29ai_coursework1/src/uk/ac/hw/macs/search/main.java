package uk.ac.hw.macs.search;
/**
 * @author mohammedfaiziqbal
 */
import java.util.Scanner;

public class main {

	// Calculate Manhattan distance between two points
	public static int calculateManhattanDistance(int startX, int startY, int goalX, int goalY) {
		int horizontalDistance = Math.abs(goalX - startX); // Calculate horizontal distance
		int verticalDistance = Math.abs(goalY - startY); // Calculate vertical distance
		return horizontalDistance + verticalDistance; // Calculate total Manhattan distance
	}

	// Generate adjacent positions for a given node
	public static void generateAdjacentPositions(GridState[][] grid, int parentX, int parentY, Node parent, int width,
			int height) {
		GridState[] adjacentPositions = new GridState[4]; // A node can have at most 4 adjacent positions

		GridState northAdjacent = new GridState(parentX, parentY - 1, width, height); // Adjacent position to the north
		GridState southAdjacent = new GridState(parentX, parentY + 1, width, height); // Adjacent position to the south
		GridState eastAdjacent = new GridState(parentX + 1, parentY, width, height); // Adjacent position to the east
		GridState westAdjacent = new GridState(parentX - 1, parentY, width, height); // Adjacent position to the west

		adjacentPositions[0] = northAdjacent;
		adjacentPositions[1] = eastAdjacent;
		adjacentPositions[2] = southAdjacent;
		adjacentPositions[3] = westAdjacent;

		for (int i = 0; i < adjacentPositions.length; i++) {
			GridState nearby = adjacentPositions[i];
			if (nearby.isValid()) {
				GridState current = grid[nearby.getX()][nearby.getY()];
				Node child = current.getNode();
				parent.addChild(child, current.getCost());
			}
		}
	}

	// Populate the grid with nodes and heuristic values
	public static void populateGrid(GridState[][] grid, int goalX, int goalY, int gridWidth, int gridHeight) {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				int h = calculateManhattanDistance(i, j, goalX, goalY); // The heuristic

				GridState current;
				if (i == goalX && j == goalY) {
					current = new GridState(i, j, true, gridWidth, gridHeight);
				} else {
					current = new GridState(i, j, gridWidth, gridHeight);
				}
				current.setHeuristic(h);
				Node node = new Node(current);
				grid[i][j] = new GridState(i, j, gridWidth, gridHeight);
				grid[i][j].setNode(node);
			}
		}
	}

	// Set black and gray nodes on the grid
	public static void setBlackGrayNodes(GridState[][] grid, int[] xBlack, int[] yBlack, int[] xGrey, int[] yGrey,
			int width, int height) {
		if (xBlack.length != yBlack.length || xGrey.length != yGrey.length) {
			System.err.println("X and Y lists must be of the same length.");
			System.exit(0);
		}

		for (int i = 0; i < width; i++) {
			
			for (int j = 0; j < height; j++) {
				// Comments
				for (int a = 0; a < xBlack.length; a++) {
					// Set nodes with black coordinates
					int x = xBlack[a]; 
					int y = yBlack[a]; 
					if (x == i && y == j) {
						// Set cost for black nodes
						grid[i][j].setCost(Integer.MAX_VALUE);
					}
				}
				
				for (int a = 0; a < xGrey.length; a++) {
					int x = xGrey[a]; // Set nodes with gray coordinates
					int y = yGrey[a]; // Set nodes with gray coordinates
					
					if (x == i && y == j) {
						// Set cost for gray nodes

						grid[i][j].setCost(3);
					}
				}
			}
		}
	}

	// Populate neighbors for each node in the grid
	public static void populateNeighbours(GridState[][] grid, int gridWidth, int gridHeight) {
		for (int x = 0; x < gridWidth; x++) { 
			for (int y = 0; y < gridHeight; y++) {
				Node currentNode = grid[x][y].getNode(); 
				generateAdjacentPositions(grid, x, y, currentNode, gridWidth, gridHeight);
			}
		}
	}

	// Perform A* search on the grid
	static void gridSearch(int startX, int startY, int goalX, int goalY, int[] blacknodeX, int[] blacknodeY,
			int[] greynodeX, int[] greynodeY, int gridWidth, int gridHeight) {
		GridState[][] grid = new GridState[gridWidth][gridHeight];

		// Populate the grid with nodes and heuristics
		populateGrid(grid, goalX, goalY, gridWidth, gridHeight);
		setBlackGrayNodes(grid, blacknodeX, blacknodeY, greynodeX, greynodeY, gridWidth, gridHeight);
		populateNeighbours(grid, gridWidth, gridHeight);

		// Run the A* Search
		Node startNode = grid[startX][startY].getNode();
		SearchOrder order = new Astarsearch();
		SearchProblem so = new SearchProblem(order);
		so.doSearch(startNode);
	}

	public static void main(String[] args) {
		// Scanner to take user input
		Scanner scan = new Scanner(System.in);
		int choice;

		int grid1Width = 6;
		int grid1Height = 4;
		int grid2Width = 5;
		int grid2Height = 5;

		// Grid 1 values
		int startX1 = 0;
		int startY1 = 0;
		int goalX1 = 4;
		int goalY1 = 3;

		// Grid 2 values
		int startX2 = 0;
		int startY2 = 0;
		int goalX2 = 4;
		int goalY2 = 4;

		// Grid1 Black nodes
		int[] blackX1 = new int[] { 2, 3, 3 };
		int[] blackY1 = new int[] { 1, 2, 3 };

		// Grid1 Grey nodes
		int[] greyX1 = new int[] { 1, 1, 2, 4, 4 };
		int[] greyY1 = new int[] { 0, 1, 2, 1, 2 };

		// Grid2 Black nodes
		int[] blackX2 = new int[] { 0, 1, 2, 2, 3 };
		int[] blackY2 = new int[] { 3, 1, 1, 2, 1 };

		// Grid2 Grey nodes
		int[] greyX2 = new int[] { 0, 2, 4, 4 };
		int[] greyY2 = new int[] { 1, 3, 1, 3 };

		// Result
		System.out.println("+-----------------------------+");
		System.out.println("|    Perform A* Search on:    |");
		System.out.println("|-----------------------------|");
		System.out.println("| 1.   Problem Grid 1         |");
		System.out.println("| 2.   Problem Grid 2         |");
		System.out.println("+-----------------------------+");
		choice = scan.nextInt();

		if (choice == 1) {
			System.out.println("\n---------------- Problem Grip 1 ----------------\n");
			gridSearch(startX1, startY1, goalX1, goalY1, blackX1, blackY1, greyX1, greyY1, grid1Width, grid1Height);
		} else if (choice == 2) {
			System.out.println("\n---------------- Problem Grid 2 ----------------\n");
			gridSearch(startX2, startY2, goalX2, goalY2, blackX2, blackY2, greyX2, greyY2, grid2Width, grid2Height);
		}else {
			System.err.println("Please enter 1 or 2");
		}
	}

}
