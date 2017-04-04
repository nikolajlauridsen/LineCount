package LineCount.Utils;

import java.util.ArrayList;

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
        // Every char is initiated as \0 (all bits flipped to zero)
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


    /**
     * Get a row divider for a string table
     * @param widths Arrary of desired widths, each integer in the array should correspond to a column width
     * @return A divider for dividing rows in a table
     */
    static String getDivider(int[] widths){
        StringBuilder divider = new StringBuilder("|");
        for (int width : widths) {
            for (int n = 0; n < width + 2; n++) { // Note that it loops two additional times, expecting
                divider.append("-");                   // the cells to be padded with one char on each time
            }
            divider.append("|");
        }
        return divider.toString();
    }


    /**
     * Return a string showing the percentage difference between two numbers displaying the part
     * and percentages: IE. 50, 200 would return "50 (25.0%)"
     * @param part to show
     * @param total the total
     * @return percentage string in the following format "50 (25.0%)"
     */
    static String getPercentageString(int part, int total){
        return String.format("%d (%.1f%%)", part, ((double)part/(double)total)*100);
    }


    /**
     * Generate a string table, displaying data in a neat way as a string.
     * @param rows Two dimensional string array for rows IE. String[rows][columns]
     * @param columns String array of titles, is expected to be the same length as columns in rows
     * @return String Array containing the table
     */
    static String[] generateTable(String[][] rows, String[] columns){
        Character cornerChar = '+';

        int[] columnWidths = new int[columns.length];
        // Find the widest row in each column and assign
        // it's width to that
        for(int i = 0; i < columns.length; i++){
            columnWidths[i] = columns[i].length();
            for(String[] row: rows){
                if (row[i].length() > columnWidths[i]){
                    columnWidths[i] = row[i].length();
                }
            }
        }

        ArrayList<String> table = new ArrayList<>();
        StringBuilder headerBuilder, rowBuilder;

        // Create header
        headerBuilder = new StringBuilder("|");
        for (int i = 0; i < columns.length; i++){
            headerBuilder.append(addPadding(columns[i], columnWidths[i] + 2)).append("|");
        }
        int tableWidth = headerBuilder.length();
        table.add(cornerChar + repeat("-", tableWidth-2) + cornerChar);  // Add top border
        table.add(headerBuilder.toString()); // Add title row

        // Create rows
        for (String[] row : rows){
            table.add(getDivider(columnWidths));  // Add a divider, splitting the rows

            // Build the row.
            rowBuilder = new StringBuilder("|");
            for (int i = 0; i < row.length; i++){
                rowBuilder.append(addPadding(row[i], columnWidths[i] + 2)).append("|");
            }
            table.add(rowBuilder.toString());  // Add it
        }
        table.add(cornerChar + repeat("-", tableWidth-2) + cornerChar);  // Add bottom border

        return table.toArray(new String[0]);
    }

    /**
     * Get an exceptions StackTrack as a string
     * @param throwable Exception to convert
     * @return String of StackTrace
     */
    static String errorToString(Throwable throwable){
        // Create a new string builder and add the error type
        StringBuilder trace = new StringBuilder(throwable.toString() + "\n");

        // Go through all the trace elements and append them to the string builder
        for (StackTraceElement element: throwable.getStackTrace()){
            // Add a tab + at as well to mimic printStackTrace
            trace.append("\tat ");
            trace.append(element.toString());
            trace.append("\n");
        }

        return trace.toString();
    }
}
