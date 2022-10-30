import java.util.Optional;

class Num extends AbstractNum<Integer> {
    protected Num(AbstractNum<Integer> absNum) {
       super(absNum.opt.filter(AbstractNum.valid));
    }
    
    protected Num(Optional<Integer> opt) {
        super(opt.filter(AbstractNum.valid));
    }

    static Num of(Integer i) {
        Optional<Integer> integer = Optional.<Integer>of(i);
        integer = integer.filter(AbstractNum.valid);
        return new Num(integer);
    }

    static Num empty() {
        return new Num(Optional.empty());
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    static Num one() {
        return new Num(Optional.<Integer>of(1));
    }

    public Num succ() {
        return new Num(this.opt.map(AbstractNum.s));
    }

    public Num add(Num num) {
        if (!num.isValid() || !this.isValid()) {
            return Num.empty();
        }
        Num result = this;
        AbstractNum<Integer> absNum = 
            new AbstractNum<Integer>(num.opt.map(AbstractNum.n));
        while (!absNum.equals(Num.zero())) {
            result = result.succ();
            absNum = new AbstractNum<Integer>(absNum.opt.map(AbstractNum.s));
        }
        return result;
    }

    public Num mul(Num num) {
        if (!num.isValid() || !this.isValid()) {
            return Num.empty();
        } 
        Num result = Num.of(0);
        AbstractNum<Integer> absNum = 
            new AbstractNum<Integer>(num.opt.map(AbstractNum.n));
        while (!absNum.equals(Num.zero())) {
            result = result.add(this);
            absNum = new AbstractNum<Integer>(absNum.opt.map(AbstractNum.s));
        }
        return result;
    }
    
    public Num sub(Num num) {
        if (!num.isValid() || !this.isValid()) {
            return Num.empty();
        }
        AbstractNum<Integer> resultAbs =
            new AbstractNum<Integer>(num.opt.map(AbstractNum.n));
        AbstractNum<Integer> absNum = 
            new AbstractNum<Integer>(this.opt.map(AbstractNum.n));
        while (!absNum.equals(Num.zero())) {
            resultAbs = new AbstractNum<Integer>(resultAbs.opt.map(AbstractNum.s));
            absNum = new AbstractNum<Integer>(absNum.opt.map(AbstractNum.s));
        }
        Num result = new Num(resultAbs.opt);
        return result;
    }

}
