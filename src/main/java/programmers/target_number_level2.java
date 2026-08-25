package programmers;

public class target_number_level2 {
	static class Solution {
	    int target = 0;
	    int answer = 0;
	    
	    void dfs(int[] numbers, int index, int sum) {
	        if (index == numbers.length) {
	            if (sum == target) {
	                answer ++;
	            }
	            return ;
	        }
	        
	        dfs(numbers, index + 1, sum + numbers[index]);
	        dfs(numbers, index + 1, sum - numbers[index]);
	    }
	    
	    public int solution(int[] numbers, int target) {
	        this.target = target;
	        this.answer = 0;
	        
	        dfs(numbers, 1, numbers[0]);
	        dfs(numbers, 1, -numbers[0]);
	        
	        return answer;
	    }
	}
}
