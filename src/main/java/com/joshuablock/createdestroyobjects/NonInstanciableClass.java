package com.joshuablock.createdestroyobjects;

/**
 * Item 4 : Enforce noninstantiability with a private constructor
 * Une classe qu'on ne veut pas pouvoir instancier, par exemple, une classe qui ne contient que des methodes statiques
 * A eviter en general mais peut avoir son utilité parfois (utilitaire ou factory)
 * Pour etre sur de pas pouvoir l'instancier, il faut un constructeur privé (class abstract ne suffit pas car peut etre heritée et sous classes instanciées)
 */
public class NonInstanciableClass {
	private NonInstanciableClass(){
		throw new AssertionError();
	}
}

class Toto{
	public static void main(String... args){
		//'NonInstanciableClass()' has private access in 'com.joshuablock.createdestroyobjects.NonInstanciableClass'
		//NonInstanciableClass nonInstanciableClass = new NonInstanciableClass();
	}
}
