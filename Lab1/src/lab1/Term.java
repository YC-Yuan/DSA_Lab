package lab1;

public class Term {
    private double coef;
    private int exp;
    private Term next;

    public Term(double coef,int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    /**
     * @return coef
     */
    public double getCoef() {
        return this.coef;
    }

    /**
     * @return exp
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * @param coef new coef
     */
    public void setCoef(double coef) {
        this.coef = coef;
    }

    /**
     * @param exp new exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * @return next term
     */
    public Term next() {
        return this.next;
    }

    /**
     * @param next next term
     */
    public void setNext(Term next) {
        this.next = next;
    }
}
