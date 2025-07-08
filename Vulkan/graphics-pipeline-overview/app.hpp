#ifndef APP_HPP
#define APP_HPP

#include "lve_window.hpp"

namespace lve {
	class App {
	  private:
		LveWindow lveWindow{WIDTH, HEIGHT, APP_NAME};
	  public:
		static constexpr int WIDTH = 800;
		static constexpr int HEIGHT = 600;
		static constexpr const char* APP_NAME = "Hello Vulkan!";	
		
		void run();	
	};
};

#endif
