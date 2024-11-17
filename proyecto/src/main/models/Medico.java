package models;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {
    private Doctor doctor;  // Usamos la clase Doctor en lugar de tener un campo de especialidad y nombre directamente
    private List<Disponibilidad> disponibilidad;

    public Medico(int id, String nombre, String email, String contraseña, Doctor doctor) {
        super(id, nombre, email, contraseña, "Médico");
        this.doctor = doctor; // Instanciamos el Doctor
        this.disponibilidad = new ArrayList<>();
    }

    // Lógica para actualizar la información del paciente
    public void actualizarInformacionPaciente(Paciente paciente) {
        System.out.println("Información actualizada para el paciente: " + paciente.getNombre());
    }

    // Lógica para notificar al paciente sobre el turno
    public void notificarTurno(Paciente paciente, Turno turno) {
        System.out.println("Notificación enviada a " + paciente.getNombre() + " sobre el turno: " + turno.getFecha());
    }

    // Disponibilidad de horarios
    public List<Disponibilidad> getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(List<Disponibilidad> disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void agregarDisponibilidad(Disponibilidad nuevaDisponibilidad) {
        // Validar que no haya un conflicto de horarios antes de agregar
        if (!disponibilidadExistente(nuevaDisponibilidad)) {
            disponibilidad.add(nuevaDisponibilidad);
            System.out.println("Nueva disponibilidad añadida: " + nuevaDisponibilidad.getFechaHora());
        } else {
            System.out.println("Conflicto de horario: La disponibilidad ya está ocupada.");
        }
    }

    private boolean disponibilidadExistente(Disponibilidad nuevaDisponibilidad) {
        for (Disponibilidad dispo : disponibilidad) {
            if (dispo.getFechaHora().equals(nuevaDisponibilidad.getFechaHora())) {
                return true; // Ya existe una disponibilidad en ese horario
            }
        }
        return false; // No hay conflicto
    }

    // Método para mostrar las disponibilidades
    public void mostrarDisponibilidad() {
        if (disponibilidad.isEmpty()) {
            System.out.println("No hay disponibilidades para este médico.");
        } else {
            for (Disponibilidad dispo : disponibilidad) {
                System.out.println(dispo.toString());
            }
        }
    }

    // Método para obtener la especialidad del médico
    public String getEspecialidad() {
        return doctor.getSpecialty(); // Obtener especialidad del objeto Doctor
    }

    // Método para obtener el nombre del médico
    public String getNombreMedico() {
        return doctor.getName(); // Obtener nombre del objeto Doctor
    }
}
