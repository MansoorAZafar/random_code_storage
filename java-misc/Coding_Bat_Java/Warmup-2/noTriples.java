public boolean noTriples(int[] nums) {
  if(nums.length < 3)
    return true;

  int repeat = 0;
  for(int i = 1; i < nums.length; ++i)
  {
    if(nums[i] == nums[i-1])
      ++repeat;
    if(repeat == 3 || (repeat == 2 && nums.length == 3)) // in case the length is 3, we're starting at 0, so we use the condition repeat == 2 && nums.length == 3
      return false;
  }
  return true; 
}
