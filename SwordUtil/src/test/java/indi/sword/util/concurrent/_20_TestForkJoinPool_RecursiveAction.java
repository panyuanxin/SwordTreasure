package indi.sword.util.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by rd_jianbin_lin on 2017/9/5.
 * 1. 什么是Fork/Join框架
 * Fork/Join框架是Java7提供了的一个用于并行执行任务的框架， 是一个把大任务分割成若干个小任务，
 * 最终汇总每个小任务结果后得到大任务结果的框架。
 *
 * 我们再通过Fork和Join这两个单词来理解下Fork/Join框架，
 * Fork就是把一个大任务切分为若干子任务并行的执行，
 * Join就是合并这些子任务的执行结果，最后得到这个大任务的结果。
 * 比如计算1+2+。。＋10000，可以分割成10个子任务，每个子任务分别对1000个数进行求和，最终汇总这10个子任务的结果。
 *
 * 2. 工作窃取算法
 * 工作窃取（work-stealing）算法是指某个线程从其他队列里窃取任务来执行。
 *
 * 那么为什么需要使用工作窃取算法呢？
 * 假如我们需要做一个比较大的任务，我们可以把这个任务分割为若干互不依赖的子任务，
 * 为了减少线程间的竞争，于是把这些子任务分别放到不同的队列里，
 * 并为每个队列创建一个单独的线程来执行队列里的任务，线程和队列一一对应，
 * 比如A线程负责处理A队列里的任务。但是有的线程会先把自己队列里的任务干完，
 * 而其他线程对应的队列里还有任务等待处理。干完活的线程与其等着，不如去帮其他线程干活，
 * 于是它就去其他线程的队列里窃取一个任务来执行。而在这时它们会访问同一个队列，
 * 所以为了减少窃取任务线程和被窃取任务线程之间的竞争，通常会使用双端队列，被窃取任务线程永远从双端队列的头部拿任务执行，
 * 而窃取任务的线程永远从双端队列的尾部拿任务执行。
 *
 * 工作窃取算法的优点是充分利用线程进行并行计算，并减少了线程间的竞争，
 * 其缺点是在某些情况下还是存在竞争，比如双端队列里只有一个任务时。
 * 并且消耗了更多的系统资源，比如创建多个线程和多个双端队列。
 *
 * 3. Fork/Join框架的介绍
 * 第一步分割任务。首先我们需要有一个fork类来把大任务分割成子任务，有可能子任务还是很大，所以还需要不停的分割，直到分割出的子任务足够小。
 * 第二步执行任务并合并结果。分割的子任务分别放在双端队列里，然后几个启动线程分别从双端队列里获取任务执行。
 * 子任务执行完的结果都统一放在一个队列里，启动一个线程从队列里拿数据，然后合并这些数据。
 *
 * Fork/Join使用两个类来完成以上两件事情：
 * ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。
 * 它提供在任务中执行fork()和join()操作的机制，通常情况下我们不需要直接继承ForkJoinTask类，
 * 而只需要继承它的子类，Fork/Join框架提供了以下两个子类：
 * RecursiveAction：用于没有返回结果的任务。
 * RecursiveTask ：用于有返回结果的任务。
 *
 * ForkJoinPool ：ForkJoinTask需要通过ForkJoinPool来执行，
 * 任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。
 * 当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务。
 */
public class _20_TestForkJoinPool_RecursiveAction {
    public static void main(String[] args) throws Exception{
        Instant start = Instant.now();
        
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoin_RecursiveAction(0L,50000l)); // submit execute invoke 我觉得都一样
        pool.awaitTermination(5, TimeUnit.SECONDS);  // 任务全部完成之后呢，等待2秒再往下跑
        pool.shutdown();
        
        Instant end = Instant.now();

        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        System.out.println("耗费时间为：" + Duration.between(start,end).toMillis());


    }
}

class ForkJoin_RecursiveAction extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -259195479995561737L;
	
	private long start;
	private long end;
	
	private static final long THURSHOLD = 10L;  //临界值
	
	public ForkJoin_RecursiveAction(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		long length = end - start;
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		if(length <= THURSHOLD){
			for (long i = start; i <= end; i++) {
				System.out.println(Thread.currentThread().getName()  + ",i -> " +  i);
			}
		}else{
			long middle = (start + end) / 2;
			
			ForkJoin_RecursiveAction left = new ForkJoin_RecursiveAction(start, middle); 
			left.fork(); //进行拆分，同时压入线程队列
			
			ForkJoin_RecursiveAction right = new ForkJoin_RecursiveAction(middle+1, end);
			right.fork(); //
			
			left.join();
			right.join();
		}
	}
	
}