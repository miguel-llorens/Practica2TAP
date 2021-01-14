package com.practica2.tap.logic;

public class EmergencyState extends ElevatorState {
    public EmergencyState() {
        this.name = "Emergencia";
    }

    @Override
    public String doors_button(Elevator elevator) {
        return "Estado de emergencia, no funciona.";
    }

    @Override
    public String emergency_button(Elevator elevator) {
        elevator.setCurrent_state(new DoorClosedState());

        return "Estado de emergencia desactivado.";
    }

    @Override
    public String move_elevator(Elevator elevator) {
        return "Estado de emergencia, no funciona.";
    }
}
