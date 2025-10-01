package monstre
import dresseur.Entraineur
import monstre.EspeceMonstre
import kotlin.Int
import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.round
import monstre.PalierEvolution

class IndividuMonstre (
    var id : Int,
    var nom : String,
    var espece : EspeceMonstre,
    var entraineur : Entraineur? = null,
    expInit : Double
) {
    var niveau: Int = 1
    var attaque: Int = Random.nextInt(espece.baseAttaque - 2, espece.baseAttaque + 3)
    var defense: Int = Random.nextInt(espece.baseDefense - 2, espece.baseDefense + 3)
    var vitesse: Int = Random.nextInt(espece.baseVitesse - 2, espece.baseVitesse + 3)
    var attaqueSpe: Int = Random.nextInt(espece.baseAttaqueSpe - 2, espece.baseAttaqueSpe + 3)
    var defenseSpe: Int = Random.nextInt(espece.baseDefenseSpe - 2, espece.baseDefenseSpe + 3)
    var pvMax: Int = Random.nextInt(espece.basePv - 5, espece.basePv + 6)
    var potentiel: Double = Random.nextDouble(0.5, 3.0)

    /**
     *  @property exp expérience.
     * Quand l'exp atteint un palier, la méthode levelUp() est appelée
     */
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            var estNiveau1 = false
            if (niveau == 1) {
                estNiveau1 = true
            }
            do {
                levelUp()
                if (estNiveau1 == false) {
                    println("Le monstre $nom est maintenant niveau $niveau")
                    break

                }
            } while (field >= palierExp(niveau))
        }


    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            if (nouveauPv >= 0 && nouveauPv <= pvMax) {
                field = nouveauPv
            }
        }


    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }


    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100 * (niveau - 1).toDouble().pow(2.0)
    }

    /**
     * Augmente le niveau d'un monstre et calcule les nouvelles valeurs de ses
     * caractéristiques.
     *
     * @return le niveau augmenté.
     */
    fun levelUp() {
        niveau += 1
        if (espece.palierEvolution != null) {
            if (espece.palierEvolution!!.peutEvoluer(this)) {
                evoluer()
            }

            attaque += round(espece.modAttaque * potentiel).toInt() + Random.nextInt(-2, 3)
            defense += round(espece.modDefense * potentiel).toInt() + Random.nextInt(-2, 3)
            vitesse += round(espece.modVitesse * potentiel).toInt() + Random.nextInt(-2, 3)
            attaqueSpe += round(espece.modAttaqueSpe * potentiel).toInt() + Random.nextInt(-2, 3)
            defenseSpe += round(espece.modDefenseSpe * potentiel).toInt() + Random.nextInt(-2, 3)

            val ancienPvMax = pvMax
            pvMax += round(pvMax * potentiel).toInt() + Random.nextInt(-5, 6)
            pv += pvMax - ancienPvMax
        }
    }


        /**
         * Attaque un autre [IndividuMonstre] et inflige des dégâts.
         *
         * Les dégâts sont calculés de manière très simple pour le moment :
         * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
         *
         * @param cible Monstre cible de l'attaque.
         */
        fun attaquer(cible: IndividuMonstre) {
            val degaBrut = this.attaque
            var degatTotal = degaBrut - (cible.defense / 2)
            if (degatTotal < 1) {
                degatTotal = 1
            }
            val pvAvant = cible.pv
            cible.pv -= degatTotal

            val pvApres = cible.pv
            println("$nom inflige ${pvAvant - pvApres} dégâts à ${cible.nom}")
        }

        /**
         * Demande au joueur de renommer le monstre.
         * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
         */
        fun renommer() {
            println("Renommer $nom ?")
            print("Nouveau nom : ")
            val nouveauNom = readln()
            if (nouveauNom.isNotEmpty()) {
                this.nom = nouveauNom
            }
        }


        /**
         * Affiche les caractéristiques du monstre et son art.
         */
        fun afficheDetail() {
            val listeDetails = mutableMapOf<String, String>(
                "Nom: " to this.nom,
                "Niveau: " to this.niveau.toString(),
                "Exp: " to this.exp.toString(),
                "PV: " to this.pv.toString(),
            )
            val listeDetails2 = mutableMapOf<String, String>(
                "Atq: " to this.attaque.toString(),
                "Def: " to this.defense.toString(),
                "Vitesse: " to this.vitesse.toString(),
                "AtqSpe: " to this.attaqueSpe.toString(),
                "DefSpe: " to this.defenseSpe.toString()
            )
            print(this.espece.afficheArt())
            println("=================================================")
            println(listeDetails)
            println("=================================================")
            println(listeDetails2)
            println("=================================================")
        }

        fun evoluer() {
            this.espece = espece.palierEvolution!!.evolution
            println("Le monstre a évolué en ${this.espece}")
        }
}