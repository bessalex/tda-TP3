package KnapsackApprox;


import KnapsackProblem.CsvScanner;
import KnapsackProblem.Instance;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by alex on 01/12/16.
 */
public class KnapsackApproxReportTest {
    @Test
    public void generateSmallKnapsack() throws Exception {
        String format = "%-40s%1.2f \t %s %n";
        String fileName ;
        float seconds;

        for (int i=1; i< 8; i++) {
            fileName = "src/main/resources/knapsackApprox/knapPI_" + i + "_50_1000.csv";
            System.out.println("\n\n\n Filename : " + fileName);
            CsvScanner csv = new CsvScanner(fileName);
            ArrayList<Instance> instances = csv.getInstances();

            if (instances != null) {
                KnapsackApproxReport report = new KnapsackApproxReport(fileName, i, instances);
                report.generate(0, 50);
            }
        }
    }


    @Test
    public void generate38_50() throws Exception {
        String format = "%-40s%1.2f \t %s %n";
        String fileName ;
        float seconds;

        for (int i=9; i< 17; i++) {
            fileName = "src/main/resources/knapsackApprox/knapPI_" + i + "_50_1000.csv";
            System.out.println("\n\n\n Filename : " + fileName);
            CsvScanner csv = new CsvScanner(fileName);
            ArrayList<Instance> instances = csv.getInstances();

            if (instances != null) {
                KnapsackApproxReport report = new KnapsackApproxReport(fileName,i,  instances);
                report.generate(0, 50);
            }
        }
    }

}


