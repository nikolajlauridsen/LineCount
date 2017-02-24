package LineCount.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilePicker extends JPanel implements ListSelectionListener {
    private JList removedList;
    private JList addedList;
    private DefaultListModel removedListModel;
    private DefaultListModel addedListModel;

    private JButton addButton;
    private JButton removeButton;

     FilePicker(){
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
        JScrollPane removedScrollPane = new JScrollPane(removedList);


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
        JScrollPane addedScrollPane = new JScrollPane(addedList);


        String addString = "+";
        addButton = new JButton(addString);
        addButton.setActionCommand(addString);
        addButton.setEnabled(false);
        addButton.addActionListener(new AddListener());

        String removeString = "-";
        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.setEnabled(false);
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

    void addPath(String path){
        removedListModel.addElement(path);
    }

    void emptyLists(){
        addedList.setSelectedIndex(-1);
        removedList.setSelectedIndex(-1);
        addedListModel.clear();
        removedListModel.clear();
    }

    public Path[] getSelectedPaths(String root){
        Path[] selectedPaths = new Path[addedListModel.getSize()];
        for(int i = 0; i < addedListModel.getSize(); i++){
            Path absPath = Paths.get(root,(String) addedListModel.getElementAt(i));
            selectedPaths[i] = absPath;
        }
        return selectedPaths;
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
            if (addedList.getSelectedIndex() < 0){
                addedList.setSelectedIndex(0);
            }
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
            if (removedList.getSelectedIndex() < 0){
                removedList.setSelectedIndex(0);
            }
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


