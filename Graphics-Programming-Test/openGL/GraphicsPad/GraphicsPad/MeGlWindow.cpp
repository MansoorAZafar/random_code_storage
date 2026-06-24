#include "MeGlWindow.hpp"

/////////////////////////////////////////////////////
//                    PRIVATE                      //
/////////////////////////////////////////////////////

void MeGlWindow::framebuffer_size_callback(GLFWwindow* window, int width, int height) {
	glViewport(0, 0, width, height);
}



void MeGlWindow::initializeGL() {
	if (!glfwInit()) {
		throw std::runtime_error("Failed to init glfw\n");
	}

	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

	this->window = glfwCreateWindow(640,480,"OpenGL",NULL,NULL);
	if (!this->window) {
		throw std::runtime_error("Failed to init window\n");
	}

	glfwMakeContextCurrent(window);
	if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)) {
		throw std::runtime_error("Failed to init glad\n");
	}

	glViewport(0, 0, 640, 480);
	glfwSetFramebufferSizeCallback(this->window, framebuffer_size_callback);
}



void MeGlWindow::initializeBuffers() {
	const float vertices[] = {
		+0.0f, +1.0f,
		-1.0f, -1.0f,
		+1.0f, -1.0f
	};

	unsigned int myContainerID;
	glGenVertexArrays(1, &myContainerID);
	glBindVertexArray(myContainerID);

	unsigned int myBufferID;
	glGenBuffers(1, &myBufferID);
	glBindBuffer(GL_ARRAY_BUFFER, myBufferID);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, sizeof(float) * 2, 0);
}



void MeGlWindow::drawGL() {
	while (!glfwWindowShouldClose(this->window)) {
		glClear(GL_COLOR_BUFFER_BIT);

		this->paintGL();

		glfwSwapBuffers(this->window);
		glfwPollEvents();
	}
}



void MeGlWindow::paintGL() {
	glDrawArrays(GL_TRIANGLES, 0, 3);
}



/////////////////////////////////////////////////////
//                     PUBLIC                      //
/////////////////////////////////////////////////////

void MeGlWindow::show() {
	this->initializeGL();
	this->initializeBuffers();
	this->drawGL();
}



MeGlWindow::~MeGlWindow() {
	if (this->window) {
		glfwDestroyWindow(this->window);
		this->window = nullptr;
	}
	glfwTerminate();
}