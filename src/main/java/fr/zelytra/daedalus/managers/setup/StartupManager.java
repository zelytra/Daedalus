package fr.zelytra.daedalus.managers.setup;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Message;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.bukkit.Bukkit;

public class StartupManager {
  private final String mapURL =
      "https://raw.githubusercontent.com/zelytra/Daedalus/master/resources/DaedalusMap_1.6.zip";
  private final File folder;
  private final File zip;

  public StartupManager() {
    /* Directory storage creation */
    folder = new File(Daedalus.getInstance().getDataFolder().toString());
    if (!folder.exists()) folder.mkdir();

    zip = new File(folder + File.separator + "DaedalusMap.zip");
    if (zip.exists()) {
      log("§aMap already downloaded !");
      log("§6Extracting files please wait...");
      extractZip();
      log("§aMap setup successfully !");
      return;
    }
    try {
      zip.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    log("§6Downloading map please wait...");
    downloadMap();
    log("§6Extracting files please wait...");
    extractZip();
    log("§aMap setup successfully !");
  }

  private void downloadMap() {
    try {
      BufferedInputStream in = new BufferedInputStream(new URL(mapURL).openStream());
      FileOutputStream fileOutputStream = new FileOutputStream(zip);
      int totalByte = getFileSize(new URL(mapURL));
      int byteCount = 0;
      byte dataBuffer[] = new byte[1024];
      int bytesRead;
      while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        fileOutputStream.write(dataBuffer, 0, bytesRead);
        byteCount++;
        printProgress(byteCount, totalByte);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static int getFileSize(URL url) {
    URLConnection conn = null;
    try {
      conn = url.openConnection();
      if (conn instanceof HttpURLConnection) {
        ((HttpURLConnection) conn).setRequestMethod("HEAD");
      }
      conn.getInputStream();
      return conn.getContentLength() / 1024;
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (conn instanceof HttpURLConnection) {
        ((HttpURLConnection) conn).disconnect();
      }
    }
  }

  private void printProgress(int byteCount, int totalByte) {
    StringBuilder progressString = new StringBuilder();
    double progressPercent = ((byteCount * 100.0) / totalByte);
    int starNumber = 30;

    for (int x = 0; x <= ((progressPercent * starNumber) / 100); x++) progressString.append("*");

    if (((progressPercent * starNumber) / 100) != 30)
      for (int x = 0; x <= (30 - ((progressPercent * starNumber) / 100)); x++)
        progressString.append(" ");
    if (progressPercent % 5 == 0)
      Bukkit.getServer()
          .getConsoleSender()
          .sendMessage(
              "§6Progress §8: |§f" + progressString + "§8| [§f" + progressPercent + "%§8]");
  }

  private void extractZip() {
    try {
      File destDir = new File(Bukkit.getServer().getWorldContainer() + File.separator + "daedalus");
      destDir.deleteOnExit();
      destDir.mkdir();
      byte[] buffer = new byte[1024];
      ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
      ZipEntry zipEntry = zis.getNextEntry();
      while (zipEntry != null) {
        File newFile = newFile(destDir, zipEntry);
        if (zipEntry.isDirectory()) {
          if (!newFile.isDirectory() && !newFile.mkdirs()) {
            throw new IOException("Failed to create directory " + newFile);
          }
        } else {
          // fix for Windows-created archives
          File parent = newFile.getParentFile();
          if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("Failed to create directory " + parent);
          }

          // write file content
          FileOutputStream fos = new FileOutputStream(newFile);
          int len;
          while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
          }
          fos.close();
        }
        zipEntry = zis.getNextEntry();
      }
      zis.closeEntry();
      zis.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
    File destFile = new File(destinationDir, zipEntry.getName());

    String destDirPath = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator)) {
      throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
    }

    return destFile;
  }

  private void log(String log) {
    Bukkit.getServer().getConsoleSender().sendMessage(Message.getPlayerPrefixe() + log);
  }
}
