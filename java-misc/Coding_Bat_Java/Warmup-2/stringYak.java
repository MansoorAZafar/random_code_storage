public String stringYak(String str) 
{
  return str.replaceAll("y.k", ""); // or you could do : str.replaceAll("y[a-z]k", "");
}
