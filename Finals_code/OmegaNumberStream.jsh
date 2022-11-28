boolean isPrime(int n) {
    return n > 1 && IntStream
        .range(2, n)
        .noneMatch(i -> n % i == 0);
}

IntStream factors(int x) {
    return IntStream.rangeClosed(1, x)
        .filter(d -> x % d == 0);
}

IntStream primeFactorsOf(int x) {
    return factors(x)
        .filter(d -> isPrime(d));
}

// omega number is the number of distinct prime factors of a number
// e.g. 12 = 2 * 2 * 3, so omega(12) = 2
LongStream omega(int n) {
    return IntStream
        .rangeClosed(1, n)
        .mapToLong(x -> primeFactorsOf(x).count());
}
omega(10).forEach(x -> System.out.print(x + " "))
