package physics.equations;

public class Exponent implements equationsInterface{
    double c;

    public Exponent(double c) {
        this.c = c;
    }

    @Override
    public double[] solve(double[] variables) {
        double[] result = new double[1];
        result[0] = c * variables[0];
        return result;
    }

    @Override
    public int getNumberOfEquations() {
        return 1;
    }
}
