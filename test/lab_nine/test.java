package lab_nine;
/**
 * @author : wqruan
 * @version : 1.0.0
 * @date : 2020/12/7 12:40
 */

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    @Test
    public void test() throws IOException {
        Scanner scanner = new Scanner(new File("test/lab_nine/test.txt"));
        String input = scanner.nextLine();
        String pattern = "abcd";
        ArrayList<Integer> res = KMP.search(input,pattern);
        ArrayList<Integer> truth = new ArrayList<>();
        int index = 0;
        while( ( index = input.indexOf(pattern, index) ) != -1 )
        {
            truth.add(index);
            index = index+pattern.length();

        }
        assertArrayEquals(truth.toArray(),res.toArray());
    }
}
