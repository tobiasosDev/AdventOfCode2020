import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordPhilosophyTobias {
    public static void main(String[] args) {
        List<String> result = null;
        try (Stream<String> lines = Files.lines(Paths.get("/home/dev/development/projects/AdventOfCode2020/Day02/passwordsTobias"))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PasswordTobias> passwordTobiasList = result.stream().map(s -> new PasswordTobias(s)).collect(Collectors.toList());
        matchByMinMax(passwordTobiasList);
        matchByCharIndex(passwordTobiasList);
    }

    private static void matchByCharIndex(List<PasswordTobias> passwordTobiasList) {
        int validPasswords = 0;
        for (PasswordTobias passwordTobias : passwordTobiasList) {
            String password = passwordTobias.getPassword();
            char passwordChar = passwordTobias.getCharacter();
            if (password.charAt(passwordTobias.getMinLength()-1) == passwordChar ^ password.charAt(passwordTobias.getMaxLenght()-1) == passwordChar) {
                validPasswords++;
            }
        }
        System.out.println("Valid Passwords by Index: " + validPasswords);
    }

    private static void matchByMinMax(List<PasswordTobias> passwordTobiasList) {
        int validPasswords = 0;
        for (PasswordTobias passwordTobias : passwordTobiasList) {
            long count = passwordTobias.getPassword().chars().filter(ch -> ch == passwordTobias.getCharacter()).count();
            if (count >= passwordTobias.getMinLength() && count <= passwordTobias.getMaxLenght()) {
                validPasswords++;
            }
        }
        System.out.println("Valid Passwords: " + validPasswords);
    }
}
