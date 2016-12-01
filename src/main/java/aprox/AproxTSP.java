package aprox;

import graph.Notdigraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by juan on 28/11/16.
*/
public class AproxTSP {

    public ArrayList<Integer> getPath(Notdigraph notdigraph) {

        ArrayList<Integer> list = notdigraph.getPrimTree(0).preOrden(0);
        Set<Integer> repeated = new HashSet<>();
        ArrayList<Integer> output = new ArrayList<>();

        for (Integer node : list) {
            if (! repeated.contains(node)) {
                repeated.add(node);
                output.add(node);
            }
        }
        output.add(0);
        return output;

    }


}
