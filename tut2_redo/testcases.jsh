/open TimeConverter.java
/open Cruise.java
/open Loader.java
/open Service.java
/open level4.jsh
/open BigCruise.java
/open SmallCruise.java

List<Cruise> cruises = List.of(new Cruise("A1234",0,2,30),new Cruise("A2345",30,2,30),new
Cruise("A3456",130,2,30))
serveCruises(cruises,1)

serveCruises(cruises,2)
serveCruises(cruises,3)

List<Cruise> cruises = List.of(new Cruise("A1234",0,2,30), new Cruise("A2345",0,2,30), new
Cruise("3456", 0 ,2,30))
serveCruises(cruises,1)
serveCruises(cruises,2)
serveCruises(cruises,3)

Cruise first = new SmallCruise("S1111",900)

Cruise second = new BigCruise("B1112",901,120,50)
Cruise third =  new BigCruise("B1113",902,40,4500)
List<Cruise> cruises = List.of(first, second,third,new SmallCruise("S2030",1031),new BigCruise("B0001",1100,40,1500),new SmallCruise("S0001",1130))

serveCruises(cruises,1)

serveCruises(cruises,2)
serveCruises(cruises,3)
serveCruises(cruises,4)
