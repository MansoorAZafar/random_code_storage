CXX = g++
CFLAGS = -std=c++17 -O2 -Wall \
  -IC:/libraries/glfw_precompiled/include \
  -IC:/libraries/glm/include \
  -IC:/libraries/glad/include

LDFLAGS = -LC:/libraries/glfw_precompiled/lib-mingw-w64 \
  -lglfw3 -lopengl32 -lgdi32 -lpthread

# Collect all .cpp files from current dir
CPP_SRCS := $(wildcard *.cpp)

# Manually include glad's C source file
GLAD_SRC = C:/libraries/glad/src/glad.c

SRCS := $(CPP_SRCS) $(GLAD_SRC)
TARGET = cube

all: $(TARGET)

$(TARGET): $(SRCS)
	$(CXX) $(CFLAGS) -o $@ $^ $(LDFLAGS)

clean:
	del /Q $(TARGET).exe

.PHONY: all clean
