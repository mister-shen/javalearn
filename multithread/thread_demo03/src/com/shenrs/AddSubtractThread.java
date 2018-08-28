package com.shenrs;


class Entitys {
	public Integer sum = 0;
	public volatile boolean flag = false;
	public int curentthread;

	public Entitys() {
	}
}
/**
 * 加1运算
 */
class AddThread implements Runnable {
	Entitys entitys;
	Object obj = new Object();

	public AddThread(Entitys entitys) {
		this.entitys = entitys;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (entitys) {
				// 如果flag为false 增加
				if(entitys.flag){
					try {
						entitys.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 锁住单个线程
				entitys.sum = entitys.sum + 1;
				System.out.println(Thread.currentThread().getName()
						+ "正在进行加法运算，sum：" + entitys.sum);
				if (entitys.sum >= 10) {
					entitys.flag = true;
				}
				entitys.notify();
			}
		}
	}
}

/**
 * 减1运算
 */
class SubtractThread implements Runnable {
	Entitys entitys;

	public SubtractThread(Entitys entitys) {
		this.entitys = entitys;
	}

	@Override
	public void run() {
		while (true) {
			//锁住全局资源
			synchronized (entitys) {
				if (!entitys.flag) {
					try {
						entitys.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				entitys.sum = entitys.sum - 1;
				System.out.println(Thread.currentThread().getName()
						+ "正在进行减法运算，sum：" + entitys.sum);

				if (entitys.sum <= 0) {
					entitys.flag = false;
				}
				entitys.notify();
			}
		}
	}
}
/**
 * @author shenrs
 * @Description 加减练习线程
 * @create 2018-08-28 10:45
 **/
public class AddSubtractThread {

	public static void main(String[] args) {
		Entitys entitys = new Entitys();
		AddThread add = new AddThread(entitys);
		SubtractThread subtract = new SubtractThread(entitys);
		Thread addT1 = new Thread(add, "add_1");
		Thread addT2 = new Thread(add, "add_2");
		Thread subtractT1 = new Thread(subtract, "subtract_1");
		Thread subtractT2 = new Thread(subtract, "subtract_2");
		addT1.start();
//		addT2.start();
		subtractT1.start();
//		subtractT2.start();
	}

}
