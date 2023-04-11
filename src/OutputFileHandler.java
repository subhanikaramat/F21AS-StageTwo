
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class OutputFileHandler {
  private static Object[] checkedLane;
private static int increment;

@SuppressWarnings({ "deprecation", "null" })
public static boolean handleFile(Path path) throws IOException {
    String base = path.toString().split(Pattern.quote("/"))[1];
    BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
    AtomicBoolean passed = new AtomicBoolean(true);
    String fileName = base.substring(0, base.length() - 4);
    Intersection intersection = InputFileHandler.handleFile(fileName);
    fileName = fileName.substring(0, fileName.length() - 2);
    String err_file = "err/" + base.substring(0, base.length() - 3) + "err";
    BufferedWriter bw = Files.newBufferedWriter(Paths.get(err_file, new String[0]), new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE });
    try {
      HashMap<String, Integer> hm;
      ArrayList<Tuple> checkedLane2 = null;
     // final int[] checkedCar = null;
//      int [] checkedLane;
      final String pattern1 = null;
      String ref_file;
      final AtomicInteger countLi = null;
      AtomicInteger countLines = null;
      int countLines2=0;
      AtomicInteger stopped;
      AtomicInteger atomicInteger2;
      ArrayList[] arrayOfArrayList2;
      ArrayList[] arrayOfArrayList1;
      AtomicInteger atomicInteger1;
      final String pattern2 = null;
      String command;
      AtomicBoolean colorGreen;
      AtomicInteger started;
      AtomicInteger countEntries;
      int i;
      AtomicInteger atomicInteger3;
      AtomicInteger stoppedCars;
      final AtomicInteger reachedCars = null;
      final String pattern3 = null;
      Process pro;
      int[] arrayOfInt1;
      AtomicInteger atomicInteger5;
      AtomicInteger atomicInteger4;
      AtomicInteger startedCars;
      AtomicInteger[] cnt_lane;
      final String pattern4 = null;
      int j;
      int k;
      ArrayList[] arrayOfArrayList3;
      int[] arrayOfInt2;
      LinkedList[] arrayOfLinkedList1;
      LinkedList[] arrayOfLinkedList2;
      int[] passingCarsInOneGoPerLane;
      int m;
      switch (fileName) {
        case "simple_semaphore":
          hm = new HashMap<>();
          stopped = new AtomicInteger(0);
          started = new AtomicInteger(0);
          atomicInteger5 = new AtomicInteger(0);
          br
            .lines()
            .map(line -> {
                if (countLines2 > InputFileHandler.numberCars * 2) {
                  try {
                    bw.write("Too many lines printed. Got " + countLines2 + ". Expected " + (InputFileHandler.numberCars * 2));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern23 = "^Car [0-9]+ has reached the semaphore, now waiting...$";
                String pattern24 = "^Car [0-9]+ has waited enough, now driving...$";
                if (!line.matches(pattern23) && !line.matches(pattern24)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                if (s[3].equals("reached"))
                  if (started.get() == 0) {
                    stopped.getAndIncrement();
                  } else {
                    try {
                      bw.write("All the cars should have reached before starting");
                      bw.newLine();
                    } catch (IOException e) {
                      e.printStackTrace();
                    } 
                    passed.set(false);
                  }  
                return s[0] + " " + s[1];
              }).forEach(s -> hm.put(s, Integer.valueOf(((Integer)hm.getOrDefault(s, Integer.valueOf(0))).intValue() + 1)));
          for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (((Integer)entry.getValue()).intValue() != 2) {
              try {
                bw.write((String)entry.getKey() + " had more than 2 actions");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
              break;
            } 
          } 
          break;
        case "simple_n_roundabout":
          final int[] checkedCar = new int[InputFileHandler.numberCars];
          atomicInteger2 = new AtomicInteger(0);
          countEntries = new AtomicInteger(0);
          for (i = 0; i < InputFileHandler.numberCars; i++)
            checkedCar[i] = 0; 
          br
            .lines()
            .map(line -> {
                AtomicInteger countLin = null;
				increment = countLin.getAndIncrement();
                if (countLines.get() > InputFileHandler.numberCars * 3) {
                  try {
                    bw.write("Too many lines printed. Got " + countLines.get() + ". Expected " + (InputFileHandler.numberCars * 3));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern25 = "^Car [0-9]+ has reached the roundabout, now waiting...$";
                String pattern26 = "^Car [0-9]+ has entered the roundabout$";
                String pattern27 = "^Car [0-9]+ has exited the roundabout after [0-9]+ seconds$";
                if (!line.matches(pattern25) && !line.matches(pattern25) && !line.matches(pattern27)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                return new Tuple(Integer.parseInt(s[1]), s[3]);
              }).forEach(p -> {
                switch (p.action) {
                  case "reached":
                    if (checkedCar[p.tid] != 0) {
                      try {
                        bw.write("Car " + p.tid + " has reached multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar[p.tid] = checkedCar[p.tid] + 1;
                    break;
                  case "entered":
                    if (checkedCar[p.tid] != 1) {
                      try {
                        bw.write("Car " + p.tid + " has entered multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (countEntries.get() == intersection.maxCarsPassingAtOnce) {
                      try {
                        bw.write("Number of entered cars at a time exceeded");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar[p.tid] = checkedCar[p.tid] + 1;
                    countEntries.getAndIncrement();
                    break;
                  case "exited":
                    if (checkedCar[p.tid] != 2) {
                      try {
                        bw.write("Car " + p.tid + " has not entered or has exited multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (countEntries.get() == 0) {
                      try {
                        bw.write("No car should have been in the intersection.");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar[p.tid] = checkedCar[p.tid] + 1;
                    countEntries.getAndDecrement();
                    break;
                } 
              });
          for (i = 0; i < InputFileHandler.numberCars; i++) {
            if (checkedCar[i] != 3) {
              try {
                bw.write("Car " + i + " had more than 3 actions");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
              break;
            } 
          } 
          if (atomicInteger2.get() != InputFileHandler.numberCars * 3) {
            try {
              bw.write("Not enough lines printed");
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          if (countEntries.get() != 0) {
            try {
              bw.write("Not all the cars have exited");
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "simple_strict_1_car_roundabout":
          final int[] checkedCar1 = new int[InputFileHandler.numberCars];
          atomicInteger2 = new AtomicInteger(0);
          for (i = 0; i < InputFileHandler.numberCars; i++)
            checkedCar1[i] = 0; 
          br
            .lines()
            .map(line -> {
                countLi.getAndIncrement();
                if (countLi.get() > InputFileHandler.numberCars * 3) {
                  try {
                    bw.write("Too many lines printed. Got " + countLi.get() + ". Expected " + (InputFileHandler.numberCars * 3));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern20 = "^Car [0-9]+ has reached the roundabout$";
                String pattern21 = "^Car [0-9]+ has entered the roundabout from lane [0-9]+$";
                String pattern22 = "^Car [0-9]+ has exited the roundabout after [0-9]+ seconds$";
                if (!line.matches(pattern1) && !line.matches(pattern2) && !line.matches(pattern3)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                return new Tuple(Integer.parseInt(s[1]), s[3]);
              }).forEach(p -> {
                switch (p.action) {
                  case "reached":
                    if (checkedCar1[p.tid] != 0) {
                      try {
                        bw.write("Car " + p.tid + " has reached multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar1[p.tid] = checkedCar1[p.tid] + 1;
                    break;
                  case "entered":
                    if (checkedCar1[p.tid] != 1) {
                      try {
                        bw.write("Car " + p.tid + " has entered multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar1[p.tid] = checkedCar1[p.tid] + 1;
                    break;
                  case "exited":
                    if (checkedCar1[p.tid] != 2) {
                      try {
                        bw.write("Car " + p.tid + " has exited multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar1[p.tid] = checkedCar1[p.tid] + 1;
                    break;
                } 
              });
          for (int n : checkedCar1) {
            if (n != 3) {
              try {
                bw.write("Not all the cars have made all 3 steps");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
            } 
          } 
          if (atomicInteger2.get() != InputFileHandler.numberCars * 3) {
            try {
              bw.write("Not enough lines printed. Got " + atomicInteger2.get() + ". Expected " + (InputFileHandler.numberCars * 3));
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "simple_strict_x_car_roundabout":
          final int[] checkedCar2 = new int[InputFileHandler.numberCars];
          arrayOfArrayList2 = new ArrayList[intersection.noLanes];
          atomicInteger3 = new AtomicInteger(0);
          atomicInteger4 = new AtomicInteger(0);
          for (k = 0; k < intersection.noLanes; k++)
            arrayOfArrayList2[k] = new ArrayList(); 
          br
            .lines()
            .map(line -> {
                countLi.getAndIncrement();
                if (countLi.get() > InputFileHandler.numberCars * 4) {
                  try {
                    bw.write("Too many lines printed. Got " + countLi.get() + ". Expected " + (InputFileHandler.numberCars * 4));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern14 = "^Car [0-9]+ has reached the roundabout, now waiting\\.\\.\\.$";
                String pattern15 = "^Car [0-9]+ was selected to enter the roundabout from lane [0-9]+$";
                String pattern16 = "^Car [0-9]+ has entered the roundabout from lane [0-9]+$";
                String pattern17 = "^Car [0-9]+ has exited the roundabout after [0-9]+ seconds$";
                if (!line.matches(pattern1) && !line.matches(pattern2) && !line.matches(pattern3) && !line.matches(pattern4)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                return s[3].equals("selected") ? new Tuple(Integer.parseInt(s[1]), s[3], Integer.parseInt(s[10])) : (s[3].equals("entered") ? new Tuple(Integer.parseInt(s[1]), s[3], Integer.parseInt(s[8])) : new Tuple(Integer.parseInt(s[1]), s[3]));
              }).forEach(p -> {
                boolean onLane;
                Tuple find_t;
                switch (p.action) {
                  case "reached":
                    if (reachedCars.get() == InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded. Got " + reachedCars.get() + ". Expected " + InputFileHandler.numberCars);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    reachedCars.getAndIncrement();
                    break;
                  case "selected":
                    if (reachedCars.get() != InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded. Got " + reachedCars.get() + ". Expected " + InputFileHandler.numberCars);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (checkedCar2[p.tid] != 0) {
                      try {
                        bw.write("Car " + p.tid + " was selected multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (((HashMap<String, Integer>) checkedLane[p.lane]).size() >= intersection.exactNoOfCarsPassingAtOnce) {
                      try {
                        bw.write("More than " + intersection.exactNoOfCarsPassingAtOnce + " cars from lane " + p.lane);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    ((ArrayList) checkedLane[p.lane]).add(p);
                    checkedCar2[p.tid] = checkedCar2[p.tid] + 1;
                    break;
                  case "entered":
                    if (reachedCars.get() != InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded. Got " + reachedCars.get() + ". Expected " + InputFileHandler.numberCars);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (checkedCar2[p.tid] != 1) {
                      try {
                        bw.write("Car " + p.tid + " has entered multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (((HashMap<String, Integer>) checkedLane[p.lane]).size() != intersection.exactNoOfCarsPassingAtOnce) {
                      try {
                        bw.write("Not exactly " + intersection.exactNoOfCarsPassingAtOnce + " cars from lane " + p.lane);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    onLane = false;
                    for (Tuple t : checkedLane2) {
                      if (t.tid == p.tid)
                        onLane = true; 
                    } 
                    if (!onLane) {
                      try {
                        bw.write("Car " + p.tid + " was not selected from lane " + p.lane + " when trying to enter");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar2[p.tid] = checkedCar2[p.tid] + 1;
                    break;
                  case "exited":
                    if (reachedCars.get() != InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded. Got " + reachedCars.get() + ". Expected " + InputFileHandler.numberCars);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (checkedCar2[p.tid] != 2) {
                      try {
                        bw.write("Car " + p.tid + " has exited multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    onLane = false;
                    find_t = null;
                    for (Object lane : checkedLane) {
                      boolean found = false;
                      for (Tuple t : checkedLane2) {
                        if (t.tid == p.tid) {
                          onLane = true;
                          found = true;
                          find_t = t;
                          break;
                        } 
                      } 
                      if (found) {
                        ((HashMap<String, Integer>) lane).remove(find_t);
                        break;
                      } 
                    } 
                    if (!onLane) {
                      try {
                        bw.write("Car " + p.tid + " was not linked to lane when trying to exit.");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar2[p.tid] = checkedCar2[p.tid] + 1;
                    break;
                } 
              });
          for (int cnt : checkedCar2) {
            if (cnt != 3) {
              try {
                bw.write("Not all the cars have made all 4 steps");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
              break;
            } 
          } 
          if (atomicInteger3.get() != InputFileHandler.numberCars * 4) {
            try {
              bw.write("Not enough lines printed. Got " + atomicInteger3.get() + ". Expected " + (InputFileHandler.numberCars * 4));
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "simple_max_x_car_roundabout":
          final int[] checkedCar7 = new int[InputFileHandler.numberCars];
          arrayOfArrayList1 = new ArrayList[intersection.noLanes];
          atomicInteger3 = new AtomicInteger(0);
          atomicInteger4 = new AtomicInteger(0);
          for (k = 0; k < intersection.noLanes; k++)
            arrayOfArrayList1[k] = new ArrayList(); 
          for (k = 0; k < InputFileHandler.numberCars; k++)
            checkedCar7[k] = 0; 
          br
            .lines()
            .map(line -> {
                countLi.getAndIncrement();
                if (countLi.get() > InputFileHandler.numberCars * 4) {
                  try {
                    bw.write("Too many lines printed. Got " + countLi.get() + ". Expected " + (InputFileHandler.numberCars * 4));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern12 = "^Car [0-9]+ has (reached|entered) the roundabout from lane [0-9]+$";
                String pattern13 = "^Car [0-9]+ has exited the roundabout after [0-9]+ seconds$";
                if (!line.matches(pattern1) && !line.matches(pattern2)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                return (s[3].equals("reached") || s[3].equals("entered")) ? new Tuple(Integer.parseInt(s[1]), s[3], Integer.parseInt(s[8])) : new Tuple(Integer.parseInt(s[1]), s[3]);
              }).forEach(p -> {
                boolean onLane;
                Tuple find_t;
                switch (p.action) {
                  case "reached":
                    if (reachedCars.get() == InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded.");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    reachedCars.getAndIncrement();
                    break;
                  case "entered":
                    if (reachedCars.get() > InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded.");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (checkedCar7[p.tid] != 0) {
                      try {
                        bw.write("Car " + p.tid + " has entered multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (((HashMap<String, Integer>) checkedLane[p.lane]).size() == intersection.maxCarsPassingAtOnce) {
                      try {
                        bw.write("Already " + intersection.exactNoOfCarsPassingAtOnce + " cars from lane " + p.lane);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    ((ArrayList<Tuple>) checkedLane[p.lane]).add(p);
                    checkedCar7[p.tid] = checkedCar7[p.tid] + 1;
                    break;
                  case "exited":
                    if (reachedCars.get() > InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars exceeded.");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (checkedCar7[p.tid] != 1) {
                      try {
                        bw.write("Car " + p.tid + " has exited multiple times");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    onLane = false;
                    find_t = null;
                    for (Tuple lane : checkedLane2) {
                      boolean found = false;
                      for (Tuple t : checkedLane2) {
                        if (t.tid == p.tid) {
                          onLane = true;
                          found = true;
                          find_t = t;
                          break;
                        } 
                      } 
                      if (found) {
                        ((Map<String, Integer>) lane).remove(find_t);
                        break;
                      } 
                    } 
                    if (!onLane) {
                      try {
                        bw.write("Car " + p.tid + " was not linked to lane when trying to exit.");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar7[p.tid] = checkedCar7[p.tid] + 1;
                    break;
                } 
              });
          for (int cnt : checkedCar7) {
            if (cnt != 2) {
              try {
                bw.write("Not all the cars have made all 3 steps");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
            } 
          } 
          if (atomicInteger3.get() + atomicInteger4.get() != InputFileHandler.numberCars * 4) {
            try {
              bw.write("Not enough lines printed. Got " + (atomicInteger3.get() + atomicInteger4.get()) + ". Expected " + (InputFileHandler.numberCars * 4));
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "railroad":
          final int[] checkedCar3 = new int[InputFileHandler.numberCars];
          atomicInteger1 = new AtomicInteger(0);
          stoppedCars = new AtomicInteger(0);
          startedCars = new AtomicInteger(0);
          arrayOfArrayList3 = new ArrayList[2];
          arrayOfArrayList3[0] = new ArrayList();
          arrayOfArrayList3[1] = new ArrayList();
          br
            .lines()
            .map(line -> {
                countLi.getAndIncrement();
                if (countLi.get() > InputFileHandler.numberCars * 2 + 1) {
                  try {
                    bw.write("Too many lines printed. Got " + countLi.get() + ". Expected " + (InputFileHandler.numberCars * 2 + 1));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern9 = "^Car [0-9]+ from side number [0-9]+ has stopped by the railroad$";
                String pattern10 = "^Car [0-9]+ from side number [0-9]+ has started driving$";
                String pattern11 = "^The train has passed, cars can now proceed$";
                if (!line.matches(pattern1) && !line.matches(pattern2) && !line.matches(pattern3)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).filter(line -> {
                String[] s = line.split(" ");
                if (!s[0].equals("Car") && !s[0].equals("The")) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return !line.equals("The train has passed, cars can now proceed");
              }).map(line -> {
                String[] s = line.split(" ");
                if (s[7].equals("stopped"))
                  stoppedCars.getAndIncrement(); 
                if (s[7].equals("started"))
                  startedCars.getAndIncrement(); 
                if (stoppedCars.get() > InputFileHandler.numberCars) {
                  try {
                    bw.write("Number of stopped cars " + stoppedCars.get() + " exceeded the total nr of cars " + InputFileHandler.numberCars);
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                if (startedCars.get() > InputFileHandler.numberCars) {
                  try {
                    bw.write("Number of stopped cars " + startedCars.get() + " exceeded the total nr of cars " + InputFileHandler.numberCars);
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return new Tuple(Integer.parseInt(s[1]), s[7], Integer.parseInt(s[5]));
              }).forEach(p -> {
                Object[] lanes = null;
				switch (p.action) {
                  case "stopped":
                    ((ArrayList<Tuple>) lanes[p.lane]).add(Integer.valueOf(p.tid), p);
                    checkedCar3[p.tid] = checkedCar3[p.tid] + 1;
                    break;
                  case "started":
                    if (p.tid!= p.tid) {
                      try {
                        bw.write("Car " + p.tid + " is not the head of its lane " + p.lane);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    ((HashMap<String, Integer>) lanes[p.lane]).remove(0);
                    if (checkedCar3[p.tid] != 1) {
                      try {
                        bw.write("Car " + p.tid + " has never stopped on lane " + p.lane);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar3[p.tid] = checkedCar3[p.tid] + 1;
                    break;
                } 
              });
          if (stoppedCars.get() != startedCars.get()) {
            try {
              bw.write("Number of started cars " + startedCars.get() + " is different from the number of stopped cars " + stoppedCars.get());
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          for (int cnt : checkedCar3) {
            if (cnt != 2) {
              try {
                bw.write("Not all the cars have made all 2 steps");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
              break;
            } 
          } 
          if (atomicInteger1.get() != InputFileHandler.numberCars * 2 + 1) {
            try {
              bw.write("Not enough lines printed. Got " + atomicInteger1.get() + ". Expected " + (InputFileHandler.numberCars * 2 + 1));
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "simple_maintenance":
          final int[] checkedCar4 = new int[InputFileHandler.numberCars];
          atomicInteger1 = new AtomicInteger(0);
          AtomicInteger reachedCars1 = new AtomicInteger(0);
          cnt_lane = new AtomicInteger[2];
          cnt_lane[0] = new AtomicInteger(0);
          cnt_lane[1] = new AtomicInteger(0);
          br
            .lines()
            .map(line -> {
                countLi.getAndIncrement();
                if (countLi.get() > InputFileHandler.numberCars * 2) {
                  try {
                    bw.write("Too many lines printed. Got " + countLi.get() + ". Expected " + (InputFileHandler.numberCars * 2));
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
                return line;
              }).filter(line -> {
                String pattern = "^Car [0-9]+ from side number [0-9]+ has (reached|passed) the bottleneck$";
                if (!line.matches(pattern)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                return new Tuple(Integer.parseInt(s[1]), s[7], Integer.parseInt(s[5]));
              }).forEach(p -> {
                switch (p.action) {
                  case "reached":
                    if (reachedCars1.get() == InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars " + reachedCars1.get() + " exceeded the total nr of cars " + InputFileHandler.numberCars);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar4[p.tid] = checkedCar4[p.tid] + 1;
                    reachedCars1.getAndIncrement();
                    break;
                  case "passed":
                    if (reachedCars1.get() > InputFileHandler.numberCars) {
                      try {
                        bw.write("Number of reached cars " + reachedCars1.get() + " exceeded the total nr of cars " + InputFileHandler.numberCars);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (cnt_lane[0].get() == 0 && cnt_lane[1].get() == 0 && p.lane == 1) {
                      try {
                        bw.write("First car expected to come from lane 0. Got " + p.lane);
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (cnt_lane[1 - p.lane].get() % intersection.noCarsPassingAtOnce != 0) {
                      try {
                        bw.write("Cars from lane " + p.lane + " went ahead of the other lane");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    if (checkedCar4[p.tid] != 1) {
                      try {
                        bw.write("Car " + p.tid + " has never reached the bottleneck");
                        bw.newLine();
                      } catch (IOException e) {
                        e.printStackTrace();
                      } 
                      passed.set(false);
                    } 
                    checkedCar4[p.tid] = checkedCar4[p.tid] + 1;
                    cnt_lane[p.lane].getAndIncrement();
                    break;
                } 
              });
          for (int cnt : checkedCar4) {
            if (cnt != 2) {
              try {
                bw.write("Not all the cars have made all 2 steps");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
              break;
            } 
          } 
          if (atomicInteger1.get() != InputFileHandler.numberCars * 2) {
            try {
              bw.write("Not enough lines printed. Got " + atomicInteger1.get() + ". Expected " + (InputFileHandler.numberCars * 2));
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "complex_maintenance":
          String pattern11 = "^Car [0-9]+ has come from the lane number [0-9]+$";
          String pattern21 = "^Car [0-9]+ from the lane [0-9]+ has entered lane number [0-9]+$";
          String pattern31 = "^The initial lane [0-9]+ has no permits and is moved to the back of the new lane queue$";
          String pattern41 = "^The initial lane [0-9]+ has been emptied and removed from the new lane queue$";
          arrayOfInt2 = new int[InputFileHandler.numberCars];
          arrayOfLinkedList1 = new LinkedList[intersection.freeLane];
          arrayOfLinkedList2 = new LinkedList[intersection.InitialLane];
          passingCarsInOneGoPerLane = new int[intersection.freeLane];
          for (m = 0; m < intersection.freeLane; m++) {
            arrayOfLinkedList1[m] = new LinkedList();
            int start = (int)(m * intersection.InitialLane / intersection.freeLane);
            int end = (int)((m + 1) * intersection.InitialLane / intersection.freeLane);
            if (end > intersection.InitialLane)
              end = intersection.InitialLane; 
            for (int n = start; n < end; n++)
              arrayOfLinkedList1[m].add(Integer.valueOf(n)); 
          } 
          for (m = 0; m < intersection.InitialLane; m++)
            arrayOfLinkedList2[m] = new LinkedList(); 
          for (m = 0; m < intersection.freeLane; m++)
            passingCarsInOneGoPerLane[m] = intersection.passingCarsGo; 
          for (m = 0; m < InputFileHandler.numberCars; m++) {
            String line = br.readLine();
            if (!line.matches(pattern11)) {
              try {
                bw.write("Wrong output");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
            String[] s = line.split(" ");
            int id = Integer.parseInt(s[1]);
            int lane = Integer.parseInt(s[8]);
            arrayOfLinkedList2[lane].add(Integer.valueOf(id));
            arrayOfInt2[id] = arrayOfInt2[id] + 1;
          } 
          while (true) {
            String line = br.readLine();
            if (line == null)
              break; 
            if (!line.matches(pattern21) && !line.matches(pattern31) && !line.matches(pattern41)) {
              try {
                bw.write("Wrong output");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
            String[] s = line.split(" ");
            if (s[2].equals("has")) {
              try {
                bw.write("More cars have reached the bottleneck than expected");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
            if (s[0].equals("Car")) {
              int id = Integer.parseInt(s[1]);
              int old_lane = Integer.parseInt(s[5]);
              int new_lane = Integer.parseInt(s[10]);
              if (((Integer)arrayOfLinkedList1[new_lane].get(0)).intValue() != old_lane) {
                try {
                  bw.write("The old lane " + old_lane + " is not the head in the new lane " + new_lane + " queue");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              if (((Integer)arrayOfLinkedList2[old_lane].get(0)).intValue() != id) {
                try {
                  bw.write("The car " + id + " is not the head of the old lane " + old_lane);
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              if (passingCarsInOneGoPerLane[new_lane] <= 0) {
                try {
                  bw.write("The old lane " + old_lane + " has no more car passing tokens");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              arrayOfLinkedList2[old_lane].poll();
              passingCarsInOneGoPerLane[new_lane] = passingCarsInOneGoPerLane[new_lane] - 1;
              arrayOfInt2[id] = arrayOfInt2[id] + 1;
              continue;
            } 
            if (s[6].equals("emptied")) {
              int old_lane = Integer.parseInt(s[3]);
              if (!arrayOfLinkedList2[old_lane].isEmpty()) {
                try {
                  bw.write("The old lane " + old_lane + " should not be empty");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              LinkedList<Integer> new_lane_found = null;
              int new_lane_id = 0;
              for (LinkedList<Integer> new_lane : arrayOfLinkedList1) {
                if (!new_lane.isEmpty() && ((Integer)new_lane.get(0)).intValue() == old_lane) {
                  new_lane_found = new_lane;
                  break;
                } 
                new_lane_id++;
              } 
              if (new_lane_found == null) {
                try {
                  bw.write("The old lane " + old_lane + " does not appear on any new lane");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              new_lane_found.poll();
              passingCarsInOneGoPerLane[new_lane_id] = intersection.passingCarsGo;
              continue;
            } 
            if (s[6].equals("permits")) {
              int old_lane = Integer.parseInt(s[3]);
              LinkedList<Integer> new_lane_found = null;
              int new_lane_id = 0;
              for (LinkedList<Integer> new_lane : arrayOfLinkedList1) {
                if (!new_lane.isEmpty() && ((Integer)new_lane.peek()).intValue() == old_lane) {
                  new_lane_found = new_lane;
                  break;
                } 
                new_lane_id++;
              } 
              if (passingCarsInOneGoPerLane[new_lane_id] != 0) {
                try {
                  bw.write("The old lane " + old_lane + " has no more car passing tokens");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              if (arrayOfLinkedList2[old_lane].isEmpty()) {
                try {
                  bw.write("The old lane " + old_lane + " is empty and should have been eliminated");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              if (((Integer)arrayOfLinkedList1[new_lane_id].get(0)).intValue() != old_lane) {
                try {
                  bw.write("The old lane " + old_lane + " is not the head in the new lane " + new_lane_id + " queue");
                  bw.newLine();
                  bw.flush();
                  bw.close();
                } catch (IOException e) {
                  e.printStackTrace();
                } 
                return false;
              } 
              arrayOfLinkedList1[new_lane_id].remove(0);
              arrayOfLinkedList1[new_lane_id].add(Integer.valueOf(old_lane));
              passingCarsInOneGoPerLane[new_lane_id] = intersection.passingCarsGo;
              continue;
            } 
            try {
              bw.write("Wrong operation");
              bw.newLine();
              bw.flush();
              bw.close();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            return false;
          } 
          for (LinkedList<Integer> new_lane : arrayOfLinkedList1) {
            if (!new_lane.isEmpty()) {
              try {
                bw.write("Final checkup: not every new lane queue is empty");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
          } 
          for (LinkedList<Integer> old_lane : arrayOfLinkedList2) {
            if (!old_lane.isEmpty()) {
              try {
                bw.write("Final checkup: not every old lane is empty");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
          } 
          for (m = 0; m < intersection.freeLane; m++) {
            if (passingCarsInOneGoPerLane[m] != intersection.passingCarsGo) {
              try {
                bw.write("Final checkup: not all the cars have passed correctly");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
          } 
          for (m = 0; m < InputFileHandler.numberCars; m++) {
            if (arrayOfInt2[m] != 2) {
              try {
                bw.write("Final checkup: not every car gone through each step of the problem");
                bw.newLine();
                bw.flush();
                bw.close();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return false;
            } 
          } 
          break;
        case "priority_intersection":
          ref_file = "ref/" + base.substring(0, base.length() - 3) + "ref";
          command = "diff " + path.toString() + " " + ref_file;
          pro = Runtime.getRuntime().exec(command);
          if (pro.getInputStream().read() != -1) {
            try {
              bw.write("The output and ref files differ");
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          break;
        case "crosswalk":
          final int[] checkedCar5 = new int[InputFileHandler.numberCars];
          final AtomicInteger  countLi1 = new AtomicInteger(0);
          colorGreen = new AtomicBoolean(false);
          arrayOfInt1 = new int[InputFileHandler.numberCars];
          for (j = 0; j < InputFileHandler.numberCars; j++)
            arrayOfInt1[j] = 1; 
          br
            .lines()
            .filter(line -> {
                String pattern = "^Car [0-9]+ has now (green|red) light$";
                if (!line.matches(pattern)) {
                  try {
                    bw.write("Wrong output");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                  return false;
                } 
                return true;
              }).map(line -> {
                String[] s = line.split(" ");
                return new Tuple(Integer.parseInt(s[1]), s[4]);
              }).forEach(p -> {
                if (p.action.equals("green")) {
                  if (countLi.get() == InputFileHandler.numberCars) {
                    try {
                      bw.write("Number of green cars exceeded");
                      bw.newLine();
                    } catch (IOException e) {
                      e.printStackTrace();
                    } 
                    passed.set(false);
                  } 
                  if (checkedCar5[p.tid] == 0) {
                    try {
                      bw.write("The previous colour should have been red");
                      bw.newLine();
                    } catch (IOException e) {
                      e.printStackTrace();
                    } 
                    passed.set(false);
                  } 
                  countLi.getAndIncrement();
                  checkedCar5[p.tid] = 0;
                } else if (p.action.equals("red")) {
                  if (countLi.get() == 0) {
                    try {
                      bw.write("Number of red cars exceeded");
                      bw.newLine();
                    } catch (IOException e) {
                      e.printStackTrace();
                    } 
                    passed.set(false);
                  } 
                  if (checkedCar5[p.tid] == 1) {
                    try {
                      bw.write("The previous colour should have been green");
                      bw.newLine();
                    } catch (IOException e) {
                      e.printStackTrace();
                    } 
                    passed.set(false);
                  } 
                  countLi.getAndDecrement();
                  checkedCar5[p.tid] = 1;
                } else {
                  try {
                    bw.write("Wrong command");
                    bw.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  } 
                  passed.set(false);
                } 
              });
          if (countLi.get() != InputFileHandler.numberCars) {
            try {
              bw.write("Number of cars is different than expected! Got " + countLi.get() + ". Expected " + InputFileHandler.numberCars);
              bw.newLine();
            } catch (IOException e) {
              e.printStackTrace();
            } 
            passed.set(false);
          } 
          for (j = 0; j < InputFileHandler.numberCars; j++) {
            if (arrayOfInt1[j] == 1) {
              try {
                bw.write("The last colour should have been green, but found red");
                bw.newLine();
              } catch (IOException e) {
                e.printStackTrace();
              } 
              passed.set(false);
            } 
          } 
          break;
      } 
    } catch (Exception e) {
      bw.write(Arrays.toString((Object[])e.getStackTrace()));
      bw.newLine();
    } 
    bw.flush();
    bw.close();
    return passed.get();
  }
}
