import java.util.stream.Stream;
class Expr<T> {
    private final T value;
    private final Stream<Operator<T>> operatorStream; 
    private Expr(T value, Stream<Operator<T>> operatorStream) {
        this.value = value;
        this.operatorStream = operatorStream;
    }

    static <U> Expr<U> of(U value) {
        return new Expr<U>(value, Stream.<Operator<U>>of());
    }

    static <U> Expr<U> of(U value, Stream<Operator<U>> operatorStream) {
        return new Expr<U>(value, operatorStream);
    }

    public Expr<T> op(Operator<T> operator, T otherValue) {
        T result = operator.apply(this.value, otherValue);
        return Expr.<T>of(result);
    }
    
    public Expr<T> op(Operator<T> operator, Expr<T> otherExpr) {
        T result = operator.apply(this.value, otherExpr.value);
        return Expr.<T>of(result);
    }

    public String toString() {
        return "(" + this.value + ")";
    }
}
