package annotation.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2017/1/10.
 * Email me : EricLi1235@gmail.com.
 */
public class TableCreator {
    public static void main(String[] args) throws Exception{
        if(args.length < 0){
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        for(String className: args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.out.println("NO DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for(Field field : cl.getDeclaredFields()){
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length < 1){
                    continue;
                }
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger)anns[0];
                    if(sInt.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLString){
                    SQLString sStr = (SQLString)anns[0];
                    if(sStr.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else {
                        columnName = sStr.name();
                    }

                    columnDefs.add(columnName +
                            " VARCHAR(" + sStr.value() + ")" + getConstraints(sStr.constraints()));
                }

                StringBuilder createCommand = new StringBuilder("CREATE TABLE "+ tableName + "(");
                for(String columnDef : columnDefs){
                    createCommand.append("\n    " + columnDef + ",");
                }
                String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ")";
                System.out.println("Table Creation SQL for " + className + "is \n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con){
        String constrains = "";
        if(!con.allowNull()){
            constrains += "NOT NULL";
        }
        if(con.primaryKey()){
            constrains += "PRIMARY KEY";
        }
        if(con.unique()){
            constrains += "UNIQUE";
        }
        return constrains;
    }
}
