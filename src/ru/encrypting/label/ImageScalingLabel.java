package ru.encrypting.label;

import ru.encrypting.icon.ResizedIconFromResources;

import javax.swing.*;

public class ImageScalingLabel extends JLabel
{
    public ImageScalingLabel(String resourceImagePath, int width, int height)
    {
            setIcon(new ResizedIconFromResources(resourceImagePath, width, height));
    }
}
