// Clase encargada de generar nuemeros aleatorios
// utilizando el metodo congruencial mixto
public class RandomGenerator {
    int k,m,a,c,Xn,sem,l;
    //Constructor de la clase
    public RandomGenerator(){
        //Se declaran las variables a utilizar
        //Utilizamos una combinación que haya pasado las
        //pruebas de independencia y uniformidad
        this.k=16;
        this.m=(int)Math.pow(2,this.k);
        this.a=5;
        this.c=5;
        this.Xn=3;
        this.sem=Xn;
        this.l=0;
        for (int i=0;i<300;i++)
            this.getNum();
    }
    //Metodo para reasiganr las variables
    public void setGenerator(int nk,int na,int nc,int nXn){
        this.k=nk;
        this.m=(int)Math.pow(2,this.k);
        this.a=na;
        this.c=nc;
        this.Xn=nXn;
        this.sem=Xn;
        this.l=0;
        for (int i=0;i<100;i++)
            this.getNum();
    }
    //Metodo que genera un nuemero aleatorio
    public double getNum(){
        //Método congruencial mixto
        int aux=(a*sem+c)%m;
        if(aux!=Xn){
            l++;
            sem=aux;
            return (double)aux/m;
        }else{
            if(l==(m)-1){
                l++;
                return (double)aux/m;
            }else{
                l++;
                return -1;
            }
        }
    }
}
