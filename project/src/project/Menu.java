package project;

import java.util.ArrayList;

public class Menu {
    private boolean graphical = false;
    
    public void show() {
        if ( graphical ) {
            showGraphical();
        } else {
            showText();
        }
    }

    public int readSelection() {
        if ( graphical ) {
            return readSelectionGraphical();
        }

        return readSelectionText();
    }

    private void showText() {
        TextInterface.printLine( "The day has ended. " +
                "What would you like to do?" );
        TextInterface.printLine( "1) Exit" );
		TextInterface.printLine( "2) Next day" );
		TextInterface.printLine( "3) Show Store" );
		TextInterface.printLine( "4) Show Storage");
        TextInterface.printLine( "5) Buy ingredients" );
        TextInterface.printLine( "6) Hire new employee" );
        TextInterface.printLine( "7) Fire employee" );
    }

    private void showGraphical() {
        System.err.println( "Graphical menu not implemented");
    }

    public void buyIngredients( Store store ) {
        ArrayList< Ingredient > ingredients = store.getIngredients();

        for ( int i = 0; i < ingredients.size(); i++ ) {
            TextInterface.printLine( ( i + 1 ) + ") "
                    + ingredients.get(i).getName()
                    + ", cost "
                    + ingredients.get(i).getCost());
        }
    }

    private int readSelectionText() {
        return TextInterface.readInt( "" );
    }

    private int readSelectionGraphical() {
        System.err.println( "Graphical menu not implemented" );
        return 0;
    }

    public void setGraphical( boolean graphical ) {
        this.graphical = graphical;
    }
}
