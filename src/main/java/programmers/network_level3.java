package programmers;

public class network_level3 {
	static class Solution {
	    
	    int[] unionSet;
	    
	    // union
	    void union(int a, int b) {
	        int aParent = find(a);
	        int bParent = find(b);
	        
	        // 여기서도 a,b보다 aParent, bParent를 넣어서 압축
	        if (aParent != bParent)
	            if (aParent < bParent)
	                unionSet[bParent] = aParent;
	            else
	                unionSet[aParent] = bParent;
	    }
	    
	    // 압축 경로 사용
	    int find(int a) {
	        if (unionSet[a] == a)
	            return a;
	        
	        unionSet[a] = find(unionSet[a]);
	        return unionSet[a]; 
	    }
	    
	    
	    public int solution(int n, int[][] computers) {
	        int answer = 0;
	        
	        unionSet = new int[n + 1];
	        
	        for (int i = 1; i <=n; i++) {
	            unionSet[i] = i;
	        }
	        
	        for (int i = 0; i < n; i ++) {
	            for (int j = 0; j < n; j ++) {
	                if (computers[i][j] == 1 && i != j) {
	                    union(i+1, j+1);
	                }
	            }
	        }
	        
	        for (int i = 1; i <=n; i ++) {
	            if (unionSet[i] == i)
	                answer++;
	        }
	        
	        return answer;
	    }
	}
}

//이해
/*

*/

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
1) 네트워크 dfs로 풀기
https://velog.io/@euneun/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%ACBFSDFS-C-9j2zwwf7
2) 자바 유니온 파인드 개념
https://sjh9708.tistory.com/241

3) 유니온 파인드의 경로 압축
경로 압축을 하면 parent 배열만으로 원래 네트워크의 전체 연결 구조를 알 수 없게 된다.
하지만 유니온 파인드의 목적은 원래 연결 구조를 보존하는 것이 아니라, 원소들이 같은 집합에 속하는지를 빠르게 판단하는 것이기 때문에 괜찮다.


*/

//input
/*

(output)

*/

//다른 사람 코드
/*
1) 유니온 접근법
class Solution {

    int[] parent;

    // 해당 컴퓨터가 속한 집합의 대표 컴퓨터 찾기
    int findParent(int computer) {

        if (parent[computer] == computer) {
            return computer;
        }

        // 경로 압축
        return parent[computer] = findParent(parent[computer]);
    }

    // 두 컴퓨터가 속한 집합 합치기 (4 -> 3 -> 2 -> 1) 경로인 경우 4도 대표 노드인 1일, 3도 대표 노드인 1을 가리키게 함으로써 대표 노드 탐색 과정을 압축시킴
    void unionComputers(int computerA, int computerB) {

        int rootA = findParent(computerA);
        int rootB = findParent(computerB);

        // 서로 다른 네트워크일 때만 합치기
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    public int solution(int n, int[][] computers) {

        parent = new int[n];

        // 처음에는 각 컴퓨터가 별개의 네트워크
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 직접 연결된 컴퓨터들을 같은 집합으로 합치기
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    unionComputers(i, j);
                }
            }
        }

        int answer = 0;

        // 자기 자신이 대표인 컴퓨터의 개수
        // 즉, 서로 다른 네트워크의 개수
        for (int i = 0; i < n; i++) {
            if (findParent(i) == i) {
                answer++;
            }
        }

        return answer;
    }
}

2) dfs 접근법
class Solution {

    boolean[] visited;

    void dfs(int currentComputer, int n, int[][] computers) {

        // 현재 컴퓨터를 방문했다고 표시
        visited[currentComputer] = true;

        // 현재 컴퓨터와 연결된 모든 컴퓨터 확인
        for (int i = 0; i < n; i++) {

            // 연결되어 있으면서 아직 방문하지 않은 컴퓨터라면 탐색
            if (!visited[i] && computers[currentComputer][i] == 1) {
                dfs(i, n, computers);
            }
        }
    }

    public int solution(int n, int[][] computers) {

        int answer = 0;
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {

            // 아직 방문하지 않은 컴퓨터에서 DFS 시작
            if (!visited[i]) {
                dfs(i, n, computers);

                // 한 번의 DFS가 끝나면 하나의 네트워크 탐색 완료
                answer++;
            }
        }

        return answer;
    }
}


*/