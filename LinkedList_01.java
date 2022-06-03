package StriversSheet;

import java.util.*;

class Node<T> {
    public T data;
    public Node<T> next;

    // Constructor
    public Node(T data){
        this.data = data;
        next = null;
    }


}



public class LinkedList_01 {

    // Taking a Linked list Input from user :-
    public static Node<Integer> takeInput(Scanner sc){

        int data = sc.nextInt(); // taking input for the first node element
        Node<Integer> head = null;
        Node<Integer> tail = null;

        while (data != -1){
            Node<Integer> currentNode = new Node<>(data);

            if (head == null){
                // Make this node as 'head' node and 'tail' node-
                head = currentNode;
            }else {
                tail.next = currentNode;
            }

            tail = currentNode;
            data = sc.nextInt();
        }

        return head;
    }

    public static void printLL(Node<Integer> head){

        Node<Integer> temp = head;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static Node<Integer> reverseLinkedListRecursive(Node<Integer> head){
        //base case
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }

        Node<Integer> sHead = reverseLinkedListRecursive(head.next);

        Node<Integer> temp = head.next;
        temp.next = head;
        head.next = null;

        return sHead;
    }

    public static Node<Integer> reverseLinkedListIterative(Node<Integer> head){
        if (head == null || head.next == null){
            return head;
        }

        Node<Integer> prevNode = null;
        Node<Integer> nextNode;
        while (head != null){
            nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        }

        return prevNode;
    }

    public static Node<Integer> middleOfLinkedList(Node<Integer> head){
        if (head == null || head.next == null){
            return head;
        }

        Node<Integer> slow = head;
        Node<Integer> fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node<Integer> addLinkedList(Node<Integer> head1, Node<Integer> head2){
        //base cases
        if (head1 == null){
            return head2;
        }
        if (head2 == null){
            return head1;
        }

        Node<Integer> newNode = null; Node<Integer> temp = null;
        int carry = 0;
        while (head1 != null || head2 != null || carry != 0){
            int sum = 0;

            if (head1 != null){
                sum += head1.data;
                head1 = head1.next;
            }
            if (head2 != null){
                sum += head2.data;
                head2 = head2.next;
            }

            sum += carry;
            //getting the units place digit
            int unitsPlaceDigit = sum%10;
            sum = sum/10; carry = sum;

            Node<Integer> curNode = new Node<>(unitsPlaceDigit);
            if (newNode == null){
                newNode = curNode;
                temp = newNode;
            }else {
                temp.next = curNode;
                temp = temp.next;
            }
            temp.next = null;
        }

        return newNode;
    }

    public static boolean isPalindromeLinkedList(Node<Integer> head){
        if (head == null){
            return true;
        }

        Stack<Integer> s = new Stack<>();
        Node<Integer> slow = head, fast = head;
        s.push(slow.data);

        //go till the middle of the LinkedList ans push elements at slow into Stack
        while (fast != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            s.push(slow.data);

            fast = fast.next.next;
        }

        if (fast != null && fast.next != null) {
            slow = slow.next;
        }
        //Now go ahead with slow and simultaneously pop elements from stack and check if they r equal
        while (slow != null){
             if (slow.data != s.pop()){
                 return false;
             }
             slow = slow.next;
        }

        return true;
    }

    public static Node<Integer> linkedListCycle(Node<Integer> head){

        Node<Integer> slow = head;
        Node<Integer> fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){
                return slow;
            }
        }

        return null;
    }

    public static Node<Integer> rotateByk(Node<Integer> head, int k){
        if (head == null || head.next == null || k == 0){
            return null;
        }

        //first we'll go to the end of the ll, and append the first node to last,
        //simultaneously cal.len
        Node<Integer> temp = head; int len = 1;

        while (temp.next != null){
            temp = temp.next;
            len += 1;
        }

        temp.next = head; //form a circle
        k = k%len; //as k can exceed the length of LL

        int count = 0;
        while (count < (len-k)){
            temp = temp.next;
            count++;
        }

        head = temp.next;
        temp.next = null;

        return head;
    }

    public static void printAllSubSequencesInArrayWithSumk(int[] arr, int si, ArrayList<Integer> outputSoFar, int sum, int k){
        //base case
        if (si == arr.length){
            //check if sum is equal to k
            if (sum == k){System.out.println(outputSoFar);}
            return;
        }

                    //take the (si)th element:
        outputSoFar.add(arr[si]);
        printAllSubSequencesInArrayWithSumk(arr, si+1, outputSoFar, sum+arr[si], k);

                    //don't take the current element:
        //we have added this element previously, so remove it
        outputSoFar.remove(outputSoFar.size()-1);
        printAllSubSequencesInArrayWithSumk(arr, si+1, outputSoFar, sum, k);
        return;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = 0; int j = 0; int temp;
        while(i < m && j < n){

            if(nums1[i] <= nums2[j]){
                i++;
            }else if(nums1[i] > nums2[j]){
                temp = nums1[i];
                nums1[i] = nums2[j];
                nums2[j] = temp;
//                j++;
            }
        }

        //sort nums2:
        Arrays.sort(nums2);
        //copy nums2 to nums1 from index = m+1
        j = 0;
        for (int k = m; k < m+n; k++) {
            nums1[k] = nums2[j];
            j++;
        }

        System.out.println(Arrays.toString(nums1));
        return;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        Node<Integer> head = takeInput(sc);
//        printLL(head);

//        Node<Integer> result = reverseLinkedListRecursive(head);
//        Node<Integer> result = reverseLinkedListIterative(head);


//        System.out.println("Middle: " + middleOfLinkedList(head).data);


        /*
        Node<Integer> head1 = takeInput(sc);
        Node<Integer> head2 = takeInput(sc);
        Node<Integer> result = addLinkedList(head1, head2);
        printLL(result);
        */

//        System.out.println("Is Palindrome: " + isPalindromeLinkedList(head));


//        Node<Integer> result = linkedListCycle(head);


//        int k = sc.nextInt();
//        printLL(rotateByk(head, k));

        /*
        int[] arr = new int[]{1, 2, 1};
        int k = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        printAllSubSequencesInArrayWithSumk(arr, 0, list, 0, k);
        */

//        int[] candidates = {2, 3, 6, 7};
//        int[] candidates = {1,2};
//        int target = 2;
//        List<List<Integer>> ans = combinationSum(candidates, target);
//        System.out.println(ans);


        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] nums1 = new int[m+n];
        for (int i = 0; i < m; i++) {
            nums1[i] = sc.nextInt();
        }

        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums2[i] = sc.nextInt();
        }

        merge(nums1, m, nums2, n);

    }
}
