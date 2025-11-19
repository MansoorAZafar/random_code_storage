public String seeColor(String str) 
{
  return str.length() < 3 ? "" : 
         "red".equals(str.substring(0,3)) ? "red" : 
         str.length() < 4 ? "" : 
         "blue".equals(str.substring(0,4)) ? "blue" : "";
}
