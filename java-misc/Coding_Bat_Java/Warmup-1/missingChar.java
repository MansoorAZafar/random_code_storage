public String missingChar(String str, int n) {
  String result = "";
  for(short i = 0; i < str.length(); ++i)
  {
    if(i != n)
    {
      result += str.substring(i, i+1);
    }
  } 
  return result;
}
//Or you can do:
//String front = str.substring(0,n);
//String back = str.substring(n+1,str.length());
//return front + back;
