package lab;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class CommonFrame extends Frame {
    public CommonFrame(String title) {
        super(title);

        setLayout(null);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                windowClose();
            }
        });
    }

    protected void windowClose() {
        dispose();
    }
}
