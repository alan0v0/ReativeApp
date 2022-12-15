package com.alan.reactive.projectreactor;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.context.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

public class MonoTest {
    @Test
    void onNext() {
        Mono mono = Mono.defer(() -> {
                    if (System.currentTimeMillis() < 0) {
                        throw new RuntimeException();
                    }
                    return Mono.just(1);
                }).log().publishOn(Schedulers.newElastic(""))
                .map(num -> {
                    if (System.currentTimeMillis() > 0) {
                        throw new RuntimeException();
                    }
                    return num + 1;
                });
        StepVerifier.create(mono).expectNext(1).verifyComplete();
    }

    @Test
    void callable() {
        Mono.just(1)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(System.out::println);
    }

    @Test
    void context() {
        Mono.deferContextual(ctx -> Mono.just((String) ctx.get("key")))
                .subscribe(new CoreSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(2);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public Context currentContext() {
                        return Context.of("key", "HelloWorld");
                    }
                });
//        StepVerifier.create(mono).expectNext((String)null).verifyComplete();
    }

    @Test
    void executor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            System.out.println();
        });
        executorService.shutdown();
    }
}
