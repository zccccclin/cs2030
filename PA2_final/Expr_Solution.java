import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {
    private final Supplier<T> left;
    private final Supplier<Optional<Expr<T>>> right;
    private final Optional<Operator<T>> operator;

    Expr(Supplier<T> left, Supplier<Optional<Expr<T>>> right, Optional<Operator<T>> operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    Expr(Expr<T> expr) {
        this.left = expr.left;
        this.right = expr.right;
        this.operator = expr.operator;
    }

    static <T> Expr<T> of(T i) {
        return new Expr<T>(() -> i, () -> Optional.empty(), Optional.empty());
    }

    Expr<T> op(Operator<T> oper, Expr<T> t) {
        return op(oper, () -> Optional.of(Expr.of(t.evaluate())));
    }

    Expr<T> op(Operator<T> oper, T t) {
        return op(oper, () -> Optional.of(Expr.of(t)));
    }

    Expr<T> op(Operator<T> oper, Supplier<Optional<Expr<T>>> t) {
        return this.operator.map(x -> x.compareTo(oper) <= 0 ?
            new Expr<T>(() -> this.evaluate(), // x * y + ..
                t, 
                Optional.of(oper)) :
            new Expr<T>(left, // x + y * ..
                () -> right.get().map(y -> y.op(oper, t)),
                this.operator))
        .orElse(new Expr<T>(left, t, Optional.of(oper)));
    }

    T evaluate() {
        return this.operator.map(
                x -> x.apply(this.left.get(), this.right.get().map(y -> y.evaluate()).orElseThrow()))
            .orElseGet(this.left);
    }

    public String toString() {
        return "(" + this.evaluate() + ")";
    }
}


