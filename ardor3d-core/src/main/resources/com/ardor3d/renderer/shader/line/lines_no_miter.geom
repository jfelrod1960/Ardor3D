#version 330 core

// Handles converting lines or line segments to triangle strips.
// portions inspired by https://github.com/paulhoux/Cinder-Samples/tree/master/GeometryShader

uniform float	lineWidth;
uniform vec2	viewSize;
uniform vec2	viewOffset;

layout(lines) in;
layout(triangle_strip, max_vertices=4) out;

in VertexData{
	#ifdef FLAT_COLORS
	flat vec4 color;
	#else
	vec4 color;
	#endif
} VertexIn[2];

out VertexData{
	#ifdef FLAT_COLORS
	flat vec4 color;
	#else
	vec4 color;
	#endif
	
	vec2 uv0;
} VertexOut;


vec3 toScreenSpace(vec4 clipSpacePos)
{
	vec3 ndcSpacePos = (clipSpacePos.xyz / clipSpacePos.w);
	vec2 windowSpacePos = ((ndcSpacePos.xy + 1.0) / (2.0 * sign(clipSpacePos.w))) * viewSize + viewOffset;
	return vec3(windowSpacePos, ndcSpacePos.z);
}

vec4 toNDCSpace(vec3 point, vec2 offset) {
    return vec4((2.0 * (point.xy + offset - viewOffset) / viewSize) - 1.0, point.z, 1.0);
}

void main()
{
	// ignore lines that are behind us.
	if (gl_in[0].gl_Position.w <= 0 &&
		gl_in[1].gl_Position.w <= 0) return;
		
	// convert the vertices passed to the shader to screen space:
	vec3 p0 = toScreenSpace(gl_in[0].gl_Position);	// start of current segment
	vec3 p1 = toScreenSpace(gl_in[1].gl_Position);	// end of current segment

	// determine the direction of the segment
	vec2 dir = normalize(p1.xy - p0.xy);

	// determine the normal of the segment
	vec2 normal = vec2(-dir.y, dir.x);
	
	// multiply normal by lineWidth to get offset
	vec2 offset = lineWidth * 0.5 * normal;

	// generate the triangle strip
	VertexOut.uv0 = vec2(0, 0);
	VertexOut.color = VertexIn[0].color;
	gl_Position = toNDCSpace(p0, offset);
	EmitVertex();

	VertexOut.uv0 = vec2(0, 1);
	VertexOut.color = VertexIn[0].color;
	gl_Position = toNDCSpace(p0, -offset);
	EmitVertex();

	VertexOut.uv0 = vec2(0, 0);
	VertexOut.color = VertexIn[1].color;
	gl_Position = toNDCSpace(p1, offset);
	EmitVertex();

	VertexOut.uv0 = vec2(0, 1);
	VertexOut.color = VertexIn[1].color;
	gl_Position = toNDCSpace(p1, -offset);
	EmitVertex();

	EndPrimitive();
}