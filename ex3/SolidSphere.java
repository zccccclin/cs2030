class SolidSphere extends Sphere implements Solid {
    private final SolidImpl impl;

    SolidSphere(double radius, double density) {
        super(radius);
        this.impl = new SolidImpl(this, density);
    }

    public double mass() {
        return this.impl.mass();
    }

    public double volume() {
        return super.volume();
    }

    @Override
    public String toString() {
        return "solid-" + super.toString() + " with a mass of " + String.format("%.2f",this.mass());
    }
}
