package physics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ODE_GUI extends JFrame {

    private JTextField paramField1, paramField2, paramField3,paramField4;
    private JTextField initCondField1, initCondField2;
    private JTextField stepSizeField, integrationTimeField;
    private JComboBox<String> solverComboBox;
    private JButton startButton;

    ODE_GUI() {
        setTitle("ODE solver GUI");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit the program when the window is closed
        setLayout(new GridLayout(3, 5));

        // parameters input section
        add(new JLabel("Parameters:"));

        paramField1 = new JTextField();
        paramField2 = new JTextField();
        paramField3 = new JTextField();
        paramField4 = new JTextField();

        paramField1.setText("parameter 1");
        paramField2.setText("parameter 2");
        paramField3.setText("parameter 3");
        paramField4.setText("parameter 4");
        paramField1.setSize(2, 2);
        paramField2.setSize(2, 2);
        paramField3.setSize(2, 2);
        paramField4.setSize(2, 2);

        add(paramField1);
        add(paramField2);
        add(paramField3);
        add(paramField4);

        // initial conditions input section
        add(new JLabel("Initial conditions:"));
        initCondField1 = new JTextField();
        initCondField2 = new JTextField();


        initCondField1.setText("wait for input");
        initCondField2.setText("wait for input");

        initCondField1.setSize(2, 2);
        initCondField2.setSize(2, 2);

        add(initCondField1);
        add(initCondField2);


        // step size input section
        add(new JLabel("Step size :"));
        stepSizeField = new JTextField();
        String[] stepSizes = { "0.1", "0.01", "0.001" };
        solverComboBox = new JComboBox<>(stepSizes);
        add(solverComboBox);

        // integration time input section
        add(new JLabel("Integration time:"));
        integrationTimeField = new JTextField();
        add(integrationTimeField);

        // solver selection section
        add(new JLabel("Select ODE Solver:"));

        String[] solvers = { "Euler", "Runge-Kutta" };
        solverComboBox = new JComboBox<>(solvers);
        add(solverComboBox);

        // start button
        startButton = new JButton("Start Simulation!");
        add(startButton);

        // action listener
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // separate method that can call the function to start the solve ODE simulation

            }
        });

        setVisible(true);
    }



    public static void main(String[] args) {

        new ODE_GUI();

    }
}