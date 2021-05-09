package client;

import com.SynalogikCodeChallenge.api.SynalogikFileApi;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by glennhealy on 09/05/2021.
 */
public class client {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a FilePath: ");
        String filePath = scanner.nextLine();

        new SynalogikFileApi().analyzeFile(filePath);
    }
}
