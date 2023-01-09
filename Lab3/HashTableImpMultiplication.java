/*********************************************
 
	* CO322: Data structures and algorithms
 
	* Implementation of the hashTable
 *********************************************/
import java.util.ArrayList;
import java.util.LinkedList;

class HashTableImpMultiplication implements HashTable {

    /* Put your code here */
	
	// With hash hash_tables, key k maps or hashes to slot T[h[k]]
	// h[k] is hash value of key k
	// h is a linked list
	// T is a normal array
	private ArrayList<LinkedList<Key>> hash_table;
	//hash_table elements are called as buckets 
	private int buckets; 

	//A method to create a HashTable where one can specify the number of buckets needed. This can be done with the constructor itself.
	HashTableImpMultiplication(int buckets) {
	
		// create a open hash table with given number of buckets 
		// define the new hash table 
		this.hash_table = new ArrayList<LinkedList<Key>>();
		this.buckets = setBuckets(buckets);
		// allocate the memory for the given number of buckets
		for(int i=0; i<this.buckets; i++){
			this.hash_table.add(null);
		}
	}
	
	// Multiplication Method
	public int valueHash(String key){
		int asciiValue = 0;
		// get the sum of ascii values
		// since there are 128 basic ascii values should get the power of 128
		for(int j=0; j<key.length(); j++){
			asciiValue += ((int)key.charAt(j))*(Math.pow(128,(key.length()-1-j)));
		}
		double A = (Math.pow(5, 0.5)-1) / 2;
		
		// calculate the hash table bucket number
		int bucketNo = (int) Math.floor(this.buckets*((A*asciiValue)%1));
		// return the bucket number
		return bucketNo;
	}
	
	// get the how many buckets are filled in the hash table
	// return type is a array(list)
	public ArrayList<Integer> noOfFilledBuckets(){
		// define a new array for that
		ArrayList<Integer> list = new ArrayList<Integer>();
		// check each bucket and find the size 
		for(int n=0; n<this.buckets; n++) {
			list.add(this.hash_table.get(n)==null ? 0 : this.hash_table.get(n).size());
		}
		//return the list (size )		
		return list;
	}

    // get the number of buckets as a power of 2
	public int setBuckets(int buckets) {
		double value = Math.log(buckets)/Math.log(2);
		
		// number of buckets is already a power of 2
		if(value-Math.floor(value) == 0) {
			// if the number of buckets is a power of 2 return the same value
			return buckets;
		}else{
			//return the power of 2 which is closer to the given value
			int p = (int) Math.ceil(value);
			int q = (int) Math.pow(2, p);
			return q;
		}
	}
	
	
	/* insert the given key to a open hash tabled.
     
	 * With each key you have a count of how many times it 
    
	 * was inserted 
     
	 */
	public void insert(String Name){
		// no need to consider the case sensitiveness
		// so convet all the letters to lowercase
		String key1 = Name.toLowerCase();
		//call the above built valuehash function to get the bucket number
		int value = this.valueHash(key1); 
		// get the relevant bucket
		// inside a bucket, there is a linked list
		// should insert the given key into that linked list
		LinkedList<Key> node = this.hash_table.get(value); 
		
		// if this is the first time that bucket is using
		if(node == null){
			node = new LinkedList<>();
			node.add(new Key(key1));
			hash_table.set(value, node);
		} else {
			int alreadyIn = 0;
			// Key is already in the list
			for(int i=0; i<node.size(); i++){
				if(node.get(i).getName().equals(key1)){
					// should increase the count 
					node.get(i).increment();
					// otherwise consider this as a new key a new key will be inserted
					alreadyIn = 1;
					break;
				}
			}
			// Key is new to the linked list
			if(alreadyIn==0){
				node.add(new Key(key1));
				//add the elemant to the hash table
				hash_table.set(value, node); 
			}
		}
	}

    
	/* given the key return the number of times it was inserted 
     
	 * to the hash_table 
     
	*/
	public int search(String Name){
		// no need to consider the case sensitiveness
		// so convet all the letters to lowercase
		String key2 = Name.toLowerCase(); 
		int number = 0;
		// consider the input given to this method
		int value = this.valueHash(key2);
		// get the linked list relevant to that value
		LinkedList<Key> node = this.hash_table.get(value);
        // node cannot be empty or null
		if(!node.isEmpty() && node != null){
			// linear searching for the given key
			for(int i=0; i<node.size(); i++){
				if(node.get(i).getName().equals(key2)){
					// get the count
					number = node.get(i).getCount();
					break;
				}
			}
		}
		return number;
	}

}// end Hashhash_tableImp 