package tech.fraction.webapp.SqliteDatabase.Sqlitehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.SqliteDatabase.DbConstants;
import tech.fraction.webapp.SqliteDatabase.model.Chamber;
import tech.fraction.webapp.SqliteDatabase.model.City;
import tech.fraction.webapp.SqliteDatabase.model.Country;
import tech.fraction.webapp.SqliteDatabase.model.ItemRent;
import tech.fraction.webapp.SqliteDatabase.model.Items;
import tech.fraction.webapp.SqliteDatabase.model.Racks;
import tech.fraction.webapp.SqliteDatabase.model.State;
import tech.fraction.webapp.SqliteDatabase.model.Units;

public class SqLiteHelperFunctions extends SQLiteOpenHelper {

    static final String DB_NAME = "AmbicaMerchant.DB";

    static final int DB_VERSION = 1;

    public SqLiteHelperFunctions(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.CREATE_TABLE_COUNTRY);
        db.execSQL(DbConstants.CREATE_TABLE_STATE);
        db.execSQL(DbConstants.CREATE_TABLE_CITY);
        db.execSQL(DbConstants.CREATE_TABLE_UNITS);
        db.execSQL(DbConstants.CREATE_TABLE_RACKS);
        db.execSQL(DbConstants.CREATE_TABLE_ITEMS);
        db.execSQL(DbConstants.CREATE_TABLE_RENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_COUNTRY_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_STATE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_CITY_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_UNITS_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_RACKS_NAME);
        onCreate(db);
    }

    /* ============================================== method Country table=======================================================================================*/
    public boolean insertCountry(Country contry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_COUNTRY_ID, contry.getId());
        contentValues.put(DbConstants.COLUMN_COUNTRY_NAME, contry.getName());
        contentValues.put(DbConstants.COLUMN_COUNTRY_CODE, contry.getCode());
        contentValues.put(DbConstants.COLUMN_COUNTRY_MOBILECODE, contry.getMobileCode());
        db.insert(DbConstants.TABLE_COUNTRY_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Country> getAllCountry() {
        ArrayList<Country> country_list = new ArrayList<Country>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_COUNTRY_NAME, null);


        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();

                country.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_COUNTRY_ID)));
                country.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_COUNTRY_NAME)));
                country.setCode(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_COUNTRY_CODE)));
                country.setMobileCode(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_COUNTRY_MOBILECODE)));

                country_list.add(country);

            } while (cursor.moveToNext());

        }
        return country_list;

    }

    /* ==============================================common method=======================================================================================*/
    public boolean getTableRecordCount(String Table_Name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + Table_Name;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public void deleteAllRecord(Context context, String TABLE_NAME) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_NAME);

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /*=====================================================method of state table===============================================================*/
    public boolean insertState(State state) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_STATE_ID, state.getId());
        contentValues.put(DbConstants.COLUMN_STATE_NAME, state.getName());
        contentValues.put(DbConstants.COLUMN_STATE_CODE, state.getCode());
        contentValues.put(DbConstants.COLUMN_STATE_COUNTRYID, state.getCountryId());
        contentValues.put(DbConstants.COLUMN_STATE_COUNTRYNAME, state.getCountryName());
        db.insert(DbConstants.TABLE_STATE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<State> getAllStates() {
        ArrayList<State> state_list = new ArrayList<State>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_STATE_NAME, null);


        if (cursor.moveToFirst()) {
            do {
                State state = new State();

                state.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_STATE_ID)));
                state.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_STATE_NAME)));
                state.setCode(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_STATE_CODE)));
                state.setCountryId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_STATE_COUNTRYID)));
                state.setCountryName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_STATE_COUNTRYNAME)));


                state_list.add(state);

            } while (cursor.moveToNext());

        }
        return state_list;

    }

    /* ============================================== method of city table=======================================================================================*/
    public boolean insertCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_CITY_ID, city.getId());
        contentValues.put(DbConstants.COLUMN_CITY_NAME, city.getName());
        contentValues.put(DbConstants.COLUMN_CITY_STATE_ID, city.getStateId());
        contentValues.put(DbConstants.COLUMN_CITY_STATE_NAME, city.getStateName());
        contentValues.put(DbConstants.COLUMN_CITY_COUNTRY_ID, city.getCountryId());
        contentValues.put(DbConstants.COLUMN_CITY_COUNTRY_NAME, city.getCountryName());


        db.insert(DbConstants.TABLE_CITY_NAME, null, contentValues);
        return true;
    }

    public ArrayList<City> getAllCity() {
        ArrayList<City> city_list = new ArrayList<City>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_CITY_NAME, null);


        if (cursor.moveToFirst()) {
            do {
                City city = new City();

                city.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_CITY_ID)));
                city.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CITY_NAME)));
                city.setStateId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_CITY_STATE_ID)));
                city.setStateName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CITY_STATE_NAME)));
                city.setCountryId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_CITY_COUNTRY_ID)));
                city.setCountryName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CITY_COUNTRY_NAME)));


                city_list.add(city);

            } while (cursor.moveToNext());

        }
        return city_list;
    }

    /* ============================================== method of unit table=======================================================================================*/
    public boolean insertUnits(Units units) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_UNITS_ID, units.getId());
        contentValues.put(DbConstants.COLUMN_UNITS_NAME, units.getName());
        contentValues.put(DbConstants.COLUMN_UNITS_WEIGHT, units.getWeight());
        contentValues.put(DbConstants.COLUMN_UNITS_WEIGHT_UNIT, units.getWeightUnit());

        db.insert(DbConstants.TABLE_UNITS_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Units> getAllUnits() {
        ArrayList<Units> units_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_UNITS_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Units units = new Units();
                units.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_UNITS_ID)));
                units.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_UNITS_NAME)));
                units.setWeight(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_UNITS_WEIGHT)));
                units.setWeightUnit(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_UNITS_WEIGHT_UNIT)));
                units_list.add(units);
            } while (cursor.moveToNext());
        }
        return units_list;
    }

    /* ============================================== method of racks table=======================================================================================*/
    public boolean insertRacks(Racks racks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_RACKS_ID, racks.getId());
        contentValues.put(DbConstants.COLUMN_RACKS_NAME, racks.getName());
        contentValues.put(DbConstants.COLUMN_RACKS_CHAMBER_ID, racks.getChamberId());
        contentValues.put(DbConstants.COLUMN_RACKS_FLOOR_ID, racks.getFloorId());
        contentValues.put(DbConstants.COLUMN_RACKS_STATUS, racks.getStatus());

        db.insert(DbConstants.TABLE_RACKS_NAME, null, contentValues);
        return true;
    }

    public List<Racks> getAllRacks() {
        List<Racks> racks_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_RACKS_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Racks racks = new Racks();

                racks.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RACKS_ID)));
                racks.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_RACKS_NAME)));
                racks.setChamberId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RACKS_CHAMBER_ID)));
                racks.setFloorId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RACKS_FLOOR_ID)));
                racks.setStatus(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_RACKS_STATUS)));


                racks_list.add(racks);
            } while (cursor.moveToNext());

        }
        return racks_list;

    }

    /* ============================================== method of chamber table=======================================================================================*/
    public boolean insertChamber(Chamber chamber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_CHAMBER_ID, chamber.getId());
        contentValues.put(DbConstants.COLUMN_CHAMBER_NAME, chamber.getName());
        contentValues.put(DbConstants.COLUMN_CHAMBER_STATUS, chamber.getStatus());

        db.insert(DbConstants.TABLE_CHAMBER_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Chamber> getAllChamber() {
        ArrayList<Chamber> chamber_list = new ArrayList<Chamber>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_CHAMBER_NAME, null);


        if (cursor.moveToFirst()) {
            do {
                Chamber chamber = new Chamber();

                chamber.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_CHAMBER_ID)));
                chamber.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CHAMBER_NAME)));
                chamber.setStatus(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CHAMBER_STATUS)));


                chamber_list.add(chamber);
            } while (cursor.moveToNext());

        }
        return chamber_list;

    }

    /* ============================================== method of Item table=======================================================================================*/
    public boolean insertItems(Items items) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_ITEMS_ID, items.getId());
        contentValues.put(DbConstants.COLUMN_ITEMS_NAME, items.getName());
        contentValues.put(DbConstants.COLUMN_ITEMS_BILLING_TYPE, items.getBillingType());
        contentValues.put(DbConstants.COLUMN_ITEMS_STATUS, items.getStatus());
        db.insert(DbConstants.TABLE_ITEMS_NAME, null, contentValues);
        return true;
    }

    public List<Items> getAllItems() {
        List<Items> items_list = new ArrayList<Items>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_ITEMS_NAME, null);


        if (cursor.moveToFirst()) {
            do {
                Items items = new Items();

                items.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_ITEMS_ID)));
                items.setName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_ITEMS_NAME)));
                items.setBillingType(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_ITEMS_BILLING_TYPE)));
                items.setStatus(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_ITEMS_STATUS)));

                items_list.add(items);

            } while (cursor.moveToNext());

        }
        return items_list;

    }

    /* ============================================== method of chamber table=======================================================================================*/
    public boolean insertItemRent(ItemRent itemRent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_RENT_ID, itemRent.getId());
        contentValues.put(DbConstants.COLUMN_RENT_ITEM_ID, itemRent.getItemId());
        contentValues.put(DbConstants.COLUMN_RENT_UNIT_ID, itemRent.getUnitId());
        contentValues.put(DbConstants.COLUMN_RENT_RENT, itemRent.getRent());
        contentValues.put(DbConstants.COLUMN_RENT_UNIT, itemRent.getUnit());
        contentValues.put(DbConstants.COLUMN_RENT_ITEM, itemRent.getItem());

        db.insert(DbConstants.TABLE_RENT_NAME, null, contentValues);
        return true;
    }

    public List<ItemRent> getItemRentByItemId(int id) {
        List<ItemRent> selectedItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbConstants.TABLE_RENT_NAME + " WHERE " + DbConstants.COLUMN_RENT_ITEM_ID + "=" + id, null);
        if (cursor.moveToFirst()) {
            do {
                ItemRent items = new ItemRent();

                items.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RENT_ID)));
                items.setItemId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RENT_ITEM_ID)));
                items.setUnitId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RENT_UNIT_ID)));
                items.setRent(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_RENT_RENT)));
                items.setUnit(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_RENT_UNIT)));
                items.setItem(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_RENT_ITEM)));

                selectedItems.add(items);

            } while (cursor.moveToNext());

        }


        return selectedItems;
    }


    public ArrayList<ItemRent> getAllItemRents() {
        ArrayList<ItemRent> itemRent_list = new ArrayList<ItemRent>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbConstants.TABLE_RENT_NAME, null);


        if (cursor.moveToFirst()) {
            do {
                ItemRent itemRent = new ItemRent();

                itemRent.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RENT_ID)));
                itemRent.setItemId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RENT_ITEM_ID)));
                itemRent.setUnitId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_RENT_UNIT_ID)));
                itemRent.setRent(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_RENT_RENT)));
                itemRent.setUnit(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_RENT_UNIT)));
                itemRent.setItem(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_RENT_ITEM)));


                itemRent_list.add(itemRent);
            } while (cursor.moveToNext());

        }
        return itemRent_list;

    }


}