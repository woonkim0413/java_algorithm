package algorithm_study;

import java.util.*;
import java.io.*;

public class vehicle_repairShop {
	static Scanner sc = new Scanner(System.in);
	static PriorityQueue<Custom> receptQ = new PriorityQueue<>((a, b) -> Integer.compare(a.CN, b.CN)); // 대기 큐
	// 먼저 접수가 끝난 사람이 우선, 같은 시간에 접수가 끝났다면 접수 창구 번호가 작은 고객이 우선
	static PriorityQueue<Custom> repairQ = new PriorityQueue<>((a, b) -> {
		if (a.WT != b.WT)
				return Integer.compare(a.WT, b.WT);
		return Integer.compare(a.receptN, b.receptN);
	});
	static Custom[] recept; // 작업 중
	static Custom[] repair; 
	static int[] receptW;
	static int[] repairW;
	static int[] getCT;
	static int[] targetC = new int[2];
	static int sum;
	
	
	public static void main(String[] args) {
		int CT = sc.nextInt();
		for (int T = 1; T <= CT; T++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();

			targetC[0] = sc.nextInt();
			targetC[1] = sc.nextInt();
			
			// 창구들 작업시간 저장
			receptW = new int[N+1];
			for (int i = 1; i <= N; i ++) {
				receptW[i] = sc.nextInt();
			}
			repairW = new int[M+1];
			for (int i = 1; i <= M; i ++) {
				repairW[i] = sc.nextInt();
			}
			
			// 고객 도착시간 저장
			getCT = new int[K];
			for (int i = 0; i < K; i ++) {
				getCT[i] = sc.nextInt();
			}
			
			// 시뮬
			int C = 0;
			int completed = 0;
			int time = 0;
			sum = 0;
			
			recept = new Custom[N+1];
			repair = new Custom[M+1];
			while (completed < K) {
				// 고객 도착
				if (C < K && getCT[C] == time) {
					receptQ.add(new Custom(C+1)); // 고객 번호는 1부터 시작
					C++;
					continue;
				}
				
				// 접수
				for (int i = 1; i <= N; i ++) { // 빼서 정비 대기 큐에 넣기
					if (recept[i] == null) {
						continue;
					}
					if (recept[i].WT == time) {
						repairQ.offer(recept[i]);
						recept[i] = null;
					}
				}
				for (int i = 1; i <= N; i ++) { // 접수 대기 큐에서 빼서 넣기
					if (recept[i] == null && !receptQ.isEmpty()) { // 창구는 비었고 기다리는 고객이 있을 때
						recept[i] = receptQ.poll();
						recept[i].receptN = i;
						recept[i].WT = time + receptW[i];
					}
				}
				
				// 정비
				for (int i = 1; i <= M; i ++) { // 지갑 고객인지 확인
					if (repair[i] == null) {
						continue;
					}
					if (repair[i].WT == time) {
						Custom custom = repair[i];
						repair[i] = null;
						completed ++;
						
						if (custom.receptN == targetC[0] && custom.repairN == targetC[1]) {
							sum += custom.CN;
						}
					}
				}
				for (int i = 1; i <= M; i ++) { // 대기 큐에서 빼서 넣기
					if (repair[i] == null && !repairQ.isEmpty()) {
						repair[i] = repairQ.poll();
						repair[i].repairN = i;
						repair[i].WT = time + repairW[i];
					}
				}
				
				time ++;
			}
			if (sum == 0) sum = -1;
			System.out.println("#"+T+" "+sum);
		}
	}
	
	
	static class Custom {
		int WT; // 서비스 남은 시간
		int receptN;
		int repairN;
		int CN;
		
		public Custom(int cN) {
			super();
			WT = -1;
			this.receptN = -1;
			this.repairN = -1;
			CN = cN;
		}
	}
}

//로직 (논리 + 코드 레벨)
/*
 * 
	 접수창고와 정비창구는 각각 고유의 처리 시간 가짐
	 모든 접수창고 및 정비창구가 가득 차면 고객은 대기해야함
	 고객의 처음 접수창고 대기실 도착 시간은 서로 다름
	 여러 고객이 하나의 정비창구로 가려고 하면 접수번호가 작은 고객이 우선된다
	 창구가 여러개 비어있는 경우 창구 번호가 작은 곳이 우선된다
	 지갑을 분실한 고객과 같은 접수 창구와 같은 정비 창구를 이용한 고객의 고객번호들을 찾아 그 합을 출력하는 프로그램을 작성한다

	 로직
	 창구는 array에 담는다 (index 1부터 시작하도록 담기)
	 고객 도착 정보도 array에 담는다
	 각 창구에서 서비스를 받고 있는 인원은 array에 저장하며 고객 class의 WT을 while을 돌리며 1씩 빼고 0이면 대기 큐에 넣는다.
	 대기하고 있는 고객은 PriorityQueue에 저장한다 (고객번호, 접수창고 번호를 사용해서 정렬)
	 while문의 1회전을 time으로 두고 회전마다 창구 array를 탐색한다.
	 null인 자리를 발견하면 해당 자리에 고객을 넣는다.

*/

//배운 것
/*

*/

//input
/*
1
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4
-> 7
 * 
 * 
10
1 1 2 1 1
5
7
7 10
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4
2 1 4 2 1
2 5
1
0 1 3 10
4 1 10 3 1
4 6 4 8
1
0 3 4 4 5 6 9 9 9 10
2 2 8 2 1
10 3
2 9
0 2 3 3 4 6 6 7
3 2 10 1 2
5 5 8
3 5
0 0 4 7 8 8 9 9 10 10
4 2 30 4 2
3 2 2 10
2 6
0 2 2 4 5 6 7 9 9 15 15 16 17 18 18 19 19 22 23 24 24 24 25 25 25 26 27 27 28 29
5 2 70 5 1
6 6 6 4 5
5 6
0 0 0 1 1 5 6 8 10 12 12 14 15 15 17 17 18 18 19 19 22 24 26 26 28 30 30 31 32 32 32 33 33 33 34 35 35 35 37 38 40 40 41 42 46 46 47 48 48 51 53 54 55 56 56 57 59 60 61 61 63 63 64 65 65 66 67 67 70 70
4 3 100 1 3
9 9 5 2
8 7 8
0 3 5 6 10 12 13 14 15 15 19 19 20 20 21 22 22 23 23 26 26 26 26 30 31 33 33 35 36 39 39 39 40 40 41 41 42 43 43 45 47 48 49 50 50 51 51 51 51 51 52 54 58 58 59 60 60 60 60 61 61 62 62 63 63 66 69 69 69 70 71 72 73 73 74 75 76 76 79 81 82 82 82 85 87 87 88 91 91 91 94 94 96 96 96 98 98 99 99 100
5 3 100 1 1
9 10 3 5 3
8 8 10
0 0 0 0 1 1 3 3 4 5 7 8 8 9 9 11 11 13 14 15 16 17 17 18 19 19 22 22 23 23 25 26 26 27 27 30 30 34 34 36 36 38 41 44 44 45 45 47 47 49 50 50 51 53 53 58 61 62 62 63 64 65 67 67 69 70 72 73 75 76 77 80 80 80 81 81 83 83 83 84 87 87 89 89 89 90 90 91 92 93 93 93 93 95 95 98 99 100 100 100
(output)
#1 3
#2 7
#3 2
#4 22
#5 18
#6 15
#7 -1
#8 259
#9 100
#10 164
*/

//다른 사람 코드
/*

*/