import java.util.function.Function; // <T, R> apply(), andThen(afterFunction), compose(beforeFunction)
import java.util.function.Predicate; // test(T t) performs boolean evaluate operation on t
import java.util.function.Consumer; // accept(T t) performs operation on t
import java.lang.Runnable; //SAM execute run() method
import java.util.function.Supplier; //delay supplying of value

class Maybe<T> {
    private final T thing;

    public Maybe(T thing) {
        this.thing = thing;
    }

    static <U> Maybe<U> of(U thing) {
        if (thing == null) {
            throw new NullPointerException();
        }
        return new Maybe<U>(thing);
    }

    static <U> Maybe<U> ofNullable(U thing) {
        if (thing == null) {
            return empty();
        }
        return of(thing);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    public T orElse(T other) {
        if (thing == null) {
            return other;
        }
        return thing;
    }

    public T orElseGet(Supplier<T> other) {
        return orElse(other.get());
    }

    // return thing or supplier's value
    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (thing == null) {
            Maybe<? extends T> result = supplier.get();
            return Maybe.<T>of(result.thing);
        }
        return this;
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (thing == null) {
            return Maybe.<R>empty();
        }
        return Maybe.of(mapper.apply(thing));
    }

    public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (thing == null) {
            return Maybe.<R>empty();
        }
        Maybe<? extends R> result = mapper.apply(thing);
        return Maybe.<R>of(result.orElse(null));
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        if (thing == null) {
            return Maybe.<T>empty();
        }
        if (predicate.test(thing)) {
            return this;
        }
        return Maybe.<T>empty();
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (thing != null) {
            consumer.accept(thing);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable emptyAction) {
        if (thing != null) {
            consumer.accept(thing);
        } else {
            emptyAction.run();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Maybe obj) {
            if (this.thing == null && obj.thing == null) {
                return true;
            }
            if (this.thing == null || obj.thing == null) {
                return false;
            }
            if (this.thing.equals(obj.thing)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (thing == null) {
            return "Maybe.empty";
        }
        return "Maybe[" + thing + "]";
    }
}
