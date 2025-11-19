public int intMax(int a, int b, int c) {
  int temporary = Math.max(a,b); // hold the maximum for these 2 numbers
  return Math.max(temporary, c);
}
