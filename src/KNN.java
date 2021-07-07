import java.io.*;
import java.util.*;

public class KNN {

    public List<Iris>trainL;
    public List<Iris>testL;
    public int k;
    public List<Iris>classified;

    public KNN(String k,String trainTab, String testTab) throws IOException {
        BufferedReader trt = new BufferedReader(new FileReader(trainTab));
        BufferedReader tet = new BufferedReader(new FileReader(testTab));
        String line=trt.readLine();
        trainL = new ArrayList<>();
        do{
            trainL.add(new Iris(line.split(";")));
        }while ((line=trt.readLine())!=null);
        line=tet.readLine();
        testL = new ArrayList<>();
        do{
            testL.add(new Iris(line.split(";")));
        }while((line=tet.readLine())!=null);
        this.k = Integer.parseInt(k);
        trt.close();
        tet.close();
        classified=new ArrayList<>();
        for(Iris i:testL){
            findNN(i);
        }
    }

    public void findNN(Iris i){
            HashMap<Iris,Double>tmp=new HashMap<>();
            for(int l=0;l<k;l++) {
                tmp.put(trainL.get(l),i.compare(trainL.get(l)));
            }
            for (int j=k;j<trainL.size();j++) {
                Map.Entry<Iris,Double>maxEntry= null;
                for(Map.Entry<Iris,Double> entry:tmp.entrySet()){
                    if(maxEntry==null||entry.getValue().compareTo(maxEntry.getValue())>0)
                        maxEntry=entry;
                }
                double currentDistance=i.compare(trainL.get(j));
                if (currentDistance<maxEntry.getValue()) {
                    tmp.remove(maxEntry.getKey());
                    tmp.put(trainL.get(j),currentDistance);
                }
            }
            HashMap<Iris,Integer>count=new HashMap<>();
            for(Map.Entry<Iris,Double> entry:tmp.entrySet()){
                if(count.containsKey(entry.getKey())){
                    count.replace(entry.getKey(),count.get(entry.getKey())+1);
                }else{
                    count.put(entry.getKey(),1);
                }
            }
            int max = 0;
            Iris ir=null;
            for(Map.Entry<Iris,Integer>m:count.entrySet()){
                if(m.getValue()>max) {
                    max = m.getValue();
                    ir=m.getKey();
                }
            }
            ir.par=i.par;
            classified.add(ir);
    }

    public void accuracy(){
        int correct=0;
        for(int i=0;i<testL.size();i++){
            if(testL.get(i).decAtr.equals(classified.get(i).decAtr)){
                correct+=1;
            }
        }
        double res = (double) (correct)/testL.size();
        System.out.println("Dokładność klasyfikacji: "+res*100);
    }
}