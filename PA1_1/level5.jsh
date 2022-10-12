/open ImList.java
/open Host.java
/open term.java
/open Pager.java
/open Transmitter.java
/open TransmitterRcv.java
/open PagerAck.java
/open TransmitterEnd.java
Pager p1 = new Pager("pager1")
Transmitter r1 = new Transmitter("transmit1")
p1.snd(r1)
p1.snd(r1).rcv().ack()
Pager p2 = new Pager("pager2")
Host h1 = p2.snd(p1.snd(r1).rcv().ack()).rcv().ack()
Host h2 = p2.snd(r1).rcv().ack()
h1.broadcast()
h2.broadcast()
p1.snd(r1).broadcast()
p1.snd(r1).rcv().ack().broadcast()
p2.snd(p1.snd(r1).rcv().ack()).broadcast()
p2.snd(p1.snd(r1)).rcv().ack()
