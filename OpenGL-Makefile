# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -g -Iheaders

# File organization
SRC := $(wildcard *.c) $(wildcard src/*.c)
OBJ := $(patsubst %.c,build/%.o,$(SRC))
DEPS := $(OBJ:.o=.d)

# Output binary
TARGET = app

# Default target
all: $(TARGET)

# Link final executable
$(TARGET): $(OBJ)
	$(CC) $(CFLAGS) -o $@ $^

# Ensure build/ exists before compiling
build/%.o: %.c
	@mkdir -p $(dir $@)
	$(CC) $(CFLAGS) -MMD -c $< -o $@

# Include dependency files if they exist
-include $(DEPS)

# Clean up
clean:
	rm -rf build $(TARGET)


