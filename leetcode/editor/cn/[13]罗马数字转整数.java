//罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。 
//
// 字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。 
//
// 示例 1: 
//
// 输入: "III"
//输出: 3 
//
// 示例 2: 
//
// 输入: "IV"
//输出: 4 
//
// 示例 3: 
//
// 输入: "IX"
//输出: 9 
//
// 示例 4: 
//
// 输入: "LVIII"
//输出: 58
//解释: L = 50, V= 5, III = 3.
// 
//
// 示例 5: 
//
// 输入: "MCMXCIV"
//输出: 1994
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
// Related Topics 数学 字符串


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        HashMap<String, Integer> hashMap = new HashMap<>(16);
        hashMap.put("I", 1);
        hashMap.put("V", 5);
        hashMap.put("X", 10);
        hashMap.put("L", 50);
        hashMap.put("C", 100);
        hashMap.put("D", 500);
        hashMap.put("M", 1000);
        /*
        需要判断传入的值在最大值的左边还是右边
        如果最大值在左边 则是每个值相加
        如果最大值是在右边的话 则是最大值减去每个值
         */
        char[] chars = s.toCharArray();
        int length = s.length();
        Integer maxval = 0;
        Integer startVal = hashMap.get(String.valueOf(chars[0]));
        Integer endVal = hashMap.get(String.valueOf(chars[length - 1]));
        int isSort = startVal.compareTo(endVal);
        for (int a = 0; a < length; a++) {
            Integer integer = hashMap.get(String.valueOf(chars[a]));
            if (isSort < 0) {
                maxval = -maxval + integer;
            } else {
                maxval = maxval + integer;
            }
        }
        return maxval;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
