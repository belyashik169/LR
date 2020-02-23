package one;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args){
        args = new String[]{"java", "ded", "a luna kanula"};
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.print(args[i]+" — ");
            isPalindrome(s);
        }
        Scanner sc = new Scanner(System.in);
        while (true){
            String j=sc.next();
            isPalindrome(j);
            if (j.equals("stop")) break;
        }
    }
    public static String reverseString(String s){
        String k = "";
        for (int i = s.length() - 1; i >= 0; --i) k += s.charAt(i);
        return k;
    }
    public static Boolean isPalindrome(String s) {
        if(s.equals(reverseString(s))) System.out.println("Полиндром");
        else System.out.println("Не полиндром");
        return s.equals(reverseString(s));
    }
}
