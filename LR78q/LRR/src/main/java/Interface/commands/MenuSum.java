package Interface.commands;

import Content.Music;
import Interface.MenuItems;
import java.util.logging.Logger;

public class MenuSum implements MenuItems {
    public MenuSum() {
    }

    public void executeCommand(Music music, Logger log) throws Exception {
        System.out.print("\n Сумарна довжина плейлиста: " + music.getSum().getDuration() + "\n");
        log.info(" Успішно виведено сумарну довжину плейлиста. ");
    }
}