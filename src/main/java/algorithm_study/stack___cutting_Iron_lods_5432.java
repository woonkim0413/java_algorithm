package algorithm_study;

import java.util.*;
import java.io.*;

public class stack___cutting_Iron_lods_5432 {
	
	static int initialRods = 0;
	static int createIron = 0;
	static int curHier = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int CN = Integer.parseInt(br.readLine());
		
		for (int N = 1; N <= CN; N++) {
			String str = br.readLine();
			
			initialRods = 0;
			createIron = 0;
			curHier = 0;
			
			calIron(str);
			
			System.out.println("#"+N+" "+(initialRods+createIron));
		}
	}
	
	static private void calIron(String str) {
		
		for (int i = 0; i < str.length(); i ++) {
			int curPart = str.charAt(i);
			
			// part별 파트 로직
			switch(curPart) {
			case '(':
				if (str.charAt(i + 1) == '(') {
					curHier ++;
				}
				else if (str.charAt(i + 1) == ')') {
					createIron += curHier;
					i ++;
				}
				break;
			case ')': // 레이저를 닫는 경우는 없음 위에서 처리됨
				curHier--;
				initialRods ++;
				break;
			}
		}
	}
}

//로직 (논리 이해 + 코드 레벨)
/*
  층층히 쌓아올린 쇠막대기 더미에 레이저를 쏴서 만들어지는
  쇠막대기 조각 갯수를 구하는 문제이다.
  ()는 레이저이고 (~)는 쇠 막대기를 나타낸다.
  
  (를 인식하면 쇠막대기 또는 레이저가 시작되는 위치다.
  바로 )가 나오면 레이저이고 (를 만나면 이전 (는 쇠막대기를 표현하는 여는 괄호였다.
  
  레이저는 앞에 여는 괄호가 몇개 있었는지에 의존하여 조각수를 생성한다.
  앞에 여는 괄호가 4개 있었다면 조각을 4개 생성하고, 앞에 여는 괄호가 2개 있었다면 조각을 두 개 생성한다.
  
  아래처럼 숫자를 구할 수 있다.
  숫자는 레이저를 쏠 때 생성되는 조각의 수다.
  또한 맨 뒤 숫자는 처음에 존재하던 철 막대의 수다.
  전체 조각의 수는 레이저로 생성된 수 + 초기 철 막대 수로 구할 수 있다.
  (((()3(()4()4))(()3)()2))(()1()1)6  6
  
*/

//배운 것
/*

*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/