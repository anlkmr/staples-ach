package com.emagia.ach;

import com.afrunt.jach.ACH;
import com.afrunt.jach.document.ACHDocument;
import com.emagia.ach.service.Achfileservice;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Optional;

public class AchfileserviceImpl implements Achfileservice {
    @Override
    public ACHDocument inputStreamToACHDoc(InputStream inputStream) {
        ACH ach = new ACH();
        ACHDocument achDocument = ach.read(inputStream);
        return achDocument;
    }

    @Override
    public String achDocToObj(ACHDocument document) {
        ACH ach = new ACH();
        String out = ach.write(document);
        return out;
    }



    @Override
    public OutputStream achDocToOutputStream(ACHDocument document, Charset charset) {
        return null;
    }
}
