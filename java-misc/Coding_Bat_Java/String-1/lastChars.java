public String lastChars(String a, String b) 
{
  return a.isEmpty() && b.isEmpty() ? "@@" : 
         a.isEmpty() ? "@" + b.charAt(b.length()-1) : b.isEmpty() ? a.charAt(0) + "@" 
         : a.substring(0,1) + b.substring(b.length() - 1, b.length());
}
