package Practica;

import java.util.Arrays;
import java.util.List;


//Clase Persona
class Persona {                
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

//Clase Telefono
class Telefono {

    private String numero;

    public Telefono(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }
}

//Clase Llamada (Donde usaremos hilos)
class Llamada extends Thread {

    //Cada llamada tiene un telefono
    private Telefono telefono;
    //Pero cada llamada puede involucrar una o más personas.
    private List<Persona> personas;

    //Constructor
    public Llamada(Telefono telefono, List<Persona> personas) {
        this.telefono = telefono;
        this.personas = personas;
    }

    //Sobreescritura del metodo run() de Thread
    @Override
    public void run() {
        //Aqui mostramos a que telefono apunta la llamada        
        System.out.println("Iniciando llamada al numero: " + telefono.getNumero());
        
        //Con la llamada en curso mostramos con quien o con quienes se esta hablando
        //en simultaneo en esta unica llamada (Requerido segun el diagrama de clases)
        for (Persona persona : personas) {
            System.out.println("Hablando con: " + persona.getNombre());
        }
        
        try {
            //Se trata de simular duración de la llamada
            //mientras no se interrumpa el hilo
            Thread.sleep(2000);
            
        } catch (InterruptedException e) {
            System.out.println("Llamada interrumpida");
        }
        System.out.println("Llamada finalizada al número: " + telefono.getNumero());
    }
}

public class CallCenter {

    public static void main(String[] args) {
        
        //Creamos los objetos personas que estaran en las llamadas
        Persona p1 = new Persona("Lina");
        Persona p2 = new Persona("Leonardo");
        Persona p3 = new Persona("Daniel");
        Persona p4 = new Persona("Sara");

        //Luego, a que telefonos apuntara la llamada
        Telefono t1 = new Telefono("123-4567");
        Telefono t2 = new Telefono("987-6543");
        Telefono t3 = new Telefono("555-7890");

        //Agrupamos las personas de acuerdo a si van a 
        //ser llamadas individuales o grupales
        List<Persona> personas1 = Arrays.asList(p1,p3);
        List<Persona> personas2 = Arrays.asList(p2);
        List<Persona> personas3 = Arrays.asList(p4);

        //Creamos las llamadas para cada grupo de personas
        Llamada llamada1 = new Llamada(t1, personas1);
        Llamada llamada2 = new Llamada(t2, personas2);
        Llamada llamada3 = new Llamada(t3, personas3);
        
        //Inicia tres hilos al mismo tiempo
        llamada1.start();
        llamada2.start();
        llamada3.start();   
    }
}

