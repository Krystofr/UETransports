package com.krystofr.uetransportz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import im.delight.android.webview.AdvancedWebView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.FoldingCube;

public class WebViewActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mProgressBar = findViewById(R.id.spin_kit);
        FoldingCube foldingCube = new FoldingCube();
        mProgressBar.setIndeterminateDrawable(foldingCube);

        mWebView = findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        mWebView.loadUrl("https://booking.unitedexpress.cm", true);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            Log.d("Test","back pressed");
            mWebView.goBack();
        }else {
            // ...
            super.onBackPressed();
        }

    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        // a new page started loading

        //show progress bar
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(String url) {
        // the new page finished loading

        //set visibility of webview visible
        mWebView.setVisibility(View.VISIBLE);

        //hide progressbar
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPageError(int errorCode, String description, final String failingUrl) {
        // the new page failed to load

        //set visibility of webview gone
        mWebView.setVisibility(View.INVISIBLE);

        //stop progressBar
        mProgressBar.setVisibility(View.GONE);

        //show alert dialog to reload the page
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mWebView.loadUrl(failingUrl, true);
            }
        });
        alertDialog.setTitle("Page Failed");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Page couldn't load, do you want to retry?");
        alertDialog.create().show();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        // some file is available for download
        // either handle the download yourself or use the code below
        if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
            // download successfully handled
        } else {
            // download couldn't be handled because user has disabled download manager app on the device
            // TODO show some notice to the user
        }
    }

    @Override
    public void onExternalPageRequest(String url) {
        // the user tried to open a page from a non-permitted hostname
    }
}
