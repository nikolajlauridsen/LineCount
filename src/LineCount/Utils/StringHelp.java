package LineCount.Utils;

public interface StringHelp {
    /**
     * repeat a string
     * function is taken from http://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java
     * @param str String to be repeated
     * @param count desired number of repetitions
     * @return repeated string
     */
    static String repeat(String str, int count){
        if(count <= 0) {return "";}
        return new String(new char[count]).replace("\0", str);
    }

    /**
     * Padd a string with whitespace to a desired length
     * @param str String to be padded
     * @param width Desired width of padded string
     * @return a padded String
     */
    static String addPadding(String str, int width){
        String paddedString = repeat(" ", (width-str.length())/2) + str + repeat(" ", (width-str.length())/2);
        // If the desired width is uneven add the missing space to the right side
        if (paddedString.length() < width){
            paddedString += " ";
        }
        return paddedString;
    }
}
