public class DAG {

    /**
     * arrays used for keeping track of graph nodes.
     * not sure whether to do it OOP style instead of this though.
     */
    //directional connections of edges
    protected SLL<Integer>[] connections;
    //used during BFS and DFS to check that a node was visited
    protected boolean[] visited;
    //used during BFS and DFS to keep track of how we got to that node, (for building a path later)
    protected int[] prev_path;
    //in BFS,DFS,TOPOSORT used for keeping track of when the node was visited
    protected int[] depart_time;
    //in BFS, DFS,TOPOSORT, used for keeping track of when node's children were finished being searched.
    protected int[] finish_time;
    //used in DFS for keeping track of steps through the algorithm
    protected int time;
    protected int v_size;

    private SLL<Integer> topoSortList = new SLL<Integer>();

    public DAG(int size){
        connections = new SLL[size];
        for(int i = 0; i < size; i++)
            connections[i] = new SLL<Integer>();
        visited = new boolean[size];
        prev_path = new int[size];
        depart_time = new int[size];
        finish_time = new int[size];
        v_size = size;
    }
    public void add_bulk_connections(int source, int[] dest){
        /**
         * takes an array of destinations and appends them
         * to the list of connections for a given source node.
         * in other words this is the function which builds the linked lists.
         */
        for(int i=0;i<dest.length;i++) {
            connections[source].prepend(dest[i]);
        }
    }
    public void clear_connections(int node){
        /**
         * ~~UNUSED~~
         * this function will reset all of the
         * directional edge connections of a given node.
         */
        connections[node] = new SLL<Integer>();
    }
    public void add_connection(int source, int dest){
        /**
         * takes just one connection and adds it to the proper linked list
         */
        connections[source].prepend(dest);
    }

    public String DFS(){
        /**
         * performs a depth-first search of the
         * whole graph
         * the result of this search is returned as an int array
         */
        //it would be better for me to use a linked list of ints rather than a string, but im not allowed to use prebuilt linked lists and i cant be bothered to make one only for this right now.
        String result = "";

        //init to unvisited
        for(int i = 0; i < v_size; i++){
            visited[i] = false;
        }
        time = 0; //init time too

        for(int i = 0; i< v_size; i++){
            if( ! visited[i]){
                result = result + graph_DFS(i) + ",";
            }
        }

        //return result but without the extra comma
        return result.substring(0,result.length()-1);

    }
    protected String graph_DFS(int source){
        /**
         * performs a depth-first search of the
         * whole graph, starting from a given node.
         * the source node is returned as a string
         */
        //rather than printing immediately, return as a string for easier testing
        String result = ""+source; time++;

        depart_time[source] = time; visited[source] = true;

        Integer i;
        //check each connection of the source node
        connections[source].resetWalk();
        while((i = connections[source].get_cur_and_advance()) != null ){

            if( ! visited[i]){
                prev_path[i] = source; result = result +","+ graph_DFS(i);
            }
        }
        time++; finish_time[source] = time; visited[source] = true;

        //This is changed from Graph
        topoSortList.prepend(source);

        return result;
    }

    public String TopoSort(){
        //clear the list
        topoSortList = new SLL<Integer>();
        //call dfs to calculate finish times
        DFS();
        //turn the linked list we grabbed from DFS doing our Overridden method into a string

        String topoString = "";
        Integer cur_node;
        topoSortList.resetWalk();
        while((cur_node = topoSortList.get_cur_and_advance()) != null ){
            topoString = topoString + cur_node + ",";
        }
        return topoString.substring(0,topoString.length()-1);
    }

    public String BFS(int source){
        /**
         * performs a breadth-first search of the
         * whole graph from a given node.
         * the result of this search is returned as an int array.
         */
        //not implementing this cuz it's not required for the assignment
        return "BFS not implemented.";
    }
    public String toString(){
        /**
         * prints out all the connections to make sure we got it right
         */
        String result = "";
        //for each node
        for(int i = 0; i < v_size; i++){
            result = result + i + ":";
            //for each connection
            result = result + connections[i].toString();

            result = result + "\n";
        }
        return result;
    }
}

