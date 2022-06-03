package StriversSheet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Arrays_01 {

    public static ArrayList<ArrayList<Long>> pascalsTriangle(int n){
        ArrayList<ArrayList<Long>> resultList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            //create an arrayList and add it to resultList

            ArrayList<Long> prevList = new ArrayList<>();
            if (i > 1) {
                prevList = resultList.get(i - 2);
            }

            ArrayList<Long> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == (i-1)){
                    list.add(1L);
                }else {
                    long elem = prevList.get(j-1) + prevList.get(j);
                    list.add(elem);
                }
            }
//            System.out.println(list);
            resultList.add(list);
        }

        return resultList;
    }

    private static void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverseArr(int i, int j, int[] arr){
        while (i < j){
            swap(i, j, arr);
            i++;
            j--;
        }
        return;
    }

    public static void nextPermutation(int[] arr){
        int n = arr.length;
        if (n == 0 || n == 1){
            return;
        }

        int idx1 = n-2;
        //find the breakpt such that arr[idx1] < arr[idx+1]
        while (idx1 >= 0 && arr[idx1] >= arr[idx1+1]){
            idx1--;
        }

        if (idx1 >= 0){
            //find an index such that arr[idx2] > arr[idx1]
            int idx2 = n-1;
            while (idx2 >= 0 && arr[idx2] <= arr[idx1]){
                idx2--;
            }

            swap(idx1, idx2, arr); //swap arr[idx1], arr[idx2]
        }

        //reverse the array from idx1+1 to last
        reverseArr(idx1+1, n-1, arr);
        return;
    }

    public static int maximumSubArraySum(int[] arr){
        int sum = 0;
        int maxSubArraySum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (maxSubArraySum < sum){
                maxSubArraySum = sum;
            }
            if (sum < 0){
                sum = 0;
            }
        }

        return maxSubArraySum;
    }

    public static void sort012(int[] arr){

        int low = 0, mid = 0;
        int high = arr.length-1;
        int temp;

        while (mid <= high){

            if (arr[mid] == 0){
                temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            }else if (arr[mid] == 1){
                mid++;
            }else {
                //arr[mid] == 2
                temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }

//        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        /*
        int n = sc.nextInt();
        pascalsTriangle(n);
        */

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        /*
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
        */

        /*
        System.out.println("Max SubArray Sum: " + maximumSubArraySum(arr));
        */

        sort012(arr);

    }
}
