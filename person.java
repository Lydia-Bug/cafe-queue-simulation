
public class person
{
  private person follower;
  private int id;
  
  public person(int id){
      this.id = id;
  }
  public void addfollower(person follower){
      this.follower = follower;
  }
  public person follower(){
      return follower;
  }
  public int myId(){
      return id;
  }
}
