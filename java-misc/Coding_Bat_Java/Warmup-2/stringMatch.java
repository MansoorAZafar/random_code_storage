public int stringMatch(String a, String b) 
{
  int min = a.length() < b.length() ? a.length() : b.length();
  int cnt = 0;
  for(int i = 0; i < min - 1; ++i)
  {
    String str1 = a.substring(i, i+2);
    String str2 = b.substring(i, i+2);
    if(str1.equals(str2))
      ++cnt;
  }
  return cnt;
}
