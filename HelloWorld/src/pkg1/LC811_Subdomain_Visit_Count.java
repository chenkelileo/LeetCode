package pkg1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Example 1:
 * Input:
 * ["9001 discuss.leetcode.com"]
 * Output:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * Explanation:
 * We only have one website domain: "discuss.leetcode.com". As discussed above,
 * the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
 *
 * Example 2:
 * Input:
 * ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output:
 * ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * Explanation:
 * We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times.
 * For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 */

public class LC811_Subdomain_Visit_Count {
    public static List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String s : cpdomains) {
            int index = s.indexOf(" ");
            int cp = Integer.valueOf(s.substring(0, index));
            String domains = s.substring(index + 1);

            map.put(domains, map.getOrDefault(domains, 0) + cp);

            for (int i = 0; i < domains.length(); i++) {
                if (domains.charAt(i) == '.') { //"." for character doesn't work check"\\." not work, and equal character don't have such function
                    //keep in mind if string use equal to check the context; == for exact the same pointing to the same memory allocation.
                    String subDomain = domains.substring(i + 1);
                    map.put(subDomain, map.getOrDefault(subDomain, 0) + cp);
                }
                //test on the before? same
            }

        }
        List<String> list = new ArrayList<>();

        for(String s : map.keySet()){
            list.add(map.get(s) + " " + s);
        }

        return list;
    }

    public static void main(String[] args){
        String[] strs = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        subdomainVisits(strs);
    }
}
