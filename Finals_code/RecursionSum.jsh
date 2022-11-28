int sum(int n) {
    if (n == 0) { // base case
        System.out.println("base case returns 0");
        return 0;
    } else { // recursive case
        int ans = sum(n - 1) + n;
        System.out.println("adding " + n);
        return ans;
    }
}
sum(5)

class Log<T> {
    private final T t;
    private final String log;

    private Log(T value, String log) {
        this.t = value;
        this.log = log;
    }

    static <T> Log<T> of(T t) {
        return new Log<T>(t, "");
    }

    static <T> Log<T> of(T t, String log) {
        return new Log<T>(t, "\n" + log);
    }

    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return new Log<R>(mapper.apply(this.t), this.log);
    }

    <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends
    R>> mapper) {
        Log<? extends R> logr = mapper.apply(t);
        return new Log<R>(logr.t, this.log + logr.log);
    }

    public String toString() {
        return "Log[" + this.t + "]"
            + this.log;
    }
}

Log<Integer> sum(int n) {
    if (n == 0) {
        return Log.of(0, "hit base case!"); 
    } else {
        return sum(n - 1).flatMap(x -> Log.of(n + x, "adding " + n)); 
    }
}

int f(int n) {
    if (n == 1) {
        return 1;
    } else if (n % 2 == 0) {
        return f(n / 2);
    } else {
        return f(3 * n + 1);
    }
}
f(11)

Log<Integer> f(int n) {
    if (n == 1) {
        return Log.<Integer>of(1, "one");
    } else if (n % 2 == 0) {
        return Log.<Integer>of(n / 2, n + " / 2")
            .flatMap(x -> f(x));
    } else {
        return Log.<Integer>of(3 * n + 1, "3(" + n + ") + 1")
            .flatMap(x -> f(x));
    }
}
