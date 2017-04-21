package project;

import java.util.Scanner;

public class TextInterface {
    private static Scanner scanner = new Scanner( System.in );

    public static void printLine( String string ) {
        System.out.println( string );
    }

    public static void print( String string ) {
        System.out.print( string );
    }

    public static String read( String message ) {
        print( message );
        return scanner.next();
    }

    public static String readLine( String message ) {
        print( message );
        return scanner.nextLine();
    }

    public static int readInt( String message ) {
        print( message );
        return scanner.nextInt();
    }

    public static float readFloat( String message ) {
        print( message );
        return scanner.nextFloat();
    }

    public static double readDouble( String message ) {
        print( message );
        return scanner.nextDouble();
    }
}
