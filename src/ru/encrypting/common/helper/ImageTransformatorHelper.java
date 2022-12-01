package ru.encrypting.common.helper;

import ru.encrypting.label.ImageScalingLabel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageTransformatorHelper
{
    public static BufferedImage getBufferedImage(ImageScalingLabel leftImage)
    {
        BufferedImage bi = new BufferedImage(
                leftImage.getIcon().getIconWidth(),
                leftImage.getIcon().getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        leftImage.getIcon().paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }
}
