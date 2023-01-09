import java.util.Map;
import java.util.HashMap;
class task4 {
    static int [][] cost = {{0, 3, 12, 23, 41, 6, 211, 4}, // cost from 0 
			    {0, 0,  2,  4, 34, 4, 12, 43}, // cost from 1 
			    {0, 0,  0,  12, 3, 5, 2,  4}, // cost from 2 
			    {0, 0,  0,  0, 12, 4, 32, 3}, // cost from 3 
			    {0, 0,  0,  0,  0, 4, 3, 12},  // cost from 4
			    {0, 0,  0,  0,  0, 0, 3, 234},  // cost from 5
			    {0, 0,  0,  0,  0, 0, 0, 12},  // cost from 6
			    {0, 0,  0,  0,  0, 0, 0, 0}  // cost from 7 
    };
    static int iMax = 7;
    static int jMax = 7;

    // Just for testing, min cost from 0 to 4 should be 8.
    //static int answer = 8;
    static int comp =0;
    
    static Map<String,Integer> costMap = null;

    public static String indexToKey(int i, int j) {	//a method to get the key using matrix position
	return Integer.toString(i) + Integer.toString(j);
    }

    public static Map<String, Integer> initHashMap() {  //make a map to store the values
	Map<String,Integer> costs = new HashMap<String, Integer>();
	for(int i=0; i < jMax-1; i++) {
	    String s = indexToKey(i, i+1); // get the key as a string
	    costs.put(s, cost[i][i+1]); // include key and relevant value to that key in to map
	}
	return costs; 
    }
    
    public static int minCostDynamicPro(int i, int j) {  // implement the fuction using dynamic programming
    comp ++;
	if(costMap == null) costMap = initHashMap(); // if map is empty
	if(costMap.containsKey(indexToKey(i,j))) 	//check whether the value is stored before or not
	    return costMap.get(indexToKey(i,j));
	

	int min = cost[i][j];
	for(int k = i+1; k < j; k++) {
	    int tmp = minCostDynamicPro(i, k) + minCostDynamicPro(k, j);
	    if(min > tmp) min = tmp; 	    
	}
	costMap.put(indexToKey(i,j), min); // include key and relevant value to that key in to map
	return min;
    }
	
    static long startTime; //variables to get the run time,
	static long endTime; //variables to get the run time,
	static long Time; //variables to get the run time,
    public static void main(String [] args) {
	for(int l=1; l<=jMax; l++) {
	    comp = 0;	
        startTime=System.nanoTime(); // get the start time	
		
	    int r = minCostDynamicPro(0,l);// call the minCostDynamicPro function
		
		endTime=System.nanoTime(); // get the time now as stop
		Time=endTime-startTime; // calculate the time taken to run the code
	    System.out.println("	Complexity=" + comp +"		Run Time(nanoseconds)=" + Time);// print results
	}
	
    }
} 