/open Maybe.java
Maybe<Integer> mi = Maybe.<Integer>of(1)
mi.filter(x -> x % 2 == 0).ifPresent(x -> System.out.println(x))
mi.filter(x -> x % 2 == 1).ifPresent(x -> System.out.println(x))
mi.filter(x -> x % 2 == 0).ifPresentOrElse(x -> System.out.println(x),() -> System.out.println("No value"))
mi.filter(x -> x % 2 == 1).ifPresentOrElse(x -> System.out.println(x),() -> System.out.println("No value"))
Consumer<Object> consumer = x -> System.out.println(x.hashCode())
Runnable action = () -> {
    for (int i = 3; i >= 0; i--) {
        System.out.print(i + " ");
    }
    System.out.println("!");
}
Maybe<String> ms = Maybe.<String>of("one")
ms.filter(x -> x.equals("ONE")).ifPresentOrElse(consumer, action)
ms.filter(x -> x.equalsIgnoreCase("ONE")).ifPresentOrElse(consumer, action)
