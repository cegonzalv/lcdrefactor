public class Numero {

    //Constantes para la impresión
    public static final String CARACTER_VERTICAL = "|";
    public static final String CARACTER_HORIZONTAL = "-";

    // Puntos fijos
    private final int[] puntoFijo1;
    private final int[] puntoFijo2;
    private final int[] puntoFijo3;
    private final int[] puntoFijo4;
    private final int[] puntoFijo5;

    private int valor;
    private ImpresorLCD impresor;

    public Numero(int valor, ImpresorLCD impresor) {
        // Inicializa variables
        this.impresor = impresor;
        this.valor = valor;
        this.puntoFijo1 = new int[2];
        this.puntoFijo2 = new int[2];
        this.puntoFijo3 = new int[2];
        this.puntoFijo4 = new int[2];
        this.puntoFijo5 = new int[2];
    }
    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

     /**
     *
     * Metodo encargado de calcular los puntos en donde se insertarán los segmentos
     *
     * @param pivotX Pivote en x del espacio donde estamos actualmente
     * @param filasDigito Numero de filas para cada digito
     * @param columnasDigito Numero de columnas para cada digito
     */
    public void calcularPuntos(int pivotX){
        int filasDigito = this.impresor.getFilasDigito();
        int columnasDigito = this.impresor.getColumnasDigito();
         //Calcula puntos fijos
         this.puntoFijo1[0] = 0;
         this.puntoFijo1[1] = 0 + pivotX;

         this.puntoFijo2[0] = (filasDigito / 2);
         this.puntoFijo2[1] = 0 + pivotX;

         this.puntoFijo3[0] = (filasDigito - 1);
         this.puntoFijo3[1] = 0 + pivotX;

         this.puntoFijo4[0] = (columnasDigito - 1);
         this.puntoFijo4[1] = (filasDigito / 2) + pivotX;

         this.puntoFijo5[0] = 0;
         this.puntoFijo5[1] = (columnasDigito - 1) + pivotX;
    }

     /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    public void adicionarDigito() {

        switch (this.valor) {
            case 1:
                adicionarSegmento(3);;
                adicionarSegmento(4);
                break;
            case 2:
                adicionarSegmento(5);
                adicionarSegmento(3);
                adicionarSegmento(6);
                adicionarSegmento(2);
                adicionarSegmento(7);
                break;
            case 3:
                adicionarSegmento(5);
                adicionarSegmento(3);
                adicionarSegmento(6);
                adicionarSegmento(4);
                adicionarSegmento(7);
                break;
            case 4:
                adicionarSegmento(1);
                adicionarSegmento(6);
                adicionarSegmento(3);
                adicionarSegmento(4);
                break;
            case 5:
                adicionarSegmento(5);
                adicionarSegmento(1);
                adicionarSegmento(6);
                adicionarSegmento(4);
                adicionarSegmento(7);
                break;
            case 6:
                adicionarSegmento(5);
                adicionarSegmento(1);
                adicionarSegmento(6);
                adicionarSegmento(2);
                adicionarSegmento(7);
                adicionarSegmento(4);
                break;
            case 7:
                adicionarSegmento(5);
                adicionarSegmento(3);
                adicionarSegmento(4);
                break;
            case 8:
                adicionarSegmento(1);
                adicionarSegmento(2);
                adicionarSegmento(3);
                adicionarSegmento(4);
                adicionarSegmento(5);
                adicionarSegmento(6);
                adicionarSegmento(7);
                break;
            case 9:
                adicionarSegmento(1);
                adicionarSegmento(3);
                adicionarSegmento(4);
                adicionarSegmento(5);
                adicionarSegmento(6);
                adicionarSegmento(7);
                break;
            case 0:
                adicionarSegmento(1);
                adicionarSegmento(2);
                adicionarSegmento(3);
                adicionarSegmento(4);
                adicionarSegmento(5);
                adicionarSegmento(7);
                break;
            default:
                break;
        }
    }

      /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.puntoFijo1, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(this.puntoFijo2, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(this.puntoFijo5, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(this.puntoFijo4, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(this.puntoFijo1, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.puntoFijo2, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.puntoFijo3, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }
      /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(int[] punto, String caracter) {
        int size = this.impresor.getSize();
        if (caracter.equalsIgnoreCase(CARACTER_HORIZONTAL)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                this.impresor.getMatrizImpr()[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                this.impresor.getMatrizImpr()[valor][punto[1]] = caracter;
            }
        }
    }

}
