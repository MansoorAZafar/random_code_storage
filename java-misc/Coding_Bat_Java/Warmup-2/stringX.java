public String stringX(String str) {
  if(str.length() < 2)
    return str;

  StringBuilder s = new StringBuilder();
  s.append(str.charAt(0));

  for(int i = 1; i < str.length()-1; ++i)
  {
    if(str.charAt(i) != 'x')
      s.append(str.charAt(i));
  }

  s.append(str.charAt(str.length()-1));
  return s.toString(); 
}
