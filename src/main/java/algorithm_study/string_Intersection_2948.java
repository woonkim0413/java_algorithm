package algorithm_study;

import java.util.*;
import java.io.*;

public class string_Intersection_2948 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Set<String> firstSection = new HashSet<>();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int case_t = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < case_t; t ++) {
			// 두 집합의 크기
			st = new StringTokenizer(br.readLine());
			int first_size = Integer.parseInt(st.nextToken());
			int second_size = Integer.parseInt(st.nextToken());
			int interSectionCount = 0;
			StringTokenizer set1 = new StringTokenizer(br.readLine());
			StringTokenizer set2 = new StringTokenizer(br.readLine());
			
			
			// firstSection 값 삽입
			firstSection.clear();
			for (int i = 0; i < first_size; i ++) {
				firstSection.add(set1.nextToken());
			}
			
			// 검증
			for (int i = 0; i < second_size; i ++) {
				if (firstSection.contains(set2.nextToken()))
					interSectionCount ++;
			}
			
			System.out.println("#" + (t + 1) + " " + interSectionCount);
		}
	}
}

//이해
/*
두 집합에 공통으로 포함된 소문자로 이루어진 문자열의 갯수를 구하는 문제다.
두 집합에 포함된 문자열의 숫자는 서로 다를 수 있다.	
*/

//로직 (논리 + 코드 레벨)
/*
 첫 번째 집합의 문자열을 모두 set에 넣는다.
 이후 두 번째 집합의 문자열이 첫 번째 집합으로 만든 set에 이미
 들어있는지 확인한다.
 들어있다면 intersection에 ++한다.
*/

//배운 것
/*

*/

//input
/*
5
4 5
cat dog bird fish
dog fish lion tiger bear
5 4
red blue green black white
yellow purple orange gray
6 6
aa bb cc dd ee ff
ff dd bb aa ee cc
8 10
java spring docker kubernetes redis mysql linux cloud
python redis linux aws docker react java git jenkins nginx
55 60
aa ab ac ad ae af ag ah ai aj ak al am an ao ap aq ar as at au av aw ax ay az aaa aab aac aad aae aaf aag aah aai aaj aak aal aam aan aao aap aaq aar aas aat aau aav aaw aax aay aaz aba abb abc
ak al am an ao ap aq ar as at au av aw ax ay az aaa aab aac aad ba bb bc bd be bf bg bh bi bj bk bl bm bn bo bp bq br bs bt bu bv bw bx by bz baa bab bac bad bae baf bag bah bai baj bak bal bam ban

(output)
#1 2
#2 0
#3 6
#4 4
#5 20
*/

//다른 사람 코드
/*

*/