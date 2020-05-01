import java.util.*;
import java.util.Queue;

public class GraphImplementation implements Graph 
{

    protected int [][] adj;
    protected int vertices;
    protected int [] incident;

    /*
     Constructs and returns a graph with the number of vertices 
     passed as the argument. Vertices have IDs,numbered 0, 1, ..., 
     vertices-1.No edges exist between vertices at instantiation.
     */

    public GraphImplementation(int vertices)
    {
        this.vertices = vertices;
        adj = new int[vertices][vertices];
        incident = new int[vertices];
    }

    //Adds a directed edge between two vertices from src to tar.
    public void addEdge(int src, int tar) throws Exception 
    {
        adj[src][tar]++;
        incident[tar]++;
    }

    /*
     Returns a List of vertex IDs, with each ID representing a vertex
     which is the destination of the edge originating from the source
     vertex, passed as the argument.
     */
    public List<Integer> neighbors(int vertex) throws Exception 
    {

        List<Integer> list = new LinkedList<>();
        for (int v = 0; v < vertices; v++)
        {
            if (adj[vertex][v] == 1)
            {
                list.add(v);
            }
        }

        return list;
    }

    protected int noIncidents (int [] incidents) 
    {
        for (int i = 0; i < incidents.length; i++) 
        {
            if (incidents[i] == 0){
                return i;
            }
        }
        return -1;
    }

    /*
    Prints (to console) one ordering of vertices such that each directed edge 
    (v1, v2) from vertex v1 to vertex v2, v1 comes before v2 in the ordering. 
    If such an ordering is not possible (due to cycles, for example), 
    this function must indicate so, though it may print a partial solution in so doing.
    */
    public List<Integer> topologicalSort() {
        

        List<Integer> schedule = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
       
            int vertex = noIncidents(incident);
            incident[vertex] -= 1;
      
            schedule.add(vertex);

            for (int j = 0; j < vertices; j++){
     
                if(adj[vertex][j] == 1) {
                    incident[j] -= 1;
                }
            }
        }

        return schedule;
    }

}