package cn.didiao.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao<T> {
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;


    public int executeUpdate(String sql, Object... obj) {
        con = JDBCUtil.getConnection();
        int index=1;
        int j = 0;
        try {
            psm = con.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] instanceof Object[]) {
                    Object[] o1 = (Object[]) obj[i];
                    for (int i1 = 0; i1 < o1.length; i1++) {
                        psm.setObject(index++,o1[i1]);
                    }
                }else {
                    psm.setObject(index++, obj[i]);
                }
            }
            j = psm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeAll(con, psm);
        }

        return j;
    }

    public T selectOne(String sql, Class aClass, Object... obj) {
        con = JDBCUtil.getConnection();
        Object o = null;
        try {
            psm = con.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                psm.setObject(i + 1, obj[i]);
            }
            rs = psm.executeQuery();
            Field[] fields = aClass.getDeclaredFields();

            if (rs.next()) {
                o = aClass.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    String name = "set" + (fields[i].getName().charAt(0) + "").toUpperCase();
                    name = name + fields[i].getName().substring(1);

                    Method method = aClass.getDeclaredMethod(name, fields[i].getType());
                    method.setAccessible(true);
                    method.invoke(o, rs.getObject(i + 1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeAll(con, psm, rs);
        }
        return (T) o;
    }

    private T selectOne(Class aClass, ResultSet rs, Field[] fields) {

        Object o = null;
        try {
            if (rs.next()) {
                o = aClass.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    String name = "set" + (fields[i].getName().charAt(0) + "").toUpperCase();
                    name = name + fields[i].getName().substring(1);

                    Method method = aClass.getDeclaredMethod(name, fields[i].getType());
                    method.setAccessible(true);
                    method.invoke(o, rs.getObject(i + 1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return (T) o;
    }

    public List<T> selectForList(String sql, Class aClass, Object... obj) {
        List list = new ArrayList();
        con = JDBCUtil.getConnection();
        try {
            psm = con.prepareStatement(sql);
            if (obj.length > 0) {
                for (int i = 0; i < obj.length; i++) {
                    psm.setObject(i + 1, obj[i]);
                }
            }
            rs = psm.executeQuery();
            while (true) {
                T t = selectOne(aClass, rs, aClass.getDeclaredFields());
                if (t != null) {
                    list.add(t);
                } else {
                    break;
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeAll(con, psm, rs);
        }
        return list;
    }

    public Map<String, Object> selectForMap(String sql, Object... obj) {
        Map<String, Object> map = new HashMap<>();
        con = JDBCUtil.getConnection();
        try {
            psm = con.prepareStatement(sql);
            if (obj.length > 0) {
                for (int i = 0; i < obj.length; i++) {
                    psm.setObject(i + 1, obj[i]);
                }
            }
            rs = psm.executeQuery();
            if (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int count = metaData.getColumnCount();
                for (int i = 0; i < count; i++) {
                    String columnClassName = metaData.getColumnName(i + 1);
                    Object object = rs.getObject(columnClassName);
                    map.put(columnClassName, object);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeAll(con, psm, rs);
        }
        return map;
    }

    private Map<String, Object> selectForMap(ResultSet rs) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int count = metaData.getColumnCount();
                for (int i = 0; i < count; i++) {
                    String columnClassName = metaData.getColumnName(i + 1);
                    Object object = rs.getObject(columnClassName);
                    map.put(columnClassName, object);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public List<Map<String, Object>> selectForListMap(String sql, Object... obj) {
        List<Map<String, Object>> list = new ArrayList<>();
        con = JDBCUtil.getConnection();
        try {
            psm = con.prepareStatement(sql);
            if (obj.length > 0) {
                for (int i = 0; i < obj.length; i++) {
                    psm.setObject(i + 1, obj[i]);
                }
            }
            rs = psm.executeQuery();
            while (true) {
                Map<String, Object> stringObjectMap = selectForMap(rs);
                if (stringObjectMap == null) {
                    break;
                }
                list.add(stringObjectMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeAll(con, psm, rs);
        }
        return list;

    }

}
