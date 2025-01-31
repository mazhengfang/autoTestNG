package com;


import com.report.report;
import org.testng.ITestContext;
import xProject.testResult.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestListener extends TestListenerAdapter {
    private int m_count = 0;
    private int index = 0;
    report iReport = new report();


    @Override
    public void onStart(ITestContext iTestContext){
//         super.onStart(iTestContext);
//         log(String.format("-------------------Test Start-------------------------"));
        log.info(String.format("-------------------%s Test Start-------------------------",iTestContext.getName()));
        log("-----pass");
    }

    @Override
    public void onTestStart(ITestResult tr) {
        Assert.flag = true;
        Assert.errors.clear();
//        super.onTestStart(tr);
        log.info(String.format("-------------------%s.%s Test Start-------------------------",tr.getName(),tr.getStatus()));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        this.handleAssertion(tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        this.handleAssertion(tr);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        this.handleAssertion(tr);
//        log("Pass");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

    private void handleAssertion(ITestResult tr) {
        if (!Assert.flag) {
            Throwable throwable = tr.getThrowable();
            if (throwable == null) {
                throwable = new Throwable();
            }
            StackTraceElement[] traces = throwable.getStackTrace();
            StackTraceElement[] alltrace = new StackTraceElement[0];
            for (Error e : Assert.errors) {
                StackTraceElement[] errorTraces = e.getStackTrace();
                StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
                StackTraceElement[] message = new StackTraceElement[]{new StackTraceElement(tr.getMethod().getMethodName(), ("message: " + e.getMessage() + " in method : "), tr.getTestClass().getRealClass().getSimpleName(), index)};
                index = 0;
                alltrace = this.merge(alltrace, message);
                alltrace = this.merge(alltrace, et);
            }
            if (traces != null) {
                traces = this.getKeyStackTrace(tr, traces);
                alltrace = this.merge(alltrace, traces);
            }
            throwable.setStackTrace(alltrace);
            tr.setThrowable(throwable);
            Assert.flag = true;
            Assert.errors.clear();
            tr.setStatus(ITestResult.FAILURE);

        }
    }

    private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
        List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
        for(StackTraceElement stackTraceElement : stackTraceElements){
            if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){
                ets.add(stackTraceElement);
                index = stackTraceElement.getLineNumber();
            }
        }
        StackTraceElement[] et = new StackTraceElement[ets.size()];
        for(int i = 0; i< et.length; i++){
            et[i] =ets.get(i);
        }
        return et;
    }

    private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){
        StackTraceElement[] ste = new StackTraceElement[traces1.length + traces2.length ];
        for(int i = 0; i < traces1.length; i++){
            ste[i] = traces1[i];
        }
        for(int i = 0; i < traces2.length; i++){
            ste[traces1.length + i] = traces2[i];
        }
        return ste;
    }
}

