import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov() {
        this(3);
    }

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        super.setTraining(text);
        myMap.clear();
        for (int i = 0; i < myWords.length - myOrder + 1; i++) {
            WordGram gram = new WordGram(myWords, i, myOrder);
            if (!myMap.containsKey(gram)) {
                myMap.put(gram, new ArrayList<String>());
            }
            if (i + myOrder == myWords.length) {
                myMap.get(gram).add(PSEUDO_EOS);
            } else {
                String stringSub = myWords[i + myOrder];
                myMap.get(gram).add(stringSub);
            }
        }
    }

    @Override
    public ArrayList<String> getFollows(WordGram kGram) {
        if (!myMap.containsKey(kGram)) {
            throw new NoSuchElementException(kGram + " not in map");
        } else {
            return myMap.get(kGram);
        }
    }
}
