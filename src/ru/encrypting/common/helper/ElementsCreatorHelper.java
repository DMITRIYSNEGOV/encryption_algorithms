package ru.encrypting.common.helper;

import ru.encrypting.button.MenuButton;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                InitLegendPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
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
                CaesarEncryptPanelCreator.getInstance().initPanel(contentPanel, groupLayoutContentPanel);
            }
        });
    }};
    public static final JButton VIGENERE_ENCRYPT_BUTTON = new MenuButton("Шифр Виженера")
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
    public static final JButton REPLACEMENT_ENCRYPT_BUTTON = new MenuButton("Шифр замены");
    public static final JButton ATBASH_ENCRYPT_BUTTON = new MenuButton("Шифр Атбаша");
    public static final JButton CODE_WORD_ENCRYPT_BUTTON = new MenuButton("Шифр кодового слова");
    public static final JButton RSA_ENCRYPT_BUTTON = new MenuButton("RSA Шифрование")
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
}
