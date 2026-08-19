package algorithm_study;

import java.util.*;
import java.io.*;

public class sudoku_1974 {
	
	private static int[] inspect_arr = new int[10];
	private static int[][] arr = new int[9][9];
	private static int flag;
	
	private static void init_inspect_arr() {
		for (int i = 0; i < 10; i ++) {
			inspect_arr[i] = -1;
		}
	}
	
	private static int check_3x3(int i, int j) {		
		init_inspect_arr();
		
		for (int y = i; y < i + 3; y ++) {
			for (int x = j; x < j + 3; x ++) {
				int value = arr[y][x];
				if (inspect_arr[value] != -1) {
					return (0);
				}
				inspect_arr[value] = value;
			}
		}
		return (1);
	}
	
	private static int check_row(int y) {
		init_inspect_arr();
		
		for (int x = 0; x < 9; x ++) {
			int value = arr[y][x];
			if (inspect_arr[value] != -1) {
				return (0);
			}
			inspect_arr[value] = value;
		}	
		return 1;
	}
	
	private static int check_col(int x) {
		init_inspect_arr();
		
		for (int y = 0; y < 9; y ++) {
			int value = arr[y][x];
			if (inspect_arr[value] != -1) {
				return (0);
			}
			inspect_arr[value] = value;
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int case_t = Integer.parseInt(br.readLine());
		
		// 2차원 배열에 값 넣기
		for (int t = 0; t < case_t; t ++) {
			flag = 1;
			
			for (int i = 0; i < 9; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j ++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 3x3 체크
			for(int i = 0; i < 9; i += 3) {
				if (flag == 0)
					break;
				for(int j = 0; j < 9; j += 3) {
					if (flag == 0) 
						break;
					if (check_3x3(i, j) == 0) {
						flag = 0;
					}
					
				}
			}
			
			// 가로 체크
			for (int i = 0; i < 9; i ++) {
				if (flag == 0) 
					break;
				if (check_row(i) == 0) {
					flag = 0;
				}
			}
			
			// 세로 체크
			for (int i = 0; i < 9; i ++) {
				if (flag == 0) 
					break;
				if (check_col(i) == 0) {
					flag = 0;
				}
			}
			sb.append("#").append(t+1).append(" ").append(flag);
			if (t + 1 != case_t) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}


// 이해
/*
 * 9 x 9 사각형에서 3x3에 겹치는 숫자가 없으며 가로, 세로 한 줄에 겹치는 숫자가 모두 없는 경우
 * 해당 케이스에서 1을 출력하고 겹치는 숫자가 있다면 0을 반환한다.
*/

// 로직 (논리 + 코드 레벨)
/*
 * 9개의 3x3 검사, 가로 검사, 세로 검사 총 3개의 축으로 검사를 진행하면 될 것 같다.
 * 
 * 크기가 10인 arr을 만들어서 기본은 -1로 초기화 하고 탐색할 때 특정 숫자를 발견하면
 * 해당 숫자를 index로 사용하여 arr에 접근 후 해당 위치에 1을 넣으면 될 것 같다.
 * 만약 arr의 특정 위치에 접근할 때 값이 -1이 아니면 수도쿠가 완성되어 있지 않다고 판단하여 0을 넣는다.
 * 
 * 세로 줄은 2차원 배열의 y를 변화하며 검사하고 가로 줄은 2차원 배열의 x즐을 이동하여 검사하면 될 것 같다.
 * 3x3은 2중 for문으로 i는 y를 +3 증가, j는 x 값을 +3씩 증가시켜서 이동시키고
 * 수식은 해당 좌표를 기준으로 y ~ y+2, x ~ x+2를 탐색한다.
 * 
 * 변수:
 * case, arr[]
*/

// 배운 것
/*
 * 1) 부적절한 type 배열 사용
 * private static int[] inspect_arr = new int[10]; 대신
 * private static boolean[] inspect_arr = new boolean[10];
 * 처럼 boolean type arrary로 사용하는 것이 더 적절했다.
 * 내가 체크하고 싶었던 것은 배열에 접근이 있었는지에 대한 유무였기 때문이다.
 * 
 * 2) 변수 이름 주의하기
 * check_3x3(int y, int x)보단 y,x가 사용되는 맥락을 파악하기 쉽도록
 * check_3x3(int start_y, int start_x)로 사용하는 것이 좋다.
*/

// input
/*
 * 6
7 3 6 4 2 9 5 8 1
5 8 9 1 6 7 3 2 4
2 1 4 5 8 3 6 9 7
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 4 3 9
1 2 3 4 5 6 7 8 9
4 5 6 7 8 9 1 2 3
7 8 9 1 2 3 4 5 6
2 3 4 5 6 7 8 9 1
5 6 7 8 9 1 2 3 4
8 9 1 2 3 4 5 6 7
3 4 5 6 7 8 9 1 2
6 7 8 9 1 2 3 4 5
9 1 2 3 4 5 6 7 8
7 3 6 4 2 9 5 8 7
5 8 9 1 6 7 3 2 4
2 1 4 5 8 3 6 9 7
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 4 3 9
7 3 6 4 2 9 5 8 1
7 8 9 1 6 3 4 2 5
2 1 4 5 8 7 6 9 3
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 3 4 9
7 3 6 4 2 9 5 8 1
5 7 9 1 6 3 4 2 8
2 1 4 5 8 7 6 9 3
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 8 7 2 1 5 3 4 9
1 1 3 4 5 6 7 8 9
4 5 6 7 8 9 1 2 3
7 8 9 1 2 3 4 5 6
2 3 4 5 6 7 8 9 1
5 6 7 8 9 1 2 3 4
8 9 1 2 3 4 5 6 7
3 4 5 6 7 8 9 1 2
6 7 8 9 1 2 3 4 5
9 1 2 3 4 5 6 7 8

#1 1
#2 1
#3 0
#4 0
#5 0
#6 0
*/

//다른 사람 코드
/* 배울 점:
 * 1) 중복 코드 검사는 set을 사용했어도 좋을 것 같다.
 * GPT는 아래 질문을 해보라고 한다.
 * 같은 인덱스 순회를 공유할 수 있는가? (SET 검사를 동시에 수행), 
 * 자료구조가 검사 자체를 대신하게 할 수 있는가? (SET 사용)
 */
/*
```
class Solution
{
 public static void main(String args[]) throws Exception
 {
     
        아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
        여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
        이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
        따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
        단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
      
     //System.setIn(new FileInputStream("res/input.txt"));

     
        표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
      
     Scanner sc = new Scanner(System.in);
     int T = sc.nextInt();

     for (int i = 1; i <= T; i++) {
         int[][] arr = new int[9][9];
         for (int j = 0; j < 9; j++) {
             for (int h = 0; h < 9; h++) {
                 arr[j][h] = sc.nextInt();
             }
         }
         Set<Integer> set1 = new HashSet<>();
         Set<Integer> set2 = new HashSet<>();
         int cnt = 1;
         for (int j = 0; j < 9; j++) {
             for (int h = 0; h < 9; h++) {
                 set1.add(arr[j][h]);
                 set2.add(arr[h][j]);
             }
             if (set1.size() != 9 || set2.size() != 9) {
                 cnt = 0;
                 break;
             }
             set1.clear();
             set2.clear();
         }
         Set<Integer> set3 = new HashSet<>();
         Set<Integer> set4 = new HashSet<>();
         Set<Integer> set5 = new HashSet<>();

         for (int j = 0; j < 9; j++) {
             for (int h = 0; h < 9; h++) {
                 if (h < 3) {
                     set3.add(arr[j][h]);
                 } else if (h < 6) {
                     set4.add(arr[j][h]);
                 } else {
                     set5.add(arr[j][h]);
                 }
             }
             if (j == 2 || j == 5 || j == 8) {
                 if (set3.size() != 9 || set4.size() != 9 || set5.size() != 9) {
                     cnt = 0;
                     break;
                 }
                 set3.clear();
                 set4.clear();
                 set5.clear();
             }
         }
         System.out.println("#" + i + " " + cnt);

     }
 }
}

2) 창선이 코드
스도쿠 검증 크기를 모두 사각형으로 보고 확인 범위를 arg로 주는 방식이 신선했다
다만 사각형의 arg naming을 arg 사용 목적이 잘 들어나도록 하는 것이 좋지 않았을까 하는
생각이 들었다 
ex) row_start, row_end, col_start, col_end 등
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[][] sudoku;

	static int T;

	public static void main(String args[]) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sudoku = new int[9][9];
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.printf("#%d %d\n", tc, solve());
		}

	}

	static int solve() {
		boolean v = true;

		for (int i = 0; i < 9; i++) {
			v&=valid(i, i, 0, 8);
			v&=valid(0, 8, i, i);
		}
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				v&=valid(i, i + 2, j, j + 2);
			}
		}

		return v ? 1 : 0;
	}

	static boolean valid(int i1, int i2, int j1, int j2) {
		boolean[] checked = new boolean[10];
		for (int i = i1; i <= i2; i++) {
			for (int j = j1; j <= j2; j++) {
				if (checked[sudoku[i][j]]) {
					return false;
				}
				checked[sudoku[i][j]] = true;
			}
		}
		return true;
	}
}

*/

