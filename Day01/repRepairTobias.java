import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class repRepairTobias {

    public static void main(String[] args) {
        List<String> result = null;
        try (Stream<String> lines = Files.lines(Paths.get("/home/dev/development/projects/AdventOfCode2020/Day01/input1Tobias"))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert result != null;
        getByPairOfThree(result);
        getResultByPair(result);
    }

    private static boolean getByPairOfThree(List<String> result) {
        List<Integer> resultAsInt = result.stream().map(Integer::parseInt).collect(Collectors.toList());
        for (Integer entry : resultAsInt) {
            Integer differenceAsInt = 2020 - entry;
            List<Integer> entryTwoList = resultAsInt.stream().filter(e -> e < differenceAsInt).collect(Collectors.toList());
            for (Integer entryTwo : entryTwoList) {
                Integer differenceTwoAsInt = differenceAsInt - entryTwo;
                List<Integer> entryThreeList = resultAsInt.stream().filter(e -> e.equals(differenceTwoAsInt)).collect(Collectors.toList());
                if (!entryThreeList.isEmpty()) {
                    entryThreeList.get(0);
                    System.out.println("Value 1: " + entry);
                    System.out.println("Value 2: " + entryTwo);
                    System.out.println("Value 3: " + entryThreeList.get(0));
                    System.out.println("Value Sum: " + (entry + entryTwo + entryThreeList.get(0)));
                    System.out.println("Multiply " + entry + " * " + entryTwo + " * " + entryThreeList.get(0) + ": " + (entry * entryTwo * entryThreeList.get(0)));
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean getResultByPair(List<String> result) {
        for (String entry : result) {
            int entryAsInt = Integer.parseInt(entry);
            String differenceToSearch = String.valueOf(2020 - entryAsInt);
            Optional<String> matchedResult = result.stream().filter(s -> s.equals(differenceToSearch)).findFirst();
            if (matchedResult.isPresent()) {
                System.out.println("Value 1: " + entry);
                System.out.println("Value 2: " + matchedResult.get());
                System.out.println("Multiply " + entry + " * " + matchedResult.get() + ": " + (entryAsInt * Integer.parseInt(matchedResult.get())));
                return true;
            }
        }
        return false;
    }
}
