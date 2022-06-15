
package logic.Colegas;

import java.util.ArrayList;
import java.util.HashMap;


public class Metodos {
    
    
     public static class Buddy {
    String ResultadoAsignar, ResultadoDesasignar;  
        class Pair {

            int lb, ub;

            Pair(int a, int b) {
                lb = a;
                ub = b;
            }
        }

        
        int size;
        ArrayList<Pair> arr[];

        HashMap<Integer, Integer> hm;

        @SuppressWarnings("unchecked")
        public Buddy(int s) {

            size = s;
            hm = new HashMap<>();

            int x = (int) Math.ceil(Math.log(s) / Math.log(2));
            arr = new ArrayList[x + 1];

            for (int i = 0; i <= x; i++) {
                arr[i] = new ArrayList<>();
            }
            arr[x].add(new Pair(0, size - 1));
        }

        public void allocate(int s) {
            int x = (int) Math.ceil(Math.log(s) / Math.log(2));
            int i;
            Pair temp = null;

            if (arr[x].size() > 0) {
                temp = (Pair) arr[x].remove(0);
                
                System.out.println("Memoria desde " + temp.lb
                        + " hasta " + temp.ub + " Asignada");
                
                
                
                ResultadoAsignar = "Memoria desde "+ temp.lb+ " hasta " + temp.ub + " Asignada";
               
                hm.put(temp.lb, temp.ub - temp.lb + 1);
                return;
            }
            for (i = x + 1; i < arr.length; i++) {
                if (arr[i].isEmpty()) {
                    continue;
                }
                break;
            }

            if (i == arr.length) {
                System.out.println("Error al momento de asignar memoria");
                
                ResultadoAsignar ="Error al momento de asignar memoria";
                
                return;
            }

            temp = (Pair) arr[i].remove(0);

            i--;

            for (; i >= x; i--) {
                Pair newPair = new Pair(temp.lb, temp.lb + (temp.ub - temp.lb) / 2);
                Pair newPair2 = new Pair(temp.lb + (temp.ub - temp.lb + 1) / 2, temp.ub);

                arr[i].add(newPair);
                arr[i].add(newPair2);

                temp = (Pair) arr[i].remove(0);
            }
            System.out.println("Memoria desde " + temp.lb
                    + " hasta " + temp.ub + " Asignada");
            
            ResultadoAsignar = "Memoria desde "+ temp.lb+ " hasta " + temp.ub + " Asignada";
            
            hm.put(temp.lb, temp.ub - temp.lb + 1);
        }

        public void deallocate(int s) {
            if (!hm.containsKey(s)) {
                System.out.println("Erorr al momento de designar memoria");
                
                ResultadoDesasignar = "Erorr al momento de designar memoria";
                
                return;
            }

            int x = (int) Math.ceil(Math.log(hm.get(s)) / Math.log(2));
            int i, buddyNumber, buddyAddress;

            arr[x].add(new Pair(s, s + (int) Math.pow(2, x) - 1));

            System.out.println("Memoria desde " + s
                    + " hasta " + (s + (int) Math.pow(2, x) - 1) + " liberada");

            ResultadoDesasignar = "Memoria desde " + s + " hasta " + (s + (int) Math.pow(2, x) - 1) + " liberada";
            
            buddyNumber = s / hm.get(s);

            if (buddyNumber % 2 != 0) {
                buddyAddress = s - (int) Math.pow(2, x);
            } else {
                buddyAddress = s + (int) Math.pow(2, x);
            }

            for (i = 0; i < arr[x].size(); i++) {
                if (arr[x].get(i).lb == buddyAddress) {
                    if (buddyNumber % 2 == 0) {
                        arr[x + 1].add(new Pair(s, s + 2 * ((int) Math.pow(2, x)) - 1));
                        System.out.println("Liberación de bloques " + s + " y " + buddyAddress + " realizado");
                        
                        ResultadoDesasignar = "Liberación de bloques " + s + " y " + buddyAddress + " realizado";
                        
                    } else {
                        arr[x + 1].add(new Pair(buddyAddress, buddyAddress + 2 * ((int) Math.pow(2, x)) - 1));
                        System.out.println("Liberación de bloques " + buddyAddress + " y " + s + " realizado");
                        
                        ResultadoDesasignar = "Liberación de bloques " + buddyAddress + " y " + s + " realizado";
                    }
                    arr[x].remove(i);
                    arr[x].remove(arr[x].size() - 1);
                    break;
                }
            }
            hm.remove(s);
        }

        public String getResultadoAsignar() {
            return ResultadoAsignar;
        }

        public String getResultadoDesasignar() {
            return ResultadoDesasignar;
        }

       
    }
    
    
}
