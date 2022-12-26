package ru.encrypting;

import ru.encrypting.panel.ButtonPanel;
import ru.encrypting.panel.ContentPanel;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import static ru.encrypting.common.ResourcesPath.MAIN_ICON;

public class EncryptLearner
{
    public static void main(String[] args) throws IOException
    {
        JFrame mainFrame = new JFrame("Изучение алгоритмов шифрования");
        mainFrame.setSize(917, 600);
        mainFrame.setResizable(false);

        File mainIcon = new File(EncryptLearner.class.getResource(MAIN_ICON).getPath());
        mainFrame.setIconImage(new ImageIcon(mainIcon.getPath()).getImage());
        JPanel contentPanel = new ContentPanel();
        contentPanel.setBackground(new Color(123,123,213));

        JScrollPane scrollPane = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new ButtonPanel(contentPanel), scrollPane);

        splitPane.setOneTouchExpandable(true);

        mainFrame.add(splitPane);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);


    }
}
