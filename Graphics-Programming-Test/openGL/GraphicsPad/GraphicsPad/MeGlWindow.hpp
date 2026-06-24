#ifndef ME_GL_WINDOW_HPP
#define ME_GL_WINDOW_HPP

#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <stdexcept>

class MeGlWindow
{
private:
	GLFWwindow* window;
	
	void initializeGL();
	void initializeBuffers();
	void paintGL();
	void drawGL();

	static void framebuffer_size_callback(GLFWwindow*, int, int);
public:
	void show();
	~MeGlWindow();
};

#endif

