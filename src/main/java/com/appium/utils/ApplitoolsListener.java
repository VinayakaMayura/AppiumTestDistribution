package com.appium.utils;

import com.appium.manager.AppiumDriverManager;
import com.applitools.eyes.BatchInfo;

import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import org.testng.*;

public class ApplitoolsListener implements  IInvokedMethodListener, ITestListener {
    private static BatchInfo batch = null;
    private Eyes eyes;
    private static ThreadLocal<Eyes> localEyes_ = new ThreadLocal<>();

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        localEyes_.get().open(AppiumDriverManager.getDriver(),"ATD","Test");
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        localEyes_.get().close();
        localEyes_.get().abortIfNotClosed();
    }

    public static Eyes get() {
        return localEyes_.get();
    }


    @Override
    public void onTestStart(ITestResult iTestResult) {
        if (eyes == null) {
            eyes = new Eyes();
            eyes.setApiKey("YOUR_KEY");
            eyes.setIsDisabled(false);
            eyes.setLogHandler(new StdoutLogHandler(true));
            localEyes_.set(eyes);
        }

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
