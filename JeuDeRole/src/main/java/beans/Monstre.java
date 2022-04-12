package beans;

import actions.IAttaque;

public class Monstre extends Personnage implements IAttaque {
	
	public Monstre(int vie, int mana) {
		super(vie, mana);
		this.setNom("Monstre");
		this.setAttaque(vie*0.65+mana*0.35);
		this.setDefense(vie*0.5+mana*0.5);
	}

	@Override
	public String attaque(IAttaque adversaire) {
		// TODO Auto-generated method stub
		String msg =this.getNom() + ": Attaque sur "+ ((Personnage) adversaire).getNom();
		msg += adversaire.hit(getAttaque()+getMana()*0.1);
		this.setMana((int) Math.max(0, this.getMana()*(1-0.3)));
		return msg;
	}

	@Override
	public String attaqueSpe(IAttaque adversaire) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String hit(double degat) {
		// TODO Auto-generated method stub
		String msg =" cause "+ (int) (degat - getDefense()*2/3) +" degats";
		this.setVie( (int) Math.max(0, getVie()- Math.max(0, degat - getDefense()*2/3 )) );
		return msg;
	}
}
