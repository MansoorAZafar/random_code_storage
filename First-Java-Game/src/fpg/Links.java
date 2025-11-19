package fpg;
/**
 * Allows for you to create a linked list that stores the scores
 *
 * @mansoor zafar
 * @2022-05-04
 */
public class Links{
    //Instance variables 
    private Node first; // creates the "head" of the node (start of node)
    private Node last; // creates the "tail" of the node (end of node)
    private int size; // creates the size of the node
    private int sHigh;
    public Links(){
        //Constructor
        size = 0; // sets size to 0
        sHigh = 0;
    }
    /*
     *********************************
     ** SCORES **
     *********************************
     * Input: takes in the scores
     * Inside: creates a new node with the scores
     * --> creates the next node which is equal to the head/first node
     * --> swaps the first node and the newly made one to make head/first the first node
     * --> checks if the last node is null, is so then this is the first item and sets it equal to first
     * --> increases size
     * Output: Inserts a node at the first position even if there is node after it...
     */
    public void insertFirst(int scores){
        Node node = new Node(scores); // creates new boxie
        node.next = first; // connects the two nodes by pointing the new made one to the original one
        first = node; // swaps first pos
        
        if(last == null){
            last = first; // if this is the first item being added
        }
        size++;
    }
    
    /*
     *********************************
     ** SCORES  **
     *********************************
     * Input: takes in the scores
     * Inside: creates a new node with the scores
     * --> creates the next node which is equal to the head/first node
     * --> swaps the first node and the newly made one to make head/first the first node
     * --> checks if the last node is null, is so then this is the first item and sets it equal to first
     * --> increases size
     * Output: Inserts a node at the first position even if there is node after it...
     */
    
    public void insert(int scores){
        if(first == null){
            // if empty
            insertFirst(scores); // creates a node
            return; // ends the process
        }
        Node node = new Node(scores); // creates a node with the needed info
        last.next = node; // sets the last node to the newly created node
        last = node; // sets last to actually be last
        size++; // increases size
    }
    
    /*
     * Input: n/a
     * Inside: checks if the first is equal to null
     * Output: returns if empty or not
     */
    public boolean isEmpty(){
        return first == null; // checks if first is equal to null
    }
    
    /*
     * Input: n/a
     * Inside: sets the front and end to null
     * Output: makes the list empty
     */
    
    public void empty(){
        first = null;
        last = null;
    } 
    
    /*
     * Input: n/a
     * Inside: Creates a node equal to first
     * --> creates the int one and two for to determine to highest score
     * --> check if the score is higher than the previous one
     * --> sets the highest score to score of the node with the highest score
     * --> returns -1 to end the process
     * Output:
     */
    public int findHighestScore(){
        Node node = first; // variables
        int storage = 0; // variables 
        int one, two; // variables 
        one = 0; two = 0; // variables
        if(isEmpty()){
        	setHighestScore(0);
        	return -1;
        }
        while(node != null){
        	if(one == 0){
        		storage = node.scores; 
        		one++;
        	}
        	if(storage < node.scores){
        		storage = node.scores;
        	}
        	node = node.next;
        }
        setHighestScore(storage);
        return -1;
    }
    
    /*
     * Input: takes in a integer
     * Inside: sets the highest score to the integer
     * Output: the highest score as the integer 
     */
    
    public void setHighestScore(int x){
        sHigh = x;
    }
    
    /*
     * Input: n/a
     * Inside: returns the highest score
     * Output: returns the highest score out all of scores
     */
    public int getHighestScore(){
        return sHigh;
    }
    
    /*
     *********************************
     ** SCORES **
     *********************************
     * Input: n/a
     * Inside: sets a node to first
     * --> tmp is set to tell which # of game this is
     * --> for loop to loop through all the scores and print their info
     * Output: all of the info of the score(s)
     */
    
    public void display(){
        Node temp = first; // sets the node to the first
        int tmp = 1; // counter for the # of employee this is
        while(temp != null){
            // checks while the node has info
            System.out.println(tmp + " score is : " + temp.scores); 
            temp = temp.next; // progresses the while loop by setting it to the next bit of info
            tmp++; // increases the counter for nth # of employee
        }
    }
    
    /*
     * Input: n/a
     * Inside: returns the size
     * Output: returns the size of how many items are inside the list
     */
    public int getSize(){
        return size;
    }    
    
    private class Node{
        // Instance variables
        private Node next; // for the next node and connected
        private int scores; // for the score
        
        /*
         * Constructor
         *********************************
         ** FOR SCORES **
         *********************************
         * Input: takes in the scores
         * Inside: sets all of the variables
         * Output: the info of the scores
         */
        public Node(int scores){
            this.scores = scores;
        }
        
        /*
         * * Constructor
         *********************************
         ** FOR NEXT NODE **
         *********************************
         ********************************** 
         * Input: takes in the next node & score
         * Inside: sets all of the variables
         * Output: the score of the next node
         */
        public Node(Node n, int scores){
            next = n;
            this.scores = scores;
        }
    }
}



