class Cuboid implements Shape3D {
    private final double h;
    private final double w;
    private final double l;

    Cuboid(double h, double w, double l) {
        this.h = h;
        this.w = w;
        this.l = l;
    }

    public double volume() {
        return this.h * this.w * this.l;
    }
    
    @Override
    public String toString() {
        return "cuboid [" + String.format("%.2f",this.h) + 
               " x " + String.format("%.2f",this.w) + 
               " x " + String.format("%.2f",this.l) + "]";
    }
}
