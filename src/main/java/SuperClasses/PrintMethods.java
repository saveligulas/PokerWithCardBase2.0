package SuperClasses;

public final class PrintMethods {
    public static void printFiller(int i,String s) {
        System.out.println();
        for(int j = 0; j<i; j++) {
            System.out.print(s);
        }
    }
}
