package algorithm_study;

import java.util.*;
import java.io.*;


public class wireless_charging_5644 {
	
	static int[] Acur; // x, y
	static int[] Bcur; // x, y
	static int[] Apath;
	static int[] Bpath;
	static BC[] BCList;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t ++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			// A 이동 기록
			Apath = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 20; i ++) {
				Apath[i] = Integer.parseInt(st.nextToken());
			}
			
			// B 이동 기록
			Bpath = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 20; i ++) {
				Bpath[i] = Integer.parseInt(st.nextToken());
			}
		
			// BC 정보 저장
			BCList = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				
				BCList[i] = new BC();
				
				BCList[i].x = Integer.parseInt(st.nextToken());
				BCList[i].y = Integer.parseInt(st.nextToken());
				BCList[i].distance = Integer.parseInt(st.nextToken());
				BCList[i].power = Integer.parseInt(st.nextToken());
			}
			
            // 시작 값
            Acur = new int[]{1, 1};
            Bcur = new int[]{10, 10};
            int result = 0;
            
			// 로직 시행
			for (int i = 0; i < M; i ++) {
				result += getMaxCharge();
				
				Acur[0] += dx[Apath[i]];
				Acur[1] += dy[Apath[i]];
				Bcur[0] += dx[Bpath[i]];
				Bcur[1] += dy[Bpath[i]];
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
	
	// 특정 T에서 가장 높은 충전 값
	static int getMaxCharge() {
	    int max = 0;

	    for (int a = -1; a < BCList.length; a++) {
	    	if (a != -1) {
		        if (!canCharge(Acur[0], Acur[1], BCList[a]))
		        		continue;
	        }
	        for (int b = -1; b < BCList.length; b++) {
	           	if (b != -1) {
		            if (!canCharge(Bcur[0], Bcur[1], BCList[b]))
		            		continue;
	            } 

	            int sum = 0;

	            if (a != -1) {
	                sum += BCList[a].power;
	            }

	            if (b != -1 && a != b) {
	                sum += BCList[b].power;
	            }
	            max = Math.max(max, sum);
	        }
	    }
	    return max;
	}
	
    static boolean canCharge(int x, int y, BC bc) {
        int distance = Math.abs(x - bc.x) + Math.abs(y - bc.y);

        return (distance <= bc.distance) ? true : false;
    }
	
	static class BC {
		int x;
		int y;
		int distance;
		int power;
	}
}


//로직 (논리 + 코드 레벨)
/*
	map이 있을 필요가 있나? -> 없어 보인다.
	
	A좌표, B좌표, BC정보 등을 자료구조로 갖고있는 것이 좋다.
	
	최대값을 구하기 위해선
	1) 두 영역에 접속할 수 있다면 두 영역중에서 충전값이 더 큰 곳에 접속한다.
	2) 한 쪽은 두 곳, 한 쪽은 한 곳에만 접속 가능할 땐 두 곳 접속할 수 있는 쪽에서 배려해야한다. (어떻게?)
	
*/

//배운 것
/*
	1) N중 for문 탈출하기
	아래처럼 사용하면 break 하나로 가장 바깥 for문까지 탈출할 수 있다.
	outer:for (~)
			for (~)
				if () break outer;
				
	2) 조합 사용하기 (dfs or for문)
	해당 문제에서 까다로웠던 것은 A가 2개, B가 1개 충전소에서 충전을 할 수 있는 경우에,
	A가 B가 연결할 수 있는 경우를 선택한 경우 B는 선택할 수 없으므로 A가 B가 연결할 수 있는 충전소를
	양보해야 한다고 생각했다.
	그러나 이런식으로 조건을 구현해서 가장 최적의 경우의 수 하나를 찾으려면 코드의 복잡도가 증가한다.
	그냥 모든 경우의 수를 조합으로 계산한 뒤에
	그 중에서 가장 MAX값이 높은 것을 선택하는 방식으로 로직을 작성하는 것이 적잘하다.
*/

//input
/*
5
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
40 2
0 3 0 3 3 2 2 1 0 4 1 3 3 3 0 3 4 1 1 3 2 2 2 2 2 0 2 3 2 2 3 4 4 3 3 3 2 0 4 4 
0 1 0 3 4 0 4 0 0 1 1 1 0 1 4 4 4 4 4 3 3 3 0 1 0 4 3 2 1 4 4 3 2 3 2 2 0 4 2 1 
5 2 4 140
8 3 3 490
60 4
0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4 
1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1 
6 9 1 180
9 3 4 260
1 4 1 500
1 3 1 230
80 7
2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3 
4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0 
4 3 1 170
10 1 3 240
10 5 3 360
10 9 3 350
9 6 2 10
5 1 4 350
1 8 2 450
100 8
2 2 3 2 0 2 0 3 3 1 2 2 2 2 3 3 0 4 4 3 2 3 4 3 3 2 3 4 4 4 2 2 2 0 2 2 4 4 4 4 1 1 1 2 2 2 4 3 0 2 3 3 4 0 0 1 1 4 1 1 1 1 2 2 1 1 3 3 3 0 3 2 3 3 0 1 3 3 0 1 1 3 3 4 0 4 1 1 2 2 4 0 4 1 1 2 2 1 1 1 
4 4 4 0 4 1 1 4 1 1 1 1 3 2 1 2 1 1 4 4 1 0 2 3 4 4 4 4 4 0 1 0 2 2 2 0 2 2 2 2 2 2 3 0 0 1 4 3 3 2 0 0 4 4 4 0 2 0 4 1 1 2 2 0 4 4 0 0 2 0 2 3 3 0 2 3 0 3 4 0 4 3 4 4 3 4 1 1 2 2 2 0 0 1 0 4 1 1 1 4 
3 4 2 340
10 1 1 430
3 10 4 10
6 3 4 400
7 4 1 80
4 5 1 420
4 1 2 350
8 4 4 300
(output)
#1 1200
#2 3290
#3 16620
#4 40650
#5 52710
*/

//다른 사람 코드
/*

*/