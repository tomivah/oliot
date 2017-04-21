package project;

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
    }
    
    private void showGraphical() {
        System.err.println( "Graphical menu not implemented");
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
