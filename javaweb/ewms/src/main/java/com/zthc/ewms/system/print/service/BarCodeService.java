package com.zthc.ewms.system.print.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 生成条码（CODE128格式）  */
@Service
public class BarCodeService {

    private static final String IMAGETYPE = "JPEG";

    public void print1D(String keycode,ServletOutputStream stream )
            throws  IOException {

            try {
                Code128Writer writer = new  Code128Writer();
                int width=60;
                int height=20;
                BitMatrix m = writer.encode(keycode, BarcodeFormat.CODE_128, width, height);
                MatrixToImageWriter.writeToStream(m, IMAGETYPE, stream);

            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }

    }

    public void print2D(String keyCode , ServletOutputStream stream)
            throws ServletException, IOException {
            try {
                int size=129;
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(keyCode, BarcodeFormat.QR_CODE, size, size);
                MatrixToImageWriter.writeToStream(m, IMAGETYPE, stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }

    }



}
