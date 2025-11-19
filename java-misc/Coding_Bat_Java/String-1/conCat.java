public String conCat(String a, String b) 
{
  if(a.isEmpty() || b.isEmpty()) return a+b;
  return a.charAt(a.length() - 1) == b.charAt(0) 
         ? a.substring(0, a.length() - 1) + b : a + b;
}
