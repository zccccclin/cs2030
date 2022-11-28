// List of T, List of U and Combine them into List of R.
<T, U, R> Stream<R> product(
    List<? extends T> list1,
    List<? extends U> list2,
    BiFunction<? super T, ? super U, ? extends R> func) {

    return list1.stream()
        .flatMap(x -> list2.stream()
            .map(y -> func.apply(x, y)));
}

List<Integer> list1 = List.of(1, 2, 3, 4)
List<String> list2 = List.of("A", "B")
product(list1, list2, (str1, str2) -> str1 + str2).toList()
