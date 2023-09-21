package com.emagia.ach.service;

import com.afrunt.jach.document.ACHDocument;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
public interface Achfileservice {
    ACHDocument inputStreamToACHDoc(InputStream inputStream);
    String achDocToObj(ACHDocument document);
    //OutputStream achDocToOutputStream(ACHDocument document);
    OutputStream achDocToOutputStream(ACHDocument document, Charset charset);

}
