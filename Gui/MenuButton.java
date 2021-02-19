package Gui;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Backend.*;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    String buttonImgSrc = "";

    public MenuButton(String buttonText)
    {
        setText(buttonText);
        setFont(new Font("Arial", Font.BOLD, 40));         
        try 
        {
            String propertyName = "";
            String propertyType = "";

            propertyName = "DefaultColor2";
            propertyType = "App";

            Field field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName, propertyType));
            setBackground((Color)field.get(null));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }
        setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
    }

    public String getButtonImgSrc()
    {
        return this.buttonImgSrc;
    }

    public void setButtonImgSrc(String buttonImgSrc)
    {
        setIcon(new ImageIcon(buttonImgSrc));
    }
}
