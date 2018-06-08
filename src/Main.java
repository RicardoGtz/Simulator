import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        //Se solicitan los parametros iniciales para la simulacion
        Scanner sc=new Scanner(System.in);
        System.out.println("Simulación");
        System.out.println("Numero de maquinas: ");
        int m=sc.nextInt();
        System.out.println("Numero de trabajadores: ");
        int s=sc.nextInt();
        System.out.println("Numero de horas a simular: ");
        int hrs=sc.nextInt();

        //Se crea la cola de prioridad de proximos fallos en las maquinas
        MachineFailureComparator mfc=new MachineFailureComparator();
        PriorityQueue <Machine> failureQueue= new PriorityQueue<Machine>(m,mfc);
        //Se crea la cola de prioridad de proximas reparaciones a terminar
        MachineRepairComparator mrc=new MachineRepairComparator();
        PriorityQueue <Machine> repairingQueue=new PriorityQueue<Machine>(m,mrc);
        //Se crea la cola de espera de maquinas por repar
        Queue<Machine> toRepairQueue=new LinkedList<Machine>();

        //Se crean los genradores de numeros aleatorios y variables aleatorias
        RandomGenerator generator=new RandomGenerator();
        RandomVars varGen=new RandomVars(generator);

        //Se define el tiempo y se crean las maquinas
        double time=0; int repairmenInUse=0;
        for(int i=0;i<m;i++)
            failureQueue.add(new Machine(varGen,2,8,time));

        //Se inicia la simulacion
        Machine aux;
        while (time<hrs){
            if(toRepairQueue.isEmpty()||repairmenInUse==s){
                if(!failureQueue.isEmpty()&&
                        (repairingQueue.isEmpty() || failureQueue.peek().getFailureHour()<repairingQueue.peek().getRepairHour())){
                    aux=failureQueue.poll();
                    time=aux.getFailureHour();
                    if(repairmenInUse<s){
                        aux.setNewRepairHour(time);
                        repairmenInUse++;
                        repairingQueue.add(aux);
                        System.out.println("Timepo: "+time+" Repara");
                    }else{
                        toRepairQueue.add(aux);
                        System.out.println("Timepo: "+time+" Espera");
                    }
                }else{
                    aux=repairingQueue.poll();
                    time=aux.getRepairHour();
                    repairmenInUse--;
                    aux.setNewFailureHour(time);
                    failureQueue.add(aux);
                    System.out.println("Timepo: "+time+" Termina Reparacion");
                }
            }else{
                aux=toRepairQueue.poll();
                aux.setNewRepairHour(time);
                repairmenInUse++;
                repairingQueue.add(aux);
                System.out.println("Timepo: "+time+" Deja de esperar y repara");
            }
        }
        System.out.println("***** Termina la simulacion ******");
        System.out.println("Maquinas en cola de por reparar:"+toRepairQueue.size());
        System.out.println("Maquinas en cola de reparacion: "+repairingQueue.size());
        //Se vacian las colas en reparacion y por reparar
        if(!repairingQueue.isEmpty())
            while (!repairingQueue.isEmpty())
                failureQueue.add(repairingQueue.poll());
        if(!toRepairQueue.isEmpty())
            while (!toRepairQueue.isEmpty()){
                aux=toRepairQueue.poll();
                aux.addTotalBreakingTime(time);
                failureQueue.add(aux);
            }
        //Se calculan los costos de la simulacion
        double breakCost=0;
        while (!failureQueue.isEmpty()){
            aux=failureQueue.poll();
            breakCost+=aux.getTotalBreakingTime()+aux.getTotalRepairingTime();
        }
        breakCost=(breakCost/m);
        System.out.println("Timpo promedio en falla: "+breakCost+"hrs.");
        breakCost=breakCost*50;
        System.out.println("El costo por maquinas en espera fue de: $"+breakCost);
        double repairmenCost=time*s*10;
        System.out.println("El costo por sueldo de trabajadores fue de: $"+repairmenCost);
        System.out.println("El costo total de la simulacion fue de: $"+(repairmenCost+breakCost));
    }
}
