public boolean has271(int[] nums) 
{
  if(nums.length < 3) return false;
  for(int i = 0; i < nums.length - 2; ++i)
  {
    int j = i+1;
    int k = i+2;
    if(nums[j] == nums[i]+5 && Math.abs(nums[k] - (nums[i]-1)) <= 2) return true;
  }
  return false;
}
