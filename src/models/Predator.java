package models;
//Хищник, наследуется от Creature. В дополнение к полям класса Creature,
// имеет силу атаки. На что может потратить ход хищник:

//Атаковать травоядное. При этом количество HP травоядного уменьшается на силу
// атаки хищника. Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature{
    private int attackPower;
    @Override
    protected void makeMove() {

    }

    //нужен метод движения
    //нужен метод атаки
}
