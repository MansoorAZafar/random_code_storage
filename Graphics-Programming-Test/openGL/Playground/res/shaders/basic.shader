#shader vertex
#version 330 core

layout(location = 0) in vec4 position;
layout(location = 1) in vec4 vertexColor;
layout(location = 2) in vec2 aTexCoord;

layout(location = 1) out vec4 theColor;
layout(location = 2) out vec2 TexCoord;

void main()
{ 
  gl_Position = position; 
  theColor = vertexColor; 
  TexCoord = aTexCoord;
}

#shader fragment
#version 330 core

layout(location = 0) out vec4 color;
layout(location = 1) in vec4 theColor;
layout(location = 2) in vec2 TexCoord;

uniform sampler2D texture1;
uniform sampler2D texture2;
// uniform vec4 theColor;
void main()
{ 
  // color = texture(ourTexture, TexCoord) * theColor;
  color = mix(texture(texture1, TexCoord), texture(texture2, TexCoord), 0.2);
}
