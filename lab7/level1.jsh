/open AbstractNum.java
/open Num.java

Num zero = Num.zero()
Num one = Num.of(1)
Num invalid = Num.of(-1)
List<AbstractNum> list = List.<AbstractNum>of(zero, one, invalid)
Num.one()
Num.zero().succ()
Num.one().equals(Num.zero().succ())
Num.of(-1).succ()
Num.one().succ().succ()
Num.of(-1).succ().succ()
