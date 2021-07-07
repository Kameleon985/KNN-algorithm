public class Iris {

    public double[]par;
    public String decAtr;

    public Iris(String[]par){
        this.par=new double[par.length-1];
        for(int i=0;i<par.length-1;i++){
            this.par[i]=Double.parseDouble(par[i]);
        }
        this.decAtr=par[par.length-1];
    }

    public double compare(Iris dest){
        double res=0;
        for(int i=0;i<par.length-1;i++){
            res+=Math.pow(par[i]-dest.par[i],2);
        }
        return Math.sqrt(res);
    }

    public String toString(){
        String res="";
        for (double d:par) {
            res+=d+";";
        }
        res+=decAtr;
        return res;
    }
}
