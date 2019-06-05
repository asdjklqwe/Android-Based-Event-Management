package com.example.arnold.hypercebuproject;

/**
 * Created by Arnold on 2/18/2017.
 */

public class Config {
    public static final String IPAddress = "192.168.254.2"; // 192.168.43.96  192.168.254.2 192.168.8.100 192.168.254.4 192.168.254.5
    //login
    public static final String LOGIN_URL = "http://"+IPAddress+"/HyperScript/Login.php";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    //register
    public static final String REGISTER_URL = "http://"+IPAddress+"/HyperScript/Register.php";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_CPASSWORD = "cpassword";
    //event
    public static final String EVENT_URL = "http://"+IPAddress+"/HyperScript/Event.php";
    public static final String KEY_EVENT = "name";
    public static final String KEY_LOCATION = "location";
    public static final String JSON_ARRAY = "result";
    //recentEvents
    public static final String ALLEVENT_URL = "http://"+IPAddress+"/HyperScript/getAllEvent.php";
    public static final String TAG_EVENTNAME = "name";
    public static final String TAG_LOCATION = "location";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_DELETED = "deleted";
    public static final String TAG_FINISHED = "finished";
    //event info
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_STARTDATE = "startdate";
    public static final String KEY_ENDDATE = "enddate";
    public static final String KEY_STARTTIME = "starttime";
    public static final String KEY_ENDTIME = "endtime";
    public static final String KEY_DELETED = "deleted";
    public static final String KEY_FINISHED = "finished";
    //event image
    public static final String IMAGE_PATH = "http://"+IPAddress+"/hyperWeb/uploads/";
    //ticket
    public static final String TICKET_URL = "http://"+IPAddress+"/HyperScript/Ticket.php";
    public static final String TICKET_DETAILS_URL = "http://"+IPAddress+"/HyperScript/Ticket_Details.php"; //Ticket_Details
    public static final String UPDATE_TICKET_DETAILS_URL = "http://"+IPAddress+"/HyperScript/Update_Purchased_Ticket_Details.php"; //Update_Purchased_Ticket_Details
    public static final String UPDATE_TICKET_QUANTITY = "http://"+IPAddress+"/HyperScript/Update_Ticket_Quantity.php";
    public static final String KEY_TICKET_NO = "ticket_no";
    public static final String KEY_TICKET_TYPE = "type";
    public static final String KEY_TICKET_QTY = "quantity";

    public static final String KEY_QR_CODE = "qr_code";
    public static final String TAG_QR_CODE = "qr_code";

    public static final String CARD_ACCOUNT_URL = "http://"+IPAddress+"/HyperScript/Card_Account.php";
    public static final String UPDATE_CARD_ACCOUNT_URL = "http://"+IPAddress+"/HyperScript/Update_Card_Account.php";
    public static final String KEY_CARD_NO = "Card_No";
    public static final String KEY_AMOUNT = "Card_Amount";
    public static final String TAG_CARD_NO = "Card_No";
    public static final String TAG_AMOUNT = "Card_Amount";

    public static final String INSERT_TICKET_HISTORY_URL = "http://"+IPAddress+"/HyperScript/Insert_TicketHistory.php";
    public static final String GET_TICKET_HISTORY_URL = "http://"+IPAddress+"/HyperScript/getPurchased_Ticket_History.php";
    public static final String KEY_TICKET_EVENT_HISTORY = "ticket_event";
    public static final String KEY_TICKET_EVENT_LOCATION_HISTORY = "ticket_event_location";
    public static final String KEY_TICKET_TYPE_HISTORY = "ticket_type";
    public static final String KEY_TICKET_PRICE_HISTORY = "ticket_price";
    public static final String KEY_TICKET_NO_HISTORY = "ticket_no";
    public static final String KEY_TICKET_ENDDATE = "ticket_enddate";
    public static final String KEY_TICKET_QRCODE_HISTORY = "ticket_qrcode";
    public static final String KEY_TICKET_HYPERCARD_HISTORY= "ticket_hypercard_no";

    public static final String TAG_TICKET_EVENT_HISTORY = "ticket_event";
    public static final String TAG_TICKET_EVENT_LOCATION_HISTORY = "ticket_event_location";
    public static final String TAG_TICKET_TYPE_HISTORY = "ticket_type";
    public static final String TAG_TICKET_PRICE_HISTORY = "ticket_price";
    public static final String TAG_TICKET_NO_HISTORY = "ticket_no";
    public static final String TAG_TICKET_ENDDATE = "ticket_enddate";
    public static final String TAG_TICKET_QRCODE_HISTORY = "ticket_qrcode";
    public static final String TAG_TICKET_HYPERCARD_HISTORY= "ticket_hypercard_no";
    public static final String TAG_TICKET_DATEOFPURCHASED_HISTORY= "ticket_date_of_purchase";

}
