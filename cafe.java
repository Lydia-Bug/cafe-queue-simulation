import java.util.*;
public class cafe
{
    double amountConstant;
    int StudentsPerTeachers; //Student to teacher ratio
    int maxQueueLength;
    int servingTime; //should this be random???
    boolean teachersCut;
    
    public cafe(){
        queue cafeQueue = new queue();
        askVariables();
        modelQueue(amountConstant, cafeQueue); //adds the rest of the queue for the hour, 3 is a constant for formula
        //cafeQueue.modelQueue(amountConstant);
        outputvariables(cafeQueue);
    }
    
    void outputvariables(queue cafeQueue){
        int hungryStudents = cafeQueue.hungryStudents() + cafeQueue.QueueLength(cafeQueue.head);
        System.out.println("Hungry students: " + hungryStudents);
        System.out.println("Served students: " + cafeQueue.notHungryStudents());
        //double averageWaitTime = (cafeQueue.totalWaitTime() / cafeQueue.notHungryStudents())/60.00000;
        double averageWaitTime = (cafeQueue.totalWaitTime() / cafeQueue.notHungryStudents());
        System.out.println(cafeQueue.totalWaitTime());
        System.out.println(cafeQueue.notHungryStudents());
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
    
    void modelQueue(double amountConstant , queue cafeQueue){
        int timeBeingServed = 0;
        for(int i = 1; i<(3600/*seconds in hour*/); i++){ //does action every second of the hour
            for(int j = 0; j < queuersAdded(amountConstant, i); j++){ 
                System.out.println("startTime i: " + i);
                cafeQueue.addQueuers(i, maxQueueLength , teachersCut , StudentsPerTeachers ); 
            }
            
            timeBeingServed = cafeQueue.servePerson(timeBeingServed); //puts people in 'serving area'
            timeBeingServed++; //keeps track of how long someone is being served
            cafeQueue.finishServing(i, timeBeingServed, servingTime);
        }
    }
    
    int queuersAdded(double amountConstant, int i){ //figures out how many queuers to add
        double numberJoiningDecimal = 0; //calculated value for people joining queue
        int numberJoining; //amount of whole numbers in initial calculated value
        double chanceJoining; //decimal amount from initial calculated value
        
        numberJoiningDecimal = (-amountConstant/3600.00000)*i+amountConstant;//This is the amount of people who will join the queue each second
        numberJoining = (int)numberJoiningDecimal; //finds amount of whole numbers in numberJoiningDecimal
        chanceJoining = numberJoiningDecimal - numberJoining; //percentage chance another person will join
  
        Random rand = new Random(); //finds whether another person is added
        double n = (rand.nextInt(1000)/1000.00000);
        if(chanceJoining > n) numberJoining ++; //adds one more person if so
        
        return numberJoining;
    }
}
