package by.iba.box.action;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@NoArgsConstructor
@Service
public class FolderAction {
    private static final int LENGTH_ID_FOLDER = 11;

    private final ArrayList<BoxFolder> BOX_FOLDERS = new ArrayList<>();

    public Boolean isFolder(BoxItem.Info itemInfo) {
        return itemInfo.getID().length() == LENGTH_ID_FOLDER;
    }

    public void addBoxFolder(BoxFolder folders) {
        BOX_FOLDERS.add(folders);
    }

    public ArrayList<BoxFolder> getListFolders() {
        return BOX_FOLDERS;
    }
}
