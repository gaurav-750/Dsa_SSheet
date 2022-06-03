package StriversSheet;
import java.util.*;

public class Arrays_03 {

    public static int longestConsecutiveSequence(int[] nums){
        if (nums.length == 0){
            return 0;
        }

        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums){ //building the hashSet -> with all unique elements
            hs.add(num);
        }

        int longestConSeq = 0;
        for (int num: nums) {
            int curNum = num; int countCon = 0;

            if (!hs.contains(curNum-1)){
                //i'm at the minimal element
                while (hs.contains(curNum)){
                    countCon += 1;
                    curNum++;
                }

                longestConSeq = Math.max(longestConSeq, countCon);
            }
        }

        return longestConSeq;
    }

    public static int maximumSubArrayWithSumZero(ArrayList<Integer> list){

        HashMap<Long, Integer> map = new HashMap<>(); // (prefixSum, index(i)) pairs
        int i = 0; long sum = 0; int maxLen = 0;

        while (i < list.size()){
            sum += list.get(i);

            if (sum == 0){
                maxLen = i+1;
            }else {
                if (map.get(sum) != null){
                    maxLen = Math.max(maxLen, i-map.get(sum));
                }else {
                    map.put(sum, i);
                }
            }

            i++;
        }

        return maxLen;
    }

    public static int longestSubStringWithoutRepeatingChars(String str){

        HashMap<Character, Integer> map = new HashMap<>(); int n = str.length();
        int i = 0;
        int maxLen = 0; String s;

        while (i < n){
            int len = 0;
            while (i < n && !map.containsKey(str.charAt(i))){
                len++;
                map.put(str.charAt(i), i);
                i++;
            }
            //the length is exceeded
            maxLen = Math.max(maxLen, len);
            if (i >= n){return maxLen;}

            //character is repeated:
            int idx = map.get(str.charAt(i));
            for (int j = idx; j < i; j++) {
                map.remove(str.charAt(j));
            }

            i = idx+1;
        }

        return maxLen;
    }

    public static int longestSubStringWithoutRepeatingCharsOptimized(String str){

        HashSet<Character> hs = new HashSet<>();
        int si = 0, ei = 0;
        int maxLenSubString = 0;

        for (ei = 0; ei < str.length(); ei++) {
            char ch = str.charAt(ei);

            if (!hs.contains(ch)){
                hs.add(ch);
            }else {
                while (hs.contains(ch)){
                    hs.remove(str.charAt(si));
                    si++;
                }

                hs.add(str.charAt(ei));
            }

            maxLenSubString = Math.max(maxLenSubString, (ei-si)+1);
        }

        return maxLenSubString;
    }

    public static int longestSubStringWithoutRepeatingCharsUltraOptimized(String str){

        HashMap<Character, Integer> map = new HashMap<>();
        int maxLenSubString = 0;
        int si = 0, ei;

        for (ei = 0; ei < str.length(); ei++) {

            if (map.containsKey(str.charAt(ei))) { //map contains the current character
                int idx = map.get(str.charAt(ei));

                //check if the idx is present in the range(between si and ei) =>
                if (idx >= si) {
                    si = idx + 1; //make si point to next char of idx
                }

                //update the index of current character
            }

            map.put(str.charAt(ei), ei);  // (char, index)
            maxLenSubString = Math.max(maxLenSubString, (ei-si)+1);
        }

        return maxLenSubString;
    }

    static String swap(String str, int i, int j){
        if (str == null || str.isEmpty())
            return str;

        // Converting the given string into a character array
        char[] ch = str.toCharArray();

        // Swapping the characters
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;

        // Converting the result into a string and return
        return new String(ch);
    }

    private static void permutationsOfString(int ind, String str){
        //base case
        if (ind == str.length()){
            System.out.println(str);
            return;
        }

        for (int i = ind; i < str.length(); i++) {
            String swappedStr = swap(str, ind, i);
            System.out.println("swappedStr = " + swappedStr);

            permutationsOfString(ind+1, swappedStr);
        }
        return;
    }

    public static void permutationsOfStringOptimized(String str){

        permutationsOfString(0, str);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();

        /*
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
//        System.out.println("Longest Consecutive Sequence: " + longestConsecutiveSequence(arr));
         */
        /*
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        System.out.println("Max Length of subArray with sum 0: " + maximumSubArrayWithSumZero(list));
        */


//        String str = sc.next();
//        System.out.println("MAX Len: " + longestSubStringWithoutRepeatingChars(str));

        //Solution With HashSet: O(2N)
//        longestSubStringWithoutRepeatingCharsOptimized(str);

        //Solution With HashMap: O(N)
//        longestSubStringWithoutRepeatingCharsUltraOptimized(str);

        System.out.println("Enter a string: ");
        String str = sc.next();
        permutationsOfStringOptimized(str);

    }
}
