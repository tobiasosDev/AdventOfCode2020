import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportTobias {
    private String byr;
    private String iyr;
    private String eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private String cid;
    private String fullString;

    public PassportTobias(String fullString) {
        Pattern patternbyr = Pattern.compile("^.*(byr:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patterniyr = Pattern.compile("^.*(iyr:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patterneyr = Pattern.compile("^.*(eyr:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patternhgt = Pattern.compile("^.*(hgt:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patternhcl = Pattern.compile("^.*(hcl:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patternecl = Pattern.compile("^.*(ecl:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patternpid = Pattern.compile("^.*(pid:([a-zA-Z0-9\\#]*\\s)).*$");
        Pattern patterncid = Pattern.compile("^.*(cid:([a-zA-Z0-9\\#]*\\s)).*$");
        Matcher matcherbyr = patternbyr.matcher(fullString);
        Matcher matcheriyr = patterniyr.matcher(fullString);
        Matcher matchereyr = patterneyr.matcher(fullString);
        Matcher matcherhgt = patternhgt.matcher(fullString);
        Matcher matcherhcl = patternhcl.matcher(fullString);
        Matcher matcherecl = patternecl.matcher(fullString);
        Matcher matcherpid = patternpid.matcher(fullString);
        Matcher matchercid = patterncid.matcher(fullString);
        this.byr = matcherbyr.matches() ? matcherbyr.group(2) : "";
        this.iyr = matcheriyr.matches() ? matcheriyr.group(2) : "";
        this.eyr = matchereyr.matches() ? matchereyr.group(2) : "";
        this.ecl = matcherecl.matches() ? matcherecl.group(2) : "";
        this.pid = matcherpid.matches() ? matcherpid.group(2) : "";
        this.cid = matchercid.matches() ? matchercid.group(2) : "";
        this.hcl = matcherhcl.matches() ? matcherhcl.group(2) : "";
        this.hgt = matcherhgt.matches() ? matcherhgt.group(2) : "";
        this.fullString = fullString;
    }

    public boolean isValid() {
        if (!this.byr.isEmpty() && !this.iyr.isEmpty() && !this.eyr.isEmpty() && !this.ecl.isEmpty() && !this.pid.isEmpty() && !this.hcl.isEmpty() && !this.hgt.isEmpty()) {
            return true;
        }

        if (fullString.split(" ").length < 7) {
            System.out.println(fullString);
            String string = "byr: " + this.byr.isEmpty() + " iyr: " + this.iyr.isEmpty() + " eyr: " + this.eyr.isEmpty() + " ecl: " + this.ecl.isEmpty() + " pid: " + this.pid.isEmpty() + " cid: " + this.cid.isEmpty() + " hcl: " + this.hcl.isEmpty() + " hgt: " + this.hgt.isEmpty();
            System.out.println(string);
        }
        return false;
    }

    public String getByr() {
        return byr;
    }

    public void setByr(String byr) {
        this.byr = byr;
    }

    public String getIyr() {
        return iyr;
    }

    public void setIyr(String iyr) {
        this.iyr = iyr;
    }

    public String getEyr() {
        return eyr;
    }

    public void setEyr(String eyr) {
        this.eyr = eyr;
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}