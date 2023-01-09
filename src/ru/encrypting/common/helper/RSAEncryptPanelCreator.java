package ru.encrypting.common.helper;

import ru.encrypting.cipher.RSACipher;
import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.encrypting.cipher.RSACipher.*;
import static ru.encrypting.common.ResourcesPath.*;
import static ru.encrypting.common.StringConstants.*;

public class RSAEncryptPanelCreator implements EncryptPanelCreator
{
    private static final EncryptPanelCreator INSTANCE = new RSAEncryptPanelCreator();

    public static EncryptPanelCreator getInstance()
    {
        return INSTANCE;
    }

    private RSAEncryptPanelCreator()
    {}

    @Override
    public void initPanel(JPanel contentPanel, GroupLayout groupLayoutContentPanel)
    {
        JLabel title = new TitleLabel(RSA_TITLE , RSA_TITLE_PATH);
        title.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 40));

        JTextPane description = new DescriptionTextPane(
                RSA_DESCRIPTION,
                new Dimension(700, 100));

        ImageScalingLabel caesarEncryptDescription = new ImageScalingLabel(RSA_ENCRYPT_DESCRIPTION_PATH, 650, 274);

        JLabel textHint = createLabelHint(RSA_TEXT_HINT);
        JTextArea textInput = createTextArea("simple text", contentPanel);

        RSACipher.RSA rsa = new RSA(textInput.getText());

        JLabel pHint = createLabelHint(RSA_P);
        JTextArea pInput = createTextArea(rsa.getP().toString(), contentPanel);

        JLabel qHint = createLabelHint(RSA_Q);
        JTextArea qInput = createTextArea(rsa.getQ().toString(), contentPanel);

        DescriptionTextPane nDescription = new DescriptionTextPane(
                RSA_N_DESCRIPTION,
                new Dimension(700, 40));
        JLabel nHint = createLabelHint(RSA_N);
        JTextArea nInput = createTextArea(rsa.getN().toString(), contentPanel);

        DescriptionTextPane totnDescription = new DescriptionTextPane(
                RSA_TOTN_DESCRIPTION,
                new Dimension(700, 40));
        JLabel totnHint = createLabelHint(RSA_TOTN);
        JTextArea totnInput = createTextArea(rsa.getTotN().toString(), contentPanel);

        DescriptionTextPane eDescription = new DescriptionTextPane(
                RSA_E_DESCRIPTION,
                new Dimension(700, 40));
        JLabel eHint = createLabelHint(RSA_E);
        JTextArea eInput = createTextArea(rsa.getE().toString(), contentPanel);

        DescriptionTextPane dDescription = new DescriptionTextPane(
                RSA_D_DESCRIPTION,
                new Dimension(700, 40));
        JLabel dHint = createLabelHint(RSA_D);
        JTextArea dInput = createTextArea(rsa.getD().toString(), contentPanel);

        JLabel byteArrayTextHint = createLabelHint(RSA_BYTE_ARRAY);
        JTextArea byteArrayTextInput = createTextArea(rsa.getCipherText().toString(), contentPanel);

        JLabel encryptedByteArrayTextHint = createLabelHint(RSA_ENCRYPTED_BYTE_ARRAY);
        JTextArea encryptedByteArrayTextInput = createTextArea(rsa.getEncrypted().toString(), contentPanel);

        JLabel encryptedTextHint = createLabelHint(RSA_ENCRYPTED_BYTE_ARRAY_TEXT);
        JTextArea encryptedTextInput = createTextArea(rsa.getEncryptedBytes(), contentPanel);

        JLabel decryptedByteArrayTextHint = createLabelHint(RSA_DECRYPTED_BYTE_ARRAY_TEXT);
        JTextArea decryptedByteArrayTextInput = createTextArea(rsa.getDecrypted().toString(), contentPanel);

        JLabel decryptedTextHint = createLabelHint(RSA_DECRYPTED_TEXT);
        JTextArea decryptedTextInput = createTextArea(rsa.getRestoredText(), contentPanel);

        JButton finalCalculate = new JButton(RSA_FINAL_CALCULATE)
        {{
            this.addActionListener(e ->
            {
                RSA rsa1 = new RSA(textInput.getText());
                pInput.setText(rsa1.getP().toString());
                qInput.setText(rsa1.getQ().toString());
                nInput.setText(rsa1.getN().toString());
                totnInput.setText(rsa1.getTotN().toString());
                eInput.setText(rsa1.getE().toString());
                dInput.setText(rsa1.getD().toString());

                byteArrayTextInput.setText(rsa1.getCipherText().toString());

                encryptedByteArrayTextInput.setText(rsa1.getEncryptedBytes());

                encryptedTextInput.setText(rsa1.getEncrypted().toString());
                decryptedByteArrayTextInput.setText(rsa1.getDecrypted().toString());
                decryptedTextInput.setText(rsa1.getRestoredText());
            });
        }};

        groupLayoutContentPanel.setHorizontalGroup(groupLayoutContentPanel.createParallelGroup()
                .addComponent(title)
                .addComponent(description)
                .addComponent(caesarEncryptDescription)
                .addComponent(finalCalculate)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(textHint)
                    .addComponent(textInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(pHint)
                    .addComponent(pInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(qHint)
                    .addComponent(qInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(nHint)
                .addComponent(nInput)
                .addComponent(nDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(totnHint)
                .addComponent(totnInput)
                .addComponent(totnDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(eHint)
                .addComponent(eInput)
                .addComponent(eDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(dHint)
                .addComponent(dInput)
                .addComponent(dDescription)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(byteArrayTextHint)
                    .addComponent(byteArrayTextInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(encryptedByteArrayTextHint)
                    .addComponent(encryptedByteArrayTextInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(encryptedTextHint)
                    .addComponent(encryptedTextInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(decryptedByteArrayTextHint)
                    .addComponent(decryptedByteArrayTextInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
                    .addComponent(decryptedTextHint)
                    .addComponent(decryptedTextInput)
                .addGroup(groupLayoutContentPanel.createSequentialGroup())
        );

        groupLayoutContentPanel.setVerticalGroup(groupLayoutContentPanel.createSequentialGroup()
                .addComponent(title)
                .addComponent(description)
                .addComponent(caesarEncryptDescription)
                .addComponent(finalCalculate)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(textHint)
                .addComponent(textInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(pHint)
                .addComponent(pInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(qHint)
                .addComponent(qInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(nHint)
                .addComponent(nInput)
                .addComponent(nDescription)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(totnHint)
                .addComponent(totnInput)
                .addComponent(totnDescription)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(eHint)
                .addComponent(eInput)
                .addComponent(eDescription)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(dHint)
                .addComponent(dInput)
                .addComponent(dDescription)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(byteArrayTextHint)
                .addComponent(byteArrayTextInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(encryptedByteArrayTextHint)
                .addComponent(encryptedByteArrayTextInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(encryptedTextHint)
                .addComponent(encryptedTextInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(decryptedByteArrayTextHint)
                .addComponent(decryptedByteArrayTextInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
                .addComponent(decryptedTextHint)
                .addComponent(decryptedTextInput)
                .addGroup(groupLayoutContentPanel.createParallelGroup())
        );
    }

    private static JLabel createLabelHint(String hint)
    {
        JLabel labelHint = new JLabel(hint);
        labelHint.setMaximumSize(new Dimension(200, 40));
        labelHint.setPreferredSize(new Dimension(200, 40));
        labelHint.setSize(200, 40);
        labelHint.setVerticalAlignment(SwingConstants.BOTTOM);
        return labelHint;
    }

    private static JTextArea createTextArea(String text, JPanel contentPanel)
    {
        JTextArea textField = new JTextArea();
        textField.setText(text);
        textField.setMaximumSize(new Dimension(600, 40));
        textField.setPreferredSize(new Dimension(600, 40));
        textField.setSize(600, 40);
        textField.setLineWrap(true);
        return textField;
    }
}
