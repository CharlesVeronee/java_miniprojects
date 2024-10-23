
/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author CHARLES VERONEE
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		for (int i = 0; i<size;i++) {
			myWords[i] = source[i+start];
		}
		myToString = "";
		myHash = 0;

		// TODO: initialize all instance variables
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Complete this comment
	 * @return
	 */
	public int length(){
		// TODO: change this
		return this.myWords.length;
	}


	/**
	 * Complete appropriate comment here
	 * @param other
	 * @return
	 */
	@Override
	public boolean equals(Object other) {
		if (! (other instanceof WordGram) || other == null){
			return false;
		}
		WordGram wordgram = (WordGram) other;
		if(this.length() == wordgram.length() && this.toString().equals(wordgram.toString())){
			return true;
		}
		return false;
		// TODO: Complete this method

	}

	@Override
	public int hashCode(){
		// TODO: complete this method: assign to myHash as needed
		if(myHash == 0){
			myHash = this.toString().hashCode();
		}
		return myHash;
	}
	

	/**
	 * Create and complete this comment
	 * @param last is last String of returned WordGram
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		// TODO: Complete this method

		for(int i = 0;i<wg.length()-1;i++){
			wg.myWords[i] = this.myWords[i+1];
		}
		wg.myWords[wg.length()-1] = last;
		return wg;
	}

	@Override
	public String toString(){
		// TODO: Complete this method, assign to myToString as needed
		if(myToString.equals("")) {
			myToString = String.join(" ", myWords);
		}
		return myToString;
	}
}
