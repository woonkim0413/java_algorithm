package algorithm_study;

import java.util.*;
import java.io.*;

public class shelf_of_janghun_DP_1486 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int caseT = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= caseT; t ++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// 모든 점원의 높이 합
			int totalHightSum = 0;
			// 점원의 키 배열
			List<Integer> staffHights = new ArrayList<>();
				
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i ++) {
				int staffHight = Integer.parseInt(st.nextToken());
				staffHights.add(staffHight);
				totalHightSum += staffHight;
			}
			
			// 점원 조합으로 만들 수 있는 높이 (index가 높이이므로 배열 size는 전체 높이 + 1)
			boolean[] beHight = new boolean[totalHightSum + 1];
			beHight[0] = true;

			
			totalHightSum = 0;
			// 모든 점원을 상대로 경우의 수를 체크한다
			for (int high : staffHights) {
				totalHightSum += high;
				
				// 기존 전체 높이 합에 high라는 경우의 수를 합하면
				// hight ~ 전체 높이합 + hight 사이의 값 중 직원들의 키 조합으로 만들 수 있는 값이 생길 수 있다.
				// 아래는 이를 체크하기 위한 식이다.
				
				// 뒤에서부터 체크하는 이유는 앞에서부터 체크하면 앞의 조합 체크를 통해 파악된 값을 기존 beHight 배열에
				// 넣는 경우 이 값이 뒤 조합을 확인하며 자연스럽게 다시 사용될 수 있기 때문이다
				// ex) 현재 hight 6, beHight[2]가 true면 beHight[2+hight]에 true를 넣게 된다 
				// -> 후에 beHight[8]을 검사해서 beHight[14]에 true를 넣게 됨 (배열 두 개 만들면 해결할 수 있긴 하겠네)
				for (int checkHight = totalHightSum; checkHight >= high; checkHight--) {
					if (beHight[checkHight - high]) 
						beHight[checkHight] = true;
				}
			}
			
			// B부터 탐색해서 가장 먼저 발견된 조합으로 만들 수 있는 값을 출력
			for (int i = B; i <= totalHightSum; i ++) {
				if (beHight[i]) {
					System.out.println("#" + t + " " + (i - B));
					break;
				}
			}
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

*/