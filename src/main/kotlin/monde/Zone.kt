package monde
import monstre.EspeceMonstre
import monstre.IndividuMonstre
import kotlin.random.Random

/**
 * Représente un lieu où le joueur peut se déplacer et rencontrer des monstres.
 *
 * Une zone contient une liste d’espèces de monstres qui peuvent apparaître et l’expérience moyenne des monstres de cette zone.
 *  Peut pointer vers une zone précédente et une suivante, créant une carte navigable.
 *
 *
 * @property id L'identifiant unique de la zone.
 * @property nom Le nom de la zone.
 * @property expZone L'expérience moyenne des monstres de cette zone.
 * @property zoneSuivante
 * @property zonePrecedente

 */

class Zone (
    var id : Int,
    var nom : String,
    var expZone : Int,
    val especeMonstres : MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante : Zone? = null,
    var zonePrecedente : Zone? = null
) {

    fun genereMonstre(listeMonstres: MutableList<IndividuMonstre>) : IndividuMonstre {

        var idMonstreGenere = 0
        var valeur : Int
        var unique : Boolean
        do {
            valeur = Random.nextInt(10,101)
            unique = true
            for (monstre in listeMonstres) {
                if (monstre.id == valeur) {
                    unique = false
                    break
                }
            }
            if(unique) {
                idMonstreGenere = valeur
            }
        } while(!unique)


    val monstreGenere = IndividuMonstre(
            idMonstreGenere,
            "???",
            especeMonstres.random(),
            null,
            Random.nextDouble(expZone*0.8,expZone*1.2)
    )

        return monstreGenere
    }

    fun rencontreMonstre() {

    }
}