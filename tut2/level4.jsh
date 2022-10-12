void serveCruises(List<Cruise> cruises, int numOfLoaders) {
    Loader loader = new Loader(numOfLoaders);
    int sCompTime = 0;
    boolean flag = false;

    for (Cruise cruise : cruises) {
        int sT = cruise.getServiceTime();
        int aT = cruise.getArrivalTime();
        if (sCompTime <= aT) {
            sCompTime = aT;
        }
        int numLoadersAvail = numOfLoaders;
        int servedLoaderCnt = 0;
        int initialTos = sCompTime;
        while (cruise.getNumOfLoadersRequired() != 0) {
            flag = false;
            Service service = new Service(loader, cruise, sCompTime);
            System.out.println(service.toString());
            cruise = cruise.serveOne();
            loader = loader.nextLoader();
            numLoadersAvail -= 1;
            if (numLoadersAvail == 0) {
                sCompTime = sCompTime + sT;
                numLoadersAvail = numOfLoaders;
                flag = true;
            }
        }
        if (flag == false) {
            sCompTime += sT;
        }
    }
}




