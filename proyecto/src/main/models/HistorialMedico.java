package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HistorialMedico {
    private int id;
    private Date fecha;
    private String descripcion;
    private static List<HistorialMedico> registros = new ArrayList<>();

    public HistorialMedico(int id, Date fecha, String descripcion) {
        if (fecha == null || descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha y la descripción no pueden ser nulas o vacías.");
        }
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    // Lógica para agregar un nuevo registro al historial
    public void agregarRegistro() {
        registros.add(this);  // Añadimos el objeto actual al historial
        System.out.println("Registro agregado: " + this.descripcion);
    }

    // Obtener todos los registros
    public static List<HistorialMedico> obtenerRegistros() {
        return registros;
    }

    // Obtener un registro por ID
    public static Optional<HistorialMedico> obtenerRegistroPorId(int id) {
        return registros.stream()
                        .filter(r -> r.getId() == id)
                        .findFirst();
    }

    // Eliminar un registro por ID
    public static boolean eliminarRegistro(int id) {
        Optional<HistorialMedico> registro = obtenerRegistroPorId(id);
        if (registro.isPresent()) {
            registros.remove(registro.get());
            System.out.println("Registro eliminado: " + id);
            return true;
        }
        System.out.println("Registro no encontrado: " + id);
        return false;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Información de un registro
    public void mostrarRegistro() {
        System.out.println("ID: " + this.id);
        System.out.println("Fecha: " + this.fecha);
        System.out.println("Descripción: " + this.descripcion);
    }

    // Método toString para representar el historial en formato legible
    @Override
    public String toString() {
        return "ID: " + id + ", Fecha: " + fecha + ", Descripción: " + descripcion;
    }
}

