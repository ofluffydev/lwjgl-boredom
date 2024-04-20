public class Camera {
    private float x, y, z; // Position
    private float pitch, yaw, roll; // Rotation

    /**
     * Create a new camera.
     */
    public Camera() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        pitch = 0.0f;
        yaw = 0.0f;
        roll = 0.0f;
    }

    /*
     * Move the camera.
     */
    public void move(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    /*
     * Rotate the camera.
     */
    public void rotate(float dPitch, float dYaw, float dRoll) {
        pitch += dPitch;
        yaw += dYaw;
        roll += dRoll;
    }

    /*
     * Get the camera's x position.
     */
    public float getX() {
        return x;
    }

    /*
     * Set the camera's x position.
     */
    public void setX(float x) {
        this.x = x;
    }

    /*
     * Get the camera's y position.
     */
    public float getY() {
        return y;
    }

    /*
     * Set the camera's y position.
     */
    public void setY(float y) {
        this.y = y;
    }

    /*
     * Get the camera's z position.
     */
    public float getZ() {
        return z;
    }

    /*
     * Set the camera's z position.
     */
    public void setZ(float z) {
        this.z = z;
    }

    /*
     * Get the camera's pitch.
     */
    public float getPitch() {
        return pitch;
    }

    /*
     * Set the camera's pitch.
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /*
     * Get the camera's yaw.
     */
    public float getYaw() {
        return yaw;
    }

    /*
     * Set the camera's yaw.
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /*
     * Get the camera's roll.
     */
    public float getRoll() {
        return roll;
    }

    /*
     * Set the camera's roll.
     */
    public void setRoll(float roll) {
        this.roll = roll;
    }
}