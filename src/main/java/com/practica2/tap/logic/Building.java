package com.practica2.tap.logic;

import java.util.ArrayList;

public class Building {
	protected static Building singleton;
	protected ArrayList<Floor> floors;
	protected ArrayList<Elevator> elevators;

	private Building(int numFloors, int numElevators) {
		this.floors= new ArrayList<Floor>();
		this.elevators = new ArrayList<Elevator>();
		
		for (int i = 0; i < numFloors; i++) {
			this.floors.add(new Floor(i));
		}
		
		for (int i = 0; i < numElevators; i++) {
			this.elevators.add(new Elevator(i));
		}
	}

	public static Building getInstance(int num_floors, int num_elevators) {
		if (singleton == null) {
			singleton = new Building(num_floors, num_elevators); 
		}

		return singleton;
	}

	public static void resetBuilding(int num_floors, int num_elevators) {
		singleton = new Building(num_floors, num_elevators);
	}
	
	public ArrayList<Floor> getFloors() {
		return floors;
	}

	public ArrayList<Elevator> getElevators() {
		return elevators;
	}
}
