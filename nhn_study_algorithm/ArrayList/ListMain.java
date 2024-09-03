public class ListMain {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");

        for (String string : list) {
            System.out.println(string);
        }

        System.out.println("-----get-----");
        System.out.println(list.get(2));

        System.out.println("-----size-----");
        System.out.println(list.size());

        System.out.println("-----remove-----");
        list.remove(2);
        for (String string : list) {
            System.out.println(string);
        }

        System.out.println("-----isEmpty-----");
        System.out.println(list.isEmpty());

        System.out.println("-----clear-----");
        list.clear();
        for (String string : list) {
            System.out.println(string);
        }
    }
}
