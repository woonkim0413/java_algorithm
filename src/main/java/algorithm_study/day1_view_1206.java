package algorithm_study;

import java.util.*;
import java.io.*;

public class day1_view_1206 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int light_house;
	static int building_num;
	static StringTokenizer sb;
	static int[] row_area; // 토지 내 빌딩 높이 저장
	
	public static void main(String[] args) throws IOException {
		
		for (int t = 1; t <= 10; t ++) {
			building_num = Integer.parseInt(br.readLine());
			row_area = new int[building_num];
			
			// 빌딩 높이 채우기
			sb = new StringTokenizer(br.readLine());
			for (int i = 0; i < building_num; i ++) {
				row_area[i] = Integer.parseInt(sb.nextToken());
			}
			
			// 조망권 계산하기
			light_house = 0;
			for (int i = 2; i < building_num - 2; i ++) {
				
				// 현재 건물의 층별 조광 확보 확인
				for (int current_high = 1; current_high <= row_area[i]; current_high ++) {
					
					// 현재 층의 높이를 옆 건물들의 높이와 비교
					boolean light = true;
					for (int next_building = i - 2; next_building <= i + 2; next_building++) {
						if (next_building == i)
							continue;
						
						if (current_high  <= row_area[next_building]) {
							light = false;
							break;
						}
							
					}
					if (light)
						light_house ++;
				}
			}
			System.out.println("#" + t + " " + light_house);
		}
	}
}

//이해
/*
강변에 위치한 건물의 갯수와 건물의 세대(=높이)를 알려준다.
건물 세대 중 양 옆으로 두 칸 이상 다른 세대가 없다면 조망이 확보된 건물이라고 취급한다.

*/

//로직 (논리 + 코드 레벨)
/*
첫 번째 줄에서 건물의 갯수(높이 0도 포함)를 받으면 건물의 갯수 만큼의 크기를 갖는 arr을 만든다.
input으로 받는 값을 arr에 넣는다.
current_high 변수를 만들어서 각 건물의 높이를 순회한다.
특정 건물 높이를 순회할 때 앞 뒤 + 2 건물의 전체 높이를 빼서 음수가 나오면 해당 건물은 조망권이
확보되지 않은 것으로 판단하여 카운트 하지 않는다.
만약 양 옆 두 건물의 높이를 빼서 양수가 나오면 조망권이 확보된 것으로 취급하여 카운트한다.
전체 조망권 숫자를 반환한다.
*/

//배운 것
/*

*/

//input
/*
14
0 0 3 5 2 4 9 0 6 4 0 6 0 0
10
0 0 5 3 2 7 4 2 0 0
8
0 0 5 3 3 4 0 0
9
0 0 3 3 7 3 3 0 0
10
0 0 10 1 1 1 1 1 0 0
10
0 0 1 5 1 1 5 1 0 0
8
0 0 1 1 1 1 0 0
9
0 0 8 3 2 3 8 0 0
10
0 0 4 2 7 1 3 2 0 0
10
0 0 6 1 2 8 2 1 0 0
*/

//output
/*
#1 6
#2 5
#3 3
#4 4
#5 9
#6 8
#7 0
#8 10
#9 3
#10 10
(output)

*/

//다른 사람 코드
/*

*/
