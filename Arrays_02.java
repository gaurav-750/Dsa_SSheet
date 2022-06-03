package StriversSheet;
import java.util.*;

public class Arrays_02 {

    public static int[][] mergeOverLappingIntervals(int[][] intervals){
        ArrayList<int[]> list = new ArrayList<>();
        if (intervals.length == 0){
            return list.toArray(new int[0][0]);
        }

        //Sorting the given intervals matrix:
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
//        System.out.println(Arrays.deepToString(intervals));

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int[] arr: intervals){
            if (arr[0] <= end){ //its overlapping
                end = Math.max(end, arr[1]);
            }else {
                list.add(new int[]{start, end}); //add in result list
                start = arr[0];
                end = arr[1];
            }
        }
        list.add(new int[]{start, end});

        return list.toArray(new int[0][]);
    }

    public static int[] missingAndRepeating(ArrayList<Integer> arr, int n) {
        // Write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int M = 0, R = 0;
        for (int elem: arr){
            if(map.containsKey(elem)){
                R = elem;
                map.put(elem, map.get(elem)+1);
            }else{
                map.put(elem, 1);
            }
        }

        for (int i = 1; i <= n; i++){
            if (!map.containsKey(i)){
                M = i;
                break;
            }
        }

        return new int[]{M, R};
    }

    private static int binarySearch(int[] arr, int target){

        int si = 0; int ei = arr.length-1;
        int mid;
        while (si <= ei){
            mid = (si+ei)/2;

            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                ei = mid-1;
            }else {
                si = mid+1;
            }
        }

        return -1;
    }

    public static boolean searchInMatrix(int[][] matrix, int target){
        //first find the row in which we need to search:
        int row = 0; int col = matrix[0].length-1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] >= target){
                row = i;
                break;
            }
        }

        int[] arr = matrix[row];

        //just do a binary search in this array:
        return binarySearch(arr, target) != -1;
    }

    public static boolean searchInMatrixOptimal(int[][] matrix, int target){
        int si = 0; int ei = matrix.length * matrix[0].length-1;
        int mid, row, col;

        while (si <= ei){
            mid = (si+ei)/2;

            row = mid / matrix[0].length;
            col = mid % matrix[0].length;
//            System.out.println("("+ row + "," + col + ")");

            if (matrix[row][col] == target){
                return true;
            }else if (matrix[row][col] > target){
                ei = mid-1;
            }else {
                si = mid+1;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
        /*
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] result = mergeOverLappingIntervals(arr);
        System.out.println(Arrays.deepToString(result));
        */

        /*
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        System.out.println( Arrays.toString(missingAndRepeating(list, list.size())) );
        */

        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int target = sc.nextInt();
//        System.out.println("Target Present: " + searchInMatrix(matrix, target));
        System.out.println("Target Present: " + searchInMatrixOptimal(matrix, target));

    }
}
