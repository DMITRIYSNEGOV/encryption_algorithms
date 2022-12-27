package ru.encrypting.common.helper;

import ru.encrypting.cipher.CaesarCipher;
import ru.encrypting.common.CryptoType;
import ru.encrypting.common.LanguageInput;
import ru.encrypting.icon.ResizedIconFromResources;
import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

import static javax.swing.JOptionPane.showMessageDialog;
import static ru.encrypting.common.ResourcesPath.*;

public class CaesarEncryptPanelCreator
{
    public static void initCaesarEncryptPanel(JPanel contentPanel, GroupLayout groupLayoutContentPanel)
    {
        JLabel title = new TitleLabel("Шифр Цезаря", CAESAR_TITLE_PATH);
        title.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 40));

        JTextPane description = new DescriptionTextPane(
                "   Шифр Цeзаря (шифр сдвига, код Цезаря) – такой простой вид шифрования текста, при котором все символы в тексте заменяются символами, сдвинутыми по алфавиту на правее или левее на постоянное количество позиций. Например, при сдвиге на 1 буква А заменяется на Б, Б на В и т.д. Вы можете как зашифровать текст данным способом, так и выполнить дешифровку.",
                new Dimension(700, 100));

        ImageScalingLabel caesarEncryptDescription = new ImageScalingLabel(CAESAR_ENCRYPT_DESCRIPTION_PATH, 650, 274);

        // пример шифрования текста
        // выбор алфавита
        JLabel alphabetHint = createLabelAlphabetHint();
        JComboBox<LanguageInput> alphabet = createAlphabetComboBox();

        // выбор типа операции
        JLabel operationTypeHint = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationType = createOperationTypeComboBox();

        // выбор сдвига
        JLabel shiftHint = createShiftHintLabel();
        JComboBox<Integer> shift = createShiftComboBox(32);

        // поля для ввода текста
        JTextArea beforeTextField = createBeforeTextField("текст на русском", contentPanel);
        JButton transformTextButton = createTransformButton();
        JTextArea afterTextField = createAfterTextField(contentPanel);

        // пример шифрования изображения 1
        // выбор типа операции
        JLabel operationTypeImageHint = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationTypeImage = createOperationTypeComboBox();

        JLabel shiftImageHint = createShiftHintLabel();
        shiftImageHint.setPreferredSize(new Dimension(500, 500));
        JComboBox<Integer> shiftImage = createShiftComboBox(299);

        ImageScalingLabel leftImage = createImageScalingLabel(HUMAN_EXAMPLE);
        JButton transformImageButton = createTransformButton();

        JButton swapImageButton = new JButton();
        swapImageButton.setText("< - >");

        ImageScalingLabel rightImage = createImageScalingLabel(EMPTY_IMAGE_PATH);

        swapImageButton.addActionListener(e ->
        {
            BufferedImage temp =  ImageTransformatorHelper.getBufferedImage(leftImage);
            BufferedImage rightBufferedImage =  ImageTransformatorHelper.getBufferedImage(rightImage);
            leftImage.setIcon(new ImageIcon(rightBufferedImage));
            rightImage.setIcon(new ImageIcon(temp));
        });

        transformTextButton.addActionListener(e ->
        {
            LanguageInput languageInput = (LanguageInput) Objects.requireNonNull(alphabet.getSelectedItem());
            int offset = (int) Objects.requireNonNull(shift.getSelectedItem());
            if(offset > languageInput.getLanguageAlphabet().length())
            {
                showMessageDialog(null, "Сдвиг не должен превышать длину алфавита");
            }
            else
            {
                String transformedText = transformText(operationType, beforeTextField, languageInput, offset);
                afterTextField.setText(transformedText);
            }
        });


        transformImageButton.addActionListener(e ->
        {
            try
            {
                CryptoType cryptoType = (CryptoType) operationTypeImage.getSelectedItem();
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        CaesarCipher.encryptImage(leftImage, rightImage, (int) shiftImage.getSelectedItem());
                        break;
                    }
                    case DECRYPT:
                    {
                        CaesarCipher.decryptImage(leftImage, rightImage, (int) shiftImage.getSelectedItem());
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

        JLabel shiftImageHint2 = createShiftHintLabel();
        shiftImageHint2.setPreferredSize(new Dimension(500, 500));
        JComboBox<Integer> shiftImage2 = createShiftComboBox(299);

        ImageScalingLabel leftImage2 = createImageScalingLabel(VERTICAL_LINE_EXAMPLE_PATH);
        JButton transformImageButton2 = createTransformButton();

        JButton swapImageButton2 = new JButton();
        swapImageButton2.setText("< - >");

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
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        CaesarCipher.encryptImage(leftImage2, rightImage2, (int) shiftImage2.getSelectedItem());
                        break;
                    }
                    case DECRYPT:
                    {
                        CaesarCipher.decryptImage(leftImage2, rightImage2, (int) shiftImage2.getSelectedItem());
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




        // пример шифрования своих изображений
        // выбор типа операции
        JLabel operationTypeImageHint3 = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationTypeImage3 = createOperationTypeComboBox();

        JLabel shiftImageHint3 = createShiftHintLabel();
        shiftImageHint3.setPreferredSize(new Dimension(500, 500));
        JComboBox<Integer> shiftImage3 = createShiftComboBox(299);

        JButton downloadImage = new JButton();
        downloadImage.setText("Загрузить картинку");

        ImageScalingLabel leftImage3 = createImageScalingLabel(VERTICAL_LINE_EXAMPLE_PATH);
        JButton transformImageButton3 = createTransformButton();

        JButton swapImageButton3 = new JButton();
        swapImageButton3.setText("< - >");

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
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        CaesarCipher.encryptImage(leftImage3, rightImage3, (int) shiftImage3.getSelectedItem());
                        break;
                    }
                    case DECRYPT:
                    {
                        CaesarCipher.decryptImage(leftImage3, rightImage3, (int) shiftImage3.getSelectedItem());
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
                .addComponent(caesarEncryptDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(alphabetHint)
                        .addComponent(operationTypeHint)
                        .addComponent(shiftHint)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(alphabet)
                        .addComponent(operationType)
                        .addComponent(shift)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(beforeTextField)
                        .addComponent(transformTextButton)
                        .addComponent(afterTextField)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImageHint)
                        .addComponent(shiftImageHint)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage)
                        .addComponent(shiftImage)
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
                        .addComponent(shiftImageHint2)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage2)
                        .addComponent(shiftImage2)
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
                        .addComponent(shiftImageHint3)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage3)
                        .addComponent(shiftImage3)
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
                .addComponent(caesarEncryptDescription)

                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(alphabetHint)
                        .addComponent(operationTypeHint)
                        .addComponent(shiftHint)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(alphabet)
                        .addComponent(operationType)
                        .addComponent(shift)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(beforeTextField)
                        .addComponent(transformTextButton)
                        .addComponent(afterTextField)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImageHint)
                        .addComponent(shiftImageHint)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage)
                        .addComponent(shiftImage)
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
                        .addComponent(shiftImageHint2)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage2)
                        .addComponent(shiftImage2)
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
                        .addComponent(shiftImageHint3)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage3)
                        .addComponent(shiftImage3)
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

    private static String transformText(JComboBox<CryptoType> operationType, JTextArea beforeTextField, LanguageInput languageInput, int offset)
    {
        CryptoType cryptoType = (CryptoType) operationType.getSelectedItem();
        switch (Objects.requireNonNull(cryptoType))
        {   case ENCRYPT: return CaesarCipher.encryptText(beforeTextField.getText(), languageInput, offset);
            case DECRYPT: return CaesarCipher.decryptText(beforeTextField.getText(), languageInput, offset);
            default: return null;
        }
    }

    private static ImageScalingLabel createImageScalingLabel(String gradientExamplePath)
    {
        ImageScalingLabel leftImage = new ImageScalingLabel(gradientExamplePath, 300, 300);
        leftImage.setBorder(new BorderUIResource.LineBorderUIResource(new Color(0, 0, 0)));
        return leftImage;
    }

    private static JTextArea createAfterTextField(JPanel contentPanel)
    {
        JTextArea afterTextField = new JTextArea();
        afterTextField.setText("BBBBBBBBBBBBBBBBBBBBBBBB");
        afterTextField.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        afterTextField.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 400));
        return afterTextField;
    }

    private static JButton createTransformButton()
    {
        JButton transformTextButton = new JButton();
        transformTextButton.setText("->");
        transformTextButton.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        return transformTextButton;
    }

    private static JTextArea createBeforeTextField(String text, JPanel contentPanel)
    {
        JTextArea beforeTextField = new JTextArea();
        beforeTextField.setText(text);
        beforeTextField.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 400));
        beforeTextField.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        return beforeTextField;
    }

    private static JComboBox<Integer> createShiftComboBox(int shiftSize)
    {
        JComboBox<Integer> shift = new JComboBox<>();
        IntStream.rangeClosed(1, shiftSize).boxed().forEach(shift::addItem);
        shift.setMaximumSize(new Dimension(200, 20));
        return shift;
    }

    private static JLabel createShiftHintLabel()
    {
        JLabel shiftHint = new JLabel("Сдвиг");
        shiftHint.setMaximumSize(new Dimension(200, 40));
        shiftHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return shiftHint;
    }

    private static JComboBox<CryptoType> createOperationTypeComboBox()
    {
        JComboBox<CryptoType> operationType = new JComboBox<>();
        operationType.setModel(new DefaultComboBoxModel(CryptoType.values()));
        operationType.setMaximumSize(new Dimension(200, 20));
        return operationType;
    }

    private static JLabel createOperationTypeHintLabel()
    {
        JLabel operationTypeHint = new JLabel("Тип операции");
        operationTypeHint.setMaximumSize(new Dimension(200, 40));
        operationTypeHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return operationTypeHint;
    }

    private static JComboBox<LanguageInput> createAlphabetComboBox()
    {
        JComboBox<LanguageInput> alphabet = new JComboBox<>();
        alphabet.setModel(new DefaultComboBoxModel(LanguageInput.values()));
        alphabet.setMaximumSize(new Dimension(200, 20));
        return alphabet;
    }

    private static JLabel createLabelAlphabetHint()
    {
        JLabel alphabetHint = new JLabel("Алфавит");
        alphabetHint.setMaximumSize(new Dimension(200, 40));
        alphabetHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return alphabetHint;
    }
}
