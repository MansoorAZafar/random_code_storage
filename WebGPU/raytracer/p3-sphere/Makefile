COMPILER=g++
OPTIONS=-Wall -std=c++20

BUILD_TARGET=out
CPP_TARGET=program
TARGET=output.ppm

HEADERS := $(wildcard *.hpp)
SRCS := $(wildcard *.cpp)
OBJS := $(patsubst %.cpp,$(BUILD_TARGET)/%.o,$(SRCS))

all: $(TARGET)

$(TARGET): $(CPP_TARGET)
	./$(BUILD_TARGET)/$< > $@

$(CPP_TARGET): $(OBJS)
	$(COMPILER) $(OPTIONS) $^ -o $(BUILD_TARGET)/$@

$(BUILD_TARGET)/%.o: %.cpp | $(BUILD_TARGET)
	$(COMPILER) $(OPTIONS) -c $< -o $@

$(BUILD_TARGET):
	mkdir -p $@

clean:
	rm -rf $(BUILD_TARGET) $(CPP_TARGET) $(TARGET)

.PHONY: all clean