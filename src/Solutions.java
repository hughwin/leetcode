import org.junit.Assert;
import org.junit.Test;

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
//    public List<Integer> inorderTraversal(TreeNode root) {
//        // TODO: Finish this
//    }

    public static TreeNode inorderTraversalHelp(TreeNode root){
        if (root.right == null && root.left == null){
            return root;
        }
        if (root.left != null){
            return inorderTraversalHelp(root.left);
        }
        if (root.right!= null){
            return inorderTraversalHelp(root.right);
        }
        return null;
    }

    // https://leetcode.com/problems/running-sum-of-1d-array/submissions/
    public static int[] runningSum(int[] nums){
        int count = 0;
        for (int i=0; i < nums.length ; i++){
            count += nums[i];
            nums[i] = count;
        }
        return nums;
    }

    @Test
    public void runningSumTest(){
        int[] actual = runningSum(new int[]{1,2,3,4});
        int[] expected = new int[]{1,3,6,10};
        Assert.assertArrayEquals(actual, expected);
    }

    // https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/submissions/
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int [] shorter = new int[nums.length];
        for (int i = 0 ; i < nums.length ; i++){
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

    @Test
    public void smallerNumbersThanCurrentTest(){
        int[] actual = smallerNumbersThanCurrent(new int[]{8,1,2,2,3});
        int[] expected = new int[]{4,0,1,1,3};
        Assert.assertArrayEquals(expected, actual);
    }

    // https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> shorterOnRight = new ArrayList<>();
        for (int i = 0 ; i < nums.length ; i++){
            int count = 0;
            for (int k = i ; k < nums.length ; k++){
                if(nums[i] > nums[k]){
                    count++;
                }
            }
            shorterOnRight.add(count);
        }
        return shorterOnRight;
    }

    @Test
    public void countSmallerTest(){
        List<Integer> actual = countSmaller(new int[]{5,2,6,1});
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        expected.add(1);
        expected.add(0);
        Assert.assertEquals(actual, expected);
    }

    public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    @Test
    public void defangIPaddrTest(){
        Assert.assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));
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

    public static int removeElement(int[] nums, int val) {
        return 0;
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

        for (String word : words){
            if (findWordsHelper(word, firstRow) || findWordsHelper(word, secondRow) || findWordsHelper(word, thirdRow)){
                returnedWords.add(word);
            }
        }

        for (String word : returnedWords){
            System.out.println(word);
        }
        return returnedWords.toArray(String[]::new);
    }

    public static boolean findWordsHelper(String word, HashSet<Character> row){
            for (int i = 0; i < word.length(); i++) {
                if (!row.contains(word.toLowerCase().charAt(i))) {
                    return false;
                }
            }
        return true;
    }

    @Test
    public void findWordsTest(){
        String[] expected = new String[]{"Alaska", "Dad"};
        Assert.assertArrayEquals(expected,findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"}));
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
    public void removeElementTest() {
        Assert.assertEquals(2, removeElement(new int[]{3, 2, 2, 3}, 3));
        Assert.assertEquals(5, removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    public double angleClock(int hour, int minutes) {
        if(hour == 12) hour = 0;
        int minutesAngle = minutes * 6;
        double minutesHour = (minutes / 60.0);
        double actualHour = minutesHour + hour;
        double actualHourAngle = actualHour * 30;
        double angle1 = Math.abs((actualHourAngle) - (minutesAngle));
        return Math.min(angle1, 360 - angle1);
    }

    @Test
    public void angleClockTest(){
        Assert.assertEquals(165.0, angleClock(12, 30), 0.0);
        Assert.assertEquals(75.0, angleClock(3, 30), 0.0);
        Assert.assertEquals(7.5, angleClock(3, 15), 0.0);
    }



    // https://leetcode.com/problems/fibonacci-number/
    public static int fib(int n) {
        if (n == 0){return 0;}
        if (n == 1){return 1;}
        int count = 1;
        int current = 0;
        int next;
        int temp = 0;
        while(count < n){
            if (current == 0){
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

    @Test
    public void fibTest(){
        Assert.assertEquals(1, fib(2));
        Assert.assertEquals(2, fib(3));
        Assert.assertEquals(3, fib(4));
    }
    // https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/submissions/
    public static int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] -1) * (nums[nums.length - 2] - 1);
    }

    @Test
    public void maxProductTest(){
        Assert.assertEquals(16, maxProduct(new int[]{1,5,4,5}));
        Assert.assertEquals(12, maxProduct(new int[]{3,4,5,2}));
        Assert.assertEquals(12, maxProduct(new int[]{3,7}));
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] newArray = new int[nums.length];
        int count = 0;
        for(int i = 0; i < nums.length ; i+=2){
            newArray[i] = nums[count];
            newArray[i + 1] = nums[n + count];
            count++;
        }
        return newArray;
    }

//    https://leetcode.com/problems/shuffle-the-array/submissions/
    @Test
    public void shuffleTest(){
        int[] expected = new int[]{2,3,5,4,1,7};
        int[] actual = shuffle(new int[]{2,5,1,3,4,7}, 3);
        Assert.assertArrayEquals(expected, actual);
    }

    public static int getDecimalValue(ListNode head) {
        String binaryString = "";
        while (head != null){
            binaryString += (String.valueOf(head.val));
            head = head.next;
        }
        return Integer.parseInt(binaryString, 2);
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


    // https://leetcode.com/problems/available-captures-for-rook
    public static int numRookCaptures(char[][] board) {
        int count = 0;
        ArrayList<int[][]> otherPieces = new ArrayList<>();
        for (int i = 0 ; i < board.length ; i++){
            for (int k = 0 ; k < board[i].length ; k++){
                if(board[i][k] == 'R'){
                    for(int j = i ; j >= 0 ; j--){
                        if(board[j][k] == 'p'){
                            count++;
                            break;
                        }
                        if(board[j][k] == 'B') break;
                    }
                    for(int n = i ; n < board.length ; n++){
                        if(board[n][k] == 'p'){
                            count++;
                            break;
                        }
                        if(board[n][k] == 'B') break;
                    }
                    for(int m = k ; m >= 0 ; m--){
                        if(board[i][m] == 'p'){
                            count++;
                            break;
                        }
                        if(board[i][m] == 'B') break;
                    }
                    for(int p = k ; p < board[i].length ; p++){
                        if(board[i][p] == 'p'){
                            count++;
                            break;
                        }
                        if(board[i][p] == 'B') break;
                    }
                }
            }
        }
        return count;
    }
    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    // I have kept this method in as an example of a naive approach to the algorithm. A much improved solution is below.
    public static int singleNonDuplicateSolution1(int[] nums) {
        List<Integer> numsAsList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numsAsList.add(num);
        }
        for(int i = 0 ; i < numsAsList.size() ; i++){
            if(numsAsList.indexOf(numsAsList.get(i)) == numsAsList.lastIndexOf(numsAsList.get(i))){
                return numsAsList.get(i);
            }
        }
        return 0;
    }

    public static int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length ; i+=2){
            try {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }catch (IndexOutOfBoundsException e){
                return nums[i];
            }
        }
        return 0;
    }

    @Test
    public void singleNonDuplicateTest() {
        int[] input1 = new int[]{1,1,2,3,3,4,4,8,8};
        int[] input2 = new int[]{3,3,7,7,10,11,11};
        Assert.assertEquals(2, singleNonDuplicate(input1));
        Assert.assertEquals(10, singleNonDuplicate(input2));
    }

    // https://leetcode.com/problems/middle-of-the-linked-list/
    public static ListNode middleNode(ListNode head) {
        ArrayList<ListNode> nodes = new ArrayList<>();
        while(head != null){
            nodes.add(head);
            head = head.next;
        }
        return nodes.get(nodes.size() / 2);
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

    public static String reverseWords(String s) {
        String[] splitString = s.split(" ");
        for (int i = 0 ; i < splitString.length ; i++) {
            StringBuilder stringBuilder = new StringBuilder();
           for(int k = splitString[i].length() - 1; k >=0 ; k--){
               stringBuilder.append(splitString[i].charAt(k));
           }
           splitString[i] = stringBuilder.toString();
        }
        return String.join(" ", splitString);
    }

    @Test
    public void reverseWordsTest(){
        Assert.assertEquals("s'teL ekat edoCteeL tsetnoc", reverseWords("Let's take LeetCode contest"));
    }

        public static int compareVersion(String version1, String version2) {
            String[] version1Array = version1.split("\\.");
            String[] version2Array = version2.split("\\.");
            int min = Math.min(version1Array.length, version2Array.length);
            int count = 0;
                for (int i = 0; i < min; i++) {
                    if (Integer.parseInt(version1Array[i]) < Integer.parseInt(version2Array[i])){
                        return -1;
                    }
                    if (Integer.parseInt(version1Array[i]) > Integer.parseInt(version2Array[i])){
                        return 1;
                    }
                    count++;
                }
            if(version2Array.length > version1Array.length){
                for(int i = count ; i < version2Array.length ; i++){
                    if (Integer.parseInt(version2Array[i]) != 0){
                        return -1;
                    }
                }
            }
            if(version1Array.length > version2Array.length){
                for(int i = count ; i < version1Array.length ; i++){
                    if (Integer.parseInt(version1Array[i]) != 0){
                        return 1;
                    }
                }
            }
            return 0;
        }

    @Test
    public void compareVersionTest(){
        Assert.assertEquals(-1, compareVersion("0.1", "1.1"));
        Assert.assertEquals(1, compareVersion("1.0.1", "1"));
        Assert.assertEquals(-1, compareVersion("7.5.2.4", "7.5.3"));
        Assert.assertEquals(0, compareVersion("01", "1"));
        Assert.assertEquals(-1, compareVersion("1", "1.1"));
        Assert.assertEquals(0, compareVersion("1.0", "1") );
    }

    // https://leetcode.com/problems/shuffle-string/discuss/765424/Easy-Approach-or-Java-Solution
    public static String restoreString(String s, int[] indices) {
        String[] split = s.split("");
        String[] reconstituted = new String[split.length];

        for(int i = 0 ; i < indices.length ; i++){
            int key = indices[i];
            reconstituted[key] = split[i];
        }
        return String.join("", reconstituted);
    }

    @Test
    public void restoreStringTest(){
        Assert.assertEquals("nihao", restoreString("aiohn", new int[]{3, 1, 4, 2, 0}));
    }

    // https://leetcode.com/problems/self-dividing-numbers/discuss/758471/Java-oror-2ms
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = left ; i <= right ; i++){
            if(selfDividingNumber(i)){
                numbers.add(i);
            }
        }
        return numbers;
    }

    public static boolean selfDividingNumber(int num){
        String[] nums = String.valueOf(num).split("");
        for(String s : nums){
            if(Integer.parseInt(s) == 0){
                return false;
            }
            if(num % Integer.parseInt(s) != 0){
                return false;
            }
        }
        return true;
    }

    @Test
    public void selfDividingNumbersTest(){
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

    // https://leetcode.com/problems/non-decreasing-array/
    public static boolean checkPossibility(int[] nums) {
        for(int i = 1;i<nums.length;i++){
            if(nums[i] < nums[i-1] && (i == 1 || nums[i] >= nums[i-2]) ){
                return checkPossibility(nums, i+1, nums[i]);
            }
            else if(nums[i] < nums[i-1]){
                return checkPossibility(nums, i+1, nums[i-1]);
            }
        }
        return true;
    }

    private static boolean checkPossibility(int [] nums, int start, int num){
        int prev = num;
        for(int i = start;i<nums.length;i++){
            if(nums[i] < prev)
                return false;
            prev = nums[i];
        }
        return true;
    }

    @Test
    public void checkPossibilityTest(){
        Assert.assertSame(true, checkPossibility(new int[]{4,2,3}));
        Assert.assertSame(false, checkPossibility(new int[]{4,2,1}));
        Assert.assertSame(false, checkPossibility(new int[]{3,4,2,3}));

    }

    // https://leetcode.com/problems/day-of-the-week
    public String dayOfTheWeek(int day, int month, int year) {
        String[] daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return daysOfWeek[LocalDate.of(year, month, day).getDayOfWeek().getValue() - 1];

    }

    @Test
    public void dayOfTheWeekTest(){
        Assert.assertEquals("Saturday", dayOfTheWeek(31, 8, 2019));
        Assert.assertEquals("Sunday", dayOfTheWeek(15, 8, 1993));

    }

    // https://leetcode.com/problems/flipping-an-image/
    public static int[][] flipAndInvertImage(int[][] A) {
        int[][] b = A.clone();
        for(int i = 0 ; i < A.length ; i++) {
            int[] copy = new int[A[i].length];
            for(int k = 0; k < A[i].length; k++){
                copy[A[i].length - k - 1] = (A[i][k] == 0 ? 1 : 0);
                b[i] = copy;
            }
        }
        return b;
    }

    // https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
    public static int countNegatives(int[][] grid){
        int count =0;
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

    @Test
    public void countNegativesTest(){
        int[][] input = new int[2][4];
        input[0] = new int []{3,2,-1,-2};
        input[1] = new int []{1,0,0,-3};
        Assert.assertEquals(3, countNegatives(input));

        int[][] input2 = new int[2][2];
        input[0] = new int []{3,2};
        input[1] = new int []{1,0};
        Assert.assertEquals(0, countNegatives(input2));
    }


    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    public String removeDuplicates(String S) {
        char[] stringArr = S.toCharArray();

        for (int i = 0; i < stringArr.length - 1; i++) {
            if (stringArr[i] == stringArr[i + 1]) {
                String beginning = String.valueOf(Arrays.copyOfRange(stringArr, 0, i));
                String end = i+1 > stringArr.length ? "" : String.valueOf(Arrays.copyOfRange(stringArr, i + 2, stringArr.length));

                return this.removeDuplicates(beginning + end);
            }
        }

        return String.valueOf(stringArr);
    }

    @Test
    public void removeDuplicatesTest(){
        Assert.assertEquals("ca", removeDuplicates("abbaca"));
    }

    // https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        if (node.next != null || node != null){
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    @Test
    public void deleteNodeTest() {
        //TODO: Finish this later
    }

    public String reverseWords2(String s) {
        String[] strSplit = s.split(" ");
        List<String> strClone = new ArrayList<>();

        for(int i = strSplit.length - 1 ; i >= 0; i--){
            if(!strSplit[i].equals("")){
                strClone.add(strSplit[i].strip());
            }
        }

        return String.join(" ", strClone);
    }

    @Test
    public void reverseWords2Test(){
        Assert.assertEquals("blue is sky the", reverseWords2("the   sky is blue"));
    }

    // https://leetcode.com/problems/water-bottles
    public static int numWaterBottles(int numBottles, int numExchange) {
        int count = 0;
        int empties = 0;
        while (numBottles != 0){
            count += numBottles;
            numBottles = numBottles + empties;
            empties = numBottles % numExchange;
            numBottles = numBottles / numExchange;
        }
        return count;
    }

    @Test
    public void numWaterBottlesTest(){
        Assert.assertEquals(19, numWaterBottles(15, 4));
    }

    // https://leetcode.com/problems/search-in-a-binary-search-tree
    public static TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if(root.val == val){
            return root;
        }
        if(val > root.val) {
            return searchBST(root.right, val);
        }
        if(val < root.val) {
            return searchBST(root.left, val);
        }
        return root;
    }

    //https://leetcode.com/problems/sort-array-by-parity/
    public static int[] sortArrayByParity(int[] A){
        ArrayList<Integer> evens = new ArrayList<>();
        ArrayList<Integer> odds = new ArrayList<>();
        for(int a : A){
            if(a % 2 == 0){
                evens.add(a);
            }
            else{
                odds.add(a);
            }
        }
        evens.addAll(odds);
        for(int i = 0; i < evens.size(); i++){
            A[i] = evens.get(i);
        }
        return A;
    }

    @Test
    public void sortArrayByParity(){
        Assert.assertArrayEquals(new int[]{2,4,3,1}, sortArrayByParity(new int[]{3,1,2,4}));
    }

    // https://leetcode.com/problems/valid-palindrome/
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase().replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(s);
        String reversed = stringBuilder.reverse().toString();
        return reversed.equals(s);
    }

    @Test
    public void validPalindromeTest(){
        Assert.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome("race a car"));
    }

    // https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
    public int numberOfSteps (int num) {
        int count = 0;
        while(num != 0){
            if(num % 2 == 0){
                count++;
                num = num / 2;
            }
            else{
                count++;
                num--;
            }
        }
        return count;
    }

    @Test
    public void numberOfStepsTest(){
        Assert.assertEquals(6, numberOfSteps(14));
        Assert.assertEquals(4, numberOfSteps(8));
        Assert.assertEquals(12, numberOfSteps(123));

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
            if(i % 2 == 0){
                A[i] = evens.get(evensPointer);
                evensPointer++;
            }
            else{
                A[i] = odds.get(oddsPointer);
                oddsPointer++;
            }
        }
        return A;
    }

    @Test
    public void sortArrayByParityII(){
        Assert.assertArrayEquals(new int[]{4,5,2,7},sortArrayByParityII(new int[]{4,2,5,7}));
    }

    // https://leetcode.com/problems/number-complement/
    public int findComplement(int num) {
        String binary = Integer.toBinaryString(num);
        StringBuilder sb = new StringBuilder();
        for(int i = 0;  i <binary.length(); i++ ){
            if(binary.charAt(i) == '0'){
                sb.append('1');
            }else sb.append('0');
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    @Test
    public void findComplementTest(){
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

    @Test
    public void isPrefixOfWordTest(){
        Assert.assertEquals(4, isPrefixOfWord("i love eating burger", "burg"));
        Assert.assertEquals(2, isPrefixOfWord("this problem is an easy problem", "pro"));
        Assert.assertEquals(2, isPrefixOfWord("dumb dream duck duck i", "drea"));
        Assert.assertEquals(4, isPrefixOfWord("love errichto jonathan dumb", "dumb"));
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

    @Test
    public void singleNumberTest(){
        Assert.assertEquals(1, singleNumber(new int[]{2,2,1}));
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
    public void sortByBitsTest(){
        int[] expected = new int[]{0,1,2,4,8,3,5,6,7};
        int[] input = new int[]{0,1,2,4,8,3,5,6,7};
        Assert.assertArrayEquals(expected, sortByBits(input));

        int[] expected1 = new int[]{2, 3, 5, 17, 7, 11, 13, 19};
        int[] input1 = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
        Assert.assertArrayEquals(expected1, sortByBits(input1));

        int[] expected2 = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        int[] input2 = new int[]{1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        Assert.assertArrayEquals(expected2, sortByBits(input2));
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

    public int reverse(int x) {
        boolean lessThan  = x < 0;
        x = Math.abs(x);
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        String reversed = sb.reverse().toString();
        reversed = Integer.toBinaryString(Integer.parseInt(reversed));
        return (lessThan) ? -Integer.parseInt(reversed, 2) : Integer.parseInt(reversed, 2);
    }

    @Test
    public void reverseTest(){
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(-321, reverse(-123));
        Assert.assertEquals(21, reverse(120));
    }

    public int findSpecialInteger(int[] arr) {
        int twentyFive = (int) (arr.length * 0.25);
        int count = 0;
        int currentInt = arr[0];
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == currentInt){
                count++;
                if(count > twentyFive){
                    return currentInt;
                }
            }
            else{
                count = 0;
                currentInt = arr[i + 1];
            }
        }
    return -1;
    }

    @Test
    public void findSpecialIntegerTest(){
        Assert.assertEquals(6, findSpecialInteger(new int[]{1,2,2,6,6,6,6,7,10}));
        Assert.assertEquals(3, findSpecialInteger(new int[]{1,2,3,3}));
    }

    public static int maxNumberOfBalloons(String text) {
        int b = 0;
        int a = 0;
        int l = 0;
        int o = 0;
        int n = 0;

        for(int i = 0 ; i < text.length() ; i++){
            if(text.charAt(i) == 'b') b++;
            if(text.charAt(i) == 'a') a++;
            if(text.charAt(i) == 'l') l++;
            if(text.charAt(i) == 'o') o++;
            if(text.charAt(i) == 'n') n++;

            }
        l = l / 2;
        o = o / 2;

        return Math.min(Math.min(o, l), Math.min(Math.min(b, a), n));

    }

    @Test
    public void maxNumberOfBalloonsTest(){
        Assert.assertEquals(1, maxNumberOfBalloons("nlaebolko"));
        Assert.assertEquals(2, maxNumberOfBalloons("loonbalxballpoon"));
        Assert.assertEquals(0, maxNumberOfBalloons("leetcode"));
    }

    public int maxPower(String s) {
        char currentCharacter = s.charAt(0);
        int count = 1;
        int max = 1;
        for(int i = 1; i < s.length(); i++){
            if(currentCharacter == s.charAt(i)){
                count++;
            }
            else{
                currentCharacter = s.charAt(i);
                count = 1;
            }
            if(count > max){
                max = count;
            }
        }
        return max;
    }

    @Test
    public void maxPowerTest(){
        Assert.assertEquals(2, maxPower("leetcode"));
        Assert.assertEquals(5, maxPower("abbcccddddeeeeedcba"));
        Assert.assertEquals(5, maxPower("triplepillooooow"));
    }

    // https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }

        List<Integer> numsList = new ArrayList<>();
        for(int x : nums){
            numsList.add(x);
        }
        Collections.sort(numsList);
        int count = 1;
        int max = 0;
        int current = numsList.get(0);
        int maxItem = 0;

        for(int i = 1; i < numsList.size() ; i++){
            if(numsList.get(i) == current){
                count++;
            }
            else{
                current = numsList.get(i);
                count = 1;
            }
            if(count > max){
                max = count;
                maxItem = numsList.get(i);
            }
        }
        return maxItem;
    }

    @Test
    public void majorityElementTest(){
        Assert.assertEquals(3, majorityElement(new int[]{3,2,3}));
        Assert.assertEquals(2, majorityElement(new int[]{2,2,1,1,1,2,2}));
    }

    // https://leetcode.com/problems/three-consecutive-odds/
    public boolean threeConsecutiveOdds(int[] arr) {
        for(int i = 0; i < arr.length - 2; i++){
            if(arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0){
                return true;
            }
        }
        return false;
    }

    @Test
    public void threeConsecutiveOddsTest() {
        Assert.assertFalse(threeConsecutiveOdds(new int[]{2, 6, 4, 1}));
        Assert.assertTrue(threeConsecutiveOdds(new int[]{1, 2, 34, 3, 4, 5, 7, 23, 12}));
    }

    public static int numSplits(String s) {
        //TODO Make this work
        int count = 0;

        HashSet<Character> aHashSet = new HashSet<>();
        HashSet<Character> bHashSet = new HashSet<>();

        for (int i = 1; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                bHashSet.add(s.charAt(j));
            }
            for (int k = i - 1; k >= 0; k--) {
                aHashSet.add(s.charAt(k));
            }
            if (aHashSet.size() == bHashSet.size()) {
                count++;
            }
            aHashSet.clear();
            bHashSet.clear();
        }
        return count;
    }

    @Test
    public void numSplitsTest() {
        Assert.assertEquals(2, numSplits("aacaba"));
        Assert.assertEquals(1, numSplits("abcd"));
        Assert.assertEquals(4, numSplits("aaaaa"));
    }

    // https://leetcode.com/problems/unique-number-of-occurrences/
    public static boolean uniqueOccurrences(int[] arr) {

        HashSet<Integer> counts = new HashSet<>();
        Arrays.sort(arr);
        int temp = arr[0];
        counts.add(arr[0]);
        int occurrences = 1;
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] == temp){
                occurrences++;
            }
            else{
                if(!counts.add(occurrences)){
                    return false;
                }
                temp = arr[i];
                occurrences = 1;
            }
        }
        return true;
    }

    @Test
    public void uniqueOccurrencesTest(){
        Assert.assertTrue(uniqueOccurrences(new int[]{1,2,2,1,1,3}));
        Assert.assertFalse(uniqueOccurrences(new int[]{1, 2}));
        Assert.assertTrue(uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();

            if(root == null){
                return result;
            }

            result.add(root.val);
            if(root.left != null){
                result.addAll(preorderTraversal(root.left));
            }
            if(root.right != null){
                result.addAll(preorderTraversal(root.right));
            }

            return result;
        }

    @Test
    public void preorderTraversalTest(){
        List<Integer> expected = new ArrayList<>();
        expected.add(1); expected.add(2); expected.add(3);

        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Assert.assertEquals(expected, preorderTraversal(root));
    }

    // https://leetcode.com/problems/maximum-69-number/
    public static int maximum69Number (int num) {
        String numString = String.valueOf(num);
        numString = numString.replaceFirst("6", "9");
        return Integer.parseInt(numString);
    }

    @Test
    public void maximum69NumberTest(){
        Assert.assertEquals(9969, maximum69Number(9669));
        Assert.assertEquals(9999, maximum69Number(9999));
        Assert.assertEquals(9999, maximum69Number(9996));
    }


    // https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if(n % 2 == 0){
            sb.append("a".repeat(Math.max(0, n - 1)));
            sb.append('z');
        }
        else {
            sb.append("a".repeat(Math.max(0, n)));
        }
        return sb.toString();
    }

    // https://leetcode.com/problems/create-target-array-in-the-given-order/
    public static int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> numsList = new ArrayList<>();
        for(int i = 0 ; i < nums.length; i++){
            numsList.add(index[i], nums[i]);
        }
        for (int i = 0; i < index.length; i++){
            index[i] = numsList.get(i);
        }
        return index;
    }

    @Test
    public void createTargetArrayTest(){
        Assert.assertArrayEquals(new int[]{0,4,1,3,2}, createTargetArray(new int[]{0,1,2,3,4},new int[]{0,1,2,2,1}));
    }

    // https://leetcode.com/problems/squares-of-a-sorted-array/submissions/
    public static int[] sortedSquares(int[] A) {

        for(int i = 0 ; i < A.length ; i++){
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    @Test
    public void sortedSquaresTest(){
        Assert.assertArrayEquals(new int[]{0,1,9,16,100}, sortedSquares(new int[]{-4,-1,0,3,10}));
    }



    public static void main(String[] args) {

    }


}

