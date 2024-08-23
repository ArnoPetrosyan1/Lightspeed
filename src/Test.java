
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        List books = new ArrayList();
        books.add("Xorhrdavor kxzi");
        books.add("Koms Monte Qristo");

        List books1 = new ArrayList();
        books1.add("Gandzeri kxzi");

        List books2 = new ArrayList();
        books2.add("Navapet Grandi Vodisakan");

        Man child1 = new Man("Samvel Karapetyan", 12, books1, null, null, false, 25.6);
        Man[] brothers = {child1};
        Man child2 = new Man("David Karapetyan", 15, books2, null, brothers, false, 33.12);

        Man[] childs = {child1, child2};
        Man man = new Man("Karen Karapetyan", 55, books, childs, null, true, 65.76);
        Man cloned = CopyUtils.deepCopy(man);
        System.out.println("Done");

    }
}
