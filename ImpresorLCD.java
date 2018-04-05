import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    /** Atributos */

    //Matriz de numeros a imprimir
    private String[][] matrizImpr;
    
    // Numero de filas por digito
    private int filasDigito;

    //Numero de columnas por digito
    private int columnasDigito;

    //Espacio entre cada digito
    private int espacioDigitos;

    //Tamanio de los digitos
    private int size;

    /** Getters y Setters */

    /**
     * @return El tamanio seleccionado de los digitos
     */
    public int getSize() {
        return size;
    }

    /**
     * @return La matriz de impresion
     */
    public String[][] getMatrizImpr() {
        return matrizImpr;
    }

    /**
     * @return Numero de filas para cada digito
     */
    public int getFilasDigito() {
        return filasDigito;
    }
    /**
     * @return Numero de columnas para cada digito
     */
    public int getColumnasDigito() {
        return columnasDigito;
    }
   
    /** Metodos */

     /**
     *
     * Metodo Constructor del impresor
     *
     * @param espacioDig Espacio entre cada uno de los digitos
     */    
    public ImpresorLCD(int espacioDig){
        this.espacioDigitos = espacioDig;
    }
    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroAImprimir Numero a Imprimir
     */    
    private void calcularMatriz(String numeroAImprimir) 
    {
        int pivotX = 0;
        char[] digitos = numeroAImprimir.toCharArray();

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }
            //crea el Numero actual a imprimir
            int numero = Integer.parseInt(String.valueOf(digito));
            Numero num = new Numero(numero,this);

            //calcula los puntos de acuerdo al pivote actual x
            num.calcularPuntos(pivotX);

            //actualiza el pivote
            pivotX = pivotX + this.columnasDigito + espacioDigitos;

            //adiciona el digito a la matriz
            num.adicionarDigito();
        }
    }

     /**
     *
     * Metodo encargado de validar el formato de los parámetros que se quieren procesar
     *
     */  
    public void validarParametros(String[] parametros){
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0]))
        {
            size = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(size <1 || size >10)
            {
                throw new IllegalArgumentException("El parametro size ["+size
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }
   }
    /**
     *
     * Metodo encargado de validar el formato del comando que se quiere procesar
     *
     * @param comando Entrada que contiene el tamaño del segmento de los digitos
     * y el numero a imprimir
     */  
    public String[] validarComando(String comando) throws IllegalArgumentException {
        
        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        String[] parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2 || parametros.length < 2)
        {
            throw new IllegalArgumentException("Cadena " + comando
            + " no contiene los parametros requeridos"); 
        }
        return parametros;
    }

    
     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el tamaño del segmento de los digitos
     * y el numero a imprimir
     * @param espacioDig Espacio entre cada uno de los digitos
     */  
    public void procesar(String comando) throws IllegalArgumentException {
        
        //validar que el string ingresado sea valido
        String[] parametros = validarComando(comando);
        validarParametros(parametros);
       
        String numeroAImprimir = parametros[1];
        // Calcula el numero de filas cada digito
        this.filasDigito = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        this.columnasDigito = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        int totalFilas = this.filasDigito;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        int totalColum = (this.columnasDigito * numeroAImprimir.length())
                + (espacioDigitos * numeroAImprimir.length());

        // crea matriz para almacenar los numeros a imprimir
        this.matrizImpr = new String[totalFilas][totalColum];

        // Inicializa matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {
                this.matrizImpr[i][j] = " ";
            }
        }

        // Calcula la matriz de impresión para el numero a imprimir
        calcularMatriz(numeroAImprimir);

         // Imprime la matriz
         for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {
                System.out.print(this.matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica. 
     *
     * @param cadena Cadena a validar si es numeric o no
     */  
    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
