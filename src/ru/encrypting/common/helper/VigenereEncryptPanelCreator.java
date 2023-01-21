package ru.encrypting.common.helper;

import ru.encrypting.cipher.VigenererCipher;
import ru.encrypting.common.CryptoType;
import ru.encrypting.common.LanguageInput;
import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static ru.encrypting.common.ResourcesPath.*;
import static ru.encrypting.common.StringConstants.*;
import static ru.encrypting.common.helper.ElementsCreatorHelper.*;

public class VigenereEncryptPanelCreator implements EncryptPanelCreator
{
    private static final EncryptPanelCreator INSTANCE = new VigenereEncryptPanelCreator();

    public static EncryptPanelCreator getInstance()
    {
        return INSTANCE;
    }

    private VigenereEncryptPanelCreator()
    {}

    @Override
    public void initPanel(JPanel contentPanel, GroupLayout groupLayoutContentPanel)
    {
        JLabel title = new TitleLabel(VIGENERE_TITLE, VIGENERE_TITLE_PATH);
        title.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 40));

        JTextPane description = new DescriptionTextPane(
                VIGENERE_DESCRIPTION,
                new Dimension(700, 100));

        ImageScalingLabel vigenereEncryptDescription = new ImageScalingLabel(VIGENERE_ENCRYPT_DESCRIPTION_PATH, 650, 542);

        // пример шифрования текста
        // выбор алфавита
        JLabel alphabetHint = createLabelAlphabetHint();
        JComboBox<LanguageInput> alphabet = createAlphabetComboBox();

        // выбор типа операции
        JLabel operationTypeHint = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationType = createOperationTypeComboBox();

        // выбор сдвига
        JLabel keyHint = createKeyHintLabel();
        JTextArea keyTextField = createBeforeTextField("ключ", contentPanel);

        // поля для ввода текста
        JTextArea beforeTextField = createBeforeTextField("текст на русском", contentPanel);
        JButton transformTextButton = createTransformButton();
        JTextArea afterTextField = createAfterTextField(contentPanel);

        transformTextButton.addActionListener(e ->
        {
            LanguageInput languageInput = (LanguageInput) Objects.requireNonNull(alphabet.getSelectedItem());
                String transformedText = transformText(operationType, beforeTextField, languageInput, keyTextField.getText());
                afterTextField.setText(transformedText);
        });

        // пример шифрования изображения 1
        // выбор типа операции
        JLabel operationTypeImageHint = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationTypeImage = createOperationTypeComboBox();

        JLabel KeyImageHint1 = createKeyHintLabel();
        KeyImageHint1.setPreferredSize(new Dimension(500, 500));

        JComboBox<Integer> keyImage1 = createShiftComboBox(299);
        JComboBox<Integer> keyImage2 = createShiftComboBox(299);
        JComboBox<Integer> keyImage3 = createShiftComboBox(299);
        JComboBox<Integer> keyImage4 = createShiftComboBox(299);
        JComboBox<Integer> keyImage5 = createShiftComboBox(299);

        keyImage1.setMaximumSize(new Dimension(10, keyImage1.getPreferredSize().height));
        keyImage2.setMaximumSize(new Dimension(10, keyImage2.getPreferredSize().height));
        keyImage3.setMaximumSize(new Dimension(10, keyImage3.getPreferredSize().height));
        keyImage4.setMaximumSize(new Dimension(10, keyImage4.getPreferredSize().height));
        keyImage5.setMaximumSize(new Dimension(10, keyImage5.getPreferredSize().height));

        ImageScalingLabel leftImage = createImageScalingLabel(HUMAN_EXAMPLE);
        JButton transformImageButton = createTransformButton();

        JButton swapImageButton = new JButton();
        swapImageButton.setText(DOUBLE_ARROW);

        ImageScalingLabel rightImage = createImageScalingLabel(EMPTY_IMAGE_PATH);

        swapImageButton.addActionListener(e ->
        {
            BufferedImage temp =  ImageTransformatorHelper.getBufferedImage(leftImage);
            BufferedImage rightBufferedImage =  ImageTransformatorHelper.getBufferedImage(rightImage);
            leftImage.setIcon(new ImageIcon(rightBufferedImage));
            rightImage.setIcon(new ImageIcon(temp));
        });

        transformImageButton.addActionListener(e ->
        {
            try
            {
                CryptoType cryptoType = (CryptoType) operationTypeImage.getSelectedItem();

                int[] keys = new int[] {
                        (int) Optional.ofNullable(keyImage1.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage2.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage3.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage4.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage5.getSelectedItem()).orElse(0)
                };
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        VigenererCipher.encryptImage(leftImage, rightImage, keys);
                        break;
                    }
                    case DECRYPT:
                    {
                        VigenererCipher.decryptImage(leftImage, rightImage, keys);
                        break;
                    }
                    default: {
                        throw new NullPointerException("Неизвестное значение");
                    }
                }
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        // пример шифрования изображения 2
        // выбор типа операции
        JLabel operationTypeImageHint2 = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationTypeImage2 = createOperationTypeComboBox();

        JLabel KeyImageHint2 = createKeyHintLabel();
        KeyImageHint2.setPreferredSize(new Dimension(500, 500));

        JComboBox<Integer> keyImage1_2 = createShiftComboBox(299);
        JComboBox<Integer> keyImage2_2 = createShiftComboBox(299);
        JComboBox<Integer> keyImage3_2 = createShiftComboBox(299);
        JComboBox<Integer> keyImage4_2 = createShiftComboBox(299);
        JComboBox<Integer> keyImage5_2 = createShiftComboBox(299);

        keyImage1_2.setMaximumSize(new Dimension(10, keyImage1_2.getPreferredSize().height));
        keyImage2_2.setMaximumSize(new Dimension(10, keyImage2_2.getPreferredSize().height));
        keyImage3_2.setMaximumSize(new Dimension(10, keyImage3_2.getPreferredSize().height));
        keyImage4_2.setMaximumSize(new Dimension(10, keyImage4_2.getPreferredSize().height));
        keyImage5_2.setMaximumSize(new Dimension(10, keyImage5_2.getPreferredSize().height));

        ImageScalingLabel leftImage2 = createImageScalingLabel(VERTICAL_LINE_EXAMPLE_PATH);
        JButton transformImageButton2 = createTransformButton();

        JButton swapImageButton2 = new JButton();
        swapImageButton2.setText(DOUBLE_ARROW);

        ImageScalingLabel rightImage2 = createImageScalingLabel(EMPTY_IMAGE_PATH);

        swapImageButton2.addActionListener(e ->
        {
            BufferedImage temp =  ImageTransformatorHelper.getBufferedImage(leftImage2);
            BufferedImage rightBufferedImage =  ImageTransformatorHelper.getBufferedImage(rightImage2);
            leftImage2.setIcon(new ImageIcon(rightBufferedImage));
            rightImage2.setIcon(new ImageIcon(temp));
        });

        transformImageButton2.addActionListener(e ->
        {
            try
            {
                CryptoType cryptoType = (CryptoType) operationTypeImage2.getSelectedItem();

                int[] keys = new int[] {
                        (int) Optional.ofNullable(keyImage1_2.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage2_2.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage3_2.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage4_2.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage5_2.getSelectedItem()).orElse(0)
                };
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        VigenererCipher.encryptImage(leftImage2, rightImage2, keys);
                        break;
                    }
                    case DECRYPT:
                    {
                        VigenererCipher.decryptImage(leftImage2, rightImage2, keys);
                        break;
                    }
                    default: {
                        throw new NullPointerException("Неизвестное значение");
                    }
                }
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });


        // пример шифрования собственных изображений
        // выбор типа операции
        JLabel operationTypeImageHint3 = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationTypeImage3 = createOperationTypeComboBox();

        JLabel KeyImageHint3 = createKeyHintLabel();
        KeyImageHint3.setPreferredSize(new Dimension(500, 500));

        JComboBox<Integer> keyImage1_3 = createShiftComboBox(299);
        JComboBox<Integer> keyImage2_3 = createShiftComboBox(299);
        JComboBox<Integer> keyImage3_3 = createShiftComboBox(299);
        JComboBox<Integer> keyImage4_3 = createShiftComboBox(299);
        JComboBox<Integer> keyImage5_3 = createShiftComboBox(299);

        keyImage1_3.setMaximumSize(new Dimension(10, keyImage1_3.getPreferredSize().height));
        keyImage2_3.setMaximumSize(new Dimension(10, keyImage2_3.getPreferredSize().height));
        keyImage3_3.setMaximumSize(new Dimension(10, keyImage3_3.getPreferredSize().height));
        keyImage4_3.setMaximumSize(new Dimension(10, keyImage4_3.getPreferredSize().height));
        keyImage5_3.setMaximumSize(new Dimension(10, keyImage5_3.getPreferredSize().height));

        ImageScalingLabel leftImage3 = createImageScalingLabel(VERTICAL_LINE_EXAMPLE_PATH);
        JButton transformImageButton3 = createTransformButton();

        JButton swapImageButton3 = new JButton();
        swapImageButton3.setText(DOUBLE_ARROW);

        JButton downloadImage = new JButton();
        downloadImage.setText(LOAD_IMAGE_BUTTON_TEXT);

        ImageScalingLabel rightImage3 = createImageScalingLabel(EMPTY_IMAGE_PATH);

        swapImageButton3.addActionListener(e ->
        {
            BufferedImage temp =  ImageTransformatorHelper.getBufferedImage(leftImage3);
            BufferedImage rightBufferedImage =  ImageTransformatorHelper.getBufferedImage(rightImage3);
            leftImage3.setIcon(new ImageIcon(rightBufferedImage));
            rightImage3.setIcon(new ImageIcon(temp));
        });

        transformImageButton3.addActionListener(e ->
        {
            try
            {
                CryptoType cryptoType = (CryptoType) operationTypeImage3.getSelectedItem();

                int[] keys = new int[] {
                        (int) Optional.ofNullable(keyImage1_3.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage2_3.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage3_3.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage4_3.getSelectedItem()).orElse(0),
                        (int) Optional.ofNullable(keyImage5_3.getSelectedItem()).orElse(0)
                };
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        VigenererCipher.encryptImage(leftImage3, rightImage3, keys);
                        break;
                    }
                    case DECRYPT:
                    {
                        VigenererCipher.decryptImage(leftImage3, rightImage3, keys);
                        break;
                    }
                    default: {
                        throw new NullPointerException("Неизвестное значение");
                    }
                }
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        downloadImage.addActionListener(e ->
        {
            JFileChooser jFileChooser = new JFileChooser();
            if(jFileChooser.showOpenDialog(contentPanel) == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    BufferedImage img = ImageIO.read(
                            new File(jFileChooser.
                                    getSelectedFile().
                                    toString())
                    );
                    leftImage3.setIcon(new ImageIcon(
                            new ImageIcon(img).getImage().getScaledInstance(
                                    300, 300, Image.SCALE_DEFAULT)));
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });

        groupLayoutContentPanel.setHorizontalGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(title)
                        .addComponent(description)
                        .addComponent(vigenereEncryptDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(alphabetHint)
                        .addComponent(operationTypeHint)
                        .addComponent(keyHint)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(alphabet)
                        .addComponent(operationType)
                        .addComponent(keyTextField)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(beforeTextField)
                        .addComponent(transformTextButton)
                        .addComponent(afterTextField)
                )

                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImageHint)
                        .addComponent(KeyImageHint1)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage)
                        .addComponent(keyImage1)
                        .addComponent(keyImage2)
                        .addComponent(keyImage3)
                        .addComponent(keyImage4)
                        .addComponent(keyImage5)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(leftImage)
                        .addGroup(groupLayoutContentPanel.createParallelGroup()
                                .addComponent(swapImageButton)
                                .addComponent(transformImageButton)
                        )
                        .addComponent(rightImage))






                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImageHint2)
                        .addComponent(KeyImageHint2)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage2)
                        .addComponent(keyImage1_2)
                        .addComponent(keyImage2_2)
                        .addComponent(keyImage3_2)
                        .addComponent(keyImage4_2)
                        .addComponent(keyImage5_2)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(leftImage2)
                        .addGroup(groupLayoutContentPanel.createParallelGroup()
                                .addComponent(swapImageButton2)
                                .addComponent(transformImageButton2)
                        )
                        .addComponent(rightImage2))






                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImageHint3)
                        .addComponent(KeyImageHint3)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage3)
                        .addComponent(keyImage1_3)
                        .addComponent(keyImage2_3)
                        .addComponent(keyImage3_3)
                        .addComponent(keyImage4_3)
                        .addComponent(keyImage5_3)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(downloadImage)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(leftImage3)
                        .addGroup(groupLayoutContentPanel.createParallelGroup()
                                .addComponent(swapImageButton3)
                                .addComponent(transformImageButton3)
                        )
                        .addComponent(rightImage3))
        );

        groupLayoutContentPanel.setVerticalGroup(groupLayoutContentPanel.createSequentialGroup()
                .addComponent(title)
                .addComponent(description)
                .addComponent(vigenereEncryptDescription)
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(alphabetHint)
                        .addComponent(operationTypeHint)
                        .addComponent(keyHint)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(alphabet)
                        .addComponent(operationType)
                        .addComponent(keyTextField)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(beforeTextField)
                        .addComponent(transformTextButton)
                        .addComponent(afterTextField)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImageHint)
                        .addComponent(KeyImageHint1)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage)
                        .addComponent(keyImage1)
                        .addComponent(keyImage2)
                        .addComponent(keyImage3)
                        .addComponent(keyImage4)
                        .addComponent(keyImage5)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(leftImage)
                        .addGroup(groupLayoutContentPanel.createSequentialGroup()
                                .addComponent(swapImageButton)
                                .addComponent(transformImageButton)
                        )
                        .addComponent(rightImage))

                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImageHint2)
                        .addComponent(KeyImageHint2)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage2)
                        .addComponent(keyImage1_2)
                        .addComponent(keyImage2_2)
                        .addComponent(keyImage3_2)
                        .addComponent(keyImage4_2)
                        .addComponent(keyImage5_2)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(leftImage2)
                        .addGroup(groupLayoutContentPanel.createSequentialGroup()
                                .addComponent(swapImageButton2)
                                .addComponent(transformImageButton2)
                        )
                        .addComponent(rightImage2))
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImageHint3)
                        .addComponent(KeyImageHint3)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage3)
                        .addComponent(keyImage1_3)
                        .addComponent(keyImage2_3)
                        .addComponent(keyImage3_3)
                        .addComponent(keyImage4_3)
                        .addComponent(keyImage5_3)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(downloadImage)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(leftImage3)
                        .addGroup(groupLayoutContentPanel.createSequentialGroup()
                                .addComponent(swapImageButton3)
                                .addComponent(transformImageButton3)
                        )
                        .addComponent(rightImage3))
        );
    }

    private static JLabel createKeyHintLabel()
    {
        JLabel keyHint = new JLabel("Ключ");
        keyHint.setMaximumSize(new Dimension(200, 40));
        keyHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return keyHint;
    }

    private static String transformText(JComboBox<CryptoType> operationType, JTextArea beforeTextField, LanguageInput languageInput, String key)
    {
        CryptoType cryptoType = (CryptoType) operationType.getSelectedItem();
        switch (Objects.requireNonNull(cryptoType))
        {   case ENCRYPT: return VigenererCipher.encryptText(beforeTextField.getText(), languageInput, key);
            case DECRYPT: return VigenererCipher.decryptText(beforeTextField.getText(), languageInput, key);
            default: return null;
        }
    }
}
