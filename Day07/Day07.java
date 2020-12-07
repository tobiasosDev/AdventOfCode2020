import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day07 {
    static class BagRule {
        String color;
        HashMap<String, Integer> containedBags = new HashMap<>();

        BagRule(String line) {
            String cleanLine = line.replace(" bags contain ", ":").
                    replace("bags, ", "").
                    replace("bag, ", "").
                    replace(" bags.", "").
                    replace("bag.", "");
            String[] split = cleanLine.split(":");
            color = split[0];
            String rest = split[1].strip();
            if (rest.contains("no other")) {
                return;
            }
            String[] singles = rest.split(" ");
            for (int i = 0; i < singles.length; i += 3) {
                int amount = Integer.parseInt(singles[i]);
                String color = singles[i + 1] + " " + singles[i + 2];
                containedBags.put(color, amount);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BagRule)) return false;
            BagRule bagRule = (BagRule) o;
            return Objects.equals(color, bagRule.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }
    }

    public static void findMatchingRule(List<BagRule> totalRules, List<BagRule> matchedRules, String color) {
        totalRules.
                forEach(bagRule -> {
                    if (bagRule.containedBags.containsKey(color) &&
                            !matchedRules.contains(bagRule)) {
                        matchedRules.add(bagRule);
                        findMatchingRule(totalRules, matchedRules, bagRule.color);
                    }
                });
    }

    public static long part1(List<BagRule> bagRules) {
        List<BagRule> matchedRules = new ArrayList<>();
        findMatchingRule(bagRules, matchedRules, "shiny gold");
        return matchedRules.size();
    }

    public static int countChildrenBags(List<BagRule> totalRules, String color) {
        return totalRules.stream().filter(r -> r.color.equals(color)).findFirst().orElse(null).
                containedBags.entrySet().stream().
                        map(e -> e.getValue() + (e.getValue() * countChildrenBags(totalRules, e.getKey()))).
                        reduce(Integer::sum).orElse(0);
    }

    public static long part2(List<BagRule> bagRules) {
        return countChildrenBags(bagRules, "shiny gold");
    }

    public static void main(String[] args) throws IOException {
        List<String> result = null;
        try (Stream<String> lines = Files.lines(Paths.get("/home/dev/development/projects/AdventOfCode2020/Day07/inputDay07Tobias"))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BagRule> bagRules = result.stream().map(BagRule::new).collect(Collectors.toList());
        System.out.println("Answer 1: " + part1(bagRules));
        System.out.println("Answer 2: " + part2(bagRules));
    }
}
