public int max1020(int a, int b) {
  int max = Math.max(a,b);
  int min = Math.min(a,b);
  if((max > 20 || max < 10) && (min > 20 || min < 10))
    return 0; // not in range
  if(max <= 20 && max >= 10)
    return max;
  return min;
}
