package simulation.models;
//Хищник, наследуется от Creature. В дополнение к полям класса Creature,
// имеет силу атаки. На что может потратить ход хищник:

import simulation.Coordinates;
import simulation.GameBoard;

//Атаковать травоядное. При этом количество HP травоядного уменьшается на силу
// атаки хищника. Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature {
    private final int attackPower;

    @Override
    public void makeMove() {

    }

    @Override
    public void makeMove(GameBoard board) {

    }

    public Predator(Coordinates coordinates, int speed, int health, int attackPower) {
        super(coordinates, speed, health);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    //нужен метод движения
    //нужен метод атаки
}
