import lombok.extern.java.Log;
import utils.MainMenu;
// comment for GitHub from Bogdan Ene

@Log
public class Main {
    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        mainMenu.createRandomProducts();
        mainMenu.createRandomClients();
        mainMenu.mainMenuOptions();

    }

}
