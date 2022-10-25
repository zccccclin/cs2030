import java.util.function.Function;
import java.lang.Runnable;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.thing));
        }
    }

    public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            @SuppressWarnings("unchecked")
            Maybe<R> r = (Maybe<R>) mapper.apply(this.thing);
            return r;
        }
    }

    static <U> Maybe<U> ofNullable(U thing) {
        if (thing == null) {
            return empty();
        }
        return of(thing);
    }

    public Maybe<T> filter(Predicate<? super T> pred) {
        if (this.thing == null || !pred.test(this.thing)) {
            return empty();
        }
        return Maybe.<T>of(this.thing);
    }

    public void ifPresent(Consumer<? super T> action) {
        if (this.thing != null) {
            action.accept(this.thing);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.thing != null) {
            action.accept(this.thing);
        } else {
            emptyAction.run();
        } 
    }

    public T orElse(T other) {
        if (this.thing != null) {
            return this.thing;
        }
        return other;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        if (this.thing != null) {
            return this.thing;
        }
        return supplier.get();
    }

    public Maybe<? extends T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (thing != null) {
            return this;
        }
        return supplier.get();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Maybe) {
            if (this.thing == ((Maybe) o).thing) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.thing == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.thing + "]";
        }
    }
}
