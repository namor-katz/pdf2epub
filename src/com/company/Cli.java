package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Cli {

    private static byte[] data = new byte[4];  //this magic bytes, this empty structure.

    private static String fpath = "/tmp/java_exceptions_structure.png";

    public static FileInputStream openFile(String fpath) throws FileNotFoundException {
            FileInputStream fs = new FileInputStream(fpath);
            return fs;
    }

    private static void fileSignature(InputStream is) throws IOException, NullPointerException {
        is.read(data, 0, 4);    //меняет внутри себя. состояние ресурса.
    }

    public static String getFileType(byte[] fileData) {
        String type = "undefined";

        if(Byte.toUnsignedInt(fileData[0]) == 0x89 && Byte.toUnsignedInt(fileData[1]) == 0x50)
            type = "png";
        else if(Byte.toUnsignedInt(fileData[0]) == 0xFF && Byte.toUnsignedInt(fileData[1]) == 0xD8)
            type = "jpg";
        else if(Byte.toUnsignedInt(fileData[0]) == 0x25 && Byte.toUnsignedInt(fileData[1]) == 0x50)
            type = "pdf";

        return type;
    }


    public static void main(String[] args) throws IOException {
        FileInputStream fs0 = openFile(fpath);
        fileSignature(fs0);
        String fileType = getFileType(data);
        System.out.println("тип файла " + fileType);
    }

    Cli() {};

    Cli(String fpath) {
        this.fpath = fpath;
    }
}

