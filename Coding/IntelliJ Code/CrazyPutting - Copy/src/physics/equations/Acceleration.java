package physics.equations;

public class Acceleration implements equationsInterface{
    final double G = 9.81;
    double muK;
    double stepSize;
    Terrain terrain;

    public Acceleration(double muK, double stepSize, Terrain terrain) {
        this.muK = muK;
        this.stepSize = stepSize;
        this.terrain = terrain;
    }

    public void setMU(double muK) {
        this.muK = muK;
    }

    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    public int getNumberOfEquations() {
        return 2;
    }

    public double[] solve(double[] stateVector) {
        double slX = terrain.slopeX(stateVector[2], stateVector[3]);
        double slY = terrain.slopeY(stateVector[2], stateVector[3]);
        double ddX = -G*(slX+muK*(stateVector[0]/Math.sqrt(stateVector[0]*stateVector[0]+stateVector[1]*stateVector[1])));
        double ddY = -G*(slY+muK*(stateVector[1]/Math.sqrt(stateVector[0]*stateVector[0]+stateVector[1]*stateVector[1])));
        return new double[] {
            ddX, ddY
        };
    }
}
