package ru.encrypting.common.helper;

import ru.encrypting.cipher.RSACipher;
import ru.encrypting.common.CryptoType;
import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static ru.encrypting.cipher.RSACipher.*;
import static ru.encrypting.common.ResourcesPath.*;
import static ru.encrypting.common.ResourcesPath.EMPTY_IMAGE_PATH;

public class RSAEncryptPanelCreator
{
    public static void initRSAEncryptPanel(JPanel contentPanel, GroupLayout groupLayoutContentPanel)
    {
        JLabel title = new TitleLabel("RSA шифрование", RSA_TITLE_PATH);
        title.setMaximumSize(new Dimension((int) contentPanel.getSize().getWidth(), 40));

        JTextPane description = new DescriptionTextPane(
                "   RSA — криптографический алгоритм с открытым ключом, основывающийся на вычислительной сложности задачи факторизации больших целых чисел.\n" +
                "   Криптосистема RSA стала первой системой, пригодной и для шифрования, и для цифровой подписи. Алгоритм используется в большом числе криптографических приложений, включая PGP, S/MIME, TLS/SSL, IPSEC/IKE и других.\n" +
                "   При шифровании RSA сообщения шифруются с помощью кода, называемого открытый ключ, которыми можно поделиться открыто. Из-за некоторых четких математических свойств алгоритма RSA, если сообщение было зашифровано открытым ключом, оно может быть расшифровано только другим ключом, известным как закрытый ключ. У каждого пользователя RSA есть пара ключей, состоящая из их открытого и закрытого ключей. Как следует из названия, закрытый ключ должен храниться в секрете.\n" +
                "   Схемы шифрования с открытым ключом отличаются от шифрование с симметричным ключом, где и в процессе шифрования, и в дешифровании используется один и тот же закрытый ключ. Эти различия делают шифрование с открытым ключом, такое как RSA, полезным для связи в ситуациях, когда не было возможности безопасно распространять ключи заранее.",
                new Dimension(700, 100));

        ImageScalingLabel caesarEncryptDescription = new ImageScalingLabel(RSA_ENCRYPT_DESCRIPTION_PATH, 650, 274);

        JLabel textHint = createLabelHint("Текст");
        JTextArea textInput = createTextArea("simple text", contentPanel);

        RSACipher.RSA rsa = new RSA(textInput.getText());

        JLabel pHint = createLabelHint("p");
        JTextArea pInput = createTextArea(rsa.getP().toString(), contentPanel);

        JLabel qHint = createLabelHint("q");
        JTextArea qInput = createTextArea(rsa.getQ().toString(), contentPanel);

        DescriptionTextPane nDescription = new DescriptionTextPane(
                "Вычисляем \"n = p * q\"",
                new Dimension(700, 40));
        JLabel nHint = createLabelHint("n");
        JTextArea nInput = createTextArea(rsa.getN().toString(), contentPanel);

        DescriptionTextPane totnDescription = new DescriptionTextPane(
                "Вычисляем тотиентную функцию Эйлера. tot(n) = φ(n) = (p - 1) * (q - 1)",
                new Dimension(700, 40));
        JLabel totnHint = createLabelHint("tot(n)");
        JTextArea totnInput = createTextArea(rsa.getTotN().toString(), contentPanel);

        DescriptionTextPane eDescription = new DescriptionTextPane(
                "Выберем любое число \"e\", где \"1 < e < φ(n)\" и \"e\" и \"e\" нечетное число, которое не имеет общих делителей с φ(n)",
                new Dimension(700, 40));
        JLabel eHint = createLabelHint("e");
        JTextArea eInput = createTextArea(rsa.getE().toString(), contentPanel);

        DescriptionTextPane dDescription = new DescriptionTextPane(
                "Вычислим \"d\", которое обратно \"e\" по модулю φ",
                new Dimension(700, 40));
        JLabel dHint = createLabelHint("d");
        JTextArea dInput = createTextArea(rsa.getD().toString(), contentPanel);

        JLabel byteArrayTextHint = createLabelHint("Текст в байтах");
        JTextArea byteArrayTextInput = createTextArea(rsa.getCipherText().toString(), contentPanel);

        JLabel encryptedByteArrayTextHint = createLabelHint("Зашифрованный текст в байтах");
        JTextArea encryptedByteArrayTextInput = createTextArea(rsa.getEncrypted().toString(), contentPanel);

        JLabel encryptedTextHint = createLabelHint("Зашифрованный текст");
        JTextArea encryptedTextInput = createTextArea(rsa.getEncryptedBytes(), contentPanel);

        JLabel decryptedByteArrayTextHint = createLabelHint("Расшифрованный текст в байтах");
        JTextArea decryptedByteArrayTextInput = createTextArea(rsa.getDecrypted().toString(), contentPanel);

        JLabel decryptedTextHint = createLabelHint("Расшифрованный текст");
        JTextArea decryptedTextInput = createTextArea(rsa.getRestoredText(), contentPanel);

        JButton finalCalculate = new JButton("Вычислить")
        {{
            this.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    RSACipher.RSA rsa = new RSA(textInput.getText());
                    pInput.setText(rsa.getP().toString());
                    qInput.setText(rsa.getQ().toString());
                    nInput.setText(rsa.getN().toString());
                    totnInput.setText(rsa.getTotN().toString());
                    eInput.setText(rsa.getE().toString());
                    dInput.setText(rsa.getD().toString());

                    byteArrayTextInput.setText(rsa.getCipherText().toString());

                    encryptedByteArrayTextInput.setText(rsa.getEncryptedBytes());

                    encryptedTextInput.setText(rsa.getEncrypted().toString());
                    decryptedByteArrayTextInput.setText(rsa.getDecrypted().toString());
                    decryptedTextInput.setText(rsa.getRestoredText());
                }
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
        textField.setBorder(new BorderUIResource.LineBorderUIResource(new Color(255, 0, 0)));
        return textField;
    }
}
