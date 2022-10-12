import java.util.List;

class Grid2D<E> {
    private final ImList<E> list;
    private final int numOfCols;

    Grid2D(int numOfCols) {
        this(new ImList<E>(), numOfCols);
    }

    Grid2D(List<E> list, int numOfCols) {
        this.list = new ImList<E>(list);
        this.numOfCols = numOfCols;
    }

    Grid2D(ImList<E> list, int numOfCols) {
        this.list = list;
        this.numOfCols = numOfCols;
    }

    public Grid2D<E> add(E elem) {
        ImList<E> newList = this.list.add(elem);
        return new Grid2D<E>(newList, this.numOfCols);
    }

    public E get(int r, int c) {
        return this.list.get(r * this.numOfCols + c);
    }

    public Grid2D<E> set(int r, int c, E elem) {
        ImList<E> newList = this.list.set(r * this.numOfCols + c, elem);
        return new Grid2D<E>(newList, this.numOfCols);
    }

    private String formatString() {
        String output = "";
        if (this.list.size() != 0) {
            int idx = 1;
            while (idx < this.list.size()) {
                if (idx % this.numOfCols == 0) {
                    output = output + this.list.get(idx - 1).toString() + ";";
                } else {
                    output = output + this.list.get(idx - 1).toString() + ",";
                }
                idx++;
            }
            output = output + this.list.get(idx - 1).toString();
        }
        return output;
    }

    @Override
    public String toString() {
        return "{" + this.formatString() + "}";
    }
}

