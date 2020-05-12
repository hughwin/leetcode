import org.junit.Assert;
import org.junit.Test;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solutions {

    // https://leetcode.com/articles/two-sum/

    // Big O(n^2) :(
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0 ; i < nums.length ; i++){
            for (int k = 0; k < nums.length ; k++){
                if (i != k){
                    if (nums[i] + nums[k] == target){
                        return new int []{i, k};
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

    public static void main(String[] args) {

    }

}
