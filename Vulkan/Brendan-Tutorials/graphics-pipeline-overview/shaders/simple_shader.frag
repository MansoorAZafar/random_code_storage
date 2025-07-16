#version 450

// there is no built in output-variable for a .frag so we 
// need to declare it outseleves
//  - after the vec4, the name outColor is the name of our variable
//
//  1. Layout qualifier takes a location value
//     - a fragment shader can output to multiple different locations
//     - for this simple one, we're only using location 0
//
//  2. the out qualifier says it'll be used as output
//	3. the variables type is vec4 and name
//
layout(location = 0) out vec4 outColor;

void main() {
	// we need to assign the output variable a value
	// each component is the rgb alpha channel for a vec4 color
	
	outColor = vec4(1.0, 0.0, 0.0, 1.0);
	
	// red at max value
	// no green
	// no blue
	// fully opaque
	
	// fragment shader runs on a per-fragment basis
	//  - this is determined by which pixels our geometry mostly contains
	//    during the rasterization stage
}