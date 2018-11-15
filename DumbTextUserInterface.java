public class DumbTextUserInterface extends UserInterface {

 public DumbTextUserInterface (Game game) {
  _game = game;
  } 

  public void display () {
    System.out.println("Generation = " + _game.getGeneration() +
  ", Population = " + _game.getPopulation());

    for (int row = 0; row < _game.getRows(); row++) {
      for (int column = 0; column < _game.getColumns(); column++) {
        Cell cell = _game.getCell(row, column);
          System.out.print(cell);
        }
     System.out.println();
    }

    System.out.println();
  }
 

} 
