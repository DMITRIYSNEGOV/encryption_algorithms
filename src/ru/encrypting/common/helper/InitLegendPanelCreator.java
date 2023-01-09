package ru.encrypting.common.helper;

import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.swing.*;
import java.awt.*;

import static ru.encrypting.common.ResourcesPath.*;
import static ru.encrypting.common.StringConstants.*;

public class InitLegendPanelCreator implements EncryptPanelCreator
{
    private static final EncryptPanelCreator INSTANCE = new InitLegendPanelCreator();

    public static EncryptPanelCreator getInstance()
    {
        return INSTANCE;
    }

    private InitLegendPanelCreator()
    {}

    @Override
    public void initPanel(JPanel contentPanel, GroupLayout groupLayoutContentPanel)
    {
        JLabel title = new TitleLabel(LEGEND_TITLE , LEGEND_TITLE_PATH);

        JTextPane description = new DescriptionTextPane(
                LEGEND_DESCRIPTION,
                new Dimension(700,23));

        Font bigFont = new Font("Arial Black", Font.BOLD, 45);
        JLabel textBeforeEncode = new JLabel(LEGEND_SIMPLE_TEXT) {{ setFont(bigFont); }};
        ImageScalingLabel arrow = new ImageScalingLabel(ARROW_RIGHT_PATH, 120, 67);
        JLabel textAfterEncode =  new JLabel(LEGEND_CIPHERED_TEXT) {{ setFont(bigFont);}};

        JLabel textBeforeDecode = new JLabel(LEGEND_CIPHERED_TEXT) {{ setFont(bigFont); }};
        ImageScalingLabel key = new ImageScalingLabel(KEY_PATH, 119, 58);
        JLabel textAfterDecode =  new JLabel(LEGEND_SIMPLE_TEXT) {{ setFont(bigFont);}};

        JLabel author = new JLabel();
        author.setHorizontalAlignment(SwingConstants.RIGHT);
        author.setVerticalAlignment(SwingConstants.BOTTOM);
        author.setText(LEGEND_AUTHOR);

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
}
