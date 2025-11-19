public String startOz(String str) {
  if(str.length() <= 1 || (str.substring(0,2).matches("[oz]") ))
    return str;
  String result = "";
  if(str.substring(0,1).equals("o"))
    result+=str.substring(0,1);
  if(str.substring(1,2).equals("z"))
    result+=str.substring(1,2);
  return result;
}
