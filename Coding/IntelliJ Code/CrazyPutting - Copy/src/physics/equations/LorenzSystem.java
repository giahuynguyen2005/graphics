package physics.equations;

public class LorenzSystem implements equationsInterface{
    double sigma; double rho; double beta;

    public LorenzSystem(double sigma, double rho, double beta) {
        this.sigma = sigma;
        this.rho = rho;
        this.beta = beta;
    }

    @Override
    public double[] solve(double[] variables) {
        double[] result = new double[3];
        result[0] = sigma * (variables[1] - variables[0]);
        result[1] = variables[0] * (rho - variables[2]) - variables[1];
        result[2] = variables[0] * variables[1] - beta * variables[2];
        return result;
    }

    @Override
    public int getNumberOfEquations() {
        return 3;
    }
}
