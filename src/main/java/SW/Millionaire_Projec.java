package algorithm_study;

import java.util.*;
import java.io.*;

public class Millionaire_Projec {
	
	// 현재 위치에서의 max index 반환
	static private int cal_max_index(int start, int n, int[] arr) {
		int max = arr[start];
		int max_index = start;
		
		// start부터 끝까지 가장 큰 값의 index 탐색
		for (int i = start + 1; i < n; i++) {
			if (max < arr[i]) {
				max = arr[i];
				max_index = i;
			}
		}
		if (max_index == start)
			max_index = -1;
		
		return max_index;
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
			
			// start가 마지막 index가 될 때까지 반복
			while (start < n - 1) {
				
				// 현재 start부터 끝까지 가장 큰 값의 index 탐색
				int max_index = cal_max_index(start, n, arr);
				
				// start가 이미 가장 큰 값이면
				// 더이상 구매 멈추고 종료
				if (max_index == -1) {
					System.out.println("index: "+ start + " / 앞으로 내림차순");
					break;
				}
				
				int max = arr[max_index];
				int ob_count = 0;
				
				// 최대값 전까지 물건 구매
				for (int i = start; i < max_index; i++) {
					total -= arr[i];
					ob_count++;
				}
				
				// 최대값에서 가지고 있는 물건 전부 판매
				total += ob_count * max;
				
				// 최대값 다음 index부터 다시 탐색
				start = max_index + 1;
			}
			
			System.out.println("#" + (t + 1) + " " + total);
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
//
// -> 코테 실력에서 중요한 감각은 "예외를 잘 처리하는 능력"보다 "예외가 적은 표현을 찾는 능력"을 갖는 것이다.

// 배운 것
// 1) buuferedReader
//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//StringTokenizer st = new StringTokenizer(br.readLine());
//
//while (st.hasMoreTokens()) {
//    int num = Integer.parseInt(st.nextToken());
//    System.out.println(num);
//}
//
// br.readLine() <- 한 글자 읽을 땐 StringTokenizer 사용 안 하고 바로 사용

//2) 배열에서 값과 index의 정보량 
//배열 문제에서 값과 위치가 둘 다 필요한데 하나만 반환해야 한다면, index를 반환하는 쪽이 정보량이 더 큰 경우가 많아.