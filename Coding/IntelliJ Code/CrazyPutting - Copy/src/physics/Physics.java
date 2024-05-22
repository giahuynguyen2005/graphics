package physics;

import java.util.Arrays;

import physics.equations.Terrain;

/**
 * Physics class is used to calculate the next position of the ball on the terrain.
 * Initialize everything through the constructor and then use the nextPosition() method to get the next position.
 */
public class Physics implements Cloneable {
    double[] stateVector = new double[4];
    double[] slopes = new double[2];
    double[] coords = new double[3];
    double[] vels = new double[2];
    private double[] input = new double[5];

    double stepSize;
    double t = 0;
    boolean formOfEqs = true;

    public Terrain terrain;
    Runge_Kutta solver;

    /**
     * Initializes all the necessary initial conditions for the ball
     * @param stateVector has a form of {dx (x-velocity), dy (y-velocity), x, y}
     * @param stepSize time step
     */
    public Physics(double[] stateVector, double stepSize, Terrain terrain) {
        this.stateVector = stateVector;
        this.stepSize = stepSize;
        this.vels[0] = stateVector[0];
        this.vels[1] = stateVector[1];
        this.terrain = terrain;

        double x = stateVector[2];
        double y = stateVector[3];
        double vX = stateVector[0];
        double vY = stateVector[1];
        slopes[0] = terrain.slopeX(x, y);
        slopes[1] = terrain.slopeY(x, y);
        solver = new Runge_Kutta(terrain.getMuK(x, y), stepSize, terrain);
        coords[0] = x; coords[1] = y; coords[2] = terrain.height(x, y);
        if ((vX==0) && (vY==0)) {
            solver.chooseEquation(4, new double[] {vX, vY, x, y, terrain.getMuK(x, y)});
            formOfEqs = false;
        } else {
            solver.chooseEquation(3, new double[] {vX, vY, x, y, terrain.getMuK(x, y)});
            formOfEqs = true;
        }
    }

    /**
     * Sets new velocities.
     * @param vels
     */
    public void setVelocities(double[] vels) {
        double vx = vels[0]; double vy = vels[1];
        this.vels[0] = vx;
        this.vels[1] = vy;
        stateVector[0] = vx;
        stateVector[1] = vy;
    }

    /**
     * Sets position and velocities of the ball
     * @param vels {x-velocity, y-velocity}
     * @param pos {x, y}
     */
    public void reset(double[] vels, double[] pos) {
        double x = pos[0]; double y = pos[1];
        double vx = vels[0]; double vy = vels[1];
        this.vels[0] = vx; this.vels[1] = vy;
        stateVector[0] = vx; stateVector[1] = vy;
        stateVector[2] = x; stateVector[3] = y;
        t=0;
        coords[0] = x; coords[1] = y; coords[2] = terrain.height(x, y);
        if ((vels[0]==0) && (vels[1]==0)) {
            solver.chooseEquation(4, new double[] {vx, vy, x, y, terrain.getMuK(x, y)});
            formOfEqs = false;
        } else {
            solver.chooseEquation(3, new double[] {vx, vy, x, y, terrain.getMuK(x, y)});
            formOfEqs = true;
        }
    }

    public void setBallPosition(double[] pos) {
        double x = pos[0]; double y = pos[1];
        stateVector[2] = x;
        stateVector[3] = y;
        coords = new double[] {x, y, terrain.height(x, y)};
        slopes = new double[] {terrain.slopeX(x, y), terrain.slopeY(x, y)};
    }

    public double[] getStateVector() {
        return stateVector;
    }

    public double getStepSize() {
        return stepSize;
    }

    /**
     * The method returns the next state vector using Runge-Kutta solver for the estimation of velocities.
     * @return state vector
     */
    public double[] nextStateVector() {
        double vx = vels[0]; double vy = vels[1];
        double x = coords[0]; double y = coords[1];
        double kFriction = terrain.getMuK(x, y);
        double slX = slopes[0]; double slY = slopes[1];
        t += stepSize;
        if ((vels[0]!=0) || (vels[1]!=0)) {
            formOfEqs = true;
            stateVector[0] = vx; stateVector[1] = vy;
            stateVector[2] = x; stateVector[3] = y;
            input[0] = vx; input[1] = vy;
            input[2] = x; input[3] = y; input[4] = kFriction;
            solver.chooseEquation(3, input);
            return solver.estimateNextPoint();
        } else {
            if (terrain.getMuS(x, y) > Math.sqrt(slX*slX+slY*slY)) {
                return new double[] {0, 0, x, y};
            } else {
                formOfEqs = false;
                stateVector[0] = slX; stateVector[1] = slY;
                stateVector[2] = x; stateVector[3] = y;
                input[0] = slX; input[1] = slY;
                input[2] = x; input[3] = y; input[4] = kFriction;
                solver.chooseEquation(4, input);
                return solver.estimateNextPoint();
            }
        }
    }

    /**
     * The method returns the next position of the ball (and also velocities for testing).
     * Before using this method, initialize the Physics object with the initial conditions.
     * @return next position of the ball (x, y, z, vX, vY)
     */
    public double[] nextPosition() {
        double[] next = nextStateVector();
        stateVector = Arrays.copyOf(next, next.length);
        double vx = next[0]; double vy = next[1];
        double x = next[2]; double y = next[3];
        if (terrain.checkIfPassThroughWall(coords, new double[] {x, y})) {
            return new double[] {coords[0], coords[1], coords[2], 0, 0};
        }
        coords[0] = x;
        coords[1] = y;
        if (terrain.checkWaterOn() && (coords[2] < 0)) {
            stateVector[0] = 0;
            stateVector[1] = 0;
            vels[0] = 0;
            vels[1] = 0;
            return new double[] {x, y, coords[2], 0, 0};
        }
        coords[2] = terrain.height(x, y);
        slopes[0] = terrain.slopeX(x, y);
        slopes[1] = terrain.slopeY(x, y);
        if (formOfEqs) {
            vels[0] = vx;
            vels[1] = vy;
        }

        double[] output = new double[] {x, y, coords[2], vx, vy};
        return output;
        
    }

    public double[] sumOfVels() {
        double vel0 = vels[0]; double vel1 = vels[1];
        double[] sumVels = new double[] {vel0, vel1, 0, 0};
        while ((vel0 != 0) || (vel1 != 0)) {
            double[] next = nextPosition();
            double vX = next[3];
            double vY = next[4];
            vel0 = vX;
            vel1 = vY;
            sumVels[0] += vX;
            sumVels[1] += vY;
            if (vX==0 && vY==0) {
                sumVels[2] = next[0];
                sumVels[3] = next[1];
            }
        }
        return sumVels;
    }

    @Override
    public Physics clone() {
        try {
            return (Physics) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}
