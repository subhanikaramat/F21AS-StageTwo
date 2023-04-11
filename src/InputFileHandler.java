
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputFileHandler {
  public static int numberCars;
  
  public static Intersection handleFile(String fileName) throws IOException {
    Intersection intersection1;
    int i2;
    String[] arrayOfString6;
    int i1;
    String[] arrayOfString5;
    int n;
    String[] arrayOfString4;
    int m;
    String[] arrayOfString3;
    Intersection in;
    int k;
    String[] arrayOfString2;
    int j;
    String[] arrayOfString1;
    int i;
    String[] lastLine;
    Intersection intersection3;
    int i3;
    String[] arrayOfString7;
    Intersection intersection2;
    String folderName = fileName.substring(0, fileName.length() - 2);
    String complete_path = "tests/" + folderName + "/" + fileName + ".in";
    BufferedReader br = new BufferedReader(new FileReader(new File(complete_path)));
    br.readLine();
    numberCars = Integer.parseInt(br.readLine());
    switch (folderName) {
      case "simple_semaphore":
      case "railroad":
        intersection1 = new Intersection();
        return intersection1;
      case "simple_n_roundabout":
        for (i2 = 0; i2 < numberCars; i2++)
          br.readLine(); 
        arrayOfString6 = br.readLine().split(" ");
        intersection3 = new Intersection();
        intersection3.maxCarsPassingAtOnce = Integer.parseInt(arrayOfString6[0]);
        intersection3.roundaboutPassingTime = Integer.parseInt(arrayOfString6[1]);
        return intersection3;
      case "simple_strict_1_car_roundabout":
        for (i1 = 0; i1 < numberCars; i1++)
          br.readLine(); 
        arrayOfString5 = br.readLine().split(" ");
        intersection3 = new Intersection();
        intersection3.noLanes = Integer.parseInt(arrayOfString5[0]);
        intersection3.roundaboutPassingTime = Integer.parseInt(arrayOfString5[1]);
        return intersection3;
      case "simple_strict_x_car_roundabout":
        for (n = 0; n < numberCars; n++)
          br.readLine(); 
        arrayOfString4 = br.readLine().split(" ");
        intersection3 = new Intersection();
        intersection3.noLanes = Integer.parseInt(arrayOfString4[0]);
        intersection3.roundaboutPassingTime = Integer.parseInt(arrayOfString4[1]);
        intersection3.exactNoOfCarsPassingAtOnce = Integer.parseInt(arrayOfString4[2]);
        return intersection3;
      case "simple_max_x_car_roundabout":
        for (m = 0; m < numberCars; m++)
          br.readLine(); 
        arrayOfString3 = br.readLine().split(" ");
        intersection3 = new Intersection();
        intersection3.noLanes = Integer.parseInt(arrayOfString3[0]);
        intersection3.roundaboutPassingTime = Integer.parseInt(arrayOfString3[1]);
        intersection3.maxCarsPassingAtOnce = Integer.parseInt(arrayOfString3[2]);
        return intersection3;
      case "priority_intersection":
        in = new Intersection();
        in.cars = new ArrayList<>();
        for (i3 = 0; i3 < numberCars; i3++) {
          String[] carLine = br.readLine().split(" ");
          in.cars.add(new Car(Integer.parseInt(carLine[0]), Integer.parseInt(carLine[1]), Integer.parseInt(carLine[2]), Integer.parseInt(carLine[3]), Integer.parseInt(carLine[4])));
        } 
        arrayOfString7 = br.readLine().split(" ");
        in.noCarsHighPriority = Integer.parseInt(arrayOfString7[0]);
        in.noCarsLowPriority = Integer.parseInt(arrayOfString7[1]);
        return in;
      case "crosswalk":
        for (k = 0; k < numberCars; k++)
          br.readLine(); 
        arrayOfString2 = br.readLine().split(" ");
        intersection2 = new Intersection();
        intersection2.exeTime = Integer.parseInt(arrayOfString2[0]);
        intersection2.maxPedestrians = Integer.parseInt(arrayOfString2[1]);
        return intersection2;
      case "simple_maintenance":
        for (j = 0; j < numberCars; j++)
          br.readLine(); 
        arrayOfString1 = br.readLine().split(" ");
        intersection2 = new Intersection();
        intersection2.noCarsPassingAtOnce = Integer.parseInt(arrayOfString1[0]);
        return intersection2;
      case "complex_maintenance":
        for (i = 0; i < numberCars; i++)
          br.readLine(); 
        lastLine = br.readLine().split(" ");
        intersection2 = new Intersection();
        intersection2.freeLane = Integer.parseInt(lastLine[0]);
        intersection2.InitialLane = Integer.parseInt(lastLine[1]);
        intersection2.passingCarsGo = Integer.parseInt(lastLine[2]);
        return intersection2;
    } 
    return null;
  }
}
