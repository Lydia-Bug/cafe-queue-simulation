import java.util.*;
public class cafe
{
    double amountConstant = 0.25;
    int StudentsPerTeachers = 10; //Student to teacher ratio
    int maxQueueLength = 125;
    int hungryStudents = 0;
    int notHungryStudents = 0;
    int servingTime = 10; //should this be random???
    int timeBeingServed;
    boolean teachersCut;
    int totalWaitTime = 0;
    
    public cafe(){
        queue cafeQueue = new queue();
        askVariables();
        cafeQueue.modelQueue(amountConstant); //adds the rest of the queue for the hour, 3 is a constant for formula
        
        System.out.println("Hungry students: " + cafeQueue.hungryStudents());
        System.out.println("Served students: " + cafeQueue.notHungryStudents());
        double averageWaitTime = (cafeQueue.totalWaitTime() / cafeQueue.notHungryStudents())/60;
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
    }
}
