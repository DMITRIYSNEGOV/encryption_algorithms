package ru.encrypting.cipher;

import ru.encrypting.common.LanguageInput;
import ru.encrypting.common.helper.ImageTransformatorHelper;
import ru.encrypting.label.ImageScalingLabel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaesarCipher
{
    public static String encryptText(String message, LanguageInput languageInput, int shiftKey)
    {
        String alphabet = languageInput.getLanguageAlphabet();
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i))))
            {
                int charPosition = alphabet.indexOf(message.charAt(i));
                int keyVal = (shiftKey + charPosition) % alphabet.length();
                char replaceVal = alphabet.charAt(keyVal);
                cipherText.append(replaceVal);
            }
            else
                cipherText.append(message.charAt(i));
        }
        return cipherText.toString();
    }

    public static String decryptText(String message, LanguageInput languageInput, int shiftKey)
    {
        String alphabet = languageInput.getLanguageAlphabet();
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i))))
            {
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

    public static void encryptImage(ImageScalingLabel leftImage, ImageScalingLabel rightImage, int shiftKey) throws IOException
    {
        BufferedImage bi = ImageTransformatorHelper.getBufferedImage(leftImage);
        BufferedImage copyImage = ImageTransformatorHelper.getBufferedImage(leftImage);

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int xCordinate = (x+shiftKey) ;
                int yCordinate = (y+shiftKey);

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

    public static void decryptImage(ImageScalingLabel leftImage, ImageScalingLabel rightImage, int shiftKey) throws IOException
    {
        BufferedImage bi = ImageTransformatorHelper.getBufferedImage(leftImage);
        BufferedImage copyImage = ImageTransformatorHelper.getBufferedImage(leftImage);

        for (int x = bi.getWidth() - 1; x >= 0; x--) {
            for (int y = bi.getHeight() - 1; y >= 0; y--) {
                int xCordinate = (x - shiftKey);
                int yCordinate = (y - shiftKey);

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
