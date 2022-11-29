// a convert method that takes in an integer array arr and a function fn and returns a new integer array with all the elements mapped over fn. The method should comprise of only one return statement.
int[] convert(int[] arr, Function<Integer, Integer> fn) {
    return Arrays.stream(arr).map(x -> fn.apply(x)).toArray(); 
} 

// method convert that takes in an integer array arr, and a function that specifies how the array is accessed, and how the elements are to be mapped. The method should comprise of only one return statement.
int[] convert(int[] arr, Function<int[], int[]> fn) {
    return fn.apply(arr);
}

Function<int[], int[]> f = arr -> IntStream.iterate(0, x < arr.length, x -> x + 2)
    .map(x -> arr[x] * 2).toArray();

    