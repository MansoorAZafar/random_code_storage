public String altPairs(String str) 
{
  StringBuilder res = new StringBuilder();
  for(int i = 0; i < str.length(); ++i)
  {
    res.append(str.charAt(i));
    if(i % 2 == 0) continue;
    i += 2;
  }
  return res.toString();
}
