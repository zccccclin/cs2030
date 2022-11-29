// Answer 2ai below. Do not remove this comment.
double pow(double a, long b) {
    return Stream.<Double>generate(() -> a)
        .limit(b)
        .reduce(1.0, (x, y) -> x * y);
}


// Answer 2aii below. Do not remove this comment.
double seriesPi(long n) {
    return Stream.<Integer>iterate(1, x -> x + 1)
        .limit(n)
        .mapToDouble(x -> x)
        .map(x -> {
            if (x % 2 == 0) {
                return -(4 / (2 * x - 1));
            }
            return 4 / (2 * x - 1);
        })
        .sum();
}




// Answer 2b below. Do not remove this comment.
double approxPi(long n) {
    return Stream.generate(() -> getRand())
    .limit(n)
    .map(x -> new Point(x, getRand()))
    .filter(y -> unitCircle.contains(y))
    .mapToDouble(x -> 1.0)
    .sum() * 4 / n;
}








