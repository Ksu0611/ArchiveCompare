# ArchiveCompare
 Сравнивает содержимое двух zip архивов   

 функция Read(Map<java.lang.Long, String> archive, String path, String name1) нужна для того чтобы считать из зип архива, который лежит в пути path и его имя name1, имена и размеры файлов  и записать их в map archive   

 функция Compare(Map<Long, String> archive1, Map<java.lang.Long, String> archive2, String path_result) принимает два map с данными архивов и пишет результат сравнения в файл лежащий в пути path_result   
