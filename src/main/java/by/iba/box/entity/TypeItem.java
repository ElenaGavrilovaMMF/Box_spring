package by.iba.box.entity;

import java.util.ArrayList;
import java.util.List;

public enum TypeItem {
    FILE {
        @Override
        public String getValue() {
            return "file";
        }
    },
    FOLDER {
        @Override
        public String getValue() {
            return "folder";
        }
    },
    WEB_LINK {
        @Override
        public String getValue() {
            return "web_link";
        }
    };

    abstract public String getValue();

    public static List<String> getListType() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(FILE.getValue());
        arrayList.add(FOLDER.getValue());
        arrayList.add(WEB_LINK.getValue());
        return arrayList;
    }
}
