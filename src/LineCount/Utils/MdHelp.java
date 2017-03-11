package LineCount.Utils;
import java.util.ArrayList;

import static LineCount.Utils.StringHelp.*;

/**
 * Interface containing static methods designed to easy the workload when creating
 * markdown files
 */
public interface MdHelp {

    /**
     * Turn a string into a markdown h1 heading
     * @param string String to be a header
     * @return Header string
     */
    static String h1(String string){
        return "# " + string;
    }

    /**
     * Turn a string into a markdown h2 heading
     * @param string String to be a header
     * @return Header string
     */
    static String h2(String string){
        return "## " + string;
    }

    /**
     * Turn a string into a markdown h3 heading
     * @param string String to be a header
     * @return Header string
     */
    static String h3(String string){
        return "### " + string;
    }

    /**
     * Turn a string into a markdown h4 heading
     * @param string String to be a header
     * @return Header string
     */
    static String h4(String string){
        return "#### " + string;
    }

    /**
     * Generate a list in the markdown format
     * @param items String[] items to be added to the list
     * @return Markdown list as a string
     */
    static String generateList(String[] items){
        String stringList = "";
        for(String item: items){
            stringList += "* " + item + "\n";
        }

        return stringList;
    }

    /**
     * Generate a table in the markdown
     * @param rows Two dimensional string array of the contents
     *             (See it as a array of rows, which happens to be an array as well)
     * @param columns String[] containing the column titles, should be as long as the arrays of rows
     *                (the column row should be same size as the rest of the rows, suprise)
     * @return Markdown table as a string array
     */
    static String[] generateTable(String[][] rows, String[] columns){
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
        String header, devider, rowString;

        // Create header
        header = "|";
        for (int i = 0; i < columns.length; i++){
            header += addPadding(columns[i], columnWidths[i]+2) + "|";
        }
        table.add(header);

        // Create header devider
        devider = "|";
        for(int i = 0; i < columns.length; i++){
            for(int n = 0; n < columnWidths[i]+2; n++){
                devider += "-";
            }
            devider += "|";
        }
        table.add(devider);

        // Create rows
        for (String[] row : rows){
            rowString = "|";
            for (int i = 0; i < row.length; i++){
                rowString += addPadding(row[i], columnWidths[i]+2) + "|";
            }
            table.add(rowString);
        }

        return table.toArray(new String[0]);
    }

}
