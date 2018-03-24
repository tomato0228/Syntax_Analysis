package com.njust.syntax.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 非终结符的first集
 *
 * @author tomato
 * @create 2018-03-24 上午11:14
 */
public class First {
    /**
     * 谁的first集（仅计算非终结符的first集）
     */
    private String name;
    /**
     * first集
     */
    private List<String> firstSet;

    public First() {
        name = "";
        firstSet = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFirstSet() {
        return firstSet;
    }

    public void setFirstSet(String firstSet) {
        this.firstSet.add(firstSet);
    }

    public void setFirstSet(List<String> firstSet) {
        this.firstSet = firstSet;
    }

    @Override
    public String toString() {
        //return "First(" + name + ")=" + "{" + firstSet + '}';
        return "First(" + name + ")=" + firstSet;
    }
}
