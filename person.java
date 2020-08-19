//stores values for person in queue
public class person
{
  private person follower; //variable for person following person
  private boolean isStudent; //teacher or students? if true: student
  private int startTime; //time they start in queue (used to find total time in queue)
  public person(boolean isStudent, int startTime){ //creates a new person object 
      this.isStudent = isStudent;
      this.startTime = startTime;
  }
  public void addfollower(person follower){ //adds follower
      this.follower = follower;
  }
  public person follower(){ //returns follower
      return follower;
  }
  public boolean isStudent(){ //returns wether teacher or student
      return isStudent;
  }
  public int startTime(){ //returns start time
      return startTime;
  }
}
