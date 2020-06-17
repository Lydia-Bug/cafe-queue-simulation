
public class person
{
  private person follower; //variable for person following person
  private int id; //identifier to use for testing
  public person(int id){ //creates a new person object 
      this.id = id;
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
}
