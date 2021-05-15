public class BasicClass {
    public static void main(String[] args) {
        BoxV1 x  = new BoxV1();

    }
}

class BoxV1 {

    /* Class is being used to containts Data + Methods. */

    /* Data Field */
    private double w, h, d;
    private String name;

    /* Constructor is being used to create an instance of class */
    /* and will be invoke using the 'new' keyword. */
    public BoxV1() {
        this(null,null,null,null);
    }

    /* Overloding Constructor */
    public BoxV1(Object w, Object h, Object d,Object name) {
        this.w = (double)w;
        this.h = (double)h;
        this.d = (double)d;
        this.name = (String)name;
    }

    public BoxV1(String name) {
        this(null,null,null,name);
    }

    /* Getters and Setters, also known as Accessors and Modifier */
    public double getVolume() {
        return this.w * this.d * this.h;
    }

    public double getD() {
        return d;
    }

    public double getH() {
        return h;
    }

    public String getName() {
        return name;
    }

    public double getW() {
        return w;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setW(double w) {
        this.w = w;
    }



}