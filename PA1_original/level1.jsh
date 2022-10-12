/open Term.java
/open Pager.java

Pager p1 = new Pager("pager1")
Term t1 = p1

p1 instanceof Pager

p1 instanceof Term

t1.equals(p1)
t1.equals(new Pager("pager1"))
p1.equals("pager1")

