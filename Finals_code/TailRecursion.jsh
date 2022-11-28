interface Compute<T> {
    boolean isRecursive();

    Compute<T> recurse();

    T evaluate();
}

class Base<T> implements Compute<T> { 
    private final Supplier<T> supplier;

    Base(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public boolean isRecursive() {
        return false;
    }

    public Compute<T> recurse() {
        throw new IllegalStateException("Evaluating a base case");
    }

    public T evaluate() {
        return this.supplier.get();
    }
}

class Recursive<T> implements Compute<T> {
    private final Supplier<Compute<T>> supplier;

    Recursive(Supplier<Compute<T>> supplier) {
        this.supplier = supplier;
    }

    public boolean isRecursive() {
        return true;
    }

    public Compute<T> recurse() {
        return this.supplier.get();
    }

    public T evaluate() {
        throw new IllegalStateException("Evaluating a recursive case");
    }
}

Compute<Long> sum(long n, long s) {
    if (n == 0) {
        return new Base<Long>(() -> s);
    } else {
        return new Recursive<Long>(() -> sum(n - 1, n + s));
    }
}

long sum(long n) {
    Compute<Long> result = sum(n, 0);

    while (result.isRecursive()) {
        result = result.recurse();
    }
    return result.evaluate();
}

Compute<Long> factorial(long n, long s) {
    if (n == 0) {
        return new Base<Long>(() -> s);
    } else {
        return new Recursive<Long>(() -> factorial(n - 1, n * s));
    }
}

long factorial(long n) {
    Compute<Long> result = factorial(n, 1);

    while (result.isRecursive()) {
        result = result.recurse();
    }
    return result.evaluate();
}
