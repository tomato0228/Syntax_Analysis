package com.njust;

import com.njust.syntax.Syntax;

public class Main {

    public static void main(String[] args) {
        Syntax syntax = new Syntax();
        String address = "/Users/tomato/Downloads/lexical_syntax_analysis-master/语法分析_文法的副本.txt";
        try {
            syntax.fileRead(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        syntax.calculateFirst();
        syntax.printFirst();
    }
}
