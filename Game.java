// =============================================================================
/**
 * The <code>Game</code> class.  Manage a single <i>Game of Life</i> by loading
 * the initial grid of cells and evolving them as requested.
 *
 * @author Scott F. Kaplan -- sfkaplan@cs.amherst.edu
 **/
// =============================================================================



// =============================================================================
// IMPORTS

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
// =============================================================================



// =============================================================================
public class Game {
// =============================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The grid itself.
     **/
    private Grid _grid;

    /**
     * The current generation number.
     **/
    private int _generation;

    /**
     * The <code>UserInterface</code> that controls and displays the game.
     **/
    private UserInterface _userInterface;
    // =========================================================================



    // =========================================================================
    /**
     * The constructor.  Read the initial state of a game from a provided
     * pathname, creating the <code>Grid</code> and the specified live
     * <code>Cell</code>s.
     *
     * @param initialStatePath The file from which the initial state of the
     *                         universe is taken.
     * @param cellType The kind of cell to create.
     **/
    public Game (String initialStatePath, String cellType) {

	// Read the initial state, creating a grid of cells as specified.
	readInitialState(initialStatePath, cellType);

	// Create the user interface.
	_userInterface = new UserInterface(this);
	
	// Start counting at generation 0.
	_generation = 0;

    } // Game()
    // =========================================================================



    // =========================================================================
    /**
     * Read the initial state file, creating a <code>Grid</code> and
     * initializing it with <code>Cell</code>s as specified by that file.
     *
     * @param initialStatePath The file from which the initial state of the
     *                         universe is taken.
     * @param cellType The kind of cell with which to populate the grid.
     **/
    private void readInitialState (String initialStatePath, String cellType) {

	// Create the reader for this file.
	Scanner reader = null;
	try {
	    reader = new Scanner(new File(initialStatePath));
	} catch (FileNotFoundException e) {
	    Support.abort("ERROR: File not found: " + initialStatePath);
	}

	// Read the first line, which contains the dimensions of the grid.
	int rows    = -1;
	int columns = -1;
	try {
	    rows    = reader.nextInt();
	    columns = reader.nextInt();
	} catch (InputMismatchException e) {
	    Support.abort("ERROR: Invalid dimensions at line 1");
	}

	// Create a Grid with these dimensions.
	_grid = new Grid(rows, columns, cellType);

	// Read coordinates for initially live cells until the end-of-file is
	// reached.
	int lineNumber = 2;
	while (true) {

	    int row = -1;
	    int col = -1;
	    try {
		row = reader.nextInt();
		col = reader.nextInt();
	    } catch (InputMismatchException e) {
		Support.abort("ERROR: From initial state file, " +
			      "could not read coordinates at line " +
			      lineNumber);
	    } catch (NoSuchElementException e) {
		break;
	    }
	    
	    // Set the cell to be alive.
	    System.out.printf("DEBUG: Setting initially live cell at " + row + ", " + col + "\n");
	    _grid.getCell(row, col).makeAlive();

	    lineNumber += 1;

	}

    } // readInitialState ()
    // =========================================================================



    // =========================================================================
    /**
     * Evolve a game of Life through its generations, emitting the state of the
     * game at each generation.  Evolution will continue until the universe
     * becomes static or until the given maximum number of generations is
     * reached.
     *
     * @param generations The number of generations to evolve.
     **/
    public void play (int generations) {

	// Show the initial state.
	_userInterface.display();

	// Loop through the generations.
	for (_generation = 0; _generation < generations; _generation += 1) {

	    // Wait for approval for the next move.
	    _userInterface.triggerMove();

	    // Evolve the game by one step and show it.
	    evolve();
	    _userInterface.display();

	} // evolution loop

    } // play ()
    // =========================================================================



    // =========================================================================
    /**
     * Evolve the state of the game's universe from the current generation to
     * the next.
     **/
    public void evolve () {

	// First, make each cell determine its state in the next generation.
	for (int row = 0; row < _grid.getRows(); row += 1) {
	    for (int col = 0; col < _grid.getColumns(); col += 1) {

		Cell currentCell = _grid.getCell(row, col);
		currentCell.evolve();

	    }
	}

	// Second, revisit each cell to have it advance to its next state.
	for (int row = 0; row < _grid.getRows(); row += 1) {
	    for (int col = 0; col < _grid.getColumns(); col += 1) {

		Cell currentCell = _grid.getCell(row, col);
		currentCell.advance();

	    }
	}
	
	
	// Increment the generation count.
	_generation += 1;

    } // evolve ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the current generation number.
     *
     * @return The current generation number for this universe.
     **/
    public int getGeneration () {

	return _generation;

    } 
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of live cells.
     *
     * @return The number of live cells in the current universe.
     **/ 
    public int getPopulation () {

	// Traverse the grid, counting live cells.
	int population = 0;
	for (int row = 0; row < _grid.getRows(); row += 1) {
	    for (int col = 0; col < _grid.getColumns(); col += 1) {

		if (_grid.getCell(row, col).isAlive()) {
		    population += 1;
		}

	    }
	}

	return population;

    } // getPopulation()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of rows in the universe.
     *
     * @return The number of rows in the universe.
     **/
    public int getRows () {

	return _grid.getRows();

    } // getRows()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of columns in the universe.
     *
     * @return The number of columns in the universe.
     **/
    public int getColumns () {

	return _grid.getColumns();

    } // getColumns()
    // =========================================================================



    // =========================================================================
    /**
     * Provide direct access to a <code>Cell</code> from this universe.
     *
     * @param row The row coordinate of the <code>Cell</code>.
     * @param column The column coordinate of the <code>Cell</code>.
     **/
    public Cell getCell (int row, int column) {

	return _grid.getCell(row, column);

    } // getCell()
    // =========================================================================



// =============================================================================
} // class Game
// =============================================================================
