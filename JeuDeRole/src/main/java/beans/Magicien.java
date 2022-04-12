package beans;

import actions.IAttaque;
import actions.IDefense;

public class Magicien extends Personnage implements IAttaque, IDefense {

	private int cmptDef = 0;
	
	public Magicien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magicien(int vie, int mana) {
		super(vie, mana);
		// TODO Auto-generated constructor stub
		this.setMaxAtt(mana*0.9+vie*0.1);
		this.setMaxMana(mana*1.5);
		this.setMaxDef(mana*0.7+vie*0.3);
		
		this.setAttaque(mana*0.9+vie*0.1);
		this.setDefense(mana*0.7+vie*0.3);
		
	}
	
	@Override
	public String attaque(IAttaque adversaire) {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Attaque normale sur "+((Personnage) adversaire).getNom();
		msg += adversaire.hit(getAttaque());
		this.setMana((int) Math.max(0, this.getMana()*(1-0.1)));
		return msg;
	}

	@Override
	public String attaqueSpe(IAttaque adversaire) {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Attaque speciale sur "+((Personnage) adversaire).getNom();
		double m = (Math.random()*0.3)+0.2;
		msg += adversaire.hit(getAttaque()+getMana()*m);
		this.setMana((int) Math.max(0, this.getMana()*(1-m)));
		return msg;
	}

	@Override
	public String hit(double degat) {
		// TODO Auto-generated method stub
		String msg =" cause "+ (int) (degat - getDefense()*2/3) +" degats";
		this.setVie((int) Math.max(0, getVie() -  Math.max(0, degat - getDefense()*2/3 )) );
		return msg;
	}

	@Override
	public String defend() {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Augmente sa defense pour 5 tours";
		if (getCmptDef()==0) {
			this.setDefense(this.getDefense()*1.3);
			this.setMana((int)(this.getMana()*(1-0.3)));
			setCmptDef(6);
		}
		return msg;
		
	}

	@Override
	public String concentration() {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": se concentre et gagne en vie et en mana";
		this.setMana((int)(this.getMana()*(1+0.3)));
		this.setVie((int)(this.getVie()*(1+0.1)));
		return msg;
	}
	
	public int getCmptDef() {
		return cmptDef;
	}

	public void setCmptDef(int cmptDef) {
		this.cmptDef = cmptDef;
	}
	
	@Override
	public String update() {
		// TODO Auto-generated method stub
		String msg ="Bonus{ ";
		this.setCmptDef(Math.max(0, getCmptDef()-1));
		if (getCmptDef()==0) { this.setDefense(this.getMaxDef()); }
		else { msg +="Bonus defense: "+getCmptDef()+" Tours"; }
		return msg+" }";
	}
	
}
