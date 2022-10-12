import java.util.Arrays;

void serveCruises(List<Cruise> cruises, int numOfLoaders) {
    Loader loaders = new Loader(numOfLoaders);
    
    for (Cruise cruise : cruises) {
        loaders = loaders.serveNextCruise();
        for (int i = 0; i < cruise.getNumOfLoadersRequired(); i ++) {
            int tos = loaders.getTimeOfService(cruise);
            loaders = loaders.serve(cruise, tos);
            System.out.println(new Service(loaders,cruise, tos).toString());
            loaders = loaders.nextLoader();
        }
    }
        
}
