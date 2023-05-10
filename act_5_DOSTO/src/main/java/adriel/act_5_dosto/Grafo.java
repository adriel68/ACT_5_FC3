package adriel.act_5_dosto;

import java.util.*;

class Vertice {
    private String etiqueta;
    private double peso;

    public Vertice(String etiqueta, double peso) {
        this.etiqueta = etiqueta;
        this.peso = peso;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public double getPeso() {
        return peso;
    }
}

public class Grafo {
    private Map<String, List<Vertice>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarVertice(String etiqueta) {
        listaAdyacencia.put(etiqueta, new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, double peso) {
        listaAdyacencia.get(origen).add(new Vertice(destino, peso));
    }

    public List<String> recorridoAnchura(String origen) {
        List<String> visitados = new ArrayList<>();
        Queue<String> cola = new LinkedList<>();
        cola.add(origen);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            visitados.add(actual);

            for (Vertice vecino : listaAdyacencia.get(actual)) {
                String etiquetaVecino = vecino.getEtiqueta();
                if (!visitados.contains(etiquetaVecino)) {
                    cola.add(etiquetaVecino);
                }
            }
        }

        return visitados;
    }

    public List<String> recorridoProfundidad(String origen) {
        List<String> visitados = new ArrayList<>();
        Stack<String> pila = new Stack<>();
        pila.push(origen);

        while (!pila.isEmpty()) {
            String actual = pila.pop();

            if (!visitados.contains(actual)) {
                visitados.add(actual);

                for (Vertice vecino : listaAdyacencia.get(actual)) {
                    pila.push(vecino.getEtiqueta());
                }
            }
        }

        return visitados;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo();

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Crear grafo");
            System.out.println("2. Recorrido en anchura");
            System.out.println("3. Recorrido en profundidad");
            System.out.println("4. Salir del programa");
            System.out.print("Selecciona una de las siguientes opciónes: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese los nodos siguiendo el siguiente formato: x,y,z ");
                    String nodos = sc.next();
                    String[] etiquetas = nodos.split(",");
                    for (String etiqueta : etiquetas) {
                        grafo.agregarVertice(etiqueta);
                    }

                    System.out.print("Ingrese las aristas siguiendo el siguiente formato: 'origen-destino-peso' (separadas por comas): ");
                    String aristas = sc.next();
                    String[] aristasArray = aristas.split(",");
                    for (String arista : aristasArray) {
                        String[] datosArista = arista.split("-");
                        String origen = datosArista[0];
                        String destino = datosArista[1];
                        double peso = Double.parseDouble(datosArista[2]);
                        grafo.agregarArista(origen, destino, peso);
                    }

                    System.out.println("Grafo creado! ");
                    break;
case 2:
    if (grafo.listaAdyacencia.isEmpty()) {
        System.out.println("Cree un grafo primero.");
    } else {
        System.out.print("Ingrese el nodo origen: ");
        String origenAnchura = sc.next();
        List<String> recorridoAnchura = grafo.recorridoAnchura(origenAnchura);
        System.out.println("Recorrido en anchura: " + recorridoAnchura);
    }
    break;
case 3:
    if (grafo.listaAdyacencia.isEmpty()) {
        System.out.println("Cree un grafo primero.");
    } else {
        System.out.print("Ingrese el nodo origen: ");
        String origenProfundidad = sc.next();
        List<String> recorridoProfundidad = grafo.recorridoProfundidad(origenProfundidad);
        System.out.println("Recorrido en profundidad: " + recorridoProfundidad);
    }
    break;
case 4:
    System.out.println("Gracias por usar mi programa!");
    System.exit(0);
default:
    System.out.println("Opción no válida");
    break;
}
}
}
    }