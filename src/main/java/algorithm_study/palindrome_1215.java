package algorithm_study;

import java.io.*;
import java.util.*;

public class palindrome_1215 {
	private static char[][] grid = new char[8][8];
	
	private static int cal_palindrome(int y, int x, int palindrome_size) {
	    int count = 0;

	    // 가로 검사
	    if (x + palindrome_size <= 8) {
	        for (int i = x; i < x + palindrome_size / 2; i++) {
	            if (grid[y][i] != grid[y][x + x - i + palindrome_size - 1])
	                break;
	            if (i == x + palindrome_size / 2 - 1)
	                count++;
	        }
	    }

	    // 세로 검사
	    if (y + palindrome_size <= 8) {
	        for (int i = y; i < y + palindrome_size / 2; i++) {
	            if (grid[i][x] != grid[y + y - i + palindrome_size - 1][x])
	                break;
	            if (i == y + palindrome_size / 2 - 1)
	                count++;
	        }
	    }

	    if (palindrome_size == 1)
	        count = 2;

	    return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st;
		int case_t = 10;
		
		for (int t = 0; t < case_t; t ++) {
			int palindrome_total = 0;
			int palindrome_size = Integer.parseInt(br.readLine());
			
			// 2차원 배열 채우기
			for (int i = 0; i < 8; i ++) {
				st = br.readLine();
				for (int j = 0; j < 8; j ++) {
					grid[i][j] = st.charAt(j);
				}
			}
			
			for (int i = 0; i < 8; i++) {
			    for (int j = 0; j < 8; j++) {
			        palindrome_total += cal_palindrome(i, j, palindrome_size);
			    }
			}
			
			System.out.println("#" + (t + 1) + " " + palindrome_total);
		}
	}
}


//이해
/*
회문이란 데칼코마니를 의미하는 것 같다.
AAA, ABBA, A, ACBCA 모두 하나의 직선 방향이면 회문이다.

8x8의 10개의 테스트 케이스가 주어지며 테스트 케이스 앞에 찾아야 하는 회문의 길이가 주어진다.
*/

//로직 (논리 + 코드 레벨)
/*
2차원 배열의 0,0부터 회문 크기만큼 x+ 방향, y+ 방향을 탐색한다.
만약 현재 "좌표 + 회문 크기"가 n보다 큰 경우 검사하지 않는다.
x-, y- 방향 검사는 불필요하다 
(0,0)에서 x+방향으로 4크기 회문을 검사하는 것과 (0,4)에서 x-방향으로 4크기 회문을 검사하는 것은 동일하다.
*/

//배운 것
/*
1) 타이핑 연습하기
영문 타이핑 능력이 좀 부족한 것 같음 코드 칠 때 신경쓰기
2) 변수 이름 짓기
map : 지도, 격자, 미로처럼 공간을 표현할 때
grid : 일반적인 N×M 격자 문제에서 가장 추천
board : 체스판, 게임판, 스도쿠처럼 판의 의미가 있을 때
matrix : 행렬 계산 문제일 때
graph : 인접 행렬을 표현할 때
dist : 각 칸까지의 거리 저장
visited : 방문 여부 저장
dp : 2차원 DP 테이블
cost : 각 위치의 비용 저장
arr : 딱히 의미가 없는 단순 2차원 숫자 배열
3) 코드가 조건을 잘 표현하고 있는지 확인

*/

//input
/*
4
CBBCBAAB
CCCBABCB
CAAAACAB
BACCCCAC
AABCBBAC
ACAACABC
BCCBAABC
ABBBCCAA
3
ABCCBAAA
BACABACC
CCABBACC
ABACABCA
BACCBABA
CCAABBCC
ABCCBACA
CBABACBC
5
ABCBAABC
BACABCCA
CCABCACC
ABCCBABC
BACABACA
ABCBAACC
CCBACABC
ABACABCA
2
AABBCCAA
CCAABBCC
BBCCAABB
AABBCCAA
CCAABBCC
BBCCAABB
AABBCCAA
CCAABBCC
6
ABCCBAAA
BACABCAB
CCABACCA
ABBAABCC
CBAABCBA
ABCBAABC
BACCCABA
CCBAABCC
7
ABCACBAA
BACABACC
CCABCACC
ABCCBABC
BACABACA
ABCBAACC
CCBACABC
ABACABCA
8
ABCCBCCA
BACABCAB
CCABACCA
ABBAABBA
CBAABCBA
ABCBAABC
BACCCABA
CCBAABCC
1
ABCABCAB
BCABCABC
CABCABCA
ABCABCAB
BCABCABC
CABCABCA
ABCABCAB
BCABCABC
3
AAABBBCC
ABAACCBB
CACBBACA
BBACABAB
ACCCBCAA
BABACABB
CCAABBCC
ABCACBAA
5
ABCBAABC
BACABACA
CABACABC
ABCCBABC
BACABACA
ABCBAACC
CCBACABC
ABACABCA

#1 12
#2 27
#3 15
#4 32
#5 4
#6 3
#7 2
#8 128
#9 30
#10 19
*/

//다른 사람 코드
/*

*/
