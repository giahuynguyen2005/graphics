package physics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputModuleGUI extends JFrame {
    private JTextField stateVectorXField;
    private JTextField stateVectorYField;
    private JTextField stateVectorVXField;
    private JTextField stateVectorVYField;
    private JTextField stepSizeField;
    private JTextField frictionStaticField;
    private JTextField frictionKineticField;
    private JTextField targetXField;
    private JTextField targetYField;
    private JTextField targetRadiusField;
    private JTextField functionField;
    private JTextField sandField;
    private JCheckBox waterCheckBox;
    private JButton submitButton;

    public InputModuleGUI() {
        setTitle("Input Module");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(14, 2));

        mainPanel.add(new JLabel("State Vector (x, y, vX, vY):"));
        stateVectorXField = new JTextField();
        stateVectorYField = new JTextField();
        stateVectorVXField = new JTextField();
        stateVectorVYField = new JTextField();
        mainPanel.add(stateVectorXField);
        mainPanel.add(stateVectorYField);
        mainPanel.add(stateVectorVXField);
        mainPanel.add(stateVectorVYField);

        mainPanel.add(new JLabel("Step Size:"));
        stepSizeField = new JTextField();
        mainPanel.add(stepSizeField);

        mainPanel.add(new JLabel("Friction (Static, Kinetic):"));
        frictionStaticField = new JTextField();
        frictionKineticField = new JTextField();
        mainPanel.add(frictionStaticField);
        mainPanel.add(frictionKineticField);

        mainPanel.add(new JLabel("Target (x, y, Radius):"));
        targetXField = new JTextField();
        targetYField = new JTextField();
        targetRadiusField = new JTextField();
        mainPanel.add(targetXField);
        mainPanel.add(targetYField);
        mainPanel.add(targetRadiusField);

        mainPanel.add(new JLabel("Function (f(x,y)):"));
        functionField = new JTextField();
        mainPanel.add(functionField);

        mainPanel.add(new JLabel("Sand (x-start, y-start, x-end, y-end):"));
        sandField = new JTextField();
        mainPanel.add(sandField);

        mainPanel.add(new JLabel("Water On:"));
        waterCheckBox = new JCheckBox();
        mainPanel.add(waterCheckBox);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        mainPanel.add(submitButton);

        add(mainPanel);
    }

    private void handleSubmit() {
        double stateVectorX = Double.parseDouble(stateVectorXField.getText());
        double stateVectorY = Double.parseDouble(stateVectorYField.getText());
        double stateVectorVX = Double.parseDouble(stateVectorVXField.getText());
        double stateVectorVY = Double.parseDouble(stateVectorVYField.getText());
        double stepSize = Double.parseDouble(stepSizeField.getText());
        double frictionStatic = Double.parseDouble(frictionStaticField.getText());
        double frictionKinetic = Double.parseDouble(frictionKineticField.getText());
        double targetX = Double.parseDouble(targetXField.getText());
        double targetY = Double.parseDouble(targetYField.getText());
        double targetRadius = Double.parseDouble(targetRadiusField.getText());
        String function = functionField.getText();
        boolean waterOn = waterCheckBox.isSelected();
        
        String[] sandCoordinates = sandField.getText().split(",");
        double[][] sand = new double[sandCoordinates.length / 4][4];
        for (int i = 0; i < sandCoordinates.length; i += 4) {
            sand[i / 4] = new double[]{
                Double.parseDouble(sandCoordinates[i]),
                Double.parseDouble(sandCoordinates[i + 1]),
                Double.parseDouble(sandCoordinates[i + 2]),
                Double.parseDouble(sandCoordinates[i + 3])
            };
        }

        InputModule.setStateVector(stateVectorX, stateVectorY, stateVectorVX, stateVectorVY);
        InputModule.setStepSize(stepSize);
        InputModule.setTerrain(new double[]{frictionStatic, frictionKinetic}, new double[]{targetX, targetY, targetRadius}, function);
        InputModule.setSand(sand, new double[]{frictionStatic, frictionKinetic});
        InputModule.setWater(waterOn);

        Physics physics = InputModule.getPhysics();
        // You can now use the Physics object for further simulations
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InputModuleGUI().setVisible(true);
            }
        });
    }
}
