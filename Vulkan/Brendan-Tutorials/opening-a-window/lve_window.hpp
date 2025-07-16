// lve_window.hpp
#ifndef LVE_WINDOW_HPP
#define LVE_WINDOW_HPP

#define GLFW_INCLUDE_VULKAN
#include <GLFW/glfw3.h> // can use it to open a window w/o worrying about running OS
#include <string>


namespace lve {
	class LveWindow {
	  private:
		void initWindow();
		const int width;
		const int height;

		const char* windowName;
		GLFWwindow* window;		
	
	  public:
		LveWindow(const int& width, const int& height, const char* windowName);
		~LveWindow();
		inline bool shouldClose() { return glfwWindowShouldClose(this->window); }
		void render();

		LveWindow(const LveWindow&) = delete;
		LveWindow& operator=(const LveWindow&) = delete;

	};
};

#endif
