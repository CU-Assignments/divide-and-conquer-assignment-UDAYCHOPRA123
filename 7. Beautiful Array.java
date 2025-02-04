import java.util.*;

class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);  
        
        while (res.size() < n) {
            List<Integer> temp = new ArrayList<>();

            
            for (int x : res) {
                if (2 * x - 1 <= n) {
                    temp.add(2 * x - 1);
                }
            }

          
            for (int x : res) {
                if (2 * x <= n) {
                    temp.add(2 * x);
                }
            }

            res = temp;  
        }

        
        return res.stream().mapToInt(i -> i).toArray();
    }
}
