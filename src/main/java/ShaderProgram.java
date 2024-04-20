import org.lwjgl.opengl.GL20;

public class ShaderProgram {
    public int programId, vertexShaderId, fragmentShaderId;

    public ShaderProgram() {
    }

    /**
     * This method is responsible for creating and compiling a vertex shader in OpenGL.
     *
     * @param shaderCode The path to the shader file
     */
    public void attachVertexShader(String shaderCode) {
        /*
         Create the vertex shader using the shader code using OpenGL through LWJGL.
         The argument GL20.GL_VERTEX_SHADER specifies that this is a vertex shader.
         THe function returns an integer ID for the newly created shader.
        */
        vertexShaderId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        /*
         Attach the source code of a vertex shader to the shader object.
         This takes in the ID of the shader object, and the source code of the shader.
         The shaderCode is a string that contains the source code of the shader which
         defines the behavior of the vertex shader.
        */
        GL20.glShaderSource(vertexShaderId, shaderCode);

        /*
         Compile the shader object.
         This takes in the ID of the shader object.
         This function compiles the shader source code attached to the shader object.
        */
        GL20.glCompileShader(vertexShaderId);

        /*
         Check if the shader was compiled successfully.
         This function returns an integer value that represents the compiler status of the shader.
         If the shader was compiled successfully, the value will be GL20.GL_TRUE.
         If the shader was not compiled successfully, the value will be GL20.GL_FALSE.
        */
        if (GL20.glGetShaderi(vertexShaderId, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            /*
             If the shader was not compiled successfully, throw a runtime exception.
             The exception message contains the error message from the shader compilation.
            */
            throw new RuntimeException("Error creating vertex shader\n" + GL20.glGetShaderInfoLog(vertexShaderId));
        }
    }

    /**
     * Create and compile a fragment shader in OpenGL.
     *
     * @param shaderCode The path to the shader file
     */
    public void attachFragmentShader(String shaderCode) {
        /*
         Create the fragment shader using the shader code using OpenGL through LWJGL.
         The argument GL20.GL_FRAGMENT_SHADER specifies that this is a fragment shader.
         THe function returns an integer ID for the newly created shader.
        */
        fragmentShaderId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

        /*
         Attach the source code of a fragment shader to the shader object.
         This takes in the ID of the shader object, and the source code of the shader.
         The shaderCode is a string that contains the source code of the shader which
         defines the behavior of the fragment shader.
        */
        GL20.glShaderSource(fragmentShaderId, shaderCode);

        /*
         Compile the shader object.
         This takes in the ID of the shader object.
         This function compiles the shader source code attached to the shader object.
        */
        GL20.glCompileShader(fragmentShaderId);

        /*
         Check if the shader was compiled successfully.
         This function returns an integer value that represents the compiler status of the shader.
         If the shader was compiled successfully, the value will be GL20.GL_TRUE.
         If the shader was not compiled successfully, the value will be GL20.GL_FALSE.
        */
        if (GL20.glGetShaderi(fragmentShaderId, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            /*
             If the shader was not compiled successfully, throw a runtime exception.
             The exception message contains the error message from the shader compilation.
            */
            throw new RuntimeException("Error creating fragment shader\n" + GL20.glGetShaderInfoLog(fragmentShaderId));
        }
    }

    /**
     * Link the vertex and fragment shaders to the shader program.
     */
    public void link() {
        /*
         Create a new shader program.
         This function returns an integer ID for the newly created shader program.
        */
        programId = GL20.glCreateProgram();

        /*
         Attach the vertex shader to the shader program.
         This takes in the ID of the shader program, and the ID of the vertex shader.
        */
        GL20.glAttachShader(programId, vertexShaderId);

        /*
         Attach the fragment shader to the shader program.
         This takes in the ID of the shader program, and the ID of the fragment shader.
        */
        GL20.glAttachShader(programId, fragmentShaderId);

        /*
         Link the shader program.
         This takes in the ID of the shader program.
         This function links the vertex and fragment shaders to the shader program.
        */
        GL20.glLinkProgram(programId);

        /*
         Check if the shader program was linked successfully.
         This function returns an integer value that represents the link status of the shader program.
         If the shader program was linked successfully, the value will be GL20.GL_TRUE.
         If the shader program was not linked successfully, the value will be GL20.GL_FALSE.
        */
        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) {
            /*
             If the shader program was not linked successfully, throw a runtime exception.
             The exception message contains the error message from the shader program linking.
            */
            throw new RuntimeException("Error linking shader program\n" + GL20.glGetProgramInfoLog(programId));
        }
    }

    /**
     * Bind the shader program.
     */
    public void bind() {
        /*
         Use the shader program.
         This takes in the ID of the shader program.
         This function tells OpenGL to use the shader program for rendering.
        */
        GL20.glUseProgram(programId);
    }

    /**
     * Unbind the shader program.
     */
    public void unbind() {
        /*
         Stop using the shader program.
         This function tells OpenGL to stop using the shader program for rendering.
        */
        GL20.glUseProgram(0);
    }

    /**
     * Cleanup the shader program.
     */
    public void cleanup() {
        /*
         Detach the vertex shader from the shader program.
         This takes in the ID of the shader program, and the ID of the vertex shader.
        */
        GL20.glDetachShader(programId, vertexShaderId);

        /*
         Detach the fragment shader from the shader program.
         This takes in the ID of the shader program, and the ID of the fragment shader.
        */
        GL20.glDetachShader(programId, fragmentShaderId);

        /*
         Delete the vertex shader.
         This takes in the ID of the vertex shader.
         This function deletes the vertex shader object.
        */
        GL20.glDeleteShader(vertexShaderId);

        /*
         Delete the fragment shader.
         This takes in the ID of the fragment shader.
         This function deletes the fragment shader object.
        */
        GL20.glDeleteShader(fragmentShaderId);

        /*
         Delete the shader program.
         This takes in the ID of the shader program.
         This function deletes the shader program object.
        */
        GL20.glDeleteProgram(programId);
    }

    public int getId() {
        return programId;
    }

    public void delete() {
        GL20.glDeleteProgram(programId);
    }
}
