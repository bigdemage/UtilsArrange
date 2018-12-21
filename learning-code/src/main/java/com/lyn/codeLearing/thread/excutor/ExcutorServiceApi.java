package com.lyn.codeLearing.thread.excutor;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * 练习目的：了解ExecutorService的两种批量任务执行方式：
 * invokeAny：最先完成任务。
 * 批量执行任务，当任意一个任务最先完成时，返回这个任务的执行结果。
 * 返回值是一个执行结果，类型为泛型的实际类型(如任务类型为Callable<Integer>，则返回值为Int eger类型)。
 * invokeAll：全部完成任务。
 * 批量执行任务，当所有的任务都完成之后，返回这些任务的Future对象。
 * 返回值是一个Future集合。
 *
 * 练习内容：
 * 定义一个任务(Callable)集合，其中的每个任务实现随机取数。
 * 定义一个结果(Future)集合，与任务(Callable)集合相对应。
 * 实现1：批量执行任务集合，当所有的任务执行完毕之后，输出所有的执行结果。
 * 实现2：批量执行任务集合，输出第一个执行完毕的任务的执行结果。
 */
public class ExcutorServiceApi {
    private static final int SIZE =10;

    private static final ExecutorService excutor = Executors.newCachedThreadPool();
    private static CountDownLatch countDownLatch=new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //列表
        List<Callable<Integer>> intList =new ArrayList<>(SIZE);
        for(int i=1;i<=SIZE;i++){
            Callable callable=new Callable() {
                @Override
                public Object call() throws Exception {
                    return RandomUtils.nextInt(100,200);
                }
            };
            intList.add(callable);
        }
        invokeAllCase(intList);//invokeAll方法
        invokeAnyCase(intList);//invokeAny方法
        excutor.shutdown();
    }

    private static void invokeAnyCase(List<Callable<Integer>> intList) throws ExecutionException, InterruptedException {
        System.out.println("invokeAnyCase:::"+excutor.invokeAny(intList));
        countDownLatch.countDown();
    }

    private static void invokeAllCase(List<Callable<Integer>> intList) throws InterruptedException {

        List<Future<Integer>> futures=new ArrayList<>(SIZE);
        futures= excutor.invokeAll(intList);
        futures.forEach(f->{
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.countDown();
    }
}
