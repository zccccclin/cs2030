/open ImList.java
/open Service.java
/open TimeConverter.java
/open Driver.java
/open JustRide.java
/open Request.java
/open TakeACab.java
/open NormalCab.java
/open Booking.java
/open ShareARide.java
/open PrivateCar.java
/open FareAndTimeComparator.java

new Request(20, 3, 1000)
new Request(20, 3, 1000).computeFare(new JustRide())
new Request(10, 1, 900)
new Request(10, 1, 900).computeFare(new JustRide())
new TakeACab()
new Request(20, 3, 1000).computeFare(new TakeACab())
new Request(10, 1, 900).computeFare(new TakeACab())
