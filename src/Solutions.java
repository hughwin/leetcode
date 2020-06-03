import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//import org.testng.collections.Lists;

public class Solutions {

    // https://leetcode.com/articles/two-sum/

    // Big O(n^2) :(
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int k = 0; k < nums.length; k++) {
                if (i != k) {
                    if (nums[i] + nums[k] == target) {
                        return new int[]{i, k};
                    }
                }
            }
        }
        return null;
    }

    @Test
    public void twoSumTest() {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        int[] expected = new int[]{0, 1};
        Assert.assertArrayEquals(expected, result);
    }

    // https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> returnedValues = new ArrayList<>();
        int[] clonedArray = candies.clone();
        Arrays.sort(candies);
        int max = candies[candies.length - 1];
        for (int i = 0; i < candies.length; i++) {
            returnedValues.add(clonedArray[i] + extraCandies >= max);
        }
        return returnedValues;

    }

//    @Test
//    public void kidsWithCandiesTest() {
//        List<Boolean> result = kidsWithCandies(new int[]{4, 2, 1, 1, 2}, 1);
//        List<Boolean> expected = Lists.newArrayList(true, false, false, false, false);
//        Assert.assertSame(expected, result);
//    }

    // https://leetcode.com/problems/reverse-string/
    // Changed return value to test
    // Big O(n)
    public static char[] reverseString(char[] s) {
        char tempChar;
        for (int i = 0; i < s.length / 2; i++) {
            tempChar = s[i];
            s[i] = s[s.length - i - 1];
            s[(s.length - i - 1)] = tempChar;
        }
        return s;
    }

    @Test
    public void reverseStringTest() {
        char[] result = reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
        char[] expected = new char[]{'o', 'l', 'l', 'e', 'h'};
        Assert.assertArrayEquals(result, expected);
    }

    // https://leetcode.com/problems/search-a-2d-matrix/
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] array : matrix) {
            if (Arrays.binarySearch(array, target) > -1) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void searchMatrixTest() {
        int[][] matrix = new int[][]{new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}};
        boolean result = searchMatrix(matrix, 1);
        Assert.assertSame(true, result);

        boolean failResult = searchMatrix(matrix, 10);
        Assert.assertSame(false, failResult);

    }

    // https://leetcode.com/problems/reverse-linked-list-ii/
    // Definition for singly-linked list.

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {

        if (m == n) {
            return head;
        }

        int count = 1;
        ListNode headCopy = head;
        ListNode beforeReverse = null;
        ListNode afterReverse = null;

        ArrayList<ListNode> nodesToBeReversed = new ArrayList<>();

        while (head != null) {

            if (count == m - 1) {
                beforeReverse = head;
            }
            if (count >= m && count <= n) {
                nodesToBeReversed.add(head);
            }
            if (count == n + 1) {
                afterReverse = head;
            }
            head = head.next;
            count++;
        }

        for (int i = nodesToBeReversed.size() - 1; i >= 0; i--) {

            if (beforeReverse == null) {
                beforeReverse = nodesToBeReversed.get(i);
                headCopy = beforeReverse;
            } else {
                beforeReverse.next = nodesToBeReversed.get(i);
                beforeReverse = beforeReverse.next;
            }
        }

        beforeReverse.next = afterReverse;

        return headCopy;
    }

    @Test
    public void reverseBetweenTest() {

        ListNode resultHead = new ListNode(1);
        ListNode rb = new ListNode(2);
        ListNode rc = new ListNode(3);
        ListNode rd = new ListNode(4);
        ListNode re = new ListNode(5);
        resultHead.next = rb;
        rb.next = rc;
        rc.next = rd;
        rd.next = re;

        ListNode expectedHead = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(5);
        expectedHead.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        Assert.assertSame(expectedHead, reverseBetween(expectedHead, 2, 4));

    }

    // https://leetcode.com/problems/length-of-last-word/
    public static int lengthOfLastWord(String s) {
        String[] stringArray = s.split(" ");
        if (stringArray.length == 0 || stringArray.length == 1) {
            return 0;
        }
        String lastWord = stringArray[stringArray.length - 1];
        return lastWord.length();
    }

    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static int maxDepth(TreeNode root) {
        return depthCalculator(root, 0);
    }

    public static int depthCalculator(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        return Math.max(depthCalculator(node.left, depth + 1), depthCalculator(node.right, depth + 1));
    }

    @Test
    public void maxDepthCalculatorTest() {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        int correctDepth = 2;
        Assert.assertSame(correctDepth, maxDepth(root));
    }

    public static int romanToInt(String s) {

        int sum = 0;

        if (s.length() == 0) {
            return sum;
        }

        HashMap<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);


        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                if (s.charAt(i) == 'I' && s.charAt(i + 1) == 'V') {
                    sum += 4;
                    i += 1;
                    continue;
                }
                if (s.charAt(i) == 'I' && s.charAt(i + 1) == 'X') {
                    sum += 9;
                    i += 1;
                    continue;
                }
                if (s.charAt(i) == 'X' && s.charAt(i + 1) == 'L') {
                    sum += 40;
                    i += 1;
                    continue;
                }
                if (s.charAt(i) == 'X' && s.charAt(i + 1) == 'C') {
                    sum += 90;
                    i += 1;
                    continue;
                }
                if (s.charAt(i) == 'C' && s.charAt(i + 1) == 'D') {
                    sum += 400;
                    i += 1;
                    continue;
                }
                if (s.charAt(i) == 'C' && s.charAt(i + 1) == 'M') {
                    sum += 900;
                    i += 1;
                    continue;
                }
            }
            sum += romanNumerals.get(s.charAt(i));
        }
        return sum;
    }

    @Test
    public void romanNumeralTest() {
        Assert.assertSame(99, romanToInt("XCIX"));
    }

    public static boolean isPalindrome(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        ArrayList<Integer> reversed = (ArrayList<Integer>) values.clone();
        Collections.reverse(reversed);
        return values.equals(reversed);
    }

    @Test
    public void isPalindromeTest() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        b.next = new ListNode(2);
        System.out.println(isPalindrome(a));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {

        int highest = nums.length;
        HashSet<Integer> numbersInNums = new HashSet<Integer>();

        List<Integer> missingNumbers = new ArrayList<>();

        for (int num : nums) {
            numbersInNums.add(num);
        }

        for (int i = 1; i <= highest; i++) {
            if (!numbersInNums.contains(i)) {
                missingNumbers.add(i);
            }
        }
        return missingNumbers;
    }

    // https://leetcode.com/problems/peak-index-in-a-mountain-array/submissions/

    public static int peakIndexInMountainArray(int[] a) {
        int max = a[0];
        int indexToReturn = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                indexToReturn = i;
            }
        }
        return indexToReturn;
    }

    @Test
    public void peakIndexInMountainArrayTest() {
        int expected = peakIndexInMountainArray(new int[]{1, 3, 2});
        int actual = 1;
        Assert.assertSame(expected, actual);
    }

    public static List<String> fizzBuzz(int n) {

        List<String> fizzBuzzList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 == 0) {
                fizzBuzzList.add("FizzBuzz");
                continue;
            }
            if (i % 3 == 0) {
                fizzBuzzList.add("Fizz");
                continue;
            }
            if (i % 5 == 0) {
                fizzBuzzList.add("Buzz");
                continue;
            }
            fizzBuzzList.add(i + "");
        }
        return fizzBuzzList;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderList(root, list);
        return list;
    }

    private static void inOrderList(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inOrderList(root.left, list);
        list.add(root.val);
        inOrderList(root.right, list);
    }

    @Test
    public void inOrderTraversalTest() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(7);
        root.left = left;
        root.right = right;
    }

    public static int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == s.lastIndexOf(s.charAt(i)) && i == s.indexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void fizzBuzzTest() {
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("Fizz");
        expected.add("4");
        expected.add("Buzz");
        expected.add("Fizz");
        expected.add("7");
        expected.add("8");
        expected.add("Fizz");
        expected.add("Buzz");
        expected.add("11");
        expected.add("Fizz");
        expected.add("13");
        expected.add("14");
        expected.add("FizzBuzz");

        List<String> actual = fizzBuzz(15);

        Assert.assertEquals(expected, actual);
    }

    public static int heightChecker(int[] heights) {
        int[] unsortedArray = heights.clone();
        Arrays.sort(heights);

        int count = 0;

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != unsortedArray[i]) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void heightCheckerTest() {
        int expected = 5;
        int actual = heightChecker(new int[]{5, 1, 2, 3, 4});
        Assert.assertSame(expected, actual);
    }

    public static List<Integer> findDuplicates(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : nums) {
            if (!hashSet.add(num)) {
                duplicates.add(num);
            }
        }
        return duplicates;
    }

    @Test
    public void findDuplicatesTest(){
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        List<Integer> actual = findDuplicates(new int[]{4,3,2,7,8,2,3,1});
        Assert.assertEquals(expected,actual);
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> exitingValues = new HashSet<>();
        while (head != null){
            if (!exitingValues.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    @Test
    public void hasCycleTest(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        b.next = a;
        boolean actual = hasCycle(a);
        Assert.assertSame(true, actual);
    }

    public static int missingNumber(int[] nums) {
        HashSet<Integer> allNums = new HashSet<>();
        for(int i : nums){
            allNums.add(i);
        }
        int length = nums.length;
        for (int i = 0 ; i < length ; i++){
            if(!allNums.contains(i)){
                return i;
            }
        }
        return length;
    }

    @Test
    public void missingNumberTest(){
        int expected = 3;
        int actual = missingNumber(new int[]{0,1,2,4});
        Assert.assertSame(expected, actual);
    }

    public static int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        String ones = s.replace("0", "");
        return ones.length();
    }

    @Test
    public void hammingWeight(){
        Assert.assertSame(3, hammingWeight(011000100000));
        Assert.assertSame(2, hammingWeight(000110));
    }

    public static int mySqrt(int x){
        double y = Math.sqrt((double) x);
        return (int) y;
    }

    @Test
    public void mySprtTest(){
        Assert.assertSame(2, mySqrt(6));
        Assert.assertSame(7, mySqrt(49));
        Assert.assertSame(5, mySqrt(34));
    }


    public static void main(String[] args) {
        System.out.println(mySqrt(6));
    }
}

