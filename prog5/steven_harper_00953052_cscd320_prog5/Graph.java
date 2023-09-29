public class Graph {

    /**
     * arrays used for keeping track of graph nodes.
     * decided to do it OOP style instead of this though.
     */
    /*//non-OOP version
    //directional connections of edges
    protected SLL<Integer>[] connections;
    //used during BFS and DFS to check that a node was visited
    protected boolean[] visited;
    //used during BFS and DFS to keep track of how we got to that node, (for building a path later)
    protected int[] prev;
    //in BFS,DFS,TOPOSORT used for keeping track of when the node was visited
    protected int[] depart_time;
    //in BFS, DFS,TOPOSORT, used for keeping track of when node's children were finished being searched.
    protected int[] finish_time;
    */
    //instead of using above arrays im using node classes instead
    protected GraphNode[] nodes;

    //used in DFS for keeping track of steps through the algorithm
    protected int time;
    protected int v_size;

    private SLL<Integer> topoSortList = new SLL<Integer>();




    public Graph(int size){
        /*
        connections = new SLL[size];
        for(int i = 0; i < size; i++)
            connections[i] = new SLL<Integer>();
        visited = new boolean[size];
        prev = new int[size];
        depart_time = new int[size];
        finish_time = new int[size];
        */
        nodes = new GraphNode[size];
        for(int i = 0; i < size; i++) {
            nodes[i] = new GraphNode(i);
        }

        v_size = size;
    }
    public void add_bulk_connections(int source, Edge[] dest){
        /**
         * takes an array of destinations and appends them
         * to the list of connections for a given source node.
         * in other words this is the function which builds the linked lists.
         */
        /*Old non-OOP code
        for(int i=0;i<dest.length;i++) {
            connections[source].prepend(dest[i]);
        }
        */
        for(int i=0;i<dest.length;i++){
            nodes[source].add_connection(dest[i]);
        }
    }
    public void clear_connections(int node){
        /**
         * ~~UNUSED~~
         * this function will reset all of the
         * directional edge connections of a given node.
         */
        /*old non-OOP
        connections[node] = new SLL<Integer>();
         */
        nodes[node].clear_connections();
    }
    public void add_connection(int source, Edge dest){
        /**
         * takes just one connection and adds it to the proper linked list
         */
        //old non-OOP code
        //connections[source].prepend(dest);
        //new OOP code
        nodes[source].add_connection(dest);
    }
    public void add_connection(int source, int dest_node, int weight){
        /**
         * takes integer values and converts them to an edge and adds it
         */
        Edge dest = new Edge(weight,nodes[dest_node]);
        this.add_connection(source,dest);
    }

//    public String DFS(){
//        /**
//         * performs a depth-first search of the
//         * whole graph
//         * the result of this search is returned as an int array
//         */
//        //NOT REFACTORING THIS ONE BECAUSE ITS NOT REQUIRED FOR THE ASSIGNMENT
//        //it would be better for me to use a linked list of ints rather than a string, but im not allowed to use prebuilt linked lists and i cant be bothered to make one only for this right now.
//        String result = "";
//
//        //init to unvisited
//        for(int i = 0; i < v_size; i++){
//            visited[i] = false;
//        }
//        time = 0; //init time too
//
//        for(int i = 0; i< v_size; i++){
//            if( ! visited[i]){
//                result = result + graph_DFS(i) + ",";
//            }
//        }
//
//        //return result but without the extra comma
//        return result.substring(0,result.length()-1);
//
//        //NOT REFACTORING THIS ONE BECAUSE ITS NOT REQUIRED FOR THE ASSIGNMENT
//    }
//    protected String graph_DFS(int source){
//        /**
//         * performs a depth-first search of the
//         * whole graph, starting from a given node.
//         * the source node is returned as a string
//         */
//        //NOT REFACTORING THIS ONE BECAUSE ITS NOT REQUIRED FOR THE ASSIGNMENT
//        //rather than printing immediately, return as a string for easier testing
//        String result = ""+source; time++;
//
//        depart_time[source] = time; visited[source] = true;
//
//        Integer i;
//        //check each connection of the source node
//        connections[source].resetWalk();
//        while((i = connections[source].get_cur_and_advance()) != null ){
//
//            if( ! visited[i]){
//                prev[i] = source; result = result +","+ graph_DFS(i);
//            }
//        }
//        time++; finish_time[source] = time; visited[source] = true;
//
//        //This is changed from Graph
//        topoSortList.prepend(source);
//
//        return result;
//        //NOT REFACTORING THIS ONE BECAUSE ITS NOT REQUIRED FOR THE ASSIGNMENT
//    }
//
//    public String TopoSort(){
//        //NOT REFACTORING THIS ONE BECAUSE ITS NOT REQUIRED FOR THE ASSIGNMENT
//        //clear the list
//        topoSortList = new SLL<Integer>();
//        //call dfs to calculate finish times
//        DFS();
//        //turn the linked list we grabbed from DFS doing our Overridden method into a string
//
//        String topoString = "";
//        Integer cur_node;
//        topoSortList.resetWalk();
//        while((cur_node = topoSortList.get_cur_and_advance()) != null ){
//            topoString = topoString + cur_node + ",";
//        }
//        return topoString.substring(0,topoString.length()-1);
//        //NOT REFACTORING THIS ONE BECAUSE ITS NOT REQUIRED FOR THE ASSIGNMENT
//    }
//
//    public String BFS(int source){
//        /**
//         * performs a breadth-first search of the
//         * whole graph from a given node.
//         * the result of this search is returned as an int array.
//         */
//        //not implementing this cuz it's not required for the assignment
//        return "BFS not implemented.";
//    }
    public String toString(){
        /**
         * prints out all the connections to make sure we got it right
         */
        String result = "";
        //for each node
        for(int i = 0; i < v_size; i++){
            result = result + i + ":";
            //for each connection
            result = result + nodes[i].get_connections().toString();

            result = result + "\n";
        }
        return result;
    }
    public String dijkastra(int source){
        /**
         * performs a run of Dijkastra's algorithm, starting from the
         * given node's index.
         * the shortest path of each non-source node is printed, along with the weight of that path.
         */
        //init all variables we will change to null or infinity
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].setDistance(Integer.MAX_VALUE/2);
            nodes[i].setPrev(null);
        }
        //distance from source to source is zero
        nodes[source].setDistance(0);

        //java generics wont allow array creation so i have to do it here.
        Min_Heap queue = new Min_Heap<GraphNode>(nodes, new GraphNode[nodes.length]);

        while(!queue.isEmpty()) {

            GraphNode u = (GraphNode) queue.pop();

            SLL<Edge> adjacencent_list = u.get_connections();
            //java generics wont allow array creation so i have to do it here.
            Edge[] adjacencies = adjacencent_list.toArray(new Edge[adjacencent_list.getSize()]);
            for (int i = 0; i < adjacencies.length; i++) {
                relax_edge(u, adjacencies[i]);
                //repair minheap
                queue.min_heapify(0);
            }
        }
        //finally return a string to print
        return(print_dijkastra(source));
    }
    private void relax_edge(GraphNode source, Edge edge){
        /**
         * checks if a better way to get to destination node
         * was found from going from current source node
         */
        GraphNode dest = edge.getDestination();
        int weight = edge.getWeight();

        //if better distance found
        if(dest.getDistance() > source.getDistance() + weight){
            dest.setDistance(source.getDistance()+weight);
            //set prev
            dest.setPrev(source);
        }
    }
    private String print_dijkastra(int source){
        /**
         * for loops through all the nodes in the graph,
         * prints their results of their shortest path and associated weight
         * (data is gotten from what is left behind after
         * running dijkastra's alg)
         */
        String output = "";
        for(int i = 0;i < nodes.length; i++){
            //skip if its the source node
            if(source == i)
                continue;
            //label current index
            output = output +
                    "[" +i+"]";
            //if no path exists
            if(nodes[i].getPrev() == null){
                output = output + "unreachable\n";
                continue;
            }
            //if path exists
            output = output +"shortest path:("+
                    print_path(nodes[i]) +
                    ") shortest distance:" + nodes[i].getDistance()+"\n"   ;
        }
        return output;
    }
    private String print_path(GraphNode node){
        /**
         * iteratively make a string using node.prev
         * of the path used to get to given node
         *
         * this is useful with basically all path algorithms
         */
        //put the final part of the path
        String path = node.getIndex()+"";
        //move backwards
        GraphNode cur_node = node.getPrev();

        while(cur_node != null){
            //prepend the next, previous part of the path
            path = cur_node.getIndex()+","+path;
            cur_node = cur_node.getPrev();
        }
        return path;
    }
}
class Edge{
    private int weight;
    private GraphNode destination;
    Edge(int weight, GraphNode destination){
        this.weight = weight;
        this.destination = destination;
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GraphNode getDestination() {
        return destination;
    }

    public void setDestination(GraphNode destination) {
        this.destination = destination;
    }
    public String toString(){
        /**
         * return a string in the same format the input file would
         */
        return destination.getIndex()+","+weight;
    }
}
class GraphNode implements Comparable<GraphNode>{
    //TODO refactor the structure of Graph class to use this class instead of a bunch of arrays
    //directional connections of edges
    private SLL<Edge> connections;
    //used during BFS and DFS to check that a node was visited
    private boolean visited;
    //used during BFS and DFS to keep track of how we got to that node, (for building a path later)
    private GraphNode prev;
    //in BFS,DFS,TOPOSORT used for keeping track of when the node was visited
    private int depart_time;
    //in BFS, DFS,TOPOSORT, used for keeping track of when node's children were finished being searched.
    private int finish_time;

    //just another reference to this node's index in the node array to make my life easier
    private final int index;

    //used in Dijkastra's algorithm to measure the cost of traveling to this node from the given source node
    private int distance;
    GraphNode(int index){
        this.index = index;
        this.connections = new SLL<Edge>();
    }
    public int getIndex(){
        return this.index;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public void add_connection(Edge conn){
        this.connections.prepend(conn);
    }
    public void clear_connections(){
        this.connections = new SLL<Edge>();
    }
    public SLL<Edge> get_connections(){
        /**
         * TODO
         * just currently returns the SLL directly,
         * so that the old toString method works
         * but i dont think i wanna do that later
         */
        return this.connections;
    }
    public GraphNode getPrev() {
        return prev;
    }

    public void setPrev(GraphNode prev) {
        this.prev = prev;
    }

    public int getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(int depart_time) {
        this.depart_time = depart_time;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void compareTo(){
        //TODO use compareto so i can have a nicely formatted generic minheap use this
    }

    @Override
    public int compareTo(GraphNode graphNode) {
        if (this.distance < graphNode.distance)
            return -1;
        if(this.distance > graphNode.distance)
            return 1;
        else
            return 0;
    }
//    public int compareTo(Integer val) {
//        /**
//         * made this cuz i dont wanna think and reimplement my minheap
//         * so im just making an overload method for Integers to handle infinity
//         */
//        if (this.distance < val)
//            return -1;
//        if(this.distance > val)
//            return 1;
//        else
//            return 0;
//    }
//    public int compareTo(Comparable comp) {
//        /**
//         * made this cuz i dont wanna think and reimplement my minheap
//         * so im just making an overload method for Integers to handle infinity
//         */
//        try{
//            Integer val = (Integer)comp;
//            return compareTo(val);
//        } catch (Exception e) {
//
//        }
//        try{
//            GraphNode node = (GraphNode) comp;
//            return compareTo(node);
//        } catch (Exception e) {
//
//        }
//        System.out.println("cannot compare this object");
//        return 0;
//    }
}

