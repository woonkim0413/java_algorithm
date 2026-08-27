package algorithm_study;

import java.util.*;
import java.io.*;

public class whereCanIInsertWordTo_1979 {
	// 전역 변수들
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[][] mapT;
	static int puzzle_size;
	static int square_side;
	
	
	// row의 퍼즐 수를 계산해주는 함수
	private static int cal_row_puzzle(int y, int[][] map) {
		int puzzle_num = 0;
		
		for (int i = 0; i < square_side - puzzle_size + 1; i++) {
			// row의 첫 시작부분이면서 1이거나 이전 자리가 0이면서 현재 자리가 1인 경우 puzzle인지 체크
			if ((i == 0 && map[y][i] == 1) || (i > 0 && map[y][i-1] == 0 && map[y][i] == 1)) {
				
				int count1 = 0;
				// puzzle 검사
				while (map[y][i] == 1) {
					count1 ++;
					if (count1 == puzzle_size) {
						// 퍼즐 끝이 square의 끝이거나 퍼즐 사이즈 다음이 0인 경우
						if (i + 1 == square_side || map[y][i + 1] == 0)
							puzzle_num ++;
							break;
					}
					i ++;
				}
			}
		}
		return puzzle_num;
	}
	
	public static void main(String[] args) throws IOException {
		int case_t = Integer.parseInt(br.readLine());
		
		// case 순회
		for (int t = 0; t < case_t; t++) {
			st = new StringTokenizer(br.readLine());
			square_side = Integer.parseInt(st.nextToken());
			puzzle_size = Integer.parseInt(st.nextToken());
			int puzzle_count = 0;
			
			// map 생성
			map = new int[square_side][square_side];
			for (int i = 0; i < square_side; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < square_side; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 전치행렬 map 생성
			mapT = new int[square_side][square_side];
			for (int i = 0; i < square_side; i ++) {
				for (int j = 0; j < square_side; j ++) {
					mapT[j][i] = map[i][j]; 
				}
			}
			
			// puzzle 갯수 계산
			for (int i = 0; i < square_side; i ++) {
					puzzle_count += cal_row_puzzle(i, map);
					puzzle_count += cal_row_puzzle(i, mapT);
			}
			System.out.println("#" + (t+1) + " " + puzzle_count);
		}
	}
}

//이해
/*
직사각형에서 넣어야 하는 퍼즐 길이와 정확히 일치하는 
1로 이루어진 공간이 있는 경우
해당 퍼즐을 넣는다.
*/

//로직 (논리 + 코드 레벨)
/*
가로를 탐색하다 1을 발견하면 길이를 체크한다.
길이가 퍼즐 사이즈와 일치하면 퍼즐 갯수를 카운트한다.
전치행렬을 만들어서 y를 x체크 로직으로 동일하게 체크한다.
*/

//배운 것
/*

*/

//input
/*
2
5 3
0 0 1 1 1
1 1 1 1 0
0 0 1 0 0
0 1 1 1 1
1 1 1 0 1
5 3
1 0 0 1 0
1 1 0 1 1
1 0 1 1 1
0 1 1 0 1
0 1 1 1 0
(output)

#1 2
#2 6
*/

//다른 사람 코드
/*
1) GPT 코드 (좀 더 간결한 puzzle count 식)
private static int cal_row_puzzle(int y, int[][] map) {
    int puzzle_num = 0;
    int count = 0;

    for (int i = 0; i < square_side; i++) {
        if (map[y][i] == 1) {
            count++;
        } else {
            if (count == puzzle_size) {
                puzzle_num++;
            }
            count = 0;
        }
    }
    // 행 끝까지 1이 이어진 경우 처리
    if (count == puzzle_size) {
        puzzle_num++;
    }
    return puzzle_num;
}

*/