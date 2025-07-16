#include "lve_window.hpp"
namespace lve {
	LveWindow::LveWindow(const int& width, const int& height, const char* windowName)
	: width(width), height(height), windowName(windowName) {
		this->initWindow();
	}

	void LveWindow::initWindow() {
		// initialize glfw library
		glfwInit();


		// tell glfw not to make a openGL context
		glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
		// disable window re-size
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);	

		// 4th param says if want to make Full Screen Window
		// 5th param only relates to an openGL context
		this->window = glfwCreateWindow(width, height, windowName, nullptr, nullptr);
	}

	void LveWindow::render() {}

	void LveWindow::createWindowSurface(VkInstance instance, VkSurfaceKHR* surface) {
		if(glfwCreateWindowSurface(instance, window, nullptr, surface) != VK_SUCCESS) {
			throw std::runtime_error("Failed to create window surface");
		}
	}

	LveWindow::~LveWindow() {
		glfwDestroyWindow(this->window);
		glfwTerminate();
	}


};
