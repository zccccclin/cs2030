import java.util.function.BinaryOperator;

class Operator<T> implements Comparable<Operator<T>> {
    private final BinaryOperator<T> operator;
    private final int precedence;

    private Operator(BinaryOperator<T> operator, int precedence) {
        this.operator = operator;
        this.precedence = precedence;
    }

    static <U> Operator<U> of(BinaryOperator<U> operator, int precedence) {
        return new Operator<U>(operator, precedence);
    }

    public T apply(T a, T b) {
        return this.operator.apply(a, b);
    }

    @Override
    public int compareTo(Operator<T> otherOperator) {
        return this.precedence - otherOperator.precedence;
    }


    public String toString() {
        return "Operator of precedence " + this.precedence;
    }
}
