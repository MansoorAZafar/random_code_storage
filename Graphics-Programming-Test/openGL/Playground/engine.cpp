#include "engine.hpp"

void Playground::Engine::framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
  glViewport(0, 0, width, height);
}

Playground::Engine::Engine(
  const unsigned int& width,
  const unsigned int& height,
  const char* title) : width(width), height(height), title(title)
{
  this->init();
}

void Playground::Engine::init()
{
  if (!glfwInit())
  {
    std::cerr << "Failed to init glfw\n";
    throw std::runtime_error("Couldn't initialize glfw");
  }

  glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
  glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
  glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

  this->window = glfwCreateWindow(this->width, this->height, this->title, NULL, NULL);
  if (!this->window)
  {
    std::cerr << "Failed to init window\n";
    glfwTerminate();
    throw std::runtime_error("Failed to init window\n");
  }

  glfwMakeContextCurrent(this->window);
  glfwSwapInterval(_GLFW_SWAP_INTERVAL_VSYNC);

  if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
  {
    std::cerr << "Failed to init glad\n";
    glfwTerminate();
    throw std::runtime_error("Failed to init glad\n");
  }

  glViewport(0, 0, this->width, this->height);
  glfwSetFramebufferSizeCallback(this->window, framebuffer_size_callback);

  Shader shader{ BASIC_SHADER_PATH };
  this->program = shader.CreateShader();

  glUseProgram(this->program);
}

unsigned int Playground::Engine::loadTexture(const char* IMAGE, unsigned int filter) {
  const unsigned int format = std::strstr(IMAGE, "png") ? GL_RGBA : GL_RGB;

  unsigned int textureID;
  glGenTextures(1, &textureID);
  glActiveTexture((GL_TEXTURE0 + this->textureIDs.size()));
  glBindTexture(GL_TEXTURE_2D, textureID);

  // set the filtering options
  //  - repeat on the x and y axis
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, filter);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, filter);

  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

  ImageLoader imgLoader;
  ImageData imgData = imgLoader.loadTexture(IMAGE);

  const int mipmapLevel = 0;
  if (imgData.data) {
    glTexImage2D(
      GL_TEXTURE_2D,
      mipmapLevel,
      GL_RGB,
      imgData.width,
      imgData.height,
      0,
      format,
      GL_UNSIGNED_BYTE,
      imgData.data
    );
    glGenerateMipmap(GL_TEXTURE_2D);
  }
  else {
    std::cerr << "Failed to load texture\n";
    throw std::runtime_error("Texture could not be loaded");
  }

  return textureID;
}


void Playground::Engine::processInput()
{
  if (glfwGetKey(this->window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
    glfwSetWindowShouldClose(this->window, true);
}

void Playground::Engine::draw(
  const float* positions,
  const unsigned int positionSize,
  const unsigned short* indices,
  const unsigned int indicesSize,
  const unsigned int nVertices,
  const std::vector<const char*>& images,
  const unsigned int nElements
) {

  for (const char* image : images) {
    unsigned int texture;
    if (!strcmp("res/assets/awesomeface.png", image)) {
      texture = this->loadTexture(image);
    }
    else {
      texture = this->loadTexture(image, GL_CLAMP_TO_EDGE);
    }
    // const unsigned int textureID = this->loadTexture(image);
    this->textureIDs.push_back(texture);
  }

  // unsigned int VAO{};
  glGenVertexArrays(1, &this->VAO);
  glBindVertexArray(this->VAO);

  // unsigned int vertexBufferID{};
  glGenBuffers(SINGLE_BUFFER, &this->vertexBufferID);
  glBindBuffer(GL_ARRAY_BUFFER, this->vertexBufferID);
  glBufferData(GL_ARRAY_BUFFER, positionSize, positions, GL_STATIC_DRAW);

  // context about position
  glEnableVertexAttribArray(0);
  glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, sizeof(float) * nElements, 0);

  // context about color
  glEnableVertexAttribArray(1);
  glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, sizeof(float) * nElements, (char*)(sizeof(float) * 2));

  // context about texture
  glEnableVertexAttribArray(2);
  glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, sizeof(float) * nElements, (char*)(sizeof(float) * 5));

  // unsigned int indexBufferID{};
  glGenBuffers(SINGLE_BUFFER, &this->indexBufferID);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this->indexBufferID);
  glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesSize, indices, GL_STATIC_DRAW);

  glUniform1i(glGetUniformLocation(this->program, "texture1"), 0);
  glUniform1i(glGetUniformLocation(this->program, "texture2"), 1);

  this->paint(nVertices, 0);
}

void Playground::Engine::paint(const unsigned int& size, const unsigned int* stride) {
  while (!glfwWindowShouldClose(this->window)) {
    this->processInput();
    glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);

    // Code to make blinking
    // float timeValue = glfwGetTime();
    // float greenValue = glm::sin(timeValue) / 2.0f + 0.5f;
    // int vertexColorLocation = glGetUniformLocation(this->program, "theColor");
    // glUniform4f(vertexColorLocation, 0.0f, greenValue, 0.0f, 1.0f);

    // good practice for later
    glUseProgram(this->program);

    glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_SHORT, stride);

    glfwSwapBuffers(this->window);
    glfwPollEvents();
  }
}

Playground::Engine::~Engine()
{
  glDeleteVertexArrays(1, &this->VAO);

  glDeleteBuffers(1, &this->vertexBufferID);
  glDeleteBuffers(1, &this->indexBufferID);

  // delete all the textures
  for (const unsigned int& textureID : this->textureIDs) {
    glDeleteTextures(1, &textureID);
  }

  glDeleteProgram(this->program);
  glfwTerminate();
}
