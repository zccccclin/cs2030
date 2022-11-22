import java.util.Optional;
import java.util.function.Supplier;
class Expr<T> {
    private final Supplier<T> value;
    private final Optional<Operator<T>> operatorStream; 
    private final Supplier<Optional<Expr<T>>> otherValue;
    private Expr(Supplier<T> value, Optional<Operator<T>> operatorStream, 
        Supplier<Optional<Expr<T>>> otherValue) {
        this.value = value;
        this.operatorStream = operatorStream;
        this.otherValue = otherValue;
    }

    public Expr(Expr<T> expr) {
        this.value = expr.value;
        this.operatorStream = expr.operatorStream;
        this.otherValue = expr.otherValue;
    }
    
    static <U> Expr<U> of(U value) {
        return new Expr<U>(() -> value, Optional.empty(), () -> Optional.empty());
    }

    public Expr<T> op(Operator<T> operator, T otherValue) {
        return op(operator, Expr.<T>of(otherValue));
    }
    
    public Expr<T> op(Operator<T> operator, Expr<T> otherOperatedValue) {
        Supplier<Optional<Expr<T>>> otherOperatoredValue = () -> Optional.of(Expr.of(otherOperatedValue.evaluate()));
        return this.operatorStream.map(x -> {if (x.compareTo(operator) <= 0) {return new Expr<T>(() -> this.evaluate(), Optional.of(operator), otherOperatoredValue);} else {return new Expr<T>(this.value, this.operatorStream, () -> this.otherValue.get().map(y -> y.op(operator, otherOperatedValue)));}}).orElse(new Expr<T>(this.value, Optional.of(operator), otherOperatoredValue));
    }

    public T evaluate() {
        return this.operatorStream.map(x -> x.apply(this.value.get(), this.otherValue.get().map(y -> y.evaluate()).orElseThrow())).orElseGet(this.value);
    }

    public String toString() {
        return "(" + this.evaluate() + ")";
    }
}
