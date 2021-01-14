package com.practica2.tap.logic;

public abstract class ElevatorState {
    protected String name;
    
    public abstract String doors_button(Elevator elevator);
    public abstract String emergency_button(Elevator elevator);
    public abstract String move_elevator(Elevator elevator);

    public String getName() {
        return name;
    }
}
