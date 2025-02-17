import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0) return new ArrayList<>();
        return divideAndConquer(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> divideAndConquer(int[][] buildings, int left, int right) {
        if (left == right) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Arrays.asList(buildings[left][0], buildings[left][2]));
            res.add(Arrays.asList(buildings[left][1], 0));
            return res;
        }

        int mid = left + (right - left) / 2;
        List<List<Integer>> leftSkyline = divideAndConquer(buildings, left, mid);
        List<List<Integer>> rightSkyline = divideAndConquer(buildings, mid + 1, right);

        return mergeSkylines(leftSkyline, rightSkyline);
    }

    private List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
        List<List<Integer>> merged = new ArrayList<>();
        int h1 = 0, h2 = 0, i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            int x;
            if (left.get(i).get(0) < right.get(j).get(0)) {
                x = left.get(i).get(0);
                h1 = left.get(i).get(1);
                i++;
            } else if (left.get(i).get(0) > right.get(j).get(0)) {
                x = right.get(j).get(0);
                h2 = right.get(j).get(1);
                j++;
            } else {
                x = left.get(i).get(0);
                h1 = left.get(i).get(1);
                h2 = right.get(j).get(1);
                i++;
                j++;
            }

            int maxHeight = Math.max(h1, h2);
            if (merged.isEmpty() || merged.get(merged.size() - 1).get(1) != maxHeight) {
                merged.add(Arrays.asList(x, maxHeight));
            }
        }

        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));
        return merged;
    }
}
