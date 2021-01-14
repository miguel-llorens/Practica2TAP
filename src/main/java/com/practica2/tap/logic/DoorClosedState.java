package com.practica2.tap.logic;

public class DoorClosedState extends ElevatorState {
    public DoorClosedState() {
        this.name = "Puerta cerrada";
    }

    @Override
    public String doors_button(Elevator elevator) {
        elevator.setCurrent_state(new DoorOpenedState());

        return "Abriendo puerta.";
    }

    @Override
    public String emergency_button(Elevator elevator) {
        elevator.setCurrent_state(new EmergencyState());

        return "Estado de emergencia activado.";
    }

    @Override
    public String move_elevator(Elevator elevator) {
        String msg;

        if (elevator.getCall_list().isEmpty()) {
            msg = "No hay ningún piso seleccionado.";
        }
        else {
            elevator.setCurrent_state(new MotionState());
            
            msg = elevator.move_elevator();
        }

        return msg;
    }
}
