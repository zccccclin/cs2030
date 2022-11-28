import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;

class ImList<T> {
    private final ArrayList<T> elems;

    ImList() { //Initializing empty ImList
        this.elems = new ArrayList<T>();
    }

    ImList(List<T> list) { //Initializing ImList with a list
        this.elems = new ArrayList<T>(list);
    }

    ImList<T> limit(int n) {
        ImList<T> newList = new ImList<T>();
        n = Math.min(Math.max(n, 0), this.elems.size()); 
        //If list.size() < n, n = list.size(). 
        //If n = negative, n = 0.
        for (int i = 0; i < n; i++) {
            newList.elems.add(this.elems.get(i));
        }
        return newList;
    }

    <R> ImList<R> map(Function<? super T, ? extends R> f) {
        ImList<R> newList = new ImList<R>();
        for (T elem : this.elems) {
            newList.elems.add(f.apply(elem));
        }
        return newList;
    }

    ImList<T> filter(Predicate<? super T> p) {
        ImList<T> newList = new ImList<T>();
        for (T elem : this.elems) {
            if (p.test(elem)) {
                newList.elems.add(elem);
            }
        }
        return newList;
    }

    <U> U reduce(U identity, BiFunction<? super U, ? super T, ? extends U> f) {
        //Bifunction: <input, input2, maps (input and input 2) to output type>
        U result = identity;
        for (T elem : this.elems) {
            result = f.apply(result, elem);
        }
        return result;
    }

    public void forEach(Consumer<? super T> action) {
        for (T elem : this.elems) {
            action.accept(elem);
        }
        // or do with delegation: this.elems.forEach(action); 
    }

    ImList<T> add(T elem) { //Adding element to ImList with delegation
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    ImList<T> set(int index, T elem) { //Setting element at index with delegation
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.set(index, elem);
        return newList;
    }

    T get(int index) { //Getting element at index with delegation
        return this.elems.get(index);
    }

    int size() { //Getting size of ImList with delegation
        return this.elems.size();
    }

    public String toString() {
        return this.elems.toString();
    }
}
