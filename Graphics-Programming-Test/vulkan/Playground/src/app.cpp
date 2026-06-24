#include "app.hpp"

void App::initWindow() {
  if (!glfwInit()) {
    throw std::runtime_error("Failed to init glfw\n");
  }

  glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
  glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

  this->window = glfwCreateWindow(
    this->width,
    this->height,
    "Vulkan",
    nullptr,
    nullptr
  );
}

void App::createInstance() {
  VkApplicationInfo appInfo{};
  appInfo.sType = VK_STRUCTURE_TYPE_APPLICATION_INFO;
  appInfo.pApplicationName = "App";
  appInfo.applicationVersion = VK_MAKE_VERSION(1, 0, 0);
  appInfo.pEngineName = "No Engine";
  appInfo.apiVersion = VK_API_VERSION_1_0;

  VkInstanceCreateInfo createInfo{};
  createInfo.sType = VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO;
  createInfo.pApplicationInfo = &appInfo;

  uint32_t glfwExtensionCount = 0;
  const char** glfwExtensions;

  glfwExtensions = glfwGetRequiredInstanceExtensions(&glfwExtensionCount);

  createInfo.enabledLayerCount = glfwExtensionCount;
  createInfo.ppEnabledExtensionNames = glfwExtensions;

  createInfo.enabledLayerCount = 0;
  VkResult result = vkCreateInstance(&createInfo, nullptr, &this->instance);
  if (result != VK_SUCCESS) {
    throw std::runtime_error("Failed to create vulkan instance");
  }
}

void App::initVulkan() {
  this->createInstance();
}


void App::mainLoop() {
  while (!glfwWindowShouldClose(this->window)) {
    glfwPollEvents();
  }
}


void App::cleanup() {
  if (this->window) {
    glfwDestroyWindow(this->window);
    this->window = nullptr;
  }
  vkDestroyInstance(this->instance, nullptr);
  glfwTerminate();
}


void App::run() {
  this->initWindow();
  this->initVulkan();
  this->mainLoop();
}


App::~App() {
  this->cleanup();
}
