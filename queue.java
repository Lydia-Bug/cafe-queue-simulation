import java.util.Random;
public class queue
{
    person first; //first person to join queue
    person head; //person at front of queue
    person tail; //person at end of queue
    person second; //person second in queue (used as a placeholder for puting someone at head of queue)
    person served = null; //person in 'serving area'
   
    int hungryStudents;
    int notHungryStudents;
    int timeBeingServed;
    int totalWaitTime;
    
    public queue(){
        head = null;
        tail = null;
        totalWaitTime = 0;
    }
    
    void addFirstPerson(person queuer){ 
        head = queuer; //sets first in queue to them
        tail = queuer; //sets last in queue to them
    }
    
    boolean isStudent(int StudentsPerTeachers){ //finds whether teach or student is added
        Random rand = new Random(); 
        int n = (rand.nextInt(StudentsPerTeachers))+1;
        if(n >= StudentsPerTeachers) {
            return false; //is teacher
        } else {   
            return true; // is student
        }
    }
    
    void addQueuers(int startTime , int maxQueueLength, boolean teachersCut , int StudentsPerTeachers ){ //adds individual queuers
        person queuer; //creates queuer variable
        if(teachersCut){
            if(!isStudent(StudentsPerTeachers)){
                queuer = new person(false, startTime);
                if(head == null){ 
                    addFirstPerson(queuer);
                }else{
                    second = head; //makes first in queue, second in queue
                    head = queuer; //puts teacher in head of queue
                    queuer.addfollower(second); //adds second as follower to head
                }
            }
        }else{
            if(isStudent(StudentsPerTeachers)){
                queuer = new person(true, startTime); 
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
    
    int QueueLength(person t){ //method for finding queue length
        int queueLength = 0;
        while (t != null){
            t=t.follower();
            queueLength++;
        }
        return queueLength;
    }
    
    
    int servePerson(int timeBeingServed){
        if (served == null){
            if (head != null){
                if(head.follower() == null){ //if no second person
                    served = head;
                    head = null;
                }else{ //if second person, that now needs to become head
                    second = head.follower();
                    served = head;
                    head = second; //Sets the second person in queue as the head of the queue
                }
            }
            return 0; //serving time is reset to 0;
        
        } else{
            return timeBeingServed;
        }
    }
    
    void finishServing(int endTime, int timeBeingServed , int servingTime){
        if(timeBeingServed >= servingTime){
            totalWaitTime += (endTime - served.startTime());
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
    
    int hungryStudents(){
        return hungryStudents;
    }
    int notHungryStudents(){
        return notHungryStudents;
    }
    int totalWaitTime(){
        return totalWaitTime;
    }
    person head(){
        return head;
    }
}
