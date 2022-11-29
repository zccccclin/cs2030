import java.lang.StackWalker.Option;
import java.util.function.BiFunction;

// Write your solution in this file only.

// Include packages below.  Do not remove this comment.
import java.util.function.Function;
import java.util.function.Predicate;

// Answer 4a below. Do not remove this comment.
class IfElse<T, U> {
    private final Predicate<T> predicate;
    private final Function<T, U> ifTrue;
    private final Function<T, U> ifFalse;

    private IfElse(Predicate<T> predicate, Function<T, U> ifTrue, Function<T, U> ifFalse) {
        this.predicate = predicate;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    public static <T, U> IfElse<T, U> of(Predicate<T> predicate, Function<T, U> ifTrue, Function<T, U> ifFalse) {
        return new IfElse<T, U>(predicate, ifTrue, ifFalse);
    }

    public U apply(T t) {
        return predicate.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }


// Answer 4b below. Do not remove this comment.
    public IfElse<T, U> mapIf(IfElse<T, U> ifElse) {
        // if predicate is true apply ifElse predicate as well.
        return IfElse.of(predicate.and(ifElse.predicate), ifElse.ifTrue,
            x -> new IfElse<T, U>(predicate, ifElse.ifFalse, ifFalse).apply(x));
    }

// Answer 4c below. Do not remove this comment.
    public IfElse<T, U> mapElse(IfElse<T, U> ifElse) {
        return IfElse.of(predicate.or(ifElse.predicate), 
            x -> new IfElse<T, U>(predicate, ifTrue, ifElse.ifTrue).apply(x),
            ifElse.ifFalse);
    }


// Answer 4d below. Do not remove this comment.
    <R> IfElse<T, R> map(Function<? super U, ? extends R> mapper) {
        return IfElse.<T, R>of(predicate, ifTrue.andThen(mapper), ifFalse.andThen(mapper));
    }
    
    <R> IfElse<T, R> flatMap(Function<? super U, ? extends IfElse<U, ? extends R>> mapper) {
        return IfElse.<T, R>of(predicate,
            x -> mapper.apply(ifTrue.apply(x)).apply(ifTrue.apply(x)),
            x -> mapper.apply(ifFalse.apply(x)).apply(ifFalse.apply(x)));
    }


}// end of class IfElse
