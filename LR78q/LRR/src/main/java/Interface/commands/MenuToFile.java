package Interface.commands;

import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuToFile implements MenuItems {
    Scanner scanner;

    public MenuToFile() {
        this.scanner = new Scanner(System.in);
    }

    public void executeCommand(Music music, Logger log) throws Exception {
        System.out.print("\n Як хочете назвати файл? Введіть назву: ");
        String filename = this.scanner.nextLine();
        music.SaveToFile(filename, log);
        log.info(" Список було успішно збережено до файла: \"" + filename + "\". ");
        System.out.print("\n Список буде збережено у файл.\n");
    }
}
