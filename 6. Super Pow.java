class Solution {
    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        return helper(a, b, b.length - 1);
    }

    private int helper(int a, int[] b, int index) {
        if (index < 0) return 1;

        int lastDigit = b[index];

        int part1 = modPow(helper(a, b, index - 1), 10, MOD); 

        int part2 = modPow(a, lastDigit, MOD); 
        return (part1 * part2) % MOD;
    }

    private int modPow(int x, int y, int mod) {
        int res = 1;
        x %= mod;

        while (y > 0) {
            if (y % 2 == 1) res = (res * x) % mod; 
            x = (x * x) % mod;  
            y /= 2;  
        }

        return res;
    }
}
