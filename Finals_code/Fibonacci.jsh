/open Pair.java
Stream<BigInteger> fibbonacci(int n) {
    Pair<BigInteger, BigInteger> startPair =
        new Pair<BigInteger, BigInteger>(BigInteger.ZERO,
        BigInteger.ONE);
    UnaryOperator<Pair<BigInteger, BigInteger>> nextPair = pr ->
        new Pair<BigInteger, BigInteger>(pr.second(),
        pr.first().add(pr.second()));

    return Stream.iterate(startPair, nextPair)
        .map(pr -> pr.second()).limit(n);
}

fibbonacci(10).toList()
