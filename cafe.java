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
        cafeQueue.askVariables();
        cafeQueue.modelQueue(amountConstant); //adds the rest of the queue for the hour, 3 is a constant for formula
        
        System.out.println("Hungry students: " + cafeQueue.hungryStudents());
        System.out.println("Served students: " + cafeQueue.notHungryStudents());
        double averageWaitTime = (cafeQueue.totalWaitTime() / cafeQueue.notHungryStudents())/60;
        System.out.println("Average wait time: " + averageWaitTime + "mins");
    }
}
