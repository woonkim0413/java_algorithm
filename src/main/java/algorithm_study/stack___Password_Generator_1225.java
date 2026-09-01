package algorithm_study;

import java.util.*;
import java.io.*;

public class stack___Password_Generator_1225 {
	
	static int decrease = 0;
	static Deque<Integer> stack = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb
		
		for (int t = 1; t <= 10; t ++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			
			// stack에 초기 값 채우기
			stack.clear();
			while (st.hasMoreElements()) {
				stack.addLast(Integer.parseInt(st.nextToken()));
			}
			
			// 암호 생성
			generator();
			
			// output에 맞게 parsing
			sb = new StringBuilder("");
			sb.append("#").append(t);
			while (!stack.isEmpty())
				sb.append(" ").append(stack.pollFirst());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static private void generator() {
		decrease = 1;
		
		while(true) {
			int temp = stack.pollFirst();
			
			// 원소가 감소 숫자보다 작거나 같은 경우
			if (temp <= decrease) {
				stack.addLast(0);
				return ;
			}
			
			temp -= decrease++;
			if (decrease == 6)
				decrease = 1;
			
			stack.addLast(temp);
		}
	}
}

//로직 (논리 이해 + 코드 레벨)
/*
	앞에서 꺼내고 뒤에 넣기... 이거 queue 아닌가?
	
	static으로 현재 감소시킬 숫자 기억
	inteager stack으로 감소시킬 값이 element보다 커질 때까지 while돌리기
*/

//배운 것
/*

*/

//input
/*
1
9550 9556 9550 9553 9558 9551 9551 9551
2
2419 2418 2423 2415 2422 2419 2420 2415
3
5100 5098 5103 5107 5099 5102 5101 5097
4
7301 7305 7298 7300 7306 7299 7302 7304
5
1204 1201 1207 1203 1205 1200 1206 1202
6
8888 8892 8885 8890 8887 8891 8886 8889
7
3456 3459 3453 3461 3458 3455 3460 3454
8
6702 6698 6705 6701 6699 6704 6700 6703
9
4320 4317 4324 4319 4322 4318 4321 4323
10
9991 9995 9989 9993 9990 9994 9992 9988
(output)
#1 6 2 2 9 4 1 3 0
#2 9 7 9 5 4 3 8 0
#3 2 8 8 1 5 5 2 0
#4 4 8 4 5 10 4 5 0
#5 10 7 4 1 7 3 5 0
#6 10 4 6 3 5 1 4 0
#7 11 8 5 10 4 5 7 0
#8 11 5 1 9 3 9 5 0
#9 8 6 6 10 3 1 9 0
#10 4 2 7 6 3 1 5 0

*/

//다른 사람 코드
/*

*/