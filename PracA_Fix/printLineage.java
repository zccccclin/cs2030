void printLineage(ImList<Case> lineage) {
    boolean first = true;
    for (Case c : lineage) {
        System.out.println((first ? "" : "-> ") + c);
        first = false;
        }
}

