COMPILER=g++
OPTIONS=-Wall -std=c++20

BUILD_TARGET=out
CPP_TARGET=program
TARGET=output.ppm


SRCS := $(wildcard *.cpp)
HEADERS := $(wildcard *.hpp)
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
	rm -rf $(BUILD_TARGET) $(TARGET) $(CPP_TARGET)

.PHONY: all clean