package com.practica2.tap.logic;

import java.util.ArrayList;

import com.vaadin.flow.component.textfield.TextField;

public class Elevator {
    protected int num_elevator;
    protected int current_floor;
    protected ArrayList<Integer> call_list;
    protected ElevatorState current_state;
    protected ArrayList<TextField> displays;
    protected ArrayList<TextField> stateDisplays;
    
    public Elevator(int num_elevator) {
        this.num_elevator = num_elevator;
        this.current_floor = 0;
        this.call_list = new ArrayList<Integer>();
        this.current_state = new DoorClosedState();
        this.displays = new ArrayList<TextField>();
        this.stateDisplays = new ArrayList<TextField>();
    }

    public String select_floor(int floor_num) {
        this.call_list.add(floor_num);
        
        return this.move_elevator();
    }

    public String move_elevator() {
        return this.current_state.move_elevator(this);
    }

    public String doors_button() {
        return this.current_state.doors_button(this);
    }

    public String emergency_button() {
        return this.current_state.emergency_button(this);
    }

    public ArrayList<Integer> getCall_list() {
        return this.call_list;
    }

    public int getCurrentFloor() {
        return current_floor;
    }

    public void setCurrentFloor(int floor_num) {
        this.current_floor = floor_num;
        this.notificar();
    }

    public void setCurrent_state(ElevatorState state) {
        this.current_state = state;
    }
    
    public void attachObserver(TextField observer) {
    	displays.add(observer);
    	observer.setValue(Integer.toString(current_floor));
    }
    
    public void removeObserver(TextField observer) {
    	displays.remove(observer);
    }
    
    public void notificar() {
    	for(TextField disp: displays) {
    		disp.setValue(Integer.toString(current_floor));
    	}
    }
    
    public void attachStateObserver(TextField observer) {
    	stateDisplays.add(observer);
    	observer.setValue(current_state.getName());
    }
    
    public void removeStateObserver(TextField observer) {
    	stateDisplays.remove(observer);
    }
    
    public void notificarState() {
    	for(TextField disp: stateDisplays) {
    		disp.setValue(current_state.getName());
    	}
    }
}
