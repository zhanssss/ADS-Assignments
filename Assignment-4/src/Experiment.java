public class Experiment {

    public static long measureSortTime(int[] arr, String type) {
        long startTime = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            Sorter.basicSort(arr);
        } else if (type.equalsIgnoreCase("advanced")) {
            Sorter.advancedSort(arr);
        } else {
            System.out.println("Unknown sort type: " + type);
        }

        return System.nanoTime() - startTime;
    }

    public static long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();

        Searcher.search(arr, target);

        return System.nanoTime() - startTime;
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = Sorter.generateRandomArray(size);
        Sorter.advancedSort(arr);
        return arr;
    }

    public static void runAllExperiments() {
        int[] sizes = {10, 100, 1000};


        for (int size : sizes) {
            int[] randomArray = Sorter.generateRandomArray(size);
            int[] sortedArray = generateSortedArray(size);

            long basicRandomTime = measureSortTime(randomArray.clone(), "basic");
            long advancedRandomTime = measureSortTime(randomArray.clone(), "advanced");

            long basicSortedTime = measureSortTime(sortedArray.clone(), "basic");
            long advancedSortedTime = measureSortTime(sortedArray.clone(), "advanced");

            System.out.println("Array size: " + size);

            System.out.println("Random array:");
            System.out.println("Basic Sort time: " + basicRandomTime + " ns");
            System.out.println("Advanced Sort time: " + advancedRandomTime + " ns");

            System.out.println("Sorted array:");
            System.out.println("Basic Sort time: " + basicSortedTime + " ns");
            System.out.println("Advanced Sort time: " + advancedSortedTime + " ns");
        }

        for (int size : sizes) {
            int[] arr = generateSortedArray(size);
            int target = arr[size / 2];

            long searchTime = measureSearchTime(arr.clone(), target);

            System.out.println("\nArray size: " + size);
            System.out.println("Target: " + target);
            System.out.println("Binary Search time: " + searchTime + " ns");
        }
    }
}