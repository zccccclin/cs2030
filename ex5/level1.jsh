/open Maybe.java
Maybe<Integer> mi = Maybe.<Integer>ofNullable(1)
Maybe<String> ms = Maybe.<String>ofNullable("1")
mi = Maybe.<Integer>ofNullable(null)
ms = Maybe.<Integer>ofNullable(null)
