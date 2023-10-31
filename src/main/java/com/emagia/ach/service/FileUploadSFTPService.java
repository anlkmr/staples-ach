package com.emagia.ach.service;

import java.io.File;
import java.io.InputStream;


import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


@Service
@Log4j2
public class FileUploadSFTPService {

    public static final String FILE_PARAMETER_NAME = "file";
    public static final String FILE_UPLOAD_URL = "https://gateway.emagia.com/camt053/convert?format=csv";
    private final RestTemplate restTemplate;

    public FileUploadSFTPService() {
        restTemplate = new RestTemplate();
    }


    private File getFile(String fileName) {
        File file = new File("D:\\Ravali\\JPMPOD\\" + fileName);
        // Path file = Files.createTempFile("test", ".txt");
        // log.info("file is created with name {}"+ file.getFileName());
        return file;
    }


    public void uplodaToEmagiaSftp(InputStream inputStream, String fileNameToCreate) {

        log.info("Service to upload unzip file from local machine to emagia sft ");
        /** Emagia sftp Connection details **/

        String SFTPHOSTLoc = "sftp.emagia.com";
        int SFTPPORTLoc = 22;
        String SFTPUSERLoc = "emagia-sftp";
        String sftpPasswordLoc = "ProdEmag$911";
        JSch jSchLoc = new JSch();
        Session sessionLoc = null;
        Channel channelLoc = null;
        ChannelSftp channelSftpLoc = null;
        try {

            sessionLoc = jSchLoc.getSession(SFTPUSERLoc, SFTPHOSTLoc, SFTPPORTLoc);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            //config.put("UseDNS", "no");
            sessionLoc.setConfig(config);
            sessionLoc.setPassword(sftpPasswordLoc);

            log.info("Remote session created.");

            sessionLoc.connect();
            channelLoc = sessionLoc.openChannel("sftp");
            channelLoc.connect();

            channelSftpLoc = (ChannelSftp) channelLoc; //channelSftpRem.connect();
            /** create directry if not exists **/
            try {
                channelSftpLoc.cd("\\Anil\\");
            } catch (SftpException e) {
                // No such folder yet:
                channelSftpLoc.mkdir("\\Anil");
                channelSftpLoc.cd("\\Anil\\");
            }
            log.info("SFTP channelRem connected...." + channelLoc.isConnected());
            channelSftpLoc.put(inputStream,
                    "\\EmagiaAch\\" + fileNameToCreate.replaceAll(" ", ""));
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (channelSftpLoc != null) {
            channelSftpLoc.disconnect();
            channelSftpLoc.exit();

        }
        if (channelLoc != null)
            channelLoc.disconnect();
        if (sessionLoc != null)
            sessionLoc.disconnect();

    }

}
