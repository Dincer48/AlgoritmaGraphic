package com.example.dincerkizildere.algoritmagrafik;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Helpers {

    public static void showAbout(AppCompatActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog_about");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        new AboutDialog().show(ft, "dialog_about");
    }

    public static class AboutDialog extends DialogFragment {

      //  String urlgooglelus = "https://plus.google.com/u/0/+NamanDwivedi14";
        String urltwitter = "https://twitter.com/Ultra_Aslan05";
        String urlgithub = "https://github.com/dincer48";
        String urlsource = "https://github.com/naman14/AlgorithmGraphic";

        public AboutDialog() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout aboutBodyView = (LinearLayout) layoutInflater.inflate(R.layout.layout_about_dialog, null);

            TextView appversion = (TextView) aboutBodyView.findViewById(R.id.app_version_name);

            TextView googleplus = (TextView) aboutBodyView.findViewById(R.id.googleplus);
            TextView twitter = (TextView) aboutBodyView.findViewById(R.id.twitter);
            TextView github = (TextView) aboutBodyView.findViewById(R.id.github);
            TextView source = (TextView) aboutBodyView.findViewById(R.id.source);

            TextView dismiss = (TextView) aboutBodyView.findViewById(R.id.dismiss_dialog);
            dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            googleplus.setPaintFlags(googleplus.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            twitter.setPaintFlags(twitter.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            github.setPaintFlags(github.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

         /*   googleplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(urlgooglelus));
                    startActivity(i);
                }

            });*/
            twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(urltwitter));
                    startActivity(i);
                }

            });
            github.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(urlgithub));
                    startActivity(i);
                }

            });
            source.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(urlsource));
                    startActivity(i);
                }
            });

            try {
                PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                String version = pInfo.versionName;
                int versionCode = pInfo.versionCode;
                appversion.setText("Algorithm Visualizer " + version);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            return new AlertDialog.Builder(getActivity())
                    .setView(aboutBodyView)
                    .create();
        }

    }

}