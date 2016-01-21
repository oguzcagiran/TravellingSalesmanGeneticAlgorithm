package tsp;


/**
 * @author oguzcagiran
 */
public class TravellingSalesman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Evolution e = new Evolution(0.70, 0.20);
        
        e.start(20000);
        
    }
    
}