import java.util.*;
public class cafe
{
    double amountConstant;
    int StudentsPerTeachers; //Student to teacher ratio
    int maxQueueLength;
    int hungryStudents = 0;
    int notHungryStudents = 0;
    int servingTime; //should this be random???
    int timeBeingServed;
    boolean teachersCut;
    int totalWaitTime = 0;
    
    public cafe(){
        queue cafeQueue = new queue();
        askVariables();
        modelQueue(amountConstant, cafeQueue); //adds the rest of the queue for the hour, 3 is a constant for formula
        //cafeQueue.modelQueue(amountConstant);
        System.out.println("Hungry students: " + cafeQueue.hungryStudents());
        System.out.println("Served students: " + cafeQueue.notHungryStudents());
        double averageWaitTime = (cafeQueue.totalWaitTime() / cafeQueue.notHungryStudents())/60.0;
        System.out.println("Average wait time: " + averageWaitTime + "mins");
    }
    
    void askVariables(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter student constant: ");
        amountConstant = scan.nextDouble();
        System.out.println("Enter amount of students per one teacher: ");
        StudentsPerTeachers = scan.nextInt();
        System.out.println("Enter maximum queue length that students will join: ");
        maxQueueLength = scan.nextInt();
        System.out.println("Enter serving time: ");
        servingTime = scan.nextInt();
        System.out.println("Can teachers cut to front of queue? (true if yes)" );
        teachersCut = scan.nextBoolean();
    }
    
    void modelQueue(double value , queue cafeQueue){
        for(int i = 1; i<(3600/*seconds in hour*/); i++){ //does action every second of the hour
            for(int j = 0; j < cafeQueue.queuersAdded(value, i); j++){ 
                cafeQueue.addQueuers(i); 
            }
            
            timeBeingServed = cafeQueue.servePerson(timeBeingServed); //puts people in 'serving area'
            timeBeingServed++; //keeps track of how long someone is being served
            cafeQueue.finishServing(i, timeBeingServed, servingTime);
        }
        hungryStudents += cafeQueue.QueueLength(cafeQueue.head); //students who are still in queue at end of lunch, are hungry students
    }
}
