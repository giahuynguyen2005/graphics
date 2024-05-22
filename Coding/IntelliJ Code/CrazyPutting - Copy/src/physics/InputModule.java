package physics;

import physics.equations.Terrain;

/**
 * Use this module to pass all the initial values and set everything for the simulation.
 * Methods: <p>
 *  setStateVector() - sets the initial state vector for the simulation. <p>
 *  setStepSize() - sets the time step size for the simulation. <p>
 *  setTerrain() - sets the terrain for the simulation. <p>
 *  addWall() - adds a wall to the terrain. <p>
 *  getPhysics() - returns the physics object with which you can simulate the balls's motion.
 *  setSand() - sets the sand on the terrain. <p>
 *  setSandOff() - turns off the sand on the terrain. <p>
 *  setWater() - switches water on the terrain. <p>
 */
public class InputModule {
    static double[] stateVector;
    static double stepSize = 0.1;
    static Terrain terrain;
    static Physics physics;

    /**
     * Sets the initial state vector for the simulation.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param vX velocity in the x-direction
     * @param vY velocity in the y-direction
     */
    public static void setStateVector(double x, double y, double vX, double vY) {
        stateVector = new double[] {vX, vY, x, y};
    }

    /**
     * Sets the time step size for the simulation.
     * @param stepSize
     */
    public static void setStepSize(double stepSize) {
        InputModule.stepSize = stepSize;
    }

    /**
     * Initializer for the terrain. First set the terrain and then add walls to it by addWall() method.
     * @param grassFrictions {static friction for grass, kinetic friction for grass}
     * @param targerDescription {x-coordinate, y-coordinate, radius}
     * @param f height function of x and y variables (should be lower case in the String)
     */
    public static void setTerrain(double[] grassFrictions, double[] targetDescription, String f) {
        terrain = new Terrain(grassFrictions, targetDescription, f);
    }

    /**
     * Set the height function of the terrain.
     * @param f height function of x and y variables (should be lower case in the String)
     */
    public static void setFunction(String f) {
        terrain.setFunction(f);
    }

    /**
     * Switch on the sand on the terrain. First create the terrain by setTerrain() method.
     * @param sand 2d array, where every row represents rectangle coordinates of the sand area (two points on a diagonal of the rectangle), e.g.<p>
     * { <p>
     * {x-start, y-start, x-end, y-end},<p> {x-start1, y-start1, x-end1, y-end1}
     * <p> }.
     * @param sandFrictions {static friction for sand, kinetic friction for sand}.
     */
    public static void setSand(double[][] sand, double[] sandFrictions) {
        terrain.setSand(sand, sandFrictions);
    }

    /**
     * Turn off the sand on the terrain.
     */
    public static void setSandOff() {
        terrain.setSandOff();
    }

    /**
     * Switch water on the terrain. Water is everywhere, where height<0.
     * @param waterOn
     */
    public static void setWater(boolean waterOn) {
        terrain.setWater(waterOn);
    }

    /**
     * Adds a wall to the terrain. First create the terrain by setTerrain() method.
     * @param wall {x-start, y-start, x-end, y-end}
     */
    public static void addWall(double[] wall) {
        terrain.addWall(wall);
    }

    public static Terrain getTerrain() {
        return terrain;
    }

    /**
     * Returns the physics object with which you can simulate the balls's motion.
     * @return Physics object
     */
    public static Physics getPhysics() {
        physics = new Physics(stateVector, stepSize, terrain);
        return physics;
    }

    public static Physics getPhysics(double vX, double vY) {
        if (physics == null) {
            throw new NullPointerException("Physics object is not initialized. Call getPhysics() method first.");
        } else {
            physics.setVelocities(new double[] {vX, vY});
            return physics;
        }
    }
}
