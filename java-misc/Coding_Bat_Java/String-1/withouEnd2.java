public String withouEnd2(String str) 
{
  if(str.length() <= 1) return "";
  return str.substring(1,str.length() - 1);  
}
