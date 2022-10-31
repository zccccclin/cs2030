import java.util.function.*;
import java.util.*;

class IFL<T> {
    private final Supplier<Optional<T>> head;
    private final Supplier<IFL<T>> tail;
    private final boolean isEmptyList;

    IFL(Supplier<Optional<T>> head, Supplier<IFL<T>> tail, boolean isEmptyList) {
        this.head = head;
        this.tail = tail;
        this.isEmptyList = isEmptyList;
    }

    //Stream.generate(() -> 1).map(..)
    static <T> IFL<T> generate(Supplier<T> supplier) {
        Supplier<Optional<T>> newHead = () -> Optional.of(supplier.get());
        Supplier<IFL<T>> newTail = () -> IFL.generate(supplier);
        return new IFL<T>(newHead, newTail, false);
    }

    // Stream.iterate(1, x -> x+1)
    static <T> IFL<T> iterate(T seed, UnaryOperator<T> next) {
        Supplier<Optional<T>> newHead = () -> Optional.of(seed);
        Supplier<IFL<T>> newTail = () -> IFL.iterate(next.apply(seed), next);
        return new IFL<T>(newHead, newTail, false);
    }

    <R> IFL<R> map(Function<? super T, ? extends R> mapper) {
        Supplier<Optional<R>> newHead = () -> this.head().map(mapper);
        Supplier<IFL<R>> newTail = () -> this.tail().map(mapper);
        return new IFL<R>(newHead, newTail, isEmptyList);
    }

    IFL<T> filter(Predicate<? super T> predicate) {
        Supplier<Optional<T>> newHead = () -> this.head().filter(predicate);
        Supplier<IFL<T>> newTail = () -> this.tail().filter(predicate);
        return new IFL<T>(newHead, newTail, isEmptyList);
    }

    IFL<T> limit(int n) {
        Supplier<Optional<T>> newHead = () -> this.head();
        Supplier<IFL<T>> newTail = () -> this.tail().limit(n - 1);
        return new IFL<T>(newHead, newTail, n <= 0);
    }

    <U> U reduce(U seed, BiFunction<U, ? super T, U> bif) {
        U result = seed;
        IFL<T> curr = this;
        while (!curr.isEmptyList) {
            U u = result;
            result = curr.head().map(x -> bif.apply(u, x)).orElse(result);
            curr = curr.tail();
        }
        return result;
    }

    void forEach(Consumer<? super T> consumer) {
        IFL<T> curr = this;
        while (!curr.isEmptyList) {
            curr.head().ifPresent(consumer);
            curr = curr.tail();
        }
    }



    //IFL.generate(() -> 1).head()
    Optional<T> head() {
        return this.head.get();
    }

    IFL<T> tail() {
        return this.tail.get();
    }

}
