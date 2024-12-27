# AStar_Search_Algorithm_Using_Java

## Part 1: A* Search problem description

![Search Grid](/Search%20Grid.png)

The grids* represent two problem environments where an agent is trying to find a path from the start
location (S) to the goal location (G). The agent can move to an adjacent square provided the square is white or 
grey. The agent cannot move to a black square which represents a wall. No diagonal movement is allowed. Moving 
into a white square has a cost of 1. Moving into a grey square has a cost of 3.

### Programming language option: Java
Starter code can be found in the package uk.ac.hw.macs.search, which is a set of classes that can be used to 
represent a search problem. To implement a specific search problem, you will need to do the following:

1. Define a class that implements the State interface. This should include the following:
a. A method for determining whether a state is a goal state (isGoal())
b. A method for computing the heuristic value of a state (getHeuristic())
2. Define a class that implements the SearchOrder interface. This interface has one public method, 
addToFringe, that is used to add a set of nodes to the frontier. You can use the costs and/or heuristic 
values to determine the order that nodes are added to the frontier

The classes in the uk.ac.hw.macs.search.example package show examples of these two interfaces being used to 
implement depth-first search and breadth-first search, as well as a simple integer-based state. The Main class in 
this package shows an example of how to use the classes.

To solve the problem, you will need to implement the following:
1. An encoding of the state in the grid by implementing the State interface appropriately, including 
methods for detecting a goal state and computing a heuristic value. You should use the Manhattan 
distance heuristic for generating heuristic values in your search.
2. A class implementing A* search by implementing the SearchOrder interface and implementing 
addToFringe appropriately.


*Grids available in the repository
