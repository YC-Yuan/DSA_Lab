package lab_nine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : wqruan
 * @version : 1.0.0
 * @date : 2020/12/7 10:37
 */
public class KMP {

    public static ArrayList<Integer> search(String str,String pattern) {
        // TODO: implement kmp search here. One str could contain multiple patterns, please output all patterns' positions
        int strLength=str.length();
        int patternLength=pattern.length();

        int lps[]=lps(pattern);

        ArrayList<Integer> list=new ArrayList<>();

        int q=0;//pattern中准备检查的指针
        for (int i=0;i<strLength;i++){//str中匹配指针
            while (q > 0 && pattern.charAt(q)!=str.charAt(i)) {//下一个不匹配时，匹配指针回溯
                q=lps[q];
            }
            if (pattern.charAt(q)==str.charAt(i)) q++;
            if (q==patternLength) {//需要检查的都结束了，即匹配成功
                list.add(i+1-patternLength);
                q=lps[q-1];
            }
        }

        return list;
    }

    private static int[] lps(String pattern) {
        // TODO: construct jump table here
        int length = pattern.length();
        int[] lps = new int[length];

        //lps[i]中储存的是，当考察第i+1个时，前面有几个可取的，即回溯指针+1
        lps[0] = 0;
        int k = 0;

        for (int i = 1; i < length; i++) {//从第二个开始遍历,i表示第i+1个
            while (k > 0 && pattern.charAt(k)!=pattern.charAt(i)) {//比较下一个，如果不相同则回溯指针回溯，直到为0或相同
                k = lps[k+1]-1;//lps中存储的是"前面有几个可取的，拿来当指针需要-1"
            }
            if (pattern.charAt(k)==pattern.charAt(i)) k++;//比较下一个，如果相同则回溯指针++
            lps[i] = k;
        }
        return lps;
    }

    public static void main(String[] args) {
        KMP kmp=new KMP();

        String str = "abxabcabyabyab";
        String pattern = "abyab";
        for (int tmp : search(str,pattern)) {
            System.out.println(tmp);
        }

    }
}
