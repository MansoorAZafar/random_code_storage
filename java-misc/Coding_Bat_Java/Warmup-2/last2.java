public int last2(String str) {
  if(str.length() <= 3)
    return 0;
  String key = str.substring(str.length() - 2, str.length());
  int cnt = 0;
  for(int i = 0; i < str.length() - 2; ++i)
    if(str.substring(i, i+2).equals(key))
      ++cnt;
  return cnt;
}
