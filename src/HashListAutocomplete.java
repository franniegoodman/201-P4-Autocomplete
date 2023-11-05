import java.util.List;
import java.util.Map;
import java.util.*;

public class HashListAutocomplete implements Autocompletor {
   
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    public HashListAutocomplete(String[] terms, double[] weights) {
        if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}
        initialize(terms, weights);
    }

    @Override
    public List<Term> topMatches(String prefix, int k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initialize(String[] terms, double[] weights) {
        for (String t : terms){
            int length = 0;
            while (length < MAX_PREFIX){
                String substr = t.substring(0, length);
                myMap.put(substr, new ArrayList<Term>());
            }
        }
        
    }

    @Override
    public int sizeInBytes() {
        return mySize;
    }  
}

