package com.bitgear.skiguard;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.bitgear.skiguard.dao"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);
        addDeviceEntities(schema);

        // addPhonesEntities(schema);
    }

    // This is use to describe the colums of your table
    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addIntProperty("user_id").notNull();
        user.addStringProperty("last_name");
        user.addStringProperty("first_name");
        user.addStringProperty("email");
        return user;
    }

    private static Entity addDeviceEntities(final Schema schema) {
        Entity history = schema.addEntity("History");
        history.addIdProperty().primaryKey().autoincrement();
        history.addIntProperty("id_device").notNull();
        history.addFloatProperty("lat").notNull();
        history.addFloatProperty("lng").notNull();
        history.addIntProperty("battery").notNull();
        history.addIntProperty("id_track").notNull();
        history.addIntProperty("id_track_change").notNull().unique();

        Entity device = schema.addEntity("Device");
        device.addIdProperty().primaryKey().autoincrement();
        device.addStringProperty("serial_number").notNull();
        device.addStringProperty("color").notNull();
        device.addStringProperty("name").notNull();
        // add the foreign key "pictureId" to the "user" entity
        Property pictureIdProperty = device.addLongProperty("last_update").getProperty();
// set up a to-one relation to the "picture" entity
        device.addToOne(history, pictureIdProperty);

        return device;
    }
//    private static Entity addHistoryEntities(final Schema schema) {
//        Entity history = schema.addEntity("History");
//        history.addIdProperty().primaryKey().autoincrement();
//        history.addIntProperty("id_device").notNull();
//        history.addFloatProperty("lat");
//        history.addFloatProperty("lng");
//        history.addIntProperty("battery");
//        history.addIntProperty("id_track");
//        history.addIntProperty("id_track_change");
//        return history;
//    }
    //    private static Entity addPhonesEntities(final Schema schema) {
    //        Entity phone = schema.addEntity("Phone");
    //        phone.addIdProperty().primaryKey().autoincrement();
    //        phone.addIntProperty("user_id").notNull();
    //        phone.addStringProperty("number");
    //        return phone;
    //    }

}
