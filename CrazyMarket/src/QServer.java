/**
 * maintains a customer queue in a circular array TODO: methodlari implement
 * ediniz, fazla method-field ekleyebilirsiniz.
 */
public class QServer {
    int kapasite=1000;

    Customer[] queue=new Customer[kapasite];

    int front = 0, back = 0, sayac=0; // head&tail of queue

    /**
     * enqueue a customer to queue
     */
    boolean enqueue(Customer customer) {

        if(size()>=kapasite){
            System.out.println("kuyruk Dolu");


        }
        if(front==back+1){
            System.out.println("Kuyruk Dolu");
        }

        sayac++;

        back = (back)%kapasite;
        back++;
        queue[back-1]=customer;
        return true;

    }


    void printQueue(){
        for(int i=0;i<queue.length;i++){
            System.out.println(queue[i]);
        }
        System.out.println(sayac);

    }

    /**
     * dequeue a customer from queue
     *
     */
    Customer dequeue() {
        Customer cikan=new Customer();
        sayac--;

        if(isEmpty()){
            return null;
        }

        else{
            queue[front]=cikan;
            queue[front]=null;
            front=(front+1)%kapasite;
            return cikan;
        }

    }

    /**
     * peek a customer in queue
     *
     */
    Customer peek() {

        return queue[front];
    }

    /** kuruktaki toplam eleman sayisi */
    int size() {
        return sayac;
    }

    boolean isEmpty() {
        return sayac<=0;
    }


}