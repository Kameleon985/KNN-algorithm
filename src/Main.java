import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        KNN knn = new KNN(args[0],args[1],args[2]);

        for(int i=0;i<knn.classified.size();i++){
            System.out.println(knn.testL.get(i).toString()+"  "+knn.classified.get(i).decAtr);
        }
        
        knn.accuracy();

        while(true){
            Scanner sc = new Scanner(System.in);
            String[]par = {String.valueOf(sc.nextDouble()),String.valueOf(sc.nextDouble()),String.valueOf(sc.nextDouble()),String.valueOf(sc.nextDouble()),null};
            Iris iris = new Iris(par);
            knn.findNN(iris);
            System.out.println(knn.classified.get(knn.classified.size()-1));
        }
    }
}
