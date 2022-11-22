class SolidCuboid extends Cuboid implements Solid {
    private final SolidImpl impl;
    
    SolidCuboid(double h, double w, double l, double d) {
        super(h, w, l);
        this.impl = new SolidImpl(this, d);
    }

    public double mass() {
        return this.impl.mass();
    }

    public double volume() {
        return super.volume();
    }

    @Override
    public String toString() {
        return "solid-" + super.toString() + " with a mass of " + String.format("%.2f",mass());
    }
}
