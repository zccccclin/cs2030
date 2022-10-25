/open Maybe.java
Maybe<String> ms = Maybe.<String>of("123")
Function<String,Integer> f = x -> x.length()
ms.map(f)
Function<String,Maybe<Integer>> g = x -> Maybe.<Integer>of(x.length())
ms.map(g)
ms.flatMap(g)
Maybe<Object> mo = ms.flatMap(g)
class MaybeSub<T> extends Maybe<T> {
    MaybeSub(T value) {super(value);}
}

Function<String,MaybeSub<Integer>> h = x -> new MaybeSub<Integer>(x.length())
mo = ms.flatMap(h)
