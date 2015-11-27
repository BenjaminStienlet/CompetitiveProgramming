
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

enum State { Unvisited, Visited };

class Solution1 {

    public static void main(String[] args) {
        new Solution1();
    }

    public Solution1() {
        Graph graph = new Graph();

        GraphNode node1 = new GraphNode("node1");
        GraphNode node2 = new GraphNode("node2");
        GraphNode node3 = new GraphNode("node3");
        GraphNode node4 = new GraphNode("node4");
        GraphNode node5 = new GraphNode("node5");
        
        graph.add_node(node1);
        graph.add_node(node2);
        graph.add_node(node3);
        graph.add_node(node4);
        graph.add_node(node5);
        
        node1.add_neighbour(node2);
        node1.add_neighbour(node3);
        node3.add_neighbour(node2);
        node3.add_neighbour(node4);
        node4.add_neighbour(node1);
        node4.add_neighbour(node5);

        System.out.println(routeBetween(graph, node1, node5));
    }

    public boolean routeBetween(Graph graph, GraphNode start, GraphNode end) {
        LinkedList<GraphNode> queue = new LinkedList<>();

        if (start == end) {
            return true;
        }
        start.state = State.Visited;
        queue.add(start);

        for (GraphNode node : graph.nodes) {
            node.state = State.Unvisited;
        }

        while(!queue.isEmpty()) {
            GraphNode node = queue.remove();
            for (GraphNode neighbour : node.neighbours) {
                if (neighbour.state == State.Unvisited) {
                    if (neighbour == end) {
                        return true;
                    }
                    neighbour.state = State.Visited;
                    queue.add(neighbour);
                }
            }
        }
        return false;
    }

    class Graph {
        List<GraphNode> nodes;

        public Graph() {
            this.nodes = new ArrayList<>();
        }

        public Graph(List<GraphNode> nodes) {
            this.nodes = nodes;
        }

        public void add_node(GraphNode node) {
            this.nodes.add(node);
        }
    }

    class GraphNode {

        String name;
        List<GraphNode> neighbours;
        State state = State.Unvisited;

        public GraphNode(String name) {
            this(name, new ArrayList<>());
        }

        public GraphNode(String name, List<GraphNode> neighbours) {
            this.name = name;
            this.neighbours = neighbours;
        }

        public void add_neighbour(GraphNode node) {
            this.neighbours.add(node);
        }
    }
}