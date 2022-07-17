
public class SynchronizeThreads {

     private static int thread_num = 1;
     private static int iter_num = 1;
     public static void main(String[] args) throws

     InterruptedException {

          Thread[] ethreads = new Thread[3];
          for(int thread_index = 0; thread_index < ethreads.length; thread_index++) {

               int threadId = thread_index+1;
               ethreads[thread_index] = new Thread(new

               Runnable() {

                     @Override
                     public void run() {
                          while (true) {
                               synchronized(this) {

                                    if (thread_num == threadId && iter_num <= 5) {
                                         System.out.println("Thread " + thread_num + " - iteration no. " + iter_num);
                                         System.out.println();
                                         thread_num++;

                                         if (threadId == ethreads.length) {
                                              iter_num++;
                                              thread_num = 1;
                                         }
                                    }
                                    if (iter_num == 6) {
                                         break;
                                    }
                               }
                          }
                     }
               });
               ethreads[thread_index].start();
          }
          for (Thread ethread : ethreads) {
               ethread.join();
          }
     }
}