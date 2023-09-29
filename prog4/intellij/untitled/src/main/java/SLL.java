public class SLL<T> {
    private SLL_Node<T> head;
    private SLL_Node<T> walkNode;
    private int size;
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
        String result = "" + head.getData();
        SLL_Node<T> cur_node = head;
        while((cur_node = cur_node.getNext()) != null){
            result = result + "," + cur_node.getData();
        }
        return result;
    }

    /*public int[] forloop(){
        //shoulda just put the stuff here.

    }*/

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
