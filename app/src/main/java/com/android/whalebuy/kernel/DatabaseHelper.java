package com.android.whalebuy.kernel;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "people_sports.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 0;

	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i("DatabaseHelper", "db_version:" + DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource)
	{
		// TODO Auto-generated method stub
		Log.i("DatabaseHelper", "create tables...");
		/**
		try
		{
			// 创建相关表
			 TableUtils.createTableIfNotExists(connectionSource, DataModel.AddressDetail.class);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource
			, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub
		Log.i("DatabaseHelper", "upgrade tables...");
		onCreate(db, connectionSource);
	}


	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close()
	{
		super.close();
	}
}
