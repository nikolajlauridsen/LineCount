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

    static String getDevider(int[] widths){
        String devider = "|";
        for(int i = 0; i < widths.length; i++){
            for(int n = 0; n < widths[i]+2; n++){
                devider += "-";
            }
            devider += "|";
        }
        return devider;
    }

    static String getPercentageString(int part, int total){
        return String.format("%d (%.1f%%)", part, ((double)part/(double)total)*100);
    }

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
        String header, rowString;

        // Create header
        header = "|";
        for (int i = 0; i < columns.length; i++){
            header += addPadding(columns[i], columnWidths[i]+2) + "|";
        }
        int tableWidth = header.length();
        table.add(cornerChar + repeat("-", tableWidth-2) + cornerChar);
        table.add(header);

        // Create rows
        for (String[] row : rows){
            rowString = "|";
            for (int i = 0; i < row.length; i++){
                rowString += addPadding(row[i], columnWidths[i]+2) + "|";
            }
            table.add(getDevider(columnWidths));
            table.add(rowString);
        }
        table.add(cornerChar + repeat("-", tableWidth-2) + cornerChar);

        return table.toArray(new String[0]);
    }
}
