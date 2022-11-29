// Modify your solution within this file only.

import java.util.function.Function;

abstract class Maybe<T> {
    // A anonymous class of Maybe for the non empty case
    static <T> Maybe<T> of(T thing) {
        return new Maybe<T>() {
            private final T t = thing;
            @Override
            public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
                return Maybe.<U>of(mapper.apply(t));
            }

            @Override
            public String toString() {
                return "Maybe[" + t + "]";
            }
        };
    }

    // A anonymous class of Maybe for the Nothing case.
    static <T> Maybe<T> empty() {
        return new Maybe<T>() {
            @Override
            public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
                return Maybe.<U>empty();
            }

            @Override
            public String toString() {
                return "Maybe.empty";
            }
        };
    }

    abstract <R> Maybe<R> map(Function<? super T, ? extends R> mapper);
}
