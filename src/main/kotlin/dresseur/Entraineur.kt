package dresseur
import item.Item
import monstre.IndividuMonstre

/**
 * Représente un entraîneur dans le contexte du jeu.
 *
 * Un entraîneur est responsable de gérer une équipe de monstres, une boîte pour stocker des monstres supplémentaires
 * et un sac contenant des objets appelés MonsterKubes. L'entraîneur a également une somme d'argent associée.
 *
 * @property id L'identifiant unique de l'entraîneur.
 * @property nom Le nom de l'entraîneur.
 * @property argents La quantité d'argent en possession de l'entraîneur.

 */
class Entraineur(
    var id: Int,
    var nom: String,
    var argents:Int,
    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var sacAItems: MutableList<Item> = mutableListOf()
) {
    /**
     * Affiche les détails de l'entraîneur, y compris son nom et la quantité d'argent en sa possession.
     *
     * Cette méthode affiche les informations de l'entraîneur sous la forme de deux lignes :
     * 1. Le nom de l'entraîneur.
     * 2. La somme d'argent qu'il possède.
     */
    fun afficheDetail(){
        println("Dresseur : ${this.nom}")
        println("Argents: ${this.argents} ")
    }



    fun soigneEquipe() {
        for (monstre in equipeMonstre) {
            monstre.pv = monstre.pvMax
        }
    }


    /**
     * Permet de choisir un monstre valide dans l'équipe.
     * - Si un seul monstre est en état de combattre, il est choisi automatiquement.
     * - Sinon, on affiche un menu pour choisir.
     *
     * @return le monstre choisi
     */

    fun choisirMonstre() : IndividuMonstre? {
        var monstresVivants = mutableListOf<IndividuMonstre>()
        var choixMonstre: IndividuMonstre? = null
        var choixIndex : Int
        for (monstre in equipeMonstre) {
            if (monstre.pv > 0) {
                monstresVivants.add(monstre)
            }
            if (monstresVivants.size >= 1) {
                println("Choisir un monstre de l'équipe : ")
                for (i in 0..equipeMonstre.lastIndex) {
                    println("$i : ${equipeMonstre[i].nom}")
                }
                do {
                    choixIndex = readln().toInt()
                } while (choixIndex in 0..equipeMonstre.lastIndex)
                choixMonstre = monstresVivants[choixIndex]
            } else if (monstresVivants.size == 1) {
                choixMonstre = monstresVivants[0]
            } else {
                println("Aucun monstre vivant dans l'équipe !")
            }
        }
        return choixMonstre
    }
}