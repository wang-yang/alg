public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid==null || grid.length==0 || grid[0].length==0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] reach = new int[m][n];
        int houseNum = 0;
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    houseNum++;
                    int level = 0;
                    boolean[][] visited = new boolean[m][n];
                    LinkedList<Integer> queue = new LinkedList<Integer>();
                    queue.offer(i*n+j);
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int t=0; t<size; t++) {
                            int cur = queue.poll();
                            int x = cur/n;
                            int y = cur%n;
                            for (int[] dir : directions) {
                                int xnew = x + dir[0];
                                int ynew = y + dir[1];
                                if (xnew>=0 && xnew<m && ynew>=0 && ynew<n && !visited[xnew][ynew] && grid[xnew][ynew]==0) {
                                    queue.offer(xnew*n+ynew);
                                    visited[xnew][ynew] = true;
                                    dist[xnew][ynew] += level+1;
                                    reach[xnew][ynew]++;
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        
        int minDist = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j]==0 && reach[i][j] == houseNum) {
                    minDist = Math.min(minDist, dist[i][j]);
                }
            }
        }
        return minDist==Integer.MAX_VALUE? -1 : minDist;
    }
ven a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), find the place to build a post office, the distance that post office to all the house sum is smallest. Return the smallest distance. Return -1 if it is not possible.

 Notice

You cannot pass through wall and house, but can pass through empty.
You only build post office on an empty.
Have you met this question in a real interview? Yes
Example
Given a grid:

0 1 0 0 0
1 0 0 2 1
0 1 0 0 0
return 8, You can build at (1,1). (Placing a post office at (1,1), the distance that post office to all the house sum is smallest.)

Challenge 
Solve this problem within O(n^3) time.

Tags 
Breadth First Search Zenefits Google}
