import java.util.Comparator;

/**
 * Factor pattern for obtaining PrefixComparator objects
 * without calling new. Users simply use
 *
 *     Comparator<Term> comp = PrefixComparator.getComparator(size)
 *
 * @author owen astrachan
 * @date October 8, 2020
 */
public class    PrefixComparator implements Comparator<Term> {

    private int myPrefixSize; // size of prefix

    /**
     * private constructor, called by getComparator
     * @param prefix is prefix used in compare method
     */
    private PrefixComparator(int prefix) {
        myPrefixSize = prefix;
    }


    /**
     * Factory method to return a PrefixComparator object
     * @param prefix is the size of the prefix to compare with
     * @return PrefixComparator that uses prefix
     */
    public static PrefixComparator getComparator(int prefix) {
        return new PrefixComparator(prefix);
    }


    @Override
    /**
     * Use at most myPrefixSize characters from each of v and w
     * to return a value comparing v and w by words. Comparisons
     * should be made based on the first myPrefixSize chars in v and w.
     * @return < 0 if v < w, == 0 if v == w, and > 0 if v > w
     */
    public int compare(Term v, Term w) {
        // if v or w < size, compare # of chars in smaller term
        // else compare size chars
        // while loop maybe?? iterate until one char is greater than another, break and return
        String myV = v.getWord();
        String myW = w.getWord();
        int limit = myPrefixSize;
        if (myV.length() < myPrefixSize || myW.length() < myPrefixSize){
            limit = Math.min(myV.length(), myW.length());
        }
        int dex = 0;
        while (dex < limit){
            if (myV.charAt(dex) > myW.charAt(dex)) return 1;
            if (myV.charAt(dex) < myW.charAt(dex)) return -1;
            dex++;
        }
        //checks if prefixes equal but one word terminates and the other doesn't
        if (dex != myPrefixSize){
            if (myV.length() == dex) return -1;
            return 1;
        }
        return 0;
        }

    }

