package StriversSheet;
import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class Recursion_01 {

    private static void subsetSumHelper(ArrayList<Integer> list, int n, int sumTillNow, int i, ArrayList<Integer> outputList){
        System.out.println("i = " + i);
        //base case
        if (i == n){
            outputList.add(sumTillNow);
            return;
        }

        //take the current element in sumTillNow
        int newSum = sumTillNow+list.get(i);
        subsetSumHelper(list, n, newSum, i+1, outputList);

        //don't take the current element in sumTillNow:
        subsetSumHelper(list, n, sumTillNow, i+1, outputList);
        return;
    }

    public static ArrayList<Integer> subsetSum(ArrayList<Integer> list, int n){

        int sumTillNow = 0;
        ArrayList<Integer> outputList = new ArrayList<>();
        subsetSumHelper(list, n, sumTillNow, 0, outputList);
//        System.out.println(outputList);
        return outputList;
    }

    private static void subsetsWithoutDupHelper(int ind, List<Integer> list, List<List<Integer>> ans, int[] nums){

        //create a deep copy of the list and append it to ans:
        ArrayList<Integer> deepCopyOfList = new ArrayList<>(list);
        ans.add(deepCopyOfList);

        if (ind == nums.length){
            return;
        }

        for (int i = ind; i < nums.length; i++) {
            if (i != ind && nums[i] == nums[i-1]){
                continue;
            }

            list.add(nums[i]);
            subsetsWithoutDupHelper(i+1, list, ans, nums);
            list.remove(list.size()-1);
        }
        return;
    }

    public static List<List<Integer>> SubsetsWithoutDuplicates(int[] nums){

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        subsetsWithoutDupHelper(0, list, ans, nums);
        System.out.println(ans);
        return ans;
    }

    private static void CombinationSum2Helper(int ind, int target, List<Integer> list, List<List<Integer>> ans, int[] arr){
        //base case
        if (target == 0){
            //Create a deep copy of list and append it to ans
            List<Integer> deepCopyOfList = new ArrayList<>(list);
            ans.add(deepCopyOfList);
            return;
        }else if (target < 0){ //as going further is of no use as target will keep on decreasing!
            return;
        }


        for (int i = ind; i < arr.length; i++){
            if (i != ind && arr[i] == arr[i-1]){
                continue; //skip the current iteration
            }

            list.add(arr[i]);
            CombinationSum2Helper(i+1, target-arr[i], list, ans, arr);
            list.remove(list.size()-1);
        }

    }

    public static List<List<Integer>> CombinationSum2(int[] arr, int target){
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        CombinationSum2Helper(0, target, list, ans, arr);
//        System.out.println(ans);
        return ans;
    }

    private static void CombinationSum1Helper(int ind, int target, List<Integer> list, List<List<Integer>> ans, int[] arr){
        //base case
        if (target == 0){
            //create a deep copy of list and append it to ans:
            List<Integer> deepCopyOfList = new ArrayList<>(list);
            ans.add(deepCopyOfList);
        }else if (target < 0){
            return;
        }

        for (int i = ind; i < arr.length; i++){
            //to avoid taking duplicates:
            if (i != ind && arr[i] == arr[i-1]){
                continue; //skip the current iteration
            }

            list.add(arr[i]);
            CombinationSum1Helper(i, target-arr[i], list, ans, arr);
            list.remove(list.size()-1);
        }

    }

    public static List<List<Integer>> CombinationSum1(int[] arr, int target){
        //sort the arr so that we can check that avoid condition in for loop in helper function:
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();

        CombinationSum1Helper(0, target, new ArrayList<>(), ans, arr);
//        System.out.println(ans);
        return ans;
    }

    private static int getFact(int n){
        int ans = 1;
        for (int i = 1; i <= n; i++){
            ans = ans*i;
        }
        return ans;
    }

    private static String permutationSequenceHelper(String str, ArrayList<Integer> list, int k){

        k = k-1; //as indexing starts from 0
        while (list.size() != 0){
            int indCombinations = getFact(list.size())/list.size(); //get the first element for the given k and n
            //for eg. the indCombinations for n=4 would be 6!
            int curInd = k/indCombinations; //index of the current element to be added in string

            str += list.get(curInd); //append the element to the string

            list.remove(curInd); //remove the element from the list
            k = k%indCombinations; //get the new k

            //Now we have the new k and n (as list size is reduced by 1) => find the next element by similar procedure
        }

        return str;
    }

    public static String permutationSequence(int n, int k){

        ArrayList<Integer> list = new ArrayList<>();
        //create a list for 1 to n elements:
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        return permutationSequenceHelper("", list, k);
    }

    private static void permutationsHelper(HashSet<Integer> hs, List<Integer> list, List<List<Integer>> ans, int[] arr){
        //base case
        if (list.size() == arr.length){
            //make a deep copy of list and append it to ans:
            ArrayList<Integer> deepCopyOfList = new ArrayList<>(list);
            ans.add(deepCopyOfList);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (hs.contains(arr[i])){
                continue; //skip the current iteration
            }

            hs.add(arr[i]);
            list.add(arr[i]);
            permutationsHelper(hs, list, ans, arr);

            //remove the current element from hs and list:
            list.remove(list.size()-1);
            hs.remove(arr[i]);
        }

    }

    public static List<List<Integer>> permutations(int[] arr){

        List<List<Integer>> ans  = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();

        permutationsHelper(hs, new ArrayList<Integer>(), ans, arr);
        System.out.println(ans);
        return ans;
    }

    private static int[] swap(int i, int j, int[] arr){
        if (i >= arr.length || j >= arr.length){
            return arr;
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return arr;
    }

    private static void permutationsOptimizedHelper(int ind, List<List<Integer>> ans, int[] arr){
        //base case
        if (ind == arr.length){
            List<Integer> list = new ArrayList<>();
            for (int elem: arr){
                list.add(elem);
            }

            ans.add(list);
            return;
        }

        for (int i = ind; i < arr.length; i++) {
            //swap the ith element with element at ind:
            int[] swappedArr = new int[arr.length];
            System.arraycopy(arr, 0, swappedArr, 0, arr.length);
            swap(ind, i, swappedArr);

            permutationsOptimizedHelper(ind+1, ans, swappedArr);
        }

    }

    public static List<List<Integer>> permutationsOptimized(int[] arr){

        List<List<Integer>> ans  = new ArrayList<>();

        permutationsOptimizedHelper(0, ans, arr);
//        System.out.println(ans);
        return ans;
    }

    private static boolean canPutHere(int i, int j, int[][] arr2D){

        //check for attacks horizontally, vertically
        for (int k = 0; k < arr2D.length; k++){
            if (arr2D[i][k] == 1){return false;}
            if (arr2D[k][j] == 1){return false;}
        }

        int n = arr2D.length;
        //check for diagonal attacks:
                //LeftDiagonal:
        //check in the upper half:
        int rowNum = i;
        int colNum = j;
        while (rowNum >= 0 && colNum >= 0){
            if (arr2D[rowNum][colNum] == 1){return false;}
            rowNum--;colNum--;
        }

        //check in the lower half:
        rowNum = i; colNum = j;
        while (rowNum < n && colNum < n){
            if (arr2D[rowNum][colNum] == 1){return false;}
            rowNum++;colNum++;
        }

        //RightDiagonal:
        //check in the upper half:
        rowNum = i; colNum = j;
        while (rowNum >= 0 && colNum < n){
            if (arr2D[rowNum][colNum] == 1){return false;}
            rowNum--;colNum++;
        }

        //check in the lower half:
        rowNum = i; colNum = j;
        while (rowNum < n && colNum >= 0){
            if (arr2D[rowNum][colNum] == 1){return false;}
            rowNum++;colNum--;
        }

        return true;
    }

    private static List<String> addTo2DArray(int[][] arr, List<String> list){
        for (int i = 0; i < arr.length; i++) {
            String str = "";
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 1){str += "Q";}
                else {str += ".";}
            }
            list.add(str);
        }
        return list;
    }

    private static void nQueensHelper(int i, int j, int[][] arr2D, List<List<String>> ans){
        //base case => to print the 2D array
        if (i == arr2D.length){ //you have filled all the rows
            ans.add(addTo2DArray(arr2D, new ArrayList<String>()));
            return;
        }

        while (j < arr2D.length && !canPutHere(i, j, arr2D)){
            j+=1;
        }
        if (j >= arr2D.length){ //if the queen cannot be placed in the current column, BACKTRACK!
            return;
        }

        //put the Queen at (i,j)
        arr2D[i][j] = 1;
        nQueensHelper(i+1, 0, arr2D, ans); //call on the next row to place the queen

        //remove the queen from the current pos. as the new queen was not able to be placed in the next row
        arr2D[i][j] = 0;
        nQueensHelper(i, j+1, arr2D, ans);//call on the next column:
        return;
    }

    public static List<List<String>> nQueens(int n){

        int[][] arr2D = new int[n][n];
        List<List<String>> ans = new ArrayList<>();

        nQueensHelper(0, 0, arr2D, ans);
//        System.out.println(ans);
        return ans;
    }

    private static boolean canIPlacekHere(int i, int j, int k, int[][] board) {

        int x = i; int y = j;
        //check if k is already present in the ith row or jth column:
        for (int l = 0; l < 9; l++) {

            if (board[x][l] == k){
                return false;
            }
            if (board[l][y] == k){
                return false;
            }
        }

        //check if k is present in the 3*3 sub-matrix:
        int row = x - (x%3);
        int col = y - (y%3);

        for (int l = row; l < (row+3); l++) {
            for (int m = col; m < (col+3); m++) {
                if (board[l][m] == k)
                    return false;
            }
        }

        return true;
    }

    private static void printBoard(int[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean sudokuHelper(int i, int j, int[][] board){
        System.out.println(i + "  " + j);
        //if all rows are covered, return true
        if (i == 9){
            return true;
        }

        //if j == 9, call on the next row
        if (j >= 9){
            return sudokuHelper(i+1, 0, board);
        }

        //if current cell != 0, call on the next cell (next column) of the current row
        if (board[i][j] != 0){
            return sudokuHelper(i, j+1, board);
        }

        //otherwise look for the element which can be place here:
        for (int k = 1; k <= 9; k++) {
            if (canIPlacekHere(i, j, k, board)){
                board[i][j] = k;

                //call on the next column:
                boolean ans = sudokuHelper(i, j+1, board);
                if (ans){
                    return true;
                }else {
                    board[i][j] = 0;
                }
            }
        }

        return false;
    }

    public static void sudokuSolver(int[][] board){

        sudokuHelper(0, 0, board);
        printBoard(board);
    }

    private static void ratHelper(int i, int j, int[][] matrix, String str, ArrayList<String> list, boolean[][] visited){
        //index out of bounds:
        if (i >= matrix.length || j >= matrix.length || i < 0 || j < 0){
            return;
        }

        //if the current path is blocked || is already visited
        if (matrix[i][j] == 0 || visited[i][j]){
            return;
        }

        //if you have reached the destination, append the string in the list and return:
        if (i == matrix.length-1 && j == matrix.length-1){
            list.add(str);
            return;
        }
        visited[i][j] = true; //mark the current cell as visited

        //call down:
        ratHelper(i+1, j, matrix, str + "D", list, visited) ;

        //call Left:
        ratHelper(i, j-1, matrix, str + "L", list, visited);

        //call right:
        ratHelper(i, j+1, matrix, str + "R", list, visited);

        //call up:
        ratHelper(i-1, j, matrix, str + "U" , list, visited);

        //You have now explored all the four paths, you can now mark this path as unvisited!
        visited[i][j] = false;
        return;
    }

    public static ArrayList<String> ratInAMaze(int[][] matrix){

        ArrayList<String> list = new ArrayList<>();
        //boolean matrix:
        boolean[][] visited = new boolean[matrix.length][matrix.length];

        ratHelper(0, 0, matrix, "", list, visited);
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            list.add(sc.nextInt());
//        }

//        subsetSum(list, n);

//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//        }
//        SubsetsWithoutDuplicates(arr);

//        System.out.println("Enter target: ");
//        int target = sc.nextInt();
//
//        CombinationSum1(arr, target);

//        CombinationSum2(arr, target);


//        System.out.println("Enter k: ");
//        int k = sc.nextInt();
        System.out.println(permutationSequence(n, k));

//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//        }
//
////        permutations(arr);
//        permutationsOptimized(arr);

//        nQueens(n);

        String str = "DDD";
        System.out.println(str.substring(0, str.length()-1));

        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

//        sudokuSolver(board);

        System.out.println(ratInAMaze(board));
    }
}
