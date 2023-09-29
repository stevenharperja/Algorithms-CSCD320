
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.File;
import java.util.Scanner;

public class TopoSort {
    public static void main(String[] args) throws IOException {
        //make dag from file
        DAG dag = file_to_DAG(args[0]);
        //call toposort on dag.
        String results = dag.TopoSort();
        //print results
        System.out.println(results);
    }
    public static DAG file_to_DAG(String filename) throws IOException {
        /**
         * this method will read a file with the format of:
         * 1:3
         * 0:3,1,2
         * 2:
         * 3:2
         * and convert it to a DAG, where each beginning number is a node,
         * and all subsequent numbers following the colon are directed
         * edge connections
         */

        /**
         * get how many lines, set as DAG size.
         */
        int size = file_line_count(filename);
        DAG dag = new DAG(size);

        /**
         * read each line, put it in as a LL to DAG
         */
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        //for each line
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            //get the first number
            int source_node = Integer.parseInt(data.substring(0,1));

            //remove the colon
            String edges = data.substring(2);

            //for each directional edge
            while(edges.length() > 0){
                //get the number
                String edge = edges.substring(0,1);
                if(edges.length() > 1){
                    //remove the number and comma
                    edges = edges.substring(2);
                }
                else{
                    //remove just the number so we dont index out of bounds
                    edges = edges.substring(1);
                }
                int destination_node = Integer.parseInt(edge);
                //add it to the dag
                dag.add_connection(source_node, destination_node);
            }
        }
        myReader.close();
        /**
         * return as DAG
         */
        return dag;
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
