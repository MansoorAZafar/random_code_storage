public String deFront(String str) 
{    
  if(str.length() < 1 || (str.length() == 1) && "a".equals(str)) return str;
  if(str.length() >= 2 && "ab".equals(str.substring(0,2))) return str;
  
  return "a".equals(str.substring(0,1)) ? "a" + str.substring(2,str.length()) 
         : "b".equals(str.substring(1,2)) ? "b" + str.substring(2,str.length()) 
         : str.substring(2,str.length());
}
