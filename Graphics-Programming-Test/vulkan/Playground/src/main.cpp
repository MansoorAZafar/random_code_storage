#include "app.hpp"
#include <stdexcept>
#include <iostream>

int main()
{
  App app = App();

  try {
    app.run();
  }
  catch (const std::exception& e) {
    std::cerr << e.what() << "\n";
    return EXIT_FAILURE;
  }

  return EXIT_SUCCESS;
}
