package physics;

public class test {

    public static void main(String[] args) {
        /**
         * Initialize all parameters through InputModule (the most essential things - setStateVector() and setTerrain()),
         * then ask for Physics object by getPhysics(), and simulate motion by nextPosition() method.
         * If you want to use AI player, you have to creat an object of Bots class, call one of its methods, which will return initial velocities,
         * and then reset the physics object with these velocities and initial position. After that, you can simulate the motion.
         */
        double initialX = -3; double initialY = -0;
        InputModule.setStateVector(initialX, initialY, 0, 0);
        InputModule.setStepSize(0.1);
        /*
         * Terrains:
         * -0.4*e^(-(x^2 + y^2)/8)+0.36 -- from the manual
         * -0.4*e^(-(x^2 + y^2)/8)+0.36-0.4*e^(-((x-7)^2+(y-9)^2)/8)-0.4*e^(-((x-6)^2+(y-2)^2)/8) - for showing that AI is capable of avoiding obstacles
         */
        InputModule.setTerrain(new double[] {0.2, 0.06}, new double[] {9, 6, 0.15}, "-0.4*e^(-(x^2 + y^2)/8)+0.36");
        InputModule.setWater(true);
        //InputModule.setSand(new double[][] {{0, 0, 1, 1}}, new double[] {0.2, 0.1});
        //InputModule.addWall(new double[] {0, 1, 2, 1});
        Physics physics = InputModule.getPhysics();

        Bots bots = new Bots(physics.terrain, physics);
        double[] vels = bots.BroydensMethod();
        // necessarily use reset() method after calculating velocities for AI player
        physics.reset(vels, new double[] {initialX, initialY});

        System.out.println(vels[0] + " " + vels[1]);

        System.out.println("   x   |   y   |   z    |   vX   |   vY   ");
        for (int i = 0; i < 100; i++) {
            double[] next = physics.nextPosition();
            if (i % 1 == 0) {
                Double x = next[0];
                Double y = next[1];
                Double z = next[2];
                Double vX = next[3];
                Double vY = next[4];
                System.out.println(x + " | " + y + " | " + z + " | " + vX + " | " + vY);
            }
        }
    }
}
