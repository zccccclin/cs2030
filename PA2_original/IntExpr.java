class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> subtraction = 
        Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> division = 
        Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exponentiation = 
        Operator.<Integer>of((x, y) -> (int) Math.pow(x, y), 2);

    static of(int value) {
        return super(Expr.<Integer>of(value));
    }

    public IntExpr add(int toAdd) {
        Expr<Integer> result = this.op(super.addition, toAdd);
        return of(result);
    }

    public IntExpr mul(int toMul) {
        Expr<Integer> result = this.op(super.multiplication, toMul);
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

