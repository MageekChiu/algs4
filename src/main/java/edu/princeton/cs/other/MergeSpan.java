package edu.princeton.cs.other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

/**
 给出一个区间的集合，请合并所有重叠的区间。
 示例 1:
 输入: [[1,3],[2,6],[8,10],[15,18]]
 输出: [[1,6],[8,10],[15,18]]
 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 示例 2:
 输入: [[1,4],[4,5]]
 输出: [[1,5]]
 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 ------------------------------------------ -----------------

 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 示例 1:
 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 输出: [[1,5],[6,9]]
 示例 2:
 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 输出: [[1,2],[3,10],[12,16]]
 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

 * @author Mageek Chiu
 */
class MergeSpan {

    public static List<Interval> merge(List<Interval> intervals) {
            List<Interval> list = new LinkedList<>();

//        intervals.sort((o1, o2) -> o2.start - o1.start);// 按照start降序排序
        intervals.sort(Comparator.comparingInt(o -> o.start));// 按照start升序排序
        System.out.println(intervals);

        for (int i = 0; i < intervals.size();) {
            int j = i;
            int range =  intervals.get(j).end;
            while (j<intervals.size()-1 && range>=intervals.get(j+1).start){
                range = Math.max(range,intervals.get(j+1).end);//更新覆盖范围
                j++;
            }
            // 到这里 j的下一个就不是一个区间的了，i-j是一个区间
//            list.add(new Interval(intervals.get(i).start,intervals.get(j).end));//错的，还有可能被包含呢
            list.add(new Interval(intervals.get(i).start ,range));
            i = j+1;
        }

//        return intervals;
        return list;
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i;
        for (i = 0; i < intervals.size(); i++) {
            if (newInterval.start<intervals.get(i).start){
                break;
            }
        }
        intervals.add(i,newInterval);
        return merge(intervals);
    }

    // 感受：
    public static void main (String ...args){
//        out.println(merge( new ArrayList<Interval>(){{
//            add(new Interval(1,4));
//            add(new Interval(4,5));
//        }}));
//
//        out.println(merge( new ArrayList<Interval>(){{
//            add(new Interval(1,4));
//            add(new Interval(2,3));
//        }}));
//
//        out.println(merge( new ArrayList<Interval>(){{//要按序号遍历，所以用ArrayList
//            add(new Interval(1,3));
//            add(new Interval(2,6));
//            add(new Interval(8,10));
//            add(new Interval(15,18));
//        }}));

//        out.println(merge( new ArrayList<Interval>(){{//要按序号遍历，所以用ArrayList
//            add(new Interval(2,3));
//            add(new Interval(4,5));
//            add(new Interval(6,7));
//            add(new Interval(8,9));
//            add(new Interval(1,10));
//        }}));

//        out.println(merge( new ArrayList<Interval>(){{//要按序号遍历，所以用ArrayList
//            add(new Interval(2,2));
//            add(new Interval(1,3));
//            add(new Interval(3,3));
//            add(new Interval(3,4));
//            add(new Interval(2,3));
//            add(new Interval(4,5));
//            add(new Interval(4,4));
//        }}));

        out.println(insert( new ArrayList<Interval>(){{
            add(new Interval(1,3));
            add(new Interval(6,9));
        }},new Interval(2,5)));

        out.println(insert( new ArrayList<Interval>(){{
            add(new Interval(1,2));
            add(new Interval(3,5));
            add(new Interval(6,7));
            add(new Interval(8,10));
            add(new Interval(12,16));
        }},new Interval(4,8)));
    }
}

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "["+start+","+end+"]";
    }
}
