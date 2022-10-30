class Num extends AbstractNum<Integer> {
    protected Num(AbstractNum<Integer> absNum) {
       super(absNum.opt);
    }
    
    protected Num(Optional<Integer> opt) {
        super(opt);
    }

    static Num of(Integer i) {
        Optional<Integer> integer = Optional.<Integer>of(i);
        integer = integer.filter(AbstractNum.valid);
        return new Num(integer);
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
        AbstractNum<Integer> absNum = 
            new AbstractNum<Integer>(num.opt.map(AbstractNum.n));
        while (!absNum.equals(Num.zero())) {
            num = num.succ();
            absNum = new AbstractNum<Integer>(absNum.opt.map(AbstractNum.s));
        }
        return num;
    }

}
