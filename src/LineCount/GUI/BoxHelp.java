package LineCount.GUI;

import javax.swing.*;
import java.awt.*;

public interface BoxHelp {

    static Component leftJustify(Component panel, int padding){
        Box box = Box.createHorizontalBox();
        box.add(Box.createRigidArea(new Dimension(padding, 0)));
        box.add(panel);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    static Component padX(Component comp, int padding){
        Box box = Box.createHorizontalBox();
        box.add(Box.createRigidArea(new Dimension(padding, 0)));
        box.add(comp);
        box.add(Box.createRigidArea(new Dimension(padding, 0)));
        return box;
    }
}
