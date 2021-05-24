import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main (String args []) {
        String a = "a a a g d d c c a";

        List<String> arrayString = Arrays.asList(a.split("\\b"));
        arrayString = arrayString.stream().map(s -> s.trim()).collect(Collectors.toList());
        arrayString.removeIf(s -> s.equals(" ") || s.equals(""));

        int maxSize = 0;
        int count= 0;
        System.out.println(arrayString);

        for (String character : arrayString) {
            System.out.println(character);
            boolean found = true;
            int indexOf = 0;

            while (found) {
                indexOf = a.indexOf(character, indexOf);
                if (indexOf == -1) {
                    found = false;
                }
                indexOf++;
                count++;
            }
            if (count > maxSize) {
                maxSize = count;
                System.out.println("For Character " + character + " the count is:" +  count );
                count = 0;

            }
        }
        System.out.println("The highest number of occurences is: " + maxSize);
    }
}
