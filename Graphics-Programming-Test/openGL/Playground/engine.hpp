#ifndef PLAYGROUND_ENGINE_HPP
#define PLAYGROUND_ENGINE_HPP
#include "shader.hpp"
#include "imageLoader.hpp"

#include <glad/glad.h>
#include <GLFW/glfw3.h>

#include <string>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <cstring>
#include <stdexcept>
#include <vector>
#include <glm/trigonometric.hpp>


#define _GLFW_SWAP_INTERVAL_VSYNC 1
#define SHADER_BREAKPOINT "#shader"
#define VERTEX_SHADER "vertex"
#define FRAGMENT_SHADER "fragment"
#define BASIC_SHADER_PATH "res/shaders/basic.shader"
#define SINGLE_BUFFER 1

namespace Playground
{

  class Engine
  {
  private:
    std::vector<unsigned int> textureIDs{};
    GLFWwindow* window{};
    unsigned int program{};
    unsigned int VAO;
    unsigned int vertexBufferID;
    unsigned int indexBufferID;
    unsigned int width;
    unsigned int height;
    const char* title;

    static void framebuffer_size_callback(GLFWwindow* window, int width, int height);

    void paint(const unsigned int& size, const unsigned int* stride);
    unsigned int  loadTexture(const char* IMAGE, unsigned int filter = GL_REPEAT);
    void processInput();
  public:
    Engine(const unsigned int& width, const unsigned int& height, const char* title);

    void init();
    void draw(
      const float* positions,
      const unsigned int positionSize,
      const unsigned short* indices,
      const unsigned int indicesSize,
      const unsigned int nVertices,
      const std::vector<const char*>& images,
      const unsigned int nElements = 5
    );

    ~Engine();
  };

}

#endif
