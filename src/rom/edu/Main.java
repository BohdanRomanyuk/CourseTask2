package rom.edu;
/* @author   Romanyuk Bohdan
   @project   CourseTask2
   @class  Main
   @version  1.0.0
   @since 19.04.2021 - 18.11
  */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class Main {

    public static void main(String[] args) throws IOException {


        // 1.1. Download a text about Harry Potter.

        String text = new String(Files.readAllBytes(Paths.get("C:/Users/Quizze/IdeaProjects/harry.txt")));


        // 1.3. Use RegEx.

        String cleanedText = text.replaceAll("\\.", "")
                .replaceAll("\\?", "")
                .replaceAll("'", "")
                .replaceAll("\"", "")
                .replaceAll("\\!", "")
                .replaceAll("�", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\\:", "")
                .replaceAll("\\.", "")
                .replaceAll("\\;", "")
                .replaceAll("--", "");

        // split array

        String[] words = cleanedText.split(" ");


        // 1.2. For each distinct word in the text calculate the number of occurrence. Upper case matters.


        // Using hashmap, counting the number of occurence

        Arrays.stream(words).collect(Collectors
                .groupingBy(Function.<String>identity(), HashMap::new, counting())).entrySet()
                .forEach(System.out:: println);


        // 1.4. Sort in the DESC mode by the number of occurrence


        Map<String, Long> result = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.<String>identity(),
                        counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (l,r) -> l,
                        LinkedHashMap::new));



        // 1.5. Find  the first 20 pairs.


        Map<String, Long> result1 = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.<String>identity(),
                        counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (l,r) -> l,
                        LinkedHashMap::new));


        // Creating String and converting results to String

        String twenty2 = result1.toString();


         /* 1.9.  Create a fine header for the file */



        // Creating file harry1.txt

        try {

            // Our Header

            String content = "Hello. You can find first 20 pairs key-value of glossary of Harry Potter \n"
                    + twenty2; // Creating header and adding content in variable


            // Creating file

            File f = new File("C:/Users/Quizze/IdeaProjects/CourseTask2/src/rom/edu/harry1.txt");

            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        /*                        RESULT
         *
         * Hello. You can find first 20 pairs key-value of glossary of Harry Potter
         * {the=3311, to=1845, and=1799, a=1577, of=1242, was=1178, he=1029, Harry=969, in=933, his=896,
         * it=804, said=789, you=734, had=697, on=617, at=581, that=580, I=537, him=495}
         *
         *                         RESULT*/


    }
}