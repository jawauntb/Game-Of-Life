public class ZombieCell extends Cell{

	public String toString () {
	
	   return "x"; 
	}
	
	public void evolve () {
		_isAlive = false;
	
		_willBeAlive = false;
	
		if(_isAlive==true || _willBeAlive==true){
			throw new IllegalArgumentException ("Zombie cell cannot be alive");
		}
	}
}
