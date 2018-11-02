package by.iba.box.parser;

import by.iba.box.action.FolderAction;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

@NoArgsConstructor
public class ParserFolder {

    @Autowired
    FolderAction folderAction;

    public void parse(BoxItem.Info itemInfo, @ModelAttribute("api")BoxAPIConnection api) {
        if (folderAction.isFolder(itemInfo)) {
            BoxFolder folder = new BoxFolder(api, itemInfo.getID());
            for (BoxItem.Info child : folder.getChildren()) {
                parse(child,api);
            }
        }
    }
}

