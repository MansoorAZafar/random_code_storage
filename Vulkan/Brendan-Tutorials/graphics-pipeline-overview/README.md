## Setup

1. Install Vulkan 

> https://vulkan.lunarg.com/sdk/home
- Install the windows SDK Installer 
- run it 

2. Install Make for Windows
> https://gnuwin32.sourceforge.net/packages/make.htm
- Install the "Complete package, except sources" Setup
- run it 

3. Download GLM 
> https://github.com/g-truc/glm/releases/tag/1.0.1 
- Download the glm light.zip
- extract it 

4. Download GLFW 
> https://www.glfw.org/download
- Download the source package 
- extract it 

5. Setup Makefile 
```make

CXX=g++
CFLAGS=-std=c++17 -O2 \
  -IC:/libraries/glfw_precompiled/include \
  -IC:/libraries/glm/include \
  -IC:/libraries/vulkan/include

LDFLAGS = -LC:/libraries/glfw_precompiled/lib-mingw-w64 \
  -LC:/libraries/vulkan/Lib \
  -lglfw3 -lvulkan-1 -lgdi32 -lpthread
  
  
VulkanTest: *.cpp *.hpp
	$(CXX) $(CFLAGS) -o VulkanTest *.cpp $(LDFLAGS)

# Other targets
.PHONY: test clean

test: VulkanTest
	VulkanTest.exe

clean:
	del /Q VulkanTest.exe
	
```

6. Run make test