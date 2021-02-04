package Backend;

import Gui.LoginDialog;
import Gui.MainGui;
import Objects.Club;
import Objects.User;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Backend.Language.LanguageHandler;

public class Application
{
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static PropertiesHandler propertiesHandler;
    private static LanguageHandler languageHandler;
    public static LoginManager loginManager = new LoginManager();
    public static RegistrationManager registrationManager = new RegistrationManager();

    public static MainGui mainGui;

    public static Club currentClub;
    private static User currentUser;

    public static void main(String[]args)
    {
        propertiesHandler = new PropertiesHandler("Files/ClubManager.properties");
        propertiesHandler.getAppProperties();
        
        languageHandler = new LanguageHandler(propertiesHandler.getValueFromProperty("CurrentLanguage", "App"));
        languageHandler.setLanguage();

        loginManager.findLastLoggedInUser();

        if (!propertiesHandler.getUserProperties().isEmpty())
        {
            setCurrentUser(new User(propertiesHandler.getValueFromProperty("username", "User")));
            initMainGui();
        }
        else
        {
            //still needs work
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.initLoginDialog();
        }
    }

    public static void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui("Club Manager");
        }
    }

    public static Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.setLevel(Level.ALL);
            Handler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
        }
        return logger;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static void setCurrentUser(User user)
    {
        currentUser = user;
    }
}