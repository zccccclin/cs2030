class Sphere implements Shape3D {
    private final double radius;
    private static final double volumeConstant = 4.0 / 3.0;
    private static final int power = 3;

    Sphere(double radius) {
        this.radius = radius;
    }

    public double volume() {
        return volumeConstant * Math.PI * Math.pow(this.radius, power);
    }

    @Override
    public String toString() {
        return "sphere [" + String.format("%.2f", this.radius) + "]";
    }
}
