public boolean in3050(int a, int b) {
  boolean in30 = (a >= 30 && a <= 40) && (b >= 30 && b <= 40);
  boolean in50 = (a >= 40 && a <= 50) && (b >= 40 && b <= 50);
  return in30 || in50;
}
