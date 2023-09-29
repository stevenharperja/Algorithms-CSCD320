public class Min_Heap <T extends Comparable<T>>{
    /*
     * in order not to use more time allocating another array,
     * one based indexing to zero based indexing conversions are used.
     */
    private T[] heap;
    private int heap_size;

//    Min_Heap(int array_length) {
//        //just allocate some array space
//        //heap = new int[array_length];
//    }
    Min_Heap(T[] heap_data,T[] inputarray) {
        /**
         * java generics wont allow me to allocate array space here
         * so the user must pass in an input array of space equal to heap data size
         */
        //build the heap
        this.build_min_heap(heap_data,inputarray);
    }



    public T[] get_heap(){
        return this.heap;
    }

    public T[] heapsort(T[] A, T[] inputarray){
        /*
         * given some array. convert it into a heap and then
         * convert it from a heap into a sorted array of
         * descending order. this array is stored in heap afterward
         */
        build_min_heap(A,inputarray);
        for(int i = A.length-1;i>=2-1;i--) {
            swap(1-1, i);
            heap_size--;
            min_heapify(1-1);
        }
        return this.heap;
    }

    public void build_min_heap(T[] A, T[] inputarray){
        /*
         * given some array, make it a min heap and store it.
         */

        //delete current heap, if exists
        this.heap = null;
        //allocate new space so we dont mess with original indexing (the nodes are not copied though)
        heap = inputarray;
        //replace with given array data
        for(int i = 0; i < A.length;i++) {
            this.heap[i] = A[i];
        }
        this.heap_size = A.length;
        for(int i = A.length/2-1;i>=1-1;i--)
            //until we hit a leaf node, keep min_heapifying
            min_heapify(i);

    }
    public void min_heapify(int i){
        int smallest = i;
        if(l(i) != null && 0 < this.heap[smallest].compareTo(this.heap[l(i)])) {
            //left child smaller
            smallest = l(i);
        }
        if(r(i) != null && 0 < this.heap[smallest].compareTo(this.heap[r(i)])) {
            //right child smaller
            smallest = r(i);
        }
        if(smallest == i)
            //heap[i] is already smallest, no need to fix
            return;
        swap(i,smallest);
        min_heapify(smallest);

    }
    private void swap(int i,int j){
        T temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }
    private Integer p(int i){
        //get index for parent of heap "node" with index i

        //convert to 1 based indexing
        i+=1;
        if(i/2 > 1)
            return i/2 - 1;
        else
            return null;
    }
    private Integer l(int i){
        //get index for left child of heap "node" with index i

        //convert to 1 based indexing
        i+=1;
        if(2*i<= this.heap_size)
            return 2*i -1;
        else
            return null;
    }
    private Integer r(int i){
        //get index for right child of heap "node" with index i

        //convert to 1 based indexing
        i+=1;
        if(2*i+1 <= this.heap_size)
            return 2*i+1 -1;
        else
            return null;
    }
    public void replace_top(T replacement){
        /**
         * change head
         * fix tree
         */
        this.heap[1-1] = replacement;
        min_heapify(1-1);
    }
    public T get_top(){
        return this.heap[1-1];
    }

    public T pop(){
        /**
         * get head
         * delete head
         * fix tree
         */
        T temp = get_top();
        this.heap[1-1] = this.heap[heap_size-1];
        heap_size--;
        min_heapify(1-1);
        return temp;
    }
    public boolean isEmpty(){
        return this.heap_size==0;
    }
}