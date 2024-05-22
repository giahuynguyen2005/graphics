package physics;

import java.util.ArrayList;
import java.util.Arrays;

import physics.equations.*;

/**
 * This class is used to solve differential equations using Runge-Kutta method.
 * Don't forget to set the equation and parameters using chooseEquation method (check the description of the method).
 * Also set the step size using setStepSize method. Default step size is 0.1.
 */
public class Runge_Kutta{
    double[] variables;
    double[] params;
    equationsInterface eq;
    Acceleration acc;
    Acceleration1 acc1;
    double[] inits;
    double stepSize;
    double t;

    public Runge_Kutta(double kFriction, double stepSize, Terrain terrain) {
        t = 0.0;
        this.stepSize = stepSize;
        acc = new Acceleration(kFriction, stepSize, terrain);
        acc1 = new Acceleration1(kFriction, stepSize, terrain);
    }

    public void chooseEquation(int choice, double[] params) {
        t = 0;
        switch(choice) {
            // Exponent
            case 1:
                variables = new double[] {params[0]};
                eq = new Exponent(params[1]);
                break;
            // Lorenz system
            case 2:
                variables = new double[] {params[0], params[1], params[2]};
                eq = new LorenzSystem(params[3], params[4], params[5]);
                break;
            // Acceleration for the case when the velocity is non-zero
            case 3:
                variables = Arrays.copyOf(params, 4);
                eq = acc;
                acc.setMU(params[4]);
                break;
            // Acceleration for the case when the velocity is zero
            case 4:
                variables = Arrays.copyOf(params, 4);
                eq = acc1;
                acc1.setMU(params[4]);
                break;
        }
        inits = Arrays.copyOf(variables, variables.length);
    }

    /**
     * This method sets the step size. Also sets variables to default values.
     * @param stepSize
     */
    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
        variables = inits;
    }

    /**
     * This method estimates the next point on t+h using Runge-Kutta method.
     * @return an array of the next points, in the same order as the variables come.
     */
    int n = 2;
    double k10; double k11;
    double k20; double k21;
    double k30; double k31;
    double k40; double k41;
    double[] nextPoint = new double[n+2];
    public double[] estimateNextPoint() {
        double[] temp = eq.solve(variables);
        k10 = temp[0]; k11 = temp[1];

        inits = Arrays.copyOf(variables, variables.length);

        t += stepSize/2;
        variables[2] = inits[2]+(stepSize/2)*inits[0];
        variables[3] = inits[3]+(stepSize/2)*inits[1];
        variables[0] = inits[0] + (stepSize/2)*k10;
        variables[1] = inits[1] + (stepSize/2)*k11;
  
        temp = eq.solve(variables);
        k20 = temp[0]; k21 = temp[1];

        variables[0] = inits[0] + (stepSize/2)*k20;
        variables[1] = inits[1] + (stepSize/2)*k21;

        temp = eq.solve(variables);
        k30 = temp[0]; k31 = temp[1];

        t += stepSize/2;
        variables[2] = inits[2]+(stepSize)*inits[0];
        variables[3] = inits[3]+(stepSize)*inits[1];
        variables[0] = inits[0] + stepSize*k30;
        variables[1] = inits[1] + stepSize*k31;

        temp = eq.solve(variables);
        k40 = temp[0]; k41 = temp[1];

        nextPoint[0] = inits[0] + (stepSize/6)*(k10 + 2*k20 + 2*k30 + k40);
        nextPoint[1] = inits[1] + (stepSize/6)*(k11 + 2*k21 + 2*k31 + k41);

        if (Math.abs(nextPoint[0]) < 1 && Math.abs(nextPoint[0]-inits[0]) <= 1e-2) {
            nextPoint[0] = 0;
        }
        if (Math.abs(nextPoint[1]) < 1 && Math.abs(nextPoint[1]-inits[1]) <= 1e-2) {
            nextPoint[1] = 0;
        }

        if (Math.signum(nextPoint[0]) != Math.signum(inits[0])) {
            nextPoint[0] = 0;
        }
        if (Math.signum(nextPoint[1]) != Math.signum(inits[1])) {
            nextPoint[1] = 0;
        }

        nextPoint[2] = variables[2];
        nextPoint[3] = variables[3];
        return nextPoint;
    }

    /**
     * This method returns an interval of estimated points from start to end.
     * Keep in mind that the interval must be divisible by the step size.
     * @param start double value of the start of the interval (value of t)
     * @param end double value of the end of the interval (value of t)
     * @return a 2D array of the estimated points. The first dimension is the number of points, the second dimension is the number of variables.
     */
    public ArrayList<ArrayList<Double>> getInterval(double start, double end) {
        double n = Math.round((end-start)/stepSize)-1;
        if (start > end) {
            throw new IllegalArgumentException("Start must be smaller than end.");
        }
        if (stepSize > end-start) {
            throw new IllegalArgumentException("Step size must be smaller than the interval.");
        }
        // if (Math.abs((end-start)/stepSize - Math.round((end-start)/stepSize)) != 0) {
        //     System.out.println((end-start)/stepSize);
        //     throw new IllegalArgumentException("The interval must be divisible by the step size.");
        // }
        double[][] interval = new double[(int)(n+1)][variables.length];
        for (int i = 0; i < interval.length; i++) {
            double[] temp = estimateNextPoint();
            interval[i] = Arrays.copyOf(temp, temp.length);
            variables = Arrays.copyOf(temp, temp.length);
        }
        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i<variables.length+1; i++) {
            ArrayList<Double> temp = new ArrayList<Double>();
            if (i == 0) {
                for (int j = 0; j<interval.length; j++) {
                    temp.add((double)(j+1)*stepSize);
                }
            } else {
                for (int j = 0; j<interval.length; j++) {
                    temp.add(interval[j][i-1]);
                }
            }
            result.add(temp);
        }
        return result;
    }

    // public static void main(String[] args) {
    //     Runge_Kutta rk = new Runge_Kutta();
    //     rk.chooseEquation(2, new double[] {0.1,0.5,0.2,10,28,2.66}, 0.0, 0.01);
    //     ArrayList<ArrayList<Double>> interval = rk.getInterval(0, 0.5);
    //     for (int i = 0; i<interval.size(); i++) {
    //         for (int j = 0; j<interval.get(i).size(); j++) {
    //             System.out.print(interval.get(i).get(j) + " ");
    //         }
    //         System.out.println();
    //     }
    // }
}
