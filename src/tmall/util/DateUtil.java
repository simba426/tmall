package tmall.util;

public class DateUtil {
    public java.sql.Timestamp d2t (java.util.Date d) {
        if (null == d)
            return null;
        return new java.sql.Timestamp(d.getTime());
    }

    public java.util.Date t2d (java.sql.Timestamp t) {
        if (null == t)
            return null;
        return new java.sql.Timestamp(t.getTime());
    }
}
