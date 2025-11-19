public String backAround(String str) {
  String lastChar = str.substring(str.length() - 1, str.length());
  return lastChar + str + lastChar;
}
