package Content;

import Content.Styles.Electronic;
import Content.Styles.HipHop;
import Content.Styles.Pop;
import Content.Styles.Rock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class Music {
    protected String title = "Untitled";
    protected String author = "Unknown";
    protected String genre = "Unknown";
    protected String link = null;
    protected MusicDuration duration = new MusicDuration();
    protected MusicDuration durationSum = new MusicDuration();
    public ArrayList<Music> list = new ArrayList();
    public ArrayList<Music> dropped = new ArrayList();

    public Music() {
    }

    public void addNewComposition(MusicStyle style, String title, String author, String len, Logger log, boolean test) {
        if (test) {
            title = "Небо";
            author = "SadSvit";
            len = "00:05:10";
        }

        Object composition;
        switch (style) {
            case ROCK:
                composition = new Rock(title, author, len, log);
                break;
            case POP:
                composition = new Pop(title, author, len, log);
                break;
            case HIPHOP:
                composition = new HipHop(title, author, len, log);
                break;
            case ELECTRO:
                composition = new Electronic(title, author, len, log);
                break;
            default:
                composition = new Music();
        }

        this.list.add((Music) composition);
        this.durationSum.setSec(this.getPlaylistDuration(log), log);
    }

    public void dropFromList(int numb) {
        this.dropped.add((Music)this.list.get(numb - 1));
        this.list.remove(numb - 1);
    }

    public void restoreSong(int numb) {
        this.list.add((Music)this.dropped.get(numb - 1));
        this.dropped.remove(numb - 1);
    }

    public void deleteSong(int numb) {
        this.dropped.remove(numb - 1);
    }

    public String getGenre() {
        return this.genre;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    long getPlaylistDuration(Logger log) {
        log.info(" Було визначено сумарну довжину пісень у плейлисті. ");
        return this.list.stream().mapToLong((x) -> {
            return x.duration.Sec();
        }).sum();
    }

    public MusicDuration getSum() {
        return this.durationSum;
    }

    public void printMusicList(Logger log) {
        if (this.IsAnySong(log)) {
            for(int n = 0; n < this.list.size(); ++n) {
                System.out.print("\n" + (n + 1) + "." + this.list.get(n));
            }

            log.fine(" Список музики успішно виведено. ");
            System.out.print("\n");
        }
    }

    public void printRemoved(Logger log) {
        if (this.IsAnyRemoved(log)) {
            for(int n = 0; n < this.dropped.size(); ++n) {
                System.out.print("\n" + (n + 1) + "." + this.dropped.get(n));
            }

            log.fine(" Список видалених композицій успішно виведено. ");
            System.out.print("\n");
        }
    }

    public boolean IsAnySong(Logger log) {
        if (this.list.size() == 0) {
            System.out.print("\n На даний момент список пісень є порожнім.\n");
            return false;
        } else {
            log.fine(" Було здійснено перевірку наявності композицій в головному списку. ");
            return true;
        }
    }

    public boolean IsAnyRemoved(Logger log) {
        if (this.dropped.size() == 0) {
            System.out.print("\n На даний момент немає видалених пісень.\n");
            return false;
        } else {
            log.fine(" Було здійснено перевірку наявності композицій в списку вилучених. ");
            return true;
        }
    }

    public boolean GetFromFile(String filename, Logger log) throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            while(true) {
                String style = bufferedReader.readLine();
                if (style.equals("#")) {
                    return true;
                }

                Object composition;
                switch (style) {
                    case "R":
                        composition = new Rock();
                        ((Music)composition).genre = "Rock";
                        log.info(" З файла було успішно зчитано композицію стиля \"ROCK\". ");
                        break;
                    case "H":
                        composition = new HipHop();
                        ((Music)composition).genre = "HipHop";
                        log.info(" З файла було успішно зчитано композицію стиля \"HIPHOP\". ");
                        break;
                    case "P":
                        composition = new Pop();
                        ((Music)composition).genre = "Pop";
                        log.info(" З файла було успішно зчитано композицію стиля \"POP\". ");
                        break;
                    case "E":
                        composition = new Electronic();
                        ((Music)composition).genre = "Electronic";
                        log.info(" З файла було успішно зчитано композицію стиля \"ELECTRONIC\". ");
                        break;
                    default:
                        composition = new Music();
                        log.warning(" Виникла критична помилка, у пісні невизначено стиль. ");
                }

                ((Music)composition).title = bufferedReader.readLine();
                ((Music)composition).author = bufferedReader.readLine();
                ((Music)composition).duration.setDuration(LocalTime.parse(bufferedReader.readLine()), log);
                if (bufferedReader.readLine().equals("1")) {
                    ((Music)composition).link = bufferedReader.readLine();
                }

                this.list.add((Music) composition);
                this.durationSum.setSec(this.getPlaylistDuration(log), log);
            }
        } catch (FileNotFoundException var8) {
            System.out.print(" Файл не знайдено.\n");
            log.fine(" Виникла помилка: " + filename + " не знайдено. ");
            return false;
        } catch (IOException var9) {
            log.warning(" Критична помилка не вдалося зчитати строку з файлу. ");

        }
        return false;
    }

    public void SaveToFile(String filename, Logger log) throws Exception {
        if (!filename.contains(".txt")) {
            filename = filename + ".txt";
        }

        try {
            FileWriter writer = new FileWriter(filename);
            log.info(" Буде створено або редаговано файл під назвою \"" + filename + "\". ");
            Iterator var9 = this.list.iterator();

            while(var9.hasNext()) {
                Music obj = (Music)var9.next();
                switch (obj.genre) {
                    case "Rock":
                        writer.write("R");
                        break;
                    case "HipHop":
                        writer.write("H");
                        break;
                    case "Pop":
                        writer.write("P");
                        break;
                    case "Electronic":
                        writer.write("E");
                }

                String var10001 = obj.title;
                writer.write("\n" + var10001 + "\n" + obj.author + "\n" + obj.duration.getDuration() + "\n");
                if (obj.link != null) {
                    writer.write("1\n" + obj.link + "\n");
                } else {
                    writer.write("0\n");
                }
            }

            writer.write("#");
            writer.close();
            log.info(" Успішно занесено усі дані у файл \"" + filename + "\". ");
        } catch (IOException var8) {
            log.warning(" Критична помилка у записі в файл. ");

        }
    }

    public MusicDuration getDuration() {
        return this.duration;
    }

    public void ByLen(long min, long max, Logger log) {
        Music Result = new Music();
        Iterator var7 = this.list.iterator();

        while(var7.hasNext()) {
            Music ptr = (Music)var7.next();
            if (ptr.getDuration().Sec() > min && ptr.getDuration().Sec() < max) {
                Result.list.add(ptr);
            }
        }

        System.out.print("\n Отримано даний список пісень в межах даної тривалості:\n");
        Result.printMusicList(log);
    }

    public String toString() {
        String var10000;
        if (this.link != null) {
            var10000 = this.author;
            return " Song: " + var10000 + " - \"" + this.title + "\" (" + this.genre + "): " + this.duration.getDuration() + "\n   Link:" + this.link + "\n";
        } else {
            var10000 = this.author;
            return " Song: " + var10000 + " - \"" + this.title + "\" (" + this.genre + "): " + this.duration.getDuration() + "\n";
        }
    }
    public static enum MusicStyle {
        ROCK,
        HIPHOP,
        POP,
        ELECTRO;

        private MusicStyle() {
        }
    }
}
