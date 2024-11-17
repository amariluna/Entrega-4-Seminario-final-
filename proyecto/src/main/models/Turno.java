package models;

import java.util.Date;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private Disponibilidad disponibilidad;
    private Date fecha;
    private boolean confirmado;

    // Constructor
    public Turno(Paciente paciente, Medico medico, Disponibilidad disponibilidad) {
        this.paciente = paciente;
        this.medico = medico;
        this.disponibilidad = disponibilidad;
        this.fecha = disponibilidad.getFechaHora();
        this.confirmado = false;  // Inicialmente no confirmado
    }

    // Getters y setters
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    // Método para confirmar el turno
    public void confirmar() {
        if (!this.confirmado) {
            this.confirmado = true;
            System.out.println("Turno confirmado con " + medico.getNombre() + " para el " + fecha.toString());
        } else {
            System.out.println("Este turno ya está confirmado.");
        }
    }

    // Método para rechazar el turno
    public void rechazar() {
        if (this.confirmado) {
            this.confirmado = false;
            System.out.println("Turno rechazado para el " + fecha.toString());
        } else {
            System.out.println("Este turno no estaba confirmado.");
        }
    }

    // Método para verificar si la disponibilidad ya ha sido tomada
    public static boolean verificarDisponibilidad(Disponibilidad disponibilidad, List<Turno> turnos) {
        for (Turno turno : turnos) {
            if (turno.getDisponibilidad().equals(disponibilidad) && turno.isConfirmado()) {
                return false; // La disponibilidad ya ha sido tomada
            }
        }
        return true; // La disponibilidad está libre
    }

    @Override
    public String toString() {
        return "Turno: " + paciente.getNombre() + " con " + medico.getNombre() + " el " + fecha.toString() + " (Confirmado: " + confirmado + ")";
    }
}

