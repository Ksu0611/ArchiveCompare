import java.io.*;
import java.util.zip.*;
import java.io.File;
import java.util.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);     //для консольного ввода
        Map<java.lang.Long, String> archive1 = new HashMap<java.lang.Long, String>(); //для хранения данных об архиве1
        Map<java.lang.Long, String> archive2 = new HashMap<java.lang.Long, String>(); //для хранения данных об архиве2
        System.out.println("Введите путь до старой zip папки");
        String path1 = in.nextLine();
        System.out.println("Введите путь до новой zip папки");
        String path2 = in.nextLine();

        System.out.println("Введите путь до файла с результатом");
        String path3 = in.nextLine();

        System.out.println("Введите имя старой zip папки без расширения .zip!");
        System.out.println("Пример: archive1.zip -> вводим archive1");
        String name1 = in.nextLine();
        System.out.println("Введите имя новой zip папки без расширения .zip!");
        String name2 = in.nextLine();

        //считываем данные из архива в мап
        Read(archive1, path1, name1);
        Read(archive2, path2, name2);
        //сравниваем архивы
        Compare(archive1, archive2, path3);
    }

    /*Сравниваем два map с данными архивов и пишем в файл который лежит в пути path_result*/
    static void Compare(Map<Long, String> archive1, Map<java.lang.Long, String> archive2, String path_result) {
        File file = new File(path_result);
        try (PrintWriter pw = new PrintWriter(file)) {

            //проверка на удалённые файлы и переименование
            for (Map.Entry<java.lang.Long, String> item : archive1.entrySet()) {
                boolean iskeyhere = archive2.containsKey(item.getKey());
                boolean isvaluehere = archive2.containsValue(item.getValue());
                if ((!iskeyhere) & (!isvaluehere)) {
                    pw.println(item.getValue() + "  has been deleted");
                }
                if ((!iskeyhere) & (isvaluehere)) {
                    pw.println(item.getValue() + "  has been edited");
                }
                if (iskeyhere) {
                    if (isvaluehere) {
                    } else {
                        pw.println(item.getValue() + "  probably was renamed");
                    }
                }
            }

            //проверка на новые файлы
            for (Map.Entry<java.lang.Long, String> item : archive2.entrySet()) {
                boolean iskeyhere = archive1.containsKey(item.getKey());
                boolean isvaluehere = archive1.containsValue(item.getValue());
                if ((!iskeyhere) & (!isvaluehere)) {
                    pw.println(item.getValue() + "  was created");
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    /*считываем в map данные из архива*/
    static void Read(Map<java.lang.Long, String> archive1, String path, String name1) {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(path)))
        {
            ZipEntry entry;
            String name;
            long size;
            String dir = name1;
            int dirlength = dir.length() + 1;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName();// получим название файла
                size = entry.getSize();
                String name_crop = name.substring(dirlength);
                archive1.put(size, name_crop);
                zin.closeEntry();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }




}
