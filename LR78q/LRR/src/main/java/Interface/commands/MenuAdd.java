package Interface.commands;

import Content.Music;
import Content.Music.MusicStyle;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuAdd implements MenuItems {
    public MenuAdd() {
    }

    public void executeCommand(Music music, Logger log) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("\n Виберіть стиль пісні, яку ви хочете додати (1 - Rock, 2 - HipHop, 3 - Pop, 4 - Electronic): ");
            int style = scanner.nextInt();
            if (style > 0 && style < 5) {
                scanner.nextLine();
                System.out.print("\n Введіть назву композиції: ");
                String title = scanner.nextLine();
                System.out.print("\n Введіть ім'я автора: ");
                String author = scanner.nextLine();
                System.out.print("\n Введіть довжину треку (формат hh:mm:ss): ");
                String len = scanner.next();
                log.info(" Успішно зчитано усю необхідну інформацію про композицію. ");
                switch (style) {
                    case 1:
                        music.addNewComposition(MusicStyle.ROCK, title, author, len, log, false);
                        log.info(" Було успішно додано композицію стилю \"ROCK\".");
                        break;
                    case 2:
                        music.addNewComposition(MusicStyle.HIPHOP, title, author, len, log, false);
                        log.info(" Було успішно додано композицію стилю \"HIPHOP\".");
                        break;
                    case 3:
                        music.addNewComposition(MusicStyle.POP, title, author, len, log, false);
                        log.info(" Було успішно додано композицію стилю \"POP\".");
                        break;
                    case 4:
                        music.addNewComposition(MusicStyle.ELECTRO, title, author, len, log, false);
                        log.info(" Було успішно додано композицію стилю \"ELECTRONIC\".");
                        break;
                    default:
                        System.out.print("\n Виникла помилка.\n");
                        log.warning(" Виникла критична помилка: у список не було додано композицію.");
                }

                System.out.print("\n Композицію було успішно додано до плейлиста.\n");
                return;
            }

            log.fine(" Було виконано спробу вказати неіснуючий стиль. ");
            System.out.print("\n Будь ласка повторіть введення.\n");
        }
    }
}
