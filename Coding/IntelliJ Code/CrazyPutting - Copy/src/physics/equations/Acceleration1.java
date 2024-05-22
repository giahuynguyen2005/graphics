package physics.equations;

public class Acceleration1 implements equationsInterface{
    final double G = 9.81;
    double muK;
    double stepSize;
    Terrain terrain;

    public Acceleration1(double muK, double stepSize, Terrain terrain) {
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
        double slY = terrain.slopeY(stateVector[3], stateVector[3]);
        double ddX = -G*(slX-muK*(slX/Math.sqrt(slX*slX+slY*slY)));
        double ddY = -G*(slY-muK*(slY/Math.sqrt(slX*slX+slY*slY)));
        return new double[] {
            ddX, ddY
        };
    }
}
