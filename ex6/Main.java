class Main {
    // Task 1
    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
            .filter(x ->  
                    (isPrime(x) && isPrime(x + 2)) ||
                    (isPrime(x) && isPrime(x - 2))
                    );
    }

    static boolean isPrime(int n) {
        return n > 1 && IntStream
            .range(2, n)
            .noneMatch(x -> n % x == 0);
    }

    // Task 2
    static String reverse(String str) {
        return Stream.<String>of(str.split("")).reduce("", (x, y) -> y + x);
    }

    // Task 3
    static long countRepeats(List<Integer> list) {
        return 0.0;   
    }
}
