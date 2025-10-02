import dresseur.Entraineur
import item.Badge
import item.MonsterKube
import jeu.Partie
import monde.Zone
import monstre.EspeceMonstre
import monstre.IndividuMonstre
import jeu.*
import monde.Ville
import monstre.PalierEvolution

//Déclarations d'objets et de variables
public var joueur = Entraineur(1, "Sacha", 100)

var especeSpringleaf = EspeceMonstre(1,"springleaf","graine",60,9,11,10,12,14,34.0,6.5,9.0,8.0,7.0,10.0,"Petit monstre espiègle rond comme une graine, adore le soleil.","Sa feuille sur la tête indique son humeur.","Curieux, amical, timide")
var especeFlamkip = EspeceMonstre(4,"flamkip","animal",50,12,8,13,16,7,22.0,10.0,5.5,9.5,9.5,6.5,"Petit animal entouré de flammes, déteste le froid.","Sa flamme change d’intensité selon son énergie.","Impulsif, joueur, loyal")
var especeAquamy = EspeceMonstre(7,"aquamy","meteo",55,10,11,9,14,14,27.0,9.0,10.0,7.5,12.0,12.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.","Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")
var especeLaoumi = EspeceMonstre(8,"laoumi","animal",58,11,10,9,8,11,23.0,11.0,8.0,7.0,6.0,11.5,"Petit ourson au pelage soyeux, aime se tenir debout.","Son grognement est mignon mais il protège ses amis.","Affectueux, protecteur, gourmand")
var especeBugsyface = EspeceMonstre(10,"bugsyface","insecte",45,10,13,8,7,13,21.0,7.0,11.0,6.5,8.0,11.5,"Insecte à carapace luisante, se déplace par bonds et vibre des antennes.","Sa carapace devient plus dure après chaque mue.","Travailleur, sociable, infatigable")
var especeGalum = EspeceMonstre(13,"galum","minéral",55,12,15,6,8,12,13.0,9.0,13.0,4.0,6.5,10.5,"Golem ancien de pierre, yeux lumineux en garde.","Peut rester immobile des heures comme une statue.","Sérieux, stoïque, fiable")
val especePyrokip = EspeceMonstre(id = 5, nom = "pyrokip", type = "Animal", baseAttaque = 18, baseDefense = 12, baseVitesse = 15, baseAttaqueSpe = 22, baseDefenseSpe = 11, basePv = 70, modAttaque = 12.0, modDefense = 8.0, modVitesse = 11.0, modAttaqueSpe = 12.5, modDefenseSpe = 8.0, modPv = 15.0, description = "Pyrokip, l’évolution de Flamkip. Son feu est devenu intense et ses flammes sont capables de fondre la pierre. Fier et courageux, il protège son dresseur à tout prix.", particularites = "Ses flammes changent de couleur selon son humeur : rouge vif en colère, dorées quand il est calme.", caractères = "Fier, protecteur, explosif.")//, elements = mutableListOf(feu))



var route1 = Zone(1,"bramblewood",20,mutableListOf(especeSpringleaf,especeBugsyface))
var route2 = Zone(2,"moonwood",45,mutableListOf(especeGalum))
var racailleCity = Ville(3,"RacailleCity",30,mutableListOf(especeFlamkip))


var objet1 = MonsterKube(1,"cube","description",280.0)
val badge = Badge(1,"Badge Roche","Badge gagné lorsque le joueur atteint l'arène de pierre.")

var palierEvolutionFlamkip = PalierEvolution(1,7,especePyrokip)

//Fonctions et méthodes

/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */

fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron" -> "\u001B[38;2;150;75;0m"
        else -> "" // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}

fun nouvellePartie(): Partie {
    println("Quel est ton nom ?")
    val nomJoueur = readln()
    joueur.nom = nomJoueur
    val partie = Partie(1, joueur, route1)
    return partie
}




//main
fun main() {
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1
    route2.zoneSuivante = racailleCity
    racailleCity.zonePrecedente = route2



    val monstre1 = IndividuMonstre(1, "springleaf", especeSpringleaf, null, 1500.0)
    val monstre2 = IndividuMonstre(2, "flamkip", especeFlamkip, null, 1500.0)
    val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy, null, 1500.0)

    especeFlamkip.palierEvolution=palierEvolutionFlamkip

    joueur.sacAItems.add(objet1)


    val partie = nouvellePartie()
    partie.choixStarter()
    println("Équipe du joueur : ${joueur.equipeMonstre.map { it.nom }}")
    for (monstre in joueur.equipeMonstre) {
        println("${monstre.nom} a ${monstre.pv} PV")
    }

    partie.jouer()
}





    ////////TESTS////////
    /*
    monstre1.exp = 2000.0
    monstre2.pv = 50
    monstre2.pv = -5
    monstre1.attaquer(monstre2)
    monstre1.renommer()
    print("Nom monstre1 : ${monstre1.nom}")
    monstre1.afficheDetail()
    objet1.utiliser(monstre1)
    */

