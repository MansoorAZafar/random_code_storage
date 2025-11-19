public class Main {
  static {
    System.loadLibrary("native");
  }
  
  public static void main(String[] args) {
    Main.sayHello();
  }

  private static native void sayHello();
}