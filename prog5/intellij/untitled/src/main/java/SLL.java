public class SLL<T> {
    private SLL_Node<T> head;
    private SLL_Node<T> walkNode;
    private int size;
    private T[] array;
    public SLL(){
        head = null;
        walkNode = head;
    }

    public void prepend(T newdata){
        //add to front of list as node
        SLL_Node<T> newnode = new SLL_Node<T>(newdata);
        newnode.setNext(head);
        head = newnode;
        size++;
        //clear our return array so it can be rebuilt later
        array = null;
    }
    public T get_cur_and_advance() {
        //get current node's value and advance once step. return null and reset if we reached the end
        if(size == 0){
            return null;
        }
        T temp = null;
        if(walkNode != null) {
            temp = walkNode.getData();
            walkNode = walkNode.getNext();
        }
        else{
            resetWalk();
        }
        return temp;

    }
    public void resetWalk() {
        //reset where we are stepping
        walkNode = head;
    }
    public String toString(){
        //catch for if empty
        if(head == null){
            return "";
        }
        String result = "" + head.getData().toString();
        SLL_Node<T> cur_node = head;
        while((cur_node = cur_node.getNext()) != null){
            result = result + ";" + cur_node.getData().toString();
        }
        return result;
    }

    public T[] toArray(T[] inputarray){
        /**
         * returns an array of type T.
         * this is nice for forloops elsewhere in main()
         * because iterating through a linked list is confusing
         */
        if(array != null){
            return array;
        }

        /**
         * java is dumb and wont allow me to allocate an array
         * for a generic so im forcing the user to pass me an array.
         */
        array = inputarray;
        T cur_val;
        this.resetWalk();
        int i = 0;
        while((cur_val = this.get_cur_and_advance()) != null ){
             array[i] = cur_val;
             i++;
        }
        return array;
    }
    public int getSize(){
        return this.size;
    }

}
class SLL_Node<T>{// SLL node for holding data of an unknown type, T
    private SLL_Node next = null;
    private T data = null;

    SLL_Node(T newdata){
        data = newdata;
    }
    SLL_Node getNext(){
        return next;
    }
    void setNext(SLL_Node newnext){
        next = newnext;
    }
    void setData(T newdata){
        data = newdata;
    }
    T getData(){
        return data;
    }
    boolean hasNext(){return next != null;}
}
