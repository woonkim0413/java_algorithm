package algorithm_study;

import java.util.*;
import java.io.*;

public class swimmingPool {
	
	static int[] credit;
	static int[] months;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int min_money = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int ST = Integer.parseInt(br.readLine());
		
		for (int t = 1; t<=ST;t++) {
			st = new StringTokenizer(br.readLine());
			credit = new int[4];
			for (int i = 0; i<4; i++) {
				credit[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			months = new int[13];
			for (int i = 1; i<13; i++) {
				months[i] = Integer.parseInt(st.nextToken());
			}
			
			min_money = Integer.MAX_VALUE;
			dfs(1, 0);
			
			System.out.println("#"+t+" "+min_money);
		}
	}
	
	static void dfs(int index, int money) {
		if (index >= 13) {
			min_money = Math.min(Math.min(money, credit[3]), min_money);
			return ;
		}
		
		int cur_month = 0;
		if (credit[0] * months[index] <= credit[1])
			 cur_month = credit[0] * months[index];
		else
			cur_month = credit[1];
		
		dfs(index + 1, money + cur_month);
		dfs(index + 3, money + credit[2]);
	}
}

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
	1) DFS 최적화
	처음엔 모든 달마다 4개의 선택지를 골라서 4^12라고 생각했다
	그러나 더 깊게 생각해보니 년은 마지막에 선택하면 되고 일과 월은 모두 달 하나를 선택하는 것이기에
	하나로 통합할 수 있다.
	그렇기에 가짓수를 2^12로 최적화 할 수 있다.
*/

//input
/*
10
10 40 100 300
0 0 2 9 1 5 0 0 0 0 0 0
10 100 50 300
0 0 0 0 0 0 0 0 6 2 7 8
10 70 180 400
6 9 7 7 7 5 5 0 0 0 0 0
10 70 200 550
0 0 0 0 8 9 6 9 6 9 8 6
10 80 200 550
0 8 9 15 1 13 2 4 9 0 0 0
10 130 360 1200
0 0 0 15 14 11 15 13 12 15 10 15
10 180 520 1900
0 18 16 16 19 19 18 18 15 16 17 16
10 100 200 1060
12 9 11 13 11 8 6 12 8 7 15 6
10 170 500 1980
19 18 18 17 15 19 19 16 19 15 17 18
10 200 580 2320
12 28 24 24 29 25 23 26 26 28 27 22
(output)
#1 110
#2 100
#3 400
#4 530
#5 430
#6 1080
#7 1840
#8 800
#9 1980
#10 2260
*/

//다른 사람 코드
/*

*/