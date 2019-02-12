package tech.fraction.webapp.SqliteDatabase;

public class DbConstants {

    /*===================================Country Table Field=======================================*/
    public static final String TABLE_COUNTRY_NAME = "COUNTRIES";

    public static final String COLUMN_COUNTRY_ID = "id";
    public static final String COLUMN_COUNTRY_NAME = "name";
    public static final String COLUMN_COUNTRY_CODE = "code";
    public static final String COLUMN_COUNTRY_MOBILECODE = "mobilecode";

    public static final String CREATE_TABLE_COUNTRY = "create table " + TABLE_COUNTRY_NAME +
            "(" + COLUMN_COUNTRY_ID + " INTEGER , "
            + COLUMN_COUNTRY_NAME + " TEXT NOT NULL, "
            + COLUMN_COUNTRY_CODE + " TEXT NOT NULL, "
            + COLUMN_COUNTRY_MOBILECODE + " TEXT NOT NULL);";

    /*===================================STATES Table Field=======================================*/
    public static final String TABLE_STATE_NAME = "STATES";

    // Table columns
    public static final String COLUMN_STATE_ID = "id";
    public static final String COLUMN_STATE_CODE = "code";
    public static final String COLUMN_STATE_NAME = "name";
    public static final String COLUMN_STATE_COUNTRYID = "countryId";
    public static final String COLUMN_STATE_COUNTRYNAME = "countryName";

    public static final String CREATE_TABLE_STATE = "create table " + TABLE_STATE_NAME +
            "(" + COLUMN_STATE_ID + " INTEGER , "
            + COLUMN_STATE_NAME + " TEXT NOT NULL, "
            + COLUMN_STATE_CODE + " TEXT NOT NULL, "
            + COLUMN_STATE_COUNTRYID + " INTEGER, "
            + COLUMN_STATE_COUNTRYNAME + " TEXT NOT NULL);";
    /*===================================CITY Table Field=======================================*/

    public static final String TABLE_CITY_NAME = "CITY";

    public static final String COLUMN_CITY_ID = "id";
    public static final String COLUMN_CITY_NAME = "name";
    public static final String COLUMN_CITY_STATE_ID = "stateId";
    public static final String COLUMN_CITY_STATE_NAME = "stateName";
    public static final String COLUMN_CITY_COUNTRY_ID = "countryId";
    public static final String COLUMN_CITY_COUNTRY_NAME = "countryName";

    public static final String CREATE_TABLE_CITY = "create table " + TABLE_CITY_NAME +
            "(" + COLUMN_CITY_ID + " INTEGER , "
            + COLUMN_CITY_NAME + " TEXT NOT NULL, "
            + COLUMN_CITY_STATE_ID + " INTEGER, "
            + COLUMN_CITY_STATE_NAME + " TEXT NOT NULL, "
            + COLUMN_CITY_COUNTRY_ID + " INTEGER, "
            + COLUMN_CITY_COUNTRY_NAME + " TEXT NOT NULL);";


    /*===================================CHAMBER Table Field=======================================*/
    public static final String TABLE_CHAMBER_NAME = "CHAMBER";

    public static final String COLUMN_CHAMBER_ID = "id";
    public static final String COLUMN_CHAMBER_NAME = "name";
    public static final String COLUMN_CHAMBER_STATUS = "status";

    public static final String CREATE_TABLE_CHAMBER = "create table " + TABLE_CHAMBER_NAME +
            "(" + COLUMN_CHAMBER_ID + " INTEGER , "
            + COLUMN_CHAMBER_NAME + " TEXT NOT NULL, "
            + COLUMN_CHAMBER_STATUS + " TEXT NOT NULL);";

    /*===================================ITEMS Table Field=======================================*/

    public static final String TABLE_ITEMS_NAME = "ITEMS";

    public static final String COLUMN_ITEMS_ID = "id";
    public static final String COLUMN_ITEMS_NAME = "name";
    public static final String COLUMN_ITEMS_BILLING_TYPE = "billingType";
    public static final String COLUMN_ITEMS_STATUS = "status";

    public static final String CREATE_TABLE_ITEMS = "create table " + TABLE_ITEMS_NAME +
            "(" + COLUMN_ITEMS_ID + " INTEGER , "
            + COLUMN_ITEMS_NAME + " TEXT NOT NULL, "
            + COLUMN_ITEMS_BILLING_TYPE + " TEXT NOT NULL, "
            + COLUMN_ITEMS_STATUS + " TEXT NOT NULL);";

    /*===================================UNITS Table Field=======================================*/
    public static final String TABLE_UNITS_NAME = "UNITS";

    public static final String COLUMN_UNITS_ID = "id";
    public static final String COLUMN_UNITS_NAME = "name";
    public static final String COLUMN_UNITS_WEIGHT = "weight";
    public static final String COLUMN_UNITS_WEIGHT_UNIT = "weightUnit";

    public static final String CREATE_TABLE_UNITS = "create table " + TABLE_UNITS_NAME +
            "(" + COLUMN_UNITS_ID + " INTEGER , "
            + COLUMN_UNITS_NAME + " TEXT NOT NULL, "
            + COLUMN_UNITS_WEIGHT + " TEXT NOT NULL, "
            + COLUMN_UNITS_WEIGHT_UNIT + " TEXT NOT NULL);";

    /*===================================RACKS Table Field=======================================*/
    public static final String TABLE_RACKS_NAME = "RACKS";

    public static final String COLUMN_RACKS_ID = "id";
    public static final String COLUMN_RACKS_NAME = "rackId";
    public static final String COLUMN_RACKS_FLOOR_ID = "floorId";
    public static final String COLUMN_RACKS_CHAMBER_ID = "chamberId";
    public static final String COLUMN_RACKS_STATUS = "staus";

    public static final String CREATE_TABLE_RACKS = "create table " + TABLE_RACKS_NAME +
            "(" + COLUMN_UNITS_ID + " INTEGER , "
            + COLUMN_RACKS_NAME + " TEXT NOT NULL, "
            + COLUMN_RACKS_FLOOR_ID + " INTEGER, "
            + COLUMN_RACKS_CHAMBER_ID + " INTEGER, "
            + COLUMN_RACKS_STATUS + " TEXT NOT NULL);";
    /*===================================RENT Table Field=======================================*/
    public static final String TABLE_RENT_NAME = "RENT";

    public static final String COLUMN_RENT_ID = "id";
    public static final String COLUMN_RENT_ITEM_ID = "itenId";
    public static final String COLUMN_RENT_UNIT_ID = "unitId";
    public static final String COLUMN_RENT_UNIT = "rentId";
    public static final String COLUMN_RENT_ITEM = "item";
    public static final String COLUMN_RENT_RENT = "rent";

    public static final String CREATE_TABLE_RENT = "create table " + TABLE_RENT_NAME +
            "(" + COLUMN_UNITS_ID + " INTEGER , "
            + COLUMN_RENT_UNIT + " TEXT, "
            + COLUMN_RENT_ITEM_ID + " INTEGER, "
            + COLUMN_RENT_UNIT_ID + " INTEGER, "
            + COLUMN_RENT_RENT + " REAL, "
            + COLUMN_RENT_ITEM + " TEXT );";


}
