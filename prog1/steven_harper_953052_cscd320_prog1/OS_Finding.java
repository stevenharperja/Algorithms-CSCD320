//Author: Steven Harper
import java.math.*;
import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OS_Finding{
	public static void main(String args[]) throws FileNotFoundException, IOException {
		//args[0] is filename to read from
		int[] data = read_integers_file(args[0]);
		int i = Integer.parseInt(args[1]);

		int os = randomized_select(data, 0, data.length-1, i);
		System.out.println();
		System.out.println("os is:"+os);
	}
	private static int partition(int[] A,int left,int right) {
		/* 
		 * move around numbers in A such that the number in the index 'right'
		 * has only numbers less than it on its left side, and 
		 * only numbers greater than it on its right side.
		 * that number in index 'right' will be in a new spot
		 */
		
		//number we will be moving
		int pivot = A[right];
		//index of where pivot will end up
		int index = left;
		for(int i=left;i<right;i++) {
			//run through all the numbers, except our pivot (right-1).
			if(A[i] <= pivot) {
				//if its bigger than pivot, move it to the left of index
				swap(A,index,i);
				//then move index
				index++;
			}
		}
		//finally move pivot into its place
		swap(A,index,right);
		//return where pivot is
		return(index);
	}
	private static int randomized_partition(int[] A, int left, int right) {
		/*
		 * use the same partition framework but we
		 * just shove a random chosen number from A into far-right index
		 */
		int r = (int)(Math.random() * (right+1-left)+left);
		//System.out.println("random partition is:" + r);
		swap(A,r,right);
		return partition(A,left,right);
	}
	private static void swap(int[] array,int a,int b){
		int temp;
		temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	public static int randomized_select(int[] A,int p,int r,int i) {
		/*
		 * Find the i`th order statistic recursively.
		 * Use the random partition pivot in order to do 
		 * the recursion more intelligently, by picking
		 * which side to recurse on.
		 */
		if(p==r) {
			//exit condition, only one option here
			return(A[p]);
		}
		
		//all nums left of A[q] are smaller than q, all to right are bigger than A[q]
		int q = randomized_partition(A,p,r);
		//where is q in our subarray, in one-based indexing
		int k = q-p+1;
		
		System.out.println("q is:" +q +"\n");
		for(int number = 0; number<A.length;number++)
			System.out.println(A[number]);
		System.out.println("working in subarray:");
		for(int number = p; number<=r;number++)
			System.out.println(A[number]);
		System.out.println();
		
		if(i==k) {
			//found the i`th order statistic
			return(A[q]);
		}
		else if(i<k) {
			//k bigger than i`th, so search on the left of q
			return randomized_select(A,p,q-1,i);//prolly change back to q-1
		}
		else {
			//k smaller than i`th, so search on the right of q
			//subtract k from i to keep i working nicely in the recursions
			return randomized_select(A,q+1,r,i-k);
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