package lab_one;

public class Polynomial {
    private Term head;

    public Polynomial(Term head) {
        this.head = head;
    }

    public Term getHead() {
        return this.head;
    }

    /**
     * @param head change head
     */
    public void setHead(Term head) {
        this.head = head;
    }

    /**
     * new term may have same exp with the term in polynomial
     *
     * @param term new term
     */
    public void addTerm(Term term) {
        //被加为空，直接加入
        if (this.getHead() == null) {
            this.setHead(term);
            return;
        }

        //指数增加了，直接添加，并连接到原来的head
        if (term.getExp() > this.getHead().getExp()) {
            term.setNext(this.getHead());
            this.setHead(term);
        }
        else {
            //根据添加项的指数查询
            Term termExp = this.getTerm(term.getExp());
            if (this.getTerm(term.getExp()) == null) {
                //原本没有此项，插入到内部
                Term termExecuting = this.getHead();
                while (termExecuting.next() != null) {
                    //还没到结尾项
                    if (termExecuting.next().getExp() < term.getExp()) {
                        //找到插入位置，进行插入操作
                        term.setNext(termExecuting.next());
                        termExecuting.setNext(term);
                        return;
                    }
                    termExecuting = termExecuting.next();
                }
                //已经遍历完了，还没找到坑位，说明比所有指数都小，插在尾巴上
                termExecuting.setNext(term);
            }
            else {
                //已经有项，系数相加
                //如果相加结果为零，应删去这一项而非显示零
                if (Math.abs(termExp.getCoef() + term.getCoef()) < 0.000001) {
                    //如果相消项为第一项
                    if (this.getPrev(termExp) == null) this.setHead(termExp.next());
                    else {
                        //如果相消项为最后一项
                        if (termExp.next() == null) this.getPrev(termExp).setNext(null);
                            //是中间项
                        else this.getPrev(termExp).setNext(termExp.next());
                    }
                }
                else termExp.setCoef(termExp.getCoef() + term.getCoef());
            }
        }
    }

    /**
     * @param another another polynomial
     * @return the sum
     */
    public Polynomial add(Polynomial another) {
        //遍历所加链表，每一项都相加
        Term termExecuting = another.getHead();
        while (termExecuting != null) {
            double coef = termExecuting.getCoef();
            int exp = termExecuting.getExp();
            //!此处直接传入termExecuting会导致链表后半部分乘2!
            this.addTerm(new Term(coef,exp));

            termExecuting = termExecuting.next();
        }
        return this;
    }

    /**
     * example: 4.0x^3+3.2x^2-2.1x^1+1.0x^0
     * example: -12.0x^9-1.0x^7+3.0x^5+10.0x^2+5.0x^0
     *
     * @return a string representing the polynomial
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        //遍历自己，每次修改字符串
        Term termExecuting = this.head;
        if (termExecuting == null) return "empty polynomial";
        while (termExecuting != null) {
            //正数需要显示加号，负数自带减号
            if (termExecuting.getCoef() >= 0) result.append("+");
            result.append(termExecuting.getCoef()).append("x^").append(termExecuting.getExp());
            termExecuting = termExecuting.next();
        }
        return result.toString();
    }

    //查找指数为n的结点,如不存在返回null
    public Term getTerm(int exp) {
        Term term = this.head;
        while (term != null) {//遍历链表
            if (term.getExp() == exp) {
                return term;
            }
            term = term.next();
        }
        return null;
    }

    //查找某结点的上一结点，如不存在返回null
    public Term getPrev(Term term) {
        Term termExecuting = this.head;
        while (termExecuting != null) {
            if (termExecuting.next() == term) return termExecuting;
            termExecuting = termExecuting.next();
        }
        return null;
    }

    public static void main(String[] args) {
        /* You can write your test code here,
         * and you can also use junit.
         * */
        Term term1 = new Term(1,1);
        Term term2 = new Term(10.3,0);
        Term term3 = new Term(90.2,5);
        Term term4 = new Term(-90.2,5);
        Term term5 = new Term(52.23,6);
        Term term6 = new Term(-12.4921,32);
        Term term7 = new Term(3.492,12);
        Polynomial polynomial1 = new Polynomial(term1);
        polynomial1.addTerm(term2);
        polynomial1.addTerm(term3);
        polynomial1.addTerm(term5);
        polynomial1.addTerm(term6);

        Polynomial polynomial2 = new Polynomial(term4);
        polynomial2.addTerm(term7);
        polynomial2.addTerm(term7);

        System.out.println(polynomial1.toString());
        System.out.println(polynomial2.toString());

        polynomial1.add(polynomial2);

        System.out.println(polynomial1.toString());


    }
}