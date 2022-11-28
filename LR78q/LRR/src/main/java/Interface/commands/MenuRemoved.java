package Interface.commands;

import Content.Music;
import Interface.MenuItems;
import java.util.logging.Logger;

public class MenuRemoved implements MenuItems {
    public MenuRemoved() {
    }

    public void executeCommand(Music music, Logger log) {
        log.fine(" Виклик методу printRemoved (вивід видалених пісень). ");
        music.printRemoved(log);
    }
}
