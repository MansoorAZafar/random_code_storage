public String stringBits(String str) {
  StringBuilder result = new StringBuilder();
  int cnt = 0;
  for(char ch : str.toCharArray())
  {
    if(cnt % 2 == 0)
      result.append(ch);
    ++cnt;
  }
  return result.toString();
}
