import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.List;
import java.util.function.UnaryOperator;

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
        return IntStream.range(1, list.size())
            .filter(i -> i - 2 < 0 || 
                    list.get(i - 2) != list.get(i))
            .filter(i -> list.get(i) == list.get(i - 1))
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
        boolean scenario1 = list.get(idx) == 1;
        boolean scenario2 = list.get(idx) == 0 && 
            (list.get(idx - 1) == 1 && list.get(idx + 1) != 1);
        boolean scenario3 = list.get(idx) == 0 && 
            (list.get(idx - 1) != 1 && list.get(idx + 1) == 1);
        return scenario1 || scenario2 || scenario3;
    }

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, generateRule()).limit(n).map(x -> {
            return IntStream.range(0, x.size()).mapToObj(i -> {
                if (x.get(i) == 0) {
                    return ".";
                } else {
                    return "x";
                }
            }).reduce("", (a, b) -> a + b);
        });
    }
}
