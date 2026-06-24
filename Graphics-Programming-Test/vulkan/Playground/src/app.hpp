#ifndef APP_HPP
#define APP_HPP

#define GLFW_INCLUDE_VULKAN
#include <GLFW/glfw3.h>

#include <stdexcept>
#include <iostream>

class App {
private:
  const unsigned int width = 800;
  const unsigned int height = 600;
  GLFWwindow* window;
  VkInstance instance;

  void createInstance();
  void initWindow();
  void initVulkan();
  void mainLoop();
  void cleanup();

public:
  void run();
  ~App();
};

#endif
