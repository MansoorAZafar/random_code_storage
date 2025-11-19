public boolean arrayFront9(int[] nums) {
  int cnt = 0;
  for(int i : nums)
  {
    if(cnt >= 4)
      return false;
    if(i == 9)
      return true;
    ++cnt;
  }
  return false;
}
