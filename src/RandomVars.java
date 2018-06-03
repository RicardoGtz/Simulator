public class RandomVars {
    RandomGenerator randGen;
    public RandomVars(RandomGenerator randGen){
        this.randGen=randGen;
    }

    //V.A Uniforme
    double getU(double a,double b){
        if(a<b)
            return a+this.randGen.getNum()*(b-a);
        else
            return -1;
    }

    //V.A Exponencial
    double getExpo(double B){
        if(B>0)
            return -B*Math.log(this.randGen.getNum());
        else
            return -1;
    }

    //V.A m-Erlang
    double getM_Erlang(double B,int m){
        if(m>0&&B>0){
            double sum=1;
            for(int i=0;i<m;i++)
                sum*=this.randGen.getNum();
            return (-B/m)*Math.log(sum);
        }else
            return -1;
    }

}
