public class Car {
  public int carId;         // Car ID
  public int priority;     // Priority of the car
  public int sLane;        // Starting lane of the car
  public int eLane;        // Ending lane of the car
  public int waitTime;     // Wait time of the car
  
  // Constructors
  public Car(int id, int sLane) {
    this(id, sLane, -1, 0, 1);
  }
  
  public Car(int id, int sLane, int waitTime) {
    this(id, sLane, -1, waitTime, 1);
  }
  
  public Car(int id, int sLane, int eLane, int waitTime) {
    this(id, sLane, eLane, waitTime, 1);
  }
  
  public Car(int id, int sLane, int eLane, int waitTime, int priority) {
    this.carId = id;
    this.sLane = sLane;
    this.eLane = eLane;
    this.waitTime = waitTime;
    this.priority = priority;
  }
}
