import java.util.ArrayList;

public class GrafoDP {
    private int[][] array;
    private boolean[] visitadosDFS;
    private boolean[] visitadosBFS;

    public GrafoDP(){
        array = new int[0][0];
    }

    public GrafoDP(int vertices){
        if (vertices < 0) vertices = 0;
        array = new int[vertices][vertices];
        for (int i = 0; i< vertices; i++){
            for (int j = 0; j < vertices; j++){
                array[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public boolean esVacio(){
        if (array != null){
            for (int i = 0; i < array.length; i++){
                for (int j = 0; j < array[i].length; j++){
                    if (array[i][j] != Integer.MAX_VALUE) return true;
                }
            }

        }
        return false;
    }

    public boolean insertaArista(int ori, int des, int peso) throws IndexOutOfBoundsException{
        if (ori < 0 || ori > array.length-1) throw new IndexOutOfBoundsException("No se puede insertar la arista entre "+ori+" y "+des);
        if (des < 0 || des > array.length-1) throw new IndexOutOfBoundsException("No se puede insertar la arista entre "+ori+" y "+des);
        if (peso < 0) return false;
        if (array[ori][des] != Integer.MAX_VALUE) return false;
        else {
            array[ori][des] = peso;
            return true;
        }
    }

    public int recuperaArista(int ori, int des) throws IndexOutOfBoundsException{
        if (ori < 0 || ori > array.length-1) throw new IndexOutOfBoundsException("No se puede recuperar la arista entre "+ori+" y "+des);
        if (des < 0 || des > array.length-1) throw new IndexOutOfBoundsException("No se puede recuperar la arista entre "+ori+" y "+des);
        return array[ori][des];
    }

    public void borraArista(int ori, int des) throws IndexOutOfBoundsException{
        if (ori < 0 || ori > array.length-1) throw new IndexOutOfBoundsException("No se puede borrar la arista entre "+ori+" y "+des);
        if (des < 0 || des > array.length-1) throw new IndexOutOfBoundsException("No se puede borrar la arista entre "+ori+" y "+des);
        array[ori][des] = Integer.MAX_VALUE;
    }

    public int getVertices(){
        return array.length;
    }

    public int getAristas(){
        int cont = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != Integer.MAX_VALUE) cont++;
            }
        }
        return cont;
    }

    public void escribeGrafo(){
        System.out.println("Vertices: "+getVertices());
        System.out.println("Aristas: "+getAristas());
        for (int i = 0; i<array.length; i++){
            for (int j = 0; j< array.length; j++){
                if (array[i][j] != Integer.MAX_VALUE) System.out.println(i+" "+j+" "+array[i][j]);
            }
        }
    }

    public ArrayList<Integer> recorridoProfunidad(int node) {
        ArrayList<Integer> recorridos = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        visitadosDFS[node] = true;
        recorridos.add(node);
        queue.add(node);
        while (!queue.isEmpty()) {
            int j = queue.remove(0);
            for (int i = 0; i < array.length; i++) {
                if (array[j][i] != Integer.MAX_VALUE&& !visitadosDFS[i]) {
                    queue.add(i);
                    recorridos.addAll(recorridoProfunidad(i));
                    visitadosDFS[i] = true;
                }
            }
        }
        return recorridos;
    }

    public void escribeDFS(){
        for (int x=0; x<getVertices(); x++) {
            System.out.print("D"+(x+1)+": ");
            visitadosDFS = new boolean[array.length];
            ArrayList<Integer> enProfundidad = recorridoProfunidad(x);
            for (int i = 0; i < enProfundidad.size(); i++) {
                System.out.print(enProfundidad.get(i));
                if (i < enProfundidad.size()-1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> recorridoAnchura(int node) {
        ArrayList<Integer> recorrido = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        visitadosBFS[node] = true;
        recorrido.add(node);
        queue.add(node);
        while (!queue.isEmpty()) {
            int j = queue.remove(0);
            for (int i = 0; i < array.length; i++) {
                if (array[j][i] != Integer.MAX_VALUE && !visitadosBFS[i]) {
                    queue.add(i);
                    recorrido.add(i);
                    visitadosBFS[i] = true;
                }
            }
        }
        return recorrido;
    }

    public void escribeBFS(){
        for (int x=0; x<getVertices(); x++) {
            System.out.print("B"+(x+1)+": ");
            visitadosBFS = new boolean[array.length];
            ArrayList<Integer> enAnchura = recorridoAnchura(x);
            for (int i = 0; i < enAnchura.size(); i++) {
                System.out.print(enAnchura.get(i));
                if (i < enAnchura.size()-1) System.out.print(", ");
            }
            System.out.println();
        }
    }
    public int[] vecinas (int x) {
        int count = 0;
        for (int i=0; i<array[x].length; i++) {
            if (array[x][i] != Integer.MAX_VALUE) count++;
        }
        final int[] answer = new int[count];
        count = 0;
        for (int i=0; i<array[x].length; i++) {
            if (array[x][i] != Integer.MAX_VALUE) answer[count++]=i;
        }
        return answer;
    }
}
