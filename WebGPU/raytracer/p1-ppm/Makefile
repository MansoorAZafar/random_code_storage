COMPILER=g++
OPTIONS=-Wall

CPP_TARGET=program
IMG_TARGET=output.ppm

SRCS := $(wildcard *.cpp)
OBJS := $(SRCS:.cpp=.o)

%.o: %.cpp
	$(COMPILER) $(OPTIONS) -c $< -o $@

all: $(IMG_TARGET)

$(IMG_TARGET): $(CPP_TARGET)
	./$(CPP_TARGET) > $(IMG_TARGET)

$(CPP_TARGET): $(OBJS)
	$(COMPILER) $(OPTIONS) $(OBJS) -o $(CPP_TARGET)

.PHONY:
	all clean

clean:
	rm -rf *.o *.ppm $(CPP_TARGET) $(IMG_TARGET)