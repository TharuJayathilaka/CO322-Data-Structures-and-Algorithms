import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

class Test {
	
	// defining an array
	static ArrayList<String> table = new ArrayList<>();
	
	public static void main(String args[]) throws Exception {
		// call the method wordtable to separate sentences into words(file which is given by the user )
		try {
			wordtable("sample-text1.txt");
		}catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		
		Scanner scanner = new Scanner(System.in);
		// one can specify the number of buckets needed..
		// get that value from the user
		System.out.println("Enter the number of buckets: ");
		int bucketsNo=0;
		try {
			// get the no of bucket needed from the user
			bucketsNo = Integer.valueOf(scanner.nextLine());
			if(bucketsNo <= 0) {
				throw new Exception("Bucket number should be a positve integer");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		// call the three classes made for division method and multiplication method
		
		// division method
		HashTableImpDivision Divition_Method_Hash_Table = new HashTableImpDivision(bucketsNo); 
		
		// multiplication method
		HashTableImpMultiplication Multiplication_Method_Hash_Table = new HashTableImpMultiplication(bucketsNo);
		
		// modified method
		HashTableImpModified Modified_Method_Hash_Table = new HashTableImpModified(bucketsNo);
		

		// enter the tables to  twotables 
		for(String Name : table){
			Divition_Method_Hash_Table.insert(Name);
			Multiplication_Method_Hash_Table.insert(Name);
			Modified_Method_Hash_Table.insert(Name);
		}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//  insert a key, search a key 
		String key1,key2;
		System.out.println("Enter a key to insert to the hash table or press enter to skip: ");
		key1 = scanner.nextLine();
		if(!key1.isEmpty()) {
			Divition_Method_Hash_Table.insert(key1);
			Multiplication_Method_Hash_Table.insert(key1);
			Modified_Method_Hash_Table.insert(key1);
		}
		System.out.println("Enter a key to search from the hash table or press enter to skip: ");
		key2 = scanner.nextLine();
		if(!key2.isEmpty()) {
			System.out.println("Divition Method HashTable count: "+Divition_Method_Hash_Table.search(key2));
			System.out.println("Multiplication Method HashTable count : "+Multiplication_Method_Hash_Table.search(key2));
			System.out.println("Modified Method HashTable count : "+Modified_Method_Hash_Table.search(key2));
		}
		
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// CALCULATIONS of table 1 //
		
		
		// division method
		ArrayList<Integer> noOfFilledBuckets_table1;
		noOfFilledBuckets_table1 = Divition_Method_Hash_Table.noOfFilledBuckets();
		
		// get the first bucket Number of elemnts in the linked table
		// and give that value as the minimum and maximum as the each table
		int maximum1 = noOfFilledBuckets_table1.get(0);
		int minimum1 = noOfFilledBuckets_table1.get(0);
		
		for(int n=0; n<noOfFilledBuckets_table1.size(); n++) {
			if(noOfFilledBuckets_table1.get(n) > maximum1){
				maximum1 = noOfFilledBuckets_table1.get(n);
			}
			if(noOfFilledBuckets_table1.get(n) < minimum1){
				minimum1 = noOfFilledBuckets_table1.get(n);
			}
		}
		
		// AVERAGE
		double table1_1 = (table.size())*(table.size()-1)/2.0;
		double table1_0 = 0;
		double table1_2 = 0;
		for(int i=0; i<noOfFilledBuckets_table1.size(); i++) {
			table1_0 += (i*noOfFilledBuckets_table1.get(i)); 
			table1_2 += (noOfFilledBuckets_table1.get(i));
		}
		double s1=table1_2/noOfFilledBuckets_table1.size();
		// STANDARD DEVIATION
		double x1 = 0;
		for(int i=0; i<noOfFilledBuckets_table1.size(); i++) {
			x1 += (noOfFilledBuckets_table1.get(i)*i*i); 
		}
		x1 -= (Math.pow(table1_0/table1_1, 2)*table.size());
		double standradDeviation1 = Math.pow((x1/(table.size()-1)), 0.5);
		
		System.out.println("\nDivision method");
		System.out.printf("Minimum number of entries in a bucket: %d\n", minimum1);
		System.out.printf("Max number of entries in a bucket: %d\n",maximum1);
		System.out.printf("Average: %f\n",s1);
		System.out.printf("Standard deviation: %f\n\n", standradDeviation1);
		
		
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		// CALCULATIONS of table 2 //
		
		// store the number of keys filled in the each bucket
		
		// multiplication method
		ArrayList<Integer> noOfFilledBuckets_table2;
		noOfFilledBuckets_table2 = Multiplication_Method_Hash_Table.noOfFilledBuckets();
		
		// get the first bucket Number of elemnts in the linked table
		// and give that value as the minimum and maximum as the each table
		int minimum2 = noOfFilledBuckets_table2.get(0);
		int maximum2 = noOfFilledBuckets_table2.get(0);
		
		for(int m=0; m<noOfFilledBuckets_table2.size(); m++) {
			if(noOfFilledBuckets_table2.get(m) > maximum2){
				maximum2 = noOfFilledBuckets_table2.get(m);
			}
			if(noOfFilledBuckets_table2.get(m) < minimum2){
				minimum2 = noOfFilledBuckets_table2.get(m);
			}
		}
		
		// AVERAGE
		double table2_1 = (table.size())*(table.size()-1)/2.0;
		double table2_0 = 0;
		double table2_2 = 0;
		for(int i=0; i<noOfFilledBuckets_table2.size(); i++) {
			table2_0 += (i*noOfFilledBuckets_table2.get(i)); 
			table2_2 += (noOfFilledBuckets_table2.get(i));
		}
	    double s2=table2_2/noOfFilledBuckets_table2.size();
		// STANDARD DEVIATION
		double x2 = 0;
		for(int i=0; i<noOfFilledBuckets_table2.size(); i++) {
			x2 += (noOfFilledBuckets_table2.get(i)*i*i); 
		}
		x2 -= (Math.pow(table2_0/table2_1, 2)*table.size());
		double standradDeviation2 = Math.pow((x2/(table.size()-1)), 0.5);
		
		System.out.println("Multiplication method");
		//System.out.println(table2_0);
		System.out.printf("Minimum number of entries in a bucket: %d\n", minimum2);
		System.out.printf("Max number of entries in a bucket: %d\n",maximum2);
		System.out.printf("Average: %f\n",s2);
		System.out.printf("Standard deviation: %f\n\n", standradDeviation2);
		
		
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		// CALCULATIONS of table 3 //
		
		// store the number of keys filled in the each bucket
		
		// multiplication method
		ArrayList<Integer> noOfFilledBuckets_table3;
		noOfFilledBuckets_table3 = Modified_Method_Hash_Table.noOfFilledBuckets();
		
		// get the first bucket Number of elemnts in the linked table
		// and give that value as the minimum and maximum as the each table
		int minimum3 = noOfFilledBuckets_table3.get(0);
		int maximum3 = noOfFilledBuckets_table3.get(0);
		
		for(int m=0; m<noOfFilledBuckets_table3.size(); m++) {
			if(noOfFilledBuckets_table3.get(m) > maximum3){
				maximum3 = noOfFilledBuckets_table3.get(m);
			}
			if(noOfFilledBuckets_table3.get(m) < minimum3){
				minimum3 = noOfFilledBuckets_table3.get(m);
			}
		}
		
		// AVERAGE
		double table3_1 = (table.size())*(table.size()-1)/2.0;
		double table3_0 = 0;
		double table3_2 = 0;
		for(int i=0; i<noOfFilledBuckets_table3.size(); i++) {
			table3_0 += (i*noOfFilledBuckets_table3.get(i)); 
			table3_2 += (noOfFilledBuckets_table3.get(i));
		}
        double s3=table3_2/noOfFilledBuckets_table3.size();
		// STANDARD DEVIATION
		double x3 = 0;
		for(int i=0; i<noOfFilledBuckets_table3.size(); i++) {
			x3 += (noOfFilledBuckets_table3.get(i)*i*i); 
		}
		x3 -= (Math.pow(table3_0/table3_1, 2)*table.size());
		double standradDeviation3 = Math.pow((x3/(table.size()-1)), 0.5);
		
		System.out.println("Modified method");
		System.out.printf("Minimum number of entries in a bucket: %d\n", minimum3);
		System.out.printf("Max number of entries in a bucket: %d\n",maximum3);
		System.out.printf("Average: %f\n",s3);
		System.out.printf("Standard deviation: %f\n\n", standradDeviation3);		
		
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
		
	// print the buckets and no of elements in it.	
		
		System.out.println("Do you want to print keys and there no of hits. If yes type 1 else type 0.");
		if(Integer.valueOf(scanner.nextLine())==1){
			System.out.println("Division method");
			for(int n=0; n<noOfFilledBuckets_table1.size(); n++) {
				System.out.printf("%d	%d\n",n+1,noOfFilledBuckets_table1.get(n));
			}
			System.out.println("Multiplication method");
			for(int m=0; m<noOfFilledBuckets_table2.size(); m++) {
				System.out.printf("%d	%d\n",m+1,noOfFilledBuckets_table2.get(m));
			}
			System.out.println("Modified method");
			for(int m=0; m<noOfFilledBuckets_table3.size(); m++) {
				System.out.printf("%d	%d\n",m+1,noOfFilledBuckets_table3.get(m));
			}
		}else{
			System.exit(0);
		}
		
	}// end of the main function
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void wordtable(String name){
		try(Scanner scanner = new Scanner(Paths.get(name))){
			// read the file
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				// split words
				String arr[] = line.split("[:,.;-]+|\\s|\\d");
				for(String key : arr){
					if(!key.isEmpty()){ 
						
						// replacing characters unnecessary characters
						String newWord = key.replaceAll("[^a-zA-Z]+", "");
						table.add(newWord.toLowerCase());
					} 
				}
			}

		}catch (FileNotFoundException e){
			System.out.println("File does not exists");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("File reading failed");
			System.exit(0);
		}		
	}// end of the wordtable function

}