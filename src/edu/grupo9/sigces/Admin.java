package edu.grupo9.sigces;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static edu.grupo9.sigces.Utilidades.*;

public class Admin extends Usuario implements VarsGlobales {

    private int idAdmin;
    private String clave;

    /* * * CONSTRUCTORES * * */
    public Admin() {
    }

    public Admin(int idAdmin,
                 String nombre,
                 String apellido,
                 int dni,
                 String clave,
                 String domicilio,
                 String telefono,
                 String email,
                 LocalDate fechaNac,
                 String sexo) {
        this.idAdmin = idAdmin;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.clave = clave;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
    }

    /* * * GETTERS Y SETTERS * * */

    public int obtenerIdAdmin() { return idAdmin; }

    public void establecerIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }

    public String obtenerClave() {
        return clave;
    }

    public void establecerClave(String clave) {
        this.clave = clave;
    }

    /* METODOS DE CLASE */

//    public static Admin buscarAdmin() {
//        ArrayList<String> datosAdmin = Main.login(planillaAdmins);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//        return new Admin(datosAdmin.get(0),
//                datosAdmin.get(1),
//                datosAdmin.get(2),
//                Integer.parseInt(datosAdmin.get(3)),
//                datosAdmin.get(4),
//                datosAdmin.get(5),
//                datosAdmin.get(6),
//                datosAdmin.get(7),
//                LocalDate.parse(datosAdmin.get(8), formatter),
//                datosAdmin.get(9).charAt(0)
//        );
//    }



    /* * * GESTI�N DE M�DICOS * * */


    /**
     * Crear un nuevo objeto Medico
     */
//    public static void cargarNuevoMedico() {
//        System.out.println("Por favor, ingrese los datos del nuevo M�dico.");
//        Scanner scanner = new Scanner(System.in);
//        Medico medico = new Medico();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//        System.out.print("Nombre: ");
//        medico.establecerNombre(scanner.nextLine());
//        System.out.print("Apellido: ");
//        medico.establecerApellido(scanner.nextLine());
//        System.out.print("DNI: ");
//        medico.establecerDni(scanner.nextInt());
//        String claveValida;
//        while (!validarClave(claveValida = scanner.nextLine())) {
//            System.out.println("Clave (debe tener 4 d�gitos):");
//        }
//        medico.establecerClave(claveValida);
//        System.out.print("Domicilio: ");
//        medico.establecerDomicilio(scanner.nextLine());
//        System.out.print("Tel�fono: ");
//        medico.establecerTelefono(scanner.nextLine());
//        System.out.print("eMail: ");
//        medico.establecerEmail(scanner.nextLine());
//        System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
//        medico.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
//        System.out.print("Sexo: ");
//        medico.establecerSexo(scanner.nextLine().charAt(0));
//        System.out.print("Matr�cula Prov.: ");
//        medico.establecerMatriculaProv(scanner.nextLine());
//        System.out.print("Matr�cula Nac.: ");
//        medico.establecerMatriculaNac(scanner.nextLine());
//        System.out.print("Especialidades (Separadas por coma): ");
//        medico.establecerEspecialidades(scanner.nextLine());
//        cargarPlanilla(establecerDatos(medico), planillaMedicos);
//    }

    /**
     * Cargar los datos del objeto Medico en un arreglo
     *
     * @param medico Medico
     * @return <i>ArrayList</i> con los <b>datos</b> del objeto Medico
     */
//    public static String establecerDatos(Medico medico) {
//        ArrayList<String> datos = new ArrayList<>();
//        try {
//            datos.add(String.valueOf(contarLineas(planillaMedicos)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        datos.add(medico.obtenerNombre());
//        datos.add(medico.obtenerApellido());
//        datos.add(String.valueOf(medico.obtenerDni()));
//        datos.add(medico.obtenerClave());
//        datos.add(medico.obtenerDomicilio());
//        datos.add(medico.obtenerTelefono());
//        datos.add(medico.obtenerEmail());
//        datos.add(String.valueOf(medico.obtenerFechaNac()));
//        datos.add(String.valueOf(medico.obtenerSexo()));
//        datos.add(medico.obtenerMatriculaProv());
//        datos.add(medico.obtenerMatriculaNac());
//        datos.add(medico.obtenerEspecialidades());
//        System.out.println("Datos establecidos");
//        return datos.toString().replace("[", "").replace("]", "").replace(",", ";");
//    }

    /**
     * Cargar los datos en una nueva l�nea en la planilla medicos.csv
     *
     * @param nuevaLinea
     * @param planilla
     */
//    public static void cargarPlanilla(String nuevaLinea, String planilla) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(planilla, true));
//            bw.newLine();
//            bw.write(nuevaLinea);
//            bw.flush();
//            bw.close();
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }

//    public static int contarLineas(String planilla) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(planilla));
//        int lines = 0;
//        while (reader.readLine() != null) lines++;
//        reader.close();
//        return lines + 1;
//    }



//    public static void eliminarMedico() {
//        System.out.println("Por favor, ingrese el nombre y apellido del m�dico.");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Nombre: ");
//        String nombre = scanner.nextLine();
//        System.out.print("Apellido: ");
//        String apellido = scanner.nextLine();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(planillaMedicos));
//            StringBuilder salida = new StringBuilder();
//            String linea;
//            String porEliminar = null;
//            while ((linea = br.readLine()) != null) {
//                String[] values = linea.split(";");
//                String idPlanilla = values[0].trim();
//                String nombrePlanilla = values[1].trim();
//                String apellidoPlanilla = values[2].trim();
//                if (Objects.equals(nombre, nombrePlanilla) && Objects.equals(apellido, apellidoPlanilla)) {
//                    porEliminar = nombre + " " + apellido;
//                    linea = idPlanilla + "; M�dico eliminado;;;;;;;;;;;";
//                }
//                salida.append(linea + "\n");
//            }
//            br.close();
//            System.out.print("""
//                    Por favor, confirme la operaci�n.\s
//                    Est� a punto de eliminar a: \s""");
//            System.out.println(porEliminar);
//            System.out.println("""
//                    1. S� \s
//                    2. No
//                    """);
//            int seleccion = scanner.nextInt();
//            if (seleccion == 1) {
//                BufferedWriter bw = new BufferedWriter(new FileWriter(planillaMedicos));
//                bw.write(String.valueOf(salida));
//                bw.flush();
//                bw.close();
//                System.out.println(porEliminar + " fue eliminado del Sistema.");
//                dormirPor(1000);
//                limpiarPantalla();
//            }
//            gestionarMedicos();
//        } catch (IOException f) {
//            System.err.println(f.getMessage());
//        }
//    }





//    public static ArrayList<String> leerCSV(String nombre, String apellido, String planilla) {
//        // Abre planilla y lee cada l�nea.
//        try (BufferedReader br = new BufferedReader(new FileReader(planilla))) {
//            String s;
//            br.close();
//            // Lee l�nea por l�nea
//            while ((s = br.readLine()) != null) {
//                // Divide cada l�nea y la divide en cada punto y coma.
//                String[] values = s.split(";");
//                String nombrePlanilla = values[1].trim();
//                String apellidoPlanilla = values[2].trim();
//                if (Objects.equals(nombre, nombrePlanilla) && Objects.equals(apellido, apellidoPlanilla)) {
//                    return new ArrayList<>(Arrays.asList(values));
//                }
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        return null;
//    }
}

