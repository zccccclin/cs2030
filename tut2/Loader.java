import java.util.Arrays;

class Loader {
    private final int identifier;
    private final int numOfLoaders;
    
    Loader(int numOfLoaders) {
        this(numOfLoaders, 0); 
    }

    Loader(int numOfLoaders, int identifier) {
        this.numOfLoaders = numOfLoaders;
        this.identifier = identifier;
    }


    public Loader nextLoader() {
        if ((this.identifier + 1) > numOfLoaders) {
            return new Loader(this.numOfLoaders, 1);
        } else {
            return new Loader(this.numOfLoaders, this.identifier + 1);
        }
    }

    @Override
    public String toString() {
        return "Loader #" + this.identifier;
    }  
}
