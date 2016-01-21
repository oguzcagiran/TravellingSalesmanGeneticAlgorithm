package tsp;

/**
 * 
 * @author oguzcagiran
 * 
 * This class represents
 * distance between cities
 * 
 */
public class CityDistance {
    
    private int[][] distance = new int[10][10];
    
    public CityDistance() {
        
        distance[0][0] = -1;
        distance[0][1] = 55;
        distance[0][2] = 34;
        distance[0][3] = 32;
        distance[0][4] = 54;
        distance[0][5] = 40;
        distance[0][6] = 36;
        distance[0][7] = 40;
        distance[0][8] = 37;
        distance[0][9] = 53;
        
        distance[1][0] = 64;
        distance[1][1] = -1;
        distance[1][2] = 54;
        distance[1][3] = 55;
        distance[1][4] = 73;
        distance[1][5] = 45;
        distance[1][6] = 71;
        distance[1][7] = 50;
        distance[1][8] = 53;
        distance[1][9] = 52;
        
        distance[2][0] = 51;
        distance[2][1] = 48;
        distance[2][2] = -1;
        distance[2][3] = 41;
        distance[2][4] = 40;
        distance[2][5] = 58;
        distance[2][6] = 55;
        distance[2][7] = 33;
        distance[2][8] = 35;
        distance[2][9] = 37;
        
        distance[3][0] = 47;
        distance[3][1] = 46;
        distance[3][2] = 55;
        distance[3][3] = -1;
        distance[3][4] = 49;
        distance[3][5] = 45;
        distance[3][6] = 56;
        distance[3][7] = 52;
        distance[3][8] = 57;
        distance[3][9] = 55;
        
        distance[4][0] = 50;
        distance[4][1] = 39;
        distance[4][2] = 43;
        distance[4][3] = 52;
        distance[4][4] = -1;
        distance[4][5] = 26;
        distance[4][6] = 40;
        distance[4][7] = 39;
        distance[4][8] = 38;
        distance[4][9] = 33;
        
        distance[5][0] = 60;
        distance[5][1] = 49;
        distance[5][2] = 48;
        distance[5][3] = 57;
        distance[5][4] = 58;
        distance[5][5] = -1;
        distance[5][6] = 48;
        distance[5][7] = 47;
        distance[5][8] = 48;
        distance[5][9] = 48;
        
        distance[6][0] = 51;
        distance[6][1] = 37;
        distance[6][2] = 44;
        distance[6][3] = 43;
        distance[6][4] = 38;
        distance[6][5] = 40;
        distance[6][6] = -1;
        distance[6][7] = 64;
        distance[6][8] = 48;
        distance[6][9] = 47;
        
        distance[7][0] = 58;
        distance[7][1] = 41;
        distance[7][2] = 53;
        distance[7][3] = 45;
        distance[7][4] = 47;
        distance[7][5] = 43;
        distance[7][6] = 74;
        distance[7][7] = -1;
        distance[7][8] = 43;
        distance[7][9] = 42;
        
        distance[8][0] = 53;
        distance[8][1] = 38;
        distance[8][2] = 40;
        distance[8][3] = 33;
        distance[8][4] = 36;
        distance[8][5] = 58;
        distance[8][6] = 35;
        distance[8][7] = 30;
        distance[8][8] = -1;
        distance[8][9] = 31;
        
        distance[9][0] = 60;
        distance[9][1] = 39;
        distance[9][2] = 40;
        distance[9][3] = 56;
        distance[9][4] = 41;
        distance[9][5] = 41;
        distance[9][6] = 45;
        distance[9][7] = 59;
        distance[9][8] = 19;
        distance[9][9] = -1;
    }
    
    public int getDistance(int from, int to) {
        
        return distance[from-1][to-1];
        
    }
    
}