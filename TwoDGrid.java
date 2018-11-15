public class TwoDGrid extends Grid {

  
public TwoDGrid (int rows, int columns, String cellType) {


	if ((rows <= 0) || (columns <= 0)) {
		Support.abort("Cannot construct a grid of size " + rows + ", " + columns);
	}
	
	_array = new Cell[rows][columns];
	
	for (int row = 0; row < rows; row += 1) {
		for (int col = 0; col < columns; col += 1) {
		   _array[row][col] = Cell.create(cellType);
		}
	}
	
	  
	
	 public int getRows () {
	
		return _array.length;
	 } 
	    
	
	  public int getColumns () {
		return _array[0].length;
	  } 
	    
	  private Cell[][] _array;
}
