package ru.encrypting.panel;

import ru.encrypting.common.helper.ElementsCreatorHelper;

import javax.swing.*;
import java.awt.*;

import static ru.encrypting.common.helper.ElementsCreatorHelper.*;

public class ButtonPanel extends JPanel
{
    public ButtonPanel(JPanel contentPanel)
    {
        ElementsCreatorHelper.setContentPanel(contentPanel);
        this.setBackground(new Color(0, 0, 0));
        setLayout(new GridBagLayout());
        addAllButtons();
    }

    private void addAllButtons()
    {
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        add(LEGEND_BUTTON, gbc);
        add(CAESER_ENCRYPT_BUTTON, gbc);
        add(VIGENERE_ENCRYPT_BUTTON, gbc);
        add(REPLACEMENT_ENCRYPT_BUTTON, gbc);
        add(ATBASH_ENCRYPT_BUTTON, gbc);
        add(ATBASH_ENCRYPT_BUTTON, gbc);
        add(CODE_WORD_ENCRYPT_BUTTON, gbc);
        add(RSA_ENCRYPT_BUTTON, gbc);

        gbc.weighty = 1;
        add(new JLabel(), gbc);
    }
}
