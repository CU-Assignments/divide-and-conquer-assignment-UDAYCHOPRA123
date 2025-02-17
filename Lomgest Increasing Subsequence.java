class SegmentTree {
    private int[] tree;
    private int size;

    public SegmentTree(int n) {
        size = n;
        tree = new int[4 * n];
    }

    private int query(int l, int r, int tl, int tr, int pos) {
        if (l > r) return Integer.MIN_VALUE; 
        if (l == tl && r == tr) return tree[pos];

        int mid = (tl + tr) / 2;
        return Math.max(
            query(l, Math.min(r, mid), tl, mid, 2 * pos + 1),
            query(Math.max(l, mid + 1), r, mid + 1, tr, 2 * pos + 2)
        );
    }

    private void update(int idx, int val, int l, int r, int pos) {
        if (l == r) {
            tree[pos] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(idx, val, l, mid, 2 * pos + 1);
        else update(idx, val, mid + 1, r, 2 * pos + 2);

        tree[pos] = Math.max(tree[2 * pos + 1], tree[2 * pos + 2]);
    }

    public int queryRange(int l, int r) {
        if (l > r) return 0; 
        return query(l, r, 0, size - 1, 0);
    }

    public void updateValue(int idx, int val) {
        update(idx, val, 0, size - 1, 0);
    }
}

class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);

        SegmentTree segTree = new SegmentTree(maxVal + 1);
        int maxLIS = 0;

        for (int num : nums) {
            int bestPrev = segTree.queryRange(Math.max(0, num - k), num - 1);
            int currLIS = bestPrev + 1;

            segTree.updateValue(num, currLIS);
            maxLIS = Math.max(maxLIS, currLIS);
        }
        return maxLIS;
    }
}
