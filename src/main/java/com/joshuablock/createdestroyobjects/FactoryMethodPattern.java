package com.joshuablock.createdestroyobjects;

//pattern permettant de créer des objets sans devoir specifier le type
//de la classe à créer. On implemente une interface ou une classe qui a une methode
// de fabrication des objets qui sera surchargées dans les classes filles
public class FactoryMethodPattern {
}

//on utilise un enum
enum WeaponType {
	SHORT_SWORD("short sword"), SPEAR("spear"), AXE("axe"), UNDEFINED("");
	private String title;

	WeaponType(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}

interface Weapon {
	WeaponType getWeaponType();
}

interface Blacksmith {
	Weapon manufactureWeapon(WeaponType weaponType);
}

class OrcBlacksmith implements Blacksmith {
	public Weapon manufactureWeapon(WeaponType weaponType) {
		return new OrcWeapon(weaponType);
	}
}

class ElfBlacksmith implements Blacksmith {
	public Weapon manufactureWeapon(WeaponType weaponType) {
		return new ElfWeapon(weaponType);
	}
}

class OrcWeapon implements Weapon {
	private WeaponType weaponType;
	public OrcWeapon(WeaponType weaponType) {
		this.weaponType = weaponType;
	}
	@Override
	public String toString() {
		return "Orcish " + getWeaponType();
	}
	@Override
	public WeaponType getWeaponType() {
		return weaponType;
	}
}

class ElfWeapon implements Weapon {
	private WeaponType weaponType;
	public ElfWeapon(WeaponType weaponType) {
		this.weaponType = weaponType;
	}
	@Override
	public String toString() {
		return "Elven " + getWeaponType();
	}
	@Override
	public WeaponType getWeaponType() {
		return weaponType;
	}
}

//classe de test
class App {
	private final Blacksmith blacksmith;

	public App(Blacksmith blacksmith) {
		this.blacksmith = blacksmith;
	}

	public static void main(String[] args) {
		// Lets go to war with Orc weapons
		App app = new App(new OrcBlacksmith());
		app.manufactureWeapons();
		// Lets go to war with Elf weapons
		app = new App(new ElfBlacksmith());
		app.manufactureWeapons();
	}

	private void manufactureWeapons() {
		Weapon weapon;
		weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
		System.out.println(weapon);
		weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
		System.out.println(weapon);
	}
}

