package demoroom.app.com.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Milan on 13-May-18.
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database").allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
