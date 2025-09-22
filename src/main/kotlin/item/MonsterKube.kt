package item
import item.*

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {}