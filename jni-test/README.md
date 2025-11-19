# Steps To Setup from nothing
#### This assumes you are using powershell
##### Setting up for the others isn't that different

1. Create a Java File
```bash
code Main.java
```

2. use a static constructor to load and name your library
```java
public class Main {
  static {
    System.loadLibrary("native");
    // This is the libraries name
    // You can name it anything
  }
}
```

3. Add a native function to call and the caller
```java
public class Main {
  // Load the library
  static {
    System.loadLibrary("native");
    // This is the libraries name
  }

  public static void main(String[] args) {
    // This is the caller, it can be anything
    Main.sayHello(); 
    // This is calling the native function
  }

  // Doesn't have to be static
  // -> if not
  // just do
  // new Main().sayHello(); or regular initialization
  public static native void sayHello();
}
```

4. Generate the header (.h) file for the java file
```bash
javac -h . [YOUR_JAVA_FILE].java # This will generate a [filename].h 
```

5. Create a cpp to holder the native function's definition 
```cpp
#include <iostream>
#include "Main.h" // this is the header file that was generated from step 4

JNIEXPORT void JNICALL Java_Main_sayHello
  (JNIEnv *, jclass) {
    std::cout << "Hello from C++ !!" << std::endl;
  }

```

6. Build the cpp file
```bash
g++ -c -I"$env:JAVA_HOME\include" -I"$env:JAVA_HOME\include\win32" Main.cpp -o Main.o
```

7. Link the actual file
```bash
g++ --% -shared -o native.dll Main.o -Wl,--add-stdcall-alias
```

8. Run the file

```bash
java -Djava.library.path="." -cp . Main
```

Finished
