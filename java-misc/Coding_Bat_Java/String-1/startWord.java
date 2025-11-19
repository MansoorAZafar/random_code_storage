public String startWord(String str, String word) 
{ 
  if(str.length() < 1 || str.length() < word.length()) return "";
  return str.substring(1, word.length()).equals(word.substring(1,word.length())) 
  ? str.substring(0,1) + word.substring(1,word.length()) :""; 
}
