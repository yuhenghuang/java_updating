
class Number {
  public static void main(String[] args) {
    Number obj = new Number();

    Thread t1 = new Thread(new Runnable(){
      @Override
      public void run() {
        while (true) {
          obj.print_odd();
        }
      }
    });
    t1.setName("t1");

    Thread t2 = new Thread(new Runnable(){
      @Override
      public void run() {
        while (true) {
          obj.print_even();
        }
      }
    });
    t2.setName("t2");


    t1.start();
    t2.start();
  }
  int i;

  Number() {
    i = 0;
  }

  private void print(int rem) {
    synchronized (this) {
      if (i % 2 == rem)
        try {
          wait();
        } 
        catch (InterruptedException e) { }

      System.out.println(Thread.currentThread().getName() + " -> " + i++);
      notify();
    }
  }

  public void print_odd() {
    print(0);
  }


  public void print_even() {
    print(1);
  }
}
