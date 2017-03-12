package LineCount.Utils;

import java.util.ArrayList;

import static LineCount.Utils.StringHelp.addPadding;
import static LineCount.Utils.StringHelp.getDevider;
import static LineCount.Utils.StringHelp.repeat;

public interface TxtHelp {

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
        String header, rowString;

        // Create header
        header = "|";
        for (int i = 0; i < columns.length; i++){
            header += addPadding(columns[i], columnWidths[i]+2) + "|";
        }
        int tableWidth = header.length();
        table.add("*" + repeat("-", tableWidth-2) + "*");
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
        table.add("*" + repeat("-", tableWidth-2) + "*");

        return table.toArray(new String[0]);
    }

}
