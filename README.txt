DAA LABS 1 TO 11 - JAVA IMPLEMENTATIONS

Each numbered folder contains the implementations for that lab. Compile and run each Java file separately from its folder, for example:
  javac GraphColoring.java
  java GraphColoring

Folder contents:
1 - Searching & Sorting: QuickSort, MergeSort, BinarySearch
2 - Prim's and Kruskal's: Prim, Kruskal
3 - Dijkstra, Warshall & Floyd: Dijkstra, Warshall, FloydWarshall
4 - Matrix Chain & LCS: MatrixChainMultiplication, LCS
5 - Optimal BST: OptimalBST
6 - TSP (DP): TSP
7 - Max flow & matching: MaxFlow, GaleShapley, KuhnMatching
8 - N-Queens & Subset Sum: NQueens, SubsetSum, BoundedSubsetGenerator
9 - Graph Coloring Problem: GraphColoring, EdgeColoring
10 - Puzzle and Assignment Problem: Puzzle15Compare, CountingAssignment
11 - Knapsack & Assignment Problem: LimitedCountKnapsackSolver, ProfitAssignmentSolver

Lab 9 GraphColoring input: V, then VxV adjacency matrix. Prints chromatic number and largest color group size.
Lab 9 EdgeColoring input: V E, then E endpoint pairs (0-based or consistent vertex labels); prints minimum edge-color count and edge colors.
Lab 10 Puzzle15Compare input: three 4x4 boards in order: main board, board 1, board 2. Use 0 for blank. It computes shortest moves using IDA* with the misplaced-tiles heuristic and reports which board is closer. Difficult states may take a long time.
Lab 10 CountingAssignment input: n then n x n cost matrix. Prints minimum cost and number of optimal assignments.
Lab 11 LimitedCountKnapsackSolver input: n capacity maxItems, then n weights, then n values. Prints maximum profit. Assumes positive weights.
Lab 11 ProfitAssignmentSolver input: n then n x n profit matrix. Prints maximum assignment profit.

These are organized generic Java implementations based on the supplied code and topic names. Confirm exact input/output labels against your lab question before submitting.
