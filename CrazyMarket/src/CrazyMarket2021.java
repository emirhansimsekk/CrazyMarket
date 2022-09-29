import java.util.Random;

/**
 * market simulatoru, detaylar icin dokumana basvurunuz
 *
 * Odev 2 -- Market Simulatörü
 *
 *
 * @author Emirhan Simsek
 *
 * @class BIL 201
 *
 * @date 10.12.2021   23.14
 *
 */
public class CrazyMarket2021 {
    /** parameters for simulations */
    double lambda;
    /** arrival rate */
    double mu;
    /** service rate */

    /**
     * number of customers to be served. (simulation is done after Nth customer
     * served)
     */
    double N;
    double Wthresold;


    private int n=0;

    public CrazyMarket2021(double lambda, double mu, int n) {
        // TODO: assign lambda mu, etc
        this.lambda=lambda;
        this.mu=mu;
        this.n=n;

    }

    QServer qServer = new QServer();
    QLottery qLottery = new QLottery();



    /* variables for statistics */
    double meanWaitingTime = 0; // mean waiting time of SERVED customers
    double totalWaitingTime = 0; // total waiting time of SERVED customers
    double meanServiceTime = 0; // mean service time of SERVED customers
    double totalServiceTime = 0; // total service time of SERVED customers





    /**
     *
     */
    public void simulateMarket() {
        double nextArrivalTime=0;
        double nextRemovalTime=0;



        for(int b=0;b<3;b++){
            Customer c=new Customer();
            qLottery.enqueue(c);

        }

        for(int a=0;a<n;a++){
            Customer c=new Customer();
            qServer.enqueue(c);

        }



        while (N<n) {
            Random random=new Random();
            double randomArrive=random.nextDouble(1);
            double interArrivalTime=-Math.log(randomArrive)/(lambda);
            nextArrivalTime+=interArrivalTime;



            double randomService=random.nextDouble();
            qServer.peek().serviceTime=-Math.log(randomService)/(mu);
            totalServiceTime+=qServer.peek().serviceTime;
            Wthresold=qServer.peek().serviceTime;


            qServer.peek().removalTime=qServer.peek().arrivalTime+qServer.peek().serviceTime;

            if(nextRemovalTime<nextArrivalTime){
                nextRemovalTime=nextArrivalTime+qServer.peek().serviceTime;
            }
            else{
                nextRemovalTime+=qServer.peek().serviceTime;
            }

            qServer.peek().waitingTime=Math.abs(nextRemovalTime-nextArrivalTime);
            totalWaitingTime+=qServer.peek().waitingTime;




            if (qServer.peek().waitingTime>=Wthresold) {// arrival event

                qServer.dequeue();


            } else  { // departure event

                Random random2=new Random();
                qLottery.enqueue(qServer.peek());
                qServer.dequeue();
                int pose=random2.nextInt(qLottery.size());

                qLottery.dequeue(pose);

                if(qServer.size()==0){//son kalan sabit musterilerin hizmet almasi icin
                    if(qLottery.size()>0){
                        int pose2=random2.nextInt(qLottery.size());
                        qLottery.dequeue(pose2);}
                    else{
                        qLottery.dequeue(0);
                    }
                }
            }
            N++;
        }
        meanWaitingTime=totalWaitingTime/n;
        meanServiceTime=totalServiceTime/n;

    }

    public void printStatistics() {
        System.out.println("totalWaitingTime:" + totalWaitingTime);
        System.out.println("meanWaitingTime:" + meanWaitingTime);
        System.out.println("totalServiceTime:" + totalServiceTime);
        System.out.println("meanServiceTime:" + meanServiceTime);
    }



    public static void main(String[] args) {
        double lambda = Double.parseDouble(args[0]);
        double mu = Double.parseDouble(args[1]);
        int N = 1000;
        CrazyMarket2021 cm = new CrazyMarket2021(lambda, mu, N);


        cm.simulateMarket();
        cm.printStatistics();

    }

}