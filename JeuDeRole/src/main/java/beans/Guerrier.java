package beans;

import actions.IAttaque;
import actions.IDefense;

public class Guerrier extends Personnage implements IAttaque, IDefense {
	private int cmptDef = 0;
	private int cmptCon = 0;
	
	
	public Guerrier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Guerrier(int vie, int mana) {
		super(vie, mana);
		// TODO Auto-generated constructor stub
		this.setMaxAtt(vie*0.9+mana*0.1);
		this.setMaxMana(mana);
		this.setMaxDef(vie*0.7+mana*0.3);
		
		this.setAttaque(vie*0.9+mana*0.1);
		this.setDefense(vie*0.7+mana*0.3);
	}

	@Override
	public String attaque(IAttaque adversaire) {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Attaque normale sur " + ((Personnage) adversaire).getNom();
		msg += adversaire.hit(getAttaque());
		return msg;
	}

	@Override
	public String attaqueSpe(IAttaque adversaire) {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Attaque speciale sur " + ((Personnage) adversaire).getNom();
		msg += adversaire.hit(getAttaque()+getMana()*0.5);
		this.setMana((int) Math.max(0, this.getMana()*(1-0.3)));
		return msg;
	}

	@Override
	public String hit(double degat) {
		// TODO Auto-generated method stub
		String msg = " cause "+ (int) (degat - getDefense()*2/3) +" degats";
		this.setVie((int) Math.max(0, getVie() -  Math.max(0, degat - getDefense()*2/3 )) );
		return msg;
	}

	@Override
	public String defend() {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Augmente sa defense pour 5 tours";
		if (getCmptDef()==0) {
			this.setDefense( (this.getDefense()*(1+0.2)) );
			setCmptDef(6);
		}
		return msg;
		
	}

	@Override
	public String concentration() {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": se concentre et gagne en attaque pour 3 tours et en mana";
		if (getCmptCon()==0) {
			this.setAttaque( (this.getAttaque()*(1+0.3)) );
			this.setMana( (int) Math.min((this.getMana()*(1+0.4)), this.getMaxMana()));
			setCmptCon(4);
		}
		return msg;
		
	}

	public int getCmptDef() {
		return cmptDef;
	}

	public void setCmptDef(int cmptDef) {
		this.cmptDef = cmptDef;
	}

	public int getCmptCon() {
		return cmptCon;
	}

	public void setCmptCon(int cmptCon) {
		this.cmptCon = cmptCon;
	}
	
	@Override
	public String update() {
		// TODO Auto-generated method stub
		String msg ="Bonus{ ";
		this.setCmptDef(Math.max(0, getCmptDef()-1));
		this.setCmptCon(Math.max(0, getCmptCon()-1));
		if (getCmptDef()==0) { this.setDefense(this.getMaxDef()); }
		else { msg +="Bonus defense: "+getCmptDef()+" Tours "; }
		if (getCmptCon()==0) { this.setAttaque(this.getMaxAtt()); }
		else { msg +="Bonus d'attaque: "+getCmptCon()+" Tours"; }
		return msg+" }";
	}

	
	
}
