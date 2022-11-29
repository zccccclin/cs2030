// Modify your solution within this file only.

import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

class ImList<T> {
    private final Supplier<List<T>> list;

    ImList() {
        this.list = () -> new ArrayList<T>();
    }

    private ImList(Supplier<List<T>> list) {
        this.list = list;
    }

    ImList<T> add(T t) {
        return new ImList<T>(() -> {
            System.out.println("Adding: " + t);
            List<T> newList = new ArrayList<T>(list.get());
            newList.add(t);
            return newList;
        });
        // System.out.println("Adding: " + t);
        // ImList<T> newList = new ImList<T>(this.list);
        // newList.list.add(t);
        // return newList;
    }
    
    ImList<T> set(int index, T t) {
        return new ImList<T>(() -> {
            System.out.println("Setting: " + index + ", " + t);
            List<T> newList = new ArrayList<T>(list.get());
            newList.set(index, t);
            return newList;
        });
        // System.out.println("Setting: " + index + ", " + t);
        // ImList<T> newList = new ImList<T>(this.list);
        // newList.list.set(index, t);
        // return newList;
    }

    T get(int index) {
        return this.list.get().get(index);
    }
    
    @Override
    public String toString() {
        return "ImList";
    }
}
