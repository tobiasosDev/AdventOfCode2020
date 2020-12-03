import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class trajectoryTobias {
    public static void main(String[] args) {
        List<String> result = null;
        try (Stream<String> lines = Files.lines(Paths.get("/home/dev/development/projects/AdventOfCode2020/Day03/mapInputTobias"))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        challengeOne(result);
        challengeTwo(result);

    }

    private static void challengeOne(List<String> result) {
        System.out.println("Trees Challenge One: " + treesCrossed(result, 3, 1));
    }

    private static void challengeTwo(List<String> result) {
        int countedTrees1 = treesCrossed(result, 1, 1);
        int countedTrees2 = treesCrossed(result, 3, 1);
        int countedTrees3 = treesCrossed(result, 5, 1);
        int countedTrees4 = treesCrossed(result, 7, 1);
        int countedTrees5 = treesCrossed(result, 1, 2);
        long multiTrees = (long) countedTrees1 * countedTrees2 * countedTrees3 * countedTrees4 * countedTrees5;
        System.out.println("Multiplied Trees: " + multiTrees);
    }

    private static int treesCrossed(List<String> result, int right, int down) {
        int positionInLine = 0;
        int treesCountered = 0;
        for (int i = down; i < result.size(); i = i + down) {
            String resultLine = result.get(i);
            positionInLine = positionInLine + right;
            if (resultLine.length() <= positionInLine) {
                positionInLine = positionInLine - resultLine.length();
            }
            char mapPosition = resultLine.charAt(positionInLine);
            if (mapPosition == '#') {
                treesCountered++;
            }
        }
        System.out.println("Trees Countered: " + treesCountered);
        return treesCountered;
    }
}
