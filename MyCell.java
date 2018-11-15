public class MyCell extends Cell{

	public String toString () {

		if (_isAlive) {
			return "$";
		} else {
			return "^";
		}
	}


	public void evolve () {

		int l = countLiveNeighbors();

		if(l<=4 ){
			_willBeAlive = true;
		}
	
		if(l==6 | l>=8) {
			if(_isAlive==true){
				_willBeAlive=true;
			}
		} else {_willBeAlive=false;
		}

	}	
}




