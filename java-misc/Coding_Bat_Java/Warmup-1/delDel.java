public String delDel(String str) {
  if(str.length() <= 3)
    return str;
  return str.substring(1,4).equals("del") ? str.replace("del", "") : str;
}
