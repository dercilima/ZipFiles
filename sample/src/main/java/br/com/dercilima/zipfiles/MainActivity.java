package br.com.dercilima.zipfiles;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;

import br.com.dercilima.zipfileslib.ZipFiles;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.log_zip_text)
    TextView logZipText;

    private File appDirectoryFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        appDirectoryFile = new File(Environment.getExternalStorageDirectory(), getString(R.string.app_name));
    }

    @OnClick(R.id.zip_button)
    void zip() {

        try {

            logZipText.setText(null);

            checkDirectory(appDirectoryFile);

            final File txtToZip = new File(appDirectoryFile, "config.txt");

            if (!txtToZip.exists()) {
                writeToFile(txtToZip, "Hello World do Zip :D");
            }

            // Diretório de saída
            final File outputDirectory = new File(appDirectoryFile, "DataZipFiles.zip");

            logZipText.append("\nStart zip directory \"" + txtToZip.getAbsolutePath() + "\" to \"" + outputDirectory.getAbsolutePath() + "\" ...");

            new ZipFiles(Collections.singletonList(txtToZip), outputDirectory).zip();

            logZipText.append("\nZip success!");

        } catch (IOException e) {
            e.printStackTrace();
            logZipText.append("\nError: " + e);
        }

    }


    private void writeToFile(File file, String data) throws IOException {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
        outputStreamWriter.write(data);
        outputStreamWriter.close();
    }


    @OnClick(R.id.unzip_button)
    void unzip() {

        try {

            logZipText.setText(null);

            checkDirectory(appDirectoryFile);

            // Arquivo zipado
            final File zipFile = new File(appDirectoryFile, "DataZipFiles.zip");

            // Diretório de saída
            final File outputDir = new File(appDirectoryFile, "extracted");

            checkDirectory(outputDir);

            logZipText.append("\nUnziping ...!");

            new ZipFiles(zipFile, outputDir).unzip();

            logZipText.append("\nZip success!");

        } catch (IOException e) {
            e.printStackTrace();
            logZipText.append("\nError: " + e);
        }

    }

    private void checkDirectory(File file) {
        logZipText.append("\nCheck directory \"" + file + "\" exists");
        if (!file.exists()) {
            if (file.mkdirs()) {
                logZipText.append("\nDirectory \"" + file + "\" created!");
            }
        }
    }

}
