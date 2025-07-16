#ifndef APP_HPP
#define APP_HPP

#include "lve_window.hpp"
#include "lve_pipeline.hpp"

namespace lve {
	class App {
	  private:
		LveWindow lveWindow{WIDTH, HEIGHT, APP_NAME};
		LvePipeline lvePipelne{VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH};
	  public:
		static constexpr int WIDTH = 800;
		static constexpr int HEIGHT = 600;
		static constexpr const char* APP_NAME = "Hello Vulkan!";	

		static constexpr const char* VERTEX_SHADER_PATH = "shaders/simple_shader.vert.spv";
		static constexpr const char* FRAGMENT_SHADER_PATH = "shaders/simple_shader.frag.spv";	

		void run();	
	};
};

#endif
