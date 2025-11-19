public boolean hasBad(String str)
{
  if(str.length() < 3) return false;
  if(str.length() < 4) return "bad".equals(str.substring(0,3));
  return "bad".equals(str.substring(0,3)) || "bad".equals(str.substring(1,4));
}
