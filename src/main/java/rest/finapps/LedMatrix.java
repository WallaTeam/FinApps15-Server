package rest.finapps;


/**
 * Created by piraces on 6/11/15.
 */
public class LedMatrix{
    String command="led";
    Process p;

    public void doOk(){
        try{
            p = Runtime.getRuntime().exec(command+" ok");
        }
        catch(Exception e){
        }
    }

    public void doFail(){
        try{
            p = Runtime.getRuntime().exec(command+" fail");
        }
        catch(Exception e){
        }
    }

    public void doUp(){
        try{
            p = Runtime.getRuntime().exec(command+" up");
        }
        catch(Exception e){
        }
    }

    public void doDown(){
        try{
            p = Runtime.getRuntime().exec(command+" down");
        }
        catch(Exception e){
        }
    }
}
