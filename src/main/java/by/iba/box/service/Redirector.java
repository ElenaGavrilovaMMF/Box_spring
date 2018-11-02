package by.iba.box.service;

import by.iba.box.action.FileAction;
import by.iba.box.action.FolderAction;
import by.iba.box.action.ItemAction;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class Redirector {

    @Autowired
    ItemAction itemAction;

    @Autowired
    FileAction fileAction;

    public ItemAction redirect(BoxFolder rootFolder, @ModelAttribute("api") BoxAPIConnection api) {
        for (BoxItem.Info itemInfo : rootFolder) {
            if (itemAction.getFolderAction().isFolder(itemInfo)) {
                itemAction.getFolderAction().addBoxFolder(new BoxFolder(api, itemInfo.getID()));
            } else {
                itemAction.getFileAction().addBoxFile(new BoxFile(api, itemInfo.getID()));
            }
        }
        return itemAction;
    }

    public void redirect(BoxFile boxFile, HttpServletResponse resp) throws IOException {
        fileAction.downloadFile(boxFile, resp);
    }

    public void redirect(BoxFolder folder, HttpServletRequest req) throws IOException, ServletException {
        fileAction.uploadFile(req, folder);
    }
}
