package tn.isetr_mpdam.asynctasksample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Starting the AsyncTask
         */
        new ExampleAsync(this).execute();
    }

    /**
     * AsyncTask Class Implemntation
     */
    private static class ExampleAsync extends AsyncTask<Integer, Integer, String> {

        /**
         * Creating the Context and ProgressDialog istances
         */
        ProgressDialog progress;
        Context context;

        /**
         * @param c -> Context, use "this"
         */
        public ExampleAsync(Context c) {
            context = c;
            progress = new ProgressDialog(c);
            progress.setMax(100);
            progress.setCancelable(false);
            progress.setMessage("Please wait ...");
            progress.setTitle("Loading");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }

        /**
         * This procedure is called before the execution of the AsyncTask
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }

        /**
         * This procedure is called when the AsyncTask is started
         */
        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * When we must show progress, publishProgress can be used for this task.
                 */
                publishProgress(i);
            }
            return "It's done.";
        }

        /**
         * This procedure is called after the execution of the AsyncTask
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.dismiss();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage(s);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        /**
         * The Logic behind publishProgress call.
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
        }
    }

}