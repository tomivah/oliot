package project;

public class Project {
    public static void main( String[] args ) {
        Menu menu = new Menu();
        
        while( true ) {
            menu.show();

            switch( menu.readSelection() ) {
                case 1:
                    System.exit( 0 );
                default:
                    TextInterface.printLine( "Invalid selection" );
            }
			System.out.println("Testiä vain");
        }
    }
}
