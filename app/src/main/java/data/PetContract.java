package data;

import android.provider.BaseColumns;

/**
 * Created by bukola_omotoso on 01/10/16.
 */
public final class PetContract implements BaseColumns {

    public PetContract(){

    }

    public static final class PetEntry  {

        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;


    }

}
