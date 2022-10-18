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
/open level5.jsh

findBestBooking(new Request(20, 3, 1000),List.of(new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)))
findBestBooking(new Request(10, 1, 900),List.of(new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)))

