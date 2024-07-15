package dev.laughingcat27.util.math;

public class MathLib {

    public static int random(int min, int max) {
        int diff = max - min;
        double random = min + Math.random() * diff;

        return (int) Math.round(random);
    }
}
