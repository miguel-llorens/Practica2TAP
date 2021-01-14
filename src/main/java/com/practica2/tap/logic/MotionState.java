package com.practica2.tap.logic;

public class MotionState extends ElevatorState {
    public MotionState() {
        this.name = "En movimiento";
    }

    @Override
    public String doors_button(Elevator elevator) {
        return "El ascensor está en movimiento, no es buena idea abrir la puerta.";
    }

    @Override
    public String emergency_button(Elevator elevator) {
        elevator.setCurrent_state(new EmergencyState());

        return "Estado de emergencia activado.";
    }

    @Override
    public String move_elevator(Elevator elevator) {
        int floor = elevator.getCall_list().remove(0);
        
        elevator.setCurrentFloor(floor);
        elevator.setCurrent_state(new DoorClosedState());

        return "El ascensor se ha movido al piso " + Integer.toString(floor) + ".";
    }
}
