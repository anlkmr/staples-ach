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

	@Value("src/main/resources/Sign/staplesKey.asc")
	private String publicKeyPath_Encrypt;
	@Value("src/main/resources/Sign/emaigakeys.asc")
	private String privateKeyPath_Sign;

	@Value("src/main/resources/AnilPGP_Private.asc")
	private String publicKeyPath_Decrypt;
	@Value("src/main/resources/Anil2_Public.asc")
	private String privateKeyPath_UnSign;

	@Value("src/main/resources/achtest1StaplesEncSigned.asc")
	private String inputEncryptedSignedFile;
	@GetMapping(value="/pgpenc")
	public void pgpenc() throws NoSuchProviderException, IOException, PGPException
	{
		String inputFilePath="achtest1StaplesCommercialLLC.ach";
		//pgpEncryption.encrypt(inputFilePath, inputFilePath+".pgp");

		char[] privateKeyPassword = new char[]{'h','a','n','u','m','a','n'};
		String outputEncryptSignedFilePath = "src/main/resources/signedEncrypted/achtest1StaplesCommercialLLC.ach.pgp";
		String outputDecryptUnSignedFilePath = "src/main/resources/achtest1StaplesDeCryptUnSigned.ach";
		//pgpServiceEncryptSign.encryptAndSignData(inputFilePath, publicKeyPath_Encrypt, privateKeyPath_Sign,privateKeyPassword,inputFilePath+".pgp");

		//pgpServiceDecryptUnsign.decryptAndVerifyData(inputEncryptedSignedFile, privateKeyPath_UnSign, publicKeyPath_Decrypt,privateKeyPassword,outputDecryptUnSignedFilePath);

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
