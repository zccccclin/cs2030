static IntStream twinPrimes(int n) {
    return IntStream.rangeClosed(2, n)
        .filter(x -> 
            (isPrime(x) && isPrime(x + 2)) || 
            (isPrime(x) && isPrime(x - 2)));
}

static boolean isPrime(int n) {
    return n > 1 && IntStream
        .range(2, n)
        .noneMatch(i -> n % i == 0);
}

static String reverse(String str) {
    return Stream.<String>of(str.split(""))
        .reduce("", (a, b) -> b + a);
}

static long countRepeats(List<Integer> list) {
    return IntStream.range(1, list.size())
        .filter(i -> i - 2 < 0 || list.get(i - 2) != list.get(i))
        .filter(i -> list.get(i - 1) == list.get(i))
        .count();
}


//Task 4
static UnaryOperator<List<Integer>> generateRule() {
    return x -> {
        List<Boolean> change = IntStream.range(0, x.size())
            .mapToObj(i -> changeState(x, i)).toList();
        IntStream result = IntStream.range(0, x.size()).map(i -> {
            if (change.get(i).booleanValue()) {
                return Math.abs(x.get(i) - 1);
            } else {
                return x.get(i);
            }
        });
        List<Integer> list = result.boxed().toList();
        return list;
    };
}

static boolean changeState(List<Integer> list, int idx) {
    if (idx == 0) {
        return list.get(idx) == 1 || (list.get(idx) == 0 && list.get(1) == 1);
    }
    int last = list.size() - 1;
    if (idx == last) {
        return list.get(last) == 1 || (list.get(last) == 0 && list.get(last - 1) == 1);
    }
    return list.get(idx) == 1 || 
        (list.get(idx) == 0 && list.get(idx - 1) + list.get(idx + 1) == 1);
}

static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<Integer> rule, int n) {
    return IntStream.iterate(list, rule).limit(n).map(x -> {
        if (x.get(i) == 0) {
            return ".";
        } else {
            return "x";
        }
    }).reduce("", (a, b) -> a + b);
}

