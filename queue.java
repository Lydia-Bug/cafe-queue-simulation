import java.util.Random;
public class queue
{
    person first; //first person to join queue
    person head; //person at front of queue
    person tail; //person at end of queue
    person second; //person second in queue (used as a placeholder for puting someone at head of queue)
    person served = null; //person in 'serving area'
   
    int hungryStudents; 
    int hungryTeachers;
    int notHungryStudents;
    int notHungryTeachers;
    int totalWaitTimeStudents;
    int totalWaitTimeTeachers;
    int timeBeingServed;
    
    //creates queue
    public queue(){ 
        //creates empty queue, with no totalWaitTime
        head = null;
        tail = null;
        totalWaitTimeStudents = 0;
        totalWaitTimeTeachers = 0;
    }
    
    //if there's no one in queue, and person added is added as head
    void addFirstPerson(person queuer){ 
        head = queuer; //sets first in queue to them
        tail = queuer; //sets last in queue to them
    }
    
    //method that will added queuers
    void addQueuers(int startTime , int maxQueueLength, boolean teachersCut , boolean isStudent){ //adds individual queuers
        person queuer; //creates queuer variable
        if(teachersCut == true && !isStudent){//if its a teacher that can cut
            if(!isStudent){ //if is a teacher
                queuer = new person(isStudent, startTime); //creates teacher
                if(head == null){ //if no one in queue, they'll be added to head
                    addFirstPerson(queuer);
                }else{ //puts them in head of queue
                    second = head; //makes first in queue, second in queue
                    head = queuer; //puts teacher in head of queue
                    queuer.addfollower(second); //adds second as follower to head
                }
            }
        }else{ 
            queuer = new person(isStudent, startTime); 
            if(QueueLength(head) < maxQueueLength){ //checks queue length 
                if(head == null){ //if queue is empty
                    addFirstPerson(queuer);
                }else{
                    tail.addfollower(queuer); //adds them to end of queue
                    tail = tail.follower(); //sets last in queue to them
                }
            }else{ //if queue if too long
                if(isStudent)hungryStudents++;
                if(!isStudent)hungryTeachers++;
            }
        }
    }
    
    //finds length of queue
    int QueueLength(person t){ //method for finding queue length
        int queueLength = 0;
        while (t != null){ //goes through queue and counts amount of followers
            t=t.follower();
            queueLength++;
        }
        return queueLength;
    }
    
    //find amount of teachers or students in queue (used to find hungry people)
    int QueueLengthPeople(person t , boolean isStudent){ //method for finding queue length
        int queueLengthPeople = 0;
        while (t != null){ //goes through queue counting either all students or teachers
            if  (t.isStudent() == isStudent)  queueLengthPeople++;
            t=t.follower();
        }
        return queueLengthPeople;
    }
    
    //this will place someone in 'serving area' to get served
    int servePerson(int timeBeingServed){
        if (served == null){ //if no one else is being served
            if (head != null){ //if someone is in queue
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
    
    //this dequeues someone if the've spent the 'servingTime' being served
    void finishServing(int endTime, int timeBeingServed , int servingTime){
        if(timeBeingServed >= servingTime){ //if time being served is more then servingTime
            if(served.isStudent() == true){
                totalWaitTimeStudents += (endTime - served.startTime()); //finds time spent in queue
                notHungryStudents++;
            }
            if(served.isStudent() == false){
                totalWaitTimeTeachers += (endTime - served.startTime()); //finds time spent in queue
                notHungryTeachers++;
            }
            served = null;
            timeBeingServed = 0;
        }
    }
    
    //returuns values, so cafe class can access them
    int hungryStudents(){
        return hungryStudents;
    }
    int hungryTeachers(){
        return hungryTeachers;
    }
    int notHungryStudents(){
        return notHungryStudents;
    }
    int notHungryTeachers(){
        return notHungryTeachers;
    }
    int totalWaitTimeStudents(){
        return totalWaitTimeStudents;
    }
    int totalWaitTimeTeachers(){
        return totalWaitTimeTeachers;
    }
    person head(){
        return head;
    }
}