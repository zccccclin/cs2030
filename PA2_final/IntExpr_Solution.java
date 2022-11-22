import java.util.stream.IntStream;

class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> sub = Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> div = Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exp = 
        Operator.of((x, y) -> IntStream.range(0, y).reduce(1, (m,n) -> m * x), 2);

    IntExpr(Expr<Integer> expr) {
        super(expr);
    }

    static IntExpr of(Integer x) {
        return new IntExpr(Expr.<Integer>of(x));
    }

    IntExpr add(int x) {
        return new IntExpr(super.op(addition, x));
    }

    IntExpr add(IntExpr x) {
        return new IntExpr(super.op(addition, x));
    }

    IntExpr mul(int x) {
        return new IntExpr(super.op(multiplication, x));
    }

    IntExpr mul(IntExpr x) {
        return new IntExpr(super.op(multiplication, x));
    }

    IntExpr exp(int x) {
        return new IntExpr(super.op(exp, x));
    }

    IntExpr exp(IntExpr x) {
        return new IntExpr(super.op(exp, x));
    }

    IntExpr sub(int x) {
        return new IntExpr(super.op(sub, x));
    }

    IntExpr sub(IntExpr x) {
        return new IntExpr(super.op(sub, x));
    }

    IntExpr div(int x) {
        return new IntExpr(super.op(div, x));
    }

    IntExpr div(IntExpr x) {
        return new IntExpr(super.op(div, x));
    }
}
