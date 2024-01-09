import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPExample {
    public static void main(String[] args) {
        String server = "ec2-13-127-174-251.ap-south-1.compute.amazonaws.com";
        int port = 22;
        String username = "sftp_user";
        String password = "dhanam2023";
        String remoteFilePath = "/sftp_user/dhanam/unprocessed/sample.xlsx"; // Adjust the path to the Excel file on the server
        String localFilePath = "/home/dell/Desktop/SFTP-Demo/sample.xlsx"; // Adjust the local path where you want to save the Excel file

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            boolean success = ftpClient.login(username, password);
            if (success) {
                System.out.println("Connected and logged in.");

                // Set transfer mode and enter passive mode (recommended)
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                // ftpClient.enterLocalPassiveMode();
                ftpClient.enterLocalActiveMode();

                // Download the Excel file
                FileOutputStream localFileOutputStream = new FileOutputStream(localFilePath);
                boolean downloaded = ftpClient.retrieveFile(remoteFilePath, localFileOutputStream);
                localFileOutputStream.close(); // Close the output stream

                if (downloaded) {
                    System.out.println("Excel file downloaded successfully.");
                } else {
                    System.out.println("Excel file download failed.");
                }

                // After performing your FTP operations, remember to disconnect.
                ftpClient.logout();
                ftpClient.disconnect();
            } else {
                System.out.println("Login failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
