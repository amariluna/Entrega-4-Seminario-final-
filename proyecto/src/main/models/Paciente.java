package models;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario {
    private List<HistorialMedico> historialMedico;
    private List<Turno> turnos;

    public Paciente(int id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña, "Paciente");
        this.historialMedico = new ArrayList<>();
        this.turnos = new ArrayList<>();
    }

    // Lógica para solicitar un turno
    public void solicitarTurno(Medico medico, Disponibilidad disponibilidad) {
        // Verifica si la disponibilidad existe antes de crear el turno
        if (medico.getDisponibilidad().contains(disponibilidad)) {
            // Verifica si ya existe un turno confirmado en esa fecha
            if (turnoExistente(disponibilidad)) {
                System.out.println("Ya tienes un turno confirmado en esta fecha.");
            } else {
                Turno turno = new Turno(this, medico, disponibilidad); // Crear el nuevo turno
                turnos.add(turno); // Añadir el turno a la lista del paciente
                System.out.println("Turno solicitado con el médico: " + medico.getNombre() + " para el " + disponibilidad.getFechaHora());
            }
        } else {
            System.out.println("La disponibilidad solicitada no está disponible.");
        }
    }

    // Verifica si ya existe un turno confirmado en la fecha de la disponibilidad
    private boolean turnoExistente(Disponibilidad disponibilidad) {
        for (Turno turno : turnos) {
            if (turno.getDisponibilidad().equals(disponibilidad) && turno.isConfirmado()) {
                return true;
            }
        }
        return false;
    }

    // Lógica para confirmar un turno
    public void confirmarTurno(Turno turno) {
        if (turnos.contains(turno)) {
            if (!turno.isConfirmado()) {
                turno.setConfirmado(true);
                System.out.println("Turno confirmado para el: " + turno.getFecha());
            } else {
                System.out.println("Este turno ya está confirmado.");
            }
        } else {
            System.out.println("Este turno no se encuentra en tu lista.");
        }
    }

    // Lógica para cancelar un turno
    public void cancelarTurno(Turno turno) {
        if (turnos.contains(turno)) {
            if (turno.isConfirmado()) {
                System.out.println("No se puede cancelar un turno confirmado.");
            } else {
                turnos.remove(turno);
                System.out.println("Turno cancelado para el: " + turno.getFecha());
            }
        } else {
            System.out.println("Este turno no se encuentra en tu lista.");
        }
    }

    // Métodos relacionados con el historial médico
    public List<HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void agregarRegistroHistorial(HistorialMedico historial) {
        this.historialMedico.add(historial);
        System.out.println("Registro de historial médico añadido: " + historial.getDescripcion());
    }

    // Método para mostrar los turnos del paciente
    public void mostrarTurnos() {
        if (turnos.isEmpty()) {
            System.out.println("No tienes turnos programados.");
        } else {
            System.out.println("Tus turnos:");
            for (Turno turno : turnos) {
                System.out.println("Médico: " + turno.getMedico().getNombre() + ", Fecha: " + turno.getFecha() + ", Confirmado: " + turno.isConfirmado());
            }
        }
    }
}
