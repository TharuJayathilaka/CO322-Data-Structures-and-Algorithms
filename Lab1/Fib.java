class Fib {

    public static int fib_r(int x) {
	if(x <= 2) return 1;
	return fib_r(x-1) + fib_r(x-2);
    }

    public static int fib_i(int x) {
	int a=1, b=1, fib=1, i=2;
	while(i<x) {
	    fib = a + b;
	    a = b;
	    b = fib;
	    i+=1;
	}
	return fib;
    }


    public static void main(String [] args) {
	int x,y,i;
		long[] time1,time2; // get two arrays to store the run time
		time1 = new long[41];
		time2 = new long[41];
		time1[0]=0; // initialize the first element
		time2[0]=0;
	
		for(x=1; x<41; x++){
			long startTime=System.nanoTime(); // get the start time
			fib_r(x); // run the recursive method for that relevant method
			long endTime=System.nanoTime(); // get the time now as stop
			long Time=endTime-startTime; // calculate the time taken to run the code
			time1[x]=Time; // add run time to the array
		}
		for(y=1; y<41; y++){
			long startTime=System.nanoTime(); // get the start time
			fib_i(y); // run the iteration method for that relevant method
			long endTime=System.nanoTime(); // get the time now as stop
			long Time=endTime-startTime; // calculate the time taken to run the code
			time2[y]=Time; // add run time to the array
		}
	    
		System.out.println("\n" + "Using Recursive method to find  Fibonacci number" +"\n");
		
		for(i=0; i<41; i++){
			System.out.println(time1[i]); // print the time   array 1
		}
		
		System.out.println("\n" + "Using Iteration method to find  Fibonacci number" +"\n");
		
		for(i=0; i<41; i++){
			System.out.println(time2[i]); // print the time array 2
		}
	}
}

	
