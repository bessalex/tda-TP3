package KnapsackApprox;

import KnapsackProblem.Item;

import java.util.ArrayList;

/**
 * Created by alex on 28/11/16.
 */
public class KnapsackApprox {
    int maxItems, maxValues, capacity;
    int [][] knapsack;
    int optValue;
    ArrayList<Item> items;
    int itemWeight, itemValue;

    float coefficient=(float)0.75;


    public KnapsackApprox(int maxItems, int capacity, ArrayList<Item> items){
        this.maxItems = maxItems;
        this.items = items;
        this.capacity = capacity;

        this.flatValues(coefficient);

        // Obtengo la suma de los valores de los vectores
        this.maxValues=0;
        for (int i=0; i< this.maxItems; i++)
            this.maxValues += items.get(i).getValue();


        // genero la matriz máxima posible
        knapsack = new int[this.maxItems+1][this.maxValues+1];

        // Initialize values
        for (int i=0; i <= this.maxItems; i++) {
            knapsack[i][0] = 0;
        }

        for (int i=1; i <= this.maxItems; i++){
            // obtengo el tome de loop para V
            int sum_i_values=0;
            for (int j=1; j<= i; j++)
                sum_i_values += items.get(j-1).getValue();

            //obtengo la suma desde 1 hasta i-1
            int sum_i_min_1_values=0;
            for (int v=1; v<= (i-1); v++)
                sum_i_min_1_values += items.get(v-1).getValue();

            for (int V=1; V <= sum_i_values; V++){

                int value_i_min_1=0;
                if ((V - items.get(i-1).getValue()) > 0)
                    value_i_min_1 = V - items.get(i-1).getValue();
                // si V es mayor a la suma de valores desde 1 hasta i-1
                if (V > sum_i_min_1_values){
                    knapsack[i][V] = items.get(i-1).getWeight() + knapsack[i-1][value_i_min_1];
                }else{
                    if (knapsack[i-1][V] < (items.get(i-1).getWeight() + knapsack[i-1][value_i_min_1])){
                        knapsack[i][V] = knapsack[i-1][V];
                    }else {
                        knapsack[i][V] = items.get(i-1).getWeight() + knapsack[i-1][value_i_min_1];
                    }
                }
            }
        }
    }


    public int getOpt(){
        int optValue=0;
        for (int i=0; i< this.maxValues; i++){
            if ((i > optValue) && (knapsack[this.maxItems][i] <= this.capacity)){
                optValue = i;
            }
        }
        return optValue;
    }


    private void flatValues(float coef){
        int maxValue = 0;
        int pseudoValue=0;
        int weight=0;
        ArrayList<Item> pseudoItems = new ArrayList<Item>();

        for(int i=0; i< this.maxItems; i++){
            if (this.items.get(i).getValue() > maxValue)
                maxValue = this.items.get(i).getValue();
        }

        float b = (coef* maxValue)/(2*this.maxItems);

        for(int i=0; i< this.maxItems; i++){
            pseudoValue = (int)Math.ceil(this.items.get(i).getValue() / b);
            weight = this.items.get(i).getWeight();
            Item item = new Item(pseudoValue, weight);
            pseudoItems.add(i, item);
        }

        this.items = null;
        this.items = pseudoItems;

    }

}

