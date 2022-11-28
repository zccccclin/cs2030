// Computable Future
class A {
    private final int x;
    A() {
        this(0);
    }

    A(int x) {
        this.x = x;
    }

    void sleep() {
        System.out.println(Thread.currentThread().getName() + " " + x);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("interruptedi");
        }
    }

    A incr() {
        sleep();
        return new A(this.x + 1);
    }

    A decr() {
        sleep();
        if (x < 0) {
            throw new IllegalStateException();
        }
        return new A(this.x - 1);
    }
    
    public String toString() {
        return "" + x;
    }
}

//Make foo a computablefuture.supplyAsync(takes in a Supplier)
CompletableFuture<A> foo(A a) {
    return CompletableFuture.<A>supplyAsync(() -> a.incr())
        .thenApplyAsync(x -> x.decr()); //thenApplyAsync is like a map
}

CompletableFuture<A> bar(A a) {
    return CompletableFuture.<A>supplyAsync(() -> a.incr());
}

CompletableFuture<A> b = foo(new A()).thenCompose(x -> bar(x));
System.out.println(b.join());

CompletableFuture<A> baz(A a, int x) {
    if (x == 0) {
        return CompletableFuture.<A>completedFuture(new A(0));
    } else {
        return CompletableFuture.<A>supplyAsync(() ->
        a.incr().decr());
    }
}

CompletableFuture<A> c = baz(new A(), 1);
System.out.println(c.join());

CompletableFuture<Void> all = CompletableFuture.<Void>allOf(
    foo(new A()),
    bar(new A()),
    baz(new A(), 1));
all.join();
System.out.println("done!");

CompletableFuture<A> exc = CompletableFuture.<A>supplyAsync(() -> new A().decr().decr()).handle((result, exception) -> {
        if (result == null) {
            System.out.println("ERROR: " + exception);
            return new A();
        } else {
            return result;
        }
    });

System.out.println(exc.join());
