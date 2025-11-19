public int array667(int[] nums) {
  int cnt = 0;
  for(int i = 0; i < nums.length - 1; ++i)
  {
    if(nums[i] == 6 && (nums[i+1] == 6 || nums[i+1] == 7) )
      ++cnt;
  }
  return cnt;
}
//Or you could directly make 2 pointers
public int array667(int[] nums) 
{
  if(nums.length <= 1) return 0;
  int j = 1, cnt = 0;  
  for(int i = 0; i < nums.length - 1; ++i, ++j)
  {
    if(nums[i] == 6 && nums[j] == 6 || nums[j] == 7) ++cnt;
  }
  return cnt;
}

