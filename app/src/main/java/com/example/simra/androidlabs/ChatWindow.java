package com.example.simra.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;



public class ChatWindow extends Activity {


    SQLiteDatabase database;
    ChatDatabaseHelper dbhelper;

    protected static final String ACTIVITY_NAME = "ChatWindow";

    private ArrayList<String> messages = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ListView myList = (ListView) findViewById(R.id.List);
        final EditText editText = (EditText) findViewById(R.id.ChatEditText);
        Button button = (Button)findViewById(R.id.sendButton);
        messages = new ArrayList<String>();

        dbhelper = new ChatDatabaseHelper(this); // open the ChatDatabaseHelper Object
        database = dbhelper.getWritableDatabase();

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        myList.setAdapter((ListAdapter) messageAdapter);//to populate the listView with data in the listView

        final ContentValues cv = new ContentValues();
        Cursor cursor = database.query(ChatDatabaseHelper.TABLE_NAME, new String[]{ChatDatabaseHelper.KEY_ID, ChatDatabaseHelper.KEY_MESSAGE}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String message = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
                messages.add(message);
                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE) ) );
                cursor.moveToNext();
            }
        }

        Log.i(ACTIVITY_NAME, "Cursor's column count=" + cursor.getColumnCount());
        for (int i=0; i<cursor.getColumnCount(); i++) {
            Log.i("  ---> Column # " + (i + 1) + " : " + ACTIVITY_NAME, cursor.getColumnName(i));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the text in the editText field
                //add it to the array List variable
                String msg = editText.getText().toString();
                messages.add(msg);
                messageAdapter.notifyDataSetChanged();
                editText.setText("");
                ContentValues newRow = new ContentValues();
                newRow.put(ChatDatabaseHelper.KEY_MESSAGE,msg);
                database.insert(ChatDatabaseHelper.TABLE_NAME,"",newRow);

            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    private  class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx)
        {
            super(ctx, 0);
        }

        public int getCount() {
            return messages.size();
        }


        public String getItem(int position) {
            return messages.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);  //this is the msg from the chat_row_incoming/outing
            message.setText(getItem(position));
            return result;


        }
        public long getItemId(int position){ // returns the database ID of the item in the row number (position)
            return position;
        }


    }// end class Adapter
    }// end of class chatWindow

