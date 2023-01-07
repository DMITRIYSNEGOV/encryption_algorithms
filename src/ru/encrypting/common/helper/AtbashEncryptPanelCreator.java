package ru.encrypting.common.helper;

import ru.encrypting.cipher.AtbashCipher;
import ru.encrypting.cipher.CaesarCipher;
import ru.encrypting.common.CryptoType;
import ru.encrypting.common.LanguageInput;
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

import static javax.swing.JOptionPane.showMessageDialog;
import static ru.encrypting.common.ResourcesPath.*;

public class AtbashEncryptPanelCreator implements EncryptPanelCreator
{
    private static final AtbashEncryptPanelCreator INSTANCE = new AtbashEncryptPanelCreator();

    public static AtbashEncryptPanelCreator getInstance()
    {
        return INSTANCE;
    }

    private AtbashEncryptPanelCreator()
    {}

    @Override
    public void initPanel(JPanel contentPanel, GroupLayout groupLayoutContentPanel)
    {
        JLabel title = new TitleLabel("Шифр Атбаша", ATBASH_TITLE_PATH);
        title.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 40));

        JTextPane description = new DescriptionTextPane(
                "   Атба́ш — простой шифр подстановки для алфавитного письма. Правило шифрования состоит в замене i-й буквы алфавита буквой с номером  n-i+1, где n — число букв в алфавите.\n" +
                        "Происхождение слова «атбаш» объясняется принципом замены букв. Слово (точнее - аббревиатура на древнееврейском языке) \"אתבש\" составлено из букв «алеф», «тав», «бет» и «шин», то есть первой, последней, второй и предпоследней букв еврейского алфавита.\n" +
                        "Впервые встречается в древнееврейском тексте Библии / Танаха.\n" +
                        "Ниже даны примеры для английского, русского алфавитов:",
                new Dimension(700, 100));

        ImageScalingLabel atbashEncryptDescription = new ImageScalingLabel(ATBASH_ENCRYPT_DESCRIPTION_PATH, 650, 105);

        // пример шифрования текста
        // выбор алфавита
        JLabel alphabetHint = createLabelAlphabetHint();
        JComboBox<LanguageInput> alphabet = createAlphabetComboBox();

        // выбор типа операции
        JLabel operationTypeHint = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationType = createOperationTypeComboBox();

        JTextArea beforeTextField = createBeforeTextField("текст на русском", contentPanel);
        JButton transformTextButton = createTransformButton();
        JTextArea afterTextField = createAfterTextField(contentPanel);

        transformTextButton.addActionListener(e ->
        {
            LanguageInput languageInput = (LanguageInput) Objects.requireNonNull(alphabet.getSelectedItem());

            String transformedText = transformText(operationType, beforeTextField, languageInput);
            afterTextField.setText(transformedText);
        });

        // пример шифрования изображения 1
        // выбор типа операции
        JLabel operationTypeImageHint = createOperationTypeHintLabel();
        JComboBox<CryptoType> operationTypeImage = createOperationTypeComboBox();

        ImageScalingLabel leftImage = createImageScalingLabel(HUMAN_EXAMPLE);
        JButton transformImageButton = createTransformButton();

        JButton swapImageButton = new JButton();
        swapImageButton.setText("↔");

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
                switch (Objects.requireNonNull(cryptoType))
                {
                    case ENCRYPT:
                    {
                        AtbashCipher.encryptImage(leftImage, rightImage);
                        break;
                    }
                    case DECRYPT:
                    {
                        AtbashCipher.decryptImage(leftImage, rightImage);
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

        ImageScalingLabel leftImage2 = createImageScalingLabel(DIAGONAL_LINE_EXAMPLE_PATH);
        JButton transformImageButton2 = createTransformButton();

        JButton swapImageButton2 = new JButton();
        swapImageButton2.setText("↔");

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
                        AtbashCipher.encryptImage(leftImage2, rightImage2);
                        break;
                    }
                    case DECRYPT:
                    {
                        AtbashCipher.decryptImage(leftImage2, rightImage2);
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

        JButton downloadImage = new JButton();
        downloadImage.setText("Загрузить картинку");

        ImageScalingLabel leftImage3 = createImageScalingLabel(DIAGONAL_LINE_EXAMPLE_PATH);
        JButton transformImageButton3 = createTransformButton();

        JButton swapImageButton3 = new JButton();
        swapImageButton3.setText("↔");

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
                        AtbashCipher.encryptImage(leftImage3, rightImage3);
                        break;
                    }
                    case DECRYPT:
                    {
                        AtbashCipher.decryptImage(leftImage3, rightImage3);
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
                .addComponent(atbashEncryptDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(alphabetHint)
                        .addComponent(operationTypeHint)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(alphabet)
                        .addComponent(operationType)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(beforeTextField)
                        .addComponent(transformTextButton)
                        .addComponent(afterTextField)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImageHint)
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage)
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
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage2)
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
                )
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(operationTypeImage3)
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
                .addComponent(atbashEncryptDescription)
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(alphabetHint)
                        .addComponent(operationTypeHint)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(alphabet)
                        .addComponent(operationType)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(beforeTextField)
                        .addComponent(transformTextButton)
                        .addComponent(afterTextField)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImageHint)
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage)
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
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage2)
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
                )
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(operationTypeImage3)
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

    private static JTextArea createBeforeTextField(String text, JPanel contentPanel)
    {
        JTextArea beforeTextField = new JTextArea();
        beforeTextField.setText(text);
        beforeTextField.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 400));
        beforeTextField.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        return beforeTextField;
    }

    private static JButton createTransformButton()
    {
        JButton transformTextButton = new JButton();
        transformTextButton.setText(" → ");
        transformTextButton.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        return transformTextButton;
    }

    private static JTextArea createAfterTextField(JPanel contentPanel)
    {
        JTextArea afterTextField = new JTextArea();
        afterTextField.setText("BBBBBBBBBBBBBBBBBBBBBBBB");
        afterTextField.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        afterTextField.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 400));
        return afterTextField;
    }

    private static String transformText(JComboBox<CryptoType> operationType, JTextArea beforeTextField, LanguageInput languageInput)
    {
        CryptoType cryptoType = (CryptoType) operationType.getSelectedItem();
        switch (Objects.requireNonNull(cryptoType))
        {   case ENCRYPT: return AtbashCipher.encryptText(beforeTextField.getText(), languageInput);
            case DECRYPT: return AtbashCipher.decryptText(beforeTextField.getText(), languageInput);
            default: return null;
        }
    }

    private static ImageScalingLabel createImageScalingLabel(String gradientExamplePath)
    {
        ImageScalingLabel leftImage = new ImageScalingLabel(gradientExamplePath, 300, 300);
        leftImage.setBorder(new BorderUIResource.LineBorderUIResource(new Color(0, 0, 0)));
        return leftImage;
    }
}
