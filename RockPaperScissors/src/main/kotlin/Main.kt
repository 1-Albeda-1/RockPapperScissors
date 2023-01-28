//Создать консольную игру "Камень-Ножницы-Бумага" (три события).
//Победитель определяется по следующим правилам:
//Бумага побеждает камень («бумага обёртывает камень»).
//Камень побеждает ножницы («камень затупляет или ломает ножницы»).
//Ножницы побеждают бумагу («ножницы разрезают бумагу»).
//Если игроки показали одинаковый знак, то засчитывается ничья и игра переигрывается.


enum class Choice {
    Камень, Ножницы, Бумага;
    companion object {
        val relationships = listOf(
            Relationship(Камень, Ножницы, "ломает"),
            Relationship(Ножницы, Бумага, "разрезают"),
            Relationship(Бумага, Камень, "обёртывает")
        )
    }
}
data class Relationship(val winner: Choice, val loser: Choice, val means: String)

fun getUserChoice(): Choice {
    while (true) {
        println("Пожалуйста, выберите один из следующих вариантов:")
        for ((index, item) in Choice.values().withIndex()) {
            println("${index + 1} . $item")
        }
        val userInput = readLine().toString()
        println("Вы выбрали: $userInput")
        try {
            return Choice.valueOf(userInput)
        } catch (e: IllegalArgumentException) {
            println("Введите вариант ответа!")
        }
    }
}

fun getGameChoice() = Choice.values().random()


fun printResult(gameChoice: Choice, userChoice: Choice) 
{
    println("Выбор игры: $gameChoice")
    val relationship = Choice.relationships.firstOrNull {
        (it.winner == gameChoice && it.loser == userChoice) ||
                (it.loser == gameChoice && it.winner == userChoice)
    }
    when (relationship) 
    {
        null -> println("Ничья!")
        else -> with (relationship)
        {
            println("$winner $means $loser. $winner победил(-а)(-и)!!")
        }
    }
}


fun main() 
{
    println("____ИГРА КАМЕНЬ НОЖНИЦЫ БУМАГА____")
    val gameChoice = getGameChoice()
    val userChoice = getUserChoice()
    printResult(gameChoice, userChoice)
}

