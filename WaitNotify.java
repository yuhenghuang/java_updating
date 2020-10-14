class PrintThread implements Runnable {
  boolean is_even;
  Number num;

  PrintThread(Number n, boolean is_even) {
    num = n;
    this.is_even = is_even;
  }

  @Override
  public void run() {
    while (num.i<num.max) 
      num.print(is_even);
  }
}

class Number {
  public static void main(String[] args) {
    Number obj = new Number(20);

    Thread t1 = new Thread(new PrintThread(obj, false));
    t1.setName("t1");

    Thread t2 = new Thread(new PrintThread(obj, true));
    t2.setName("t2");

    t1.start();
    t2.start();
  }

  int i, max;

  Number(int m) {
    i = 1;
    max = m;
  }

  public void print(boolean is_even) {
    synchronized (this) {
      if (i % 2 != (is_even ? 0 : 1))
        try {
          wait();
        } 
        catch (InterruptedException e) { }

      System.out.println(Thread.currentThread().getName() + " -> " + i++);
      notify();
    }
  }
}
