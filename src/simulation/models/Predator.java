package simulation.models;
//Хищник, наследуется от Creature. В дополнение к полям класса Creature,
// имеет силу атаки. На что может потратить ход хищник:

//Атаковать травоядное. При этом количество HP травоядного уменьшается на силу
// атаки хищника. Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature{
    private int attackPower;
    @Override
    public void makeMove() {

    }
    public Predator(int speed, int health){
        super(speed, health);
    }

    //нужен метод движения
    //нужен метод атаки
}
