package org.example.Helper;

import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Timer;
import java.util.TimerTask;

public class Clip {

    /**
     * @param toCopy 
     * @param pushnotification
     */
    public void CopyToClipboard(String toCopy, Text pushnotification){
        StringSelection stringSelection = new StringSelection(toCopy);
        if(toCopy.equals("")) {
            pushnotification.setText("Nothing to copy!");
            pushnotification.getStyleClass().remove("success");
            pushnotification.getStyleClass().add("error");
            pushnotification.setVisible(true);
        } else {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection,null);
            pushnotification.setText("Copied to clipboard!");
            pushnotification.getStyleClass().remove("error");
            pushnotification.getStyleClass().add("success");
            pushnotification.setVisible(true);
        }

        Timer timer = new Timer();
        var task = new TimerTask() {
            public void run() {
                pushnotification.setVisible(false);
            }
        };
        timer.schedule(task,2000);
    }
}
