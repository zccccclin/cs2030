/open Maybe.java
Maybe<Integer> mi = Maybe.<Integer>of(1)
mi.filter(x -> x % 2 == 0)
mi.filter(x -> x % 2 == 1)
mi.filter(x -> x % 2 == 0).filter(x -> x > 0)
mi.filter(x -> x % 2 == 1).filter(x -> x > 0)
Predicate<Object> pred = x -> x.hashCode() == 1
mi.filter(pred)
