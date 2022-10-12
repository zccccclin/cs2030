/open ImList.java
/open Person.java
/open Protocol.java
/open Test.java
/open ART.java
/open PCR.java
/open Case.java
/open ContactCase.java
/open P1.java
/open P2.java
/open P3.java
/open D1.java
/open D2.java
/open D3.java

Case c = new Case(new Person("A123", "PP", 8), new PCR("omicron"))
new ContactCase(new Person("B234", "M", 7), new ART("CT"), c)
Test t = new ART("C")
Case d = new ContactCase(new Person("B234", "M", 7), t, c)
Protocol p = d.getCurrentProtocol()
p instanceof P3
d = d.test(t)
d = d.test(t)
d = d.test(t)
d = d.test(t)
d = d.test(t)
d = d.test(t)
d = d.test(new PCR("delta"))
