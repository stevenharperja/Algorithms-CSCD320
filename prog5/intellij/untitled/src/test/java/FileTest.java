import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileTest {
    static String debugFilename = "Debugfile";
    static String orderedString =
            "0:1,2;2,5;3,4\n" +
                    "1:3,1\n" +
                    "2:\n" +
                    "3:2,2";
    static String unorderedString =
            "1:3,1\n" +
            "0:2,5;1,2;3,4\n" +
            "3:2,2\n" +
            "2:";

    @Test
        //@DisplayName("Add two numbers")
    void read_to_Graph() throws IOException {
        //make the file
        print_to_file(debugFilename,orderedString);
        //call read stuff from your function to get a graph
        Graph testgraph = Dijkstra.file_to_weighted_Graph(debugFilename);
        //assert graph is as it should be (any order of edges is fine though!)
        assertEquals(orderedString, testgraph.toString());
    }
    @Test
    void read_to_Graph_unordered() throws IOException {
        //make the file with unordered node indices
        print_to_file(debugFilename,unorderedString);
        //call read stuff to get a graph
        Graph testgraph = Dijkstra.file_to_weighted_Graph(debugFilename);
        //assert graph is as it should be (any order of edges is fine though!)
        assertEquals(orderedString, testgraph.toString());
    }





    static void print_to_file(String filename,String data) throws IOException {
        /**
         * this method will make a file so that
         * your read-in function will have something to read
         */

        File myObj = new File(filename);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists. Overwriting.");
        }
        FileWriter myWriter = new FileWriter(filename);

        myWriter.write(data);

        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
}


class GraphTest {

    @Test
        //@DisplayName("Add two numbers")
    void test_dijkastra_3() throws IOException {
        Graph testgraph = load_graph(FileTest.unorderedString);
        String results = testgraph.dijkastra(3);
        assertEquals("[0]unreachable\n" +
                "[1]unreachable\n" +
                "[2]shortest path:(3,2) shortest distance:2\n", results);
    }
    @Test
    void test_dijkastra_0() throws IOException {
        Graph testgraph = load_graph(FileTest.unorderedString);
        String results = testgraph.dijkastra(0);
        assertEquals("[0]unreachable\n" +
                "[1]unreachable\n" +
                "[2]shortest path:(3,2) shortest distance:2\n", results);
    }

    static Graph load_graph(String contents) throws IOException {
        //make the file with unordered node indices
        FileTest.print_to_file(FileTest.debugFilename,contents);
        //call read stuff to get a graph
        Graph testgraph = Dijkstra.file_to_weighted_Graph(FileTest.debugFilename);
        return testgraph;
    }
}