package com.emagia.ach.pgp;

import java.io.IOException;
import java.security.NoSuchProviderException;

import org.bouncycastle.openpgp.PGPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("PGPEncryption")
@RequestMapping("/v2")
public class PgpEncryptionController {
	
	@Autowired
	PgpEncryption pgpEncryption;
	@Autowired
	PgpServiceEncryptSign pgpServiceEncryptSign;
	@Autowired
	PgpServiceDecryptUnsign pgpServiceDecryptUnsign;

	@Value("src/main/resources/AnilPGP_Public.asc")
	private String publicKeyPath;
	@Value("src/main/resources/AnilPGP_Private.asc")
	private String privateKeyPath;
	@Value("src/main/resources/achtest1StaplesEncSigned.asc")
	private String inputEncryptedSignedFile;
	@GetMapping(value="/pgpenc")
	public void pgpenc() throws NoSuchProviderException, IOException, PGPException
	{
		String inputFile="src/main/resources/achtest1Staples & CommercialLLC.ach";
		//pgpEncryption.encrypt(inputFile, "src/main/resources/achtest1StaplesEnc.pgp");

		char[] privateKeyPassword = new char[]{'h','a','n','u','m','a','n'};
		String outputEncryptSignedFilePath = "src/main/resources/achtest1StaplesEncSigned.asc";
		String outputDecryptUnSignedFilePath = "src/main/resources/achtest1StaplesDeCryptUnSigned.asc";
		pgpServiceEncryptSign.encryptAndSignData("hi",publicKeyPath,privateKeyPath,privateKeyPassword,outputEncryptSignedFilePath);

		pgpServiceDecryptUnsign.decryptAndVerifyData(inputEncryptedSignedFile,publicKeyPath,privateKeyPath,privateKeyPassword,outputDecryptUnSignedFilePath);

		 }
		
		
		
		
	
	
	
	@PostMapping(value = "/rest/upload", consumes = {
		      "multipart/form-data"
	   })
	public void fileUpload(@RequestParam("file") MultipartFile file) throws NoSuchProviderException, IOException, PGPException
	{
	String fileName=file.getOriginalFilename();
	System.out.println("fileName :"+fileName);

	}

}
