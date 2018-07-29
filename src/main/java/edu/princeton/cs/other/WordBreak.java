package edu.princeton.cs.other;

import java.util.*;

import static java.lang.System.out;

/**
 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 说明：
     拆分时可以重复使用字典中的单词。
     你可以假设字典中没有重复的单词。
 示例 1：
 输入: s = "leetcode", wordDict = ["leet", "code"]
 输出: true
 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 示例 2：
 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 输出: true
 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 注意你可以重复使用字典中的单词。
 示例 3：
 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 输出: false
 * @author Mageek Chiu
 */
class WordBreak {

    // 暴力解法
//    https://www.programcreek.com/2012/12/leetcode-solution-word-break/
    public boolean wordBreak0(String s, Set<String> dict) {
        return wordBreakHelper(s, dict, 0);
    }

    public boolean wordBreakHelper(String s, Set<String> dict, int start){
        if(start == s.length())
            return true;

        for(String a: dict){
            int len = a.length();
            int end = start+len;

            //end index should be <= string length
            if(end > s.length())
                continue;

            if(s.substring(start, start+len).equals(a))
                if(wordBreakHelper(s, dict, start+len))
                    return true;
        }

        return false;
    }

    /**
     * DP 解法
     Define an array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
     Initial state t[0] == true
     Time: O(string length * dict size).
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] t = new boolean[s.length()+1];
        t[0] = true; //set first to be true, why?
        //Because we need initial state
        for(int i=0; i<s.length(); i++){
            //should continue from match position
            if(!t[i])
                continue;
            for(String a: wordDict){
                int len = a.length();
                int end = i + len;
                if(end > s.length())
                    continue;
                if(t[end]) continue;
                if(s.substring(i, end).equals(a)){
                    t[end] = true;
                }
            }
        }
        return t[s.length()];
    }

    /**
     in last Solution , if the size of the dictionary is very large, the time is bad.
     Instead we can solve the problem in O(n^2) time (n is the length of the string).
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        int[] pos = new int[s.length()+1];
        Arrays.fill(pos, -1);
        pos[0]=0;
        for(int i=0; i<s.length(); i++){
            if(pos[i]!=-1){
                for(int j=i+1; j<=s.length(); j++){
                    String sub = s.substring(i, j);
                    if(wordDict.contains(sub)){
                        pos[j]=i;
                    }
                }
            }
        }
        return pos[s.length()]!=-1;
    }



//    LeetCode – Word Break II
//    https://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
//    public static List<String> wordBreak(String s, Set<String> dict) {
//        //create an array of ArrayList<String>
//        List<String> dp[] = new ArrayList[s.length()+1];
//        dp[0] = new ArrayList<String>();
//
//        for(int i=0; i<s.length(); i++){
//            if( dp[i] == null )
//                continue;
//
//            for(String word:dict){
//                int len = word.length();
//                int end = i+len;
//                if(end > s.length())
//                    continue;
//
//                if(s.substring(i,end).equals(word)){
//                    if(dp[end] == null){
//                        dp[end] = new ArrayList<String>();
//                    }
//                    dp[end].add(word);
//                }
//            }
//        }
//
//        List<String> result = new LinkedList<String>();
//        if(dp[s.length()] == null)
//            return result;
//
//        ArrayList<String> temp = new ArrayList<String>();
//        dfs(dp, s.length(), result, temp);
//
//        return result;
//    }
//
//    public static void dfs(List<String> dp[],int end,List<String> result, ArrayList<String> tmp){
//        if(end <= 0){
//            String path = tmp.get(tmp.size()-1);
//            for(int i=tmp.size()-2; i>=0; i--){
//                path += " " + tmp.get(i) ;
//            }
//
//            result.add(path);
//            return;
//        }
//
//        for(String str : dp[end]){
//            tmp.add(str);
//            dfs(dp, end-str.length(), result, tmp);
//            tmp.remove(tmp.size()-1);
//        }
//    }

//    public List<String> wordBreak(String s, Set<String> wordDict) {
//        ArrayList<String> [] pos = new ArrayList[s.length()+1];
//        pos[0]=new ArrayList<String>();
//
//        for(int i=0; i<s.length(); i++){
//            if(pos[i]!=null){
//                for(int j=i+1; j<=s.length(); j++){
//                    String sub = s.substring(i,j);
//                    if(wordDict.contains(sub)){
//                        if(pos[j]==null){
//                            ArrayList<String> list = new ArrayList<String>();
//                            list.add(sub);
//                            pos[j]=list;
//                        }else{
//                            pos[j].add(sub);
//                        }
//
//                    }
//                }
//            }
//        }
//
//        if(pos[s.length()]==null){
//            return new ArrayList<String>();
//        }else{
//            ArrayList<String> result = new ArrayList<String>();
//            dfs(pos, result, "", s.length());
//            return result;
//        }
//    }
//
//    public void dfs(ArrayList<String> [] pos, ArrayList<String> result, String curr, int i){
//        if(i==0){
//            result.add(curr.trim());
//            return;
//        }
//
//        for(String s: pos[i]){
//            String combined = s + " "+ curr;
//            dfs(pos, result, combined, i-s.length());
//        }
//    }

    // 感受：
    public static void main (String ...args){
        out.println(wordBreak("leetcode",new LinkedList<String>(){{add("leet");add("code");}}));
    }
}
