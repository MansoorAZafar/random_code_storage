boolean doubleX(String str) 
{
  if(str.length() <= 1)
    return false;
  int indexs[] = new int[2]; // only need first 2 cases
  int mcount = 0;
  for(int i = 0; i < str.length(); ++i)
  {
    if(str.charAt(i) == 'x' && mcount < indexs.length)
    {
      indexs[mcount] = i;
      ++mcount;
      
    }
  }
  return (indexs[0]+1) == indexs[1]; 
}
