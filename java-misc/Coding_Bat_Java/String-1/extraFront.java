public String extraFront(String str) 
{
  return str.length() >= 2 ? str.substring(0,2) 
                           + str.substring(0,2) 
                           + str.substring(0,2)
                           : str.substring(0,str.length()) 
                           + str.substring(0,str.length()) 
                           + str.substring(0,str.length());   
}
