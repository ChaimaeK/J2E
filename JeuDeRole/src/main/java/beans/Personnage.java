package beans;

public class Personnage {

	private double maxAtt;
	private double maxDef;
	private double maxMana;
	
	private String nom;
	private int vie;
	private int mana;
	private double attaque;
	private double defense;
	
	public Personnage() {
	}


	public Personnage( int vie, int mana) {
		this.vie = vie;
		this.mana = mana;

	}


	public double getMaxAtt() {
		return maxAtt;
	}


	public void setMaxAtt(double maxAtt) {
		this.maxAtt = maxAtt;
	}


	public double getMaxDef() {
		return maxDef;
	}


	public void setMaxDef(double maxDef) {
		this.maxDef = maxDef;
	}


	public double getMaxMana() {
		return maxMana;
	}


	public void setMaxMana(double maxMana) {
		this.maxMana = maxMana;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getVie() {
		return vie;
	}


	public void setVie(int vie) {
		this.vie = vie;
	}


	public int getMana() {
		return mana;
	}


	public void setMana(int mana) {
		this.mana = mana;
	}


	public double getAttaque() {
		return attaque;
	}


	public void setAttaque(double attaque) {
		this.attaque = attaque;
	}


	public double getDefense() {
		return defense;
	}


	public void setDefense(double defense) {
		this.defense = defense;
	}
	
	
	
}
