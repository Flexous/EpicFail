package Gui.Dialogs;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Backend.Application;
import Backend.ClubManagerFunctions;
import Gui.MenuButton;
import Objects.Club;

public class CreateClubDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -7010188678308927116L;
    
    public CreateClubDialog()
    {
        super();

        Club club = new Club();

        JLabel nameOfClubLabel = new JLabel("Name des Vereins:");
        nameOfClubLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nameOfClubLabel, "gapleft 50, wrap");
        
        JTextField nameOfClubField = new JTextField(20);
        getPanel().add(nameOfClubField, "gapleft 50, wrap");

        MenuButton chooseColor1Button = new MenuButton("Farbe 1");
        chooseColor1Button.setFont(new Font("Arial", Font.BOLD, 20)); 

        JLabel color1Label = new JLabel("Farbe 1");
        color1Label.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        color1Label.setVisible(false);

        chooseColor1Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor1(JColorChooser.showDialog(null, "Farbauswahl", null));
                color1Label.setForeground(club.getColor1());
                color1Label.setVisible(true);
                revalidate();
                repaint();
            }
        });
        getPanel().add(chooseColor1Button, "gapleft 50");
        getPanel().add(color1Label, "gapleft 5, wrap");

        MenuButton chooseColor2Button = new MenuButton("Farbe 2");
        chooseColor2Button.setFont(new Font("Arial", Font.BOLD, 20)); 

        JLabel color2Label = new JLabel("Farbe 2");
        color2Label.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        color2Label.setVisible(false);

        chooseColor2Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor2(JColorChooser.showDialog(null, "Farbauswahl", null));
                color2Label.setForeground(club.getColor2());
                color2Label.setVisible(true);
                revalidate();
                repaint();
            }
        });
        getPanel().add(chooseColor2Button, "gapleft 50");
        getPanel().add(color2Label, "gapleft 5, wrap");

        JLabel logoOfClubLabel = new JLabel("Logo des Vereins:");
        logoOfClubLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(logoOfClubLabel, "gapleft 50, gaptop 20, wrap");

        MenuButton chooseLogoBtn = new MenuButton("Bild");
        chooseLogoBtn.setFont(new Font("Arial", Font.BOLD, 20));        
        chooseLogoBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");

                JFileChooser chooser = new JFileChooser();   
                chooser.setFileFilter(filter);   
                int fileSelected = chooser.showOpenDialog(null);

                if (fileSelected == JFileChooser.APPROVE_OPTION)
                {
                    club.setLogo(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        getPanel().add(chooseLogoBtn, "gapleft 50, wrap");

        MenuButton createClubBtn = new MenuButton("Verein erstellen");
        createClubBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                club.setName(nameOfClubField.getText());

                Application.setCurrentClub(club);

                ClubManagerFunctions.createNewClubFile(club);

                dispose();
                Application.closeMainGui();
                Application.initMainGui();
            }
        });
        getPanel().add(createClubBtn, "gapleft 50, gaptop 150, wrap");

        add(getPanel());
    }
}
