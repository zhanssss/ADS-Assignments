public class Searcher {

    public static int search(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (sorted[mid] == target) {
                return mid;
            }

            if (target > sorted[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}