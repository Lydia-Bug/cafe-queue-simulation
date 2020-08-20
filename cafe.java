//asks for an outputs variables, also caculates how many people added
import java.util.*;
public class cafe
{
    double amountConstant; //variable that I'll apply to the equation that calculates how many people are added
    int StudentsPerTeachers; //Student to teacher ratio
    int maxQueueLength; //max queue length
    int servingTime; //time it takes someone to be served
    boolean teachersCut; //can teachers cut (true) in front or not (false)
    boolean simulate; //simulating queue (true) or reading in queue from CSV file (false)
    String filename = null; //name of file
    readIn file = new readIn(); //creates new file(class)
    
    public cafe(){
        System.out.println("This code will run a simualtion of a cafe queue");
        System.out.println("You can simulate the people being read into the queue, or read in you own numbers");
        System.out.println("");
        queue cafeQueue = new queue(); 
        askVariables();
        modelQueue(amountConstant, cafeQueue);
        outputvariables(cafeQueue);
    }
    
    //asks user to enter all values for variables
    void askVariables(){
        System.out.println("Do you want to simulate the queue or read it in from a file?");
        System.out.println("Type either simulate or read");
        simulate = checkSimulateOrRead(); //checks if value is "simulation" or "read", and returns boolean value if so
        if(simulate == true){ //asks for simulation requied variables
            System.out.println("Enter queuers constant, the higher the student constant the more queuers ");
            System.out.println("Suggested between 1 to 100 (can be higher then 100)");
            amountConstant = checkNegativeDouble(); //checks if number is negative, and returns value if so
            System.out.println("Enter amount of students per one teacher: ");
            StudentsPerTeachers = checkNegativeNumber();  //checks if number is negative, and returns value if so
        }else{ //asks for reading in requied variables
            System.out.println("What file do you want to read in? ");
            System.out.println("This code reads in CSV files, with 3600 rows (for each second) and two columbs (columb one for students and columb two for teachers)");
            filename = checkCSV(); //runs various checks on CSV file
            file.readInFile(filename); //reads in CSV file for later use
        }
        System.out.println("Enter maximum queue length (when the queue has reached that length students will no longer join the queue) ");
        maxQueueLength = checkNegativeNumber(); //checks if number is negative, and returns value if so
        System.out.println("Enter serving time (in seconds) ");
        servingTime = checkNumberAboveZero(); //checks if number is above zero, and returns value if so
        System.out.println("Can teachers cut to front of queue? (true if yes)" );
        teachersCut = checkIsBoolean(); //checks if value is boolean, and returns value if so
    }
    
    //checks that "simulation" or "read" has been entered, will return boolean if so
    boolean checkSimulateOrRead(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        boolean value = true;
        while(correct == false){ //loop will keep running until valid response
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
    
    //checks that int value isn't negitive, then returns value if so
    int checkNegativeNumber(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        int value = 0;
        while(correct == false){ //loop will keep running until valid response
            String tempValue = scan.nextLine(); //reads in as string because anything can be read in as an string
            try { 
               value = Integer.parseInt(tempValue); //atempts to set int value to the string value
               if (value < 0){ //checks value isn't negative
                   System.out.println("Please enter a number that isn't negitive");
               }else{
                    correct = true;
               }
            }catch (NumberFormatException e){
                System.out.println("Please enter a whole number under 2147483647");
            }
        }
        return value;
    }
    
    //checks value is positive, if so returns value
    int checkNumberAboveZero(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        int value = 0;
        while(correct == false){//loop will keep running until valid response
            String tempValue = scan.nextLine(); //reads in as string because anything can be read in as an string
            try { 
               value = Integer.parseInt(tempValue); //checks value is int
               if (value < 1){ //checks value is positive
                   System.out.println("Please enter a positive number");
               }else{
                    correct = true;
               }
            }catch (NumberFormatException e){
                System.out.println("Please enter a whole number under 2147483647");
            }
        }
        return value;
    }
    
    //checks value isn't negative, if so returns value
    double checkNegativeDouble(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        double value = 0;
        while(correct == false){//loop will keep running until valid response
            String tempValue = scan.nextLine(); //reads in as string because anything can be read in as an string
            try {
               value = Double.parseDouble(tempValue); //checks value is double
               if (value < 0){ //checks value isn't negative
                   System.out.println("Please enter a number that isn't negitive");
               }else{
                    correct = true;
               }
            }catch (NumberFormatException e){
                System.out.println("Please enter a number");
            }
        }
        //my calculation returns 'expected' results when the student constant is less then 1, but this is a bit confusing and arbitrary for the user, so I ask them to enter a higher number, then I make it smaller
        value = value/100; 
        return value;
    }
    
    //checks CSV file is valid, if so returns value
    String checkCSV(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        String value = null;
        while(correct == false){ //loop will keep running until valid response
            value = scan.nextLine();
            if(file.fileExists(value) == false){//if the files does exist
                System.out.println("Please enter valid file name make sure to include '.csv'");
            }else if(file.fileIsCSV(value) == false){//if the file is a CSV file
                System.out.println("Please enter valid file, this programe only excepts csv formate");
            }else if(file.checkColumbs(value) == false){ //checks there is only two columbs
                System.out.println("Please enter file with only two columbs");
            }else if (file.checkRows() == false){//checks amount of rows, and that all values are ints
                System.out.println("File must have 3600 columbs, and all ints");
            }else{correct = true;}
        }
        return value;
    }
    
    //checks value is boolean, if so returns value
    boolean checkIsBoolean(){
        Scanner scan = new Scanner(System.in);
        boolean correct = false;
        boolean value = true;
        while(correct == false){ //loop will keep running until valid response
            String tempValue = scan.nextLine(); //reads in as string because anything can be read in as an string
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
    
    //this function decides how many queuers are added every second
    void modelQueue(double amountConstant , queue cafeQueue){
        int timeBeingServed = 0; //time someone has been being served
        for(int i = 1; i<(3600/*seconds in hour*/); i++){ //does action every second of the hour
            if(simulate == true){ //if running simulation
                for(int j = 0; j < queuersAdded(amountConstant, i); j++){ //queuersAdded will calculate how many students to add
                    cafeQueue.addQueuers(i, maxQueueLength , teachersCut , isStudent()); //adds queuer
                }
            }else{ //if reading in CSV file
                for(int j = 0; j < file.students(i); j++){ //add students
                    cafeQueue.addQueuers(i, maxQueueLength , teachersCut , true); //adds queuer
                }
                for(int j = 0; j < file.teachers(i); j++){ //add teachers
                    cafeQueue.addQueuers(i, maxQueueLength , teachersCut , false); //adds queuer
                }
            }
            timeBeingServed++; //keeps track of how long someone is being served (if equal to timeServed then they'll leave queue)
            timeBeingServed = cafeQueue.servePerson(timeBeingServed); //puts people in 'serving area'
            cafeQueue.finishServing(i, timeBeingServed, servingTime); //will dequeue people who have been served
        }
    }
    
    //this code is used for simulating queue, it will calculate queuers added
    int queuersAdded(double amountConstant, int i){
        double numberJoiningDecimal = 0; //calculated value for people joining queue
        int numberJoining; //amount of whole numbers in initial calculated value
        double chanceJoining; //decimal amount from initial calculated value
        
        /*This equation will output a postive decimal, that decreases with time
          reaching zero at 3600 seconds */
        numberJoiningDecimal = (-amountConstant/3600.00000)*i+amountConstant;
        numberJoining = (int)numberJoiningDecimal; //this amount of whole numbers in decimal 
        chanceJoining = numberJoiningDecimal - numberJoining; //finds decimal portion of number (chance of someone being added to queue)
 
        //finds whether another person is added based on chance
        Random rand = new Random(); 
        double n = (rand.nextInt(1000)/1000.00000); //makes random number between 0 - 1
        if(chanceJoining > n) numberJoining ++; //adds one more person if chance is higher then random number
        
        return numberJoining;
    }
    
    //this code is used for simulating queue, it will pick weather a student of teacher is added
    boolean isStudent(){ //finds whether teach or student is added
        Random rand = new Random(); 
        if(StudentsPerTeachers == 0) return false; //if there is no students per teacher, teacher is added
        int n = (rand.nextInt(StudentsPerTeachers))+1; //generates random number between 1 - studentsPerTeachers
        if(n >= StudentsPerTeachers) { 
            return false; //is teacher
        } else {   
            return true; // is student
        }
    }
    
    //this will output all the values I want to output when the simulation is run
    void outputvariables(queue cafeQueue){
        System.out.println("");
        System.out.println("------------------------");
        //hungry people is the amount that didn't join queue because it was too long, and amount left after its been one hour
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
    
    //calculates average wait time
    String averageWaitTime(int totalWaitTime , int notHungryStudents){ //this formats average wait time: mm:ss
        if (notHungryStudents == 0){ //if no one was served
            return "No one was served";
        }
        int totalSeconds = totalWaitTime / notHungryStudents; //wait time in seconds
        //formats the wait time, example 6:09 instead of 369s
        int secondsInt = totalSeconds % 60; //amount of seconds more then amount of minutes in wait time
        String seconds = String.valueOf(secondsInt); //turn second int into string
        if (secondsInt < 10) seconds = "0" + seconds; //2 seconds will be shown as 0:02 not 0:2
        int minutes = (totalSeconds - secondsInt)/60; //find amount of minutes
        String averageWaitTime = minutes +":"+seconds; //puts minutes and second together
        return averageWaitTime;
    }
}