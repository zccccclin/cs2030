/open Operator.java
/open Expr.java
Expr<Integer> expr = Expr.<Integer>of(2)
Operator<Integer> add = Operator.<Integer>of((x, y) -> x + y, 4)
Expr.<Integer>of(2).op(add, -3)
expr
Expr.<Integer>of(2).op(add, -3).op(add, 4).op(add, -5)
Operator<Integer> mul = Operator.<Integer>of((x, y) -> x * y, 3)
Expr.<Integer>of(2).op(mul, -3).op(mul, 4).op(mul, -5)
