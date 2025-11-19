public String withoutX2(String str) 
{
  StringBuilder res = new StringBuilder("");
  for(int i = 0; i < str.length(); ++i)
  {
      if(i < 2 && str.charAt(i) == 'x') continue;
      res.append(str.charAt(i));
  }
  return res.toString();
}

//Can also do:
public String withoutX2(String str) 
{
  return str.length() < 1 ? str : str.length() <= 2 ? str.replaceAll("x", "") 
  : str.substring(0,2).replaceAll("x", "") + str.substring(2,str.length());   
}
