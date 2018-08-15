/**
 * @author dercilima
 * <p>
 * Classe utilizada para compactar e descompactar arquivos
 */

package br.com.dercilima.zipfileslib;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

    // Arquivo Zip
    private File zipFile;

    // Destino onde será Extraído/Salvo o arquivo .zip
    private File sourceFolder;

    // Lista com os arquivo para serem zipados
    private List<File> listFiles;


    public ZipFiles(File zipFile, File sourceFolder) {
        this.zipFile = zipFile;
        this.sourceFolder = sourceFolder;
    }

    public ZipFiles(List<File> listFiles, File zipFile) {
        this.listFiles = listFiles;
        this.zipFile = zipFile;
    }

    public void zip() throws IOException {

        byte[] buffer = new byte[1024];

        final FileOutputStream fos = new FileOutputStream(zipFile);
        final ZipOutputStream zos = new ZipOutputStream(fos);

        for (File file : listFiles) {

            final ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);

            final FileInputStream in = new FileInputStream(file);

            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
        }

        zos.closeEntry();
        zos.close();

    }

    public void unzip() throws IOException {

        final FileInputStream fin = new FileInputStream(zipFile);
        final ZipInputStream zin = new ZipInputStream(fin);
        ZipEntry ze;

        checkIntegrity();
        zin.available();

        while ((ze = zin.getNextEntry()) != null) {

            if (ze.isDirectory()) {
                dirChecker(ze.getName());

            } else {

                final FileOutputStream fout = new FileOutputStream(new File(sourceFolder, ze.getName()));

                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }

                zin.closeEntry();
                fout.close();
            }

        }

        zin.close();
    }


    // Verificando se é um arquivo ZIP válido, pois caso não seja será gerada uma exceção
    public void checkIntegrity() throws IOException {
        new ZipFile(zipFile, ZipFile.OPEN_READ);
    }

    private void dirChecker(String dir) {

        final File directory = new File(sourceFolder + dir);
        if (!directory.isDirectory()) {
            if (directory.mkdirs()) {
                Log.d(ZipFiles.class.getSimpleName(), "dirChecker: Diretório \"" + directory.getPath() + "\" criado!");
            }
        }

    }

}
