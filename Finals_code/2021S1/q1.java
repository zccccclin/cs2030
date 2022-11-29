import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
class ImList<T> {
    private final List<T> list;

    ImList() {
        this.list = new ArrayList<T>();
    }

    private ImList(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    public ImList<T> update(Consumer<ImList<T>> updater) {
        ImList<T> newList = new ImList<T>(this.list);
        updater.accept(newList);
        return newList;
    }

    ImList<T> add(T elem) {
        return update(list -> list.add(elem));
    }

    ImList<T> set(int index, T elem) {
        return update(list -> list.set(index, elem));
    }

    ImList<T> remove(T elem) {
        return update(list -> list.remove(elem));
    }

// other methods omitted for brevity
}