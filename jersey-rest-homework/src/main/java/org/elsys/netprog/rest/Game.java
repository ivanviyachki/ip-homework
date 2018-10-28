package org.elsys.netprog.rest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;

public class Game {
	String id;
	int cows;
	int bulls;
	int secret;
	int turnsCounters;
	boolean succes;
	
	public Game() {
		cows = 0;
		bulls = 0;
		id = UUID.randomUUID().toString();
		secret = RandomSecret(); 
		turnsCounters = 0;
		succes = false;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getCows() {
		return cows;
	}


	public void setCows(int cows) {
		this.cows = cows;
	}


	public int getBulls() {
		return bulls;
	}


	public void setBulls(int bulls) {
		this.bulls = bulls;
	}


	public int getSecret() {
		return secret;
	}


	public void setSecret(int secret) {
		this.secret = secret;
	}


	public int getTurnsCounters() {
		return turnsCounters;
	}


	public void setTurnsCounters(int turnsCounters) {
		this.turnsCounters = turnsCounters;
	}


	public boolean isSucces() {
		return succes;
	}


	public void setSucces(boolean succes) {
		this.succes = succes;
	}


	private int RandomSecret() {
		Set<Integer>numbers = new HashSet<Integer>();
		Random r = new Random();
		
		numbers.add(r.nextInt(9) + 1);
		int j =  0; 
				
		for(int i = 0; i < 4; i++) {
			int k = r.nextInt(9);
			numbers.add(k);
			if(numbers.size() == i) {
				i--;
			}
			else {
				j += k;
				j *= 10;
			}
		}
		return j;
	}
	
	public JSONObject jsonformat() {
		
		JSONObject json = new JSONObject();
		
		json.put("gameId", id);
		json.put("cowsNumber", cows);
		json.put("bullsNumber", bulls);
		json.put("turnsCount", turnsCounters);
		
        if(succes) {
        	json.put("secret", secret);
        }else {
        	json.put("secret", "****");
        }
        json.put("success", succes);		
        return json;
		
	}
}
