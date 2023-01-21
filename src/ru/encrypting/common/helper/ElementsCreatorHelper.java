package ru.encrypting.common.helper;

import ru.encrypting.button.MenuButton;
import ru.encrypting.common.CryptoType;
import ru.encrypting.common.LanguageInput;
import ru.encrypting.label.ImageScalingLabel;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import static ru.encrypting.common.StringConstants.*;

public class ElementsCreatorHelper
{
    // Кнопки
    public static final JButton LEGEND_BUTTON = new MenuButton(DESCRIPTION)
    {{
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contentPanel.removeAll();
                contentPanel.repaint();
                InitLegendPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
            }
        });
    }};

    public static final JButton CAESER_ENCRYPT_BUTTON = new MenuButton(CAESAR_TITLE)
    {{
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contentPanel.removeAll();
                contentPanel.repaint();
                CaesarEncryptPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
            }
        });
    }};
    public static final JButton VIGENERE_ENCRYPT_BUTTON = new MenuButton(VIGENERE_TITLE)
    {{
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contentPanel.removeAll();
                contentPanel.repaint();
                VigenereEncryptPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
            }
        });
    }};
    public static final JButton ATBASH_ENCRYPT_BUTTON = new MenuButton(ATBASH_TITLE)
    {{
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contentPanel.removeAll();
                contentPanel.repaint();
                AtbashEncryptPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
            }
        });
    }};

    public static final JButton RSA_ENCRYPT_BUTTON = new MenuButton(RSA_TITLE)
    {{
       this.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               contentPanel.removeAll();
               contentPanel.repaint();
               RSAEncryptPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
           }
       });
    }};



    private static JPanel contentPanel;
    private static GroupLayout groupLayoutContentPanel;

    public static void setContentPanel(JPanel panel)
    {
        contentPanel = panel;
    }

    public static void setGroupLayoutContentPanel(GroupLayout groupLayout)
    {
        groupLayoutContentPanel = groupLayout;
    }

    public static JLabel createLabelAlphabetHint()
    {
        JLabel alphabetHint = new JLabel(ALPHABET);
        alphabetHint.setMaximumSize(new Dimension(200, 40));
        alphabetHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return alphabetHint;
    }

    public static JLabel createOperationTypeHintLabel()
    {
        JLabel operationTypeHint = new JLabel(OPERATION_TYPE);
        operationTypeHint.setMaximumSize(new Dimension(200, 40));
        operationTypeHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return operationTypeHint;
    }

    public static JComboBox<Integer> createShiftComboBox(int shiftSize)
    {
        JComboBox<Integer> shift = new JComboBox<>();
        IntStream.rangeClosed(1, shiftSize).boxed().forEach(shift::addItem);
        shift.setMaximumSize(new Dimension(200, 20));
        return shift;
    }

    public static JTextArea createBeforeTextField(String text, JPanel contentPanel)
    {
        JTextArea beforeTextField = new JTextArea();
        beforeTextField.setText(text);
        beforeTextField.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 400));
        return beforeTextField;
    }

    public static JButton createTransformButton()
    {
        JButton transformTextButton = new JButton();
        transformTextButton.setText(RIGHT_ARROW);
        return transformTextButton;
    }

    public static JTextArea createAfterTextField(JPanel contentPanel)
    {
        JTextArea afterTextField = new JTextArea();
        afterTextField.setText("BBBBBBBBBBBBBBBBBBBBBBBB");
        afterTextField.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 400));
        return afterTextField;
    }

    public static JComboBox<LanguageInput> createAlphabetComboBox()
    {
        JComboBox<LanguageInput> alphabet = new JComboBox<>();
        alphabet.setModel(new DefaultComboBoxModel(LanguageInput.values()));
        alphabet.setMaximumSize(new Dimension(200, 20));
        return alphabet;
    }

    public static JComboBox<CryptoType> createOperationTypeComboBox()
    {
        JComboBox<CryptoType> operationType = new JComboBox<>();
        operationType.setModel(new DefaultComboBoxModel(CryptoType.values()));
        operationType.setMaximumSize(new Dimension(200, 20));
        return operationType;
    }

    public static ImageScalingLabel createImageScalingLabel(String gradientExamplePath)
    {
        ImageScalingLabel leftImage = new ImageScalingLabel(gradientExamplePath, 300, 300);
        leftImage.setBorder(new BorderUIResource.LineBorderUIResource(new Color(0, 0, 0)));
        return leftImage;
    }
}
