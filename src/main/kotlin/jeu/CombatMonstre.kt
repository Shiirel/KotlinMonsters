package jeu

import item.Utilisable
import joueur
import monstre.EspeceMonstre
import monstre.IndividuMonstre


class CombatMonstre (
    var monstreJoueur : IndividuMonstre,
    var monstreSauvage : IndividuMonstre,
) {
    var round  : Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver() : Boolean {
        var res = true
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) {
                res = false
            }
        }
        return res
    }


    /**
     * Indique si le joueur a gagné le combat.
     *
     * Conditions pour que le joueur gagne le combat :
     *  - Capturer le monstre sauvage
     *  - Ou amener les pv du monstre sauvage à 0
     * @return `true` si le joueur a gagné, sinon `false`.
     */
    fun joueurGagne() : Boolean {
        if (monstreSauvage.pv <= 0) {
            println("${joueur.nom} a gagné !")
            val gainExp = monstreSauvage.exp * 0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne $gainExp exp.")
            return true
        } else {
            if (monstreSauvage.entraineur == joueur) {
                println("${monstreSauvage.nom} a été capturé !")
                return true
            } else {
                return false
            }
        }
    }


    fun actionAdversaire() {
        if(monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    fun actionJoueur() : Boolean {
        gameOver()
        if (gameOver() == true) {
            return false
        } else {
            println("Menu : 1 -> Attaquer, 2 -> Utiliser un objet, 3 -> Changer de monstre")
            var choixAction = readln().toInt()
            if (choixAction == 1) {
                monstreJoueur.attaquer(monstreSauvage)
            } else if(choixAction == 2) {
                println(joueur.sacAItems)
                println("Saisir le nom de l'item : ")
                var indexChoix = readln().toInt()
                var objetChoisi = joueur.sacAItems[indexChoix]

                if(objetChoisi is Utilisable) {
                    val captureReussi = objetChoisi.utiliser(monstreSauvage)
                    if (captureReussi == true) {
                        return false
                    }
                } else {
                    println("Objet non utilisable")
                }
            } else if (choixAction == 3) {
                val listeMonstres : MutableList<IndividuMonstre> = mutableListOf()
                for (monstre in joueur.equipeMonstre)
                    if (monstre.pv <= 0) {
                        listeMonstres.add(monstre)
                    }
                println(listeMonstres)
                println("Saisir l'index du monstre : ")
                val indexChoix = readln().toInt()
                val choixMonstre = joueur.equipeMonstre[indexChoix]

                if(choixMonstre.pv<=0) {
                    println("Impossible ! ce monstre est KO")
                } else {
                    println("$choixMonstre remplace ${monstreJoueur}")
                    monstreJoueur=choixMonstre
                }
            }
        }
        return true
    }

    fun afficheCombat() {
        println("=============Début Round : $round=============")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv/monstreSauvage.pvMax}")
        println("${monstreSauvage.espece.afficheArt()}")
        println("${monstreSauvage.espece.afficheArt(false)}")
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv/monstreJoueur.pvMax}")
    }


    fun jouer() {
        var joueurPlusRapide = monstreJoueur.vitesse >= monstreSauvage.vitesse
        afficheCombat()
        if(joueurPlusRapide) {
            var continuer = actionJoueur()
            if(continuer) {
                actionAdversaire()
            }
        } else {
            actionAdversaire()
            if(gameOver() == false){
                var continuer = false
            }
        }
    }


    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */

    fun lancerCombat() {
        fun lanceCombat() {
            while (!gameOver() && !joueurGagne()) {
                this.jouer()
                println("======== Fin du Round : $round ========")
                round++
            }
            if (gameOver()) {
                joueur.equipeMonstre.forEach { it.pv = it.pvMax }
                println("Game Over !")
            }
        }

    }




}