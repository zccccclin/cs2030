class Puzzle {
    private final Grid2D<Object> grid;
    private final int numOfCols;

    Puzzle(int n) {
        this.numOfCols = n;
        ImList<Object> list = new ImList<Object>();
        for (int i = 1; i < n * n; i++) {
            list = list.add(i);
        }
        list = list.add(".");
        this.grid = new Grid2D<Object>(list, n);
    }
    
    Puzzle(Grid2D<Object> puzzle, int numOfCols) {
        this.grid = puzzle;
        this.numOfCols = numOfCols;
    }

    private int getIdx(Object obj) {
        for (int i = 0; i < this.numOfCols * this.numOfCols; i++) {
            int r = this.getRow(i);
            int c = this.getCol(i);
            if (obj == this.grid.get(r, c)) {
                return i;
            }
        }
        return -1;
    }

    private boolean canMove(int idx, int emptyIdx) {
        if (this.getCol(emptyIdx) == 0 && emptyIdx == idx + 1) {
            return false;
        } else if (this.getCol(emptyIdx) == this.numOfCols - 1 && emptyIdx == idx - 1) {
            return false;
        } else if (emptyIdx == idx + 1 || emptyIdx == idx - 1 || 
                emptyIdx == idx - this.numOfCols || emptyIdx == idx + this.numOfCols) {
            return true;
        }
        return false;
    }

    private int getRow(int n) {
        return Math.floorDiv(n, this.numOfCols);
    }
    
    private int getCol(int n) {
        return n % this.numOfCols;
    }

    public Puzzle move(int n) {
        int idx = this.getIdx(n);
        int emptyIdx = this.getIdx(".");
        if (idx != -1 && this.canMove(idx, emptyIdx)) {
            Grid2D<Object> newPuzzle = this.grid.set(this.getRow(idx), this.getCol(idx), ".");
            newPuzzle = newPuzzle.set(this.getRow(emptyIdx), this.getCol(emptyIdx), n);
            return new Puzzle(newPuzzle, this.numOfCols);
        }
        return this;
    }
    
    public boolean isSolved() {
        Puzzle solvedPuzzle = new Puzzle(this.numOfCols);
        for (int i = 0; i < this.numOfCols * this.numOfCols; i++) {
            int r = this.getRow(i);
            int c = this.getCol(i);
            if (solvedPuzzle.grid.get(r, c) != this.grid.get(r, c)) {
                return false;
            }
        }
        return true;
    }

    private String formatString() {
        String output = "\n";
        int n = this.numOfCols;
        for (int i = 0; i < n * n; i++) {
            int r = this.getRow(i);
            int c = this.getCol(i);
            Object curr = this.grid.get(r, c);
            if (c == n - 1 && r == n - 1) {
                output = output + curr.toString();
                return output;
            } else if (c == n - 1) {
                output = output + curr.toString() + "\n";
            } else {
                output = output + curr.toString() + " ";
            }
        }
        return "";
    }

    @Override 
    public String toString() {
        return this.formatString();           
    }
}
