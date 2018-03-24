package com.njust.syntax.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 文法
 *
 * @author tomato
 * @create 2018-03-24 上午11:01
 */
public class Grammar {
    /**
     * 文法的左部
     */
    private String formulaL;
    /**
     * 文法右部
     */
    private String formulaR;
    /**
     * 点的位置
     */
    private int location;
    /**
     * 向前搜索附
     */
    private List<String> forwardSearchs;

    public Grammar() {
        location = 0;
        forwardSearchs = new ArrayList<String>();
    }

    public Grammar(String formulaL, String formulaR) {
        this.formulaL = formulaL;
        this.formulaR = formulaR;
    }

    public Grammar(String formulaL, String formulaR, int location, List<String> forwardSearchs) {
        this.formulaL = formulaL;
        this.formulaR = formulaR;
        this.location = location;
        this.forwardSearchs = forwardSearchs;
    }

    public String getFormulaL() {
        return formulaL;
    }

    public void setFormulaL(String formulaL) {
        this.formulaL = formulaL;
    }

    public String getFormulaR() {
        return formulaR;
    }

    public void setFormulaR(String formulaR) {
        this.formulaR = formulaR;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<String> getForwardSearchs() {
        return forwardSearchs;
    }

    public void setForwardSearchs(String forwardSearchs) {
        this.forwardSearchs.add(forwardSearchs);
    }

    public void setForwardSearchs(List<String> forwardSearchs) {
        this.forwardSearchs = forwardSearchs;
    }

    @Override
    public String toString() {
        String result = "";
        StringBuilder str = new StringBuilder(formulaR);
        str.insert(location, (char) 7);
        result = result.concat(formulaL).concat("->").concat(str.toString()).concat(" , ").concat(forwardSearchs.toString());
        return result;
    }
}
