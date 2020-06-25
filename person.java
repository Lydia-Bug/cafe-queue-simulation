
public class person
{
  private person follower; //variable for person following person
  private int id; //identifier to use for testing
  private boolean student; //teacher or students? if true: student
  private int startTime;
  private int waitingTime;
  public person(int id, boolean student){ //creates a new person object 
      this.id = id;
      this.student = student;
  }
  public person(boolean student, int srartTime){ //creates a new person object 
      this.student = student;
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
  public boolean myRole(){
      return student;
  }
  public int startTime(){
      return startTime;
  }
}
