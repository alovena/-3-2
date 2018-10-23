
import java.util.concurrent.*;


public class MultithreadedMaxFinder {
   public static int max(int[] data) throws InterruptedException, ExecutionException {

      if (data.length == 1) {
         return data[0];
      } else if (data.length == 0) {
         throw new IllegalArgumentException();
      }

      // split the job into 2 pieces

      FindMaxTask task1 = new FindMaxTask(data, 0, data.length * 1 / 4); // 0부터 중간까지, 중간부터 끝까지 //
      FindMaxTask task2 = new FindMaxTask(data, data.length * 1 / 4, data.length * 2 / 4);
      FindMaxTask task3 = new FindMaxTask(data, data.length * 2 / 4, data.length * 3 / 4);
      FindMaxTask task4 = new FindMaxTask(data, data.length * 3 / 4, data.length);
      
      //FindMaxTask task1 = new FindMaxTask(data, 0, data.length/2); //0부터 중간까지, 중간부터 끝까지
      //FindMaxTask task2 = new FindMaxTask(data, data.length/2, data.length);

      // spawn 2 threads ExecutorService service = Executors.newFixedThreadPool(4);
      // 대기하는 스레드 개수

      ExecutorService service = Executors.newFixedThreadPool(4);
      
      Future<Integer> future1 = service.submit(task1); //결과를 받는 인스턴스
      Future<Integer> future2 = service.submit(task2);
      Future<Integer> future3 = service.submit(task3);
      Future<Integer> future4 = service.submit(task4);
      return Math.max(future1.get(), future2.get());


   }

   public static void main (String[] args)

   {

      int samples[] = new int[1000];

      for (int i = 0; i < 1000; i++)
         samples[i] = (int) (Math.random() * 1000 - 1);
      int maxReturn;
      try {
         long stratTime = System.nanoTime();
         maxReturn = max(samples);

         long esTimatedTime1 = System.nanoTime() - stratTime;
         System.out.println("Max:=" + maxReturn);
         System.out.println(esTimatedTime1);

      } catch (InterruptedException ex) {
         System.err.println(ex.getMessage());
      } catch (ExecutionException ex) {
         System.err.println(ex.getMessage());
      }

   }
}