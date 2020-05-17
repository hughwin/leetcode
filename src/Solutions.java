import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.testng.collections.Lists;

import java.util.*;

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

    @Test
    public void kidsWithCandiesTest() {
        List<Boolean> result = kidsWithCandies(new int[]{4, 2, 1, 1, 2}, 1);
        List<Boolean> expected = Lists.newArrayList(true, false, false, false, false);
        Assert.assertSame(expected, result);
    }

    // https://leetcode.com/problems/reverse-string/
    // Changed return value to test
    // Big O(n)
    public static char[] reverseString(char @NotNull [] s) {
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

        if(m == n){return head;}

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
            if (count == n + 1){
                afterReverse = head;
            }
            head = head.next;
            count++;
        }

            for (int i = nodesToBeReversed.size() - 1; i >= 0; i--) {

                if (beforeReverse == null){
                    beforeReverse = nodesToBeReversed.get(i);
                    headCopy = beforeReverse;
                }
                else {
                    beforeReverse.next = nodesToBeReversed.get(i);
                    beforeReverse = beforeReverse.next;
                }
            }

            beforeReverse.next = afterReverse;

        return headCopy;
    }

    @Test
    public void reverseBetweenTest(){

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

    public static int depthCalculator(TreeNode node, int depth){
        if (node == null){return depth;}
        return Math.max(depthCalculator(node.left, depth + 1), depthCalculator(node.right, depth + 1));
    }

    @Test
    public void maxDepthCalculatorTest(){
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        int correctDepth = 2;
        Assert.assertSame(correctDepth, maxDepth(root));
    }

    public static int romanToInt(String s) {

        int sum = 0;

        if (s.length() == 0){
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


        for (int i = 0; i < s.length() ; i++) {
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
        while (head != null){
            values.add(head.val);
            head = head.next;
        }
        ArrayList<Integer> reversed = (ArrayList<Integer>) values.clone();
        Collections.reverse(reversed);
        if (values.equals(reversed)){
            return true;
        }
        return  false;
    }

    @Test
    public void isPalindromeTest(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        b.next = new ListNode(2);
        System.out.println(isPalindrome(a));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {

        int highest = nums.length;
        HashSet<Integer> numbersInNums = new HashSet();

        List<Integer> missingNumbers = new ArrayList<>();

        for (int i = 0; i < highest ; i++){
            numbersInNums.add(nums[i]);
        }

        for (int i = 1; i <= highest; i++){
            if (!numbersInNums.contains(i)){
                missingNumbers.add(i);
            }
        }
        return missingNumbers;
    }



    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{1,1}));
    }


}
