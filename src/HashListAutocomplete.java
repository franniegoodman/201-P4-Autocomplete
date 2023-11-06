import java.util.*;

public class HashListAutocomplete implements Autocompletor {
   
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    public HashListAutocomplete(String[] terms, double[] weights) {
        if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}
        if (terms.length != weights.length) {
			throw new IllegalArgumentException("Terms and weights don't match");
        }
        initialize(terms, weights);
    }
    @Override
    public List<Term> topMatches(String prefix, int k) {
        if (prefix.length() > MAX_PREFIX) prefix = prefix.substring(0, MAX_PREFIX);
        List<Term> list = new ArrayList<>();
        if (myMap.containsKey(prefix)){
            List<Term> all = myMap.get(prefix);
            list = all.subList(0, Math.min(k, all.size()));
        }
        return list;
    }

    @Override
    public void initialize(String[] terms, double[] weights) {
        myMap = new HashMap<>();
        mySize = 0;
        for (int i = 0; i < terms.length; i++){
            String term = terms[i];
            for (int n = 0; n<Math.min(MAX_PREFIX, term.length()) + 1; n++){
                String prefix = term.substring(0, n);
                if (! myMap.containsKey(prefix)){
                    myMap.put(prefix, new ArrayList<>());
                    mySize += (BYTES_PER_CHAR * prefix.length());
                }
                Term t = new Term(terms[i], weights[i]);
                myMap.get(prefix).add(t);
                mySize += (BYTES_PER_CHAR * t.getWord().length()) + BYTES_PER_DOUBLE;
            }
        }
        for (String term: myMap.keySet()){
            Collections.sort(myMap.get(term), Comparator.comparing(Term::getWeight).reversed());
        }
    }


    @Override
    public int sizeInBytes() {
		return mySize;

    }  
}



