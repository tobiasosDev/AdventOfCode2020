package days;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Day4_Passport_Processing {
    public static void main(String[] args) {
        System.out.println("answer A: " + runA(getInput()));
        System.out.println("answer B: " + runB(getInput()));
    }

    public static long runA(String input) {
        return Arrays.stream(input.split("\n\n"))
                .filter(Day4_Passport_Processing::hasAllrequiredFields)
                .count();
    }

    public static boolean hasAllrequiredFields(String passport) {
        String[] data = passport.replace("\n", " ").split(" ");
        if(data.length == 8) return true;
        if(data.length == 6) return false;
        return passport.contains("byr") && passport.contains("iyr") && passport.contains("eyr") && passport.contains("hgt")
                && passport.contains("hcl") && passport.contains("ecl") && passport.contains("pid");
    }

    public static long runB(String input) {
        return Arrays.stream(input.split("\n\n"))
                .filter(Day4_Passport_Processing::hasAllrequiredFields)
                .filter(Day4_Passport_Processing::allFieldsCorrect)
                .count();
    }

    public static boolean allFieldsCorrect(String passport) {
        String[] data = passport.replace("\n", " ").split(" ");
        return Arrays.stream(data).noneMatch(Day4_Passport_Processing::fieldNotCorrect);

    }

    public static boolean fieldNotCorrect(String field) {
        String[] split = field.split(":");
        switch (split[0]) {
            case "byr" : return !isValidNumber(parseInt(split[1]), 1920, 2002);
            case "iyr" : return !isValidNumber(parseInt(split[1]), 2010, 2020);
            case "eyr" : return !isValidNumber(parseInt(split[1]), 2020, 2030);
            case "hgt" : return !isValidHeight(split[1]);
            case "hcl" : return !isValidHairColor(split[1]);
            case "ecl" : return !isValidEyeColor(split[1]);
            case "pid" : return !isValidPassportId(split[1]);
            case "cid" : return false;
        }
        return true;
    }

    /**
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     */
    public static boolean isValidPassportId(String passportId) {
        if (passportId.length() != 9) {
            return false;
        }
        try {
            parseInt(passportId);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     */
    public static boolean isValidEyeColor(String eyeColor) {
        return "amb".equals(eyeColor) || "blu".equals(eyeColor) || "brn".equals(eyeColor) || "gry".equals(eyeColor)
                || "grn".equals(eyeColor) || "hzl".equals(eyeColor) || "oth".equals(eyeColor);
    }

    /**
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     */
    public static boolean isValidHairColor(String hairColor) {
        if(hairColor.length() != 7 || hairColor.charAt(0) != '#') {
            return false;
        }
        try {
            Long.parseLong(hairColor.substring(1), 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * hgt (Height) - a number followed by either cm or in:
     * If cm, the number must be at least 150 and at most 193.
     * If in, the number must be at least 59 and at most 76.
     */
    public static boolean isValidHeight(String height) {
        if (height.contains("cm")) {
            return isValidNumber(parseInt(height.replace("cm", "")), 150, 193);
        }
        return isValidNumber(parseInt(height.replace("in", "")), 59, 76);
    }

    public static boolean isValidNumber(int num, int min, int max) {
        return num >= min && num <= max;
    }

    public static String getInput() {
        return "byr:2024 iyr:2016\n" +
                "eyr:2034 ecl:zzz pid:985592671 hcl:033b48\n" +
                "hgt:181 cid:166\n" +
                "\n" +
                "hgt:66cm\n" +
                "pid:152cm\n" +
                "hcl:cfb18a eyr:1947\n" +
                "byr:2020 ecl:zzz iyr:2029\n" +
                "\n" +
                "ecl:gry hcl:#888785 eyr:2023 cid:63\n" +
                "iyr:2019 hgt:177cm\n" +
                "pid:656793259\n" +
                "\n" +
                "pid:#5e832a\n" +
                "ecl:dne hcl:#7d3b0c byr:2018 eyr:1928 hgt:61cm iyr:1936 cid:241\n" +
                "\n" +
                "hcl:#888785 ecl:oth eyr:2025\n" +
                "pid:597580472\n" +
                "iyr:2017 hgt:187cm byr:1957 cid:247\n" +
                "\n" +
                "eyr:2029 cid:145 iyr:2026 pid:178cm hgt:162in ecl:gry\n" +
                "hcl:#a5d09f byr:2002\n" +
                "\n" +
                "eyr:2024\n" +
                "ecl:amb\n" +
                "pid:349191561 iyr:2018 hgt:156cm byr:1972\n" +
                "hcl:#341e13\n" +
                "\n" +
                "byr:1998 pid:408617933\n" +
                "hcl:#fffffd hgt:193cm eyr:2021 ecl:brn iyr:2013\n" +
                "\n" +
                "eyr:1964 byr:2026\n" +
                "hcl:#cfa07d hgt:154cm pid:175cm cid:85 iyr:2018 ecl:gmt\n" +
                "\n" +
                "hgt:161cm\n" +
                "pid:021086946\n" +
                "iyr:2020 ecl:blu eyr:2024\n" +
                "byr:1921\n" +
                "\n" +
                "pid:173cm cid:235 eyr:1935 iyr:1952 ecl:utc byr:2015 hgt:187\n" +
                "hcl:6239ed\n" +
                "\n" +
                "iyr:2015 hcl:#18171d pid:2691566700 ecl:gmt hgt:68cm eyr:2032 byr:2016\n" +
                "cid:341\n" +
                "\n" +
                "hcl:#c0946f\n" +
                "iyr:2010 pid:941290886 eyr:2023 ecl:brn\n" +
                "hgt:190cm\n" +
                "byr:1927\n" +
                "\n" +
                "pid:423210256 eyr:2026 iyr:2011 byr:1926 hcl:#efcc98\n" +
                "ecl:grn\n" +
                "hgt:185cm cid:135\n" +
                "\n" +
                "ecl:blu eyr:2030\n" +
                "byr:1996 hgt:154cm iyr:2019 pid:809421142\n" +
                "hcl:#b6652a\n" +
                "cid:250\n" +
                "\n" +
                "hcl:39615c iyr:1934 byr:2009 pid:7752456272 hgt:191cm\n" +
                "eyr:2024 ecl:#5b7a58\n" +
                "\n" +
                "iyr:1938 hgt:160in hcl:#623a2f eyr:2031\n" +
                "ecl:grn pid:169cm byr:1964\n" +
                "\n" +
                "ecl:grt byr:2004 hcl:026249 hgt:176in iyr:1933 eyr:2023\n" +
                "pid:176cm\n" +
                "\n" +
                "hgt:167cm\n" +
                "pid:023312072 ecl:oth iyr:2018 hcl:#866857 eyr:2024 byr:1994\n" +
                "\n" +
                "hcl:#c0946f\n" +
                "ecl:blu iyr:2016\n" +
                "eyr:2023 hgt:193cm\n" +
                "pid:190756361 byr:1943\n" +
                "cid:306\n" +
                "\n" +
                "iyr:2013 hgt:179cm ecl:oth\n" +
                "cid:290 eyr:2020 pid:953948851\n" +
                "hcl:#6b5442\n" +
                "byr:1973\n" +
                "\n" +
                "pid:795414848\n" +
                "cid:51\n" +
                "ecl:oth iyr:2020 hcl:#ceb3a1 eyr:2026 byr:1995 hgt:186cm\n" +
                "\n" +
                "iyr:2020\n" +
                "byr:1993 hgt:159cm pid:905121332\n" +
                "ecl:brn eyr:2023\n" +
                "hcl:#341e13\n" +
                "\n" +
                "ecl:hzl iyr:2017 eyr:2020 hcl:z byr:2003 hgt:163\n" +
                "\n" +
                "eyr:2038\n" +
                "cid:342 hgt:116 iyr:2019 hcl:73db63 byr:1973 ecl:gry pid:468207739\n" +
                "\n" +
                "pid:022772899 eyr:2040 hgt:181cm\n" +
                "ecl:dne\n" +
                "hcl:#866857\n" +
                "byr:1931 iyr:2012\n" +
                "\n" +
                "pid:36127238 eyr:2038 cid:227 iyr:1985 hgt:103 byr:2004\n" +
                "hcl:z ecl:#45a680\n" +
                "\n" +
                "hgt:183cm\n" +
                "iyr:2013 byr:1933 cid:112\n" +
                "eyr:2024 pid:795616425\n" +
                "hcl:#7d3b0c\n" +
                "ecl:gry\n" +
                "\n" +
                "cid:236 byr:1985 hgt:160cm\n" +
                "hcl:#6b5442 eyr:2028\n" +
                "ecl:gmt pid:277781460 iyr:2018\n" +
                "\n" +
                "ecl:amb\n" +
                "byr:1948 pid:750948701 eyr:2025 iyr:2013 hgt:62in hcl:#7d3b0c\n" +
                "\n" +
                "eyr:2029 iyr:2020 pid:385941629 byr:1998 hgt:153cm cid:321\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "byr:1966 hgt:69in iyr:2012\n" +
                "pid:423254216\n" +
                "eyr:2020 hcl:#602927 ecl:blu\n" +
                "\n" +
                "byr:2004 pid:14257358 cid:338 iyr:2010\n" +
                "ecl:gry\n" +
                "hcl:#602927\n" +
                "hgt:152cm\n" +
                "\n" +
                "ecl:gry eyr:2027 hcl:#341e13\n" +
                "iyr:2018 pid:298762877 hgt:162cm byr:1968\n" +
                "\n" +
                "hcl:#ceb3a1\n" +
                "iyr:2019 ecl:brn eyr:2020 pid:575413143 byr:2030 hgt:185cm\n" +
                "cid:159\n" +
                "\n" +
                "eyr:2025 pid:628251539 ecl:blu byr:1953 hgt:63in hcl:#18171d iyr:2016\n" +
                "\n" +
                "pid:973247326 byr:1925 hcl:#341e13 ecl:grn eyr:2028 hgt:156cm\n" +
                "iyr:2014\n" +
                "\n" +
                "byr:1957 hgt:154cm\n" +
                "hcl:#7d3b0c\n" +
                "pid:062760305 eyr:2023 ecl:brn\n" +
                "iyr:2012\n" +
                "\n" +
                "ecl:brn eyr:2029\n" +
                "cid:250\n" +
                "pid:477431667 hgt:177cm\n" +
                "byr:1993 iyr:2020 hcl:#ceb3a1\n" +
                "\n" +
                "pid:155471269 iyr:2010 byr:1985 hgt:169cm eyr:2026 hcl:#694b7d cid:337 ecl:gry\n" +
                "\n" +
                "ecl:grt hgt:152in byr:2012\n" +
                "pid:#b9d022 iyr:2030\n" +
                "eyr:2037 hcl:z cid:201\n" +
                "\n" +
                "hcl:9270b3 eyr:2028\n" +
                "hgt:71cm iyr:2017 pid:#6c7af7 ecl:grn byr:1971\n" +
                "\n" +
                "pid:#0ab7e1 hgt:166cm byr:1929\n" +
                "cid:289\n" +
                "hcl:#623a2f iyr:2005\n" +
                "ecl:amb\n" +
                "\n" +
                "pid:967754435\n" +
                "hgt:175cm hcl:#341e13 ecl:utc iyr:2023 eyr:1949 byr:1988\n" +
                "\n" +
                "byr:1942 ecl:brn eyr:2023 pid:898126666 hgt:182cm cid:138\n" +
                "hcl:#866857\n" +
                "iyr:2010\n" +
                "\n" +
                "eyr:2027 iyr:2019 byr:1953 pid:760664328 hcl:#b6652a\n" +
                "ecl:grn hgt:155cm\n" +
                "\n" +
                "iyr:2018\n" +
                "ecl:brn hcl:#18171d pid:953129359 hgt:167cm eyr:2030 byr:1974\n" +
                "\n" +
                "cid:77\n" +
                "pid:975070417 hcl:#b6652a ecl:amb iyr:2013 byr:1995\n" +
                "eyr:2025 hgt:184cm\n" +
                "\n" +
                "hgt:168cm\n" +
                "eyr:2026 byr:1938 ecl:gry pid:037135813 hcl:#6b5442\n" +
                "iyr:2010\n" +
                "\n" +
                "iyr:2017 hcl:4cc74b byr:1980 hgt:176in\n" +
                "pid:460622356 ecl:xry eyr:2030\n" +
                "\n" +
                "hcl:z\n" +
                "ecl:oth pid:872514537 hgt:175in eyr:1984 iyr:2011 cid:145 byr:2024\n" +
                "\n" +
                "iyr:2014 ecl:blu\n" +
                "pid:332729196 hgt:63in\n" +
                "eyr:2020\n" +
                "hcl:#602927\n" +
                "\n" +
                "hcl:#7d3b0c pid:369981570 byr:1996 ecl:gry\n" +
                "eyr:2024 iyr:2017\n" +
                "\n" +
                "pid:446117373 hgt:158cm\n" +
                "iyr:2010 ecl:gry eyr:2024 hcl:#e54715\n" +
                "\n" +
                "cid:341 pid:842156559\n" +
                "hgt:167cm hcl:#602927 byr:1939 ecl:amb iyr:2016\n" +
                "\n" +
                "byr:2017\n" +
                "cid:176\n" +
                "ecl:#9e42ae iyr:2029 pid:8965345 eyr:2038 hcl:z\n" +
                "hgt:116\n" +
                "\n" +
                "eyr:2027 iyr:2014 hcl:#ceb3a1\n" +
                "cid:280 pid:871521406 ecl:oth byr:1957 hgt:166cm\n" +
                "\n" +
                "cid:303 iyr:2019\n" +
                "eyr:2021 pid:579593282 ecl:gry hcl:011a46 byr:1981\n" +
                "hgt:184cm\n" +
                "\n" +
                "iyr:2015 byr:1936 cid:61 ecl:brn pid:307248029 hgt:172cm hcl:#18171d eyr:2029\n" +
                "\n" +
                "pid:708687506 ecl:brn hcl:#602927\n" +
                "iyr:2014 byr:1987\n" +
                "hgt:193cm\n" +
                "eyr:2021\n" +
                "\n" +
                "ecl:gry\n" +
                "iyr:2015 hcl:#efcc98 byr:1950\n" +
                "cid:150 eyr:2023 pid:569864726\n" +
                "hgt:161cm\n" +
                "\n" +
                "byr:1920 hcl:#602927 iyr:2018\n" +
                "ecl:gry eyr:2020\n" +
                "hgt:192cm pid:020336420\n" +
                "\n" +
                "hcl:#539335 byr:1949\n" +
                "hgt:152cm\n" +
                "cid:95 pid:002018679\n" +
                "eyr:2030 iyr:2015 ecl:brn\n" +
                "\n" +
                "hcl:#b6652a eyr:2023\n" +
                "pid:515291463 iyr:2013 byr:1990\n" +
                "ecl:blu hgt:160cm\n" +
                "\n" +
                "eyr:2030 iyr:2012\n" +
                "ecl:grn pid:416049447 hgt:188cm\n" +
                "hcl:#a97842 byr:1976\n" +
                "\n" +
                "iyr:2017 hgt:61in ecl:oth byr:2001 eyr:2028\n" +
                "hcl:#733820\n" +
                "pid:078720467\n" +
                "\n" +
                "eyr:2021 hgt:151cm iyr:2020\n" +
                "hcl:#efcc98 ecl:oth\n" +
                "byr:1985\n" +
                "pid:876231883\n" +
                "\n" +
                "iyr:2019 hcl:#ceb3a1 hgt:156in\n" +
                "eyr:2004 pid:093465895 ecl:amb byr:2026\n" +
                "\n" +
                "pid:103964845 hgt:178cm cid:320 ecl:oth\n" +
                "hcl:#fffffd byr:2002 eyr:2021 iyr:2019\n" +
                "\n" +
                "ecl:brn byr:1920 pid:499363957\n" +
                "cid:217 hcl:#341e13 hgt:164cm\n" +
                "iyr:2017 eyr:2028\n" +
                "\n" +
                "ecl:brn pid:5837609946 iyr:2011\n" +
                "byr:1971 eyr:2030 hgt:189cm\n" +
                "hcl:#866857\n" +
                "\n" +
                "byr:1974 iyr:2015 hcl:#cfa07d\n" +
                "hgt:190cm ecl:grn pid:330115418 eyr:2020 cid:173\n" +
                "\n" +
                "iyr:2010\n" +
                "eyr:2024 byr:1943 pid:227557868\n" +
                "cid:102 hgt:72in\n" +
                "hcl:#6b5442\n" +
                "\n" +
                "hgt:151cm byr:1994\n" +
                "pid:050723476\n" +
                "cid:104 iyr:2010 hcl:#b6652a\n" +
                "ecl:brn eyr:2030\n" +
                "\n" +
                "ecl:gry byr:1942\n" +
                "hcl:#c5e354 hgt:192cm iyr:2019\n" +
                "eyr:2027 pid:192177566\n" +
                "\n" +
                "ecl:hzl hcl:#c0946f byr:1997 pid:853199076 hgt:166cm\n" +
                "iyr:2019\n" +
                "eyr:2020\n" +
                "cid:329\n" +
                "\n" +
                "eyr:2027 iyr:2029 hcl:#602927\n" +
                "pid:#1b8593 byr:2028 hgt:172in ecl:grn\n" +
                "\n" +
                "eyr:2036 iyr:2030 ecl:utc\n" +
                "pid:22330365 hcl:z byr:2016 hgt:65cm\n" +
                "\n" +
                "pid:936740771\n" +
                "ecl:grn byr:1976\n" +
                "hcl:#602927\n" +
                "iyr:2017 hgt:192cm\n" +
                "cid:257 eyr:2027\n" +
                "\n" +
                "eyr:2021 pid:415535681\n" +
                "hcl:#b6652a hgt:191cm\n" +
                "ecl:hzl\n" +
                "iyr:2013 byr:1985\n" +
                "\n" +
                "hgt:193cm\n" +
                "pid:499609129\n" +
                "iyr:2014 ecl:oth\n" +
                "eyr:2029 hcl:#cfa07d byr:1973\n" +
                "\n" +
                "ecl:gry byr:1996 cid:85 hgt:174cm iyr:2013 pid:444611265 hcl:#6b5442 eyr:2028\n" +
                "\n" +
                "pid:170343402\n" +
                "iyr:2029\n" +
                "hgt:167cm\n" +
                "byr:2016 eyr:2034 ecl:hzl hcl:z cid:254\n" +
                "\n" +
                "hcl:4893d5 ecl:blu byr:1960\n" +
                "eyr:2027 pid:573029479 iyr:1952 hgt:74in\n" +
                "\n" +
                "eyr:2021 hgt:192cm pid:729836929 iyr:2020 byr:1973\n" +
                "cid:155 ecl:grn hcl:#602927\n" +
                "\n" +
                "hcl:#a97842 byr:1946\n" +
                "iyr:2018 cid:172 hgt:162cm eyr:2030 ecl:amb pid:028768413\n" +
                "\n" +
                "iyr:2020 hcl:#c0946f ecl:grn pid:601040247 cid:237 hgt:177cm eyr:2024\n" +
                "byr:1941\n" +
                "\n" +
                "iyr:2014 byr:1959\n" +
                "eyr:2028\n" +
                "hcl:#fffffd\n" +
                "ecl:#7df42b cid:165 hgt:68cm\n" +
                "\n" +
                "byr:1944 hcl:#18171d\n" +
                "pid:009636175 hgt:155cm ecl:amb eyr:2032 iyr:2015\n" +
                "\n" +
                "byr:1987 hgt:150cm\n" +
                "hcl:#b6652a eyr:2021\n" +
                "ecl:blu\n" +
                "pid:723709387 iyr:2017\n" +
                "\n" +
                "byr:1937 ecl:oth eyr:2022 hcl:#733820 pid:143694811 cid:175 hgt:150cm\n" +
                "\n" +
                "pid:562911401\n" +
                "cid:80 hgt:61in byr:1922 eyr:2027 ecl:amb iyr:2014\n" +
                "hcl:#602927\n" +
                "\n" +
                "iyr:2013 ecl:amb pid:826291674 hcl:#ceb3a1 eyr:2021 byr:1968 hgt:75in\n" +
                "\n" +
                "hcl:#5e0e07\n" +
                "hgt:164cm pid:572114080 eyr:2028 iyr:2016\n" +
                "byr:1922 ecl:oth\n" +
                "\n" +
                "ecl:blu eyr:2029 hgt:155cm\n" +
                "byr:1950 hcl:#866857 cid:271 iyr:2015\n" +
                "pid:216813084\n" +
                "\n" +
                "iyr:2018 byr:2009 cid:212 ecl:#71a98c pid:3467231102 hcl:z hgt:173cm\n" +
                "eyr:2026\n" +
                "\n" +
                "eyr:1945 cid:302 iyr:2030\n" +
                "hcl:z pid:0411732179\n" +
                "byr:2004 hgt:165in\n" +
                "ecl:grt\n" +
                "\n" +
                "iyr:2010 hgt:169cm\n" +
                "hcl:#ceb3a1\n" +
                "eyr:2022 pid:659709686 ecl:hzl\n" +
                "byr:1931\n" +
                "\n" +
                "hcl:z pid:5077677466\n" +
                "eyr:2021 hgt:184cm\n" +
                "iyr:1973\n" +
                "byr:2009 ecl:#35575e\n" +
                "\n" +
                "hgt:190cm cid:103 iyr:2018\n" +
                "byr:1970 ecl:gry\n" +
                "eyr:2028\n" +
                "pid:549406360 hcl:#18171d\n" +
                "\n" +
                "byr:1964 eyr:2023 iyr:2015 hgt:174cm pid:305268492\n" +
                "hcl:#b98e00 ecl:blu\n" +
                "\n" +
                "hgt:162cm\n" +
                "ecl:brn iyr:2019 byr:1951\n" +
                "cid:169 pid:755632568 eyr:2030 hcl:#ceb3a1\n" +
                "\n" +
                "iyr:2010 ecl:oth hgt:192cm eyr:2025\n" +
                "hcl:#efcc98 pid:003868873 byr:1925\n" +
                "\n" +
                "hcl:z\n" +
                "ecl:gmt hgt:62 eyr:1991\n" +
                "byr:2012\n" +
                "iyr:2025 pid:1835041269\n" +
                "\n" +
                "hgt:162\n" +
                "iyr:2026 byr:2024\n" +
                "pid:6463124513 hcl:#602927 ecl:oth eyr:2028\n" +
                "\n" +
                "hcl:bfb027 iyr:1945 byr:1989 eyr:1944 pid:826897563 ecl:dne cid:117 hgt:177\n" +
                "\n" +
                "hcl:#b6652a iyr:2013\n" +
                "ecl:blu byr:1952 eyr:2020 pid:737726601 cid:308\n" +
                "hgt:167cm\n" +
                "\n" +
                "eyr:2028 iyr:2012 hcl:#6b5442\n" +
                "byr:1986 ecl:blu\n" +
                "pid:365821409 hgt:180cm\n" +
                "\n" +
                "hgt:101 eyr:2037 pid:2457985649\n" +
                "ecl:gmt\n" +
                "hcl:4c0784 iyr:2029\n" +
                "\n" +
                "hcl:#341e13 iyr:1954 ecl:dne byr:2007\n" +
                "eyr:2028 pid:552497791 hgt:189cm cid:263\n" +
                "\n" +
                "ecl:blu\n" +
                "hgt:71in pid:360012597 eyr:2029 hcl:#18171d byr:1949\n" +
                "iyr:2014\n" +
                "\n" +
                "hgt:164cm cid:237 iyr:2016 byr:1966\n" +
                "hcl:#623a2f ecl:oth\n" +
                "pid:129809894 eyr:2027\n" +
                "\n" +
                "hcl:a2243b eyr:2031\n" +
                "ecl:#de9183\n" +
                "byr:1982 hgt:109 iyr:1968 pid:848311277\n" +
                "\n" +
                "eyr:1950\n" +
                "hcl:45470a byr:1983\n" +
                "pid:82580746 cid:321\n" +
                "hgt:191cm ecl:dne iyr:2013\n" +
                "\n" +
                "iyr:2018 ecl:blu eyr:2021\n" +
                "byr:1983 pid:045613120\n" +
                "\n" +
                "hgt:164cm byr:1926\n" +
                "hcl:#cfa07d\n" +
                "eyr:2030\n" +
                "iyr:2010\n" +
                "pid:360787918 cid:61 ecl:amb\n" +
                "\n" +
                "pid:#d6dddd iyr:1967 ecl:gmt hcl:z byr:1947 eyr:1943 cid:93 hgt:97\n" +
                "\n" +
                "ecl:oth hgt:172cm\n" +
                "hcl:#efcc98 byr:1957\n" +
                "pid:729531506 iyr:2017\n" +
                "eyr:2030\n" +
                "\n" +
                "ecl:#5a51b9 byr:2023 eyr:2021 iyr:2027\n" +
                "pid:174cm hgt:80 hcl:6b4f6e\n" +
                "\n" +
                "hgt:157cm\n" +
                "ecl:amb\n" +
                "eyr:2020 pid:994733990 iyr:2020 cid:174 byr:1966\n" +
                "hcl:#7d3b0c\n" +
                "\n" +
                "eyr:1986\n" +
                "byr:2020 hcl:#c0946f\n" +
                "pid:499981246\n" +
                "iyr:1926 cid:214 ecl:amb hgt:161cm\n" +
                "\n" +
                "hcl:#18171d byr:1971\n" +
                "pid:#ef8a06\n" +
                "eyr:2019 ecl:blu\n" +
                "hgt:158cm iyr:2011\n" +
                "\n" +
                "byr:1959 cid:298 pid:876132993\n" +
                "ecl:blu iyr:2011 hgt:176cm\n" +
                "hcl:#6b5442 eyr:2030\n" +
                "\n" +
                "eyr:2030\n" +
                "byr:1926 hcl:#733820 iyr:2017\n" +
                "hgt:150cm pid:165cm ecl:blu\n" +
                "\n" +
                "byr:1991 eyr:2027\n" +
                "hgt:183cm\n" +
                "pid:178cm\n" +
                "ecl:grn hcl:8a6142 iyr:2017\n" +
                "cid:265\n" +
                "\n" +
                "hgt:183cm\n" +
                "eyr:2027 hcl:#602927 pid:530884763 byr:1959 ecl:brn iyr:2013\n" +
                "\n" +
                "hcl:#b6652a eyr:2030 hgt:171cm cid:97 byr:2002 pid:166795809\n" +
                "ecl:hzl\n" +
                "iyr:2016\n" +
                "\n" +
                "pid:479944517 hgt:166cm byr:1976 eyr:2022 hcl:#a97842\n" +
                "ecl:grn iyr:2018\n" +
                "\n" +
                "byr:1949\n" +
                "eyr:2013\n" +
                "hcl:z ecl:hzl\n" +
                "pid:152cm hgt:139 iyr:1962\n" +
                "\n" +
                "pid:278193062 hcl:#c0946f iyr:2013 byr:1950 eyr:2020 hgt:167cm\n" +
                "ecl:oth\n" +
                "\n" +
                "eyr:1955\n" +
                "iyr:2018 pid:#ffd4de byr:1922 hgt:178cm hcl:z\n" +
                "ecl:lzr\n" +
                "cid:154\n" +
                "\n" +
                "ecl:grn hcl:#a189d5 byr:1977 pid:120286096\n" +
                "eyr:1966\n" +
                "hgt:178cm\n" +
                "iyr:2011\n" +
                "\n" +
                "eyr:2028\n" +
                "hcl:#fffffd iyr:2013 byr:1956 pid:565425333 cid:207 ecl:gry hgt:67in\n" +
                "\n" +
                "hgt:188cm eyr:2028 cid:90\n" +
                "iyr:2019 pid:574624890 hcl:#b6652a byr:1937 ecl:oth\n" +
                "\n" +
                "hcl:#efcc98 hgt:152cm ecl:hzl iyr:2010 cid:217 byr:1936 eyr:2027 pid:857984889\n" +
                "\n" +
                "hgt:124 iyr:2015 byr:1989 ecl:oth eyr:2023 hcl:#ceb3a1 pid:917440765\n" +
                "\n" +
                "ecl:#61e880 cid:82\n" +
                "pid:165cm byr:2019 eyr:1930 hgt:152in hcl:z iyr:2030\n" +
                "\n" +
                "byr:1974\n" +
                "hgt:60in ecl:gry\n" +
                "hcl:#c0946f\n" +
                "pid:215085889\n" +
                "iyr:2017 eyr:2020\n" +
                "\n" +
                "ecl:grn hcl:#ceb3a1\n" +
                "pid:602147794 hgt:180cm eyr:2028 byr:1927 iyr:2018\n" +
                "\n" +
                "hcl:#ceb3a1 byr:1958 ecl:gry\n" +
                "pid:210246609 iyr:2018 hgt:169cm\n" +
                "eyr:2023\n" +
                "cid:255\n" +
                "\n" +
                "eyr:2025\n" +
                "byr:1950 hgt:177cm\n" +
                "ecl:gry pid:547034188\n" +
                "iyr:2018\n" +
                "cid:218 hcl:#341e13\n" +
                "\n" +
                "pid:380369950\n" +
                "eyr:2022 hgt:65in iyr:2019 ecl:brn\n" +
                "byr:1996\n" +
                "\n" +
                "hgt:167cm\n" +
                "iyr:2014\n" +
                "eyr:2029 cid:199 pid:144048588 byr:1963 ecl:oth hcl:#733820\n" +
                "\n" +
                "byr:1962 ecl:brn hgt:161cm eyr:2022 hcl:#cfa07d\n" +
                "pid:247699670 iyr:2016\n" +
                "\n" +
                "hgt:192cm ecl:#8bdb3e pid:#b152a9 hcl:#18171d iyr:2012 byr:2011 cid:335 eyr:2040\n" +
                "\n" +
                "hcl:11f8f4 ecl:#86f885 iyr:2022\n" +
                "hgt:75in\n" +
                "pid:920078124 byr:1952 eyr:1994\n" +
                "\n" +
                "pid:718378772 cid:282\n" +
                "hcl:#fffffd hgt:166cm eyr:2023 iyr:2019 byr:1944\n" +
                "ecl:grn\n" +
                "\n" +
                "eyr:1963 pid:562051556\n" +
                "ecl:#793853\n" +
                "hcl:z byr:2030 iyr:2026\n" +
                "\n" +
                "byr:1980\n" +
                "cid:143 hgt:161cm eyr:2022\n" +
                "pid:778422225\n" +
                "hcl:#7d3b0c iyr:2011 ecl:blu\n" +
                "\n" +
                "byr:1940 eyr:2021 hgt:74in cid:161 hcl:#a97842 ecl:blu\n" +
                "pid:864682628 iyr:2014\n" +
                "\n" +
                "byr:1991 pid:163871357 ecl:oth\n" +
                "hgt:189cm cid:229 eyr:2024\n" +
                "hcl:#efcc98\n" +
                "iyr:2012\n" +
                "\n" +
                "iyr:2013 hcl:#ceb3a1 ecl:hzl byr:1985 pid:801995950 hgt:73in\n" +
                "eyr:2029\n" +
                "\n" +
                "byr:1931 hcl:#ceb3a1 iyr:2013\n" +
                "cid:289 eyr:2027 ecl:blu\n" +
                "hgt:150cm pid:969679765\n" +
                "\n" +
                "iyr:2015 hgt:176cm eyr:2025 ecl:blu\n" +
                "byr:1935 pid:388582890 hcl:#efcc98\n" +
                "\n" +
                "iyr:1956 eyr:2034 pid:049440894 ecl:xry byr:1950 cid:228\n" +
                "hgt:186cm\n" +
                "\n" +
                "iyr:2014 hcl:#fffffd byr:1985\n" +
                "eyr:2022\n" +
                "hgt:176cm pid:556889061 ecl:amb\n" +
                "\n" +
                "pid:994755123 hgt:165cm\n" +
                "ecl:brn\n" +
                "eyr:2022 byr:1937 iyr:2011\n" +
                "\n" +
                "byr:2012\n" +
                "hcl:z cid:217 iyr:1958\n" +
                "ecl:brn hgt:160cm\n" +
                "pid:871866608\n" +
                "eyr:2027\n" +
                "\n" +
                "hcl:#a97842\n" +
                "ecl:hzl byr:1929\n" +
                "iyr:2016\n" +
                "eyr:2029 pid:220410871 hgt:192cm\n" +
                "\n" +
                "pid:332596122 byr:1986\n" +
                "hcl:#cfa07d\n" +
                "eyr:2022 hgt:189cm ecl:grn iyr:2016\n" +
                "\n" +
                "hgt:167cm\n" +
                "hcl:#6b5442 eyr:2026\n" +
                "iyr:2020 ecl:grn cid:231 pid:521486150\n" +
                "\n" +
                "cid:299\n" +
                "hgt:178cm byr:1945 ecl:brn iyr:2012\n" +
                "hcl:#fffffd eyr:2022\n" +
                "pid:264693268\n" +
                "\n" +
                "ecl:lzr pid:157cm hcl:f0a766\n" +
                "iyr:2029 hgt:175in byr:2004\n" +
                "\n" +
                "hcl:#888785\n" +
                "hgt:159cm eyr:2025\n" +
                "iyr:2012 ecl:oth\n" +
                "byr:1995 pid:135442622\n" +
                "\n" +
                "eyr:2024 hcl:#a97842\n" +
                "iyr:2013 pid:186863004 ecl:brn byr:1929 hgt:162cm\n" +
                "\n" +
                "pid:518619727 ecl:grn\n" +
                "hcl:#866857 byr:1954\n" +
                "iyr:2016\n" +
                "hgt:69in\n" +
                "\n" +
                "eyr:2022 iyr:2018\n" +
                "byr:1926\n" +
                "ecl:oth pid:073817633\n" +
                "hgt:167cm hcl:#b6652a\n" +
                "\n" +
                "hgt:190cm iyr:2019\n" +
                "ecl:dne byr:2010 eyr:2023 pid:979671212\n" +
                "\n" +
                "hgt:190\n" +
                "ecl:#5ddbda byr:2025 iyr:1973 pid:#a082dc eyr:1998 cid:177 hcl:z\n" +
                "\n" +
                "iyr:1976 eyr:2026 pid:4862485 hgt:71in\n" +
                "byr:1975 ecl:#41855f hcl:#ceb3a1\n" +
                "\n" +
                "ecl:brn\n" +
                "byr:1927 iyr:2012 hgt:67in\n" +
                "pid:479646443 eyr:2039 hcl:z\n" +
                "\n" +
                "pid:779458123\n" +
                "cid:263 hcl:#efcc98\n" +
                "byr:1951\n" +
                "hgt:153cm iyr:2011\n" +
                "ecl:amb eyr:2030\n" +
                "\n" +
                "hgt:187cm\n" +
                "eyr:2028\n" +
                "byr:1957\n" +
                "hcl:#b6652a iyr:2015 pid:616963706 ecl:brn\n" +
                "\n" +
                "hgt:154cm byr:1947\n" +
                "pid:51196404\n" +
                "eyr:2020 iyr:2001\n" +
                "ecl:hzl\n" +
                "\n" +
                "hgt:157cm iyr:2013 eyr:2021\n" +
                "ecl:blu hcl:d9e6e0\n" +
                "byr:2023\n" +
                "\n" +
                "iyr:2010\n" +
                "hcl:#866857\n" +
                "ecl:brn eyr:2022\n" +
                "cid:163 byr:1962 pid:458396337 hgt:160cm\n" +
                "\n" +
                "ecl:amb eyr:2027\n" +
                "byr:1987\n" +
                "hgt:184cm\n" +
                "pid:125222897 cid:214\n" +
                "iyr:2020\n" +
                "\n" +
                "ecl:grt eyr:1942\n" +
                "iyr:2023\n" +
                "hcl:901862 hgt:159in\n" +
                "byr:2009\n" +
                "\n" +
                "eyr:2028\n" +
                "byr:1973 hgt:151cm iyr:2013 ecl:hzl pid:229165831\n" +
                "\n" +
                "eyr:2024 hgt:190cm ecl:grn byr:1962 iyr:1921 pid:531272718 hcl:#ceb3a1\n" +
                "\n" +
                "hgt:169in pid:1747598652 ecl:dne\n" +
                "iyr:1974 eyr:2031 hcl:#602927 byr:2030\n" +
                "\n" +
                "hgt:177cm iyr:1963 eyr:2006\n" +
                "cid:276 hcl:#7d3b0c\n" +
                "pid:192cm\n" +
                "ecl:grn byr:2022\n" +
                "\n" +
                "pid:893346945 hcl:#733820 ecl:oth iyr:2013 byr:1973 hgt:186cm eyr:2025\n" +
                "\n" +
                "hgt:177cm\n" +
                "eyr:2025\n" +
                "byr:1997 hcl:#733820\n" +
                "cid:295 iyr:2016 pid:019887743 ecl:grn\n" +
                "\n" +
                "iyr:2019\n" +
                "byr:1982 eyr:2030 ecl:brn pid:041140029 hcl:#a97842 hgt:189cm\n" +
                "\n" +
                "pid:165cm iyr:1962 hcl:e5c590 ecl:#e394a4\n" +
                "eyr:2040\n" +
                "hgt:70cm byr:2006\n" +
                "\n" +
                "byr:1940 eyr:2025 iyr:2020 hcl:#a97842 ecl:brn pid:502841851 cid:121\n" +
                "hgt:160cm\n" +
                "\n" +
                "eyr:2015 ecl:zzz hgt:174 pid:154cm iyr:2021\n" +
                "hcl:z\n" +
                "cid:113\n" +
                "byr:2003\n" +
                "\n" +
                "iyr:2020\n" +
                "eyr:2023 pid:402183362 hcl:#120eb2\n" +
                "ecl:amb\n" +
                "hgt:150in\n" +
                "byr:1921\n" +
                "\n" +
                "pid:059311672 cid:290 hcl:#7d3b0c byr:1996\n" +
                "iyr:2021 hgt:155cm ecl:hzl eyr:2030\n" +
                "\n" +
                "pid:790768270 hgt:167cm ecl:gry\n" +
                "byr:1973\n" +
                "hcl:#b6652a eyr:2028 iyr:2017\n" +
                "\n" +
                "hcl:#18171d eyr:2024 pid:062349624\n" +
                "byr:2002 iyr:2019 cid:230 ecl:oth\n" +
                "\n" +
                "iyr:2015\n" +
                "hgt:170cm\n" +
                "cid:193\n" +
                "eyr:2025 ecl:hzl byr:1947\n" +
                "pid:484823445\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "pid:5792950369 cid:343\n" +
                "hcl:1d16b7 eyr:2024\n" +
                "hgt:185in iyr:1965\n" +
                "ecl:gmt byr:1948\n" +
                "\n" +
                "pid:#a1a101\n" +
                "ecl:grn\n" +
                "byr:1945\n" +
                "iyr:2013 hcl:#ceb3a1 hgt:156cm cid:279 eyr:2026\n" +
                "\n" +
                "pid:936593230 eyr:2026 hgt:179cm\n" +
                "hcl:#7d3b0c\n" +
                "iyr:2015 byr:1976\n" +
                "ecl:gry\n" +
                "\n" +
                "eyr:2021 pid:210678956 hgt:153cm cid:72 iyr:2018 ecl:grn\n" +
                "\n" +
                "pid:995512119\n" +
                "byr:1937 eyr:2021 hcl:#4d8fe2 ecl:brn\n" +
                "iyr:2019\n" +
                "hgt:187cm\n" +
                "\n" +
                "hcl:#341e13 pid:282336259 eyr:2022\n" +
                "cid:95 byr:1977 hgt:101 ecl:#955529\n" +
                "iyr:2029\n" +
                "\n" +
                "pid:934992980 hcl:#fffffd iyr:2028 eyr:1945 ecl:gry byr:1926\n" +
                "\n" +
                "iyr:2018 ecl:gry\n" +
                "hgt:179cm hcl:#866857 pid:573616710\n" +
                "byr:1987\n" +
                "\n" +
                "byr:2010 eyr:2021 hcl:z pid:018180068 hgt:74\n" +
                "cid:183 iyr:2027 ecl:dne\n" +
                "\n" +
                "iyr:2020 hgt:160cm\n" +
                "pid:760124779 hcl:#b6652a ecl:grn eyr:2030 byr:1951\n" +
                "\n" +
                "pid:354858055 ecl:hzl hgt:192cm\n" +
                "hcl:#ceb3a1 byr:1983 cid:314\n" +
                "iyr:2016 eyr:2027\n" +
                "\n" +
                "eyr:2028 iyr:2013 hgt:171cm hcl:54be83 ecl:xry byr:2021\n" +
                "pid:89895052\n" +
                "\n" +
                "byr:1963 iyr:2019\n" +
                "pid:298992037 eyr:2024\n" +
                "hgt:63in hcl:#341e13\n" +
                "\n" +
                "hgt:64cm\n" +
                "pid:7645482607 ecl:#c94773 cid:231 hcl:621e9d eyr:2000 byr:2013\n" +
                "\n" +
                "hcl:#ceb3a1 ecl:gry iyr:2015 hgt:178cm byr:1945\n" +
                "pid:416960939\n" +
                "\n" +
                "hgt:178cm\n" +
                "cid:121\n" +
                "byr:1961 iyr:2019 hcl:#6b5442\n" +
                "eyr:2028 pid:098226989\n" +
                "\n" +
                "iyr:2015 eyr:2028\n" +
                "ecl:amb\n" +
                "hgt:154cm\n" +
                "pid:364426658 byr:1960\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "byr:1998\n" +
                "pid:#e5ab03 hcl:z ecl:grn\n" +
                "iyr:2006 hgt:61cm eyr:2038\n" +
                "\n" +
                "hcl:#866857 eyr:2020\n" +
                "hgt:187in\n" +
                "pid:#8239dc iyr:2010 ecl:#6259e7 byr:1939\n" +
                "\n" +
                "pid:#6082fa eyr:2028 ecl:gry hcl:937410 iyr:2010\n" +
                "hgt:173cm\n" +
                "\n" +
                "eyr:1922 ecl:grt pid:228396726 hgt:65cm\n" +
                "hcl:#6b5442 byr:2024 iyr:2010\n" +
                "\n" +
                "hcl:#c0946f iyr:2016 byr:1978\n" +
                "pid:774430678 eyr:2026\n" +
                "ecl:oth hgt:61in\n" +
                "\n" +
                "cid:208 pid:714195768 iyr:2019 eyr:2022 byr:1937 ecl:gry hcl:#ceb3a1 hgt:159cm\n" +
                "\n" +
                "eyr:2028 ecl:gry iyr:2010 byr:2001 hgt:163cm pid:409136005 hcl:#623a2f cid:200\n" +
                "\n" +
                "pid:471094613 eyr:2028 hgt:165cm iyr:2010 ecl:amb byr:1995 hcl:#a97842\n" +
                "\n" +
                "ecl:oth iyr:2011 pid:907249487 eyr:2020\n" +
                "byr:1924 cid:68 hcl:#a97842\n" +
                "hgt:155cm\n" +
                "\n" +
                "byr:1983\n" +
                "hgt:69cm\n" +
                "eyr:2027 iyr:2010 hcl:#866857\n" +
                "pid:671371092\n" +
                "ecl:amb\n" +
                "\n" +
                "hgt:97 pid:168cm cid:67\n" +
                "eyr:1957 hcl:#623a2f byr:2027 ecl:gry iyr:2019\n" +
                "\n" +
                "ecl:blu iyr:2012 pid:287999130 hgt:178cm eyr:2020\n" +
                "byr:1940\n" +
                "hcl:#888785\n" +
                "\n" +
                "hcl:z hgt:63cm ecl:grt\n" +
                "iyr:2024 eyr:2031 pid:167cm\n" +
                "byr:2013\n" +
                "\n" +
                "hgt:65in hcl:z cid:300 ecl:amb pid:58257193\n" +
                "byr:1969 iyr:2011 eyr:1985\n" +
                "\n" +
                "hcl:#341e13 eyr:2036 hgt:189in\n" +
                "pid:58541401\n" +
                "ecl:#d793f2 iyr:2010 byr:2006\n" +
                "\n" +
                "cid:259 ecl:grn\n" +
                "hcl:#a97842\n" +
                "hgt:167cm\n" +
                "pid:641690548 eyr:2021\n" +
                "byr:1930\n" +
                "iyr:2010\n" +
                "\n" +
                "eyr:2027\n" +
                "hgt:75in\n" +
                "cid:335 hcl:#602927 iyr:2012 ecl:blu pid:724014178\n" +
                "byr:1928\n" +
                "\n" +
                "eyr:2027\n" +
                "hcl:#8345d8 ecl:grn byr:1965 pid:728151722\n" +
                "hgt:182cm iyr:2016\n" +
                "\n" +
                "iyr:2020 pid:794922933\n" +
                "hcl:#341e13\n" +
                "ecl:oth\n" +
                "byr:1985\n" +
                "hgt:71in\n" +
                "eyr:2022\n" +
                "\n" +
                "ecl:#025c94\n" +
                "hcl:#fffffd\n" +
                "iyr:2027\n" +
                "byr:1987 hgt:186cm cid:135 pid:005852205\n" +
                "eyr:2021\n" +
                "\n" +
                "byr:2020\n" +
                "ecl:grn\n" +
                "pid:91200233\n" +
                "iyr:2014 eyr:2020 hgt:179cm hcl:be0c95\n" +
                "\n" +
                "ecl:gmt iyr:2010 pid:171cm byr:2010 eyr:2036 hgt:159in hcl:424df0\n" +
                "\n" +
                "ecl:gry iyr:2013 hcl:#7d3b0c hgt:175cm pid:337611432 byr:1953 eyr:2021\n" +
                "\n" +
                "byr:1926 pid:678688040\n" +
                "hcl:#cfa07d\n" +
                "cid:311 eyr:2027 hgt:183cm iyr:2018\n" +
                "ecl:blu\n" +
                "\n" +
                "byr:2021\n" +
                "pid:156cm\n" +
                "ecl:dne\n" +
                "hgt:156cm\n" +
                "hcl:#6b5442\n" +
                "iyr:2027\n" +
                "\n" +
                "pid:380639402 eyr:2025\n" +
                "cid:196\n" +
                "hgt:163cm\n" +
                "hcl:#602927 ecl:hzl\n" +
                "iyr:2015\n" +
                "\n" +
                "eyr:2039 hcl:5aa018 byr:2007 ecl:dne\n" +
                "hgt:77 pid:#d30c1c iyr:2025\n" +
                "\n" +
                "ecl:blu\n" +
                "iyr:1986 pid:3544865154 eyr:2027 byr:2007 hgt:74cm hcl:ca33b3\n" +
                "\n" +
                "pid:#b05294 byr:1951 hcl:#ceb3a1 cid:221\n" +
                "iyr:2000 hgt:110 eyr:2021\n" +
                "\n" +
                "byr:1954 hcl:#efcc98 iyr:2018 ecl:#f4ea81\n" +
                "pid:414210788 hgt:174cm eyr:2037\n" +
                "\n" +
                "pid:#abbdd9\n" +
                "hgt:164in\n" +
                "iyr:2003 cid:319 eyr:2035\n" +
                "byr:2015\n" +
                "ecl:#9b38c7\n" +
                "\n" +
                "ecl:amb\n" +
                "iyr:2018 byr:1922\n" +
                "hgt:157cm pid:801421993 eyr:2029\n" +
                "hcl:#efcc98\n" +
                "\n" +
                "eyr:2022\n" +
                "hcl:#b6652a\n" +
                "ecl:amb byr:1942\n" +
                "hgt:153cm iyr:2018\n" +
                "pid:805225382\n" +
                "\n" +
                "byr:1925\n" +
                "hcl:#efcc98 ecl:amb pid:539625393 cid:269\n" +
                "hgt:161cm iyr:2013 eyr:2025\n" +
                "\n" +
                "cid:305 hgt:154cm\n" +
                "hcl:#18171d byr:1998\n" +
                "eyr:2029 iyr:2011 pid:634235387 ecl:gry\n" +
                "\n" +
                "hcl:#6b5442 pid:504467634 iyr:2018 cid:96 byr:1970\n" +
                "ecl:gry eyr:2023 hgt:167cm\n" +
                "\n" +
                "iyr:2020 hgt:163cm hcl:#fffffd pid:762271916 byr:2029 eyr:2022\n" +
                "ecl:#12027c\n" +
                "\n" +
                "byr:1978\n" +
                "cid:134 hcl:#6b5442\n" +
                "iyr:2010 pid:627335191 ecl:blu eyr:2023 hgt:171cm\n" +
                "\n" +
                "pid:260340768 hcl:#a97842 byr:1947 ecl:#2150e3 iyr:2019\n" +
                "cid:153\n" +
                "hgt:65in eyr:2025\n" +
                "\n" +
                "byr:1973 eyr:2022\n" +
                "cid:229 pid:515108192 ecl:amb hcl:z\n" +
                "iyr:2013 hgt:178cm\n" +
                "\n" +
                "ecl:hzl\n" +
                "hgt:158cm hcl:#a97842\n" +
                "pid:657117959 byr:1977 eyr:2023 iyr:2018\n" +
                "\n" +
                "ecl:gry eyr:2024\n" +
                "hgt:163cm byr:1976 iyr:2017\n" +
                "pid:653769092 hcl:#18171d\n" +
                "\n" +
                "ecl:amb pid:161694953\n" +
                "hcl:#602927 hgt:188cm eyr:2021 byr:1946\n" +
                "iyr:2016\n" +
                "\n" +
                "eyr:2021\n" +
                "hgt:178cm ecl:grn\n" +
                "byr:1961 pid:472866063 iyr:2011 hcl:#cfa07d cid:160\n" +
                "\n" +
                "eyr:2037 iyr:2011\n" +
                "hgt:187cm byr:1973 pid:163cm hcl:#b6652a\n" +
                "ecl:brn cid:283\n" +
                "\n" +
                "iyr:2017 hcl:#18171d eyr:2020 pid:059926864 hgt:160cm ecl:brn\n" +
                "\n" +
                "byr:2010\n" +
                "hcl:8217d4 hgt:153in iyr:1989 eyr:2037 pid:#74e0a4 ecl:#ba5782\n" +
                "\n" +
                "ecl:#28f922\n" +
                "byr:1951 hgt:65cm\n" +
                "iyr:2025 pid:151cm hcl:z eyr:2028\n" +
                "\n" +
                "pid:302959013\n" +
                "hgt:167cm eyr:2022 ecl:hzl iyr:2014\n" +
                "cid:168\n" +
                "hcl:#888785 byr:1969\n" +
                "\n" +
                "pid:013861920 hcl:#623a2f\n" +
                "ecl:gry\n" +
                "byr:1928\n" +
                "iyr:2017 hgt:174cm eyr:2028 cid:250\n" +
                "\n" +
                "byr:1997 hgt:74cm hcl:#efcc98 eyr:2039 ecl:#d3dc6a\n" +
                "cid:243\n" +
                "iyr:2030 pid:189cm\n" +
                "\n" +
                "hgt:67cm byr:2011 eyr:2040 pid:192cm ecl:#b7a5a8 hcl:281b25\n" +
                "\n" +
                "iyr:2014 ecl:brn byr:1954 hcl:#fffffd pid:285922660 hgt:76in eyr:2028\n" +
                "\n" +
                "iyr:2021 byr:2028\n" +
                "eyr:2023\n" +
                "hgt:69in hcl:e832ef pid:714568559 ecl:#f10004 cid:208\n" +
                "\n" +
                "ecl:brn hgt:166cm hcl:#cfa07d\n" +
                "cid:59 pid:4884483993\n" +
                "iyr:2012\n" +
                "eyr:1932\n" +
                "\n" +
                "iyr:2029 byr:2025\n" +
                "cid:238 ecl:utc\n" +
                "eyr:2022\n" +
                "pid:887425834 hgt:177in\n" +
                "\n" +
                "pid:014383055 cid:342 hgt:183cm eyr:2025\n" +
                "iyr:2014 ecl:blu byr:1969 hcl:#7d3b0c\n" +
                "\n" +
                "eyr:2033\n" +
                "hcl:#341e13\n" +
                "iyr:2014\n" +
                "hgt:71cm ecl:#750eec\n" +
                "byr:1980 pid:#e2d3ac\n" +
                "\n" +
                "hgt:154cm iyr:2019 hcl:#341e13 ecl:hzl byr:1942 pid:393181243 eyr:2025\n" +
                "cid:316\n" +
                "\n" +
                "ecl:amb\n" +
                "hgt:158cm\n" +
                "eyr:2025 byr:1986 hcl:#fffffd pid:379180765 iyr:2014\n" +
                "\n" +
                "pid:8191674491 eyr:2028 hcl:#efcc98 byr:2015 iyr:2012\n" +
                "ecl:amb cid:140\n" +
                "\n" +
                "byr:2015 hcl:8e3e81 eyr:2024 hgt:180cm iyr:1984 pid:#56cd0e\n" +
                "ecl:zzz\n" +
                "\n" +
                "pid:247138863 eyr:2022 hgt:176cm iyr:2014 byr:1929\n" +
                "ecl:gry cid:87 hcl:#b6652a\n" +
                "\n" +
                "hcl:z pid:#c4cdee ecl:#ca97a6 byr:1971 eyr:2032\n" +
                "iyr:1997 hgt:156in\n" +
                "\n" +
                "ecl:gry\n" +
                "hgt:167cm eyr:2030 byr:1984 hcl:#cfa07d iyr:2016\n" +
                "\n" +
                "hcl:#a97842 hgt:163cm\n" +
                "pid:373461578 iyr:2017\n" +
                "byr:1926 ecl:amb\n" +
                "eyr:2021\n" +
                "\n" +
                "cid:161 hcl:#b6652a byr:1930 ecl:brn eyr:2028 hgt:166cm iyr:2016\n" +
                "\n" +
                "ecl:amb cid:126\n" +
                "eyr:2024\n" +
                "hcl:#18171d iyr:2019\n" +
                "byr:1991 hgt:183cm\n" +
                "\n" +
                "ecl:lzr iyr:2026 byr:1996\n" +
                "hgt:69in pid:#ccef7e eyr:2028\n" +
                "hcl:#fffffd\n" +
                "\n" +
                "hgt:68cm iyr:1945\n" +
                "ecl:brn byr:2009 pid:#56e987 hcl:fdd212 eyr:2023\n" +
                "\n" +
                "pid:72039060 eyr:2036 hgt:172cm\n" +
                "hcl:#fffffd iyr:2011\n" +
                "byr:1944 ecl:xry cid:202\n" +
                "\n" +
                "ecl:hzl\n" +
                "eyr:2025\n" +
                "byr:1922 hcl:#efcc98\n" +
                "pid:795790549\n" +
                "iyr:2020 hgt:150cm\n" +
                "\n" +
                "hgt:155cm\n" +
                "hcl:#373de2 pid:#707dc6 iyr:2017 byr:2002 eyr:2038\n" +
                "ecl:oth\n" +
                "\n" +
                "hcl:#888785 iyr:2011\n" +
                "hgt:159cm\n" +
                "byr:1962 eyr:2026 ecl:amb pid:672792762\n" +
                "\n" +
                "cid:217 eyr:2025 hgt:164cm byr:1999 pid:975218035 ecl:grn hcl:#18171d iyr:2011\n" +
                "\n" +
                "pid:655501194 eyr:2029 byr:1999 iyr:2013\n" +
                "hgt:183cm hcl:#a97842 ecl:hzl\n" +
                "\n" +
                "byr:1986 hcl:#efcc98 pid:160cm ecl:oth eyr:2022 iyr:2022 hgt:150cm\n" +
                "cid:63\n" +
                "\n" +
                "hgt:69in byr:2002 pid:0528229881 ecl:blu hcl:z iyr:2022\n" +
                "eyr:2033\n" +
                "\n" +
                "iyr:2020 pid:007754028 hcl:z cid:232 ecl:dne hgt:73in\n" +
                "\n" +
                "ecl:hzl\n" +
                "iyr:2016 hcl:#7d3b0c byr:1948\n" +
                "eyr:2021\n" +
                "cid:269 hgt:173cm pid:176430746\n" +
                "\n" +
                "cid:310\n" +
                "ecl:xry\n" +
                "eyr:2032 hgt:64cm\n" +
                "pid:190cm hcl:z byr:2019\n" +
                "\n" +
                "hgt:150cm pid:660176034 hcl:#c0946f\n" +
                "ecl:hzl byr:1986 eyr:2021 iyr:2019\n" +
                "\n" +
                "pid:7876582\n" +
                "eyr:2021 iyr:2020\n" +
                "hgt:185cm hcl:#18171d cid:319 ecl:amb byr:1943\n" +
                "\n" +
                "ecl:hzl hcl:#623a2f iyr:1950 byr:2012 cid:334 eyr:2028\n" +
                "\n" +
                "eyr:2028\n" +
                "byr:1992 hcl:#b6652a ecl:hzl cid:222\n" +
                "hgt:189cm iyr:2016\n" +
                "pid:092856842\n" +
                "\n" +
                "hcl:a3c52a\n" +
                "iyr:2025 byr:2023\n" +
                "hgt:182cm ecl:#be1503 pid:9311657615 eyr:2005\n" +
                "\n" +
                "eyr:2035\n" +
                "byr:1988 hgt:193cm\n" +
                "iyr:2028 cid:128 hcl:#18171d ecl:utc pid:9743739773\n" +
                "\n" +
                "ecl:zzz hcl:z\n" +
                "hgt:64cm pid:160cm byr:2026 eyr:1943 iyr:2028 cid:74\n" +
                "\n" +
                "ecl:oth\n" +
                "pid:874577361\n" +
                "iyr:2010 eyr:2021\n" +
                "hgt:160cm hcl:#c0946f\n" +
                "byr:1959";
    }

}