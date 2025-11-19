int countXX(String str) {
  if(str.length() < 1)
    return 0;
  int count = 0;
  char prev = ' ';
  for(char c : str.toCharArray())
  {
    if(c == 'x' && c == prev)
      ++count;
    
    prev = c;
  }
  return count;
}
