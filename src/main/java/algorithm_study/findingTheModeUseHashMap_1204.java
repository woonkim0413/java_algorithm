package algorithm_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


public class findingTheModeUseHashMap_1204 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	static Map<Integer, Integer> studentScoreFrequency = new HashMap<>(); 
	
	public static void main(String[] args) throws IOException {
		int case_t = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < case_t; t++) {
			 
			 br.readLine();
			 st = new StringTokenizer(br.readLine());
			 
			 // 점수 빈도 구하기
			 for (int i = 0; i < 1000; i ++) {
				 int student_score = Integer.parseInt(st.nextToken());
				 studentScoreFrequency.put(
						 student_score,
						 studentScoreFrequency.getOrDefault(student_score, 0) + 1
				         );
			 }
			 
			 // 최고 값의 index 찾기
			 int max_index = 0;
			 int max_frequency = 0;
			 for (int i = 0; i < 101; i ++) {
				 if (studentScoreFrequency.containsKey(i)) {
					 if (studentScoreFrequency.get(i) >= max_frequency) {
						max_frequency = studentScoreFrequency.get(i);
						max_index = i;
					 }
				 }
			 }
			 System.out.println("#" + (t + 1) + " " + max_index);
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
1) Map.getOrDefault(key, 조회 못 한 경우 반환 값)
studentScoreFrequency.getOrDefault(student_score, 0)
위 코드를 사용하면 key로 조회가 되는 경우 조회된 value 값을 반환하고 조회가 안되면
두 번째 arg를 반환한다.
if (!studentScoreFrequency.containsKey(student_score))
위 식으로 분기하는 코드보다 간편하게 사용할 수 있다.


2) Map key - value에 의미 부여하기
obsidian node에 정리함
*/

//input
/*

*/

//다른 사람 코드
/*

*/
