import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Floyd {
    public static void main(String args[]){
        if (args.length == 1){
            FileReader fr = null;
            try {
                fr = new FileReader(args[0]);
                BufferedReader br = new BufferedReader(fr);
                String linea = br.readLine();
                String[] splitted;
                if (linea != null && isNum(linea)){
                    int numdestinos = Integer.parseInt(linea);
                    GrafoDP grafo = new GrafoDP(numdestinos);
                    linea = br.readLine();
                    while (linea != null){
                        splitted = linea.split(" ");
                        if (splitted.length == 3){
                            if (isNum(splitted[0]) && isNum(splitted[1]) && isNum(splitted[2])){
                                grafo.insertaArista(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
                                //ToDo: aplicar djkastra?
                            }
                        }
                    }
                }
                br.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean isNum(String number){
        try {
            Integer.parseInt(number);
            return true;
        }
        catch (NumberFormatException err){
            return false;
        }
    }
}
