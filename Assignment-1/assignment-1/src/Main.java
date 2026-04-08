public class Main {
    public static void main(String[] args) {
//        task1(5481);
//        int[] arr = {3, 2, 4, 1};
//        System.out.println(task2(4, arr));
//        System.out.println(task3(8, 2));
//        System.out.println(task4(5));
//        System.out.println(task5(5));
//        System.out.println(task6(2, 10));
        System.out.println(task8("123456"));
//        System.out.println(task9("hello"));
//        System.out.println(task10(32, 48));

    }
//    public static void task1(int n){
//        if (n < 10){
//            System.out.println(n);
//            return;
//        }
//        System.out.println(n % 10);
//        task1(n / 10);
//    }
//    public static double task2(int n, int[] arr){
//        if (n<=0) return 0;
//        return task2(n-1, arr) + arr[n-1];
//    };
//    public static String task3(int n, int d){
//        if (n <= 2) return (n==2) ? "Prime" : "Composite";
//        if (n % d == 0) return "Composite";
//        if (d * d > n) return "Prime";
//        return task3(n, d + 1);
//    }

//    public static int task4(int n) {
//        if (n <= 1) return 1;
//        return n * task4(n - 1);
//    }
//
//    public static int task5(int n) {
//        if (n <= 1) return n;
//        return task5(n - 1) + task5(n - 2);
//    }
//    public static int task6(int a, int n) {
//        if (n == 0) return 1;
//        return a * task6(a, n - 1);
//    }

    public static String task8(String s) {
        if (s.isEmpty()) return "Yes";
        if (!Character.isDigit(s.charAt(0))) return "No";
        return task8(s.substring(1));
    }

//    public static int task9(String s) {
//        if (s.isEmpty()) return 0;
//        return 1 + task9(s.substring(1));
//    }
//
//    public static int task10(int a, int b) {
//        if (b == 0) return a;
//        return task10(b, a % b);
//    }
}