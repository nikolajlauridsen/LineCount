package LineCount.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProjectSelector extends JPanel implements ListSelectionListener {
    private JList folderList;
    private JList projectList;
    private DefaultListModel folderListModel;
    private DefaultListModel projectListModel;
    private JScrollPane listScrollPane;
    private JScrollPane projectScrollPane;

    private final String addString = "+";
    private final String removeString = "-";
    private JButton addButton;
    private JButton removeButton;

    public ProjectSelector(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        folderListModel = new DefaultListModel();

        Dimension listSize = new Dimension(100, 100);
        // Create the folderList and put it in a scroll pane
        folderList = new JList(folderListModel);
        folderList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        folderList.setSelectedIndex(-1);
        folderList.setLayoutOrientation(JList.VERTICAL);
        folderList.addListSelectionListener(this);
        folderList.setSize(listSize);
        folderList.setMaximumSize(listSize);
        folderList.setMinimumSize(listSize);
        listScrollPane = new JScrollPane(folderList);


        // Create the projectList
        // Create the folderList and put it in a scroll pane
        projectListModel = new DefaultListModel();
        projectList = new JList(projectListModel);
        projectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        projectList.setSelectedIndex(-1);
        projectList.setLayoutOrientation(JList.VERTICAL);
        projectList.addListSelectionListener(this);
        projectList.setSize(listSize);
        projectList.setMaximumSize(listSize);
        projectList.setMinimumSize(listSize);
        projectScrollPane = new JScrollPane(projectList);


        addButton = new JButton(addString);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddListener());

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        // Add the elements to the panel
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        this.add(listScrollPane, c);

        c.gridheight = 1;
        c.gridx = 2;
        this.add(addButton, c);
        c.gridy = 1;
        this.add(removeButton, c);

        c.gridheight = 2;
        c.gridx = 3;
        c.gridy = 0;
        this.add(projectScrollPane, c);


    }

    public void addPath(String path){
        folderListModel.addElement(path);
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (folderListModel.getSize() < 1)  {
                //No selection, disable fire button.
                addButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                addButton.setEnabled(true);
            }

            if (projectListModel.getSize() < 1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    class AddListener implements ActionListener{
        // Required by ActionListener
        public void actionPerformed(ActionEvent e){
            int selectedIndex = folderList.getSelectedIndex();

            projectListModel.addElement(folderList.getSelectedValue());
            folderListModel.remove(selectedIndex);



            int folderSize = folderListModel.getSize();

            if(folderSize == 0){ // If the folder is empty disable add button
                addButton.setEnabled(false);
            } else {
                if(selectedIndex == folderListModel.getSize()){ // Index out of bounds, move it down
                    selectedIndex--;
                }
                folderList.setSelectedIndex(selectedIndex);
                folderList.ensureIndexIsVisible(selectedIndex);
            }

        }
    }

    class RemoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int index = projectList.getSelectedIndex();

            folderListModel.addElement(projectList.getSelectedValue());
            projectListModel.remove(index);

            int projectSize = projectListModel.getSize();
            if(projectSize < 1){
                removeButton.setEnabled(false);
            } else {
                if(index == projectListModel.getSize()){
                    index--;
                }
                projectList.setSelectedIndex(index);
                projectList.ensureIndexIsVisible(index);
            }

        }
    }
}


