public class queue
{
    person first; //first person to join queue
    person head; //person at front of queue
    person tail; //person at end of queue
    
    public queue()
    {
        System.out.println("10 people added to queue");
        addFirstPerson(); //adds the first person, that people can follow
        addQueuers(10); //add followers to first person
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
            queuer = new person(i); //creates new queuer with id
            tail.addfollower(queuer); //adds them to end of queue
            tail = tail.follower(); //sets last in queue to them
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
