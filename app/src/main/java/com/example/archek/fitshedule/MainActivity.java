package com.example.archek.fitshedule;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.archek.fitshedule.network.FitService;
import com.example.archek.fitshedule.network.ObjectResponse;
import com.example.archek.fitshedule.network.RestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private List<ObjectResponse> list = new ArrayList <>();
    private FitService service = RestApi.createService(FitService.class);
    private FitAdapter adapter = new FitAdapter();
    private SQLiteDatabase database;
    private ContentValues contentValues = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupRecyclerView();
        final Cursor cursor = database.query(DBHelper.TABLE_TRENINGS, null, null, null, null, null, null);

        /*download data from a database(if it not empty)*/
        getDataFromDB(cursor);

        /*if database is empty, load from net into adapter & database */
        if (list.isEmpty()){
            loadDataFromNet(cursor);
        }
        /*put geting data into adapter*/
        else adapter.replaceAll(list);

        /*Active Image for reloading*/
        ImageView ivRefresh = findViewById(R.id.ivRefresh);
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                database.delete(DBHelper.TABLE_TRENINGS, null, null);
                startActivity(intent);
            }
        });

    }


    private void setupRecyclerView() {
        RecyclerView rvShedule = findViewById(R.id.rvShedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvShedule.setLayoutManager(layoutManager);
        rvShedule.setAdapter(adapter);
    }
    private void loadDataFromNet(final Cursor cursors){
        Call <List <ObjectResponse>> callList = service.getShedule();
        callList.enqueue(new Callback <List <ObjectResponse>>() {
            @Override
            public void onResponse(Call <List <ObjectResponse>> call, Response <List <ObjectResponse>> response) {
                assert response.body() != null;
                List <ObjectResponse> tempList = new ArrayList <>(response.body());
                /*load data to adapter*/
                adapter.replaceAll(tempList);
                /*load data to database*/
                if(cursors == null ){
                    for (int i = 0; i < tempList.size(); i++) {
                        contentValues.put(DBHelper.KEY_NAME, tempList.get(i).getName());
                        contentValues.put(DBHelper.KEY_DESC, tempList.get(i).getDescription());
                        contentValues.put(DBHelper.KEY_WEEKDAY, tempList.get(i).getWeekDay());
                        contentValues.put(DBHelper.KEY_START_TIME, tempList.get(i).getStartTime());
                        contentValues.put(DBHelper.KEY_END_TIME, tempList.get(i).getEndTime());
                        contentValues.put(DBHelper.KEY_PLACE, tempList.get(i).getPlace());
                        contentValues.put(DBHelper.KEY_NAME_COACH, tempList.get(i).getTeacher());
                        database.insert(DBHelper.TABLE_TRENINGS, null, contentValues);
                    }
                }

            }

            @Override
            public void onFailure(Call <List <ObjectResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getDataFromDB(Cursor cursor){
        /*get data from database*/
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int descIndex = cursor.getColumnIndex(DBHelper.KEY_DESC);
            int weekdayIndex = cursor.getColumnIndex(DBHelper.KEY_WEEKDAY);
            int startIndex = cursor.getColumnIndex(DBHelper.KEY_START_TIME);
            int endIndex = cursor.getColumnIndex(DBHelper.KEY_END_TIME);
            int placeIndex = cursor.getColumnIndex(DBHelper.KEY_PLACE);
            int coachIndex = cursor.getColumnIndex(DBHelper.KEY_NAME_COACH);
            do {
                Integer weekday = Integer.valueOf(cursor.getString(weekdayIndex));
                list.add(new ObjectResponse(cursor.getString(nameIndex), cursor.getString(descIndex), weekday, cursor.getString(startIndex), cursor.getString(endIndex), cursor.getString(placeIndex), cursor.getString(coachIndex)));
            } while (cursor.moveToNext());
        } else cursor.close();

        cursor.close();
    }
}

