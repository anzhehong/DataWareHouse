import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;
/**
 * Created by Harold_LIU on 11/26/15.
 */
public class MovieNamePipeline implements Pipeline{
    private static File movieNameFile = new File("/Users/Harold_LIU/Desktop/Amazon/OutPut.txt");
    private static File failedMovieId = new File("/Users/Harold_LIU/Desktop/Amazon/ComparedMoiveId_N.txt");
    private BufferedWriter bufferWritter = null;
    private BufferedWriter faildWritter = null;
    private  static  int count;

    public void process(ResultItems resultItems, Task task) {

        try {
            if (!movieNameFile.exists()) {
                movieNameFile.createNewFile();
            }
            bufferWritter = new BufferedWriter(new FileWriter(movieNameFile, true));
            faildWritter = new BufferedWriter(new FileWriter(failedMovieId,true));
            String movieId = resultItems.get("productId");
            String movieName = resultItems.get("title");
            String moiveInfo = resultItems.get("detail");

            if(!movieId.equals(null)) {
                System.out.println(movieId);
                bufferWritter.write("\n==============================\n" + "count" + count + "\nproductId:" + movieId + "\nproductName" + movieName + "\nDetail:" + moiveInfo);
                faildWritter.write(movieId+"\n");
                bufferWritter.flush();


                faildWritter.flush();
                faildWritter.close();
                bufferWritter.close();
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
