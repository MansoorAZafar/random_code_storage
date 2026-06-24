#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <glm/vec4.hpp>
#include <glm/mat4x4.hpp>

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <stdexcept>

#define WIDTH 800
#define HEIGHT 600
#define TITLE "LearnOpenGL"
#define BASIC_SHADER_PATH "res/shaders/basic.shader"
#define SHADER_BREAKPOINT "#shader"

struct ShaderProgramStruct { std::string VertexSource; std::string FragmentSource; };
enum class ShaderType { NONE = -1, VERTEX = 0, FRAGMENT = 1 };

static ShaderProgramStruct ParseShader(const std::string& filepath)
{
    std::ifstream stream{ filepath };
    std::string line{};
    std::stringstream ss[2]{};
    ShaderType type = ShaderType::NONE;

    while (getline(stream, line))
    {
        if (line.find(SHADER_BREAKPOINT) != std::string::npos) {
            if (line.find("vertex") != std::string::npos)
                type = ShaderType::VERTEX;
            else if (line.find("fragment") != std::string::npos)
                type = ShaderType::FRAGMENT;
        }
        else {
            ss[static_cast<int>(type)] << line << "\n";
        }
    }

    return { ss[0].str(), ss[1].str() };
}

static unsigned int CompileShader(unsigned int type, const std::string& sourceCode)
{
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
            << (type == GL_VERTEX_SHADER ? "Vertex " : "Fragment ")
            << " Shader\n> "
            << message
            << "\n";

        throw std::runtime_error("Failed to compile shader");
    }

    return id;
}

static unsigned int CreateShader(const std::string& vertexShader, const std::string& fragmentShader)
{
    unsigned int program = glCreateProgram();
    unsigned int vs = CompileShader(GL_VERTEX_SHADER, vertexShader);
    unsigned int fs = CompileShader(GL_FRAGMENT_SHADER, fragmentShader);

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


void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
    glViewport(0, 0, width, height);
}

void processInput(GLFWwindow* window)
{
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
        glfwSetWindowShouldClose(window, true);
}

int main()
{

    if (!glfwInit())
    {
        std::cerr << "Failed t initialize glfw\n";
        return -1;
    };

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
    if (window == NULL)
    {
        std::cerr << "Failed to create GLFW window\n";
        glfwTerminate();
        return -1;
    }
    // This makes the OpenGL context, openGL is init after this
    glfwMakeContextCurrent(window);
    glfwSwapInterval(1);

    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
    {
        std::cerr << "Failed to initialize GLAD\n";
        glfwTerminate();
        return -1;
    }

    glViewport(0, 0, WIDTH, HEIGHT);
    // Allows the triangle to grow w the screen
    glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);

    // Need to define a VAO
    unsigned int VAO;
    glGenVertexArrays(1, &VAO);
    glBindVertexArray(VAO);

    const float positions[6] = {
        -0.5f, -0.5f,
         0.0f, 0.5f,
         0.5f, -0.5f,
    };

    unsigned int buffer;
    glGenBuffers(1, &buffer);
    glBindBuffer(GL_ARRAY_BUFFER, buffer);
    glBufferData(GL_ARRAY_BUFFER, 6 * sizeof(float), positions, GL_STATIC_DRAW);

    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, 2 * sizeof(float), 0);

    // Lines 168 & 169 should be able to be done in threads
    //  - they aren't reliant on other information
    ShaderProgramStruct source = ParseShader(BASIC_SHADER_PATH);
    unsigned int shader = CreateShader(source.VertexSource, source.FragmentSource);
    glUseProgram(shader);

    while (!glfwWindowShouldClose(window))
    {
        processInput(window);

        // glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // Draw 
        glDrawArrays(GL_TRIANGLES, 0, 3);

        glfwSwapBuffers(window);
        glfwPollEvents();
    }
    glDeleteProgram(shader);
    glfwTerminate();

    return 0;
}
