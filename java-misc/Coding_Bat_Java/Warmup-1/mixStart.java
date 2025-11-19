public boolean mixStart(String str) {
  if(str.length() <= 2)
    return false;
  return str.substring(0,3).matches(".ix");
}
