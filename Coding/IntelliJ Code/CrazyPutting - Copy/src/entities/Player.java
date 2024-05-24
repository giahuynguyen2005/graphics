package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import physics.InputModule;
import physics.Physics;
import renderEngine.DisplayManager;
import terrain.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    private static final float RUN_SPEED = 20;
    private static final float TURN_SPEED = 100;
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;

    private static final float TERRAIN_HEIGHT = 0;
    private Terrain terrain;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;
    private boolean isInAir = false;

    private Physics ballPhysics;
    private boolean shootKeyPressed = false; // Flag to track if the shoot key is pressed

    private double initialVelocityX = 1; // Default initial X velocity
    private double initialVelocityY = 1; // Default initial Y velocity
    private double strength = 1;

    private boolean showDialogKeyPressed = false; // Flag to track if the show dialog key is pressed

    private Camera camera;

//    private final float targetHeight = Terrain.getHeight(5.0f,5.0f);
    private final Vector3f targetPosition = new Vector3f(100f, 0f, 100f); // Example target position
    private final float targetRadius = 0.15f; // Target radius

    private List<Vector3f> shotPositions = new ArrayList<>(); // Store positions during the shot simulation
    private int positionIndex = 0; // Index to track current position

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, Terrain terrain) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.terrain = terrain;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void move() {
        checkInputs(terrain);

        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
//        System.out.println(super.getPosition());
        upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
//        System.out.println(super.getPosition());

        float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);

        if (super.getPosition().y < terrainHeight) {
            upwardsSpeed = 0;
            isInAir = false;
            super.getPosition().y = terrainHeight;
        }

        // Check if the ball is in the target area
        if (isInTargetArea()) {
            endGame();
        }
    }

    private void shoot() {
        if (camera == null) {
            System.out.println("Camera not set!");
            return;
        }
        // Calculate the shooting direction based on the camera's orientation
        float angle = camera.getYaw();
        Vector3f direction = new Vector3f((float) Math.sin(Math.toRadians(angle)), 0, (float) -Math.cos(Math.toRadians(angle)));

        // Calculate the initial velocities based on direction and strength
        initialVelocityX = direction.x * strength;
        initialVelocityY = direction.z * strength;

        // Set the initial state vector (velocity and position) and step size
        InputModule.setStateVector(super.getPosition().x, super.getPosition().z, initialVelocityX, initialVelocityY);

        // The Smaller, The Smoother
        InputModule.setStepSize(0.05);

        // Initialize the Terrain in the Physics Engine
        InputModule.setTerrain(new double[]{0.2, 0.1}, new double[]{0, 0, 0.5}, "-0.4*e^(-(x^2 + y^2)/8)+0.36"); // Example values

        // Get the physics object
        ballPhysics = InputModule.getPhysics();

        // Simulate the shot
        shotPositions.clear();
        positionIndex = 0;

        double[] nextPos;
        while (true) {
            nextPos = ballPhysics.nextPosition();

            float terrainMinX = terrain.getX();
            float terrainMaxX = terrain.getX() + Terrain.getSize();
            float terrainMinZ = terrain.getZ();
            float terrainMaxZ = terrain.getZ() + Terrain.getSize();

            if (nextPos[0] < terrainMinX) {
                nextPos[0] = terrainMinX;
            } else if (nextPos[0] > terrainMaxX) {
                nextPos[0] = terrainMaxX;
            }
            if (nextPos[1] < terrainMinZ) {
                nextPos[1] = terrainMinZ;
            } else if (nextPos[1] > terrainMaxZ) {
                nextPos[1] = terrainMaxZ;
            }

            // Add all intermediate shots into the ArrayList to render out in the main game loop
            shotPositions.add(new Vector3f((float) nextPos[0], (float) nextPos[2], (float) nextPos[1]));
            System.out.println("Ball position: x=" + nextPos[0] + ", y=" + nextPos[1] + ", z=" + nextPos[2]);

            if (nextPos[3] == 0 && nextPos[4] == 0) { // Stop simulation when velocities are zero
                // Initialize the final position to reset the engine
                double[] finalPos = {nextPos[0],nextPos[1]};
                // Reset the engine
                ballPhysics.reset(new double[]{0,0}, finalPos);

                break;
            }
        }
    }

    public void updateBallPosition() {
        // Render out each position
        if (positionIndex < shotPositions.size()) {
            Vector3f nextPosition = shotPositions.get(positionIndex);
            super.setPosition(nextPosition);
            positionIndex++;
        }
    }

    private void jump() {
        if (!isInAir) {
            this.upwardsSpeed = JUMP_POWER;
            isInAir = true;
        }
    }

    private void checkInputs(Terrain terrain) {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            this.currentTurnSpeed = -TURN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            this.currentTurnSpeed = TURN_SPEED;
        } else {
            currentTurnSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump();
        }

        // Toggle typing mode with "T" key
        if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
            if (!showDialogKeyPressed) {
                showDialogKeyPressed = true;
                showStrengthInputDialog();
            }
        } else {
            showDialogKeyPressed = false; // Reset flag when the key is released
        }

        // Add a key for shooting, for example, the 'F' key
        if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            if (!shootKeyPressed) {
                shootKeyPressed = true;
                shoot(); // Call shoot method when the key is pressed
            }
        } else {
            shootKeyPressed = false; // Reset flag when the key is released
        }
    }

    private void showStrengthInputDialog() {
        // Create a new thread for the Swing dialog to avoid blocking the main game loop
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTextField strengthField = new JTextField("1.0", 5); // Default strength

                JPanel inputPanel = new JPanel();
                inputPanel.add(new JLabel("Strength:"));
                inputPanel.add(strengthField);

                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Strength", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        strength = Float.parseFloat(strengthField.getText());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format.");
                    }
                }
            }
        });
    }

    // Checks if the ball is inside the target area
    private boolean isInTargetArea() {
        float ballX = super.getPosition().x;
        float ballY = super.getPosition().z; // Assuming the z-coordinate is used for Y in 2D plane
        float targetX = targetPosition.x;
        float targetY = targetPosition.z;

        float distance = (float) Math.sqrt(Math.pow(ballX - targetX, 2) + Math.pow(ballY - targetY, 2));
        return ((Math.abs(ballX-targetX) <= targetRadius)&&(Math.abs(ballY - targetY) <= targetRadius));
    }

    private void endGame() {
        System.out.println("The ball is in the target area. Game over!");
        // Add any other game-ending logic here
        System.exit(0); // Example: exit the application
    }
}
