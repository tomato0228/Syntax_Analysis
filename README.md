# Syntax_Analysis

编译原理：语法分析器

* 第一次提交：工程的创建
* 第二次提交：
    * 文法读入保存在Grammar类中（第0个文法为扩展文法）
    * First集的构建（完美构建，不会出错）
    * First集构建时文法的要求：
        * 终结符不能包含'<'和'>'这两个符号
        * 非终结符在'<'和'>'这两个符号中，如<CalcWord>
        * '@'代表空 ε 
    * 函数介绍：

        
```java
    /**
     * 从文件读入二型文法转存入Grammar中
     *
     * @param address 正规文法文件的地址
     * @throws Exception 文件操作异常
     */
    public void fileRead(String address) throws Exception;
```

```java
    /**
     * 计算First集
     *
     * @return i: 出错的文法位置（i>0），0: first 集计算完毕，-1: 出错
     */
    public int calculateFirst();
```

```java
    /**
     * 补充全面First集
     *
     * @param name   计算的左部
     * @param nextVn 计算的深度
     */
    private void findFirst(String name, int nextVn);
```

```java
    /**
     * 计算first的集->后面的第一个结点，非终结符在符号"<"和符号">"之间，如<CalcWord>代表关键字
     *
     * @param num    grammars中存的第几个文法
     * @param strB   保存结果，strB.append("");是引用传值
     * @param nextVn 计算的深度
     * @return 0: 右边是空，1: 右边第一个结点是终结符，2: 右边第一个结点是非终结符，-1: 出错
     */
    private int findNextNode(int num, StringBuilder strB, int nextVn);
```

```java
    /**
     * 输出First集
     */
    public void printFirst();
```


