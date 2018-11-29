package by.iba.box;

import by.iba.box.entity.JsonBox;
import by.iba.box.service.ConfigService;

import javax.sql.DataSource;

public class DataBaseDemo {

    public static void main(String[] args) {
        new ConfigService().getConfig();
    }
}

