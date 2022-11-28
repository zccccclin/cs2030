public // try catch sequence example
Class A {
    static void f() throws Exception {
        try {
            throw new Exception();
        } finally {
            System.out.println("1");
        }
    }

    static void g() throws Exception {
        System.out.print("2");
        f();
        System.out.print("3");
    }

    public static void main(String[] args) {
        try {
            g(); // 2 -> 1 -> 4, 3 will not be printed as f() invokes exception which is catched in main.
        } catch (Exception e) {
            System.out.print("4");
        }
    }
} 