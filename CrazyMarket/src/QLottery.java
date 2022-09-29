/**
 * TODO: indeksle cikarma islemenin ve ekleme isleminin verimli oldugu bir
 * implementasyon yapmaniz istenmektedir.
 */
class Node{
    Customer c;
    Node next;

    public Node(Customer c){
        this.c=c;
        this.next=null;
    }


}


public class QLottery {
    Node front = null, back = null;
    int sayac=0;




    boolean enqueue(Customer customer){
        sayac++;
        Node temp= new Node(customer);

        if(front==null){
            front=temp;
            back=temp;
        }

        else{
            back.next=temp;
            back=temp;

        }
        return true; }


    void dequeue(int pose){



        Node temp=front;
        sayac--;

        if(isEmpty()){
            back=null;
        }

        if(pose==0){
            front=temp.next;
            //front=null;
            return;
        }

        for(int  j=0;temp!=null&&j<pose-1;j++){
            temp=temp.next;}

        Node next=temp.next;
        temp.next=next;


    }

    int size(){

        return sayac;}

    Customer peek(){

        return front.c;}

    boolean isEmpty(){

        return sayac<0;}

    void printQueue(){
        Node t=front;
        int i=0;
        while(t!=null){
            i++;
            System.out.println(i+"--"+t.c);
            t=t.next;
        }
        System.out.println("-----");
    }

}