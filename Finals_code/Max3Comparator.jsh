// Compares and gives out maximum of 3 T type values.
<T> T max3(T a, T b, T c, Comparator<T> comp) {
    T max = a;
    if (comp.compare(b, max) > 0) {
        max = b;
    }
    if (comp.compare(c, max) > 0) {
        max = c;
    }
    return max;
}

// Comparator passed in for integer comparison
class IntComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        return a - b;
    }
}

// Restricting the type of T to have a compareTo method
<T extends Comparable<T>> T max3(T a, T b, T c) {
    T max = a;
    if (b.compareTo(max) > 0) {
        max = b;
    }
    if (c.compareTo(max) > 0) {
        max = c;
    }
    return max;
}

// For T to take any type, and List to take any type of list
static <T extends Comparable<? super T>> T max3(List<? extends T> list) {
    T max = list.get(0);
    for (T t : list) {
        if (t.compareTo(max) > 0) {
            max = t;
        }
    }
    return max;
}