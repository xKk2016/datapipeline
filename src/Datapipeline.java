
import java.io.*;
import java.util.ArrayList;

public class Datapipeline {
    public static float[] singleclick(ArrayList<String> strings){
        float[] xx={0,0};
        float sum=0;
        int count=0;
        float average=0;
        float fangcha=0;
        for (int i=0;i<10;i=i+2){
            String down = strings.get(i).split(",")[0];
            String up = strings.get(i+1).split(",")[0];
            sum+=(float)(1000000*Double.parseDouble(up)-1000000*Double.parseDouble(down));
            count++;
        }
        average = sum/count;
        System.out.println(average);
        for (int i=0;i<10;i=i+2){
            String down = strings.get(i).split(",")[0];
            String up = strings.get(i+1).split(",")[0];
            float del=Float.parseFloat(up)-Float.parseFloat(down);
            fangcha+=Math.pow(del-average,2);
        }
        System.out.println(fangcha);
        xx[0]=average;
        xx[1]=fangcha;


        return xx;
    }
    public static float[] speedAndDistance(ArrayList<String> strings){
        float[] xx={0,0,0,0,0,0,0,0};
        float averageSpeed;
        float maxSpeed=0;
        float minSpeed=Float.MAX_VALUE;
        float speedSum=0;

        float speedFangcha=0;
        ArrayList<Float> speed = new ArrayList<Float>();

        float averageDistance;
        float maxDistance=0;
        float minDistance=Float.MAX_VALUE;
        float distanceSum=0;

        float distanceFangcha=0;
        ArrayList<Float> distance = new ArrayList<Float>();

        for (int i=0;i<8;i=i+2){
            String down_x = strings.get(i).split(",")[2];
            String up_x = strings.get(i+2).split(",")[2];
            String down_y =strings.get(i).split(",")[3];
            String up_y = strings.get(i+2).split(",")[3];

            String down = strings.get(i).split(",")[0];
            String up = strings.get(i+2).split(",")[0];

            float distanced =(float) Math.sqrt(Math.pow(Double.parseDouble(up_y)-Double.parseDouble(down_y),2)
                    +Math.pow(Double.parseDouble(up_x)-Double.parseDouble(down_x),2));

            float timer =(float) (1000000*Double.parseDouble(up)-1000000*Double.parseDouble(down));
            float speeder = distanced/timer;

            if (maxSpeed<speeder) maxSpeed=speeder;
            if (minSpeed>speeder) minSpeed=speeder;
            if (maxDistance<distanced) maxDistance=distanced;
            if (minDistance>distanced) minDistance=distanced;
            distance.add(distanced);
            speed.add(speeder);
        }
        for (int i=0;i<4;i++){
            distanceSum+=distance.get(i);
            speedSum+=speed.get(i);
        }
        averageDistance = distanceSum/4;
        averageSpeed = speedSum/4;
        for (int i=0;i<4;i++){
            distanceFangcha+=Math.pow(distance.get(i)-averageDistance,2);
            speedFangcha+=Math.pow(speed.get(i)-averageSpeed,2);
        }
        xx[0] = averageSpeed;
        xx[1] = minSpeed;
        xx[2] = maxSpeed;
        xx[3] = speedFangcha;

        xx[4] = averageDistance;
        xx[5] = minDistance;
        xx[6] = maxDistance;
        xx[7] = distanceFangcha;
        return xx;
    }

    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir");
        String fileName = root+"\\src"+"\\datapipeline\\mouselogger";
        FileWriter fileWriter = new FileWriter(root+"\\src"+"\\datapipeline.txt");

        try {

            for (int i=0;i<33;i++){
                FileReader fileReader = new FileReader(fileName+i+".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                ArrayList<String> arrayList=new ArrayList<>();
                String str;
                while ((str=bufferedReader.readLine())!=null){
                    arrayList.add(str);
                }

                fileWriter.append(singleclick(arrayList)[0]+","+
                        (float) Math.sqrt(singleclick(arrayList)[1])+","+
                        speedAndDistance(arrayList)[0]+","+
                        speedAndDistance(arrayList)[1]+","+
                        speedAndDistance(arrayList)[2]+","+
                        (float)Math.sqrt(speedAndDistance(arrayList)[3])+","+
                        speedAndDistance(arrayList)[4]+","+
                        speedAndDistance(arrayList)[5]+","+
                        speedAndDistance(arrayList)[6]+","+
                        (float)Math.sqrt(speedAndDistance(arrayList)[7]));
                fileWriter.append("\n");
                fileReader.close();
            }





        }catch (IOException e){
            e.printStackTrace();

            fileWriter.close();
        }
        fileWriter.close();






    }
}
