package org.example.dresseur

class Entraineur (val id : Int, var nom : String, var argents : Int) {
    fun afficheDetail() {
        print($nom,$argents)
    }
}