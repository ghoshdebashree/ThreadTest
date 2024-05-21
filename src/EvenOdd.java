public class EvenOdd implements Runnable{

    static int count = 1;
    Object object ;
    private EvenOdd(Object o){
        this.object = o;
    }
    @Override
    public void run() {
        while (count <= 15) {
            if (count % 2 == 0 && Thread.currentThread().getName().equals("Even")) {
                synchronized (object) {
                    System.out.println("Thread Name : " + Thread.currentThread().getName() + "value : " + count);
                    count++;
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (count % 2 != 0 && Thread.currentThread().getName().equals("Odd")) {
                synchronized (object) {
                    System.out.println("Thread Name : " + Thread.currentThread().getName() + "value : " + count);
                    count++;
                    object.notify();
                }
            }
        }
    }
        public static void main(String[] args){
            Object lock = new Object();
            Runnable r1 = new EvenOdd(lock);
            Runnable r2 = new EvenOdd(lock);
            new Thread(r1,"Even").start();
            new Thread(r2,"Odd").start();

        }


}
