* First, we have to setup Vulkan,
*  - https://vulkan-tutorial.com/Development_environment
*    - I'm using Fedora Linux, so here's what I did
*       
*				1. sudo dnf install vulkan-tools
*         - Installs cli utilities
* 
*       2. sudo dnf install vulkan-loader-devel
*         - Installs Vulkan's loader (looks up functions in the driver at runtime)
*       
*       3. sudo dnf install mesa-vulkan-devel vulkan-validation-layers-devel
*         - Installs standard validation layers, used for debugging 
*
*       4. Now run: vulkaninfo and vkcube to make sure it's installed
*       
*       5. sudo dnf install glfw-devel
*         - Install's GLFW (used to make the window cross platform)
*
*       6. sudo dnf install glm-devel
*         - Install's GLM (linear algbra operations)
*
*       7. sudo dnf install glslc
*         - Install's the shader's compiler
*         - Run glslc and expect the output: glslc: error: no input files
*         - Also run: sudo dnf install libXi-devel libXxf86vm-devel
*
*       8. Setup the MakeFile
*         - Add the Code below into a file and make this cmake file
*
* ==================================
*                CMAKE        
* ==================================
*  
*  CFLAGS = -std=c++17 -O2
*  LDFLAGS = -lglfw -lvulkan -ldl -lpthread -lX11 -lXxf86vm -lXrandr -lXi
*
*  VulkanTest: main.cpp
*      g++ $(CFLAGS) -o VulkanTest main.cpp $(LDFLAGS)
*
*  .PHONY: test clean
*
*  test: VulkanTest
*	  ./VulkanTest
*
*  clean:
*      rm -f VulkanTest
*
*
*
