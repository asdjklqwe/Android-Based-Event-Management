package com.example.arnold.hypercebuproject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


/**
 * Created by Arnold on 5/20/2017.
 */

public class PurchasedTicket extends AppCompatActivity{

    private ImageView QrImage;
    private String EditTextValue;
    private TextView stv_ename, stv_elocation, stv_price ,stv_ecardno, stv_eticketNo, stv_edateofpurchased;
    public final static int QRcodeWidth = 500 ;
    private Bitmap bitmap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_ticket);

        QrImage = (ImageView)findViewById(R.id.imageView);
        stv_ename = (TextView) findViewById(R.id.tv_ticket_eventName);
        stv_elocation = (TextView) findViewById(R.id.tv_ticket_location);
        stv_price = (TextView) findViewById(R.id.tv_ticket_type_price);
        stv_ecardno = (TextView) findViewById(R.id.tv_ticket_hypercardnumber);
        stv_eticketNo = (TextView) findViewById(R.id.tv_ticket_no_purchased);
        stv_edateofpurchased = (TextView) findViewById(R.id.tv_ticket_dateofpurchased);

        Bundle intent = getIntent().getExtras();
        stv_ename.setText(intent.getString(Config.TAG_TICKET_EVENT_HISTORY));
        stv_elocation.setText(intent.getString(Config.TAG_TICKET_EVENT_LOCATION_HISTORY));
        stv_price.setText(intent.getString(Config.TAG_TICKET_TYPE_HISTORY)+ " Php "+intent.getString(Config.TAG_TICKET_PRICE_HISTORY));
        stv_eticketNo.setText(intent.getString(Config.TAG_TICKET_NO_HISTORY));
        intent.getString(Config.TAG_TICKET_ENDDATE);
        intent.getString(Config.TAG_TICKET_QRCODE_HISTORY);
        stv_ecardno.setText(intent.getString(Config.TAG_TICKET_HYPERCARD_HISTORY));
        stv_edateofpurchased.setText(intent.getString(Config.TAG_TICKET_DATEOFPURCHASED_HISTORY));

        EditTextValue = intent.getString(Config.TAG_TICKET_EVENT_HISTORY)+","+intent.getString(Config.TAG_TICKET_TYPE_HISTORY)+","+ intent.getString(Config.TAG_TICKET_PRICE_HISTORY) +","+intent.getString(Config.TAG_TICKET_NO_HISTORY)+","+intent.getString(Config.TAG_TICKET_QRCODE_HISTORY)+","+intent.getString(Config.TAG_TICKET_ENDDATE)+","+ intent.getString(Config.TAG_TICKET_DATEOFPURCHASED_HISTORY);

        try {
            bitmap = TextToImageEncode(EditTextValue);
            QrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
