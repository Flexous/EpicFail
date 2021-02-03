package Gui;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.Application;
import net.miginfocom.swing.MigLayout;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private MenuButton openAddClubDialogBtn = new MenuButton("Add Club");
    private MenuButton openAddPlayerToClubDialogBtn = new MenuButton("Add Player to Club");

    private AddClubDialog addClubDialog;
    private AddPlayerToClubDialog addPlayerToClubDialog;

    private JPanel panel = new JPanel();

    public MainGui(String guiTitle)
    {
        setTitle(guiTitle);
        createMainGuiMenuWithoutClub();
        setIconImage(new ImageIcon(Application.propertiesHandler.getValueFromProperty("DefaultLogo", "App")).getImage());
        setResizable(true);
        //setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight()-10;
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createMainGuiMenuWithoutClub()
    {
        panel.setLayout(new MigLayout());
        Field field;
        try 
        {
            String propertyName = "";
            String propertyType = "";

            if (Application.currentUser == null)
            {
                propertyName = "DefaultColor1";
                propertyType = "App";
            }
            else
            {
                propertyName = "Color1";
                propertyType = "User";
            }

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName, propertyType));
            panel.setBackground((Color)field.get(null));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }

        openAddClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddClubDialog();
            } 
        });

        openAddPlayerToClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddPlayerToClubDialog();
            } 
        });

        panel.add(openAddClubDialogBtn);
        panel.add(openAddPlayerToClubDialogBtn, "gapleft 10");

        add(panel);
    }

    public void createMainGuiMenuWithClub()
    {
        openAddPlayerToClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddPlayerToClubDialog();
            } 
        });
    }

    public void showAddClubDialog()
    {
        if (addClubDialog == null)
        {
            addClubDialog = new AddClubDialog();
        }
        addClubDialog.setVisible(true);
    }

    public void showAddPlayerToClubDialog()
    {
        if (addPlayerToClubDialog == null)
        {
            addPlayerToClubDialog = new AddPlayerToClubDialog();
        }
        addPlayerToClubDialog.setVisible(true);
    }
}
