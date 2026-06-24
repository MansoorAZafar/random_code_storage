#ifndef ME_GL_WINDOW_HPP
#define ME_GL_WINDOW_HPP

#include <glad/glad.h>
#include <GLFW/glfw3.h>

class MeGlWindow {
private:
  void initializeGL();
  void paintGL();
public:
  void show();
  MeGlWindow(void);
  ~MeGlWindow(void);
};

#endif
