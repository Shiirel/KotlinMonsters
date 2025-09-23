package item
import joueur
import monstre.IndividuMonstre
import kotlin.random.Random

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {

    override fun utiliser(cible: IndividuMonstre): Boolean {
        val res = true
        println("Vous lancez le Monster Kube !")
        if(cible.entraineur != null) {
            print("Le monstre ne peut pas être capturé.")
        } else {
            val ratioVie = cible.pv/cible.pvMax
            var chanceEffective = chanceCapture * (1.5 - ratioVie)
            if (chanceEffective < 5.0) {
                chanceEffective = 5.0
            }
            val nbAleatoire = kotlin.random.Random.nextInt(0,101)
            if (nbAleatoire<chanceEffective) {
                println("Le monstre est capturé !")
                print("Saisir un nouveau nom : ")
                val nouveauNom = readln()
                if(nouveauNom.isNotEmpty()) {
                    cible.nom = nouveauNom
                }
                if (joueur.equipeMonstre.size >= 6) {
                    joueur.boiteMonstre += cible
                } else {
                    joueur.equipeMonstre += cible
                }
                cible.entraineur = joueur
                val res = true
            } else {
                print("Presque ! Le Kube n'a pas pu capturer le monstre !")
                val res = false
            }
        }
        return res
    }
}