class P3 implements Protocol {
   private static final int monitoring = 5;

   public Protocol next(Person person, Test test, int numOfDays) {
       if (numOfDays > monitoring) {
           if (test.isPositive()) {
               return new D2();
           }
           return new D3();
       }
       if (person.isHighRisk()) {
           return new P1();
       }
       return new P2();
   }
    
   @Override
   public String toString() {
       return "P3";
   }
}


