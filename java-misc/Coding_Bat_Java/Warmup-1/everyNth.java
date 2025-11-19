public String everyNth(String str, int n) {
  StringBuilder result = new StringBuilder();
  for(int i = 0; i < str.length(); ++i)
  {
    if(i % n == 0)
      result.append(str.charAt(i));
  }
  return result.toString();
}
