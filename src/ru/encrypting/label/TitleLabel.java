package ru.encrypting.label;

import ru.encrypting.icon.ResizedIconFromResources;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel
{
    private final int ICON_WIDTH = 32;
    private final int ICON_HEIGHT = 32;

    public TitleLabel(String text, String resourceImagePath)
    {
        super(text);
        this.setFont(new Font("Arial Black", Font.BOLD, 20));
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setHorizontalAlignment(SwingConstants.CENTER);

        this.setIcon(new ResizedIconFromResources(resourceImagePath, ICON_WIDTH, ICON_HEIGHT));
    }
}
