import java.util.Scanner;

public class Main {

    private static int reverseInt(int input){

        int reverse = 0;
        while (input != 0) {
            int digit = input % 10;
            reverse = reverse * 10 + digit;
            input /= 10;
        }

        System.out.println("Reversed: " + reverse);
        return reverse;
    }

    private static int getNumSign(int input){
        if (input > 0) {
            System.out.println("Integer is positive");
            return 1;
        }else if (input < 0) {
            System.out.println("Integer is negative");
            return -1;
        }else{
            System.out.println("Integer is 0");
            return 0;
        }
    }

    private static void printHalfPyramid(){
        final int height = 5; final char ch = '*';

        for (int i = 0; i < height; i++) {
            for (int j = -1; j < i; j++) {
                System.out.print(ch + " ");
            }
            System.out.print("\n");
        }
    }

    private static void printPyramid() {
        final int height = 5;

        for (int i = 0; i < height + 1; i++) {

            int s, s1 = 0;
            for (s = 0; s < height - i + 1; s++) {
                System.out.print("  ");
            }

            for (int c = 0; c < 2 * i - 1; c++) {

                if (s <= height - 1) {
                    System.out.print((i + c) + " ");
                    s++;
                } else {
                    System.out.print((i + c - 2 * s1) + " ");
                    s1++;
                }

            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args){

        System.out.print("Enter an integer: ");
        Scanner sinput = new Scanner(System.in);
        int input = sinput.nextInt(); sinput.close();
        reverseInt(input);
        getNumSign(input);

        printHalfPyramid();
        printPyramid();

    }

}