package LineCount.GUI.Elements;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * A filepicker for selecting a number of files from a list of files
 */
public class FilePicker extends JPanel implements ListSelectionListener {
    private JList removedList;
    private JLabel removedTitle = new JLabel("Files");
    private JList addedList;
    private JLabel addedTitle = new JLabel("Added files");
    private DefaultListModel removedListModel;
    private DefaultListModel addedListModel;

    private JButton addButton;
    private JButton removeButton;

    /**
     * Creates an empty FilePicker.
     */
     public FilePicker(){
         this.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();

         removedListModel = new DefaultListModel();

         Dimension listSize = new Dimension(400, 300);
         // Create the removedList and put it in a scroll pane
         removedList = new JList(removedListModel);
         removedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         removedList.setSelectedIndex(-1);
         removedList.setLayoutOrientation(JList.VERTICAL);
         removedList.addListSelectionListener(this);
         JScrollPane removedScrollPane = new JScrollPane(removedList);
         removedScrollPane.setPreferredSize(listSize);


         // Create the addedList
         // Create the removedList and put it in a scroll pane
         addedListModel = new DefaultListModel();
         addedList = new JList(addedListModel);
         addedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         addedList.setSelectedIndex(-1);
         addedList.setLayoutOrientation(JList.VERTICAL);
         addedList.addListSelectionListener(this);
         JScrollPane addedScrollPane = new JScrollPane(addedList);
         addedScrollPane.setPreferredSize(listSize);


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
         this.add(removedTitle, c);
         c.gridy = 1;
         this.add(removedScrollPane, c);

         // + and - buttons
         Box buttonBox = Box.createVerticalBox();
         buttonBox.add(Box.createVerticalGlue());
         buttonBox.add(addButton);
         buttonBox.add(Box.createRigidArea(new Dimension(0, 20)));
         buttonBox.add(removeButton);
         buttonBox.add(Box.createVerticalGlue());
         c.gridy = 0;
         c.gridx = 1;
         c.gridheight = 2;
         this.add(Box.createRigidArea(new Dimension(5, 0)));
         c.gridx = 2;
         this.add(buttonBox, c);
         c.gridx = 3;
         this.add(Box.createRigidArea(new Dimension(5, 0)));

        // added files
         c.gridheight = 1;
         c.gridx = 4;
         c.gridy = 0;
         this.add(addedTitle, c);
         c.gridy = 1;
         this.add(addedScrollPane, c);
    }

    /**
     * Add a file to selectable files
     * @param path to file
     */
    public void addPath(String path){
        removedListModel.addElement(path);
    }

    /**
     * Clear out both the selectable files list and the selected files list,
     * effectively resetting the FilePicker
     */
    public void emptyLists(){
        addedList.setSelectedIndex(-1);
        removedList.setSelectedIndex(-1);
        addedListModel.clear();
        removedListModel.clear();
    }

    /**
     * Get all paths the user has selected, uses a common root, to resolve the
     * relative paths
     * @param root root folder the files originate from
     * @return Array of file Paths
     */
    public Path[] getSelectedPaths(String root){
        Path[] selectedPaths = new Path[addedListModel.getSize()];
        for(int i = 0; i < addedListModel.getSize(); i++){
            Path absPath = Paths.get(root,(String) addedListModel.getElementAt(i));
            selectedPaths[i] = absPath;
        }
        return selectedPaths;
    }


    /**
     * Required method for ListSelectionListener, determines what to do when a change
     * occurs in the list
     * @param e
     */
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

    /**
     * Listens to the + button (the add button)
     */
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

    /**
     * Listens to the - button (the remove button)
     */
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


