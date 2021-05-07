#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec4 aColor;
layout (location = 2) in vec2 aTexCoordinates;

out vec4 fColor;
out vec2 fTexCoordinates;

void main()
{
    fColor = aColor;
    fTexCoordinates = aTexCoordinates;
    gl_Position = vec4(aPos, 1.0);
}