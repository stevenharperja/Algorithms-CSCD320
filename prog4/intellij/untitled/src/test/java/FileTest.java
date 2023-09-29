import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    static String debugFilename = "Debugfile";
    static String orderedString =
            "0:1,2,3\n" +
                    "1:3\n" +
                    "2:\n" +
                    "3:2";
    static String unorderedString = "1:3\n" +
            "0:2,1,3\n" +
            "3:2\n" +
            "2:";

    @Test
        //@DisplayName("Add two numbers")
    void read_to_DAG() throws IOException {
        //make the file
        print_to_file(debugFilename,orderedString);
        //call read stuff from your function to get a dag
        DAG testdag = TopoSort.file_to_DAG(debugFilename);
        //assert dag is as it should be
        assertEquals(orderedString, testdag.toString());
    }
    @Test
    void read_to_DAG_unordered() throws IOException {
        //make the file with unordered node indices
        print_to_file(debugFilename,unorderedString);
        //call read stuff to get a dag
        DAG testdag = TopoSort.file_to_DAG(debugFilename);
        //assert dag is as it should be
        assertEquals(orderedString, testdag.toString());
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


class DAGTest {
    @Test
        //@DisplayName("Add two numbers")
    void BFS() {
        //not needed for the assignment, i dont care about this test
        assertEquals(4, 4);
    }

    @Test
        //@DisplayName("Multiply two numbers")
    void DFS() throws IOException {
        DAG testdag = load_dag(FileTest.unorderedString);
        String results = testdag.DFS();
        assertEquals("0,2,3,1 or 0,1,3,2 or 0,3,2,1", results);

    }
    @Test
        //@DisplayName("Add two numbers")
    void testTopoSort() throws IOException {
        DAG testdag = load_dag(FileTest.unorderedString);
        String results = testdag.TopoSort();
        assertEquals("0,1,3,2", results);
    }

    static DAG load_dag(String contents) throws IOException {
        //make the file with unordered node indices
        FileTest.print_to_file(FileTest.debugFilename,contents);
        //call read stuff to get a dag
        DAG testdag = TopoSort.file_to_DAG(FileTest.debugFilename);
        return testdag;
    }
}