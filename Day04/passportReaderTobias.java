import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class passportReaderTobias {
    public static void main(String[] args) throws IOException {
        BufferedReader in;
        in = new BufferedReader(new FileReader("/Users/tobias/Development/AdventOfCode2020/Day04/passportTobias"));

        List<PassportTobias> allPassports = new ArrayList<PassportTobias>();
        String str = "";
        String line = in.readLine();
        while(line != null)
        {
            if (line.isEmpty()) {
                allPassports.add(new PassportTobias(str));
                str = "";
            } else {
                str = str + line + " ";
            }
            // read next line
            line = in.readLine();
        }
        in.close();
        List<PassportTobias> validPassports = allPassports.stream().filter(PassportTobias::isValid).collect(Collectors.toList());
        System.out.println("Valid Passports: " + (validPassports.size() + 1));
    }
}
