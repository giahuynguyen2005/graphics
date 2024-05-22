package physics;

import java.util.Arrays;

import physics.equations.Terrain;

public class additionalEqs {
    static Terrain terrain;
    static double[] initialPos;
    static double[] targetPos;
    static double stepSize;
    static Physics newPhysics;

    /**
     * xORy = 1 for x, xORy = 2 for y
     */
    public static double[] getValue(double vels[], int xORy) {
        double x0 = initialPos[0];
        double y0 = initialPos[1];
        double x1 = targetPos[0];
        double y1 = targetPos[1];
        newPhysics.reset(vels, initialPos);
        double temp = 0;
        double[] sumOfVels = new double[4];

        switch (xORy) {
            case 1:
                temp = (x1 - x0)/stepSize;
                sumOfVels = newPhysics.sumOfVels();
                return new double[] {sumOfVels[0] - temp, sumOfVels[2], sumOfVels[3]};
            case 2:
                temp = (y1 - y0)/stepSize;
                sumOfVels = newPhysics.sumOfVels();
                return new double[] {sumOfVels[1] - temp, sumOfVels[2], sumOfVels[3]};
            default:
                return sumOfVels;
        }

    }

    public static double[] objFunction(double[] vels) {
        double[] f1 = getValue(vels, 1);
        double[] f2 = getValue(vels, 2);

        return new double[] {0.5*(f1[0]*f1[0]+f2[0]*f2[0]), f1[1], f1[2], f1[0], f2[0]};
    }

    public static double[] constructGradient(double[] vels, double h, double F1, double F2) {
        double vx = vels[0]; double vy = vels[1];
        double[][] jacobian = constructJacobian(vx, vy, h);
        double[] gradient = new double[2];
        gradient[0] = jacobian[0][0]*F1 + jacobian[1][0]*F2;
        gradient[1] = jacobian[0][1]*F1 + jacobian[1][1]*F2;
        return gradient;
    }

    /**
     * function = 1 for x-function, function = 2 for y-function
     * variable = 1 for x, variable = 2 for y
     */
    public static double partialDeriv(double vx, double vy, int function, int variable, double h) {
        if (variable == 1) {
            return (getValue(new double[] {vx+h, vy}, function)[0] - getValue(new double[] {vx-h, vy}, function)[0])/(2*h);
        } else {
            return (getValue(new double[] {vx, vy+h}, function)[0] - getValue(new double[] {vx, vy-h}, function)[0])/(2*h);
        }
    }

    public static double[][] constructJacobian(double vx, double vy, double h) {
        double[][] jacobian = new double[2][2];
        jacobian[0][0] = partialDeriv(vx, vy, 1, 1, h);
        jacobian[0][1] = partialDeriv(vx, vy, 1, 2, h);
        jacobian[1][0] = partialDeriv(vx, vy, 2, 1, h);
        jacobian[1][1] = partialDeriv(vx, vy, 2, 2, h);
        return jacobian;
    }

    public static double[] findSolution(double[][] jacobian, double[] F) {
        double[][] tempJacobian = Arrays.copyOf(jacobian, jacobian.length);
        double[] tempF = Arrays.copyOf(F, F.length);

        double temp = tempJacobian[0][0];
        tempJacobian[0][0] = 1;
        tempJacobian[0][1] = tempJacobian[0][1]/temp;
        tempF[0] = tempF[0]/temp;
        temp = tempJacobian[1][0];
        tempJacobian[1][0] = 0;
        tempJacobian[1][1] = tempJacobian[1][1] - tempJacobian[0][1]*temp;
        tempF[1] = tempF[1] - tempF[0]*temp;
        tempF[1] = tempF[1]/tempJacobian[1][1];
        tempF[0] = tempF[0] - tempJacobian[0][1]*tempF[1];

        return new double[] {tempF[0], tempF[1]};
    }
}
