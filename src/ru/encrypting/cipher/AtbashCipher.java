package ru.encrypting.cipher;

import ru.encrypting.common.LanguageInput;
import ru.encrypting.common.helper.ImageTransformatorHelper;
import ru.encrypting.label.ImageScalingLabel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AtbashCipher
{
    public static String encryptText(String message, LanguageInput languageInput)
    {
        String alphabet = languageInput.getLanguageAlphabet();
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i))))
            {
                int charPosition = alphabet.indexOf(message.charAt(i));

                char replaceVal = alphabet.charAt(alphabet.length() - 1 - charPosition);
                cipherText.append(replaceVal);
            }
            else
                cipherText.append(message.charAt(i));
        }
        return cipherText.toString();
    }

    public static String decryptText(String message, LanguageInput languageInput)
    {
        String alphabet = languageInput.getLanguageAlphabet();
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i))))
            {
                int charPosition = alphabet.indexOf(message.charAt(i));

                char replaceVal = alphabet.charAt(alphabet.length() - 1 - charPosition);
                cipherText.append(replaceVal);
            }
            else
                cipherText.append(message.charAt(i));
        }
        return cipherText.toString();
    }

    public static void encryptImage(ImageScalingLabel leftImage, ImageScalingLabel rightImage) throws IOException
    {
        BufferedImage bi = ImageTransformatorHelper.getBufferedImage(leftImage);
        BufferedImage copyImage = ImageTransformatorHelper.getBufferedImage(leftImage);

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int xCordinate = (bi.getWidth() - x - 1);
                int yCordinate = (bi.getWidth() - y - 1);

                if (xCordinate >= bi.getWidth())
                    xCordinate = xCordinate - bi.getWidth();
                if (yCordinate >= bi.getHeight())
                    yCordinate = yCordinate - bi.getHeight();
                int rgb = copyImage.getRGB(xCordinate, yCordinate);
                bi.setRGB(x, y, rgb);
            }
        }

        rightImage.setIcon(new ImageIcon(bi));
    }

    public static void decryptImage(ImageScalingLabel leftImage, ImageScalingLabel rightImage) throws IOException
    {
        BufferedImage bi = ImageTransformatorHelper.getBufferedImage(leftImage);
        BufferedImage copyImage = ImageTransformatorHelper.getBufferedImage(leftImage);

        for (int x = bi.getWidth() - 1; x >= 0; x--) {
            for (int y = bi.getHeight() - 1; y >= 0; y--) {
                int xCordinate = (bi.getWidth() - x - 1);
                int yCordinate = (bi.getWidth() - y - 1);

                if (xCordinate < 0)
                    xCordinate = bi.getWidth() + xCordinate;
                if (yCordinate < 0)
                    yCordinate = bi.getHeight() + yCordinate;
                int rgb = copyImage.getRGB(xCordinate, yCordinate);
                bi.setRGB(x, y, rgb);
            }
        }
        rightImage.setIcon(new ImageIcon(bi));
    }
}
