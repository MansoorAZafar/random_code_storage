public String frontTimes(String str, int n) {
  StringBuilder result = new StringBuilder();
  if(str.length() < 3)
  {
    for(int i = 0; i < n; ++i)
    {
      result.append(str);
    }
    return result.toString();
  }
  for(int i = 0; i < n; ++i)
  {
    result.append(str.substring(0,3));
  }
  return result.toString();
}
