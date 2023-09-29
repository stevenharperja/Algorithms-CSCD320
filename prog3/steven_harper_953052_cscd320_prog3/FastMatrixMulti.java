import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FastMatrixMulti {
    public static void main(String args[]) throws IOException {
        int[] p = read_integers_file(args[0]);
        int[][][] m_and_s = matrix_chain_order(p);
        int[][] m = m_and_s[0];
        int[][] s = m_and_s[1];
        //System.out.print(p.length);
        //print_matrix(s);
        //print_matrix(m);
        print_optimal_parens(s,0,p.length-2);//is it p.length thats right???
        System.out.println();
        System.out.println(m[0][p.length-2]);
    }

    private static int[][][] matrix_chain_order(int[] p){
        /*
         * takes an array P, of matrix dimensions
         * finds the best order to multiply those matrices
         * returns an array of 2d arrays
         */
        int n = p.length-1;
        //stores time cost

        //holds best/least time cost
        int[][] m = new int[n][n];//ones based indexing offset
        //stores position of parentheses
        int[][] s = new int[n][n];//ones based indexing offset

        //unnecessary for loop goes here?
        //for

        for(int h = 1;h<n;h++){
            for(int i = 0; i<n-h;i++){
                int j = i+h;

                //
                m[i][j] = Integer.MAX_VALUE;

                for(int k = i;k<j;k++){
                    int q = (m[i][k] + m[k+1][j] + p[i] * p[k+1] * p[j+1]);
                    if(q < m[i][j]){ //|| m[i][j] == -1){
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        int [][][] m_and_s = {m,s};
        return m_and_s;
    }

    private static void print_optimal_parens(int[][] s, int i, int j) {
        /*
         * given an array of parenthesis locations, S, print out the parenthesis
         * ???
         */
        if (i==j){
            System.out.print("A"+ (i + 1));
        }
        else{
            System.out.print('(');
            print_optimal_parens(s, i, s[i][j]);
            print_optimal_parens(s, s[i][j]+1, j);
            System.out.print(')');
        }
    }

    private static void print_matrix(int[][] a){
        for(int i = 0; i<a.length;i++){
            for(int j = 0;j<a[0].length;j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static int[] read_integers_file(String filename) throws FileNotFoundException, IOException {
        /*
         * this method grabs all the numbers from the file and sends as int[]
         */

        File myfile = new File(filename);
        //template grabbed right off of stackexchange https://stackoverflow.com/questions/10257981/read-text-file-into-an-array
        BufferedReader reader = new BufferedReader(new FileReader(myfile));
        List<Integer> lines = new ArrayList<Integer>();

        String line;
        while((line = reader.readLine()) != null) {
            lines.add(Integer.parseInt(line));
        }
        reader.close();

        Integer[] data = lines.toArray(new Integer[]{});


        //convert it all to ints instead of Integers
        int[] intdata = new int[data.length];
        for(int i = 0;i<data.length;i++) {
            intdata[i] = data[i];
        }

        return(intdata);
    }
}
