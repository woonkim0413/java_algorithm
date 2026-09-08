package algorithm_study;

import java.util.*;
import java.io.*;

public class check_arithmetic_validation_1233 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Node> tree = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		for (int t = 1; t <= 10; t++) {
			
			// tree 초기화
			tree.clear();
			tree.add(new Node(0, false, -1, '1'));
			
			int N = Integer.parseInt(br.readLine());
			boolean validation = true;
			
			// tree 생성
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int nodeNum = Integer.parseInt(st.nextToken());
				
				String temp = st.nextToken();
				
				if (temp.length() == 1 && (temp.charAt(0) > '9' || temp.charAt(0) < '0')) {
					// operator node 생성
					tree.add(new Node(nodeNum, true, -1, temp.charAt(0)));
				} else {
					// value node 생성
					tree.add(new Node(nodeNum, false, Integer.parseInt(temp), '1'));
				}
			}
			
			// 검증
			for (int i = 1; i < tree.size(); i++) {
			    Node node = tree.get(i);

			    int nodeNum = node.nodeNum();

			    if (node.isOperator()) {
			        if (nodeNum * 2 >= N) {
			            validation = false;
			        }
			    } else {
			        if (nodeNum * 2 + 1 <= N) {
			            validation = false;
			        }
			    }
			}
			System.out.println("#"+t+" "+ (validation ? 1 : 0));
		}
	}
	
	private static class Node {
		boolean isOperator;
		int nodeNum;
		int value;
		char operator;
		
		public Node(int nodeNum, boolean isOperator, int value, char operator) {
			this.isOperator = isOperator;
			this.value = value;
			this.nodeNum = nodeNum;
			this.operator = operator;
		}
		public int nodeNum() {
			return nodeNum;
		}
		
		public boolean isOperator() {
			return isOperator;
		}

		public void setOperator(boolean isOperator) {
			this.isOperator = isOperator;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public char getOperator() {
			return operator;
		}

		public void setOperator(char operator) {
			this.operator = operator;
		}
	}
}



//로직 (논리 + 코드 레벨)
/*
	현재 노드 값, 왼쪽 오른쪽 노드 Reference를 가지고 있는 node clss를 생성한다.
	
	문제만 두고 보았을 땐 array로 풀기는 쉬울 것 같다.
	
	리프노드는 어떻게 표현되지? 정점이 없을라나?
	
	
*/

//배운 것
/*

*/

//input
/*
7
1 + 2 3
2 * 4 5
3 - 6 7
4 8
5 2
6 6
7 4
7
1 + 2 3
2 9 4 5
3 - 6 7
4 8
5 2
6 6
7 4
7
1 + 2 3
2 * 4 5
3 - 6 7
4 8
5 2
6 +
7 4
15
1 + 2 3
2 * 4 5
3 - 6 7
4 / 8 9
5 + 10 11
6 * 12 13
7 - 14 15
8 8
9 2
10 3
11 4
12 5
13 6
14 9
15 1
15
1 + 2 3
2 * 4 5
3 100 6 7
4 / 8 9
5 + 10 11
6 * 12 13
7 - 14 15
8 8
9 2
10 3
11 4
12 5
13 6
14 9
15 1
1
1 42
3
1 7 2 3
2 10
3 20
3
1 / 2 3
2 100
3 5
7
1 + 2 3
2 * 4 5
3 / 6 7
4 3
5 4
6 5
7 -
180
1 + 2 3
2 - 4 5
3 * 6 7
4 / 8 9
5 + 10 11
6 - 12 13
7 * 14 15
8 / 16 17
9 + 18 19
10 - 20 21
11 * 22 23
12 / 24 25
13 + 26 27
14 - 28 29
15 * 30 31
16 / 32 33
17 + 34 35
18 - 36 37
19 * 38 39
20 / 40 41
21 + 42 43
22 - 44 45
23 * 46 47
24 / 48 49
25 + 50 51
26 - 52 53
27 * 54 55
28 / 56 57
29 + 58 59
30 - 60 61
31 * 62 63
32 / 64 65
33 + 66 67
34 - 68 69
35 * 70 71
36 / 72 73
37 + 74 75
38 - 76 77
39 * 78 79
40 / 80 81
41 + 82 83
42 - 84 85
43 * 86 87
44 / 88 89
45 + 90 91
46 - 92 93
47 * 94 95
48 / 96 97
49 + 98 99
50 - 100 101
51 * 102 103
52 / 104 105
53 + 106 107
54 - 108 109
55 * 110 111
56 / 112 113
57 + 114 115
58 - 116 117
59 * 118 119
60 / 120 121
61 + 122 123
62 - 124 125
63 * 126 127
64 / 128 129
65 + 130 131
66 - 132 133
67 * 134 135
68 / 136 137
69 + 138 139
70 - 140 141
71 * 142 143
72 / 144 145
73 + 146 147
74 - 148 149
75 * 150 151
76 / 152 153
77 + 154 155
78 - 156 157
79 * 158 159
80 / 160 161
81 + 162 163
82 - 164 165
83 * 166 167
84 / 168 169
85 + 170 171
86 - 172 173
87 * 174 175
88 / 176 177
89 + 178 179
90 7 180
91 56
92 63
93 70
94 77
95 84
96 91
97 1
98 8
99 15
100 22
101 29
102 36
103 43
104 50
105 57
106 64
107 71
108 78
109 85
110 92
111 2
112 9
113 16
114 23
115 30
116 37
117 44
118 51
119 58
120 65
121 72
122 79
123 86
124 93
125 3
126 10
127 17
128 24
129 31
130 38
131 45
132 52
133 59
134 66
135 73
136 80
137 87
138 94
139 4
140 11
141 18
142 25
143 32
144 39
145 46
146 53
147 60
148 67
149 74
150 81
151 88
152 95
153 5
154 12
155 19
156 26
157 33
158 40
159 47
160 54
161 61
162 68
163 75
164 82
165 89
166 96
167 6
168 13
169 20
170 27
171 34
172 41
173 48
174 55
175 62
176 69
177 76
178 83
179 90
180 97
(output)
#1 1
#2 0
#3 0
#4 1
#5 0
#6 1
#7 0
#8 1
#9 0
#10 0
*/

//다른 사람 코드
/*

*/