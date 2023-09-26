package com.df.cn.leetcode;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qindongfang
 * @date 2023/9/26
 **/
public class LeetCodeTest {

    @Test
    public void lengthOfLongestSubstring() {
        /**
         * 3. 无重复字符的最长子串
         *
         * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
         *
         *
         *
         * 示例 1:
         *
         * 输入: s = "abcabcbb"
         * 输出: 3
         * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
         * 示例 2:
         *
         * 输入: s = "bbbbb"
         * 输出: 1
         * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
         * 示例 3:
         *
         * 输入: s = "pwwkew"
         * 输出: 3
         * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
         */
//        String s = "abcabcbb";
        String s = "abcdhabcbewrbnvkljhb";

        /**
         * 1.将字符串转为字符数组A
         * 2.将字符数组A去重得到Set集合B，A和B取交集得到C
         * 3.遍历字符数组，记录重复各个字符出现的次数，若有字符出现次数>=2,则记录遍历的字符串当前的长度
         */
        // TODO
        char[] chars = s.toCharArray();

        ArrayList<Character> charList = Lists.newArrayList();
        for (int i = 0; i < chars.length - 1; i++) {
            charList.add(chars[i]);
        }
        HashSet<Character> charSet = Sets.newHashSet();
        ArrayList<Integer> strLengthList = Lists.newArrayList();
        int startIndex = 0;
        for (int currentIndex = 0; currentIndex <= chars.length -1; currentIndex++) {
            boolean add = charSet.add(chars[currentIndex]);
            if(!add){
                int length = currentIndex - startIndex;
                strLengthList.add(length);
                startIndex = currentIndex;
            }
        }
        Integer maxStrLength = strLengthList.stream().max(Comparator.naturalOrder()).orElse(0);
        System.out.println(maxStrLength);
    }

    // 3. 无重复字符的最长子串
    @Test
    public void test(){
        String s = "pwwkew";
        char[] chars = s.toCharArray();

        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < chars.length - 1; i++) {
            charList.add(chars[i]);
        }
        HashSet<Character> charSet = new HashSet<>();
        ArrayList<Integer> strLengthList = new ArrayList<>();
        int startIndex = 0;
        for (int currentIndex = 0; currentIndex <= chars.length -1; currentIndex++) {
            boolean add = charSet.add(chars[currentIndex]);
            if(!add){
                int length = currentIndex - startIndex;
                strLengthList.add(length);
                startIndex = currentIndex;
            }
        }
        Integer maxStrLength = strLengthList.stream().max(Comparator.naturalOrder()).orElse(0);
        System.out.println(maxStrLength);
    }

    // 3. 无重复字符的最长子串
    @Test
    public void lengthOfLongestSubstring2(){
            String s = "";
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
    }


    @Test
    public int longestConsecutive() {
        /**
         * top:最长连续序列
         *
         * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
         *
         * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
         *
         *
         *
         * 示例 1：
         *
         * 输入：nums = [100,4,200,1,3,2]
         * 输出：4
         * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
         * 示例 2：
         *
         * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
         * 输出：9
         */
        int[] nums = new int[]{100,4,200,1,3,2};
        Arrays.sort(nums);
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        if(numList ==null || numList.size() == 0){
            return 0;
        }
        if(numList.size() == 1){
            return 1;
        }

        int mustLongestConsecutive = 1;
        int longestConsecutive = 1;
        for (int i = 1; i < numList.size(); i++) {
            Integer beforeNum = numList.get(i-1);
            Integer currentNum = numList.get(i);
            if(beforeNum.equals(currentNum)){
                continue;
            }else if((++beforeNum).equals(currentNum)){
                longestConsecutive++;
            }else{
                longestConsecutive = 1;
            }
            mustLongestConsecutive = Math.max(mustLongestConsecutive, longestConsecutive);
        }
        System.out.println(mustLongestConsecutive);
        return mustLongestConsecutive;
    }


}
