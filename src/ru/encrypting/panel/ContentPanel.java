package ru.encrypting.panel;

import lombok.Getter;
import lombok.Setter;
import ru.encrypting.common.helper.ElementsCreatorHelper;
import ru.encrypting.common.helper.InitLegendPanelCreator;

import javax.swing.*;
import java.awt.*;


@Getter
@Setter
public class ContentPanel extends JPanel
{

    public ContentPanel()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        this.setLayout(groupLayout);
        this.setBackground(new Color(255, 255, 255));
        ElementsCreatorHelper.setGroupLayoutContentPanel(groupLayout);
        InitLegendPanelCreator.initLegendPanel(groupLayout);
    }


}
