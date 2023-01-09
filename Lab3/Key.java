public class Key {
    private String KeyName; 
    private int count;

    public Key(String KeyName){
        this.KeyName = KeyName;
        this.count = 1; 
    }
	// incrementing the count
    public void increment(){
        this.count++;
    }
    public String getName(){
        return this.KeyName;
    }
    public int getCount(){
        return this.count;
    }
}