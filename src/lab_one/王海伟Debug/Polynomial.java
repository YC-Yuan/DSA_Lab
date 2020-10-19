package lab1.王海伟Debug;

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
     * @param term new term
     */
    public void addTerm(Term term) {
        System.out.println("Add term running"+term);
    Term oldHead = head;
        if(head.getExp() < term.getExp()){
            setHead(term);
            term.setNext(oldHead);
            System.out.println("add term end 1 successfully");
            return;
        }

        while(head.next() != null){
            if(head.next().getExp()<term.getExp()){
                term.setNext(head.next());
                head.setNext(term);
                head = oldHead;
                System.out.println("add term end 2 successfully");
                return;
            }
            setHead(head.next());
        }
        head = oldHead;
    }

    /**
     * @param another another polynomial
     * @return the sum
     */
    public Polynomial add(Polynomial another) {
        Term oldHead = another.getHead();
        while(another.getHead().next() != null){
            System.out.println("add running in");
            this.addTerm(another.getHead());
            System.out.println("add running out");
            another.setHead(another.getHead().next());
        }
        addTerm(another.getHead());
        another.setHead(oldHead);

        return this;
    }

    /**
     * example: 4.0x^3+3.2x^2-2.1x^1+1.0x^0
     * xample: -12.0x^9-1.0x^7+3.0x^5+10.0x^2+5.0x^0
     * @return a string representing the polynomial
     */
    @Override
    public String toString() {
        String result = "";
        Term theHeadTerm = head;


        result += head.getCoef()+"x^"+head.getExp();
        setHead(head.next());


        while(head.next() != null){
            System.out.println(head.getCoef()+"x"+head.getExp());
            if(head.getCoef()>=0){
                result += "+"+head.getCoef()+"x^"+head.getExp();
            }else{
                result += head.getCoef()+"x^"+head.getExp();
            }
            setHead(head.next());
        }

        if(head.getCoef()>=0){
            result += "+"+head.getCoef()+"x^"+head.getExp();
        }else{
            result += head.getCoef()+"x^"+head.getExp();
        }
        head = theHeadTerm;

        return result;
    }

    public static void main(String[] args) {
        /* You can write your test code here,
         * and you can also use junit.
         * */
        Term term1 = new Term(1, 1);
        Term term2 = new Term(1, 2);

        Polynomial polynomial=new Polynomial(term1);

        polynomial.addTerm(term2);
        System.out.println(polynomial);

        polynomial.addTerm(term1);
        polynomial.addTerm(term2);
        System.out.println(polynomial);

    }
}
