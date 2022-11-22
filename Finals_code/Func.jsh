abstract class Func {
    abstract int apply(int a);

    Func compose(Func other) {
        return new Func() {
            int apply(int x) {
                return Func.this.apply(other.apply(x));
            }
        };
    }
}

Func f = new Func() {
    int apply(int x) {
        return 2 * x;
    }
}

Func g = new Func() {
    int apply(int x) {
        return 2 + x;
    }
}

f.compose(g).apply(10)
/reset
"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
abstract class Func<T, R> {
    abstract R apply(T t);

    <U> Func<U, R> compose(Func<U, T> other) {
        return new Func<U, R>() {
            R apply(U x) {
                return Func.this.apply(other.apply(x));
            }
        };
    }
}

Func<String, Integer> f = new Func<String, Integer> () {
    @Override
    Integer apply(String s) {
        return s.length();
    }
}

Func<Integer, String> g = new Func<Integer, String>() {
    @Override
    String apply(Integer x) {
        return x + "";
    }
}

g.compose(f).apply("this") +
g.compose(f).apply("is") +
g.compose(f).apply("fun!!!")
