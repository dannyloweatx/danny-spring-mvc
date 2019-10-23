package com.dannysplayground.platform;
import com.dannysplayground.helpers.NumberNames;
import com.dannysplayground.helpers.TenNames;
import com.dannysplayground.helpers.NumberPlaceName;


public class DannyUtils {
    private static final NumberNames NUM_NAMES;
    private static final TenNames TEN_NAMES;
    private static final NumberPlaceName NUM_PLACE_NAMES;
    private static final int HUNDREDS_PLACE = 2;
    private static final String EMPTY_STRING = "";
    private static final String SEPERATOR_STRING = " ";
    private static final String TENS_SEPERATOR_STRING = "-";
    private static final String NEGATIVE_INDICATOR = "Negative ";
    private static final String NULL_NAME = "Null";
    
    private DannyUtils () {}
    
    static {
        NUM_NAMES = new NumberNames();
        TEN_NAMES = new TenNames();
        NUM_PLACE_NAMES = new NumberPlaceName();
    }
    
    public static String removeChar(String inputString, String replaceString) {
        
        if(inputString.length()<=0) {
            return inputString;
        }
        
        StringBuilder ret = new StringBuilder(inputString);
        ret.setCharAt(0, Character.toUpperCase(ret.charAt(0)));
    
        int index = 0;
            while ((index = ret.indexOf(replaceString, index)) >= 0) {
                ret.deleteCharAt(index);
                if(index<ret.length()) {
                     ret.setCharAt(index, Character.toUpperCase(ret.charAt(index)));
                }
            }
        return ret.toString();
    }
    
    public static String removeChar(String inputString) {
       return removeChar(inputString,"_");
    }
    
    /**
     * Returns the name of a number in string form of an integer
     * <p>
     * This method will return the number name as a string of integer values
     * ranging from <code>Long.MIN_VALUE</code> to <code>Long.MAX_VALUE</code>.
     * 
     * If <code>null</code> is passed in as an argument this method
     * returns the string "Null"
     *
     * @param value
     *            integer
     * @return Name of an integer number as a string
     */
    public static String numberToString(Integer value) {
        if (value == null) {
            return NULL_NAME;
        } else {
            return numberToString((long) value);
        }
    }

    /**
     * Returns the name of a number in string form of an integer
     * <p>
     * This method will return the number name as a string of integer values
     * ranging from <code>Long.MIN_VALUE</code> to <code>Long.MAX_VALUE</code>.
     *
     * @param value
     *            integer
     * @return Name of an integer number as a string
     */
    public static String numberToString(long value) {
        String prefix = EMPTY_STRING;
        
        if (value < 0) prefix = NEGATIVE_INDICATOR;
        
        return prefix + getNumberName(value);
    }

    private static String getNumberName(long value) {
        long nextvalue = 0;
        boolean isMinimum = false;
        
        if (value == Long.MIN_VALUE) {
            isMinimum = true;
        }
        
        // Handles numbers less than 20
        if (Math.abs(value) < 20 && !isMinimum) return NUM_NAMES.getNumberName(Math.abs((int) value));

        // Handles 20 or higher but less than 100
        if (Math.abs(value) >= 20 && Math.abs(value) < 100 && !isMinimum) {
            nextvalue = Math.abs(value) % 10;
            return TEN_NAMES.getTenName(Math.abs((int) (value / 10) % 10))
                    + addNextValue(nextvalue, TENS_SEPERATOR_STRING);
        }

        // Handles numbers from 100 to 999
        if (Math.abs(value) >= 100 && Math.abs(value) < 1000 && !isMinimum) {
            nextvalue = Math.abs(value) % 100;
            return getNumberName(value / 100) 
                    + SEPERATOR_STRING 
                    + NUM_PLACE_NAMES.getNumberPlaceName(HUNDREDS_PLACE)
                    + addNextValue(nextvalue, SEPERATOR_STRING);
        }

        // Numbers larger than greater that or equal to 1000 get broken up
        // by short scale (thousands, millions, billions, etc.) 
        int tenthPower = (int) Math.floor(Math.log10(Math.abs(value + (isMinimum ? 1 : 0))));
        tenthPower = (tenthPower - (tenthPower % 3));

        long divisor = (long) Math.pow(10, tenthPower);
        long difference = (value < 0 ? -1 : 1) * Math.abs((value / divisor) * divisor);
        
        nextvalue = Math.abs(value - difference) ;

        return getNumberName(value / divisor) 
                + SEPERATOR_STRING 
                + NUM_PLACE_NAMES.getNumberPlaceName(tenthPower)
                + addNextValue(nextvalue, SEPERATOR_STRING);

    }

    private static String addNextValue(long nextvalue, String seperator) {
        return nextvalue > 0 ? seperator.concat(getNumberName(nextvalue)) : EMPTY_STRING;
    }
    
}