package ru.encrypting.textPane;

import javax.swing.*;
import java.awt.*;

public class DescriptionTextPane extends JTextPane
{
    public DescriptionTextPane(String text, Dimension dimension)
    {
        this.setText(text);
        this.setEditable(false);
        this.setMaximumSize(dimension);
    }
}
