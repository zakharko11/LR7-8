import Content.Music;
import Interface.CommandMenu;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    public static final Logger log = Logger.getGlobal();
    public static Handler fileHandler;
    static {
        try {
            fileHandler = new FileHandler();
            log.setUseParentHandlers(false);
            log.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Music obj = new Music();
    public static Scanner input = new Scanner(System.in);
    public static CommandMenu menu = new CommandMenu();

    public static void main(String[] args) throws Exception {
        log.info(" Оголошення змінних: типу Music, командного меню та сканера. ");
        menu.exeCommand(obj, "menu", log);
        while(true){
            System.out.print("\n Введіть команду: ");
            String command = input.next();
            menu.exeCommand(obj, command, log);
            input.nextLine();
        }
    }
}