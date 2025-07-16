// The first thing we do is specify the version, 450 refers to glsl 4.5
#version 450

// vec2 is built in
// use any values that are in the range of {-1, 1}
// don't hardcode values
//   - usually values are passed from a vertex buffer
vec2 positions[3] = vec2[] (
	vec2(0.0, -0.5),
	vec2(0.5, 0.5),
	vec2(-0.5, 0.5)
); 
// To see what this'll look like, see the img call 6 val triangle ex

void main() {
	// executed once, for each vertex we have
	// gl_Position acts as our output
	//       - it is a built in output variable
	//
	//  - a 4 dimensional vector that maps to our output buffer frame image
	//  - center is 0,0, see img below
	// gl_VertexIndex (think of it as a static val ig?)
	//  - it contains the vertex Index for each time our main function is ran
	
	// 1st arg is the positions, the next arg is the z value
	//  ranges from {0, 1}
	// 3rd argument is the value some pipelines devide by
	//    - diving by 1 = no change (obvi)
	// 
	//  - gl_position is turned into a normalized device coordinate
	//    - it divides its whole vector list by that value
	
	
	gl_Position = vec4(positions[gl_VertexIndex], 0.0, 1.0);
}

