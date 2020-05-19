package pack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TSPSolver {
    //readFile() is almost the same as the one provided in CSE105, but with a handful changes.
    public static ArrayList<City> readFile(String filename) {
        ArrayList<City> cities = new ArrayList<>();
        Path path = Paths.get(filename);
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String lineContent;
            while ((lineContent = reader.readLine()) != null) {
                // "\\s" is the difference
                String[] temp = lineContent.split("\\s");
                // the three values will compose a new city
               cities.add(new City(Integer.parseInt(temp[0]),
                       Integer.parseInt(temp[1]),Integer.parseInt(temp[2])));
            }
            reader.close(); // must close!

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return cities;
    }


    public static ArrayList<City> solveProblem(ArrayList<City> citiesToVisit) {
        ArrayList<City> routine = new ArrayList<City>();
        int N = citiesToVisit.size();
        //initialize the distance matrix to improve efficiency.
        double[][] dist = new double[N][N];
        //fill the distance matrix
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                dist[i][j]= citiesToVisit.get(i).distance(citiesToVisit.get(j));
            }
        }
        //since it starts from city 0, we put it in first
        routine.add(citiesToVisit.get(0));
        //we don't know the minimum value so set the min to INF(but we can't so just set it at the max value of int)
        double min = Integer.MAX_VALUE;
        //now is the number of  the current city, next is that of next city
        int now=0,next = 1;
        //Start the loop to find solution
        for(int i =0;i<citiesToVisit.size()-1;i++){
            // for every current city, we need to find the next city for it.
            for(int j =1;j<citiesToVisit.size();j++){
                //System.out.println(j+" "+routine.contains(citiesToVisit.get(j)));
                // the next city should not have been visited and be the current city itself(distance=0), also according to definition the distance should be the least among all possible targets.
                if(!routine.contains(citiesToVisit.get(j))&&min>dist[now][j]){
                        min = dist[now][j];
                        next = j;
                }
            }
            //System.out.println(citiesToVisit.get(next).toString());
            //reset the value
            min = Integer.MAX_VALUE;
            //put it into the result arraylist
            routine.add(citiesToVisit.get(next));
            now = next;
        }
        /*
			provide code here. you are allowed to add your own functions outside
			But you are NOT allowed to modify method name, parameters, return type.
		*/
        //System.out.println(routine.size());
        return routine;
    }



    public static double printSolution(ArrayList<City> routine) {
        //initialize the value
        double totalDistance = 0.0;
        /*
			provide code here. you are NOT allowed to call your own functions.
			But you can use your own variables defined somewhere else! 
		*/
        for(int i=0;i<routine.size();i++){
            //each city number follows an arrow, so there are n of them.
            System.out.print(routine.get(i).getNum()+"->");
            if(i!=routine.size()-1){
                //for most values just calculate the distance normally.
                totalDistance += routine.get(i).distance(routine.get(i+1));
            }
            else{
                //however, since the journey is a circle it should goes to city 0, and thus we need to calculate one more distance and print one more 0.
                totalDistance += routine.get(i).distance(routine.get(0));
                System.out.print(routine.get(0).getNum()+"\n");
        }
        }

        return totalDistance;
    }

    /* to test the string and output it into a file, once there are no bugs it is useless.
    public static String printSolution2(ArrayList<City> routine) {

        String answer = "";

        for(int i=0;i<routine.size()-1;i++){
            answer+= routine.get(i).getNum()+"->";
            if(i==routine.size()-2){

                answer+=routine.get(0).getNum()+"\n";
            }
        }

        return answer;
    }*/

}
