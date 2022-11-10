class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> subtraction = 
        Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> division = 
        Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exponentiation = 
        Operator.<Integer>of((x, y) -> (int) Math.pow(x, y), 2);

    private IntExpr(Expr<Integer> expr) {
        super(expr);
    }

    static IntExpr of(int value) {
        return new IntExpr(Expr.<Integer>of(value));
    }

    static IntExpr of(Expr<Integer> expr) {
        return new IntExpr(expr);
    }

    public IntExpr add(int toAdd) {
        Expr<Integer> result = this.op(addition, toAdd);
        return of(result);
    }

    public IntExpr mul(int toMul) {
        Expr<Integer> result = this.op(multiplication, toMul);
        return of(result);
    }

    public IntExpr div(int toDiv) {
        Expr<Integer> result = this.op(division, toDiv);
        return of(result);
    }

    public IntExpr sub(int toSub) {
        Expr<Integer> result = this.op(subtraction, toSub);
        return of(result);
    }

    public IntExpr exp(int toExp) {
        Expr<Integer> result = this.op(exponentiation, toExp);
        return of(result);
    }

    

}

