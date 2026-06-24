CXX = g++
CFLAGS = -std=c++17 -O2 -Wall \
  -IC:/libraries/glfw-3.4/include \
  -IC:/libraries/glad/include \
  -IC:/libraries/glm \
  -IC:/libraries/stb_image

LDFLAGS = -LC:/libraries/glfw-3.4/lib-mingw-w64 \
  -LC:/libraries/glm \
  -lglfw3 -lopengl32 -lgdi32 -lpthread

# Collect all .cpp files from current dir
CPP_SRCS := $(wildcard *.cpp)
GLAD_SRC = C:/libraries/glad/src/glad.c
STB_IMAGE_SRC = C:/libraries/stb_image/stb_image.cpp


SRCS := $(CPP_SRCS) $(GLAD_SRC) $(STB_IMAGE_SRC)
TARGET = playground

all: $(TARGET)

$(TARGET): $(SRCS)
	$(CXX) $(CFLAGS) -o $@ $^ $(LDFLAGS)

clean:
	rm $(TARGET).exe

.PHONY: all clean
