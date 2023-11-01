package com.emagia.ach.pgp;

import java.io.IOException;
import java.security.NoSuchProviderException;

import org.bouncycastle.openpgp.PGPException;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping(value="/pgpenc")
	public void pgpenc() throws NoSuchProviderException, IOException, PGPException
	{
		String inputFile="src/main/resources/achtest1Staples & CommercialLLC.ach";
		pgpEncryption.encrypt(inputFile, "src/main/resources/achtest1StaplesEnc.pgp");
	
		

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
