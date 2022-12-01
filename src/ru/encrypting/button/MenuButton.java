package ru.encrypting.button;

import javax.swing.*;

public class MenuButton extends JButton
{
    public MenuButton(String text)
    {
        super(text);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }

}
