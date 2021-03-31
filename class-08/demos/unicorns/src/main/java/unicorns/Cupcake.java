package unicorns;

public class Cupcake {
    String name;
    String color;
    boolean sparkles;
    String flavor;

    public Cupcake(String name, String color, boolean sparkles, String flavor) {
        this.name = name;
        this.color = color;
        this.sparkles = sparkles;
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "I am the cupcake toString ::: Cupcake{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", sparkles=" + sparkles +
                ", flavor='" + flavor + '\'' +
                '}';
    }
}
