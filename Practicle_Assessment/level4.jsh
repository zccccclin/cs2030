/open ImList.java
/open Person.java
/open Protocol.java
/open Test.java
/open ART.java
/open PCR.java
/open Case.java
/open P1.java
/open P2.java
/open P3.java

new Case(new Person("B234", "M", 7), new PCR("decepticon"))
Case c = new Case(new Person("B234", "M", 7), new PCR("delta"))
Protocol p = c.getCurrentProtocol()
p instanceof P2
c.test(new PCR("zero"))
c = c.test(new PCR("zero")).test(new ART("C"))
c.test(new ART("C"))
c.test(new ART("C")).test(new ART("C"))
c.test(new PCR("beta"))
c.test(new PCR("beta")).test(new PCR("ok"))
Test pt = new ART("CT")
Case c = new Case(new Person("C345", "MM", 7), new PCR("delta"))
c = c.test(pt)
c = c.test(pt)
c = c.test(pt)
c = c.test(pt)
c = c.test(new ART("T"))
c = c.test(pt)
c = c.test(pt)
c = c.test(pt)
c = c.test(new ART("C"))
c.test(pt)
c.test(pt).test(pt)
c.test(pt).test(pt).test(new ART("C"))
