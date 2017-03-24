package LineCount.GUI;

import javax.swing.*;
import java.awt.*;

interface BoxHelp {

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
