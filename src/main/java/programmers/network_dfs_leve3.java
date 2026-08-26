package programmers;

import java.util.*;

public class network_dfs_leve3 {
	

	static class Solution {
	    public int solution(int n, int[][] computers) {
	        int answer = 0;
	        Deque<Integer> queue = new ArrayDeque<>();
	        boolean[] visited = new boolean[n];
	        
	        for (int i = 0; i < n; i ++) {
	            if (!visited[i]) {
	                answer ++;
	                
	                visited[i] = true;
	                queue.addLast(i);
	                
	                while (!queue.isEmpty()) {
	                    int curNode = queue.pollFirst();
	                    
	                    for (int j = 0; j < n; j ++) {
	                        if (!visited[j] && computers[curNode][j] == 1) {
	                            visited[j] = true;
	                            queue.addLast(j);
	                        }
	                    }
	                }
	            }
	        }
	        return answer;
	    }
	}
}
