package jeu

import dresseur.Entraineur

class CombatDresseur (
    var id : Int,
    var joueur : Entraineur,
    var entraineurAdversaire : Entraineur
){
    fun avoirGagne() : Boolean {
        var gagne = true
        for(monstre in entraineurAdversaire.equipeMonstre) {
            if (monstre.pv > 0) {
                gagne = false
                break
            }
        }
        return gagne
    }

    fun avoirPerdu() : Boolean {
        var perdu = true
        for(monstre in entraineurAdversaire.equipeMonstre) {
            if (monstre.pv > 0) {
                perdu = false
                break
            }
        }
        return perdu
    }
}