import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

class Stack<T> {
    private final List<T> list;

    Stack() {
        this.list = new ArrayList<T>();
    }

    private Stack(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    Stack<T> push(T elem) {
        Stack<T> newStack = new Stack<T>(this.list);
        newStack.list.add(0, elem);
        return newStack;
    }

    Pair<Optional<T>, Stack<T>> pop() {
        if (list.isEmpty()) {
            return new Pair<Optional<T>, Stack<T>>(Optional.empty(), this);
        } else {
            T elem = list.get(0);
            Stack<T> newStack = new Stack<T>(this.list);
            newStack.list.remove(0);
            return new Pair<Optional<T>, Stack<T>>(Optional.of(elem), newStack);
        }
    }

    boolean isEmpty() {
        return this.list.isEmpty();
    }

    public String toString() {
        return "Top -> " + this.list;
    }
}

class Pair<T, U> {
    private final T t;
    private final U u;

    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }
}

class Main {
    public static int evaluate(String expr) {
        Scanner sc = new Scanner(expr);
        Stack<Integer> stack = new Stack<Integer>();
        while (sc.hasNext()) {
            String term = sc.next();

            if (term.equals("+") || term.equals("*")) {
                Pair<Optional<Integer>, Stack<Integer>> pair = stack.pop();
                int op1 = pair.first().get();
                stack = pair.second();
                pair = stack.pop();
                int op2 = pair.first().get();
                stack = pair.second();
                if (term.equals("+")) {
                    stack = stack.push(op1 + op2);
                } else {
                    stack = stack.push(op1 * op2);
                }
            } else {
                stack = stack.push(Integer.parseInt(term));
            }
        }
        sc.close();
        return stack.pop().first().get();
    }
}