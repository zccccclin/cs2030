// Write your solution in this file only.
import java.util.List;

interface Payee {
    public String getId();

    public double getLoan();
}

interface Gradable {
    public String getId();

    public double getCAP();
}

abstract class Student implements Payee, Gradable {
    private final double CAP = 5.0;
    private final double loan;
    private final String id;

    public Student(String id, double loan) {
        this.loan = loan;
        this.id = id;
    }

    public double getCAP() {
        return CAP;
    }

    public double getLoan() {
        return loan;
    }

    public String getId() {
        return id;
    }
}

class UG extends Student{
    public UG(String id) {
        super(id, 99.99);
    }

    public double getLoan() {
        return super.getLoan();
    }

    public double getCAP() {
        return super.getCAP();
    }
}

class PG extends Student {
    public PG(String id) {
        super(id, 111.11);
    }

    public double getLoan() {
        return super.getLoan();
    }

    public double getCAP() {
        return super.getCAP();
    }
}

class Admin {
    public void process(List<? extends Gradable> list) {
        for (Gradable s : list) {
            System.out.println(s.getId() + " : " + s.getCAP());
        }
    }
}

class Bursar {
    public void process(List<? extends Payee> list) {
        for (Payee s : list) {
            System.out.println(s.getId() + " : " + s.getLoan());
        }
    }
}