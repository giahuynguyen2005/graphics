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

public class Player extends Entity {

    private static final float RUN_SPEED = 20;
    private static final float TURN_SPEED = 100;
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;

    private static final float TERRAIN_HEIGHT = 0;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;
    private boolean isInAir = false;

    private Physics ballPhysics;
    private boolean shootKeyPressed = false; // Flag to track if the shoot key is pressed

    private double initialVelocityX = 2; // Default initial X velocity
    private double initialVelocityY = 2; // Default initial Y velocity

    private boolean showDialogKeyPressed = false; // Flag to track if the show dialog key is pressed

//    private final float targetHeight = Terrain.getHeight(5.0f,5.0f);
    private final Vector3f targetPosition = new Vector3f(100f, 0f, 100f); // Example target position
    private final float targetRadius = 0.15f; // Target radius

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public void move(Terrain terrain) {
        checkInputs(terrain);

        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
        System.out.println(super.getPosition());
        upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);

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
        // Set the initial state vector (velocity and position) and step size
        InputModule.setStateVector(super.getPosition().x, super.getPosition().z, initialVelocityX, initialVelocityY);
        InputModule.setStepSize(0.1);
        InputModule.setTerrain(new double[]{0.2, 0.1}, new double[]{0, 0, 0.5}, "-0.4*e^(-(x^2 + y^2)/8)+0.36"); // Example values

        // Get the physics object
        ballPhysics = InputModule.getPhysics();

        // Simulate the shot
        double[] nextPos;
        while (true) {
            nextPos = ballPhysics.nextPosition();
            if (nextPos[3] == 0 && nextPos[4] == 0) { // Stop simulation when velocities are zero
                break;
            }
            System.out.println("Ball position: x=" + nextPos[0] + ", y=" + nextPos[1] + ", z=" + nextPos[2]);
            super.setPosition(new Vector3f((float) nextPos[0], (float) nextPos[2], (float) nextPos[1]));
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

        // Toggle typing mode with 'T' key
        if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
            if (!showDialogKeyPressed) {
                showDialogKeyPressed = true;
                showVelocityInputDialog();
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

    private void showVelocityInputDialog() {
        // Create a new thread for the Swing dialog to avoid blocking the main game loop
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTextField velocityXField = new JTextField(Double.toString(initialVelocityX), 5);
                JTextField velocityYField = new JTextField(Double.toString(initialVelocityY), 5);

                JPanel inputPanel = new JPanel();
                inputPanel.add(new JLabel("Velocity X:"));
                inputPanel.add(velocityXField);
                inputPanel.add(Box.createHorizontalStrut(15)); // a spacer
                inputPanel.add(new JLabel("Velocity Y:"));
                inputPanel.add(velocityYField);

                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Velocities", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        initialVelocityX = Double.parseDouble(velocityXField.getText());
                        initialVelocityY = Double.parseDouble(velocityYField.getText());
                        System.out.println("Set velocities: X=" + initialVelocityX + ", Y=" + initialVelocityY);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format.");
                    }
                }
            }
        });
    }

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
