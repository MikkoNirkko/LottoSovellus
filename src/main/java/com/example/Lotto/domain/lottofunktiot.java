package com.example.Lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class lottofunktiot {
	
	//Generoidaan yksittäinen lottorivi List-muodossa
	public static List<Long> generoiRivi(){
		List<Long> rivinumerot = new ArrayList<Long>();
		int i = 0;
		Long numero;
		numero= ThreadLocalRandom.current().nextLong(1, 39);
		rivinumerot.add(numero);
		for (i=1; i<7; i++){
			numero= ThreadLocalRandom.current().nextLong(1, 39);
			if(rivinumerot.contains(numero)){
				i--;
			}else{
				rivinumerot.add(numero);
			}
		}
		
		return(rivinumerot);
	}
	
	//Generoitu lottorivi String-muotoon pilkuilla
	public static String stringify(List<Long> numerot){
		String rivi ="";
		for(int u=0; u<6; u++){
			rivi+=numerot.get(u)+", ";
		}
		rivi+=numerot.get(6);
		return rivi;
	}
	
	//Laske, montako lukua rivien välillä oli samoja
	public static int laskeOikeat(List<Long> rivi1, List<Long> rivi2){
		int oikein=0;
		Long luku=null;
		for(int i=0; i<rivi1.size(); i++){
			luku=rivi1.get(i);
			if(rivi2.contains(luku)){
				oikein++;
			}
		}
		return oikein;
	}
	
	
	//Tee String-muotoinen lista samoista numeroista
	public static String listaaOikeat(List<Long> rivi1, List<Long> rivi2){
		String oikein="Samat numerot: ";
		List<Long> oikeatnumerot = new ArrayList<Long>();
		Long luku=null;
		int lkm=0;
		for(int i=0; i<rivi1.size(); i++){
			luku=rivi1.get(i);
			if(rivi2.contains(luku)){
				oikeatnumerot.add(luku);
				lkm++;
			}
		}
		if(lkm>0){
		for(int u=0; u<(lkm-1); u++){
			oikein+=oikeatnumerot.get(u)+", ";
		}
		oikein+=oikeatnumerot.get(lkm-1);
		}else{
			oikein = "Ei yhtäkään samaa numeroa";
		}
		
		return oikein;
	}
	


	
}
