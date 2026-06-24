#ifndef PLAYGROUND_SHADER_HPP
#define PLAYGROUND_SHADER_HPP

#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <stdexcept>

#include <glad/glad.h>

#define SHADER_BREAKPOINT "#shader"
#define VERTEX_SHADER "vertex"
#define FRAGMENT_SHADER "fragment"

namespace Playground {
  class Shader {
  private:
    enum class ShaderType { NONE = -1, VERTEX = 0, FRAGMENT = 1 };

    struct ShaderProgramSource {
      std::string VertexSource;
      std::string FragmentSource;
    };

    ShaderProgramSource source;

    ShaderProgramSource ParseShader(const std::string& filepath);
    unsigned int CompileShader(unsigned int type, const std::string& sourceCode);
  public:
    Shader(const char* shaderPath);
    unsigned int CreateShader();
  };
};

#endif
