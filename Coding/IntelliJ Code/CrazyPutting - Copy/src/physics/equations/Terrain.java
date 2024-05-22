package physics.equations;


import physics.org.mariuszgromada.math.mxparser.*;
import physics.org.mariuszgromada.math.mxparser.Argument;
import physics.org.mariuszgromada.math.mxparser.Expression;
import physics.org.mariuszgromada.math.mxparser.License;

import java.util.ArrayList;

/**
 * Terrain class is a description of the terrain on which the ball is rolling.
 * Initialize the terrain through the constructor and then use the methods
 * to get the height(double x, double y), slopeX(double x, double y)
 * slopeY(double x, double y), and friction at a given point - 
 * getMuK(double x, double y) for kinetic friction and getMuS(double x, double y)
 * for static friction.
 * Use setSand() to add sand blocks on the terrain.
 */
public class Terrain implements Cloneable {
    Argument x = new Argument("x");
    Argument y = new Argument("y");
    Expression f;
    Expression derivX;
    Expression derivY;
    double[][] sand;
    double[] targetPos;
    ArrayList<Double[]> walls = new ArrayList<Double[]>();
    double r;
    boolean sandOn = false;
    boolean waterOn = false;
    double sandStaticFriction;
    double sandKineticFriction;
    double grassStaticFriction;
    double grassKineticFriction;

    /**
     * Initializer for the terrain.
     * @param grassFrictions {static friction for grass, kinetic friction for grass}
     * @param targerDescription {x-coordinate, y-coordinate, radius}
     * @param f height function of x and y variables (should be lower case in the String)
     */
    public Terrain(double[] grassFrictions, double[] targetDescription, String f) {
        License.iConfirmNonCommercialUse("Vladislav Snytko");
        grassStaticFriction = grassFrictions[0];
        grassKineticFriction = grassFrictions[1];
        targetPos = new double[] {targetDescription[0], targetDescription[1]};
        r = targetDescription[2];
        this.f = new Expression(f, x, y);
        derivX = new Expression("der(" + f + ", x)", x, y);
        derivY = new Expression("der(" + f + ", y)", x, y);
    }

    public void setFunction(String f) {
        this.f = new Expression(f, x, y);
        derivX = new Expression("der(" + f + ", x)", x, y);
        derivY = new Expression("der(" + f + ", y)", x, y);
    }

    /**
     * Set the sand on the terrain.
     * @param sand 2d array, where every row represents rectangle coordinates of the sand, (two points on a diagonal of the rectangle) e.g.<p>
     * { <p>
     * {x-start, y-start, x-end, y-end},<p> {x-start1, y-start1, x-end1, y-end1}
     * <p> }.
     * @param sandFrictions {static friction for sand, kinetic friction for sand}.
     */
    public void setSand(double[][] sand, double[] sandFrictions) {
        this.sand = sand;
        sandStaticFriction = sandFrictions[0];
        sandKineticFriction = sandFrictions[1];
        sandOn = true;
    }

    /**
     * Turn off the sand on the terrain.
     */
    public void setSandOff() {
        sandOn = false;
    }

    /**
     * Check whether water is switched on.
     */
    public boolean checkWaterOn() {
        return waterOn;
    }

    public void setWater(boolean waterOn) {
        this.waterOn = waterOn;
    }

    /**
     * Returns the kinetic friction at a given point.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return kinetic friction at the given point
     */
    public double getMuK(double x, double y) {
        if (sandOn) {
            for (int i = 0; i<sand.length; i++) {
                if ((x>=sand[i][0]) && (x<=sand[i][2]) && (y>=sand[i][1]) && (y<=sand[i][3])) {
                    return sandKineticFriction;
                }
            }
        }
        return grassKineticFriction;
    }

    /**
     * Returns the static friction at a given point.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return static friction at the given point
     */
    public double getMuS(double x, double y) {
        if (sandOn) {
            for (int i = 0; i<sand.length; i++) {
                if ((x>=sand[i][0]) && (x<=sand[i][2]) && (y>=sand[i][1]) && (y<=sand[i][3])) {
                    return sandStaticFriction;
                }
            }
        }
        return grassStaticFriction;
    }

    /**
     * Returns the distance to the target from a given point.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return distance to the target from the given point
     */
    public double getDistanceToTarget(double x, double y) {
        return Math.sqrt((x-targetPos[0])*(x-targetPos[0]) + (y-targetPos[1])*(y-targetPos[1]));
    }

    /**
     * Set a wall in the terrain, which the ball cannot pass through and will stop at.
     * @param coordinates {x-start, y-start, x-start, y-end}
     */
    public void addWall(double[] coordinates) {
        walls.add(new Double[] {coordinates[0], coordinates[1], coordinates[2], coordinates[3]});
    }

    /**
     * Checks if the ball passes through a wall.
     * @param initialPos starting position of the ball
     * @param finalPos final position of the ball
     * @return
     */
    public boolean checkIfPassThroughWall(double[] initialPos, double[] finalPos) {
        if (walls.isEmpty()) return false;
        for (Double[] wall : walls) {
            if (checkBoxOverlap(new double[] {initialPos[0], initialPos[1], finalPos[0], finalPos[1]},
            new double[] {wall[0], wall[1], wall[2], wall[3]})) {
                if (checkIntersection(new double[] {initialPos[0], initialPos[1], finalPos[0], finalPos[1]},
                new double[] {wall[0], wall[1], wall[2], wall[3]})) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIntersection(double[] line1, double[] line2) {
        double x1 = line1[0];
        double y1 = line1[1];
        double x2 = line1[2];
        double y2 = line1[3];
        double x3 = line2[0];
        double y3 = line2[1];
        double x4 = line2[2];
        double y4 = line2[3];

        int o1 = orientation(new double[] {x1, y1}, new double[] {x2, y2}, new double[] {x3, y3});
        int o2 = orientation(new double[] {x1, y1}, new double[] {x2, y2}, new double[] {x4, y4});
        int o3 = orientation(new double[] {x3, y3}, new double[] {x4, y4}, new double[] {x1, y1});
        int o4 = orientation(new double[] {x3, y3}, new double[] {x4, y4}, new double[] {x2, y2});
        
        if ((o1 != o2) && (o3 != o4)) return true;
        return false;
    }

    private boolean checkBoxOverlap(double[] line1, double[] line2) {
        double x1 = line1[0];
        double y1 = line1[1];
        double x2 = line1[2];
        double y2 = line1[3];
        double x3 = line2[0];
        double y3 = line2[1];
        double x4 = line2[2];
        double y4 = line2[3];

        if ((x1<x3) && (x1<x4) && (x2<x3) && (x2<x4)) return false;
        if ((x1>x3) && (x1>x4) && (x2>x3) && (x2>x4)) return false;
        if ((y1<y3) && (y1<y4) && (y2<y3) && (y2<y4)) return false;
        if ((y1>y3) && (y1>y4) && (y2>y3) && (y2>y4)) return false;
        return true;
    }

    private int orientation(double[] p, double[] q, double[] r) {
        double val = (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    /**
     * Returns the height at a given point.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return height at the given point
     */
    public double height(double x, double y) {
        this.x.setArgumentValue(x);
        this.y.setArgumentValue(y);
        return f.calculate();
    }

    /**
     * Returns the slope in the x-direction at a given point.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return slope in the x-direction at the given point
     */
    public double slopeX(double x, double y) {
        this.x.setArgumentValue(x);
        this.y.setArgumentValue(y);
        return derivX.calculate();
    }

    /**
     * Returns the slope in the y-direction at a given point.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return slope in the y-direction at the given point
     */
    public double slopeY(double x, double y) {
        this.x.setArgumentValue(x);
        this.y.setArgumentValue(y);
        return derivY.calculate();
    }

    public double[] getTargetDescr() {
        return new double[] {targetPos[0], targetPos[1], r};
    }

    @Override
    public Terrain clone() {
        try {
            return (Terrain) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}
