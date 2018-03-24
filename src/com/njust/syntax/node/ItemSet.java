package com.njust.syntax.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目集
 *
 * @author tomato
 * @create 2018-03-24 上午11:42
 */
public class ItemSet {
    /**
     * 通过V推出新的项目集,存入"V"
     */
    private List<String> roads;
    /**
     * 通过V指向的项目集，存入编号
     */
    private List<Integer> nexts;
    /**
     * 项目集项
     */
    private List<Grammar> grammars;
    /**
     * 编号
     */
    private int number;

    public ItemSet() {
        roads = new ArrayList<String>();
        nexts = new ArrayList<Integer>();
        grammars = new ArrayList<Grammar>();
        number = 0;
    }

    public List<String> getRoads() {
        return roads;
    }

    public void setRoads(String roads) {
        this.roads.add(roads);
    }

    public void setRoads(List<String> roads) {
        this.roads = roads;
    }

    public List<Integer> getNexts() {
        return nexts;
    }

    public void setNexts(Integer nexts) {
        this.nexts.add(nexts);
    }

    public void setNexts(List<Integer> nexts) {
        this.nexts = nexts;
    }

    public List<Grammar> getGrammars() {
        return grammars;
    }

    public void setGrammars(Grammar grammars) {
        this.grammars.add(grammars);
    }

    public void setGrammars(List<Grammar> grammars) {
        this.grammars = grammars;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        String str = "I".concat(String.valueOf(number)).concat(":\r\n");
        for (Grammar g : grammars) {
            str = str.concat("\t").concat(g.toString()).concat("\r\n");
        }
        return str;
    }

    public String toEdge() {
        String str = "";
        for (int i = 0; i < roads.size(); i++) {
            str = "I".concat(String.valueOf(number)).concat(" -- ").concat(roads.get(i)).concat(" -- I").concat(nexts.get(i).toString()).concat("\r\n");
        }
        return str;
    }
}
