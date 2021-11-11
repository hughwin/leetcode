import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    // Two pointer solution
    public int[] twoSumTwoPointer(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                return new int[]{l + 1, r + 1};
            }
            if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }


    @Test
    public void twoSumTwoPointerTest() {
        int[] result = twoSumTwoPointer(new int[]{2, 7, 11, 15}, 9);
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
        HashSet<Integer> numbersInNums = new HashSet<>();

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

    public static TreeNode inorderTraversalHelp(TreeNode root) {
        if (root.right == null && root.left == null) {
            return root;
        }
        if (root.left != null) {
            return inorderTraversalHelp(root.left);
        }
        if (root.right != null) {
            return inorderTraversalHelp(root.right);
        }
        return null;
    }

    // https://leetcode.com/problems/running-sum-of-1d-array/submissions/
    public static int[] runningSum(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            nums[i] = count;
        }
        return nums;
    }

    // https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/submissions/
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] shorter = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int num : nums) {
                if (nums[i] > num) {
                    count++;
                }
            }
            shorter[i] = count;
        }
        return shorter;
    }

    public static String[] findWords(String[] words) {
        HashSet<Character> firstRow = new HashSet<>();
        firstRow.add('q');
        firstRow.add('w');
        firstRow.add('e');
        firstRow.add('r');
        firstRow.add('t');
        firstRow.add('y');
        firstRow.add('u');
        firstRow.add('i');
        firstRow.add('o');
        firstRow.add('p');

        HashSet<Character> secondRow = new HashSet<>();
        secondRow.add('a');
        secondRow.add('s');
        secondRow.add('d');
        secondRow.add('f');
        secondRow.add('g');
        secondRow.add('h');
        secondRow.add('j');
        secondRow.add('k');
        secondRow.add('l');

        HashSet<Character> thirdRow = new HashSet<>();
        thirdRow.add('z');
        thirdRow.add('x');
        thirdRow.add('c');
        thirdRow.add('v');
        thirdRow.add('b');
        thirdRow.add('n');
        thirdRow.add('m');


        ArrayList<String> returnedWords = new ArrayList<>();

        for (String word : words) {
            if (findWordsHelper(word, firstRow) || findWordsHelper(word, secondRow) || findWordsHelper(word, thirdRow)) {
                returnedWords.add(word);
            }
        }

        for (String word : returnedWords) {
            System.out.println(word);
        }
        return returnedWords.toArray(String[]::new);
    }

    // https://leetcode.com/problems/fibonacci-number/
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int count = 1;
        int current = 0;
        int next;
        int temp = 0;
        while (count < n) {
            if (current == 0) {
                current = 1;
            }
            next = current + temp;
            temp = current;
            current = next;
            count++;
            System.out.println(current);
        }
        return current;
    }

    // https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/submissions/
    public static int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    // https://leetcode.com/problems/available-captures-for-rook
    public static int numRookCaptures(char[][] board) {
        int count = 0;
        ArrayList<int[][]> otherPieces = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (board[i][k] == 'R') {
                    for (int j = i; j >= 0; j--) {
                        if (board[j][k] == 'p') {
                            count++;
                            break;
                        }
                        if (board[j][k] == 'B') break;
                    }
                    for (int n = i; n < board.length; n++) {
                        if (board[n][k] == 'p') {
                            count++;
                            break;
                        }
                        if (board[n][k] == 'B') break;
                    }
                    for (int m = k; m >= 0; m--) {
                        if (board[i][m] == 'p') {
                            count++;
                            break;
                        }
                        if (board[i][m] == 'B') break;
                    }
                    for (int p = k; p < board[i].length; p++) {
                        if (board[i][p] == 'p') {
                            count++;
                            break;
                        }
                        if (board[i][p] == 'B') break;
                    }
                }
            }
        }
        return count;
    }

    public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    // I have kept this method in as an example of a naive approach to the algorithm. A much improved solution is below.
    public static int singleNonDuplicateSolution1(int[] nums) {
        List<Integer> numsAsList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numsAsList.add(num);
        }
        for (int i = 0; i < numsAsList.size(); i++) {
            if (numsAsList.indexOf(numsAsList.get(i)) == numsAsList.lastIndexOf(numsAsList.get(i))) {
                return numsAsList.get(i);
            }
        }
        return 0;
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
    public void findDuplicatesTest() {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        List<Integer> actual = findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        Assert.assertEquals(expected, actual);
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> exitingValues = new HashSet<>();
        while (head != null) {
            if (!exitingValues.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    @Test
    public void hasCycleTest() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        b.next = a;
        boolean actual = hasCycle(a);
        Assert.assertSame(true, actual);
    }

    public static int missingNumber(int[] nums) {
        HashSet<Integer> allNums = new HashSet<>();
        for (int i : nums) {
            allNums.add(i);
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (!allNums.contains(i)) {
                return i;
            }
        }
        return length;
    }

    @Test
    public void missingNumberTest() {
        int expected = 3;
        int actual = missingNumber(new int[]{0, 1, 2, 4});
        Assert.assertSame(expected, actual);
    }

    public static int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        String ones = s.replace("0", "");
        return ones.length();
    }

    @Test
    public void hammingWeight() {
        Assert.assertSame(3, hammingWeight(011000100000));
        Assert.assertSame(2, hammingWeight(000110));
    }

    public static int mySqrt(int x) {
        double y = Math.sqrt(x);
        return (int) y;
    }

    @Test
    public void mySprtTest() {
        Assert.assertSame(2, mySqrt(6));
        Assert.assertSame(7, mySqrt(49));
        Assert.assertSame(5, mySqrt(34));
    }

    public static boolean validMountainArray(int[] a) {
        int firstValue = a[0];
        boolean descending = false;

        for (int i = 1; i < a.length; i++) {
            if (a[i] < firstValue && !descending) {
                descending = true;
            }
            if (a[i] > firstValue && descending) {
                return false;
            }
            if (a[i] == firstValue) {
                return false;
            }
            firstValue = a[i];
        }
        return true;
    }

    @Test
    public void validMountainArrayTest() {
        Assert.assertTrue(validMountainArray(new int[]{1, 2, 3, 4, 5, 6, 7}));
        Assert.assertFalse(validMountainArray(new int[]{0, 2, 3, 3, 5, 2, 1, 0}));
        Assert.assertTrue(validMountainArray(new int[]{0, 3, 2, 1}));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutiveNumbers = 0;
        int count = 0;
        for (int num : nums) {
            if (num != 0) {
                count++;
            } else {
                count = 0;
            }
            if (count > maxConsecutiveNumbers) {
                maxConsecutiveNumbers = count;
            }
        }
        return maxConsecutiveNumbers;
    }

    @Test
    public void findMaxConsecutiveOnesTest() {
        Assert.assertEquals(3, findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        Assert.assertEquals(6, findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1, 1}));
    }

    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int i : nums) {
            String k = i + "";
            if (k.length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void findNumbersTest() {
        Assert.assertEquals(2, findNumbers(new int[]{12, 345, 2, 6, 7896}));
    }

    public static int islandPerimeter(int[][] island) {

        int perimeter = 0;

        for (int i = 0; i < island.length; i++) {
            for (int k = 0; k < island[i].length; k++) {
                if (island[i][k] == 1) {
                    try {
                        if (island[i - 1][k] == 0) {
                            perimeter += 1;
                        }
                    } catch (Exception e) {
                        perimeter += 1;
                    }
                    try {
                        if (island[i + 1][k] == 0) {
                            perimeter += 1;
                        }
                    } catch (Exception e) {
                        perimeter += 1;
                    }
                    try {
                        if (island[i][k + 1] == 0) {
                            perimeter += 1;
                        }
                    } catch (Exception e) {
                        perimeter += 1;
                    }
                    try {
                        if (island[i][k - 1] == 0) {
                            perimeter += 1;
                        }
                    } catch (Exception e) {
                        perimeter += 1;
                    }
                }
            }
        }
        return perimeter;
    }

    public static int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i += 2) {
            try {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            } catch (IndexOutOfBoundsException e) {
                return nums[i];
            }
        }
        return 0;
    }

    public static boolean findWordsHelper(String word, HashSet<Character> row) {
        for (int i = 0; i < word.length(); i++) {
            if (!row.contains(word.toLowerCase().charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String reverseWords(String s) {
        String[] splitString = s.split(" ");
        for (int i = 0; i < splitString.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int k = splitString[i].length() - 1; k >= 0; k--) {
                stringBuilder.append(splitString[i].charAt(k));
            }
            splitString[i] = stringBuilder.toString();
        }
        return String.join(" ", splitString);
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] newArray = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length; i += 2) {
            newArray[i] = nums[count];
            newArray[i + 1] = nums[n + count];
            count++;
        }
        return newArray;
    }

    public static int compareVersion(String version1, String version2) {
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int min = Math.min(version1Array.length, version2Array.length);
        int count = 0;
        for (int i = 0; i < min; i++) {
            if (Integer.parseInt(version1Array[i]) < Integer.parseInt(version2Array[i])) {
                return -1;
            }
            if (Integer.parseInt(version1Array[i]) > Integer.parseInt(version2Array[i])) {
                return 1;
            }
            count++;
        }
        if (version2Array.length > version1Array.length) {
            for (int i = count; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) != 0) {
                    return -1;
                }
            }
        }
        if (version1Array.length > version2Array.length) {
            for (int i = count; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) != 0) {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Test
    public void angleClockTest() {
        Assert.assertEquals(165.0, angleClock(12, 30), 0.0);
        Assert.assertEquals(75.0, angleClock(3, 30), 0.0);
        Assert.assertEquals(7.5, angleClock(3, 15), 0.0);
    }

    // https://leetcode.com/problems/middle-of-the-linked-list/
    public static ListNode middleNode(ListNode head) {
        ArrayList<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        return nodes.get(nodes.size() / 2);
    }

    // https://leetcode.com/problems/shuffle-string/discuss/765424/Easy-Approach-or-Java-Solution
    public static String restoreString(String s, int[] indices) {
        String[] split = s.split("");
        String[] reconstituted = new String[split.length];

        for (int i = 0; i < indices.length; i++) {
            int key = indices[i];
            reconstituted[key] = split[i];
        }
        return String.join("", reconstituted);
    }

    public static boolean selfDividingNumber(int num) {
        String[] nums = String.valueOf(num).split("");
        for (String s : nums) {
            if (Integer.parseInt(s) == 0) {
                return false;
            }
            if (num % Integer.parseInt(s) != 0) {
                return false;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/flipping-an-image/
    public static int[][] flipAndInvertImage(int[][] A) {
        int[][] b = A.clone();
        for (int i = 0; i < A.length; i++) {
            int[] copy = new int[A[i].length];
            for (int k = 0; k < A[i].length; k++) {
                copy[A[i].length - k - 1] = (A[i][k] == 0 ? 1 : 0);
                b[i] = copy;
            }
        }
        return b;
    }

    public static int getDecimalValue(ListNode head) {
        String binaryString = "";
        while (head != null) {
            binaryString += (String.valueOf(head.val));
            head = head.next;
        }
        return Integer.parseInt(binaryString, 2);
    }

    // https://leetcode.com/problems/non-decreasing-array/
    public static boolean checkPossibility(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1] && (i == 1 || nums[i] >= nums[i - 2])) {
                return checkPossibility(nums, i + 1, nums[i]);
            } else if (nums[i] < nums[i - 1]) {
                return checkPossibility(nums, i + 1, nums[i - 1]);
            }
        }
        return true;
    }

    private static boolean checkPossibility(int[] nums, int start, int num) {
        int prev = num;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] < prev)
                return false;
            prev = nums[i];
        }
        return true;
    }

    @Test
    public void getDecimalValue() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(0);
        ListNode c = new ListNode(1);

        a.next = b;
        b.next = c;

        Assert.assertSame(5, getDecimalValue(a));
    }

    // https://leetcode.com/problems/to-lower-case/
    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    @Test
    public void toLowerCaseTest() {
        Assert.assertEquals("hello", toLowerCase("HELLO"));
    }

    // https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
    public static int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] ints : grid) {
            for (int k = ints.length - 1; k >= 0; k--) {
                if (ints[k] < 0) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    // https://leetcode.com/problems/water-bottles
    public static int numWaterBottles(int numBottles, int numExchange) {
        int count = 0;
        int empties = 0;
        while (numBottles != 0) {
            count += numBottles;
            numBottles = numBottles + empties;
            empties = numBottles % numExchange;
            numBottles = numBottles / numExchange;
        }
        return count;
    }

    // https://leetcode.com/problems/maximum-69-number/
    public static int maximum69Number(int num) {
        String numString = String.valueOf(num);
        numString = numString.replaceFirst("6", "9");
        return Integer.parseInt(numString);
    }

    // https://leetcode.com/problems/create-target-array-in-the-given-order/
    public static int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> numsList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numsList.add(index[i], nums[i]);
        }
        for (int i = 0; i < index.length; i++) {
            index[i] = numsList.get(i);
        }
        return index;
    }

    // https://leetcode.com/problems/squares-of-a-sorted-array/submissions/
    public static int[] sortedSquares(int[] A) {

        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    @Test
    public void middleNode() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        Assert.assertEquals(d, middleNode(a));
    }

    public static int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                int kCopy = k;
                int iCopy = i;
                if (board[i][k] == 'X') {
                    count++;
                    try {
                        while (board[i][k] == 'X') {
                            board[i][k] = 'Y';
                            k++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                    }
                    k = kCopy;
                    i++;
                    try {
                        while (board[i][k] == 'X') {
                            board[i][k] = 'Y';
                            i++;
                            System.out.println(board[i][k]);
                        }
                    } catch (IndexOutOfBoundsException e) {
                    }
                    i = iCopy;
                }
            }
        }
        return count;
    }

    @Test
    public void countBattleshipsTest() {
        char[][] board = new char[3][6];
        board[0] = new char[]{'X', '.', '.', '.', '.', 'X'};
        board[1] = new char[]{',', '.', '.', '.', '.', 'X'};
        board[2] = new char[]{',', '.', '.', '.', '.', 'X'};
        Assert.assertEquals(2, countBattleships(board));
    }

    @Test
    public void numRookCapturesTest() {
//        char[][] board = new char[][]
    }

    //https://leetcode.com/problems/sort-array-by-parity/
    public static int[] sortArrayByParity(int[] A) {
        ArrayList<Integer> evens = new ArrayList<>();
        ArrayList<Integer> odds = new ArrayList<>();
        for (int a : A) {
            if (a % 2 == 0) {
                evens.add(a);
            } else {
                odds.add(a);
            }
        }
        evens.addAll(odds);
        for (int i = 0; i < evens.size(); i++) {
            A[i] = evens.get(i);
        }
        return A;
    }

    public static int maxNumberOfBalloons(String text) {
        int b = 0;
        int a = 0;
        int l = 0;
        int o = 0;
        int n = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'b') b++;
            if (text.charAt(i) == 'a') a++;
            if (text.charAt(i) == 'l') l++;
            if (text.charAt(i) == 'o') o++;
            if (text.charAt(i) == 'n') n++;

        }
        l = l / 2;
        o = o / 2;

        return Math.min(Math.min(o, l), Math.min(Math.min(b, a), n));

    }

    // https://leetcode.com/problems/search-in-a-binary-search-tree
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (val > root.val) {
            return searchBST(root.right, val);
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        }
        return root;
    }

    // https://leetcode.com/problems/unique-number-of-occurrences/
    public static boolean uniqueOccurrences(int[] arr) {

        HashSet<Integer> counts = new HashSet<>();
        Arrays.sort(arr);
        int temp = arr[0];
        counts.add(arr[0]);
        int occurrences = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == temp) {
                occurrences++;
            } else {
                if (!counts.add(occurrences)) {
                    return false;
                }
                temp = arr[i];
                occurrences = 1;
            }
        }
        return true;
    }

    @Test
    public void runningSumTest() {
        int[] actual = runningSum(new int[]{1, 2, 3, 4});
        int[] expected = new int[]{1, 3, 6, 10};
        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void smallerNumbersThanCurrentTest() {
        int[] actual = smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3});
        int[] expected = new int[]{4, 0, 1, 1, 3};
        Assert.assertArrayEquals(expected, actual);
    }

    // https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> shorterOnRight = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int k = i; k < nums.length; k++) {
                if (nums[i] > nums[k]) {
                    count++;
                }
            }
            shorterOnRight.add(count);
        }
        return shorterOnRight;
    }

    @Test
    public void countSmallerTest() {
        List<Integer> actual = countSmaller(new int[]{5, 2, 6, 1});
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        expected.add(1);
        expected.add(0);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void defangIPaddrTest() {
        Assert.assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        result.add(root.val);
        if (root.left != null) {
            result.addAll(preorderTraversal(root.left));
        }
        if (root.right != null) {
            result.addAll(preorderTraversal(root.right));
        }

        return result;
    }

    @Test
    public void findWordsTest() {
        String[] expected = new String[]{"Alaska", "Dad"};
        Assert.assertArrayEquals(expected, findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"}));
    }

    @Test
    public void checkPossibilityTest() {
        Assert.assertSame(true, checkPossibility(new int[]{4, 2, 3}));
        Assert.assertSame(false, checkPossibility(new int[]{4, 2, 1}));
        Assert.assertSame(false, checkPossibility(new int[]{3, 4, 2, 3}));

    }

    // https://leetcode.com/problems/day-of-the-week
    public String dayOfTheWeek(int day, int month, int year) {
        String[] daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return daysOfWeek[LocalDate.of(year, month, day).getDayOfWeek().getValue() - 1];

    }

    @Test
    public void fibTest() {
        Assert.assertEquals(1, fib(2));
        Assert.assertEquals(2, fib(3));
        Assert.assertEquals(3, fib(4));
    }

    @Test
    public void maxProductTest() {
        Assert.assertEquals(16, maxProduct(new int[]{1, 5, 4, 5}));
        Assert.assertEquals(12, maxProduct(new int[]{3, 4, 5, 2}));
        Assert.assertEquals(12, maxProduct(new int[]{3, 7}));
    }

    @Test
    public void singleNonDuplicateTest() {
        int[] input1 = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8};
        int[] input2 = new int[]{3, 3, 7, 7, 10, 11, 11};
        Assert.assertEquals(2, singleNonDuplicate(input1));
        Assert.assertEquals(10, singleNonDuplicate(input2));
    }

    @Test
    public void restoreStringTest() {
        Assert.assertEquals("nihao", restoreString("aiohn", new int[]{3, 1, 4, 2, 0}));
    }

    // https://leetcode.com/problems/self-dividing-numbers/discuss/758471/Java-oror-2ms
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (selfDividingNumber(i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    @Test
    public void selfDividingNumbersTest() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        input.add(8);
        input.add(9);
        input.add(11);
        input.add(12);
        input.add(15);
        input.add(22);
        Assert.assertEquals(input, selfDividingNumbers(1, 22));
    }

    @Test
    public void dayOfTheWeekTest() {
        Assert.assertEquals("Saturday", dayOfTheWeek(31, 8, 2019));
        Assert.assertEquals("Sunday", dayOfTheWeek(15, 8, 1993));

    }

    @Test
    public void deleteNodeTest() {
        //TODO: Finish this later
    }

    @Test
    public void countNegativesTest() {
        int[][] input = new int[2][4];
        input[0] = new int[]{3, 2, -1, -2};
        input[1] = new int[]{1, 0, 0, -3};
        Assert.assertEquals(3, countNegatives(input));

        int[][] input2 = new int[2][2];
        input[0] = new int[]{3, 2};
        input[1] = new int[]{1, 0};
        Assert.assertEquals(0, countNegatives(input2));
    }

    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    public String removeDuplicates(String S) {
        char[] stringArr = S.toCharArray();

        for (int i = 0; i < stringArr.length - 1; i++) {
            if (stringArr[i] == stringArr[i + 1]) {
                String beginning = String.valueOf(Arrays.copyOfRange(stringArr, 0, i));
                String end = i + 1 > stringArr.length ? "" : String.valueOf(Arrays.copyOfRange(stringArr, i + 2, stringArr.length));

                return this.removeDuplicates(beginning + end);
            }
        }

        return String.valueOf(stringArr);
    }

    @Test
    public void removeDuplicatesTest() {
        Assert.assertEquals("ca", removeDuplicates("abbaca"));
    }

    @Test
    public void numWaterBottlesTest() {
        Assert.assertEquals(19, numWaterBottles(15, 4));
    }

    public double angleClock(int hour, int minutes) {
        if (hour == 12) hour = 0;
        int minutesAngle = minutes * 6;
        double minutesHour = (minutes / 60.0);
        double actualHour = minutesHour + hour;
        double actualHourAngle = actualHour * 30;
        double angle1 = Math.abs((actualHourAngle) - (minutesAngle));
        return Math.min(angle1, 360 - angle1);
    }

    @Test
    public void reverseWordsTest() {
        Assert.assertEquals("s'teL ekat edoCteeL tsetnoc", reverseWords("Let's take LeetCode contest"));
    }

    @Test
    public void compareVersionTest() {
        Assert.assertEquals(-1, compareVersion("0.1", "1.1"));
        Assert.assertEquals(1, compareVersion("1.0.1", "1"));
        Assert.assertEquals(-1, compareVersion("7.5.2.4", "7.5.3"));
        Assert.assertEquals(0, compareVersion("01", "1"));
        Assert.assertEquals(-1, compareVersion("1", "1.1"));
        Assert.assertEquals(0, compareVersion("1.0", "1"));
    }

    // https://leetcode.com/problems/valid-palindrome/
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase().replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(s);
        String reversed = stringBuilder.reverse().toString();
        return reversed.equals(s);
    }

    @Test
    public void sortArrayByParity() {
        Assert.assertArrayEquals(new int[]{2, 4, 3, 1}, sortArrayByParity(new int[]{3, 1, 2, 4}));
    }

    @Test
    public void validPalindromeTest() {
        Assert.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome("race a car"));
    }

    @Test
    public void numberOfStepsTest() {
        Assert.assertEquals(6, numberOfSteps(14));
        Assert.assertEquals(4, numberOfSteps(8));
        Assert.assertEquals(12, numberOfSteps(123));

    }

    @Test
    public void islandPerimeterTest() {
        int[][] values = new int[4][4];
        values[0][1] = 1;
        values[1][0] = 1;
        values[1][1] = 1;
        values[1][1] = 1;
        values[1][2] = 1;
        values[2][1] = 1;
        values[3][0] = 1;
        values[3][1] = 1;
        int[][] singleValue = new int[1][4];
        singleValue[0][2] = 1;
        Assert.assertEquals(16, islandPerimeter(values));
        Assert.assertEquals(4, islandPerimeter(singleValue));
    }

    @Test
    public void sortArrayByParityII() {
        Assert.assertArrayEquals(new int[]{4, 5, 2, 7}, sortArrayByParityII(new int[]{4, 2, 5, 7}));
    }

    //    https://leetcode.com/problems/shuffle-the-array/submissions/
    @Test
    public void shuffleTest() {
        int[] expected = new int[]{2, 3, 5, 4, 1, 7};
        int[] actual = shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findComplementTest() {
        Assert.assertEquals(2, findComplement(5));
    }

    // https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] split = sentence.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(searchWord))
                return i + 1;
        }
        return -1;
    }

    // https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        if (node.next != null || node != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    // https://leetcode.com/problems/single-number/
    public int singleNumber(int[] nums) {
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        for (int num : nums) {
            if (numsList.indexOf(num) == numsList.lastIndexOf(num)) {
                return num;
            }
        }
        return -1;
    }

    public String reverseWords2(String s) {
        String[] strSplit = s.split(" ");
        List<String> strClone = new ArrayList<>();

        for (int i = strSplit.length - 1; i >= 0; i--) {
            if (!strSplit[i].equals("")) {
                strClone.add(strSplit[i].strip());
            }
        }

        return String.join(" ", strClone);
    }

    // https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
    public int[] sortByBits(int[] arr) {
        Integer[] integerArr = new Integer[arr.length];
        Arrays.setAll(integerArr, i -> arr[i]);
        Arrays.sort(integerArr, (o1, o2) -> {
            if (Integer.bitCount(o1) > Integer.bitCount(o2))
                return 1;
            else if (Integer.bitCount(o1) < Integer.bitCount(o2))
                return -1;
            else
                return o1.compareTo(o2);
        });
        return Arrays.stream(integerArr).mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void reverseWords2Test() {
        Assert.assertEquals("blue is sky the", reverseWords2("the   sky is blue"));
    }

    // https://leetcode.com/problems/binary-prefix-divisible-by-5/submissions/
    public List<Boolean> prefixesDivBy5(int[] A) {
        if (A == null || A.length <= 0) return null;
        int num = 0;
        List<Boolean> list = new ArrayList<>();
        for (int i : A) {
            num = (num * 2 + i) % 5;
            if (num == 0) list.add(true);
            else list.add(false);
        }
        return list;
    }

    @Test
    public void prefixesDivBy5Test() {
        List<Boolean> expected;
        expected = Arrays.asList(true, false, false);
        int[] input = new int[]{0, 1, 1};
        Assert.assertEquals(expected, prefixesDivBy5(input));
    }

    @Test
    public void isPrefixOfWordTest() {
        Assert.assertEquals(4, isPrefixOfWord("i love eating burger", "burg"));
        Assert.assertEquals(2, isPrefixOfWord("this problem is an easy problem", "pro"));
        Assert.assertEquals(2, isPrefixOfWord("dumb dream duck duck i", "drea"));
        Assert.assertEquals(4, isPrefixOfWord("love errichto jonathan dumb", "dumb"));
    }

    @Test
    public void reverseTest() {
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(-321, reverse(-123));
        Assert.assertEquals(21, reverse(120));
    }

    // https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
    public int numberOfSteps(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                count++;
                num = num / 2;
            } else {
                count++;
                num--;
            }
        }
        return count;
    }

    @Test
    public void findSpecialIntegerTest() {
        Assert.assertEquals(6, findSpecialInteger(new int[]{1, 2, 2, 6, 6, 6, 6, 7, 10}));
        Assert.assertEquals(3, findSpecialInteger(new int[]{1, 2, 3, 3}));
    }

    // https://leetcode.com/problems/sort-array-by-parity-ii/
    public int[] sortArrayByParityII(int[] A) {

        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        int evensPointer = 0;
        int oddsPointer = 0;

        for (int j : A) {
            if (j % 2 == 0) {
                evens.add(j);
            } else odds.add(j);
        }
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0) {
                A[i] = evens.get(evensPointer);
                evensPointer++;
            } else {
                A[i] = odds.get(oddsPointer);
                oddsPointer++;
            }
        }
        return A;
    }

    @Test
    public void maxNumberOfBalloonsTest() {
        Assert.assertEquals(1, maxNumberOfBalloons("nlaebolko"));
        Assert.assertEquals(2, maxNumberOfBalloons("loonbalxballpoon"));
        Assert.assertEquals(0, maxNumberOfBalloons("leetcode"));
    }

    // https://leetcode.com/problems/number-complement/
    public int findComplement(int num) {
        String binary = Integer.toBinaryString(num);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                sb.append('1');
            } else sb.append('0');
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    @Test
    public void maxPowerTest() {
        Assert.assertEquals(2, maxPower("leetcode"));
        Assert.assertEquals(5, maxPower("abbcccddddeeeeedcba"));
        Assert.assertEquals(5, maxPower("triplepillooooow"));
    }

    public int findSpecialInteger(int[] arr) {
        int twentyFive = (int) (arr.length * 0.25);
        int count = 0;
        int currentInt = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == currentInt) {
                count++;
                if (count > twentyFive) {
                    return currentInt;
                }
            } else {
                count = 0;
                currentInt = arr[i + 1];
            }
        }
        return -1;
    }

    @Test
    public void singleNumberTest() {
        Assert.assertEquals(1, singleNumber(new int[]{2, 2, 1}));
    }

    @Test
    public void sortByBitsTest() {
        int[] expected = new int[]{0, 1, 2, 4, 8, 3, 5, 6, 7};
        int[] input = new int[]{0, 1, 2, 4, 8, 3, 5, 6, 7};
        Assert.assertArrayEquals(expected, sortByBits(input));

        int[] expected1 = new int[]{2, 3, 5, 17, 7, 11, 13, 19};
        int[] input1 = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
        Assert.assertArrayEquals(expected1, sortByBits(input1));

        int[] expected2 = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        int[] input2 = new int[]{1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        Assert.assertArrayEquals(expected2, sortByBits(input2));
    }

    @Test
    public void threeConsecutiveOddsTest() {
        Assert.assertFalse(threeConsecutiveOdds(new int[]{2, 6, 4, 1}));
        Assert.assertTrue(threeConsecutiveOdds(new int[]{1, 2, 34, 3, 4, 5, 7, 23, 12}));
    }

    public int maxPower(String s) {
        char currentCharacter = s.charAt(0);
        int count = 1;
        int max = 1;
        for (int i = 1; i < s.length(); i++) {
            if (currentCharacter == s.charAt(i)) {
                count++;
            } else {
                currentCharacter = s.charAt(i);
                count = 1;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    @Test
    public void uniqueOccurrencesTest() {
        Assert.assertTrue(uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
        Assert.assertFalse(uniqueOccurrences(new int[]{1, 2}));
        Assert.assertTrue(uniqueOccurrences(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
    }

    // https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        List<Integer> numsList = new ArrayList<>();
        for (int x : nums) {
            numsList.add(x);
        }
        Collections.sort(numsList);
        int count = 1;
        int max = 0;
        int current = numsList.get(0);
        int maxItem = 0;

        for (int i = 1; i < numsList.size(); i++) {
            if (numsList.get(i) == current) {
                count++;
            } else {
                current = numsList.get(i);
                count = 1;
            }
            if (count > max) {
                max = count;
                maxItem = numsList.get(i);
            }
        }
        return maxItem;
    }

    public int reverse(int x) {
        boolean lessThan = x < 0;
        x = Math.abs(x);
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        String reversed = sb.reverse().toString();
        reversed = Integer.toBinaryString(Integer.parseInt(reversed));
        return (lessThan) ? -Integer.parseInt(reversed, 2) : Integer.parseInt(reversed, 2);
    }

    @Test
    public void majorityElementTest() {
        Assert.assertEquals(3, majorityElement(new int[]{3, 2, 3}));
        Assert.assertEquals(2, majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    // https://leetcode.com/problems/three-consecutive-odds/
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0) {
                return true;
            }
        }
        return false;
    }

    // https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            sb.append("a".repeat(Math.max(0, n - 1)));
            sb.append('z');
        } else {
            sb.append("a".repeat(Math.max(0, n)));
        }
        return sb.toString();
    }

    @Test
    public void preorderTraversalTest() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Assert.assertEquals(expected, preorderTraversal(root));
    }

    @Test
    public void maximum69NumberTest() {
        Assert.assertEquals(9969, maximum69Number(9669));
        Assert.assertEquals(9999, maximum69Number(9999));
        Assert.assertEquals(9999, maximum69Number(9996));
    }

    @Test
    public void createTargetArrayTest() {
        Assert.assertArrayEquals(new int[]{0, 4, 1, 3, 2}, createTargetArray(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 2, 1}));
    }

    @Test
    public void sortedSquaresTest() {
        Assert.assertArrayEquals(new int[]{0, 1, 9, 16, 100}, sortedSquares(new int[]{-4, -1, 0, 3, 10}));
    }

    public static boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int difference = arr[1] - arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] + difference != arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void canMakeArithmeticProgressionTest() {
        Assert.assertTrue(canMakeArithmeticProgression(new int[]{3, 5, 1}));
        Assert.assertFalse(canMakeArithmeticProgression(new int[]{1, 2, 4}));
    }

    public static void insertIntoBSTHelper(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
                return;
            }
            insertIntoBSTHelper(root.right, val);
        }

        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return;
            }
            insertIntoBSTHelper(root.left, val);
        }
    }

    // https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }
        insertIntoBSTHelper(root, val);
        return root;
    }

    // https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/submissions/
    public int subtractProductAndSum(int n) {
        int sum = 0, prod = 1;
        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            prod *= digit;
            n /= 10;
        }
        return prod - sum;
    }

    @Test
    public void subtractProductAndSumTest() {
        Assert.assertEquals(15, subtractProductAndSum(234));
        Assert.assertEquals(21, subtractProductAndSum(4421));

    }

    public boolean judgeCircle(String moves) {
        int vertical = 0;
        int horizontal = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'U') vertical++;
            if (moves.charAt(i) == 'D') vertical--;
            if (moves.charAt(i) == 'R') horizontal++;
            if (moves.charAt(i) == 'L') horizontal--;
        }
        return vertical == 0 && horizontal == 0;
    }

    @Test
    public void judgeCircleTest() {
        Assert.assertTrue(judgeCircle("UD"));
        Assert.assertFalse(judgeCircle("LL"));
    }

    public String toGoatLatin(String S) {
        String[] sArray = S.split(" ");

        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i].toLowerCase().charAt(0) == 'a' || sArray[i].toLowerCase().charAt(0) == 'e' ||
                    sArray[i].toLowerCase().charAt(0) == 'i' || sArray[i].toLowerCase().charAt(0) == 'o' ||
                    sArray[i].toLowerCase().charAt(0) == 'u') {
                sArray[i] = sArray[i].concat("ma");
            } else {

                ArrayList<String> consonants = new ArrayList<>();
                Collections.addAll(consonants, sArray[i].split(""));
                String temp = consonants.get(0);
                consonants.remove(0);
                consonants.add(temp + "ma");
                sArray[i] = String.join("", consonants);
            }
            for (int k = 0; k < i + 1; k++) {
                sArray[i] = sArray[i].concat("a");
            }
        }
        return String.join(" ", sArray);
    }

    @Test
    public void goatLatinTest() {
        Assert.assertEquals("Imaa peaksmaaa oatGmaaaa atinLmaaaaa", toGoatLatin("I speak Goat Latin"));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[nums1.length];

        for (int i = 0; i < nums2.length - 1; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                if (nums2[j] > nums2[i]) {
                    map.put(nums2[i], nums2[j]);
                    break;
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            answer[i] = map.getOrDefault(nums1[i], -1);
        }
        return answer;
    }

    @Test
    public void nextGreaterElementTest() {
        Assert.assertArrayEquals(new int[]{-1, 3, -1}, nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
        Assert.assertArrayEquals(new int[]{3, -1}, nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));

    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0; // base case.
        if (root.val < L) return rangeSumBST(root.right, L, R); // left branch excluded.
        if (root.val > R) return rangeSumBST(root.left, L, R); // right branch excluded.
        return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R); // count in both children.
    }

    // https://leetcode.com/problems/minimum-absolute-difference/
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDif = arr[1] - arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            minDif = Math.min(arr[i + 1] - arr[i], minDif);
        }
        ArrayList<List<Integer>> pairs = new ArrayList<>();
        for (int k = 0; k < arr.length - 1; k++) {
            if (arr[k + 1] - arr[k] == minDif) {
                pairs.add(Arrays.asList(arr[k], arr[k + 1]));
            }
        }
        return pairs;
    }

    @Test
    public void minimumAbsDifferenceTest() {
        ArrayList<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 3));
        Assert.assertEquals(expected, minimumAbsDifference(new int[]{1, 3, 6, 10, 15}));
    }

//    public int sumEvenGrandparent(TreeNode root){
////        return sumEvenGrandparentHelper(root, 0);
//    }

//    public int sumEvenGrandparentHelper(TreeNode root, int sum) {
//
//        if(root == null || root.){
//            return 0;
//        }
//        if(root.val % 2 == 0){
//            if(root.left != null && root.left.left != null) {
//                sum += root.left.left.val;
//            }
//            if(root.right != null && root.right.right != null) {
//                sum += root.right.right.val;
//            }
//
//        }
//        sum = sumEvenGrandparentHelper(root.left, sum);
//        sum = sumEvenGrandparentHelper(root.right, sum);
//        return sum;
//
//    }

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void containsDuplicateTest() {
        Assert.assertTrue(containsDuplicate(new int[]{1, 2, 3, 1}));
        Assert.assertFalse(containsDuplicate(new int[]{1, 2, 3, 4}));
    }

    public boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) || word.equals(word.toLowerCase())
                || word.equals(word.toUpperCase().substring(0, 1).concat(word.toLowerCase().substring(1)));
    }

    @Test
    public void detectCapitalUseTest() {
        Assert.assertTrue(detectCapitalUse("USA"));
        Assert.assertFalse(detectCapitalUse("FlaG"));
        Assert.assertTrue(detectCapitalUse("Leetcode"));
    }

    public int search(int[] nums, int target) {

        if (nums.length == 1) {
            if (target == nums[0]) {
                return 0;
            } else return -1;
        }

        int leftBound = 0;
        int rightBound = nums.length - 1;
        while (true) {
            if (rightBound - leftBound == 1) {
                if (nums[rightBound] == target) {
                    return rightBound;
                } else if (nums[leftBound] == target) {
                    return leftBound;
                }
                return -1;
            }
            if (nums[(leftBound + rightBound) / 2] < target) {
                leftBound = (leftBound + rightBound) / 2;
            } else {
                rightBound = (leftBound + rightBound) / 2;
            }
        }
    }

    @Test
    public void searchTest() {
        int[] input = new int[]{-1, 0, 3, 5, 9, 12};
        Assert.assertEquals(4, search(input, 9));
    }

    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int j = mat.length - 1;
        for (int i = 0; i < mat.length; i++) {
            sum += mat[i][i];
            if (j != i)
                sum += mat[i][j];
            --j;

        }
        return sum;
    }

    public String[] uncommonFromSentences(String A, String B) {
        HashMap<String, Integer> unique = new HashMap<>();

        String[] aArray = A.split(" ");
        String[] bArray = B.split(" ");

        for (String x : aArray) {
            if (unique.containsKey(x)) {
                unique.put(x, unique.get(x) + 1);
            } else {
                unique.put(x, 1);
            }
        }

        for (String x : bArray) {
            if (unique.containsKey(x)) {
                unique.put(x, unique.get(x) + 1);
            } else {
                unique.put(x, 1);
            }
        }


        ArrayList<String> returnArrayList = new ArrayList<>();

        for (String x : unique.keySet()) {
            if (unique.get(x) == 1) {
                returnArrayList.add(x);
            }
        }

        String[] returnArray = new String[returnArrayList.size()];
        returnArrayList.toArray(returnArray);

        return returnArray;

    }

    @Test
    public void uncommonFromSentencesTest() {
        Assert.assertArrayEquals(new String[]{"sweet", "sour"}, uncommonFromSentences("this is sweet", "this is sour"));
        Assert.assertArrayEquals(new String[]{"a", "b"}, uncommonFromSentences("a d c e", "b d c e"));

    }

    public String reformat(String s) {
        ArrayList<Character> digits = new ArrayList<>();
        ArrayList<Character> letters = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                digits.add(s.charAt(i));
            } else letters.add(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        boolean even = digits.size() > letters.size();

        for (int i = 0; i < s.length(); i++) {
            if (even) {
                if (digits.isEmpty()) {
                    return "";
                }
                sb.append(digits.get(0));
                digits.remove(0);
                even = false;
            } else {
                if (letters.isEmpty()) {
                    return "";
                }
                sb.append(letters.get(0));
                letters.remove(0);
                even = true;
            }
        }
        return sb.toString();
    }

    @Test
    public void reformatTest() {
        Assert.assertEquals("0a1b2c", reformat("ab123"));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }


        ListNode currentNode = l1.val < l2.val ? l1 : l2;
        ListNode listHead = currentNode;
        if (l1.val < l2.val) {
            l1 = l1.next;
        } else {
            l2 = l2.next;
        }
        currentNode.next = null;


        while (l1 != null && l2 != null) {
            currentNode.next = l1.val < l2.val ? l1 : l2;
            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
            currentNode = currentNode.next;
        }
        if (l1 == null) {
            currentNode.next = l2;
        } else {
            currentNode.next = l1;
        }
        return listHead;
    }

    public int[] duplicateZeros(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && i != arr.length - 1) {
                int[] copy = Arrays.copyOfRange(arr, i + 1, arr.length);
                arr[i + 1] = 0;
                int index = 0;
                for (int k = i + 2; k < arr.length; k++) {
                    arr[k] = copy[index];
                    index++;
                }
                i++;
            }
        }
        return arr;
    }

    @Test
    public void duplicateZerosTest() {
        Assert.assertArrayEquals(new int[]{1, 0, 0, 2, 3, 0, 0, 4}, duplicateZeros(new int[]{1, 0, 2, 3, 0, 4, 5, 0}));
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0}, duplicateZeros(new int[]{0, 0, 0, 0, 0}));

    }

    public int longestPalindrome(String s) {

        if (s == null) {
            return 0;
        }

        HashMap<Character, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (occurrences.containsKey(s.charAt(i))) {
                occurrences.put(s.charAt(i), occurrences.get(s.charAt(i)) + 1);
            } else {
                occurrences.put(s.charAt(i), 1);
            }
        }
        int one = 0;
        int count = 0;
        for (int x : occurrences.values()) {
            if (x % 2 == 1) {
                one = 1;
                count += x - 1;
            } else count += x;
        }
        return count + one;
    }

    @Test
    public void longestPalindromeTest() {
        Assert.assertEquals(983, longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
        Assert.assertEquals(7, longestPalindrome("abccccdd"));
        Assert.assertEquals(3, longestPalindrome("ccc"));

    }

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, sum = 0, rSum = 0;
        for (int i : nums) sum += i;
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            rSum += nums[i];
            res.add(nums[i]);
            if (rSum > sum - rSum) return res;
        }
        return new ArrayList<>();
    }

    @Test
    public void minSubsequenceHelp() {
        int[] actual = new int[]{4, 4, 7, 6, 7};
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(7);
        expected.add(7);
        expected.add(6);
        Assert.assertEquals(expected, minSubsequence(actual));
    }


    private static class ParkingSystem {

        private final int small;
        private final int medium;
        private final int big;

        private int currentSmall = 0;
        private int currentMedium = 0;
        private int currentBig = 0;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            if (carType == 1 && currentBig < big) {
                currentBig++;
                return true;
            }
            if (carType == 2 && currentMedium < medium) {
                currentMedium++;
                return true;
            }
            if (carType == 3 && currentSmall < small) {
                currentSmall++;
                return true;
            } else return false;
        }

    }

    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;

        HashSet<Character> jewelSigns = new HashSet<>();

        for (int i = 0; i < jewels.length(); i++) {
            jewelSigns.add(jewels.charAt(i));
        }

        for (int k = 0; k < stones.length(); k++) {
            if (jewelSigns.contains(stones.charAt(k))) count++;
        }
        return count;
    }

    @Test
    public void numJewelsInStonesTest() {
        Assert.assertEquals(3, numJewelsInStones("aA", "aAAbbbb"));
        Assert.assertEquals(0, numJewelsInStones("z", "ZZ"));
    }


    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        double sum = 0;
        for (int i = n / 20; i < n - n / 20; i++)
            sum += arr[i];
        return sum / (n - n / 10);
    }

    @Test
    public void trimMeanTest() {
        Assert.assertEquals(2.0, trimMean(new int[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3}), .001);
        Assert.assertEquals(4.0, trimMean(new int[]{6, 2, 7, 5, 1, 2, 0, 3, 10, 2, 5, 0, 5, 5, 0, 8, 7, 6, 8, 0}), .001);

    }

    public char findTheDifference(String s, String t) {
        char[] original = s.toCharArray();
        char[] additional = t.toCharArray();
        Arrays.sort(original);
        Arrays.sort(additional);

        for (int i = 0; i < t.length(); i++) {
            if (i == t.length() - 1) return additional[i];
            if (original[i] != additional[i]) return additional[i];
        }
        return 0;
    }

    @Test
    public void findTheDifferenceTest() {
        Assert.assertEquals('e', findTheDifference("abcd", "abcde"));
        Assert.assertEquals('a', findTheDifference("a", "aa"));

    }


    public int numUniqueEmails(String[] emails) {
        for (int i = 0; i < emails.length; i++) {
            char[] email = emails[i].toCharArray();
            for (int k = 0; k < email.length; k++) {
                if (email[k] == '.') email[k] = '\0';
                if (email[k] == '+') {
                    do {
                        email[k] = '\0';
                        k++;
                    }
                    while (email[k] != '@');
                }
                if (email[k] == '@') break;
            }
            emails[i] = String.copyValueOf(email);
        }
        HashSet<String> unique = new HashSet<>();
        for (String email : emails) {
            email = email.replace("\0", "");
            unique.add(email);
        }
        return unique.size();
    }

    @Test
    public void numUniqueEmailsTest() {
        String[] input = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        Assert.assertEquals(2, numUniqueEmails(input));
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) count++;
        }
        return count;
    }

    @Test
    public void busyStudentTest() {
        Assert.assertEquals(1, busyStudent(new int[]{1, 2, 3}, new int[]{3, 2, 7}, 4));
        Assert.assertEquals(5, busyStudent(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10}, 5));
    }

    public int[] finalPrices(int[] prices) {

        for (int i = 0; i < prices.length; i++) {
            for (int k = i + 1; k < prices.length; k++) {
                if (prices[k] <= prices[i]) {
                    prices[i] = prices[i] - prices[k];
                    break;
                }
            }
        }
        return prices;
    }


    @Test
    public void finalPricesTest() {
        Assert.assertArrayEquals(new int[]{4, 2, 4, 2, 3}, finalPrices(new int[]{8, 4, 6, 2, 3}));
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, finalPrices(new int[]{1, 2, 3, 4, 5}));

    }

    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int k = i + 1; k < numbers.length; k++) {
                if (numbers[i] + numbers[k] == target) return new int[]{(i + 1), (k + 1)};
                if (numbers[i] + numbers[k] > target) break;
            }
        }
        return new int[]{0};
    }

    @Test
    public void twoSum2Test() {
        Assert.assertArrayEquals(new int[]{1, 2}, twoSum2(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 2}, twoSum2(new int[]{-1, 0}, -1));
    }

    public List<String> commonChars(String[] A) {
        ArrayList<String> commonCharacters = new ArrayList<>();

        for (int i = 1; i <= A[0].length(); i++) {
            if (commonCharsHelper(A[0].subSequence(i - 1, i), A)) {
                commonCharacters.add((A[0].subSequence(i - 1, i).toString()));
                for (int k = 1; k < A.length; k++) A[k] = A[k].replaceFirst(A[0].substring(i - 1, i), "");
            }
        }

        return commonCharacters;
    }

    public boolean commonCharsHelper(CharSequence character, String[] A) {
        for (String s : A) {
            if (!s.contains(character)) return false;
        }
        return true;
    }

    @Test
    public void commonCharsTest() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("e", "l", "l"));
        String[] input = new String[]{"bella", "label", "roller"};
        Assert.assertEquals(expected, commonChars(input));

        ArrayList<String> expected2 = new ArrayList<>(Arrays.asList("c", "o"));
        String[] input2 = new String[]{"cool", "lock", "cook"};
        Assert.assertEquals(expected2, commonChars(input2));
    }

    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        for (int i = max; i >= 0; i--) {
            int count = 0;
            for (int k = nums.length - 1; k >= 0; k--) {
                if (nums[k] >= i) count++;
            }
            if (count == i) return i;
        }
        return -1;
    }

    @Test
    public void specialArrayTest() {
        Assert.assertEquals(2, specialArray(new int[]{3, 5}));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> characters = new HashMap<>();
        char[] magazineCharacters = magazine.toCharArray();
        for (char c : magazineCharacters) {
            if (characters.containsKey(c)) characters.put(c, characters.get(c) + 1);
            else characters.put(c, 1);
        }


        for (int i = 0; i < ransomNote.length(); i++) {
            if (characters.containsKey(ransomNote.charAt(i))) {
                if (characters.get(ransomNote.charAt(i)) == 0) return false;
                else characters.put(ransomNote.charAt(i), characters.get(ransomNote.charAt(i)) - 1);
            } else return false;
        }
        return true;
    }

    @Test
    public void canConstructTest() {
        Assert.assertFalse(canConstruct("aa", "ab"));
        Assert.assertTrue(canConstruct("aa", "aa"));
        Assert.assertFalse(canConstruct("bb", "aa"));
    }

    public boolean checkRecord(String s) {
        char[] absentCheck = s.toCharArray();
        char[] absentCheck2 = absentCheck.clone();
        if (absentCheck.length >= 2) {
            Arrays.sort(absentCheck);
            if (absentCheck[0] == 'A' && absentCheck[1] == 'A') return false;
        }

        for (int i = 0; i < absentCheck2.length; i++) {
            if (i < absentCheck2.length - 2) {
                if (absentCheck2[i] == 'L' && absentCheck2[i + 1] == 'L' && absentCheck2[i + 2] == 'L') {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void checkRecordTest() {
        Assert.assertTrue(checkRecord("PPALLP"));
        Assert.assertFalse(checkRecord("PPALLL"));
    }


    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null) return null;
        if (r * c != nums.length * nums[0].length) return nums;

        int[][] newMatrix = new int[r][c];
        int rCount = 0;
        int cCount = 0;
        for (int[] num : nums) {
            for (int i : num) {
                if (cCount == c) {
                    cCount = 0;
                    rCount++;
                }
                newMatrix[rCount][cCount] = i;
                cCount++;
            }
        }
        return newMatrix;
    }

    @Test
    public void matrixReshapeTest() {
        int[][] matrix = new int[1][4];
        matrix[0] = new int[]{1, 2, 3, 4};
        Assert.assertArrayEquals(matrix, matrixReshape(matrix, 1, 4));
    }

    public int uniqueMorseRepresentations(String[] words) {

        HashSet<String> uniques = new HashSet<>();
        String[] morseCode = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        for (String string : words) {
            char[] characterArray = string.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : characterArray) {
                sb.append(morseCode[c - 'a']);
            }
            uniques.add(sb.toString());
        }
        return uniques.size();
    }

    @Test
    public void uniqueMorseRepresentationsTest() {
        Assert.assertEquals(2, uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }

    public int numberOfMatches(int n) {
        int numberOfMatches = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                numberOfMatches += n / 2;
                n = n / 2;
            } else {
                numberOfMatches += (n - 1) / 2;
                n = (n - 1) / 2 + 1;
            }
        }
        return numberOfMatches;
    }

    @Test
    public void numberOfMatchesTest() {
        Assert.assertEquals(6, numberOfMatches(7));
        Assert.assertEquals(13, numberOfMatches(14));
    }


    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int k = i + 1; k < arr.length + 1; k++) {
                int[] temp = Arrays.copyOfRange(arr, i, k);
                if (temp.length % 2 != 0) {
                    sum += Arrays.stream(temp).sum();
                }
            }
        }
        return sum;
    }

    @Test
    public void sumOddLengthSubarraysTest() {
        Assert.assertEquals(58, sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        Assert.assertEquals(3, sumOddLengthSubarrays(new int[]{1, 2}));
        Assert.assertEquals(66, sumOddLengthSubarrays(new int[]{10, 11, 12}));
    }


    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int score = 0;

        for (int i = 1; i - 2 < piles.length; i = i + 3) {
            score = score + piles[i];
        }

        return score;
    }

    @Test
    public void maxCoinsTest() {
        Assert.assertEquals(9, maxCoins(new int[]{2, 4, 1, 2, 7, 8}));
    }


    public int countGoodRectangles(int[][] rectangles) {

        if (rectangles.length == 1) return 1;


        if (rectangles.length == 0) return 0;


        int[] squares = new int[rectangles.length];
        for (int i = 0; i < rectangles.length; i++) {
            squares[i] = Math.min(rectangles[i][0], rectangles[i][1]);
        }

        Arrays.sort(squares);
        int max = squares[squares.length - 1];
        int count = 0;
        int index = squares.length - 1;
        while (max == squares[index]) {
            count++;
            index--;
        }
        return count;
    }

    @Test
    public void countGoodRectanglesTest() {
        int[][] input = new int[4][2];
        input[0] = new int[]{5, 8};
        input[1] = new int[]{3, 9};
        input[2] = new int[]{5, 12};
        input[3] = new int[]{16, 5};

        Assert.assertEquals(3, countGoodRectangles(input));
    }

    public String destCity(List<List<String>> paths) {
        HashSet<String> depature = new HashSet<>();
        for (List<String> destination : paths) {
            depature.add(destination.get(0));
        }
        for (List<String> destination : paths) {
            if (depature.add(destination.get(1))) return destination.get(1);
        }
        return null;
    }

    @Test
    public void destCityVoidTest() {

    }

    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return (Arrays.equals(target, arr));
    }

    @Test
    public void canBeEqualTest() {
        Assert.assertTrue(canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
        Assert.assertFalse(canBeEqual(new int[]{3, 7, 9}, new int[]{3, 7, 11}));
        Assert.assertTrue(canBeEqual(new int[]{1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1}));

    }


    public double average(int[] salary) {
        double sum = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, n = salary.length;
        for (int num : salary) {
            sum += num;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return (sum - min - max) / (n - 2);
    }

    @Test
    public void averageTest() {
        Assert.assertEquals(2500.00000, average(new int[]{4000, 3000, 1000, 2000}), 0.005);
        Assert.assertEquals(3500.00000, average(new int[]{6000, 5000, 4000, 3000, 2000, 1000}), 0.005);
        Assert.assertEquals(4750.00000, average(new int[]{8000, 9000, 2000, 3000, 6000, 1000}), 0.005);
    }

    public int totalMoney(int n) {
        int day = 0, money = 1, total = 0;
        while (day < n) {
            if (day > 1 && day % 7 == 0) money = day / 7 + 1;
            total += money;
            money++;
            day++;
        }
        return total;
    }

    @Test
    public void totalMoneyTest() {
        Assert.assertEquals(10, totalMoney(4));
        Assert.assertEquals(37, totalMoney(10));
        Assert.assertEquals(96, totalMoney(20));
    }

    public int countCharacters(String[] words, String chars) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c : chars.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        HashMap<Character, Integer> copyMap;
        for (String word : words) {
            copyMap = (HashMap<Character, Integer>) countMap.clone();
            boolean fail = false;
            for (char c : word.toCharArray()) {
                if (copyMap.get(c) == null || copyMap.get(c) <= 0) {
                    fail = true;
                    break;
                } else {
                    copyMap.put(c, copyMap.get(c) - 1);
                }
            }
            if (!fail) res += word.length();
        }
        return res;
    }


    @Test
    public void countCharactersTest() {
        Assert.assertEquals(6, countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
        Assert.assertEquals(10, countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr"));
        Assert.assertEquals(0, countCharacters(new String[]{"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin", "ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb", "ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl", "boygirdlggnh", "xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx", "nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop", "hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx", "juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr", "lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo", "oxgaskztzroxuntiwlfyufddl", "tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp", "qnagrpfzlyrouolqquytwnwnsqnmuzphne", "eeilfdaookieawrrbvtnqfzcricvhpiv", "sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz", "yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue", "hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv", "cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo", "teyygdmmyadppuopvqdodaczob", "qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs", "qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"}, "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp"));

    }

    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1, lineCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (lineCount + widths[s.charAt(i) - 'a'] > 100) {
                lines++;
                lineCount = widths[s.charAt(i) - 'a'];
            } else {
                lineCount += widths[s.charAt(i) - 'a'];
            }
        }
        return new int[]{lines, lineCount};
    }

    @Test
    public void numberOfLinesTest() {
        Assert.assertArrayEquals(new int[]{3, 60}, numberOfLines(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz"));
        Assert.assertArrayEquals(new int[]{2, 4}, numberOfLines(new int[]{4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "bbbcccdddaaa"));
    }

    public int calPoints(String[] ops) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String op : ops) {
            try {
                numbers.add(Integer.parseInt(op));
            } catch (NumberFormatException e) {
                if (op.equals("C")) {
                    numbers.remove(numbers.size() - 1);
                }
                if (op.equals("D")) {
                    numbers.add((numbers.get(numbers.size() - 1)) * 2);
                }
                if (op.equals("+")) {
                    numbers.add(numbers.get(numbers.size() - 1) + numbers.get(numbers.size() - 2));
                }
            }
        }
        int score = 0;
        for (Integer x : numbers) score += x;
        return score;
    }

    @Test
    public void calPointsTest() {
        Assert.assertEquals(30, calPoints(new String[]{"5", "2", "C", "D", "+"}));
        Assert.assertEquals(27, calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));

    }


    @Test
    public void maximumWealthTest() {
        int[][] input = new int[2][3];
        input[0] = new int[]{1, 2, 3};
        input[1] = new int[]{3, 2, 1};
        Assert.assertEquals(6, maximumWealth(input));
    }

    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) count++;
            }
        }
        return count;
    }

    @Test
    public void numIdenticalPairsTest() {
        Assert.assertEquals(4, numIdenticalPairs(new int[]{1, 2, 3, 1, 1, 3}));
        Assert.assertEquals(6, numIdenticalPairs(new int[]{1, 1, 1, 1}));
        Assert.assertEquals(0, numIdenticalPairs(new int[]{1, 2, 3}));
    }

    public int minPartitions(String n) {
        return n.chars().max().orElse(0) - '0';
    }

    @Test
    public void minPartitionsTest() {
        Assert.assertEquals(3, minPartitions("32"));
        Assert.assertEquals(8, minPartitions("82734"));
    }

    public String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }

    @Test
    public void interpretTest() {
        Assert.assertEquals("Goal", interpret("G()(al)"));
        Assert.assertEquals("Gooooal", interpret("G()()()()(al)"));
    }

    public int[] decompressRLElist(int[] nums) {
        ArrayList<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                returnList.add(nums[i + 1]);
            }
        }
        int[] returnArray = new int[returnList.size()];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = returnList.get(i);
        }
        return returnArray;
    }

    @Test
    public void decompressRLElistTest() {
        Assert.assertArrayEquals(new int[]{2, 4, 4, 4}, decompressRLElist(new int[]{1, 2, 3, 4}));
    }

    public int countConsistentStrings(String allowed, String[] words) {
        char[] stringArray = allowed.toCharArray();
        int count = 0;
        for (String word : words) {
            char[] characters = word.toCharArray();
            Set<Character> set = new HashSet<>();
            for (char y : stringArray) set.add(y);
            for (char x : characters) set.add(x);
            if (set.size() != stringArray.length) count++;
        }
        return words.length - count;
    }

    @Test
    public void countConsistentStringsTest() {
        Assert.assertEquals(2, countConsistentStrings("ab", new String[]{"ad", "bd", "aaab", "baa", "badab"}));
        Assert.assertEquals(7, countConsistentStrings("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"}));
    }

    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        int count = 1;
        int current = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] == current) count++;
            else {
                current = A[i];
                count = 1;
            }
            if (count == A.length / 2) return A[i];

        }
        return -1;
    }

    @Test
    public void repeatedNTimes() {
        Assert.assertEquals(3, repeatedNTimes(new int[]{1, 2, 3, 3}));
        Assert.assertEquals(2, repeatedNTimes(new int[]{2, 1, 2, 5, 3, 2}));
        Assert.assertEquals(5, repeatedNTimes(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
    }


    public int[] replaceElements(int[] arr) { //Terribly slow, but learnt something.
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) arr[i] = -1;
            else {
                arr[i] = Arrays.stream(Arrays.copyOfRange(arr, i + 1, arr.length)).max().orElse(0);
            }
        }
        return arr;
    }

    @Test
    public void replaceElementsTest() {
        Assert.assertArrayEquals(new int[]{18, 6, 6, 6, 1, -1}, replaceElements(new int[]{17, 18, 5, 4, 6, 1}));
    }

    public int countBalls(int lowLimit, int highLimit) {
        HashMap<Integer, Integer> boxes = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = countBallsSum(i);
            if (boxes.containsKey(sum)) {
                boxes.put(sum, boxes.get(sum) + 1);
            } else boxes.put(sum, 1);
        }
        int largestCount = 0;
        for (int key : boxes.keySet()) {
            if (boxes.get(key) > largestCount) {
                largestCount = boxes.get(key);
            }
        }
        return largestCount;
    }

    public static void main(String[] args) {
    }

    public int countBallsSum(int ballNumber) {
        int sum = 0;
        while (ballNumber > 0) {
            sum = sum + ballNumber % 10;
            ballNumber = ballNumber / 10;
        }
        return sum;
    }

    @Test
    public void countBallsTest() {
        Assert.assertEquals(2, countBalls(1, 10));
    }

    public int sumOfUnique(int[] nums) {
        HashMap<Integer, Integer> uniques = new HashMap<>();
        for (int n : nums) {
            if (!uniques.containsKey(n)) uniques.put(n, 1);
            else uniques.put(n, uniques.get(n) + 1);
        }
        int sum = 0;
        for (int key : uniques.keySet()) {
            if (uniques.get(key) == 1) {
                sum += key;
            }
        }
        return sum;
    }

    @Test
    public void sumOfUniqueTest() {
        Assert.assertEquals(4, sumOfUnique(new int[]{1, 2, 3, 2}));
        Assert.assertEquals(0, sumOfUnique(new int[]{0, 0, 0, 0, 0}));
        Assert.assertEquals(15, sumOfUnique(new int[]{1, 2, 3, 4, 5}));

    }

    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] account : accounts) {
            max = Math.max(Arrays.stream(account).sum(), max);
        }
        return max;
    }

    public static int indexOfSmallest(int[] array) {

        if (array.length == 0)
            return -1;

        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++) {
            if (array[i] <= min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    public List<Integer> luckyNumbers(int[][] matrix) {

        ArrayList<Integer> luckyNumbers = new ArrayList<>();

        for (int[] value : matrix) {
            int index = indexOfSmallest(value);
            boolean test = true;
            for (int[] ints : matrix) {
                if (ints[index] > value[index]) {
                    test = false;
                    break;
                }
            }
            if (test) luckyNumbers.add(value[index]);
        }
        return luckyNumbers;
    }

    @Test
    public void luckyNumbersTest() {
        int[] row1 = new int[]{3, 7, 8};
        int[] row2 = new int[]{9, 11, 13};
        int[] row3 = new int[]{15, 16, 17};
        int[][] matrix = new int[3][];
        matrix[0] = row1;
        matrix[1] = row2;
        matrix[2] = row3;
        ArrayList<Integer> expected = new ArrayList(Arrays.asList(new int[]{15}));

        Assert.assertEquals(expected, luckyNumbers(matrix));
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        String longest = word1.length() > word2.length() ? word1 : word2;
        String shortest = word1.length() < word2.length() ? word1 : word2;
        for (int i = 0; i < shortest.length(); i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        for (int k = shortest.length(); k < longest.length(); k++) sb.append(longest.charAt(k));
        return sb.toString();
    }

    @Test
    public void mergeAlternatelyTest() {
        Assert.assertEquals("apbqcr", mergeAlternately("abc", "pqr"));
        Assert.assertEquals("apbqrs", mergeAlternately("ab", "pqrs"));
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (String word : word1) sb1.append(word);
        for (String word : word2) sb2.append(word);

        return sb1.toString().equals(sb2.toString());
    }

    @Test
    public void arrayStringsAreEqualTest() {
        String[] string1 = new String[]{"ab", "c"};
        String[] string2 = new String[]{"a", "bc"};

        Assert.assertTrue(arrayStringsAreEqual(string1, string2));

        String[] string3 = new String[]{"ab", "c"};
        String[] string4 = new String[]{"ad", "bc"};

        Assert.assertFalse(arrayStringsAreEqual(string3, string4));
    }

    public int largestAltitude(int[] gain) {
        int height = 0;
        int max = 0;
        for (int change : gain) {
            height += change;
            max = height > max ? height : max;
        }
        return max;
    }

    @Test
    public void largestAltitudeTest() {
        Assert.assertEquals(1, largestAltitude(new int[]{-5, 1, 5, 0, -7}));
        Assert.assertEquals(0, largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2}));

    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int difference = 0;
        for (int[] value : grid) {
            int[] xClone = value.clone();
            Arrays.sort(xClone);
            int maxRow = xClone[xClone.length - 1];

            for (int j = 0; j < value.length; j++) {
                int maxColumn = 0;
                for (int[] ints : grid) {
                    maxColumn = Math.max(maxColumn, ints[j]);
                }
                difference += Math.min(maxColumn, maxRow) - value[j];
            }
        }
        return difference;
    }

    @Test
    public void maxIncreaseKeepingSkylineTest() {
        int[][] block = new int[4][];
        int[] row1 = new int[]{3, 0, 8, 4};
        int[] row2 = new int[]{2, 4, 5, 7};
        int[] row3 = new int[]{9, 2, 6, 3};
        int[] row4 = new int[]{0, 3, 1, 0};

        block[0] = row1;
        block[1] = row2;
        block[2] = row3;
        block[3] = row4;

        Assert.assertEquals(35, maxIncreaseKeepingSkyline(block));

    }

    public ListNode reverseList(ListNode head) {

        if (head == null) return head;

        ArrayList<ListNode> nodes = new ArrayList<>();

        while (head != null) {
            nodes.add(head);
            head = head.next;
        }

        head = nodes.get(nodes.size() - 1);
        ListNode temp = head;
        for (int i = nodes.size() - 2; i >= 0; i--) {
            temp.next = nodes.get(i);
            temp = temp.next;
            temp.next = null;
        }

        return head;
    }

    @Test
    public void reverseListTest() {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        head.next = two;
        head.next.next = three;

        ListNode headExpected = new ListNode(3);
        ListNode expectedTwo = new ListNode(2);
        ListNode expectedOne = new ListNode(1);
        headExpected.next = expectedTwo;
        headExpected.next.next = expectedOne;
        //TODO: Fix this
        Assert.assertEquals(head, reverseList(head));
    }

    public List<String> stringMatching(String[] words) {
        HashSet<String> matchedStrings = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int k = 0; k < words.length; k++) {
                if (i == k) continue;
                if (words[k].contains(words[i])) matchedStrings.add(words[i]);
            }
        }
        return new ArrayList<>(matchedStrings);
    }

    @Test
    public void stringMatchingTest() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("as", "hero"));
        Assert.assertEquals(expected, stringMatching(new String[]{"mass", "as", "hero", "superhero"}));

        ArrayList<String> expected2 = new ArrayList<>(Arrays.asList("et", "code"));
        List<String> actual = stringMatching(new String[]{"leetcode", "et", "code"});
        Collections.sort(expected2);
        Collections.sort(actual);
        Assert.assertEquals(expected2, actual);
    }

    public int lastStoneWeight(int[] stones) {
        ArrayList<Integer> stonesList = new ArrayList<>();
        for (int stone : stones) stonesList.add(stone);
        while (stonesList.size() > 1) {
            Collections.sort(stonesList);
            int y = stonesList.get(stonesList.size() - 2);
            int x = stonesList.get(stonesList.size() - 1);
            stonesList.remove(stonesList.size() - 2);
            stonesList.remove(stonesList.size() - 1);
            stonesList.add(x - y);
        }
        return stonesList.get(0);
    }

    @Test
    public void lastStoneWeightTest() {
        Assert.assertEquals(1, lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }

    public boolean divisorGame(int N) {
        int playerGo = 1;

        for (int i = 1; i < N; i++) {
            if (N % 2 == 0) {
                N = N - i;
                i = 1;
                playerGo++;
            }
        }

        return playerGo % 2 == 0;
    }

    @Test
    public void divisorGameTest() {
        Assert.assertTrue(divisorGame(2));
        Assert.assertFalse(divisorGame(3));
    }

    public boolean hasAlternatingBits(int n) {
        char[] charArray = Integer.toBinaryString(n).toCharArray();
        char current = charArray[0];
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == current) {
                return false;
            }
            current = charArray[i];
        }
        return true;
    }

    @Test
    public void hasAlternatingBitsTest() {
        Assert.assertTrue(hasAlternatingBits(5));
        Assert.assertTrue(hasAlternatingBits(10));
        Assert.assertFalse(hasAlternatingBits(7));
        Assert.assertFalse(hasAlternatingBits(11));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Arrays.sort(sCharArray);
        Arrays.sort(tCharArray);

        for (int i = 0; i < sCharArray.length; i++) {
            if (sCharArray[i] != tCharArray[i]) return false;
        }
        return true;
    }

    @Test
    public void isAnagramTest() {
        Assert.assertTrue(isAnagram("anagram", "nagaram"));
        Assert.assertFalse(isAnagram("rat", "car"));
    }

    public int findDuplicate(int[] nums) {
        HashSet<Integer> singles = new HashSet<>();
        for (int num : nums) {
            if (!singles.add(num)) {
                return num;
            }
        }
        return -1;
    }

    @Test
    public void findDuplicateTest() {
        Assert.assertEquals(2, findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

    public int countPrimes(int n) {
        if (n <= 1) return 0;
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primeTester(i)) count++;
        }
        return count;
    }

    public boolean primeTester(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Test
    public void countPrimesTest() {
        Assert.assertEquals(4, countPrimes(10));
        Assert.assertEquals(0, countPrimes(0));
        Assert.assertEquals(0, countPrimes(1));
        Assert.assertEquals(41537, countPrimes(499979));
    }

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    @Test
    public void sortArrayTest() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortArray(new int[]{5, 4, 3, 2, 1}));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> repeatedSequences = new HashSet<>();
        HashSet<String> sequences = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            if (sequences.add(s.substring(i, i + 10))) sequences.add(s.substring(i, i + 10));
            else repeatedSequences.add(s.substring(i, i + 10));
        }
        return new ArrayList<>(repeatedSequences);
    }

    @Test
    public void findRepeatedDnaSequencesTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"));
        Assert.assertEquals(expected, findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    // https://leetcode.com/problems/single-number-iii/submissions/
    public int[] singleNumber3(int[] nums) {
        if (nums.length == 0)
            return new int[]{0, 0};
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else
                set.add(n);
        }
        int[] arr = new int[2];
        int i = 0;
        for (int n : set) {
            arr[i] = n;
            i++;
        }
        return arr;
    }

    @Test
    public void singleNumber3Test() {
        Assert.assertArrayEquals(new int[]{3, 5}, singleNumber3(new int[]{1, 2, 1, 3, 2, 5}));
    }

    public int dayOfYear(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.getDayOfYear();
    }

    @Test
    public void dayOfYearTest() {
        Assert.assertEquals(9, dayOfYear("2019-01-09"));
    }

    public boolean isHappy(int n) {
        if (n == 1) return true;
        if (n == 7) return true;
        if (n < 10) return false;
        int count = 0;
        while (n / 10 != 0) {
            count += (n % 10) * (n % 10);
            n = (n / 10);
        }
        count += n * n;
        return isHappy(count);
    }

    @Test
    public void isHappyTest() {
        Assert.assertTrue(isHappy(19));
        Assert.assertTrue(isHappy(7));
        Assert.assertFalse(isHappy(2));
    }

    public boolean isUgly(int n) {
        if (n == 0) return false;
        while (n != 1) {
            if (n % 2 == 0)
                n = n / 2;
            else if (n % 3 == 0)
                n = n / 3;
            else if (n % 5 == 0)
                n = n / 5;
            else
                return false;

        }
        return true;
    }

    @Test
    public void isUglyTest() {
        Assert.assertTrue(isUgly(6));
        Assert.assertTrue(isUgly(8));
        Assert.assertFalse(isUgly(14));
        Assert.assertFalse(isUgly(-2147483648));
    }


    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3], nums[nums.length - 1] * nums[0] * nums[1]);
    }

    @Test
    public void maximumProductTest() {
        Assert.assertEquals(6, maximumProduct(new int[]{1, 2, 3}));
        Assert.assertEquals(24, maximumProduct(new int[]{1, 2, 3, 4}));
        Assert.assertEquals(39200, maximumProduct(new int[]{-100, -98, -1, 2, 3, 4}));
    }

    public int findKthPositive(int[] arr, int k) {
        int nextNumber = arr[0];
        int index = 0;
        int missingCount = 0;
        int i = 1;
        while (true) {
            if (i != nextNumber) missingCount++;
            if (i == nextNumber && index + 1 < arr.length) nextNumber = arr[++index];
            if (missingCount == k) return i;
            i++;
        }
    }

    @Test
    public void findKthPositiveTest() {
        Assert.assertEquals(9, findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
        Assert.assertEquals(6, findKthPositive(new int[]{1, 2, 3, 4}, 2));
    }

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();
        boolean swapped = false;
        char tempChar = '0';
        int index = 0;
        for (int i = 0; i < s1CharArray.length; i++) {
            if (s1CharArray[i] != s2CharArray[i] && swapped) {
                s2CharArray[index] = s2CharArray[i];
                s2CharArray[i] = tempChar;
                break;
            }
            if (s1CharArray[i] != s2CharArray[i] && !swapped) {
                swapped = true;
                tempChar = s2CharArray[i];
                index = i;
            }
        }
        return String.valueOf(s2CharArray).equals(String.valueOf(s1CharArray));
    }

    @Test
    public void areAlmostEqualTest() {
        Assert.assertTrue(areAlmostEqual("bank", "kanb"));
        Assert.assertFalse(areAlmostEqual("attack", "defend"));
    }

    public boolean checkOnesSegment(String s) {
        boolean ones = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' && ones) ones = false;
            if (s.charAt(i) == '1' && !ones) return false;
        }
        return true;
    }

    @Test
    public void checkOnesSegmentTest() {
        Assert.assertTrue(checkOnesSegment("11110000"));
        Assert.assertFalse(checkOnesSegment("111100001111"));
    }

    public int bitwiseComplement(int n) {
        char[] nAsArray = Integer.toBinaryString(n).toCharArray();
        for (int i = 0; i < nAsArray.length; i++) {
            if (nAsArray[i] == '0') nAsArray[i] = '1';
            else nAsArray[i] = '0';
        }
        return Integer.parseInt(String.valueOf(nAsArray), 2);
    }

    @Test
    public void bitwiseComplementTest() {
        Assert.assertEquals(2, bitwiseComplement(5));
        Assert.assertEquals(0, bitwiseComplement(7));
    }

    public String truncateSentence(String s, int k) {
        String[] stringArray = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            if (i == k - 1) sb.append(stringArray[i]);
            else sb.append(stringArray[i]).append(" ");
        }
        return sb.toString();
    }

    @Test
    public void truncateSentence() {
        Assert.assertEquals("Hello how are you", truncateSentence("Hello how are you Contestant", 4));
        Assert.assertEquals("What is the solution", truncateSentence("What is the solution to this problem", 4));

    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int row = mat.length, col = mat[0].length, index = 0;
        Set<Integer> seen = new HashSet<>();
        int[] res = new int[k];
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (mat[i][j] == 0 && !seen.contains(i)) {
                    res[index++] = i;
                    seen.add(i);
                    k--;
                    if (k == 0) {
                        return res;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            if (!seen.contains(i)) {
                res[index++] = i;
                seen.add(i);
                k--;
                if (k == 0) {
                    return res;
                }
            }
        }
        return res;
    }

    public boolean squareIsWhite(String coordinates) {
        if (Character.codePointAt(coordinates, 0) % 2 != 0 && Character.getNumericValue(coordinates.charAt(1)) % 2 != 0)
            return false;
        return Character.codePointAt(coordinates, 0) % 2 != 0 || Character.getNumericValue(coordinates.charAt(1)) % 2 != 0;
    }

    @Test
    public void squareIsWhiteTest() {
        Assert.assertFalse(squareIsWhite("a1"));
        Assert.assertTrue(squareIsWhite("h3"));
        Assert.assertFalse(squareIsWhite("h8"));
        Assert.assertFalse(squareIsWhite("c7"));
    }

    public boolean checkIfPangram(String sentence) {
        HashSet<Character> stringSet = new HashSet<>();
        char[] sentenceArray = sentence.toCharArray();
        for (int i = 0; i < sentence.length(); i++) {
            stringSet.add(sentenceArray[i]);
            if (stringSet.size() == 26) return true;
        }
        return false;
    }

    @Test
    public void checkIfPangramTest() {
        Assert.assertTrue(checkIfPangram("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertFalse(checkIfPangram("abcde"));
    }

    public String reverseOnlyLetters(String S) {
        char[] sArr = S.toCharArray();
        int left = 0, right = S.length() - 1;
        while (left < right) {
            if (Character.isLetter(sArr[left]) && Character.isLetter(sArr[right])) {
                char temp = sArr[left];
                sArr[left] = sArr[right];
                sArr[right] = temp;
                left++;
                right--;
            } else {
                if (!Character.isLetter(sArr[left])) left++;
                if (!Character.isLetter(sArr[right])) right--;
            }
        }
        return new String(sArr);
    }

    @Test
    public void reverseOnlyLetters() {
        Assert.assertEquals("dc-ba", reverseOnlyLetters("ab-cd"));
        Assert.assertEquals("j-Ih-gfE-dCba", reverseOnlyLetters("a-bC-dEf-ghIj"));
        Assert.assertEquals("Qedo1ct-eeLg=ntse-T!", reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        char[] charArray = s.toCharArray();
        int longestGap = -1;
        for (int i = 0; i < charArray.length; i++) {
            for (int k = charArray.length - 1; k > i; k--) {
                if (charArray[i] == charArray[k]) {
                    if (k - i > longestGap) {
                        longestGap = k - i - 1;
                        break;
                    }
                }
            }
        }
        return longestGap;
    }

    @Test
    public void maxLengthBetweenEqualCharactersTest() {
        Assert.assertEquals(0, maxLengthBetweenEqualCharacters("aa"));
        Assert.assertEquals(2, maxLengthBetweenEqualCharacters("abca"));
        Assert.assertEquals(0, maxLengthBetweenEqualCharacters("abcdef"));
        Assert.assertEquals(4, maxLengthBetweenEqualCharacters("cabbac"));
    }

    public int addDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        if (String.valueOf(sum).length() == 1) return sum;
        return addDigits(sum);
    }

    @Test
    public void addDigitsTest() {
        Assert.assertEquals(2, addDigits(38));
        Assert.assertEquals(2, addDigits(11));

    }


//    public int hammingDistance(int x, int y) {
//    }
//
//    @Test
//    public void hammingDistanceTest(){
//        Assert.assertEquals(2, hammingDistance(1, 4));
//    }

    public int findLucky(int[] arr) {
        if (arr.length == 1 && arr[0] != 1) return -1;
        Arrays.sort(arr);
        int count = 0;
        int num = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (num == arr[i]) count++;
            if (i != 0) {
                if (count == num && arr[i - 1] != count) return num;
            } else {
                if (count == num) return num;
            }
            if (num != arr[i]) {
                num = arr[i];
                count = 1;
                if (num == 1 && i == 0) return 1;
            }
        }
        return -1;
    }

    @Test
    public void findLuckyTest() {
        Assert.assertEquals(-1, findLucky(new int[]{13, 16, 7, 3, 14, 4, 12, 19, 6, 6, 7, 16, 17, 17}));
        Assert.assertEquals(-1, findLucky(new int[]{5}));
        Assert.assertEquals(2, findLucky(new int[]{1, 2, 2, 3, 4, 5}));
        Assert.assertEquals(4, findLucky(new int[]{1, 2, 2, 3, 4, 4, 4, 4, 5}));
        Assert.assertEquals(2, findLucky(new int[]{2, 2, 3, 3, 4, 5}));
    }

    public int distributeCandies(int[] candyType) {
        HashSet<Integer> uniques = new HashSet<>();
        for (int candy : candyType) {
            uniques.add(candy);
            if (uniques.size() >= candyType.length / 2) return candyType.length / 2;
        }
        return uniques.size();
    }

    @Test
    public void distributeCandiesTest() {
        Assert.assertEquals(3, distributeCandies(new int[]{1, 1, 2, 2, 3, 3}));
        Assert.assertEquals(2, distributeCandies(new int[]{1, 1, 2, 3}));
        Assert.assertEquals(1, distributeCandies(new int[]{1, 1, 1, 1}));
    }

    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int k = i; k < nums.length; k++) {
                    if (nums[k] != 0) {
                        nums[i] = nums[k];
                        nums[k] = 0;
                        break;
                    }
                }
            }
        }
    }

    // Two pointer solution to the above problem.
    public void moveZeroesTwoPointer(int[] nums) {
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[slow] == 0 && nums[i] != 0) {
                nums[slow] = nums[i];
                nums[i] = 0;
            }
            if (nums[slow] != 0) {
                slow++;
            }
        }
    }

    // Test not appropriate

    public int countOdds(int low, int high) {
        if (low % 2 == 0 && high % 2 == 0) return (high - low) / 2;
        return ((high - low) / 2) + 1;
    }

    @Test
    public void countOddsTest() {
        Assert.assertEquals(3, countOdds(3, 7));
        Assert.assertEquals(1, countOdds(8, 10));
    }

    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) sum += i;
        }
        return num == sum;
    }

    @Test
    public void checkPerfectNumberTest() {
        Assert.assertTrue(checkPerfectNumber(28));
        Assert.assertTrue(checkPerfectNumber(496));
        Assert.assertTrue(checkPerfectNumber(8128));
        Assert.assertFalse(checkPerfectNumber(2));
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length == 0) return Arrays.copyOfRange(list2, 0, 1);
        if (list2.length == 0) return Arrays.copyOfRange(list1, 0, 1);

        HashSet<String> likes = new HashSet<>();
        ArrayList<String> commonLikes = new ArrayList<>();
        Collections.addAll(likes, list1);
        for (String s : list2) {
            if (likes.contains(s)) commonLikes.add(s);
        }


        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String s : commonLikes) {
            int x = 0, y = 0;
            for (int i = 0; i < list1.length; i++) {
                if (list1[i].equals(s)) x = i;
            }
            for (int i = 0; i < list2.length; i++) {
                if (list2[i].equals(s)) y = i;
            }
            hashMap.put(s, x + y);
        }
        int min = Collections.min(hashMap.values());
        ArrayList<String> returnList = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            if (hashMap.get(s) == min) returnList.add(s);
        }
        return returnList.toArray(new String[0]);
    }

    @Test
    public void findRestaurantTest() {
        Assert.assertArrayEquals(new String[]{"Shogun"},
                findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                        new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}));

        Assert.assertArrayEquals(new String[]{"Shogun"},
                findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                        new String[]{"KFC", "Shogun", "Burger King"}));
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    @Test
    public void plusOneTest() {
        Assert.assertArrayEquals(new int[]{1, 2, 4}, plusOne(new int[]{1, 2, 3}));
        Assert.assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
                plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
    }

    public boolean halvesAreAlike(String s) {
        s = s.toLowerCase();
        char[] vowelsArr = new char[]{'a', 'e', 'o', 'i', 'u'};
        HashSet<Character> vowels = new HashSet<>();
        for (char c : vowelsArr) vowels.add(c);

        char[] stringArr = s.toCharArray();
        int left = 0, right = 0;

        for (int i = 0; i < stringArr.length; i++) {
            if (vowels.contains(stringArr[i]) && i < stringArr.length / 2) left++;
            else if (vowels.contains(stringArr[i])) right++;
        }
        return left == right;
    }

    @Test
    public void halvesAreAlikeTest() {
        Assert.assertTrue(halvesAreAlike("heel"));
        Assert.assertTrue(halvesAreAlike("book"));
        Assert.assertFalse(halvesAreAlike("MerryChristmas"));
        Assert.assertTrue(halvesAreAlike("AbCdEfGh"));
    }

    public int sumBase(int n, int k) {
        int base = Integer.parseInt(Integer.toString(Integer.parseInt(String.valueOf(n), 10), k));
        int sum = 0;
        while (base != 0) {
            sum += base % 10;
            base /= 10;
        }
        return sum;
    }

    @Test
    public void sumBaseTest() {
        Assert.assertEquals(9, sumBase(34, 6));
        Assert.assertEquals(1, sumBase(1, 10));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String output = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(output) != 0) {
                output = output.substring(0, output.length() - 1);
            }
        }
        return output;
    }

    @Test
    public void longestCommonPrefixTest() {
        Assert.assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        Assert.assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenh = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenh;
        }
        return head;
    }

    @Test
    public void oddEvenListTest() {
        ListNode head = new ListNode(1);
        ListNode headtemp = head;
        headtemp.next = new ListNode(2);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(3);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(4);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(5);

        ListNode headTest = new ListNode(1);
        headtemp.next = new ListNode(3);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(5);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(2);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(4);

        Assert.assertEquals(headTest, oddEvenList(head));
    }

    public ListNode sortList(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        Collections.sort(values);
        ListNode headOfSorted = null;
        ListNode tail = null;
        for (Integer val : values) {
            if (headOfSorted == null) {
                headOfSorted = new ListNode(val);
                tail = headOfSorted;
            } else {
                tail.next = new ListNode(val);
                tail = tail.next;
            }
        }
        return headOfSorted;
    }

    @Test
    public void sortListTest() {
        ListNode head = new ListNode(1);
        ListNode headtemp = head;
        headtemp.next = new ListNode(3);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(5);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(4);
        headtemp = headtemp.next;
        headtemp.next = new ListNode(2);

        ListNode headTest = new ListNode(1);

        Assert.assertEquals(headTest, sortList(head));
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    @Test
    public void arrayPairSumTest() {
        Assert.assertEquals(4, arrayPairSum(new int[]{1, 4, 3, 2}));
        Assert.assertEquals(9, arrayPairSum(new int[]{6, 2, 6, 5, 1, 2}));
    }

    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    @Test
    public void hammingDistanceTest() {
        Assert.assertEquals(2, hammingDistance(1, 4));
    }

    public int arraySign(int[] nums) {
        int x = 1;
        for (int num : nums) {
            if (num < 0)
                x *= -1;
            else if (num == 0)
                return 0;
        }
        return x;
    }

    @Test
    public void arraySignTest() {
        Assert.assertEquals(-1, arraySign(new int[]{-1, 1, 2}));
        Assert.assertEquals(1, arraySign(new int[]{-1, -2, -3, -4, 3, 2, 1}));
        Assert.assertEquals(-1, arraySign(new int[]{41, 65, 14, 80, 20, 10, 55, 58, 24, 56, 28, 86, 96, 10, 3, 84, 4, 41, 13, 32, 42, 43, 83, 78, 82, 70, 15, -41}));
    }


    // https://leetcode.com/problems/distribute-candies-to-people/
    public int[] distributeCandies2(int candies, int num_people) {
        int[] candyArray = new int[num_people];
        int index = 0;
        int count = 1;
        while (candies > 0) {
            if (index == num_people) index = 0;
            if (candies - count < 0) {
                candyArray[index] += candies;
                return candyArray;
            }
            candyArray[index] += count;
            candies -= count;
            count += 1;
            index++;
        }
        return candyArray;
    }

    @Test
    public void distributeCandies2Test() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 1}, distributeCandies2(7, 4));
        Assert.assertArrayEquals(new int[]{5, 2, 3}, distributeCandies2(10, 3));
    }

    // https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/submissions/
    public boolean kLengthApart(int[] nums, int k) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                start = i;
                break;
            }
        }
        int count = 0;
        for (int j = start + 1; j < nums.length; j++) {
            if (nums[j] == 1 && count < k) return false;
            if (nums[j] == 1) count = 0;
            else count++;
        }
        return true;
    }

    @Test
    public void kLengthApartTest() {
        Assert.assertTrue(kLengthApart(new int[]{1, 0, 0, 0, 1, 0, 0, 1}, 2));
        Assert.assertFalse(kLengthApart(new int[]{1, 0, 0, 1, 0, 1}, 2));
        Assert.assertTrue(kLengthApart(new int[]{0, 1, 0, 1}, 1));
        Assert.assertFalse(kLengthApart(new int[]{1, 0, 1}, 2));
    }

    public int maxAscendingSum(int[] nums) {
        int count = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) count += nums[i];
            else count = nums[i];
            if (count > max) max = count;
        }
        return max;
    }

    @Test
    public void maxAscendingSumTest() {
        Assert.assertEquals(65, maxAscendingSum(new int[]{10, 20, 30, 5, 10, 50}));
        Assert.assertEquals(150, maxAscendingSum(new int[]{10, 20, 30, 40, 50}));
        Assert.assertEquals(33, maxAscendingSum(new int[]{12, 17, 15, 13, 10, 11, 12}));
    }

    public boolean checkZeroOnes(String s) {
        char[] stringCharArray = s.toCharArray();
        if (stringCharArray.length == 1) {
            return stringCharArray[0] == '1';
        }
        int ones = 0, zeroes = 0, currentCount = 1;
        for (int i = 1; i < stringCharArray.length; i++) {
            if (stringCharArray[i] != stringCharArray[i - 1] && stringCharArray[i - 1] == '1') {
                ones = Math.max(currentCount, ones);
                currentCount = 0;
            }
            if (stringCharArray[i] != stringCharArray[i - 1] && stringCharArray[i - 1] == '0') {
                zeroes = Math.max(currentCount, zeroes);
                currentCount = 0;
            }
            currentCount++;
            if (i == stringCharArray.length - 1) {
                if (stringCharArray[i] == '1' && currentCount > ones) ones = currentCount;
                else if (currentCount > zeroes) zeroes = currentCount;
            }
        }
        return ones > zeroes;
    }

    @Test
    public void checkZeroOnesTest() {
        Assert.assertTrue(checkZeroOnes("1"));
        Assert.assertTrue(checkZeroOnes("1101"));
        Assert.assertFalse(checkZeroOnes("111000"));
        Assert.assertFalse(checkZeroOnes("110100010"));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] days = new int[temperatures.length];
        for (int i = 0; i < days.length; i++) {
            int daysCount = 1;
            for (int k = i + 1; k < temperatures.length; k++) {
                if (temperatures[i] < temperatures[k]) {
                    days[i] = daysCount;
                    break;
                }
                daysCount++;
            }
        }
        return days;
    }

    @Test
    public void dailyTemperaturesVoid() {
        Assert.assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    //TODO: Finish this.
    public String sortString(String s) {
        return s;
    }

    @Test
    public void sortStringTest() {
        Assert.assertEquals("abccbaabccba", sortString("aaaabbbbcccc"));
        Assert.assertEquals("art", sortString("rat"));
    }

    public String sortSentence(String s) {
        String[] unsortedStringArray = s.split(" ");
        String[] sortedStringArray = new String[unsortedStringArray.length];
        for (String unsortedStr : unsortedStringArray) {
            sortedStringArray[Character.getNumericValue(unsortedStr.charAt(unsortedStr.length() - 1)) - 1]
                    = unsortedStr.substring(0, unsortedStr.length() - 1);
        }
        return String.join(" ", sortedStringArray);
    }

    @Test
    public void sortSentenceTest() {
        Assert.assertEquals("This is a sentence", sortSentence("is2 sentence4 This1 a3"));
    }

    public String replaceDigits(String s) {
        char[] sCharArray = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            if (i % 2 != 0) {
                sCharArray[i] = (char) ((sCharArray[i - 1]) + (Character.getNumericValue(sCharArray[i])));
            }
        }
        return new String(sCharArray);
    }

    @Test
    public void replaceDigitsTest() {
        Assert.assertEquals("abcdef", replaceDigits("a1c1e1"));
        Assert.assertEquals("abbdcfdhe", replaceDigits("a1b2c3d4e"));
    }

    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int pos = -n;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            if (S.charAt(i) == C) pos = i;
            res[i] = i - pos;
        }
        for (int i = pos - 1; i >= 0; --i) {
            if (S.charAt(i) == C) pos = i;
            res[i] = Math.min(res[i], pos - i);
        }
        return res;
    }

    @Test
    public void shortestToCharTest() {
        Assert.assertArrayEquals(new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0}, shortestToChar("loveleetcode", 'e'));
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        if (ruleKey.equals("color")) {
            for (List<String> item : items) {
                if (item.get(1).equals(ruleValue)) count++;
            }
        }
        if (ruleKey.equals("type")) {
            for (List<String> item : items) {
                if (item.get(0).equals(ruleValue)) count++;
            }
        }
        if (ruleKey.equals("name")) {
            for (List<String> item : items) {
                if (item.get(2).equals(ruleValue)) count++;
            }
        }
        return count;
    }

    @Test
    public void countMatchesTest() {
        ArrayList<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("computer", "silver", "lenovo"));
        Assert.assertEquals(1, countMatches(input, "color", "silver"));
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        // Necessary to pass the stupid leetcode tests
        paragraph = paragraph.replace(',', ' ');

        HashSet<String> bannedWords = new HashSet<>(Arrays.asList(banned));

        String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String s : words) {
            if (wordCount.containsKey(s)) {
                wordCount.put(s, wordCount.get(s) + 1);
            } else {
                wordCount.put(s, 1);
            }
        }
        String largest = null;
        int count = 0;
        for (String key : wordCount.keySet()) {
            if (wordCount.get(key) > count && !bannedWords.contains(key)) {
                count = wordCount.get(key);
                largest = key;
            }
        }
        return largest;
    }

    @Test
    public void mostCommonWordTest() {
        Assert.assertEquals("ball", mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
        Assert.assertEquals("b", mostCommonWord("a, a, a, a, b, b, b, c, c", new String[]{"a"}));

    }

    public int removePalindromeSub(String s) {
        if (s.length() == 0) return 0;
        if (removePalindromeSubHelper(s)) {
            return 1;
        }
        return 2;
    }

    public boolean removePalindromeSubHelper(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString().equals(str);
    }

    @Test
    public void removePalindromeSubTest() {
        Assert.assertEquals(1, removePalindromeSub("ababa"));
        Assert.assertEquals(2, removePalindromeSub("abb"));
        Assert.assertEquals(2, removePalindromeSub("baabb"));
        Assert.assertEquals(2, removePalindromeSub("bbaabaaa"));
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        int[] returnArray = new int[arr1.length];
        int index = 0;
        for (int num : arr2) {
            for (int j : arr1) {
                if (j > num) break;
                if (j == num) returnArray[index++] = num;
            }
        }
        HashSet<Integer> uniques = new HashSet<>();
        for (int k : returnArray) {
            uniques.add(k);
        }
        for (int i : arr1) {
            if (!uniques.contains(i)) {
                returnArray[index++] = i;
            }
        }
        return returnArray;
    }

    @Test
    public void relativeSortArrayTest() {
        Assert.assertArrayEquals(new int[]{2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19},
                relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6}));
    }

    public int[] diStringMatch(String S) {
        int[] res = new int[S.length() + 1];
        int l = 0, r = S.length();
        for (int i = 0; i < S.length(); i++) {
            res[i] = S.charAt(i) == 'I' ? l++ : r--;
        }
        res[S.length()] = l;
        return res;
    }

    @Test
    public void diStringMatchTest() {
        Assert.assertArrayEquals(new int[]{0, 4, 1, 3, 2}, diStringMatch("IDID"));
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3}, diStringMatch("III"));
        Assert.assertArrayEquals(new int[]{3, 2, 0, 1}, diStringMatch("DDI"));
    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return isSumEqualHelper(firstWord) + isSumEqualHelper(secondWord) == isSumEqualHelper(targetWord);
    }

    public int isSumEqualHelper(String word) {
        int wordSum = 0;
        for (int i = 0; i < word.length(); i++) {
            wordSum = wordSum * 10 + (word.charAt(i) - 97);
        }
        return wordSum;
    }

    @Test
    public void isSumEqualTest() {
        Assert.assertTrue(isSumEqual("acb", "cba", "cdb"));
    }


    public int xorOperation(int n, int start) {
        int count = 0, sum = 0, current;
        while (count < n) {
            current = start + 2 * count;
            sum = sum ^ current;
            count++;
        }
        return sum;
    }

    @Test
    public void xorOperationTest() {
        Assert.assertEquals(8, xorOperation(5, 0));
        Assert.assertEquals(8, xorOperation(4, 3));
    }

    public int[] intersection(int[] nums1, int[] nums2) {

        int[] shortest = nums1.length < nums2.length ? nums1 : nums2;
        int[] longest = shortest == nums1 ? nums2 : nums1;
        Arrays.sort(longest);
        // Sort the longest array as this will be the one being searched

        HashSet<Integer> returnList = new HashSet<>();
        for (int j : shortest) {
            if (Arrays.binarySearch(longest, j) > -1) returnList.add(j);
        }
        int[] res = new int[returnList.size()];
        int index = 0;
        for (Integer i : returnList) {
            res[index++] = i;
        }
        return res;
    }

    @Test
    public void intersectionTest() {
        Assert.assertArrayEquals(new int[]{2}, intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
        Assert.assertArrayEquals(new int[]{4, 9}, intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}));
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int res = 0, n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) continue;
                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[k] - arr[i]) <= c) res++;
                }
            }
        }
        return res;
    }

    @Test
    public void countGoodTripletsTest() {
        Assert.assertEquals(4, countGoodTriplets(new int[]{3, 0, 1, 1, 9, 7}, 7, 2, 3));
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0) return 0;
        int begin = timeSeries[0], total = 0;
        for (int t : timeSeries) {
            total = total + (t < begin + duration ? t - begin : duration);
            begin = t;
        }
        return total + duration;
    }

    @Test
    public void findPoisonedDurationTest() {
        Assert.assertEquals(4, findPoisonedDuration(new int[]{1, 4}, 2));
    }

    public int countLargestGroup(int n) {
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int sum = 0, k = i;
            while (k != 0) {
                sum += k % 10;
                k = k / 10;
            }
            arr[sum] = arr[sum] + 1;
        }
        Arrays.sort(arr);
        int res = 0, max = arr[arr.length - 1];
        for (int i : arr) {
            if (i == max) res++;
        }
        return res;
    }

    @Test
    public void countLargestGroupTest() {
        Assert.assertEquals(4, countLargestGroup(13));
    }

    public int binaryGap(int n) {
        String binary = Integer.toBinaryString(n);
        int largest = 0, currentGap = 0;
        boolean counted = false;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1' && !counted) counted = true;
            if (binary.charAt(i) == '1' && counted) {
                largest = Math.max(currentGap, largest);
                counted = false;
                currentGap = 0;
            }
            currentGap++;
        }

        return largest;
    }

    @Test
    public void binaryGapTest() {
        Assert.assertEquals(2, binaryGap(22));
        Assert.assertEquals(1, binaryGap(6));
        Assert.assertEquals(0, binaryGap(8));
        Assert.assertEquals(0, binaryGap(1));
    }

    // Not the best solution, but it works.
    public boolean isPalindromeNumber(int x) {
        if (x < 0) return false;
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        return sb.toString().equals(sb.reverse().toString());
    }

    @Test
    public void isPalindromeNumberTest() {
        Assert.assertTrue(isPalindromeNumber(121));
        Assert.assertFalse(isPalindromeNumber(123));
    }

    public String intToRoman(int num) {
        int[] numbersKey = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanKey = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            for (int i = 0; i < numbersKey.length; i++) {
                if (numbersKey[i] <= num) {
                    num -= numbersKey[i];
                    res.append(romanKey[i]);
                    break;
                }
            }
        }
        return res.toString();
    }

    @Test
    public void intToRomanTest() {
        Assert.assertEquals("XX", intToRoman(20));
        Assert.assertEquals("III", intToRoman(3));
        Assert.assertEquals("IV", intToRoman(4));
        Assert.assertEquals("LVIII", intToRoman(58));
        Assert.assertEquals("MCMXCIV", intToRoman(1994));
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public int removeDuplicatesSorted(int[] nums) {
        if (nums.length < 1) return 0;
        int current = nums[0], index = 0, count = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] != current) {
                current = nums[i];
                nums[++index] = current;
                count++;
            }
        }
        return count;
    }

    @Test
    public void removeDuplicatesSortedTest() {
        Assert.assertEquals(5, removeDuplicatesSorted(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        Assert.assertEquals(2, removeDuplicatesSorted(new int[]{1, 1, 2}));
    }

    // https://leetcode.com/problems/implement-strstr/submissions/
    public int strStr(String haystack, String needle) {
        if (haystack.length() == 0) return 0;
        return haystack.indexOf(needle);
    }

    @Test
    public void strStrTest() {
        Assert.assertEquals(2, strStr("hello", "ll"));
        Assert.assertEquals(-1, strStr("aaaaa", "bba"));
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    @Test
    public void isPowerOfTwoTest() {
        Assert.assertTrue(isPowerOfTwo(1));
        Assert.assertTrue(isPowerOfTwo(16));
        Assert.assertFalse(isPowerOfTwo(3));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        HashSet<Integer> unique = new HashSet<>();
        unique.add(head.val);
        ListNode current = head;
        current = current.next;

        ListNode endUnique = head;

        while (current != null) {
            if (!unique.contains(current.val)) {
                endUnique.next = current;
                endUnique = endUnique.next;
                unique.add(current.val);
            }
            current = current.next;
        }
        endUnique.next = null;
        return head;
    }

    @Test
    public void deleteDuplicatesTest() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(2);
        ListNode fourth = new ListNode(3);
        ListNode fifth = new ListNode(3);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        deleteDuplicates(head);
    }

    public boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;

        for (int bill : bills) {
            if (bill == 5) fives++;
            if (bill == 10) {
                tens++;
                fives--;
            }
            if (bill == 20) {
                if (tens > 0) {
                    fives--;
                    tens--;
                } else {
                    fives -= 3;
                }
            }
            if (fives < 0) return false;
        }
        return true;
    }

    @Test
    public void lemonadeChangeTest() {
        Assert.assertTrue(lemonadeChange(new int[]{5, 5, 10}));
        Assert.assertFalse(lemonadeChange(new int[]{5, 5, 10, 10, 20}));
        Assert.assertTrue(lemonadeChange(new int[]{5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5}));
    }


    // Does not return anything... No test.
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums2Pointer = 0;
        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[nums2Pointer++];
        }
        Arrays.sort(nums1);
    }

    public String reverseStr2(String s, int k) {
        char[] ca = s.toCharArray();
        for (int left = 0; left < ca.length; left += 2 * k) {
            for (int i = left, j = Math.min(left + k - 1, ca.length - 1); i < j; i++, j--) {
                char tmp = ca[i];
                ca[i] = ca[j];
                ca[j] = tmp;
            }
        }
        return new String(ca);
    }

    @Test
    public void reverseStr2Test() {
        Assert.assertEquals("bacdfeg", reverseStr2("abcdefg", 2));
        Assert.assertEquals("cbaed", reverseStr2("abcde", 3));

    }

    public boolean stoneGame(int[] piles) {
        return true;
    }

    @Test
    public void stoneGameTest() {
        Assert.assertTrue(stoneGame(new int[]{5, 3, 4, 5}));
        Assert.assertTrue(stoneGame(new int[]{3, 2, 10, 4}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        if (nums[0] == 0) return false;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max--;
            if (nums[i] > max) max = nums[i];
            if (max == 0) return false;
            if (i + max >= nums.length - 1) return true;
        }
        return true;
    }

    @Test
    public void canJumpTest() {
        Assert.assertTrue(canJump(new int[]{2, 0}));
        Assert.assertTrue(canJump(new int[]{2, 3, 1, 1, 4}));
        Assert.assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));
        Assert.assertTrue(canJump(new int[]{0}));
        Assert.assertTrue(canJump(new int[]{2, 0, 0}));
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        int index = 1;
        for (int i = 1; i <= n; i++) {
            res[index] = Integer.bitCount(i);
            index++;
        }
        return res;
    }

    @Test
    public void countBitsTest() {
        Assert.assertArrayEquals(new int[]{0, 1, 1}, countBits(2));
        Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, countBits(5));
    }

    public int numSplits(String s) {
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            HashSet<Character> uniquesA = new HashSet<>();
            HashSet<Character> uniquesB = new HashSet<>();
            for (int j = 0; j < i; j++) {
                uniquesA.add(s.charAt(j));
            }
            for (int k = i; k < s.length(); k++) {
                uniquesB.add(s.charAt(k));
            }
            if (uniquesA.size() == 26 && uniquesB.size() == 26) return res;
            if (uniquesA.size() == uniquesB.size()) res++;
        }
        return res;
    }

    @Test
    public void numSplitTest() {
        Assert.assertEquals(3, numSplits("aaaa"));
        Assert.assertEquals(0, numSplits("bac"));
        Assert.assertEquals(2, numSplits("ababa"));
        Assert.assertEquals(1, numSplits("abcd"));
        Assert.assertEquals(2, numSplits("acbadbaada"));
    }

    class MyHashSet {
        boolean[] arr = new boolean[100];// start with 100 elements for fast initialization

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {

        }

        public void add(int key) {
            if (key >= arr.length) // if array is too small to accomodate key, extend it.
                extend(key);
            arr[key] = true;
        }

        public void remove(int key) {
            if (key >= arr.length) // if array is too small to accomodate key, extend it.
                extend(key);
            arr[key] = false;
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            if (key >= arr.length) // key cannot be in array if array's length < key
                return false;
            return arr[key];
        }

        public void extend(int key) {
            arr = Arrays.copyOf(arr, key + 2);  // extend array to one more item than necessary, we need "key" items.
            // we give "key+1" items to reduce collisions.
        }
    }

    public boolean isIsomorphic(String s, String t) {
        // TODO: Finish
        return false;
    }

    @Test
    public void isIsomorphic() {
        Assert.assertTrue(isIsomorphic("egg", "add"));
        Assert.assertFalse(isIsomorphic("foo", "bar"));
        Assert.assertTrue(isIsomorphic("paper", "title"));
    }

    public int maxScore(String s) {
        if (s.length() == 2) return 1;
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == '0') count++;
            }
            for (int k = i; k < s.length(); k++) {
                if (s.charAt(k) == '1') count++;
            }
            if (res < count) res = count;
        }
        return res;
    }

    @Test
    public void maxScoreTest() {
        Assert.assertEquals(4, maxScore("01001"));
        Assert.assertEquals(1, maxScore("00"));
        Assert.assertEquals(5, maxScore("011101"));
        Assert.assertEquals(5, maxScore("00111"));
        Assert.assertEquals(3, maxScore("1111"));
    }

    public String addBinary(String a, String b) {
        BigInteger s1Big = new BigInteger(a, 2);
        BigInteger s2Big = new BigInteger(b, 2);
        return s1Big.add(s2Big).toString(2);
    }

    @Test
    public void addBinaryTest() {
        Assert.assertEquals("100", addBinary("11", "1"));
    }

    public boolean backspaceCompare(String s, String t) {
        return backspaceCompareHelper(s).equals(backspaceCompareHelper(t));
    }

    public String backspaceCompareHelper(String string) {
        StringBuilder sb = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c == '#' && sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            } else if (c != '#') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Test
    public void backspaceCompareTest() {
        Assert.assertTrue(backspaceCompare("ab#c", "ad#c"));
        Assert.assertFalse(backspaceCompare("ab##", "abc#"));
        Assert.assertTrue(backspaceCompare("y#fo##f", "y#f#o##f"));
    }

    public int[] getConcatenation(int[] nums) {
        int[] returnArray = new int[nums.length * 2];
        int index = 0;
        for (int num : nums) {
            returnArray[index] = num;
            returnArray[index++ + nums.length] = num;
        }
        return returnArray;
    }

    @Test
    public void getConcatenationTest() {
        Assert.assertArrayEquals(new int[]{1, 2, 1, 1, 2, 1}, getConcatenation(new int[]{1, 2, 1}));
    }

    public boolean isPowerOfFour(int n) {
        return Math.log(n) / Math.log(4) % 1 == 0;
    }

    @Test
    public void isPowerOfFourTest() {
        Assert.assertTrue(isPowerOfFour(16));
        Assert.assertFalse(isPowerOfFour(5));
        Assert.assertTrue(isPowerOfFour(1));
        Assert.assertFalse(isPowerOfFour(8));
        Assert.assertTrue(isPowerOfFour(64));
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        String res = "";
        licensePlate = licensePlate.toLowerCase();
        int[] counts = new int[26];
        int total = 0;

        for (char c : licensePlate.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                counts[c - 'a']++;
                total++;
            }
        }

        for (String s : words) {
            int n = total;
            int[] sCount = counts.clone();
            for (char c : s.toCharArray()) {
                if (sCount[c - 'a']-- > 0) n--;
                if (n == 0 && (res.length() == 0 || s.length() < res.length())) res = s;
            }
        }
        return res;
    }

    @Test
    public void shortestCompletingWordTest() {
        Assert.assertEquals("steps", shortestCompletingWord("1s3 PSt",
                new String[]{"step", "steps", "stripe", "stepple"}));
        Assert.assertEquals("according", shortestCompletingWord("GrC8950",
                new String[]{"measure", "other", "every", "base", "according", "level", "meeting", "none", "marriage", "rest"}));
    }

    public int searchInsert(int[] nums, int target) {

        if (target > nums[nums.length - 1]) return nums.length;
        if (target < nums[0]) return 0;

        int leftBound = 0;
        int rightBound = nums.length - 1;
        int midPoint = (leftBound + rightBound) / 2;


        while (true) {
            if (nums[midPoint] < target) {
                leftBound = midPoint + 1;
            } else {
                rightBound = midPoint - 1;
            }
            if (nums[leftBound] == target) return leftBound;
            if (nums[rightBound] == target) return rightBound;
            if (nums[rightBound] < target && nums[leftBound] > target) return leftBound;
            midPoint = (leftBound + rightBound) / 2;
        }
    }

    @Test
    public void searchInsertTest() {
        Assert.assertEquals(2, searchInsert(new int[]{1, 3, 5, 6}, 5));
        Assert.assertEquals(1, searchInsert(new int[]{1, 3, 5, 6}, 2));
        Assert.assertEquals(4, searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    public boolean isBadVersion(int n) {
        return n == 4;
    }

    public int firstBadVersion(int n) {
        int low = 0, high = n, midPoint = (high + low) / 2;

        while (true) {
            if (low > high) {
                if (isBadVersion(low)) return low;
                if (isBadVersion(high)) return high;
            }
            if (isBadVersion(midPoint)) {
                high = midPoint - 1;
            } else {
                low = midPoint + 1;
            }
            midPoint = low + (high - low) / 2;
        }
    }

    @Test
    public void firstBadVersionTest() {
        Assert.assertEquals(4, firstBadVersion(5));
    }

    public int[] rotate(int[] nums, int k) {
        int pivot = k % nums.length;
        int[] clone = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            if (i + pivot < nums.length) {
                nums[i] = clone[(i + pivot)];
            } else nums[i] = clone[(i + pivot) - nums.length];
        }
        return nums;
    }

    @Test
    public void rotateTest() {
        Assert.assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
        Assert.assertArrayEquals(new int[]{3, 99, -1, -100}, rotate(new int[]{-1, -100, 3, 99}, 2));
        Assert.assertArrayEquals(new int[]{2, 1}, rotate(new int[]{1, 2}, 1));
        Assert.assertArrayEquals(new int[]{2, 1}, rotate(new int[]{1, 2}, 3));
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    @Test
    public void canWinNimTest() {
        Assert.assertTrue(canWinNim(1));
        Assert.assertTrue(canWinNim(2));
        Assert.assertFalse(canWinNim(4));
    }

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        s = s + s;
        return s.contains(goal);
    }

    @Test
    public void rotateStringTest() {
        Assert.assertTrue(rotateString("cdeab", "abcde"));
        Assert.assertFalse(rotateString("abcde", "abced"));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;

        // build the sliding window before using it
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i <= k && i < n; i++) {
            if (!seen.add(nums[i])) return true;
        }

        // go through the nums array and check for duplicates in the window
        for (int i = k + 1; i < n; i++) {
            seen.remove(nums[i - k - 1]);
            if (!seen.add(nums[i])) return true;
        }

        return false;
    }

    @Test
    public void containsNearbyDuplicatesTest() {
        Assert.assertTrue(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        Assert.assertFalse(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

    public static int maxSubArray(int[] A) {
        int maxSoFar = A[0], maxEndingHere = A[0];
        for (int i = 1; i < A.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
            // MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
            // maxSoFar is the biggest out of the maxEndingHere and maxSoFar
        }
        return maxSoFar;
    }

    @Test
    public void maxSubArray() {
        Assert.assertEquals(6, maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        Assert.assertEquals(-1, maxSubArray(new int[]{-1}));
        Assert.assertEquals(-1, maxSubArray(new int[]{-2, -1}));
    }

    public boolean makeEqual(String[] words) {
        if (words.length == 1) return true;
        HashMap<Character, Integer> counter = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (counter.containsKey(c)) {
                    counter.put(c, counter.get(c) + 1);
                } else {
                    counter.put(c, 1);
                }
            }
        }
        for (Character c : counter.keySet()) {
            if (counter.get(c) % words.length != 0) return false;
        }
        return true;
    }

    @Test
    public void makeEqualTest() {
        Assert.assertTrue(makeEqual(new String[]{"abc", "aabc", "bc"}));
        Assert.assertFalse(makeEqual(new String[]{"ab", "a"}));
        Assert.assertFalse(makeEqual(new String[]{"a", "b"}));
        Assert.assertTrue(makeEqual(new String[]{"a", "a"}));
        Assert.assertTrue(makeEqual(new String[]{"abc", "cba"}));
        Assert.assertFalse(makeEqual(new String[]{"bc", "de"}));
    }

    int[] dp = new int[46];

    public int climbStairs(int n) {
        if (dp[n] != 0) return dp[n];

        if (n == 1 || n == 2) return n;
        dp[n] = climbStairs(n - 1) + climbStairs(n - 2);
        return dp[n];
    }

    @Test
    public void climbStairs() {
        Assert.assertEquals(1, climbStairs(2));
        Assert.assertEquals(3, climbStairs(3));
    }

    public String reverseVowels(String s) {
        char[] charArray = s.toCharArray();
        int frontPointer = 0;
        int endPointer = charArray.length - 1;
        while (frontPointer < endPointer) {

            var frontPointerBol = charArray[frontPointer] == 'a' || charArray[frontPointer] == 'e' || charArray[frontPointer] == 'i'
                    || charArray[frontPointer] == 'o' || charArray[frontPointer] == 'u' || charArray[frontPointer] == 'A' || charArray[frontPointer] == 'E' || charArray[frontPointer] == 'I'
                    || charArray[frontPointer] == 'O' || charArray[frontPointer] == 'U';

            var endPointerBol = charArray[endPointer] == 'a' || charArray[endPointer] == 'e' || charArray[endPointer] == 'i'
                    || charArray[endPointer] == 'o' || charArray[endPointer] == 'u' || charArray[endPointer] == 'A' || charArray[endPointer] == 'E' || charArray[endPointer] == 'I'
                    || charArray[endPointer] == 'O' || charArray[endPointer] == 'U';

            if (frontPointerBol && endPointerBol) {
                char temp = charArray[frontPointer];
                charArray[frontPointer] = charArray[endPointer];
                charArray[endPointer] = temp;
                frontPointer++;
                endPointer--;
            } else if (frontPointerBol) {
                endPointer--;
            } else if (endPointerBol) {
                frontPointer++;
            } else {
                frontPointer++;
                endPointer--;
            }
        }
        return String.valueOf(charArray);
    }

    @Test
    public void reverseVoweslTest() {
        Assert.assertEquals("holle", reverseVowels("hello"));
        Assert.assertEquals("leotcede", reverseVowels("leetcode"));
    }

    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int divisor = nums[nums.length - 1];

        while (divisor > 1) {
            if (nums[0] % divisor == 0 && nums[nums.length - 1] % divisor == 0) {
                return divisor;
            }
            divisor--;
        }
        return 1;
    }

    @Test
    public void findGCDTest() {
        Assert.assertEquals(2, findGCD(new int[]{2, 5, 6, 9, 10}));
    }

    // https://leetcode.com/problems/remove-element/submissions/
    public int removeElement(int[] nums, int val) {
        int copyPointer = 0, counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[copyPointer++] = nums[i];
                counter++;
            }
        }
        return counter;
    }

    @Test
    public void removeElementTest() {
        Assert.assertEquals(2, removeElement(new int[]{3, 2, 2, 3}, 3));
        Assert.assertEquals(5, removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    public int countGoodSubstrings(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 2; i++) {
            if (chars[i] != chars[i + 1] && chars[i + 1] != chars[i + 2]
                    && chars[i] != chars[i + 2]) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void countGoodSubstringsTest() {
        Assert.assertEquals(1, countGoodSubstrings("xyzzaz"));
        Assert.assertEquals(4, countGoodSubstrings("aababcabc"));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        int count = 0;
        if (n == 0) return true;
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 0) return n <= 1;
            else {
                return n <= 0;
            }
        }

        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            count++;
            flowerbed[0] = 1;
        }


        for (int i = 1; i < flowerbed.length - 1; i++) {
            if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                count++;
            }
        }

        if (flowerbed[flowerbed.length - 1] == 0 & flowerbed[flowerbed.length - 2] == 0) {
            count++;
        }

        return n <= count;
    }

    @Test
    public void canPlaceFlowersTest() {
        Assert.assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        Assert.assertFalse(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        Assert.assertFalse(canPlaceFlowers(new int[]{1, 0, 1}, 1));
        Assert.assertFalse(canPlaceFlowers(new int[]{0, 1, 0}, 1));
    }


    public boolean testZeroes(int n) {
        while (n != 0) {
            int d = n % 10;
            if (d == 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    @Test
    public void testZeroesTest() {
        Assert.assertTrue(testZeroes(111));
        Assert.assertFalse(testZeroes(101));
        Assert.assertFalse(testZeroes(1010));

    }


    public int[] getNoZeroIntegers(int n) {
        int x = 1, y = n - 1;
        while (!(testZeroes(y)) || !(testZeroes(x))) {
            y--;
            x++;
        }
        return new int[]{x, y};
    }

    @Test
    public void getNoZeroIntegersTest() {
        Assert.assertArrayEquals(new int[]{1, 68}, getNoZeroIntegers(69));
        Assert.assertArrayEquals(new int[]{11, 999}, getNoZeroIntegers(1010));
    }

    public String thousandSeparator(int n) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (n != 0) {
            if (count == 3) {
                count = 0;
                sb.append('.');
            } else {
                count++;
                int d = n % 10;
                sb.append(d);
                n /= 10;
            }
        }
        return sb.reverse().toString();
    }

    @Test
    public void thousandSeparatorTest() {
        Assert.assertEquals("1.234", thousandSeparator(1234));
    }

    public int secondHighest(String s) {
        Set<Integer> uniques = new TreeSet();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                uniques.add(Character.getNumericValue(s.charAt(i)));
            }
        }
        if (uniques.size() == 1) return -1;
        if (uniques.isEmpty()) return -1;
        Object[] arr = uniques.toArray();
        return (int) arr[uniques.size() - 2];
    }

    @Test
    public void secondHighestTest() {
        Assert.assertEquals(2, secondHighest("dfa12321afd"));
        Assert.assertEquals(-1, secondHighest("dfa11111afd"));
    }

    public String convertToTitle(int n) {
        return n == 0 ? "" : convertToTitle(--n / 26) + (char) ('A' + (n % 26));
    }

    @Test
    public void convertToTitleTest() {
        Assert.assertEquals("AA", convertToTitle(27));
    }

    public String makeGood(String s) {
        int n = s.length();

        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(s.charAt(i) - s.charAt(i + 1)) == 32) {
                return makeGood(s.substring(0, i) + s.substring(i + 2));
            }
        }

        return s;
    }

    @Test
    public void makeGoodTest() {
        Assert.assertEquals("leetcode", makeGood("leEeetcode"));
        Assert.assertEquals("", makeGood("abBAcC"));
    }

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        if (releaseTimes.length == 0) return 0;

        char charPress = keysPressed.charAt(0);
        int longestPress = releaseTimes[0];

        for (int i = 1; i < releaseTimes.length; i++) {
            int releaseTime = releaseTimes[i] - releaseTimes[i - 1];
            if (releaseTime > longestPress) {
                longestPress = releaseTime;
                charPress = keysPressed.charAt(i);
            }
            if (releaseTime == longestPress) {
                charPress = keysPressed.charAt(i) > charPress ? keysPressed.charAt(i) : charPress;
            }
        }
        return charPress;
    }

    @Test
    public void slowestKey() {
        Assert.assertEquals('c', slowestKey(new int[]{9, 29, 49, 50}, "cbcd"));
        Assert.assertEquals('a', slowestKey(new int[]{12, 23, 36, 46, 62}, "spuda"));
    }

    public String reversePrefix(String word, char ch) {
        String prefix = word.substring(0, word.indexOf(ch) + 1);
        String suffix = word.substring(word.indexOf(ch) + 1);

        StringBuilder sb = new StringBuilder(prefix);
        sb.reverse();
        sb.append(suffix);
        return sb.toString();
    }

    @Test
    public void reversePrefixTest() {
        Assert.assertEquals("dcbaefd", reversePrefix("abcdefd", 'd'));
    }

    public boolean isLongPressedName(String name, String typed) {
        int namePointer = 0;
        for (int i = 0; i < typed.length(); i++) {
            if (namePointer < name.length() - 1 &&
                    (typed.charAt(i) != name.charAt(namePointer)
                            || typed.charAt(i) != name.charAt(namePointer + 1))) return false;
            if (namePointer == name.length()) {
                if (typed.charAt(i) != name.charAt(name.length() - 1)) {
                    return false;
                }
            } else if (typed.charAt(i) == name.charAt(namePointer)) {
                namePointer++;
            }
        }
        return namePointer == name.length();
    }

    @Test
    public void isLongPressedName() {
        Assert.assertTrue(isLongPressedName("alex", "aaleex"));
        Assert.assertTrue(isLongPressedName("alex", "alex"));
        Assert.assertFalse(isLongPressedName("alex", "aaleelx"));
        Assert.assertTrue(isLongPressedName("vtkgn", "vttkgnn"));
        Assert.assertFalse(isLongPressedName("alex", "aleexa"));
        Assert.assertFalse(isLongPressedName("saeed", "ssaaedd"));
    }

    public int numDifferentIntegers(String word) {
        for (char c : word.toCharArray()) {
            if (!Character.isDigit(c)) {
                word = word.replace(c, ' ');
            }
        }
        String[] numbers = word.split(" ");
        HashSet<String> uniques = new HashSet<>();
        for (String s : numbers) {
            if (!s.isBlank()) {
                uniques.add(trimLeadingZeros(s));
            }
        }
        return uniques.size();
    }

    public static String trimLeadingZeros(String source) {
        for (int i = 0; i < source.length(); ++i) {
            char c = source.charAt(i);
            if (c != '0') {
                return source.substring(i);
            }
        }
        return ""; // or return "0";
    }


    @Test
    public void numDifferentIntegersTest() {
        Assert.assertEquals(3, numDifferentIntegers("a123bc34d8ef34"));
        Assert.assertEquals(1, numDifferentIntegers("a1b01c001"));
        Assert.assertEquals(1, numDifferentIntegers("167278959591294"));
    }

    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (sb.toString().equals(s)) return true;
        }
        return false;
    }

    @Test
    public void isPrefixStringTest() {
        Assert.assertTrue(isPrefixString("iloveleetcode", new String[]{"i", "love", "leetcode", "apples"}));
        Assert.assertFalse(isPrefixString("iloveleetcode", new String[]{"apples", "i", "love", "leetcode"}));
        Assert.assertFalse(isPrefixString("a", new String[]{"aa", "aaaa", "banana"}));
    }

    public String maximumTime(String time) {
        char[] times = time.toCharArray();
        if (times[0] == '?') {
            times[0] = times[1] <= '3' || times[1] == '?' ? '2' : '1';
        }

        if (times[1] == '?') {
            times[1] = times[0] == '2' ? '3' : '9';
        }

        if (times[3] == '?') {
            times[3] = '5';
        }

        if (times[4] == '?') {
            times[4] = '9';
        }

        return new String(times);
    }


    @Test
    public void maximumTimeTest() {
        Assert.assertEquals("23:50", maximumTime("2?:?0"));
        Assert.assertEquals("09:39", maximumTime("0?:3?"));
        Assert.assertEquals("14:03", maximumTime("?4:03"));
        Assert.assertEquals("15:13", maximumTime("?5:13"));
        Assert.assertEquals("20:15", maximumTime("?0:15"));
    }

    public String makeFancyString(String s) {
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length - 2; i++) {
            if (sChar[i] == sChar[i + 1] && sChar[i + 1] == sChar[i + 2]) {
                sChar[i] = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : sChar) {
            if (c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Test
    public void makeFancyStringTest() {
        Assert.assertEquals("leetcode", makeFancyString("leeetcode"));
        Assert.assertEquals("aabaa", makeFancyString("aaabaaaa"));
    }


    public boolean areNumbersAscending(String s) {
        int currentNumber = 0;
        String[] words = s.split(" ");
        for (String word : words) {
            if (Character.isDigit(word.charAt(0))) {
                if (Integer.parseInt(word) <= currentNumber) return false;
                else currentNumber = Integer.parseInt(word);
            }
        }
        return true;
    }

    @Test
    public void areNumbersAscendingTest() {
        Assert.assertTrue(areNumbersAscending("a puppy has 2 eyes 4 legs"));
        Assert.assertFalse(areNumbersAscending("sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"));
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;

        int index = 0;
        while (count < k) {
            nums[index] = -nums[index++];
            count++;
            if (index == nums.length - 1 || nums[index] > 0) {
                Arrays.sort(nums);
                index = 0;
            }
        }
        count = 0;
        for (int i : nums) count += i;
        return count;
    }

    @Test
    public void largestSumAfterKNegationsTest() {
        Assert.assertEquals(5, largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        Assert.assertEquals(6, largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        Assert.assertEquals(53, largestSumAfterKNegations(new int[]{8, -7, -3, -9, 1, 9, -6, -9, 3}, 8));
        Assert.assertEquals(5, largestSumAfterKNegations(new int[]{-4, -2, -3}, 4));
    }

    public int countKDifference(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j == i) continue;
                if (Math.abs(nums[i] - nums[j]) == k) res++;
            }
        }
        return res;
    }

    @Test
    public void CountKDifferenceTest() {
        Assert.assertEquals(4, countKDifference(new int[]{1, 2, 2, 1}, 1));
    }


    public boolean isSubsequence(String s, String t) {
        if (s.isBlank()) return true;
        int pointer = 0;
        for (char c : t.toCharArray()) {
            if (c == s.charAt(pointer)) pointer++;
            if (pointer == s.length()) return true;
        }
        return false;
    }

    @Test
    public void isSubsequenceTest() {
        Assert.assertTrue(isSubsequence("abc", "ahbgdc"));
    }

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>(), set3 = new HashSet<>(), set = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
            set.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
            set.add(i);
        }
        for (int i : nums3) {
            set3.add(i);
            set.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (int i : set)
            if (set1.contains(i) && set2.contains(i) || set2.contains(i) && set3.contains(i) || set1.contains(i) && set3.contains(i))
                result.add(i);
        return result;
    }


    public String kthDistinct(String[] arr, int k) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        HashSet<String> duplicates = new HashSet<>();
        for (String s : arr) {
            if (!duplicates.contains(s)) {
                if (linkedHashSet.contains(s)) {
                    linkedHashSet.remove(s);
                    duplicates.add(s);
                } else linkedHashSet.add(s);
            }
        }
        int count = 1;
        for (String s : linkedHashSet) {
            if (count == k) {
                return s;
            }
            count++;
        }
    return "";
    }

    @Test
    public void kthDistinctTest(){
        Assert.assertEquals("a", kthDistinct(new String[]{"d","b","c","b","c","a"}, 2));
        Assert.assertEquals("aaa", kthDistinct(new String[]{"aaa","aa","a"}, 1));
    }

    public int smallestEqual(int[] nums) {
        for(int i = 0; i < nums.length ; i++){
            if(nums[i] == i % 10) return i;
        }
        return -1;
    }

    @Test
    public void smallestEqualTest(){
        Assert.assertEquals(0, smallestEqual(new int[]{0, 1, 2}));
        Assert.assertEquals(2, smallestEqual(new int[]{4, 3, 2, 1}));
    }

    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for(String s : operations){
            if(s.charAt(1) == '+') res++;
            else res--;
        }
        return res;
    }

    @Test
    public void finalValueAfterOperationsTest () {
        Assert.assertEquals(1, finalValueAfterOperations(new String[]{"--X","X++","X++"}));
    }

    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for(String s : patterns){
            if(word.contains(s)){
                count++;
            }
        }
        return count;
    }

    @Test
    public void numOfStringsTest() {
        Assert.assertEquals(3, numOfStrings(new String[]{"a", "abc", "bc", "d"}, "abc"));
    }


}



