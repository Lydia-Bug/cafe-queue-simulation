import java.util.*;
public class cafe
{
    double amountConstant;
    int StudentsPerTeachers; //Student to teacher ratio
    int maxQueueLength;
    int servingTime; //should this be random???
    boolean teachersCut;
    boolean simulate;
    String filename = null;
    
    public cafe(){
        queue cafeQueue = new queue();
        askVariables();
        modelQueue(amountConstant, cafeQueue); //adds the rest of the queue for the hour, 3 is a constant for formula
        //cafeQueue.modelQueue(amountConstant);
        outputvariables(cafeQueue);
    }
    
    void outputvariables(queue cafeQueue){
        int hungryStudents = cafeQueue.hungryStudents() + cafeQueue.QueueLengthPeople(cafeQueue.head , true);
        System.out.println("Hungry students: " + hungryStudents);
        int hungryTeachers = cafeQueue.hungryTeachers() + cafeQueue.QueueLengthPeople(cafeQueue.head , false);
        System.out.println("Hungry teachers: " + hungryTeachers);
        System.out.println("Served students: " + cafeQueue.notHungryStudents());
        System.out.println("Served teachers: " + cafeQueue.notHungryTeachers());
        String averageWaitTimeS = averageWaitTime(cafeQueue.totalWaitTimeStudents() , cafeQueue.notHungryStudents());
        String averageWaitTimeT = averageWaitTime(cafeQueue.totalWaitTimeTeachers() , cafeQueue.notHungryTeachers());
        System.out.println("Student average wait time: " + averageWaitTimeS);
        System.out.println("Teacher average wait time: " + averageWaitTimeT);
    }
    
    String averageWaitTime(int totalWaitTime , int notHungryStudents){ //this formats average wait time: mm:ss
        if (notHungryStudents == 0){
            return "No one was served";
        }
        int totalSeconds = totalWaitTime / notHungryStudents; 
        int secondsInt = totalSeconds % 60;
        String seconds = String.valueOf(secondsInt);
        if (secondsInt < 10) seconds = "0" + seconds; //2 seconds will be shown as 0:02 not 0:2
        int minutes = (totalSeconds - secondsInt)/60;
        String averageWaitTime = minutes +":"+seconds;
        return averageWaitTime;
    }
    
    void askVariables(){
        System.out.println("Do you want to simulate or read in the queue");
        simulate = checkSimulateOrRead();
        if(simulate == true){
            System.out.println("Enter student constant: ");
            amountConstant = checkNegativeDouble();
            System.out.println("Enter amount of students per one teacher: ");
            StudentsPerTeachers = checkNegativeNumber();  
        }else{ //if reading in
            System.out.println("What file do you want to read in? ");
            filename = checkCSV();
        }
        System.out.println("Enter maximum queue length that students will join: ");
        maxQueueLength = checkNegativeNumber();
        System.out.println("Enter serving time: ");
        servingTime = checkNumberAboveZero();
        System.out.println("Can teachers cut to front of queue? (true if yes)" );
        teachersCut = checkIsBoolean();
    }
    
    boolean checkSimulateOrRead(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        boolean value = true;
        while(correct == false){
            String tempValue = scan.nextLine();
            if(tempValue.equals("simulate")) {
                value= true;
                correct = true;
            }   else if (tempValue.equals("read")){  
                value = false;
                correct = true;
            }   else{
                correct = false;
                System.out.println("Please type either simulate or read");
            }
        }  
        return value;
    }
    
    int checkNegativeNumber(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        int value = 0;
        while(correct == false){
            String tempValue = scan.nextLine();
            try { //used to make sure number entered is number (if I don't use try then I'll get an error when I try to change a string into an int
               value = Integer.parseInt(tempValue);
               if (value < 0){
                   System.out.println("Please enter a number that isn't negitive");
               }else{
                    correct = true;
               }
            }catch (NumberFormatException e){
                System.out.println("Please enter a whole number");
            }
        }
        return value;
    }
    
    int checkNumberAboveZero(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        int value = 0;
        while(correct == false){
            String tempValue = scan.nextLine();
            try { //used to make sure number entered is number (if I don't use try then I'll get an error when I try to change a string into an int
               value = Integer.parseInt(tempValue);
               if (value < 1){
                   System.out.println("Please enter a positive number");
               }else{
                    correct = true;
               }
            }catch (NumberFormatException e){
                System.out.println("Please enter a whole number");
            }
        }
        return value;
    }
    
    double checkNegativeDouble(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        double value = 0;
        while(correct == false){
            String tempValue = scan.nextLine();
            try { //used to make sure number entered is number (if I don't use try then I'll get an error when I try to change a string into an int
               value = Double.parseDouble(tempValue);
               if (value < 0){
                   System.out.println("Please enter a number that isn't negitive");
               }else{
                    correct = true;
               }
            }catch (NumberFormatException e){
                System.out.println("Please enter a number");
            }
        }
        return value;
    }
    
    String checkCSV(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        readIn file = new readIn();
        String value = null;
        while(correct == false){
            value = scan.nextLine();
            boolean fileExists = file.fileExists(value);
            boolean isCSV = file.fileIsCSV(value);
            if(fileExists == false){
                System.out.println("Please enter valid file name make sure to include '.csv'");
            }else if(isCSV == false){
                System.out.println("Please enter valid file, this programe only excepts csv formate");
            }else{
               correct = true; 
            }
        }
        return value;
    }
    
    boolean checkIsBoolean(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        boolean value = true;
        while(correct == false){
            String tempValue = scan.nextLine();
            if(tempValue.equals("true"))  {
                value = true;
                correct = true;
            }else if(tempValue.equals("false")){
                value = false;
                correct = true;
            }else{
                System.out.println("Please enter either true or false");
            }
        }
        return value;
    }
    
    void modelQueue(double amountConstant , queue cafeQueue){
        int timeBeingServed = 0;
        readIn file = new readIn();
        for(int i = 1; i<(3600/*seconds in hour*/); i++){ //does action every second of the hour
            if(simulate == true){
                for(int j = 0; j < queuersAdded(amountConstant, i); j++){ //for simulating whos added
                    cafeQueue.addQueuers(i, maxQueueLength , teachersCut , isStudent()); 
                }
            }else{
                file.readInFile(filename);
                for(int j = 0; j < file.students(i); j++){ //add students
                    cafeQueue.addQueuers(i, maxQueueLength , teachersCut , true); 
                }
                for(int j = 0; j < file.teachers(i); j++){ //add teachers
                    cafeQueue.addQueuers(i, maxQueueLength , teachersCut , false); 
                }
            }
            timeBeingServed++; //keeps track of how long someone is being served
            timeBeingServed = cafeQueue.servePerson(timeBeingServed); //puts people in 'serving area'
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
    
    boolean isStudent(){ //finds whether teach or student is added
        Random rand = new Random(); 
        int n = (rand.nextInt(StudentsPerTeachers))+1;
        if(n >= StudentsPerTeachers) {
            return false; //is teacher
        } else {   
            return true; // is student
        }
    }
}