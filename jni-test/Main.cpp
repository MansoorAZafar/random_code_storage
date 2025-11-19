#include <iostream>
#include "Main.h"

JNIEXPORT void JNICALL Java_Main_sayHello
  (JNIEnv *, jclass) {
    std::cout << "Hello from C++ !!" << std::endl;
  }