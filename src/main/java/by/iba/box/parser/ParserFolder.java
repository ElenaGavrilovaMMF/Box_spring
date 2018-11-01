package by.iba.box.parser;

import by.iba.box.action.FolderAction;
import by.iba.box.service.BeanUtil;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

@NoArgsConstructor
public class ParserFolder {

    public void parse(BoxItem.Info itemInfo, @ModelAttribute("api")BoxAPIConnection api) {
        if (BeanUtil.getBean(FolderAction.class).isFolder(itemInfo)) {
            BoxFolder folder = new BoxFolder(api, itemInfo.getID());
//            BeanUtil.getBean(RepositoryFolder.class).addFolder(folder);
            for (BoxItem.Info child : folder.getChildren()) {
                parse(child,api);
            }
        }
    }
}

