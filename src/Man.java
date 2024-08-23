
import java.util.List;

public class Man {
    private String name;
    private int age;
    private List<String> favoriteBooks;
    Boolean adult;


    public Man[] getChilds() {
        return childs;
    }

    public void setChilds(Man[] childs) {
        this.childs = childs;
    }

    private Man[] childs;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Man[] getBrothers() {
        return brothers;
    }

    public void setBrothers(Man[] brothers) {
        this.brothers = brothers;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    private Man[] brothers;
    private Double weight;

    public Man(String name, int age, List<String> favoriteBooks, Man[] childs, Man[] brothers, Boolean adult, Double weight) {
        this.name = name;
        this.age = age;
        this.favoriteBooks = favoriteBooks;
        this.childs = childs;
        this.brothers = brothers;
        this.adult = adult;
        this.weight = weight;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

}
