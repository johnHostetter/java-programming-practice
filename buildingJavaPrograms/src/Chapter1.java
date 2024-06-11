public class Chapter1 {

    public static void demo() {
        drawDiamond();
        drawX();
//        recursion();
    }

    public static void recursion() {
        recursion();
    }

    public static void drawDiamond() {
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
        System.out.println(" \\    /");
        System.out.println("  \\  /");
        System.out.println("   \\/");

    }

    public static void drawX() {
        System.out.println(" \\    /");
        System.out.println("  \\  /");
        System.out.println("   \\/");
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
    }
}
