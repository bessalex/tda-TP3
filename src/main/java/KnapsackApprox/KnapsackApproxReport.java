package KnapsackApprox;


import KnapsackProblem.Instance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by alex on 16/11/16.
 */
public class KnapsackApproxReport {
    ArrayList<Instance> instances;
    File file;

    public KnapsackApproxReport(String fileName, int numFile, ArrayList<Instance> instances) {
        this.instances = instances;
        try {
            this.file = new File("src/main/resources/knapsackApprox/Report_" + numFile + ".csv");
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Instance,Miliseconds,Opt, optCalc,Capacity\n");
                bw.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generate(int from, int to){
        if (to >= this.instances.size())
           to = this.instances.size();

        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);


            for (int j = from; j < to; j++) {
                Instance instance = this.instances.get(j);

                long start = System.currentTimeMillis();
                KnapsackApprox knapsack = new KnapsackApprox(instance.getMaxItems(), instance.getCapacity(), instance.getItems());
                long finish = System.currentTimeMillis();

                int opt = instance.getOptimum();
                int optCalc = knapsack.getOpt();
                int capacity = instance.getCapacity();

                String content = j + "," + Long.toString(finish - start) + "," + opt + "," + optCalc + "," + capacity + '\n';
                bw.write(content);
                knapsack = null;
                instance = null;
            }
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }



}
