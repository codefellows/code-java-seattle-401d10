package dankery;

// `static` means: you can use this variable or method
// even if there is no instance of the class
// methods and variables that are not static belong to the instance

//static methods can only reference static class variables
public class Pastry {
//    Define state and behavior: things true of pastries, things that we can do with pastries
    String shape;
    String topping;
    float cost;
    boolean gluten;
    static boolean baked = true;
    static String taste = "yummy";

//    Default constructor always public: returns and has the name of the Class
//    This exists on every class we have defined, its just hidden
    public Pastry(){

    }

    public Pastry(String shape, String topping, float cost, boolean gluten) {
        this.shape = shape;
        this.topping = topping;
        this.cost = cost;
        this.gluten = gluten;
    }

    @Override
    public String toString(){
        return String.format(
                "shape : %s, topping: %s, price : %f, gluten: %b, baked : %b, taste : %s",
                this.shape,
                topping,
                cost,
                this.gluten,
                baked,
                this.taste
        );
    }


    public void eat(){
        System.out.println(
                "aaaaaaaah, don't eat me, I don't want to be digested!!! I am just a poor, sad, " +
                shape +
                " " +
                taste +
                " pastry!");
    }
}
