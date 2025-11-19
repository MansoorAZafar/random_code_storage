public boolean stringE(String str) {
  int count = 0;
  for(Character c : str.toCharArray())
  {
    if(c == 'e')
      ++count;
  }
  return count > 0 && count <= 3 ;
}
