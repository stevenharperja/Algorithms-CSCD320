
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.File;
import java.util.Scanner;

public class Dijkstra {
    public static void main(String[] args) throws IOException {
        //make graph from file
        Graph graph = file_to_weighted_Graph(args[0]);

        //convert given source node index to a number
        int source = Integer.parseInt(args[1]);
        //call toposort on graph.
        String results = graph.dijkastra(source);
        //print results
        System.out.println(results);
    }
    public static Graph file_to_weighted_Graph(String filename) throws IOException {
        /**TODO
         * this method will read a file with the format of:
         *
         *
         *
         *
         * and convert it to a Graph, where each beginning number is a node,
         * and all subsequent numbers following the colon are directed
         * edge connections
         */

        /**
         * get how many lines, set as Graph size.
         */
        int size = file_line_count(filename);
        Graph graph = new Graph(size);

        /**
         * read each line, put it in as a LL to Graph
         */
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        //for each line
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            //get the first number
            int source_node = Integer.parseInt(data.substring(0,1));

            //remove the colon
            data = data.substring(2);


            if(data.length() == 0)
                continue;
            //make an array of each directional edge
            String[] edges = data.split(";");

            //for each edge
            for (int i = 0; i < edges.length ; i++) {
                //if its broken, print some debug output
                if(edges[i].length() < 3) {
                    System.out.println("error processing edge!");
                }
                //split destination and weight of edge
                String[] dest_weight_pair = edges[i].split(",");
                int destination_node = Integer.parseInt(dest_weight_pair[0]);
                int weight = Integer.parseInt(dest_weight_pair[1]);
                //add it to the graph
                graph.add_connection(source_node, destination_node, weight);
            }
        }
        myReader.close();
        /**
         * return as Graph
         */
        return graph;
    }
    public static int file_line_count(String filename) throws IOException {
        /**
         * taken from https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
         * this will return how many lines there are in the file
         */

        FileReader       input = new FileReader(filename);
        LineNumberReader count = new LineNumberReader(input);
        while (count.skip(Long.MAX_VALUE) > 0)
        {
            // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
        }
            return count.getLineNumber() + 1;    // +1 because line index starts at 0
    }
}
