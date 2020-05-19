package pack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here


//do something

        ArrayList<City> cities = TSPSolver.readFile("instances/C110_1.TXT"/*put your file path here to test with different instances*/);
        long startTime = System.currentTimeMillis();
        cities = TSPSolver.solveProblem(cities);
       
        Double totalDistance = TSPSolver.printSolution(cities);
        System.out.printf("Distances: %f\n", totalDistance);
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        //writeFile("1. "+String.valueOf(TSPSolver.printSolution(cities))+"  "+TSPSolver.printSolution2(cities),"res/ans.txt");
        /*for(City c:cities){
            System.out.println(c.toString());
        }
        City x = new City(100,23,40);
        ArrayList<City> a = new ArrayList<City>();
        a.add(new City(0,100,1));
        a.add(x);
        ArrayList<City> b = new ArrayList<City>();
        b.add(x);
        System.out.println(a.get(1).toString()+" "+!a.contains(b.get(0)));*/

    }

    public static void writeFile(String fileContent, String filePath) {
        Path path = Paths.get(filePath);
        try {
            BufferedWriter writer =
                    Files.newBufferedWriter(path, StandardOpenOption.APPEND);
            writer.write(fileContent);
            writer.close(); // must close!
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
