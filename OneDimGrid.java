public class OneDimGrid extends Grid {
    public OneDimGrid (int rows, String cellType) {

    	if (rows <= 0) {
    		Support.abort("Cannot construct a grid of size " + rows);
    	}

    	_array = new Cell[rows];

	for (int row = 0; row < rows; row += 1) {
	    _array[row] = Cell.create(cellType);
	}
   
    public int getRows () {
    	return _array.length;
    } 


    public Cell getCell (int row) {

		if ((row >= 0) && (row < getRows())) {
		    return _array[row];
		} else {
		    return null;
		}
    } 

    private Cell[] _array;
}
