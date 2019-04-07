package ch4.item17;

// Immutable class with static factories instead of constructors
public  class ComplexNotFinal {
    private final double re;
    private final double im;

    private ComplexNotFinal(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static ComplexNotFinal valueOf(double re, double im) {
        return new ComplexNotFinal(re, im);
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public ComplexNotFinal plus(ComplexNotFinal c) {
        return new ComplexNotFinal(re + c.re, im + c.im);
    }

    public ComplexNotFinal minus(ComplexNotFinal c) {
        return new ComplexNotFinal(re - c.re, im - c.im);
    }

    public ComplexNotFinal times(ComplexNotFinal c) {
        return new ComplexNotFinal(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public ComplexNotFinal dividedBy(ComplexNotFinal c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new ComplexNotFinal((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ComplexNotFinal)) return false;
        ComplexNotFinal c = (ComplexNotFinal) o;
        // See page 47 to find out why we use compare instead of ==
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
