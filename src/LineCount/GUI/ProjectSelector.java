package LineCount.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProjectSelector extends JPanel implements ListSelectionListener {
    private JList removedList;
    private JList addedList;
    private DefaultListModel removedListModel;
    private DefaultListModel addedListModel;
    private JScrollPane removedScrollPane;
    private JScrollPane addedScrollPane;

    private final String addString = "+";
    private final String removeString = "-";
    private JButton addButton;
    private JButton removeButton;

    public ProjectSelector(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        removedListModel = new DefaultListModel();

        Dimension listSize = new Dimension(100, 100);
        // Create the removedList and put it in a scroll pane
        removedList = new JList(removedListModel);
        removedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        removedList.setSelectedIndex(-1);
        removedList.setLayoutOrientation(JList.VERTICAL);
        removedList.addListSelectionListener(this);
        removedList.setSize(listSize);
        removedList.setMaximumSize(listSize);
        removedList.setMinimumSize(listSize);
        removedScrollPane = new JScrollPane(removedList);


        // Create the addedList
        // Create the removedList and put it in a scroll pane
        addedListModel = new DefaultListModel();
        addedList = new JList(addedListModel);
        addedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addedList.setSelectedIndex(-1);
        addedList.setLayoutOrientation(JList.VERTICAL);
        addedList.addListSelectionListener(this);
        addedList.setSize(listSize);
        addedList.setMaximumSize(listSize);
        addedList.setMinimumSize(listSize);
        addedScrollPane = new JScrollPane(addedList);


        addButton = new JButton(addString);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddListener());

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        // Add the elements to the panel
        // removed files
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        this.add(removedScrollPane, c);

        // + and - buttons
        c.gridheight = 1;
        c.gridx = 2;
        this.add(addButton, c);
        c.gridy = 1;
        this.add(removeButton, c);

        // added files
        c.gridheight = 2;
        c.gridx = 3;
        c.gridy = 0;
        this.add(addedScrollPane, c);


    }

    public void addPath(String path){
        removedListModel.addElement(path);
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (removedListModel.getSize() < 1)  {
                //No selection, disable fire button.
                addButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                addButton.setEnabled(true);
            }

            if (addedListModel.getSize() < 1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    class AddListener implements ActionListener{
        // Required by ActionListener
        public void actionPerformed(ActionEvent e){
            int selectedIndex = removedList.getSelectedIndex();

            addedListModel.addElement(removedList.getSelectedValue());
            removedListModel.remove(selectedIndex);



            int folderSize = removedListModel.getSize();

            if(folderSize == 0){ // If the folder is empty disable add button
                addButton.setEnabled(false);
            } else {
                if(selectedIndex == removedListModel.getSize()){ // Index out of bounds, move it down
                    selectedIndex--;
                }
                removedList.setSelectedIndex(selectedIndex);
                removedList.ensureIndexIsVisible(selectedIndex);
            }

        }
    }

    class RemoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int index = addedList.getSelectedIndex();

            removedListModel.addElement(addedList.getSelectedValue());
            addedListModel.remove(index);

            int projectSize = addedListModel.getSize();
            if(projectSize < 1){
                removeButton.setEnabled(false);
            } else {
                if(index == addedListModel.getSize()){
                    index--;
                }
                addedList.setSelectedIndex(index);
                addedList.ensureIndexIsVisible(index);
            }

        }
    }
}


