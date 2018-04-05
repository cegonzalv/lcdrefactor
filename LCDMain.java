import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LCDMain {
    /** Constantes */
    private static final String CADENA_FINAL = "0,0";

    /**Atributos */

    //Espacio entre los digitos
    private static int espacioDig;
    
    //Lista de los comandos
    private static List<String> listaComandos = new ArrayList<>();
    
    /**Metodos */

    /**
     *
     * Metodo encargado de leer las instrucciones del usuario
     *
     */   
    public static void leerInstrucciones(){

        try (Scanner lector = new Scanner(System.in)) {
                
            System.out.print("Espacio entre Digitos (0 a 5): ");
            String comando = lector.next();

            // Valida si es un numero
            if (ImpresorLCD.isNumeric(comando)) 
            {
                espacioDig = Integer.parseInt(comando);
                
                // se valida que el espaciado este entre 0 y 5
                if(espacioDig <0 || espacioDig >5)
                {
                    throw new IllegalArgumentException("El espacio entre "
                            + "digitos debe estar entre 0 y 5");
                }
                
            } 
            else 
            {
                throw new IllegalArgumentException("Cadena " + comando
                        + " no es un entero");
            }
            
            while(!comando.equalsIgnoreCase(CADENA_FINAL))
            {
                System.out.print("Entrada: ");
                comando = lector.next();
                if(!comando.equalsIgnoreCase(CADENA_FINAL))
                {
                    listaComandos.add(comando);
                }
            } 
        }
    }

    public static void main(String[] args) {
        
        try {

            //Se lee el input del usuario para cada entrada.
            leerInstrucciones();

            //Empieza el procesamiento de los datos ingresador
            ImpresorLCD impresorLCD = new ImpresorLCD(espacioDig);
            Iterator<String> iterator = listaComandos.iterator();
            while (iterator.hasNext()) 
            {
                try 
                {
                    impresorLCD.procesar(iterator.next());
                } catch (Exception ex) 
                {
                    System.out.println("Error: "+ex.getMessage());
                }
            }
        } catch (Exception ex) 
        {
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
