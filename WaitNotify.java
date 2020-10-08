class PrintThread implements Runnable {
  int rem;
  Number num;

  PrintThread(Number n, int r) {
    num = n;
    rem = r;
  }

  @Override
  public void run() {
    while (num.i<num.max) {
      num.print(rem);
    }
  }
}

class Number {
  public static void main(String[] args) {
    Number obj = new Number(200);

    Thread t1 = new Thread(new PrintThread(obj, 1));
    t1.setName("t1");

    Thread t2 = new Thread(new PrintThread(obj, 0));
    t2.setName("t2");


    t1.start();
    t2.start();
  }

  int i, max;

  Number(int m) {
    i = 0;
    max = m;
  }

  public void print(int rem) {
    synchronized (this) {
      if (i % 2 != rem)
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
