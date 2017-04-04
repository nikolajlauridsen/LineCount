package LineCount.GUI;

import javax.swing.*;
import java.awt.*;

public interface BoxHelp {

    /**
     * Pull a component to the left side
     * @param comp Component to pull left
     * @param padding Space between the left edge and component
     * @return Box filling all available space with the component pulled to the left side
     */
    static Component leftJustify(Component comp, int padding){
        Box box = Box.createHorizontalBox();
        box.add(Box.createRigidArea(new Dimension(padding, 0)));
        box.add(comp);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    /**
     * Align a component to the center of the parent
     * @param comp component to allow
     * @return Box containign horizontal glue and the component
     */
    static Component centerJustify(Component comp){
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(comp);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    /**
     * Pad a gui component horizontally, applies padding to both sides
     * @param comp Component to be padded
     * @param padding Width of the padding
     * @return Box with component and padding
     */
    static Component padX(Component comp, int padding){
        Box box = Box.createHorizontalBox();
        box.add(Box.createRigidArea(new Dimension(padding, 0)));
        box.add(comp);
        box.add(Box.createRigidArea(new Dimension(padding, 0)));
        return box;
    }

}
