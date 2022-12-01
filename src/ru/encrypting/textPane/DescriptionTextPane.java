package ru.encrypting.textPane;

import javax.swing.*;

public class DescriptionTextPane extends JTextPane
{
    public DescriptionTextPane(String text)
    {
        this.setText(text);
        this.setEditable(false);
    }
}
