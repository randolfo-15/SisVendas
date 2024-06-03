package bank;

public class SQL {

    public static final String
            /* Table */
            TABLE_USER    = "User",
            TABLE_PRODUCT = "User",

            /* Querys */
            SELECT = "SELECT * FROM users WHERE ? = ?",

            /* Columns */
            COLUNM_NAME     = "name",
            COLUNM_UNAME    = "uname",
            COLUNM_PHONE    = "phone",
            COLUNM_EMAIL    = "email",
            COLUNM_PASSW    = "password",
            COLUMN_ADM      = "administrador",
            COLUMN_CODE     = "codigo",
            COLUMN_CATEGORY = "category",
            COLUMN_VALUE    = "valor",
            COLUMN_AMOUNT   = "amount";


            public static final String URL = "jdbc:mysql://localhost/mystore?serverTimezone=UTC";
            public static final String USER = "root";
            public static final String PASSWORD = "";
}
