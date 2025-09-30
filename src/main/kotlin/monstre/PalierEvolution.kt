package monstre

class PalierEvolution (
    var Id : Int,
    var niveauRequis : Int,
    var evolution : EspeceMonstre
) {
    fun peutEvoluer(individuMonstre: IndividuMonstre) : Boolean {
        if(individuMonstre.niveau == niveauRequis) {
            return true
        } else {
            return false
        }
    }
}
