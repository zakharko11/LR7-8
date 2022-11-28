package Interface.commands;
import Content.Music;
import Interface.MenuItems;

import java.util.logging.Logger;
public class MenuHelp implements MenuItems {
    /**
     * Виконання команди вивід на екран меню команд
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        log.info(" Виклик методу з виведенням команд. ");
        System.out.print("\n Ця програма - список ваших музичних композицій. Ви можете створити та записати на диск збірку." +
                "\n Користуючись даними командами виберіть подальшу дію:" +
                "\n 'add' - додати нову композицію;" +
                "\n 'remove' - видалити певну композицію із списку;" +
                "\n 'restore' - відновити композицію із списку вилучених;" +
                "\n 'byStyle' - відсортувати композицію за стилем на основі ваших вподобань;" +
                "\n 'get' - отримання інформації з файлу;" +
                "\n 'save' - зберегти збірку до файлу;" +
                "\n 'byLen' - відсортувати за тривалістю;" +
                "\n 'show' - виводить усі композиції з вашого плейлиста;" +
                "\n 'sum' - виводить сумарну довжину всіх треків плейлиста;" +
                "\n 'removed' - видалити пісню;" +
                "\n 'delete' - виводить список видалених мелодій;" +
                "\n 'play' - відтворити відео з музикою за посиланням;" +
                "\n 'menu' - переглянути меню команд;" +
                "\n 'exit' - вихід з програми.\n");
    }
}
