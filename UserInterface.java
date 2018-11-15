// =============================================================================
/**
 * The <code>UserInterface</code> for the <i>Game of Life</i>.  A
 * <code>UserInterface</code> controls the progression from one generation to
 * the next, displaying the state at each generation.
 **/
// =============================================================================




// =============================================================================
// IMPORTS

// =============================================================================



// =============================================================================
public abstract class UserInterface {
// =============================================================================



    // =========================================================================
    /**
     * The constructor.  Hold onto a pointer to the <code>Game</code> for which
     * this <code>UserInterface</code> is providing interaction.
     *
     * @param game The <code>Game</code> whose state to draw.
     **/
    public UserInterface (Game game) {

	_game = game;

    } // UserInterface ()
    // =========================================================================



    // =========================================================================
    /**
     * Display the state of the <code>Cell</code>s in the <code>Grid</code>.
     **/
    public void display ();
    // =========================================================================



    // =========================================================================
    /**
     * Keep control of the program until it is time to advance the state of the
     * <code>Game</code>.
     **/
    public void triggerMove () {

	// Do nothing.  Just let it advance as quickly as it wants.
	
    }
    // =========================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The <code>Game</code> that this interface is controlling.
     **/
    protected Game _game;
    // =========================================================================



// =============================================================================
} // class UserInterface
// =============================================================================
