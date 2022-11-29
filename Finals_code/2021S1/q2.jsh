// Cyclic Dependency Method
// abstract class TrafficLight {
//     private final String color;

//     TrafficLight(String color) {
//         this.color = color;
//     }

//     abstract TrafficLight toggle();

//     @Override
//     public String toString() {
//         return this.color;
//     }
// }

// class Redlight extends TrafficLight {
//     Redlight() {
//         super("red");
//     }

//     @Override
//     TrafficLight toggle() {
//         return new Greenlight();
//     }
// }

// class Greenlight extends TrafficLight {
//     Greenlight() {
//         super("green");
//     }

//     @Override
//     TrafficLight toggle() {
//         return new Redlight();
//     }
// }


// No Cyclic Dependency Method
abstract class TrafficLight {
    private final String color;

    TrafficLight(String color) {
        this.color = color;
    }

    abstract TrafficLight toggle();

    @Override
    public String toString() {
        return this.color;
    }
}

abstract class Redlight extends TrafficLight {
    Redlight() {
        super("red");
    }
}

abstract class Greenlight extends TrafficLight {
    Greenlight() {
        super("green");
    }
}

abstract class Amberlight extends TrafficLight {
    Amberlight() {
        super("amber");
    }
}


void toggling(TrafficLight t, int n) {
    System.out.print(t);
    for (int i = 1; i < n; i++) {
        t = t.toggle();
        System.out.print(" -> " + t);
    }
    System.out.println();
}

// toggling(new Redlight(), 5); // Cyclic Dependency Method
// No Cyclic Dependency Method
toggling(new Redlight() { 
    private final Redlight red = this; 
    // Can define instances to be itself
    // Anonymous inner class can be made concrete by defining abstract methods

    @Override
    TrafficLight toggle() {
        return new Greenlight() {
            @Override
            TrafficLight toggle() {
                return new Amberlight() {
                    @Override
                    TrafficLight toggle() {
                        return red;
                    }
                };
            }
        };
    }
}, 5);

// toggling red -> amber -> green -> amber -> red -> amber -> green
toggling(new Redlight() {
    private final Redlight red = this;
    @Override
    TrafficLight toggle() {
        return new Amberlight() {
            @Override
            TrafficLight toggle() {
                return new Greenlight() {
                    @Override
                    TrafficLight toggle() {
                        return new Amberlight() {
                            @Override
                            TrafficLight toggle() {
                                return red;
                            }
                        };
                    }
                };
            }
        };
    }
}, 7)
            
