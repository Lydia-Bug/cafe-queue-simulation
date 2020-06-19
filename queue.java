
import java.util.Random;
public class queue
{
    person first; //first person to join queue
    person head; //person at front of queue
    person tail; //person at end of queue
    
    public queue()
    {
        /* Test Code
        System.out.println("10 people added to queue");
        addQueuers(10); //add followers to first person
        */
        addFirstPerson(); //adds the first person, that people can follow
        modelQueuers(3); //adds the rest of the queue for the hour
        printQueue(head); //prints out the id's of people in queue, from head
    }
    
    void addFirstPerson(){
        first = new person(0); //creates new person with id 0
        tail = first; //sets first in queue to them
        head = first; //sets last in queue to them
    }
    
    void addQueuers(int amount){
        person queuer; //creates queuer variable
        for(int i = 1; i<(amount+1); i++){
            queuer = new person(tail.myId()+1); //creates new queuer with id
            tail.addfollower(queuer); //adds them to end of queue
            tail = tail.follower(); //sets last in queue to them
        }
    }
    
    void modelQueuers(int value){
        person queuer; //creates queuer variable
        double numberJoiningDecimal = 0; //calculated value for people joining queue
        int numberJoining; //amount of whole numbers in initial calculated value
        double chanceJoining; //decimal amount from initial calculated value
        for(int i = 1; i<(3600/*seconds in hour*/); i++){
            numberJoiningDecimal = (-value/3600.00000)*i+value;
            /* This is the amount of people who will join the queue each second
             * I found this formula, the students joining will go down and reach 0, at one hour
             * Value is a constent that can be changed to change the amount of students
             */ 
            numberJoining = (int)numberJoiningDecimal; //finds amount of whole numbers in numberJoiningDecimal
            chanceJoining = numberJoiningDecimal - numberJoining; //percentage chance another person will join
     
            addQueuers(numberJoining); //adds whole amount of queuers
            Random rand = new Random(); //finds whether another person is added
            double n = (rand.nextInt(1000)/1000.00000);
            if(chanceJoining > n){
               addQueuers(1); 
            }
        }
    }
    
    void leaveQueue(){
        head = head.follower(); //Sets the second person in queue as the head of the queue
    }
    
    void printQueue(person t){ //prints out queue
        System.out.println("The queue consists of:");
        while (t != null){
            System.out.println(t.myId());
            t=t.follower();
        }
    }
}
/*
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }*/