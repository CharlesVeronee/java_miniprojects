import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();
		for(int i = 0; i<text.length()-myOrder+1; i++) {
			String sub = myText.substring(i, i+myOrder);
			if (!myMap.containsKey(sub)) {
				myMap.put(sub, new ArrayList<>());
			}
			if (i+myOrder == myText.length()) {
				myMap.get(sub).add(PSEUDO_EOS);
			}
			else{
				String stringSub = myText.substring(i+myOrder, i+myOrder+1);
				myMap.get(sub).add(stringSub);
			}

		}
	}


	@Override
	public ArrayList<String> getFollows(String key) {
		if(!myMap.containsKey(key)) {
			throw new NoSuchElementException(key + " not in map");
		}
		else {
			return myMap.get(key);
		}
	}
}	
