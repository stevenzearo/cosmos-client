package ui;

import javax.swing.*;
import javax.swing.text.Document;

/**
 * @author steve
 */
public class SQLTextArea extends JTextArea {
    public SQLTextArea(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
    }
}
