import java.text.DecimalFormat;

public class Main {
    public static void main(String [] args){
        int x=Integer.MAX_VALUE;
        RandomGenerator generator=new RandomGenerator();
        RandomVars var=new RandomVars(generator);
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        for(int i=0;i<10;i++)
            System.out.println(df.format(var.getExpo(8)));
        System.out.println("El maximo valor es: "+x);
    }
}
