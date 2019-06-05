package com.example.arnold.hyperscanner;

/**
 * Created by Arnold on 5/17/2017.
 */

public class Config {
    public static final String IPAddress = "192.168.254.2"; // 192.168.43.96  192.168.254.2 192.168.8.100 192.168.254.4 192.168.254.5
    //ticket
    public static final String UPDATE_STATUS_TICKET_DETAILS_URL = "http://"+IPAddress+"/HyperScript/Update_Status_TD.php"; //Update_Status_TD
    public static final String USED_TICKET_DETAILS_URL = "http://"+IPAddress+"/HyperScript/Used_Ticket_Details.php";
    public static final String KEY_TICKET_NO = "ticket_no";
    public static final String KEY_QR_CODE = "qr_code";
    public static final String JSON_ARRAY = "result";
    public static final String TAG_STATUS = "status";
}
