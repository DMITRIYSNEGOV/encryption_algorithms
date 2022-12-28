package ru.encrypting.cipher;

import ru.encrypting.common.LanguageInput;
import ru.encrypting.common.helper.ImageTransformatorHelper;
import ru.encrypting.label.ImageScalingLabel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VigenererCipher
{
    public static String encryptText(String message, LanguageInput languageInput, String key)
    {
        String alphabet = languageInput.getLanguageAlphabet();
        StringBuilder cipherText = new StringBuilder();
        final int keyLen = key.length();
        int countKey = 0;
        for (int i = 0, len = message.length(); i < len; i++)
        {
            if (alphabet.contains(String.valueOf(message.charAt(i))))
            {
                if (countKey >= keyLen)
                {
                    countKey = 0;
                }
                int charPosition = alphabet.indexOf(message.charAt(i));
                int shiftKey = alphabet.indexOf(key.charAt(countKey++));

                int keyVal = (shiftKey + charPosition) % alphabet.length();
                char replaceVal = alphabet.charAt(keyVal);
                cipherText.append(replaceVal);
            }
            else
                cipherText.append(message.charAt(i));
        }
        return cipherText.toString();
    }

    public static String decryptText(String message, LanguageInput languageInput, String key)
    {
        String alphabet = languageInput.getLanguageAlphabet();
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();

        final int keyLen = key.length();
        int countKey = 0;
        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i))))
            {
                if (countKey >= keyLen)
                {
                    countKey = 0;
                }
                int shiftKey = alphabet.indexOf(key.charAt(countKey++));

                int charPosition = alphabet.indexOf(message.charAt(i));
                int keyVal = (charPosition - shiftKey) % alphabet.length();
                if (keyVal < 0)
                    keyVal = alphabet.length() + keyVal;
                char replaceVal = alphabet.charAt(keyVal);
                cipherText.append(replaceVal);
            }
            else
                cipherText.append(message.charAt(i));
        }
        return cipherText.toString();
    }

    public static void encryptImage(ImageScalingLabel leftImage, ImageScalingLabel rightImage, int[] keys) throws IOException
    {
        BufferedImage bi = ImageTransformatorHelper.getBufferedImage(leftImage);
        BufferedImage copyImage = ImageTransformatorHelper.getBufferedImage(leftImage);

        int countKey = 0;
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                if (countKey >= keys.length)
                    countKey = 0;
                int shiftKey = keys[countKey++];

                int xCordinate = (x+shiftKey) ;

                if (xCordinate >= bi.getWidth())
                    xCordinate = xCordinate - bi.getWidth();
                int rgb = copyImage.getRGB(xCordinate, y);
                bi.setRGB(x, y, rgb);
            }
        }

        rightImage.setIcon(new ImageIcon(bi));
    }

    public static void decryptImage(ImageScalingLabel leftImage, ImageScalingLabel rightImage, int[] keys) throws IOException
    {
        BufferedImage bi = ImageTransformatorHelper.getBufferedImage(leftImage);
        BufferedImage copyImage = ImageTransformatorHelper.getBufferedImage(leftImage);

        int countKey = 4;

        for (int x = bi.getWidth() - 1; x >= 0; x--) {
            for (int y = bi.getHeight() - 1; y >= 0; y--) {
                if (countKey < 0)
                    countKey = 4;
                int shiftKey = keys[countKey--];

                int xCordinate = (x - shiftKey);

                if (xCordinate < 0)
                    xCordinate = bi.getWidth() + xCordinate;

                int rgb = copyImage.getRGB(xCordinate, y);
                bi.setRGB(x, y, rgb);
            }
        }

        rightImage.setIcon(new ImageIcon(bi));
    }
}
