package ru.encrypting.icon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResizedIconFromResources extends ImageIcon
{
    public ResizedIconFromResources(String resourceImagePath, int width, int height)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(resourceImagePath);
            BufferedImage img = ImageIO.read(is);
//            BufferedImage img = ImageIO.read(new File(getClass().getResource(resourceImagePath).getPath()));

            setImage(new ImageIcon(
                    new ImageIcon(img).getImage().getScaledInstance(
                            width, height, Image.SCALE_DEFAULT))
                    .getImage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
