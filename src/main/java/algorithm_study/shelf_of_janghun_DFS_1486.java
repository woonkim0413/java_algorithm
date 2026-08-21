package algorithm_study;

import java.util.*;
import java.io.*;

public class shelf_of_janghun_DFS_1486 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int B;
	static int hightSum;
	
	// 현재 deepth의 staff값은 이전 deepth에서 계산됨 (마지막 deep의 methodStack을 안 쌓기 위해 설계)
	static void dfs(int index, int sum, int[] staffHights) {
		if (sum >= B) {
			hightSum = Math.min(hightSum, sum);
			return ;
		}
		if (index == N - 1 || hightSum == B)
			return ;

		// 다음 staff hight 미포함
		dfs(index + 1, sum, staffHights);
		
		// 다음 staff hight 포함
		dfs(index + 1, sum + staffHights[index + 1], staffHights);
	}
	
	public static void main(String[] args) throws Exception {
		int caseT = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= caseT; t ++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			hightSum = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			int[] staffHights = new int[N];			
			for (int i = 0; i < N; i ++) {
				staffHights[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, staffHights[0], staffHights);
			
			System.out.println("#" + t + " " + (hightSum - B));
		}
	}
}
//이해
/*
점원들의 조합을 통해 만들 수 있는 탑의 높이를 순회하며
B 이상의 탑 중 가장 높이가 작은 탑 높이를 찾은 뒤 그 높이 - B를 출력한다.
만약 B 높이를 쌓을 수 있다면 0을 출력하면 된다.
*/

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*

*/

//input
/*
3
4 16
3 3 5 6
5 10
1 2 3 4 8
3 12
4 7 10
(output)
#1 1
#2 0
#3 2
*/

//다른 사람 코드
/*
1) 재우 코드 (조합 방식으로 풀었다)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Solution {

    // B 이상이 되는 탑의 높이를 저장
    static ArrayList<Integer> possibleHeights = new ArrayList<>();

    // employees : 직원들의 키
    // start     : 이번 탐색을 시작할 인덱스
    // selectCnt : 앞으로 더 선택해야 하는 직원 수
    // target    : 선반의 높이 B
    // sum       : 현재까지 선택한 직원들의 키 합
    static void combination(
            int[] employees,
            int start,
            int selectCnt,
            int target,
            int sum
    ) {

        // 원하는 수만큼 직원을 모두 선택했다면
        if (selectCnt == 0) {

            // 선반 높이 이상인 경우만 정답 후보로 저장
            if (sum >= target) {
                possibleHeights.add(sum);
            }

            return;
        }

        // start부터 한 명씩 선택
        for (int i = start; i < employees.length; i++) {

            combination(
                    employees,
                    i + 1,                  // 다음 직원부터 탐색
                    selectCnt - 1,          // 한 명 선택했으므로 1 감소
                    target,
                    sum + employees[i]      // 선택한 직원의 키를 합산
            );
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] employees = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                employees[i] = Integer.parseInt(st.nextToken());
            }

            // 1명 선택
            // 2명 선택
            // ...
            // N명 선택
            // 모든 조합을 검사
            for (int selectCnt = 1; selectCnt <= N; selectCnt++) {
                combination(employees, 0, selectCnt, B, 0);
            }

            // B 이상인 높이들을 오름차순 정렬
            Collections.sort(possibleHeights);

            // B 이상인 값 중 최소값
            int minimumHeight = possibleHeights.get(0);

            // 선반과의 차이
            int answer = minimumHeight - B;

            System.out.printf("#%d %d\n", testCase, answer);

            // 다음 테스트 케이스를 위해 초기화
            possibleHeights.clear();
        }
    }
}
*/