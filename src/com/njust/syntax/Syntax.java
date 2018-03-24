package com.njust.syntax;

import com.njust.syntax.node.First;
import com.njust.syntax.node.Grammar;
import com.njust.syntax.node.ItemSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 语法分析
 *
 * @author tomato
 * @create 2018-03-24 上午10:24
 */
public class Syntax {
    /**
     * 文法
     */
    private List<Grammar> grammars;
    /**
     * 项目集
     */
    private List<ItemSet> itemSets;
    /**
     * First集
     */
    private List<First> firsts;
    /**
     * 二型文法数量
     */
    private int grammarNum;

    /**
     * Syntax构造函数
     */
    public Syntax() {
        grammars = new ArrayList<Grammar>();
        itemSets = new ArrayList<ItemSet>();
        firsts = new ArrayList<First>();
        grammarNum = 0;
    }

    /**
     * 从文件读入二型文法转存入Grammar中
     *
     * @param address 正规文法文件的地址
     * @throws Exception 文件操作异常
     */
    public void fileRead(String address) throws Exception {
        //定义一个file对象，用来初始化FileReader
        File file = new File(address);
        //定义一个fileReader对象，用来初始化BufferedReader
        FileReader reader = new FileReader(file);
        //new一个BufferedReader对象，将文件内容读取到缓存
        BufferedReader bReader = new BufferedReader(reader);
        String s;
        int i;
        Grammar grammar;
        String str;
        //逐行读取文件内容，不读取换行符和末尾的空格
        while ((s = bReader.readLine()) != null) {
            //清除字符串中的空格
            s = s.replace(" ", "");
            //该行为空，跳过
            if (s.length() <= 2) {
                continue;
            }
            grammar = new Grammar();
            str = "";
            for (i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '-') {
                    str = str.concat(String.valueOf(s.charAt(i)));
                } else {
                    break;
                }
            }
            grammar.setFormulaL(str);
            grammar.setFormulaR(s.substring(i + 2));
            grammarNum++;
            grammars.add(grammar);
            if (grammarNum == 1) {
                grammar = new Grammar();
                grammar.setFormulaR(str);
                grammar.setFormulaL(str.substring(0, str.length() - 1).concat("\'").concat(">"));
                grammars.add(0, grammar);
                grammarNum++;
            }
        }
        bReader.close();
    }

    /**
     * 计算First集
     *
     * @return i: 出错的文法位置（i>0），0: first 集计算完毕，-1: 出错
     */
    public int calculateFirst() {
        int i, j, haveOld, nextKind;
        First first;
        StringBuilder nextNode;
        for (i = 1; i < grammarNum; i++) {
            haveOld = 0;
            first = new First();
            for (First f : firsts) {
                if (f.getName().equals(grammars.get(i).getFormulaL())) {
                    haveOld = 1;
                    first = f;
                    break;
                }
            }
            if (haveOld == 0) {
                first.setName(grammars.get(i).getFormulaL());
            }
            nextNode = new StringBuilder("");
            if ((nextKind = findNextNode(i, nextNode, 1)) == -1) {
                return i;
            }
            if (nextKind == 0) {
                first.setFirstSet("@");
            } else if (nextKind == 1) {
                first.setFirstSet(nextNode.toString());
            } else {
                for (First f : firsts) {
                    if (f.getName().equals(nextNode.toString())) {
                        for (j = 0; j < f.getFirstSet().size(); j++) {
                            if (!first.getFirstSet().contains(f.getFirstSet().get(j))) {
                                first.setFirstSet(f.getFirstSet().get(j));
                            }
                        }
                        break;
                    }
                }
            }
            if (haveOld == 0) {
                firsts.add(first);
            }
        }
        if (firsts.size() > 0) {
            findFirst(firsts.get(0).getName(), 1);
        }
        return 0;
    }

    /**
     * 补充全面First集
     *
     * @param name   计算的左部
     * @param nextVn 计算的深度
     */
    private void findFirst(String name, int nextVn) {
        int i, j, k, nextKind;
        First first;
        first = new First();
        StringBuilder nextNode;
        for (i = 0; i < firsts.size(); i++) {
            if (firsts.get(i).getName().equals(name)) {
                first = firsts.get(i);
                break;
            }
        }
        nextNode = new StringBuilder("");
        for (k = 1; k < grammarNum; k++) {
            if (grammars.get(k).getFormulaL().equals(first.getName())) {
                nextKind = findNextNode(k, nextNode, nextVn);
                if (nextKind == 2) {
                    for (First f : firsts) {
                        if (!f.getName().equals(name) && f.getName().equals(nextNode.toString())) {
                            findFirst(f.getName(), 1);
                            for (j = 0; j < f.getFirstSet().size(); j++) {
                                if (!first.getFirstSet().contains(f.getFirstSet().get(j)) && !"@".equals(f.getFirstSet().get(j))) {
                                    first.setFirstSet(f.getFirstSet().get(j));
                                }
                            }
                            if (f.getFirstSet().contains("@")) {
                                findFirst(first.getName(), ++nextVn);
                            }
                            break;
                        }
                    }
                } else if (nextKind == 0 && !first.getFirstSet().contains("@")) {
                    first.setFirstSet("@");
                }
            }
        }
    }

    /**
     * 计算first的集->后面的第一个结点，非终结符在符号"<"和符号">"之间，如<CalcWord>代表关键字
     *
     * @param num    grammars中存的第几个文法
     * @param strB   保存结果，strB.append("");是引用传值
     * @param nextVn 计算的深度
     * @return 0: 右边是空，1: 右边第一个结点是终结符，2: 右边第一个结点是非终结符，-1: 出错
     */
    private int findNextNode(int num, StringBuilder strB, int nextVn) {
        String str = grammars.get(num).getFormulaR();
        int i, j, count;
        if ("@".equals(str)) {
            return 0;
        } else if (str.charAt(0) != '<') {
            for (i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '<') {
                    strB.append(str.substring(0, i));
                    return 1;
                }
            }
            strB.append(str);
            return 1;
        } else {
            for (i = 0, j = 0, count = 1; i < str.length(); i++) {
                if (str.charAt(i) == '<' && count < nextVn) {
                    count++;
                    if (count == nextVn) {
                        j = i;
                    }
                }
                if (count == nextVn && str.charAt(i) == '>') {
                    strB.append(str.substring(j, i + 1));
                    return 2;
                }
            }
            if (i == str.length()) {
                return 0;
            }
        }
        return -1;
    }

    /**
     * 输出First集
     */
    public void printFirst() {
        for (First f : firsts) {
            System.out.println(f.toString());
        }
    }
}
