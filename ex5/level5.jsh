/open Maybe.java
Maybe<Integer> mi = Maybe.<Integer>of(1)
mi.filter(x -> x % 2 == 0).orElse(2)
mi.filter(x -> x % 2 == 1).orElse(2)
 mi.filter(x -> x % 2 == 0).orElseGet(() -> 2)
 mi.filter(x -> x % 2 == 1).orElseGet(() -> 2)
  Maybe<Object> mo = Maybe.<Object>of(1)
  Supplier<Integer> supp = () -> 2
  mo.filter(x -> x.hashCode() == 0).orElseGet(supp)
  mo.filter(x -> x.hashCode() == 1).orElseGet(supp)
  Supplier<Maybe<Integer>> suppMaybe = () -> Maybe.<Integer>of(2)
  mo.filter(x -> x.hashCode() == 0).or(suppMaybe)
  mo.filter(x -> x.hashCode() == 1).or(suppMaybe)

class MaybeSub<T> extends Maybe<T> {
    MaybeSub(T value) { super(value); }
}

Supplier<MaybeSub<Integer>> suppMaybeSub = () -> new MaybeSub<Integer>(2)
mo.filter(x -> x.hashCode() == 0).or(suppMaybeSub)
mo.filter(x -> x.hashCode() == 1).or(suppMaybeSub)
