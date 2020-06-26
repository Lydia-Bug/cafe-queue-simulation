
public class cafe
{
    public cafe(){
        queue Queue = new queue();
        
        Queue.askVariables();
        modelQueue(amountConstant); //adds the rest of the queue for the hour, 3 is a constant for formula
        System.out.println("Hungry students: " + hungryStudents);
        System.out.println("Served students: " + notHungryStudents);
        double averageWaitTime = (totalWaitTime / notHungryStudents)/60;
        System.out.println("Average wait time: " + averageWaitTime + "mins");
    }
}
