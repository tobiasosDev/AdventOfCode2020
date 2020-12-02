import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordTobias {
    int minLength;
    int maxLenght;
    char character;
    String password;

    public PasswordTobias(int minLength, int maxLenght, char character, String password) {
        this.minLength = minLength;
        this.maxLenght = maxLenght;
        this.character = character;
        this.password = password;
    }

    public PasswordTobias(String value) {
        value = value.replace("\n", "").replace("\r", "");
        Pattern pattern = Pattern.compile("^(\\d*-\\d*)(.*:)(.*)$");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find() && matcher.matches()) {
            String[] minMaxLenght = matcher.group(1).split("-");
            this.minLength = Integer.parseInt(minMaxLenght[0]);
            this.maxLenght = Integer.parseInt(minMaxLenght[1]);
            this.character = matcher.group(2).replaceAll(" ", "").replaceAll(":", "").charAt(0);
            this.password = matcher.group(3).replaceAll(" ", "");
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLenght() {
        return maxLenght;
    }

    public void setMaxLenght(int maxLenght) {
        this.maxLenght = maxLenght;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
