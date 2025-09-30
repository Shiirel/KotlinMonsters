package monstre
import java.io.File

/**
 * Représente les différentes espèces de monstres dans le contexte du jeu.
 *
 * Une espèce de monstre contient ses statistiques de base, ses multiplicateurs de croissance,
 * son nom, son type et éventuellement son art ASCII.
 *
 * @property id L'identifiant unique de l'espèce.
 * @property nom Le nom de l'espèce.
 * @property type Le type de l'espèce.
 * @property baseAttaque
 * @property baseDefense
 * @property baseVitesse
 * @property baseAttaqueSpe
 * @property baseDefenseSpe
 * @property basePv
 * @property modAttaque
 * @property modDefense
 * @property modVitesse
 * @property modAttaqueSpe
 * @property modDefenseSpe
 * @property modPv
 * @property description
 * @property particularites
 * @property caractères
 */

class EspeceMonstre (
    var id : Int,
    var nom: String,
    var type: String,
    val basePv: Int,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val modPv: Double,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
    var palierEvolution: PalierEvolution? = null
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}