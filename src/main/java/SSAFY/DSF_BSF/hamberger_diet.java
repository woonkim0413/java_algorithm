package SSAFY.DSF_BSF;

import java.util.*;

public class hamberger_diet {

    static int max_kalorie;
    static int max_taste;
    static List<Menu> arr = new ArrayList<>();

    static class Menu {
        int kalorie;
        int taste;
        Menu (int _taste, int _kalorie) {
            taste = _taste;
            kalorie = _kalorie;
        }
    }

    static void bfs(int index, int kalorie, int taste) {
        if (kalorie > max_kalorie) {
            return ;
        }

        if (kalorie <= max_kalorie) {
            if (taste > max_taste) {
                max_taste = taste;
            }
        }

        for (int i = index; i < arr.size(); i++) {
            bfs(i + 1, kalorie + arr.get(i).kalorie, taste + arr.get(i).taste);
        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        StringBuilder result = new StringBuilder("");
        int test_num = kb.nextInt();

        for (int t = 0; t < test_num; t++) {
            int menu_num = kb.nextInt();
            max_taste = 0;
            max_kalorie = kb.nextInt();
            arr.clear();
            for (int i = 0; i < menu_num; i ++) {
                arr.add(new Menu(kb.nextInt(), kb.nextInt()));
            }

            bfs(0, 0, 0);

            result.append("#").append(t+1).append(" ").append(max_taste);
            if (t + 1 < test_num) {
                result.append("\n");
            }
        }
        System.out.println(result);
    }

    // dfs (재귀 + bactracking) OR bfs (stack + backtracking)
    // 메뉴 갯수와 칼로리 제한을 가져옴
    // [파싱]
    // test 갯수를 가져오고 이를 기반으로 for문
    // 하나의 테스트에선 메뉴 갯수와 전체 칼로리를 kb.nextInt()로
    // 가져와서 전역 변수로 저장한다.
    // 각 메뉴의 맛 - 칼로리를 Menu class에 저장하여 배열로 만들어서
    // dfs를 실행할 때 사용한다.
    //
    // [비지니스 로직]
    // 각 노드 deep를 높이며 들어감 재귀 구현
    // 칼로리가 초과되면 되돌아감
    // 칼로리가 초과되지 않았다면 최적의 값과 계산해서 맛 수치가 높으면 업데이트 함
}
