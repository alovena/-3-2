import java.util.concurrent.*;

class FindMaxTask implements Callable<Integer> {
	   private int[] data;
	   private int start;
	   private int end;
	   FindMaxTask(int[] data, int start, int end) {
	      this.data = data;
	      this.start = start;
	      this.end = end;
	   }
	   public Integer call() {
	      int max = Integer.MIN_VALUE;
	      for (int i = start; i < end; i++) {
	         if (data[i] > max)
	            max = data[i];
	      }
	      return max;
	   }
	}

public class MultiThreaded {
   public static int max(int[] data) throws InterruptedException, ExecutionException {

      if (data.length == 1) {
         return data[0];
      } else if (data.length == 0) {
         throw new IllegalArgumentException();
      }

      // split the job into 2 pieces

      FindMaxTask task1 = new FindMaxTask(data, 0, data.length * 1 /8); 
      FindMaxTask task2 = new FindMaxTask(data, data.length * 1 /8, data.length * 2 /8);
      FindMaxTask task3 = new FindMaxTask(data, data.length * 2 /8,data.length * 3/8);
      FindMaxTask task4 = new FindMaxTask(data, data.length * 3 /8, data.length * 4 /8);
      FindMaxTask task5 = new FindMaxTask(data, data.length * 4 /8, data.length * 5 /8);
      FindMaxTask task6 = new FindMaxTask(data, data.length * 5 /8, data.length * 6 /8);
      FindMaxTask task7 = new FindMaxTask(data, data.length * 6 /8, data.length * 7 /8);
      FindMaxTask task8 = new FindMaxTask(data, data.length * 7 /8, data.length);
   
      ExecutorService service = Executors.newFixedThreadPool(2);
      
      Future<Integer> future1 = service.submit(task1); 
      Future<Integer> future2 = service.submit(task2);
      Future<Integer> future3 = service.submit(task3);
      Future<Integer> future4 = service.submit(task4);
      Future<Integer> future5 = service.submit(task5);
      Future<Integer> future6 = service.submit(task6);
      Future<Integer> future7 = service.submit(task7);
      Future<Integer> future8 = service.submit(task8);
      //Future<Integer> future3 = service.submit(task3);
     // Future<Integer> future4 = service.submit(task4);
      return Math.max(future7.get(), future8.get());


   }

   public static void main (String[] args)

   {

      int array[] = new int[1000000];

      for (int i = 0; i < array.length; i++)
         //samples[i] = (int) (Math.random() * 1000 - 1);
    	  array[i]=i;
      int max = Integer.MIN_VALUE;
      long stratTime = System.nanoTime();
      for (int i = 0; i < array.length; i++) {
         if (array[i] > max)
            max = array[i];
      }
      long esTimatedTime1 = System.nanoTime() - stratTime;
      System.out.println("�ִ밪:" + max);
      System.out.println(esTimatedTime1*0.000000001);
      
   }
     

   }
