package Gui.Dialogs;

import java.lang.reflect.Field;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Backend.Application;
import net.miginfocom.swing.MigLayout;

public class ClubManagerDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;
 
    private JPanel panel = new JPanel();

    public ClubManagerDialog()
    {
        setPanelSettings();
        setIconImage(new ImageIcon(Application.propertiesHandler.getValueFromProperty("DefaultLogo", "App")).getImage());
        setMinimumSize(new DimensionUIResource(700, 700));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(false);
    }

    public void setPanelSettings()
    {
        panel.setLayout(new MigLayout());

        Field field;
        try 
        {
            String propertyName = "";
            String propertyType = "";

            propertyName = "DefaultColor";
            propertyType = "App";

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName+"1", propertyType));
            panel.setBackground((Color)field.get(null));

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName+"2", propertyType));
            panel.setBorder(BorderFactory.createLineBorder((Color)field.get(null)));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }
    }

    public JPanel getPanel()
    {
        return panel;
    }
}
