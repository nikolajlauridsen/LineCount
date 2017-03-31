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
     * Generate a list in the markdown format
     * @param items String[] items to be added to the list
     * @return Markdown list as a string
     */
    static String generateList(String[] items){
        StringBuilder stringList = new StringBuilder();
        for(String item: items){
            stringList.append("* ").append(item).append("\n");
        }

        return stringList.toString();
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

        // Find the widest row in each column and assign it's width to that
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
        table.add(headerBuilder.toString());

        // Create header devider
        table.add(StringHelp.getDivider(columnWidths));

        // Create rows
        for (String[] row : rows){
            rowBuilder = new StringBuilder("|");
            for (int i = 0; i < row.length; i++){
                rowBuilder.append(addPadding(row[i], columnWidths[i] + 2)).append("|");
            }
            table.add(rowBuilder.toString());
        }

        return table.toArray(new String[0]);
    }

}
