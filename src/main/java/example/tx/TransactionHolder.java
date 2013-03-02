package example.tx;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class TransactionHolder {

    private final static ThreadLocal<OObjectDatabaseTx> dbs = new ThreadLocal<OObjectDatabaseTx>();

    public static OObjectDatabaseTx getDb() {
        return dbs.get();
    }

    public static void setDb(OObjectDatabaseTx db) {
        dbs.set(db);
    }

    public static void remove() {
        dbs.remove();
    }
}
