import java.util.Random;
public class queue
{
    person first; //first person to join queue
    person head; //person at front of queue
    person tail; //person at end of queue
    person second; //person second in queue (used as a placeholder for puting someone at head of queue)
    person served = null; //person in 'serving area'
    
    double amountConstant = 0.25;
    int StudentsPerTeachers = 10; //Student to teacher ratio
    int maxQueueLength = 125;
    int hungryStudents = 0;
    int notHungryStudents = 0;
    int servingTime = 10; //should this be random???
    int timeBeingServed;
    
    double numberJoiningDecimal = 0; //calculated value for people joining queue
    int numberJoining; //amount of whole numbers in initial calculated value
    double chanceJoining; //decimal amount from initial calculated value
    public queue()
    {
        modelQueue(amountConstant); //adds the rest of the queue for the hour, 3 is a constant for formula
        printQueue(head); //prints out the id's of people in queue, from head
        System.out.println("Hungry students: " + hungryStudents);
        System.out.println("Served students: " + notHungryStudents);
        System.out.println("Queue length: " + QueueLength(head));
    }
    
    void modelQueue(double value){
        for(int i = 1; i<(3600/*seconds in hour*/); i++){ //does action every second of the hour
            modelAddQueuers(value, i); //adds queuers
            servePerson(); //puts people in 'serving area'
            timeBeingServed++; //keeps track of how long someone is being served
            finishServing();
        }
    }
    
    void addFirstPerson(person queuer){ 
        head = queuer; //sets first in queue to them
        tail = queuer; //sets last in queue to them
    }
    
    void addQueuers(int amount){ //adds individual queuers
        person queuer; //creates queuer variable
        for(int i = 1; i<(amount+1); i++){
            queuer = null;
            Random rand = new Random(); 
            int n = (rand.nextInt(StudentsPerTeachers))+1;
            if(n >= StudentsPerTeachers){ ///creates teacher
                queuer = new person(false);
                if(head == null){ 
                    addFirstPerson(queuer);
                }else{
                    second = head; //makes first in queue, second in queue
                    head = queuer; //puts teacher in head of queue
                    queuer.addfollower(second); //adds second as follower to head
                }
            }
            if(n < StudentsPerTeachers){//creates student
                queuer = new person(true); 
                if(head == null){ //if queue is empty
                    addFirstPerson(queuer);
                }else{
                    if(QueueLength(head) < maxQueueLength){ //checks queue length 
                        tail.addfollower(queuer); //adds them to end of queue
                        tail = tail.follower(); //sets last in queue to them
                    }else{
                        hungryStudents++;
                    }
                }
            }
        }
    }
    
    void modelAddQueuers(double value, int i){ //figures out how many queuers to add
        numberJoiningDecimal = (-value/3600.00000)*i+value;//This is the amount of people who will join the queue each second
        numberJoining = (int)numberJoiningDecimal; //finds amount of whole numbers in numberJoiningDecimal
        chanceJoining = numberJoiningDecimal - numberJoining; //percentage chance another person will join
     
        addQueuers(numberJoining); //adds whole amount of queuers
        Random rand = new Random(); //finds whether another person is added
        double n = (rand.nextInt(1000)/1000.00000);
        if(chanceJoining > n){
            addQueuers(1); 
        }
    }
    
    int QueueLength(person t){ //method for finding queue length
        int queueLength = 0;
        while (t != null){
            t=t.follower();
            queueLength++;
        }
        return queueLength;
    }
    
    void servePerson(){
        if (served == null && head != null){ //checks if no one being served, and if there is head
            if(head.follower() == null){ //if no second person
                served = head;
                head = null;
            }else{ //if second person, that now needs to become head
                second = head.follower();
                served = head;
                head = second; //Sets the second person in queue as the head of the queue
            }
            timeBeingServed = 0;
        }
    }
    
    void finishServing(){
        if(timeBeingServed >= servingTime){
            served = null;
            notHungryStudents++;
            timeBeingServed = 0;
        }
    }
    
    void printQueue(person t){ //prints out queue
        System.out.println("The queue consists of:");
        while (t != null){
            System.out.println(t.myRole());
            //System.out.println(t.myId());
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