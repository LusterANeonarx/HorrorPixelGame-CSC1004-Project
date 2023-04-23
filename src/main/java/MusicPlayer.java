import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer implements Runnable{
    File soundFile;
    Thread thread;
    boolean circulate;
    static final String path = System.getProperty("user.dir")+"/src/main/java/musics/";
//    static final String path = "./target/classes/";
    public MusicPlayer(String filepath,boolean circulate) throws FileNotFoundException{

        this.circulate = circulate;
        soundFile = new File(path+filepath);
        if(!soundFile.exists()){
            throw new FileNotFoundException(filepath+" not found");
        }
    }

    @Override
    public void run() {
        byte[] auBuffer = new byte[1024*1024];
        do{
            AudioInputStream audioInputStream = null;
            SourceDataLine auline = null;
            try{
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
                auline.start();
                int byteCount = 0;
                while(byteCount !=-1){
                    byteCount = audioInputStream.read(auBuffer,0,auBuffer.length);
                    if(byteCount>=0){
                        auline.write(auBuffer,0,byteCount);
                    }
                }

            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } finally{
                auline.drain();
                auline.close();
            }
        } while (circulate);
    }

    public void play(){
        thread = new Thread(this);
        thread.start();
    }
    public void stop(){
        thread.interrupt();
    }


}
