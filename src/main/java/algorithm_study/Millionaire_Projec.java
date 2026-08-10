package algorithm_study;

import java.util.*;
import java.io.*;

public class Millionaire_Projec {
	static private int cal_max(int start, int n, int[] arr) {
		// start가 끝이거나 끝 바로 앞일 때
		while (start < n - 1) {
			// start가 0이 아니면서 연속된 숫자일 때
			if (arr[start] == arr[start + 1] && start != 0) {
				start += 1;
				continue ;
			}

			// start가 0일 때와 0이 아닐때 분기 (0이 아니라면 해당 index가 최고값이었음)
			if (start != 0)
				start += 1;
			
			int max = arr[start];
			int max_index = start;
			for (int i = start; i < n; i++) {
				if (max < arr[i]) {
					max = arr[i];
					max_index = i;
				}
			}
			
			// 처음 값이 제일 큰 경우
			if (max_index == 0)
				continue;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int total = 0;
			int start = 0;
			int ob_count = 0;
			int max = cal_max(start, n, arr);
			
			// 계산을 해야하는 경우에만 for문
			if (max != -1) {	
				for (int i = 0; i < n; i++) {
					if (arr[i] == max) {
						total += ob_count * max;
						ob_count = 0;
						start = i++;
						max = cal_max(start, n, arr);
						if (max == -1)
							break;
						continue;
					}
					total -= arr[i];
					ob_count++;
				}
			}
		}		
	}
}

// 코테 질문 읽고 다시 풀기

// 이해
// total / object_count
// 연속된 숫자에 대해 숫자가 낮은 날에는 total에서 값을 뺀 뒤 object_count를 늘려서 물건을 챙기고
// 숫자가 높은 날에는 물건을 팔아서 total을 늘ㅑㅐ리는 문제이다.
// 연속된 숫자에 대해 total 값이 가장 많이 생성될 수 있는 알고리즘을 작성해야 한다.

// 문제 분류

// 로직
// 연속된 숫자 n개 중 자가 가장 큰 값이 있는 index가 x1이라고 하면 x1까지 물건을 사고 x1에 물건을 모두 판다.
// 그 다음 숫자 중 가장 큰 값이 x2라면 x2까지 물건을 사고 x2 index에서 물건을 모두 판다.
// 해당 행위를 index가 n이 될 때까지 반복한다
// 모든 숫자가 내림차순인 경우 값은 0이다.

// 코드 레벨 (머릿속으로 코드 레벨 로직 구상하기)
// 전체적인 틀은 머리로 구상 가능했음
// 그러나 실제 숫자열을 돌면서 n개의 숫자 중 x1에서 물건을 판 뒤 어떤 조건에서 알고리즘이 끝나고 어떤 조건에서
// 알고리즘이 끝나지 않는지, 같은 숫자가 연속되는 경우의 조건들, 사지 않고 넘길 수 있다는 조건 등은 문제를 풀다가 떠올랐음
// (이것도 구현 전에 머릿속으로 어느정도 윤곽을 그리고 들어가려고 하는건 욕심인가?)

// 배경지식
//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//StringTokenizer st = new StringTokenizer(br.readLine());
//
//while (st.hasMoreTokens()) {
//    int num = Integer.parseInt(st.nextToken());
//    System.out.println(num);
//}
//
// br.readLine() <- 한 글자 읽을 땐 StringTokenizer 사용 안 하고 바로 사용