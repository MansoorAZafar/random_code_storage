#include "shader.hpp"

Playground::Shader::ShaderProgramSource Playground::Shader::ParseShader(const std::string& filepath) {
  std::ifstream stream{ filepath };
  std::string line{};
  std::stringstream ss[2]{};
  ShaderType type = ShaderType::NONE;

  while (getline(stream, line)) {
    if (line.find(SHADER_BREAKPOINT) != std::string::npos) {
      if (line.find(VERTEX_SHADER) != std::string::npos)
        type = ShaderType::VERTEX;
      else if (line.find(FRAGMENT_SHADER) != std::string::npos)
        type = ShaderType::FRAGMENT;
    }
    else {
      ss[static_cast<int>(type)] << line << "\n";
    }
  }

  return { ss[0].str(), ss[1].str() };
}

unsigned int Playground::Shader::CompileShader(unsigned int type, const std::string& sourceCode) {
  unsigned int id = glCreateShader(type);
  const char* src = sourceCode.c_str();

  glShaderSource(id, 1, &src, nullptr);
  glCompileShader(id);

  int result;
  glGetShaderiv(id, GL_COMPILE_STATUS, &result);

  if (result == GL_FALSE) {
    int length;
    glGetShaderiv(id, GL_INFO_LOG_LENGTH, &length);

    char* message = (char*)alloca(length * sizeof(char));
    glGetShaderInfoLog(id, length, &length, message);

    std::cerr << "Failed to compile "
      << (type == GL_VERTEX_SHADER ? "Vertex" : "Fragment")
      << " Shader\n"
      << message
      << "\n";

    throw std::runtime_error("Failed to compile shader");
  }

  return id;
}

unsigned int Playground::Shader::CreateShader() {
  unsigned int program = glCreateProgram();
  unsigned int vs = this->CompileShader(GL_VERTEX_SHADER, this->source.VertexSource);
  unsigned int fs = this->CompileShader(GL_FRAGMENT_SHADER, this->source.FragmentSource);

  glAttachShader(program, vs);
  glAttachShader(program, fs);

  glLinkProgram(program);
  glValidateProgram(program);

  glDetachShader(program, vs);
  glDetachShader(program, fs);

  glDeleteShader(vs);
  glDeleteShader(fs);

  return program;
}


Playground::Shader::Shader(const char* shaderPath) {
  this->source = this->ParseShader(shaderPath);
}
