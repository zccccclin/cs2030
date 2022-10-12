/open ImList.java
/open Host.java
/open term.java
/open Pager.java
/open TransmitterRcv.java
/open PagerAck.java
/open Transmitter.java

new Pager("pager1").snd(new Transmitter("transmit1")).rcv().ack()
Pager p1 = new Pager("pager1")
Transmitter r1 = new Transmitter("transmit1")
p1.snd(r1).rcv().ack()
p1.snd(r1).rcv().equals(p1)
p1.ack()
p1.snd(r1).rcv().ack().equals(r1)

p1.snd(r1).rcv().ack().equals(p1.snd(r1))
