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