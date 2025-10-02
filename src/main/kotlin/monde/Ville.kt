package monde
import monde.*
import monstre.EspeceMonstre

class Ville(id : Int, nom : String, expZone : Int, especeMonstres : MutableList<EspeceMonstre> = mutableListOf(), zoneSuivante : Zone? = null, zonePrecedente : Zone? = null) : Zone(id,nom,expZone,especeMonstres,zoneSuivante,zonePrecedente) {

}