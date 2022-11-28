/open Pair.java
Stream<BigInteger> fibbonacci(int n) {
    Pair<BigInteger, BigInteger> start = 
        new Pair<BigInteger, BigInteger>(BigInteger.ZERO, BigInteger.ONE);

    UnaryOperator<Pair<BigInteger, BigInteger>> next = 
        p -> new Pair<BigInteger, BigInteger>(p.second(), p.first().add(p.second()));

    return Stream.iterate(start, next).map(p -> p.second()).limit(n);
}

fibbonacci(10).toList()

