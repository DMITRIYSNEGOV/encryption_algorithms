package ru.encrypting.common.helper;

import ru.encrypting.button.MenuButton;
import ru.encrypting.cipher.CaesarCipher;
import ru.encrypting.common.CryptoType;
import ru.encrypting.common.LanguageInput;
import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

import static javax.swing.JOptionPane.showMessageDialog;
import static ru.encrypting.common.ResourcesPath.*;

public class ElementsCreatorHelper
{
    // Кнопки
    public static final JButton LEGEND_BUTTON = new MenuButton("Описание")
    {{
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contentPanel.removeAll();
                contentPanel.repaint();
                initLegendPanel();
            }
        });
    }};

    public static final JButton CAESER_ENCRYPT_BUTTON = new MenuButton("Шифр Цезаря")
    {{
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contentPanel.removeAll();
                contentPanel.repaint();
                initCaesarEncryptPanel();
            }
        });
    }};
    public static final JButton VIGENERE_ENCRYPT_BUTTON = new MenuButton("Шифр Виженера");
    public static final JButton REPLACEMENT_ENCRYPT_BUTTON = new MenuButton("Шифр замены");
    public static final JButton ATBASH_ENCRYPT_BUTTON = new MenuButton("Шифр Атбаша");
    public static final JButton CODE_WORD_ENCRYPT_BUTTON = new MenuButton("Шифр кодового слова");

    private static JPanel contentPanel;
    private static GroupLayout groupLayoutContentPanel;
    private static JFrame mainFrame;

    public static void setContentPanel(JPanel panel)
    {
        contentPanel = panel;
    }

    public static void setGroupLayoutContentPanel(GroupLayout groupLayout)
    {
        groupLayoutContentPanel = groupLayout;
    }

    public static void setFrame(JFrame frame)
    {
        mainFrame = frame;
    }

    public static void initLegendPanel()
    {
        JLabel title = new TitleLabel("Приложение для изучения алгоритмов шифрования", LEGEND_TITLE_PATH);

        JTextPane description = new DescriptionTextPane("Данная программа позволит познакомиться и изучить популярные виды шифрования на существующих и своих примерах");
        description.setMaximumSize(new Dimension(700,23));

        Font bigFont = new Font("Arial Black", Font.BOLD, 45);
        JLabel textBeforeEncode = new JLabel("TEXT") {{ setFont(bigFont); }};
        ImageScalingLabel arrow = new ImageScalingLabel(ARROW_RIGHT_PATH, 200, 140);
        JLabel textAfterEncode =  new JLabel("UFYU") {{ setFont(bigFont);}};

        arrow.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));

        JLabel textBeforeDecode = new JLabel("UFYU") {{ setFont(bigFont); }};
        ImageScalingLabel key = new ImageScalingLabel(KEY_PATH, 200, 140);
        JLabel textAfterDecode =  new JLabel("TEXT") {{ setFont(bigFont);}};

        JLabel author = new JLabel();
        author.setHorizontalAlignment(SwingConstants.RIGHT);
        author.setVerticalAlignment(SwingConstants.BOTTOM);
        author.setText("<html><br><br><br><br><br><br><br>Разработчик: Снегов Дмитрий<br>Год: 2022</html>");

        groupLayoutContentPanel.setHorizontalGroup(groupLayoutContentPanel.createParallelGroup()
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(title))
                .addComponent(description)
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(textBeforeEncode)
                        .addComponent(arrow)
                        .addComponent(textAfterEncode))
                .addGroup(groupLayoutContentPanel.createSequentialGroup()
                        .addComponent(textBeforeDecode)
                        .addComponent(key)
                        .addComponent(textAfterDecode))
                .addComponent(author)
        );

        groupLayoutContentPanel.setVerticalGroup(groupLayoutContentPanel.createSequentialGroup()
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(title))
                .addComponent(description)
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(textBeforeEncode)
                        .addComponent(arrow)
                        .addComponent(textAfterEncode))
                .addGroup(groupLayoutContentPanel.createParallelGroup()
                        .addComponent(textBeforeDecode)
                        .addComponent(key)
                        .addComponent(textAfterDecode))
                .addComponent(author)
        );
    }

    private static void initCaesarEncryptPanel()
    {
        JLabel title = new TitleLabel("Шифр Цезаря", CAESAR_TITLE_PATH);
        title.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 40));

        JTextPane description = new DescriptionTextPane("   Шифр Цeзаря (шифр сдвига, код Цезаря) – такой простой вид шифрования текста, при котором все символы в тексте заменяются символами, сдвинутыми по алфавиту на правее или левее на постоянное количество позиций. Например, при сдвиге на 1 буква А заменяется на Б, Б на В и т.д. Вы можете как зашифровать текст данным способом, так и выполнить дешифровку.");
        description.setMaximumSize(new Dimension(700, 100));

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
        JTextArea beforeTextField = createBeforeTextField("текст на русском");
        JButton transformTextButton = createTransformButton();
        JTextArea afterTextField = createAfterTextField();

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

    private static JTextArea createAfterTextField()
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

    private static JTextArea createBeforeTextField(String text)
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
