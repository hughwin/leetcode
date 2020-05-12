import org.junit.Assert;
import org.junit.Test;

public class Solutions {

    // https://leetcode.com/articles/two-sum/

    // Big O(n^2) :(
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0 ; i < nums.length ; i++){
            for (int k = 0; k < nums.length ; k++){
                if (i == k){
                    continue;
                }
                else{
                    if (nums[i] + nums[k] == target){
                        return new int []{i, k};
                    }
                }
            }
        }
        return null;
    }

    @Test
    public void twoSumTest(){
        int[] result =  twoSum(new int[]{2 ,7 , 11, 15}, 9);
        int[] expected = new int[]{0, 1};
        Assert.assertArrayEquals(expected, result);
    }




    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{2 ,7 , 11, 15}, 9));
    }

}
