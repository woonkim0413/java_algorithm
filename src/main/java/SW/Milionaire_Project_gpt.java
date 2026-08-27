package algorithm_study;

import java.util.*;
import java.io.*;

public class Milionaire_Project_gpt {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			long total = 0;
			int max = arr[n - 1];
			
			// 뒤에서부터 탐색
			for (int i = n - 2; i >= 0; i--) {
				
				// 현재 값보다 미래의 최대값이 크다면
				// 현재 가격에 사고 미래 최대값에 판매
				if (arr[i] < max) {
					total += max - arr[i];
				}
				
				// 현재 값이 더 크다면
				// 앞으로 이 가격에 판매하는 것이 가장 이득
				else {
					max = arr[i];
				}
			}
			
			System.out.println("#" + (t + 1) + " " + total);
		}
	}
}