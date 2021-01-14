package com.practica2.tap.logic;

public class DoorOpenedState extends ElevatorState {
    public DoorOpenedState() {
        this.name = "Puerta abierta";
    }

    @Override
    public String doors_button(Elevator elevator) {
        elevator.setCurrent_state(new DoorClosedState());
        
        if(!elevator.getCall_list().isEmpty()) {
        	elevator.move_elevator();
        }
        
        return "Cerrando puerta.";
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
            msg = "Cierra la puerta y selecciona un piso de destino.";
        }
        else {
            msg = "Esperando a que se cierre la puerta.";
        }

        return msg;
    }
}
