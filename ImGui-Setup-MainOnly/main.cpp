// Add ImGUI libraries
// Add ImGui before GLAD & GLFW
#include "../Imgui/imgui.h"
#include "../Imgui/imgui_impl_opengl3.h"
#include "../Imgui/imgui_impl_glfw.h"

// Add GLAD and GLFW
// Add Glad Before GLFW
#include <glad/glad.h>
#include "GLFW/glfw3.h"

// Others
#include <iostream>
#include <string>

// Define glfw & glad init's
#define WINW 520
#define WINH 800
#define WINDOW_X_COORD 0
#define WINDOW_Y_COORD 0
#define DARK_GRAY 0.1f, 0.1f, 0.1f, 1.0f
#define BUFFER_SIZE 50

void createLoginForm(char*, char*); 

int main() {
	
	glfwInit();

	//glfwWindowHint(): Specify Configurations for future window operations
	// Specify the Version
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

	// Specifies the 'version', aka which profile of openGL we expose to GLAD
	// GLAD we specified CORE
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

	// Style points
	glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, GL_TRUE);
	
	// - Prevents Resizeability 
	// - Removes Top Bar, (Minimize, X ...)
	//glfwWindowHint(GLFW_DECORATED, GL_FALSE);

	//with floating it puts your window on top of everything (you can't click through it with this
	//glfwWindowHint(GLFW_FLOATING, GLFW_TRUE);


	//Creating the Window
	GLFWwindow* window = glfwCreateWindow(WINW, WINH, "Cal PvP", NULL, NULL);
	if (!window) {
		std::cout << "[FAIL]: Could not Initialize Window!\n";
		glfwDestroyWindow(0);
		return -1;
	}

	//GLFW, openGL, GLAD setup
	// Set the 'focus'/Context window
	//	- All openGL commands will effect this window
	glfwMakeContextCurrent(window);

	// Load OpenGL Functions Dynamically
	gladLoadGL();

	// Area of the window, we want openGL to render on
	// Covers entire surface of the window
	glViewport(WINDOW_X_COORD, WINDOW_Y_COORD, WINW, WINH);


	//ImGUI init
	// Ensures compatability with application
	//	-> Compares the one here with the one the application is being compiled with
	IMGUI_CHECKVERSION();

	// Required for managing State settings, styles ...
	ImGui::CreateContext();

	// Gets reference for the ImGUI IO structure
	//	- Contains input and output functions & data
	ImGuiIO& io = ImGui::GetIO();
	// (Optional) 
	// - Removes warning from compiler
	// - "Good Practice"
	(void)io;

	// Sets the default look nd feel to a dark style
	ImGui::StyleColorsDark();

	// initializes ImGUI to be used with GLFW & OpenGL
	//	- Also gives ImGUI the window it'll work with
	//		- Also sets up necessary bindings so ImGUI can work with
	//		  GLFW and OpenGL
	ImGui_ImplGlfw_InitForOpenGL(window, true);

	// Initializes ImGUI to be used for OpenGL version 3.30
	//	- Sets up ImGUI's rendering system to work with the specificed
	//    version of OpenGL
	//  - Configures its own settings based on OpenGL's version
	ImGui_ImplOpenGL3_Init("#version 330");

	//Initialize Holder Variables
	char username[BUFFER_SIZE]{};
	char password[BUFFER_SIZE]{};

	std::string email{};
	std::string pass{};
	
	bool loggedIn{0};

	// Main while loop
	// - while window is open
	while (!glfwWindowShouldClose(window)) {
		// Set the color of the background essentially 
		//glClearColor(DARK_GRAY);
		
		// Clear old frames contents
		glClear(GL_COLOR_BUFFER_BIT);

		// Create ImGUI's Frames
		//	- Prepares ImGui to handle input and Render the GUI correctly
		//    using GLFW & OpenGL
		ImGui_ImplOpenGL3_NewFrame();
		ImGui_ImplGlfw_NewFrame();
		ImGui::NewFrame();

		//Always Render ImGUI **AFTER** GLFW or OpenGL
		// Put ImGui in the middle of the screen rather to any side
		//ImGui::SetNextWindowPosCenter(ImGuiCond_Once | ImGuiCond_Appearing;
		ImGui::SetNextWindowSize({WINW, WINH});
		// Tell ImGui what to draw in this 'scope'
		// - Things inside here are called Draw Data	
		ImGui::Begin("Login", NULL, ImGuiWindowFlags_NoResize 
			| ImGuiWindowFlags_NoCollapse | ImGuiWindowFlags_AlwaysAutoResize );

		if (!loggedIn) {
			createLoginForm(username, password);
		}
		else {
			ImGui::Text("Logged In!");
		}

		ImGui::End();

		// Doesn't 'render' (lmao), Completes the frame
		// after you give it what to draw
		//	- Prepares the draw data for rendering
		ImGui::Render();
		// Uses OpenGL to render the 'draw data'
		//	- The data was 'prepared' by Render()
		ImGui_ImplOpenGL3_RenderDrawData(ImGui::GetDrawData());

		/*
		* 1. Front Buffer
		*	- Frame that is CURRENTLY being shown on a window
		*
		* 2. Back Buffer
		*	- Frame that is being drawn on
		*	- The next frame that will be displayed after swapping
		*
		* - You switch the buffers to see the change
		* - Switches from the Front -> Back buffer
		*	-> Ensures no flickering and smooth swaps
		*/
		glfwSwapBuffers(window);

		// GLFW now handles all windows events
		glfwPollEvents();
	}

	// Clean-up
	// Clean-up ImGUI
	ImGui_ImplOpenGL3_Shutdown();
	ImGui_ImplGlfw_Shutdown();
	ImGui::DestroyContext();

	// Clean-up GLFW
	glfwDestroyWindow(window);
	glfwTerminate();

	return 0;
}

void createLoginForm(char* username, char* password) {
	ImGui::Text("Input Username >");
	ImGui::InputText("##usernameinput", username, BUFFER_SIZE);

	ImGui::Text("Input Password >");
	ImGui::InputText("##passwordinput", password, BUFFER_SIZE, ImGuiInputTextFlags_Password);

	ImGui::Button("Login");
}