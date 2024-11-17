package models;

import java.util.List;

public class Administrador extends Usuario {

    public Administrador(int id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña, "Administrador");
    }

    public void generarReportes(List<String> datos) {
        // Generar reportes basados en los datos
        if (datos == null || datos.isEmpty()) {
            System.out.println("No hay datos disponibles para generar el reporte.");
            return;
        }

        System.out.println("Generando reporte...");
        for (String dato : datos) {
            System.out.println(dato); 
        }
    }

    public void gestionarUsuarios(List<Usuario> usuarios, String accion, Usuario usuario) {
        // Gestionar usuarios: añadir, modificar, eliminar
        if (usuarios == null || usuario == null) {
            System.out.println("La lista de usuarios o el usuario son nulos.");
            return;
        }

        switch (accion.toLowerCase()) {
            case "añadir":
                if (!usuarios.contains(usuario)) {
                    usuarios.add(usuario);
                    System.out.println("Usuario añadido: " + usuario.getNombre());
                } else {
                    System.out.println("El usuario ya existe.");
                }
                break;

            case "modificar":
                int index = usuarios.indexOf(usuario);
                if (index != -1) {
                    usuarios.set(index, usuario); // Modifica al usuario en la lista
                    System.out.println("Usuario modificado: " + usuario.getNombre());
                } else {
                    System.out.println("Usuario no encontrado.");
                }
                break;

            case "eliminar":
                if (usuarios.remove(usuario)) {
                    System.out.println("Usuario eliminado: " + usuario.getNombre());
                } else {
                    System.out.println("Usuario no encontrado.");
                }
                break;

            default:
                System.out.println("Acción no válida. Las acciones válidas son: añadir, modificar, eliminar.");
                break;
        }
    }
}
