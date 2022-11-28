package Interface.commands;

import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuRestor implements MenuItems {
    Scanner scanner;
    int numb;

    public MenuRestor() {
        this.scanner = new Scanner(System.in);
    }

    public void executeCommand(Music music, Logger log) {
        if (music.IsAnyRemoved(log)) {
            System.out.print("\n Список доступних на відновлення пісень: ");
            music.printRemoved(log);

            while(true) {
                System.out.print("\n Введіть номер пісні (із списку вилучених), яку хочете відновити: ");
                this.numb = this.scanner.nextInt();
                if (this.numb > 0 && this.numb <= music.dropped.size()) {
                    music.restoreSong(this.numb);
                    log.info(" Композицію за номером " + this.numb + " було успішно відновлено. ");
                    System.out.print("\n Композицію було успішно відновлено.\n");
                    return;
                }

                log.fine(" Було виконано спробу вийти за межі списку видалених (індекс неіснуючого елемента). ");
                System.out.print("\n Введіть номер існуючого елемента.\n");
            }
        }
    }
}