import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdapterArrayTobias {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.stream(textInput().split("\n")).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> nums2 = Arrays.stream(textInput().split("\n")).map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("Puzzle 1: " + runA(nums));
        System.out.println("Puzzle 2: " + runB(nums2));
    }

    public static int runA(List<Integer> nums) {
        nums.add(0); // charger outlet
        List<Integer> collect = nums.stream().sorted(Integer::compare)
                .collect(Collectors.toList());
        collect.add(collect.get(collect.size() - 1) + 3);

        int oneDifference = 0;
        int threeDifference = 0;
        for (int index = 0; index < collect.size() - 1; index++) {
            int joltDifference = collect.get(index + 1) - collect.get(index);
            if (joltDifference == 1) {
                oneDifference++;
            } else if (joltDifference == 3) {
                threeDifference++;
            }
        }
        return threeDifference * oneDifference;
    }

    public static long runB(List<Integer> nums) {
        nums.add(0); // charging outlet
        nums.sort(Integer::compareTo);
        nums.add(nums.get(nums.size() - 1) + 3); // add final
        Map<Integer, Adapter> adapterMap = nums.stream().map(Adapter::new).collect(Collectors.toMap(adapter -> adapter.jolts, adapter -> adapter));
        adapterMap.values().forEach(adapter -> adapter.setPossibleConnections(adapterMap));
        return adapterMap.get(0).getNumPossibilities();
    }

    private static class Adapter {
        List<Adapter> possibleConnections = new ArrayList<>();
        Integer jolts;
        Long permutations;

        public Adapter(int jolts) {
            this.jolts = jolts;
        }

        public void setPossibleConnections(Map<Integer, Adapter> adapterMap) {
            IntStream.rangeClosed(1, 3).forEach(index -> {
                if( adapterMap.containsKey(jolts + index)) {
                    possibleConnections.add(adapterMap.get(jolts + index));
                }
            });
        }

        public Long getNumPossibilities() {
            if(permutations != null) {
                return permutations;
            }

            permutations = possibleConnections.stream()
                    .mapToLong(Adapter::getNumPossibilities)
                    .sum();

            if(possibleConnections.isEmpty()) {
                permutations = 1l;
            }

            return permutations;
        }
    }

    private static String textInput() {
        return "107\n" +
                "13\n" +
                "116\n" +
                "132\n" +
                "24\n" +
                "44\n" +
                "56\n" +
                "69\n" +
                "28\n" +
                "135\n" +
                "152\n" +
                "109\n" +
                "42\n" +
                "112\n" +
                "10\n" +
                "43\n" +
                "122\n" +
                "87\n" +
                "49\n" +
                "155\n" +
                "175\n" +
                "71\n" +
                "39\n" +
                "173\n" +
                "50\n" +
                "156\n" +
                "120\n" +
                "145\n" +
                "176\n" +
                "45\n" +
                "149\n" +
                "148\n" +
                "15\n" +
                "1\n" +
                "68\n" +
                "9\n" +
                "168\n" +
                "131\n" +
                "150\n" +
                "59\n" +
                "83\n" +
                "167\n" +
                "3\n" +
                "169\n" +
                "6\n" +
                "123\n" +
                "174\n" +
                "81\n" +
                "138\n" +
                "72\n" +
                "157\n" +
                "144\n" +
                "65\n" +
                "75\n" +
                "33\n" +
                "19\n" +
                "140\n" +
                "160\n" +
                "16\n" +
                "57\n" +
                "93\n" +
                "90\n" +
                "8\n" +
                "58\n" +
                "98\n" +
                "130\n" +
                "141\n" +
                "114\n" +
                "84\n" +
                "29\n" +
                "22\n" +
                "94\n" +
                "113\n" +
                "129\n" +
                "108\n" +
                "36\n" +
                "14\n" +
                "115\n" +
                "102\n" +
                "151\n" +
                "78\n" +
                "139\n" +
                "170\n" +
                "82\n" +
                "2\n" +
                "70\n" +
                "126\n" +
                "101\n" +
                "25\n" +
                "62\n" +
                "95\n" +
                "104\n" +
                "23\n" +
                "163\n" +
                "32\n" +
                "103\n" +
                "121\n" +
                "119\n" +
                "48\n" +
                "166\n" +
                "7\n" +
                "53";
    }
}
