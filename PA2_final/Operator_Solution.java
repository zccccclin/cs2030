import java.util.function.BinaryOperator;

class Operator<T> implements Comparable<Operator<T>> {
    private final BinaryOperator<T> bifn;
    private final int prec; // must be non-zero

    private Operator(BinaryOperator<T> bifn, int prec) {
        this.bifn = bifn;
        this.prec = prec;
    }

    static <T> Operator<T> of(BinaryOperator<T> bifn, int prec) {
        return new Operator<T>(bifn, prec);
    }

    public T apply(T t, T u) {
        return this.bifn.apply(t, u);
    }

    @Override
    public int compareTo(Operator<T> other) {
        return this.prec - other.prec;
    }

    @Override
    public String toString() {
        return "Operator of precedence " + this.prec;
    }
}


