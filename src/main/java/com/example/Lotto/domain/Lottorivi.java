package com.example.Lotto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lottorivi {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String playname;
	private String row;
	private Long wins;
	
	public Lottorivi(){
		super();
		this.playname = null;
		this.row = null;
		this.wins = null;
	}
	
	public Lottorivi(String playname, String row, Long wins){
		this.playname = playname;
		this.row = row;
		this.wins = wins;
	}

	public Long getId() {
		return Id;
	}

	public String getPlayname() {
		return playname;
	}

	public String getRow() {
		return row;
	}

	public Long getWins() {
		return wins;
	}

	public void setId(Long id) {
		Id = id;
	}

	public void setPlayname(String playname) {
		this.playname = playname;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public void setWins(Long wins) {
		this.wins = wins;
	}

	@Override
	public String toString() {
		return "Lottorivi [Id=" + Id + ", playname=" + playname + ", row=" + row + ", wins=" + wins + "]";
	}
	
	
	
}
