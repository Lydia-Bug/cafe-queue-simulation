
public class person
{
  private person follower; //variable for person following person
  private int id; //identifier to use for testing
  private boolean isStudent; //teacher or students? if true: student
  private int startTime;
  private int waitingTime;
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
  public int myId(){ //returns id
      return id;
  }
  public boolean isStudent(){
      return isStudent;
  }
  public int startTime(){
      return startTime;
  }
}
