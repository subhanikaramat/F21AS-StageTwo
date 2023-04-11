import java.util.ArrayList;

public class Intersection {
  public int freeLane;                   // Number of free lanes in the intersection
  public int InitialLane;                // Initial lane of the intersection
  public int passingCarsGo;             // Number of passing cars in the intersection
  public int exeTime;                    // Execution time of the intersection
  public int maxPedestrians;            // Maximum number of pedestrians in the intersection
  public int noCarsHighPriority;       // Number of cars with high priority in the intersection
  public int noCarsLowPriority;        // Number of cars with low priority in the intersection
  public int noLanes;                   // Number of lanes in the intersection
  public int roundaboutPassingTime;  // Time taken for cars to pass through the roundabout
  public int maxCarsPassingAtOnce;  // Maximum number of cars passing through the intersection at once
  public int exactNoOfCarsPassingAtOnce;  // Exact number of cars passing through the intersection at once
  public int noCarsPassingAtOnce;    // Number of cars passing through the intersection at once
  public ArrayList<Car> cars;       // ArrayList to store cars in the intersection
}
