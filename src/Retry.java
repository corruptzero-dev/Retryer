package ru.corruptzero;

public class Retry <T extends Retryable> {
    T clazz;
    int attempts = 0;
    int maxAttempts;
    int ms;

    public Retry(T clazz, int maxAttempts, int ms) throws InterruptedException {
        this.clazz = clazz;
        this.maxAttempts = maxAttempts;
        this.ms = ms;
        TaskThread tt;
        while (true){
            if(this.attempts<=maxAttempts){
                int i = 0;
                tt = new TaskThread((Query) this.clazz);
                tt.start();
                while (i<ms){
                    if(!tt.isAlive()){
                        break;      //поток завершился раньше
                    }
                    Thread.sleep(ms/100);
                    i+=ms/100;
                }
                if(tt.hasError){
                    System.out.print("Retry!\n");
                    tt.interrupt();
                    attempts++;
                }
                if(tt.isAlive()){
                    System.out.print("Timeout. Retry!\n");
                    tt.interrupt();
                    attempts++;     //timeout
                }
                System.out.println("Success!");
            } else {
                System.out.println("out of attempts, halt.");
            }
            break;
        }
    }
}
