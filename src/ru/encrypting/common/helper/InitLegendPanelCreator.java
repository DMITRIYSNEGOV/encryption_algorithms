package ru.encrypting.common.helper;

import ru.encrypting.label.ImageScalingLabel;
import ru.encrypting.label.TitleLabel;
import ru.encrypting.textPane.DescriptionTextPane;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

import static ru.encrypting.common.ResourcesPath.*;

public class InitLegendPanelCreator
{
    public static void initLegendPanel(GroupLayout groupLayoutContentPanel)
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
}
