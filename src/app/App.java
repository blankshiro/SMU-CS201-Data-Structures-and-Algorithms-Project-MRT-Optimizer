package app;

import java.util.*;
import java.util.Map.Entry;

import datastructure.*;

/**
 * Main application class.
 */
public class App {
    HashMap<String, List<Station>> adjMap = DataUtilities.createAdjMap();
    int numOfStations = adjMap.size();
    Graph network = new Graph(numOfStations);

    public App() {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("== MRT Optimizer App ==");
            System.out.print("Please enter the starting MRT code: ");
            String start = sc.nextLine();
            System.out.print("Please enter the destination MRT code: ");
            String end = sc.nextLine();
            System.out.println("=============================================================================");

            if (checkInput(start, end)) {
                network.solve(adjMap, start);
                ArrayList<String> firstPath = new ArrayList<>();
                DataUtilities.getPath(network.getParentMap(), start, end, end, firstPath);
                System.out.printf("Best path from %s to %s is: ", start, end);
                System.out.print(firstPath);
                System.out.println();

                // this will give an identical path as first path if no second path
                // available.
                ArrayList<String> secondPath = new ArrayList<>();
                DataUtilities.getPath(network.getParentMap2(), start, end, end, secondPath);
                System.out.printf("Alternative path from %s to %s is: ", start, end);
                System.out.print(secondPath);
                System.out.println();

                // DataUtilities.printTimeToAllStations(network.getDistMap(), start);
                // DataUtilities.printTimeToAllStations(network.getDistMap2(), start);

                // DataUtilities.printParentMap(network.getParentMap());
                // DataUtilities.printParentMap(network.getParentMap2());
                run = false;
            } else {
                System.out.println("You did not enter a valid train code!");
            }
        }
        sc.close();
        System.out.println("=============================================================================");
        System.out.println("Thank you for using our app.");
        System.out.println("Bye bye!");
    }

    public boolean checkInput(String start, String end) {
        boolean check1 = false;
        boolean check2 = false;
        Iterator<Entry<String, List<Station>>> iterator = adjMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Station>> entry = iterator.next();
            if (start.equals(entry.getKey())) {
                check1 = true;
            }

            if (end.equals(entry.getKey())) {
                check2 = true;
            }
        }

        if (check1 && check2) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {
        App app = new App();
    }
}