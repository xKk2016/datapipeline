import java.io.*;

public class Filesplit {
    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir");
        FileReader fileReader = new FileReader(root+"\\src"+"\\chenfei.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str=bufferedReader.readLine();
        int index = 0;
        while (str !=null){
            FileWriter fileWriter =  new FileWriter("C:\\Users\\KK\\IdeaProjects\\datapipeline\\src\\chenfei\\chenfei"+index+".txt");
            for (int i=0;i<10;i++){
                fileWriter.append(str.replace("b", "")
                        .replace("'", "")
                        .replace("(","")
                        .replace(")",""));
                fileWriter.append("\n");
                str = bufferedReader.readLine();
                if (str==null) break;
            }
            index++;
            fileWriter.close();
        }





        fileReader.close();


    }
}
