// && for predicate
Predicate<T> and(Predicate<T> p1, Predicate<T> p2) {
    // using lambda: return x -> p1.test(x) && p2.test(x);
    // using anonymous inner class, define SAM when creating class
    return new Predicate<T>() {
        public boolean test(T x) {
            return p1.test(x) && p2.test(x);
        }
    }
}

// Anonymous Inner class example:
abstract class Func { // not SAM
    abstract int apply(int x);

    Func compose(Func other) {
        return new Func() {
            public int apply(int x) {
                return Func.this.apply(other.apply(x)); 
                // other is applied first for compose. f(g(x))
            }
        }
    }
}

Func addTwo = new Func() {
    int apply(int x) {
        return x + 2;
    }
}

Func mulTwo = new Func() {
    int apply(int x) {
        return x * 2;
    }
}

//Making Func a SAM that allows lambda
interface Func {
    int apply(int x);
}

Func addTwo = x -> x + 2;
Func mulTwo = x -> x * 2;


// Making Func a generic T, R class
abstract Func<T, R> {
    abstract R apply(T t);

    <U> Func<U, R> compose(Func<U, T> other) {
        return new Func<U, R>() {
            R apply(U u) {
                return Func.this.apply(other.apply(u));
                // Takes in U, applies other to it, then applies this to the result T to get R.
            }
        }
    }
}

//f.compose(g) -> "abc" + "is" -> 3, 2 -> "32"
Func<String, Integer> f = new Func<String, Integer>() {
    @Override
    Integer apply(String s) {
        return s.length();
    }
}

Func<Integer, String> g = new Func<Integer, String>() {
    @Override
    String apply(Integer i) {
        return i.toString();
    }
}