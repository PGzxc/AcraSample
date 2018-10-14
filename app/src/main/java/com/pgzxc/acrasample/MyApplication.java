package com.pgzxc.acrasample;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraDialog;
import org.acra.annotation.AcraHttpSender;
import org.acra.annotation.AcraLimiter;
import org.acra.annotation.AcraMailSender;
import org.acra.annotation.AcraNotification;
import org.acra.annotation.AcraToast;
import org.acra.data.StringFormat;
import org.acra.dialog.BaseCrashReportDialog;
import org.acra.sender.HttpSender;

@AcraCore(buildConfigClass = BuildConfig.class,
        reportFormat = StringFormat.JSON)
@AcraHttpSender(uri = "http://192.168.174.136:5984/acra-myapp/_design/acra-storage/_update/report",
        httpMethod = HttpSender.Method.POST,
        basicAuthLogin = "test",
        basicAuthPassword = "123456")
@AcraDialog(
        resCommentPrompt = R.string.resCommentPrompt,
        resEmailPrompt = R.string.resEmailPrompt,
        resIcon = android.R.drawable.ic_dialog_info,
        resNegativeButtonText = R.string.resNegativeButtonText,
        resPositiveButtonText = R.string.resPositiveButtonText,
        resText = R.string.resText,
        resTitle = R.string.resTitle)
//@AcraToast(resText = R.string.exception)
//@AcraMailSender(mailTo = "834228918@qq.com")
/*@AcraNotification(resChannelName=R.string.ChannelName,
        resText = R.string.resChannelText,
        resTitle=R.string.resChannelText
    )*/
//@AcraLimiter
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
