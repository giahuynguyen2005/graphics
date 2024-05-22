package physics;

import java.util.Arrays;
import java.util.Random;

import physics.equations.Terrain;

public class Bots {
    Terrain terrain;
    double[] initialStateVector;
    double[] targetPos;
    double initialStepSize;
    boolean wasWaterOn = false;

    public Bots(Terrain terrain, Physics physics) {
        this.terrain = terrain.clone();
        initialStateVector = physics.getStateVector();
        this.targetPos = this.terrain.getTargetDescr();
        initialStepSize = physics.stepSize;
        additionalEqs.initialPos = new double[] {initialStateVector[2], initialStateVector[3]};
        additionalEqs.targetPos = targetPos;
        additionalEqs.stepSize = initialStepSize;
        additionalEqs.terrain = this.terrain;
        additionalEqs.newPhysics = physics.clone();
    }

    public double[] simpleNewtonRaphson() {
        double vx = 0;
        double distance = terrain.getDistanceToTarget(initialStateVector[2], initialStateVector[3]);
        double directionRatio = (targetPos[1] - initialStateVector[3])/(targetPos[0] - initialStateVector[2]);
        vx = distance<5 ? distance : 5;
        boolean flag = true;

        while (flag) {
            double[] currentF = additionalEqs.getValue(new double[] {vx, vx*directionRatio}, 1);
            double currentDeriv = additionalEqs.partialDeriv(vx, vx*directionRatio, 1, 1, 0.01);
            vx = vx - currentF[0]/currentDeriv;
            flag = terrain.getDistanceToTarget(currentF[1], currentF[2]) > targetPos[2] ? true : false;
        }

        vx = (Math.abs(vx)>5) ? 5 : vx;
        double vy = vx*directionRatio;
        return new double[] {vx, vy};
    }

    public double[] generalNewtonRaphson() {
        double vx = 0;
        double vy = 0;
        boolean flag = true;
        int n = 0;
        Random rand = new Random();
        vx = rand.nextDouble()*10-5;
        vy = rand.nextDouble()*10-5;
        double[] best = new double[] {vx, vy};
        double bestDistance = Double.MAX_VALUE;

        while (flag) {
            if (n>10000) {
                flag = false;
                return best;
            }
            double temp[] = additionalEqs.getValue(new double[] {vx, vy}, 1);
            double[] currentF = new double[] {temp[0], additionalEqs.getValue(new double[] {vx, vy}, 2)[0], temp[1], temp[2]};
            n+=9;
            double[][] currentJacobian = additionalEqs.constructJacobian(vx, vy, 0.01);
            double delta[] = additionalEqs.findSolution(currentJacobian, currentF);
            vx = vx - delta[0];
            vy = vy - delta[1];
            vx = Math.abs(vx)>5 ? rand.nextDouble()*10-5 : vx;
            vy = Math.abs(vy)>5 ? rand.nextDouble()*10-5 : vy;
            double distance = terrain.getDistanceToTarget(currentF[2], currentF[3]);
            if (distance < bestDistance) {
                best = new double[] {vx, vy};
                bestDistance = distance;
            }
            flag = distance > targetPos[2] ? true : false;
        }

        return new double[] {vx, vy};
    }

    public double[] BroydensMethod() {
        Random rand = new Random();
        double previousVX = rand.nextDouble()*10-5;
        double previousVY = rand.nextDouble()*10-5;
        double[] previousF = new double[] {additionalEqs.getValue(new double[] {previousVX, previousVY}, 1)[0], additionalEqs.getValue(new double[] {previousVX, previousVY}, 2)[0]};
        double[][] previousJacobian = additionalEqs.constructJacobian(previousVX, previousVY, 0.01);
        double[][] currentJacobian = new double[2][2];
        double[] currentXDelta = additionalEqs.findSolution(previousJacobian, previousF);
        double currentXDeltaSqr = currentXDelta[0]*currentXDelta[0] + currentXDelta[1]*currentXDelta[1];
        double currentVX = previousVX - currentXDelta[0];
        double currentVY = previousVY - currentXDelta[1];
        double[] temp = additionalEqs.getValue(new double[] {currentVX, currentVY}, 1);
        double[] currentF = new double[] {temp[0], additionalEqs.getValue(new double[] {currentVX, currentVY}, 2)[0], temp[1], temp[2]};
        double[] currentFDelta = new double[] {currentF[0] - previousF[0], currentF[1] - previousF[1]};
        double[] bestVels = new double[] {currentVX, currentVY};
        double bestDistance = terrain.getDistanceToTarget(currentF[2], currentF[3]);
        boolean flag = true;
        int n = 0;
        double[] jacbianByDeltaX = new double[2];
        double[] numerator = new double[2];
        double[] numeratorByDeltaX = new double[2];
        double[][] completeUpdate = new double[2][2];

        while (flag) {
            if (n>1000) {
                flag = false;
                return bestVels;
            }
            if (!(currentVX>5 || currentVX<-5 || currentVY>5 || currentVY<-5)) {
                currentXDelta[0] = -currentXDelta[0];
                currentXDelta[1] = -currentXDelta[1];
                jacbianByDeltaX[0] = previousJacobian[0][0]*currentXDelta[0] + previousJacobian[0][1]*currentXDelta[1];
                jacbianByDeltaX[1] = previousJacobian[1][0]*currentXDelta[0] + previousJacobian[1][1]*currentXDelta[1];
                numerator[0] = currentFDelta[0] - jacbianByDeltaX[0];
                numerator[1] = currentFDelta[1] - jacbianByDeltaX[1];
                numeratorByDeltaX[0] = numerator[0]/currentXDeltaSqr;
                numeratorByDeltaX[1] = numerator[1]/currentXDeltaSqr;
                completeUpdate[0][0] = numeratorByDeltaX[0]*currentXDelta[0];
                completeUpdate[0][1] = numeratorByDeltaX[0]*currentXDelta[1];
                completeUpdate[1][0] = numeratorByDeltaX[1]*currentXDelta[0];
                completeUpdate[1][1] = numeratorByDeltaX[1]*currentXDelta[1];
                currentJacobian[0][0] = previousJacobian[0][0] + completeUpdate[0][0];
                currentJacobian[0][1] = previousJacobian[0][1] + completeUpdate[0][1];
                currentJacobian[1][0] = previousJacobian[1][0] + completeUpdate[1][0];
                currentJacobian[1][1] = previousJacobian[1][1] + completeUpdate[1][1];
                
                currentXDelta = additionalEqs.findSolution(currentJacobian, currentF);
                previousVX = currentVX;
                previousVY = currentVY;
                currentVX = previousVX - currentXDelta[0];
                currentVY = previousVY - currentXDelta[1];
                if (currentVX>5 || currentVX<-5 || currentVY>5 || currentVY<-5) {
                    continue;
                }
                previousF[0] = currentF[0]; previousF[1] = currentF[1];
                temp = additionalEqs.getValue(new double[] {currentVX, currentVY}, 1);
                currentF[0] = temp[0]; currentF[1] = additionalEqs.getValue(new double[] {currentVX, currentVY}, 2)[0];
                currentF[2] = temp[1]; currentF[3] = temp[2];
                double distance = terrain.getDistanceToTarget(currentF[2], currentF[3]);
                if (distance < bestDistance) {
                    bestVels[0] = currentVX; bestVels[1] = currentVY;
                    bestDistance = distance;
                    //System.out.print(n+" "+"vx: "+currentVX+" vy: "+currentVY+" distance: "+terrain.getDistanceToTarget(currentF[2], currentF[3])+"\n");
                }
                if (distance < targetPos[2]) {
                    //System.out.println("vx: "+currentVX+" vy: "+currentVY+" distance: "+terrain.getDistanceToTarget(currentF[2], currentF[3])+"!!!!!");
                    return new double[] {currentVX, currentVY};
                }

                currentFDelta[0] = currentF[0] - previousF[0];
                currentFDelta[1] = currentF[1] - previousF[1];
                currentXDeltaSqr = currentXDelta[0]*currentXDelta[0] + currentXDelta[1]*currentXDelta[1];
                previousJacobian[0][0] = currentJacobian[0][0]; previousJacobian[0][1] = currentJacobian[0][1];
                previousJacobian[1][0] = currentJacobian[1][0]; previousJacobian[1][1] = currentJacobian[1][1];
            } else {
                previousVX = rand.nextDouble()*10-5;
                previousVY = rand.nextDouble()*10-5;
                previousF = new double[] {additionalEqs.getValue(new double[] {previousVX, previousVY}, 1)[0], additionalEqs.getValue(new double[] {previousVX, previousVY}, 2)[0]};
                previousJacobian = additionalEqs.constructJacobian(previousVX, previousVY, 0.01);
                currentJacobian = new double[2][2];
                currentXDelta = additionalEqs.findSolution(previousJacobian, previousF);
                currentXDeltaSqr = currentXDelta[0]*currentXDelta[0] + currentXDelta[1]*currentXDelta[1];
                currentVX = previousVX - currentXDelta[0];
                currentVY = previousVY - currentXDelta[1];
                if (currentVX>5 || currentVX<-5 || currentVY>5 || currentVY<-5) {
                    continue;
                }
                temp = additionalEqs.getValue(new double[] {currentVX, currentVY}, 1);
                currentF[0] = temp[0]; currentF[1] = additionalEqs.getValue(new double[] {currentVX, currentVY}, 2)[0];
                currentF[2] = temp[1]; currentF[3] = temp[2];
                currentFDelta[0] = currentF[0] - previousF[0];
                currentFDelta[1] = currentF[1] - previousF[1];
                double distance = terrain.getDistanceToTarget(currentF[2], currentF[3]);
                if (distance < bestDistance) {
                    bestVels[0] = currentVX; bestVels[1] = currentVY;
                    bestDistance = distance;
                }
                if (distance < targetPos[2]) {
                    return new double[] {currentVX, currentVY};
                }
            }

            n+=2;
        }

        return bestVels;
    }

    public double[] gradientDescent() {
        double[] currX = new double[2];
        Random rand = new Random();
        double[] prevX = new double[] {rand.nextDouble()*10-5, rand.nextDouble()*10-5};
        double[] best = new double[] {prevX[0], prevX[1]};
        double bestDistance = Double.MAX_VALUE;
        double[] prevObjFunc = additionalEqs.objFunction(prevX);
        boolean flag = true;
        int i = 0;

        while (flag) {
            double distance = terrain.getDistanceToTarget(prevObjFunc[1], prevObjFunc[2]);
            if (distance < bestDistance) {
                best[0] = prevX[0]; best[1] = prevX[1];
                bestDistance = distance;
            }
            if (distance < targetPos[2]) {
                return best;
            }
            if (prevX[0]>5 || prevX[0]<-5 || prevX[1]>5 || prevX[1]<-5) {
                prevX[0] = rand.nextDouble()*10-5;
                prevX[1] = rand.nextDouble()*10-5;
                prevObjFunc = additionalEqs.objFunction(prevX);
                continue;
            }
            i++; System.out.print(i+" ");
            if (i>1000) {
                flag = false;
                return best;
            }
            double[] gradient = additionalEqs.constructGradient(prevX, 0.01, prevObjFunc[3], prevObjFunc[4]);
            for (int n = 0; n>=-6; n--) {
                double stepSize = Math.pow(10, n);
                currX[0] = prevX[0] - stepSize*gradient[0];
                currX[1] = prevX[1] - stepSize*gradient[1];
                if (currX[0]>5 || currX[0]<-5 || currX[1]>5 || currX[1]<-5) {
                    prevX[0] = currX[0]; prevX[1] = currX[1];
                    continue;
                }
                double[] currObjFunc = additionalEqs.objFunction(currX);
                if (currObjFunc[0] < prevObjFunc[0]) {
                    prevX[0] = currX[0]; prevX[1] = currX[1];
                    prevObjFunc = Arrays.copyOf(currObjFunc, currObjFunc.length);
                    break;
                }
            }
        }

        return best;
    }

    public double[] gridSearch() {
        double vx = 0;
        double vy = 0;
        double[] best = new double[] {vx, vy};
        double bestDistance = Double.MAX_VALUE;
        double stepSize = 0.1;

        for (double i = -5; i <= 5; i += stepSize) {
            for (double j = -5; j <= 5; j += stepSize) {
                double[] temp = additionalEqs.getValue(new double[] {i, j}, 1);
                System.out.println("i: "+i+" j: "+j);
                double distance = terrain.getDistanceToTarget(temp[1], temp[2]);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    best = new double[] {i, j};
                }
                if (distance < targetPos[2]) {
                    return new double[] {i, j};
                }
            }
        }

        return best;
    }

    public double[] stochasticHillClimbing() {
        Random rand = new Random();
        double[] best = new double[] {0, 0};
        double vx = 0; double vy = 0;
        double bestDistance = Double.MAX_VALUE;
        boolean flag = true;

        while (flag) {
            vx = rand.nextDouble()*10-5;
            vy = vy+rand.nextDouble()*10-5;
            double[] temp = additionalEqs.getValue(new double[] {vx, vy}, 1);
            double[] currentF = new double[] {temp[0], additionalEqs.getValue(new double[] {vx, vy}, 2)[0], temp[1], temp[2]};
            double distance = terrain.getDistanceToTarget(currentF[2], currentF[3]);
            if (distance < targetPos[2]) {
                return new double[] {vx, vy};
            }
            if (distance < bestDistance) {
                best = new double[] {vx, vy};
                bestDistance = distance;
                System.out.println("vx: "+vx+" vy: "+vy + " distance: "+distance);
            }
            flag = distance > targetPos[2] ? true : false;
            //points.remove(ind);
        }

        return best;
    }
}
