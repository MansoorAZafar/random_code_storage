public boolean endsLy(String str) 
{
  if(str.length() < 2) return false;
  return "ly".equals(str.substring(str.length() - 2, str.length()));
}
