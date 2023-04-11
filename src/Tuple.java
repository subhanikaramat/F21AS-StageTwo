public class Tuple {
  public int tid;         // Tuple ID
  public String action;  // Action associated with the tuple
  public int lane;       // Lane associated with the tuple
  
  // Constructor with two parameters: ID and action
  public Tuple(int id, String action) {
    this.tid = id;
    this.action = action;
  }
  
  // Constructor with three parameters: ID, action, and lane
  public Tuple(int id, String action, int lane) {
    this.tid = id;
    this.action = action;
    this.lane = lane;
  }
}
