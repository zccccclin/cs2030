import java.util.Optional;

class Fraction extends AbstractNum<Frac> {
    protected Fraction(Num a, Num b) {
        super(Frac.of(a, b));
    }
    
    protected Fraction(Optional<Frac> opt) {
        super(opt);
    }
    
    static Fraction of(int a, int b) {
        return Fraction.of(Num.of(a), Num.of(b));
    }

    static Fraction of(Num numA, Num numB) {
        if (!numA.isValid() || !numB.isValid() || numB.equals(Num.zero())) {
            return new Fraction(Optional.empty());
        }   
        return new Fraction(numA, numB);
    }
    
    // public Fraction add(Fraction other) {
    //     Num a = this.opt.map(x -> x.first());
    //     Num b = this.opt.map(x -> x.second());
    //     Num c = other.opt.map(x -> x.first());
    //     Num d = other.opt.map(x -> x.second());
    //     Num ad = a.mul(d);
    //     Num bc = b.mul(c);
    //     Num bd = b.mul(d);
    //     return Fraction.of(ad.add(bc), bd);
    // }

    // public Fraction sub(Fraction other) {
    //     Num a = this.opt.map(x -> x.first());
    //     Num b = this.opt.map(x -> x.second());
    //     Num c = other.opt.map(x -> x.first());
    //     Num d = other.opt.map(x -> x.second());
    //     Num ad = a.mul(d);
    //     Num bc = b.mul(c);
    //     Num bd = b.mul(d);
    //     return Fraction.of(ad.sub(bc), bd);
    // }
    // public Fraction mul(Fraction other) {
    //     Num a = this.opt.map(x -> x.first());
    //     Num b = this.opt.map(x -> x.second());
    //     Num c = other.opt.map(x -> x.first());
    //     Num d = other.opt.map(x -> x.second());
    //     Num ac = a.mul(c);
    //     Num bd = b.mul(d);
    //     return Fraction.of(ac, bd);
    // }
}
