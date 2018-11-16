package by.iba.box.service;

import by.iba.box.action.FileAction;
import by.iba.box.action.FolderAction;
import by.iba.box.action.ItemAction;
import com.box.sdk.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class Redirector {

    public ItemAction redirect(BoxFolder rootFolder) {
        ItemAction itemAction = new ItemAction();
        FolderAction folderAction = new FolderAction();
        FileAction fileAction = new FileAction();
        for (BoxItem.Info itemInfo : rootFolder) {
            if (folderAction.isFolder(itemInfo)) {
                folderAction.addBoxFolder(new BoxFolder(rootFolder.getAPI(), itemInfo.getID()));
            } else {
                fileAction.addBoxFile(new BoxFile(rootFolder.getAPI(), itemInfo.getID()));
            }
        }
        itemAction.setFileAction(fileAction);
        itemAction.setFolderAction(folderAction);
        return itemAction;
    }

    public ItemAction redirect(String searchContext, String type, BoxAPIConnection api) throws IOException {
        ItemAction itemAction = new ItemAction(new FolderAction(), new FileAction());
        PartialCollection<BoxItem.Info> searchTerm = new ItemAction().searchItem(api, searchContext, type);
        if (searchTerm!=null) {
            for (BoxItem.Info info : searchTerm) {
                if(itemAction.getFolderAction().isFolder(info)){
                    itemAction.getFolderAction().addBoxFolder(new BoxFolder(info.getResource().getAPI(),info.getID()));
                } else {
                    if (info.getName().contains(searchContext)) {
                       itemAction.getFileAction().addBoxFile(new BoxFile(info.getResource().getAPI(), info.getID()));
                    }
                }
            }
        }
        return itemAction;
    }

    public void redirect(BoxFile boxFile, HttpServletResponse resp) throws IOException {
        FileAction fileAction = new FileAction();
        fileAction.downloadFile(boxFile, resp);
    }

    public void redirect(BoxFolder folder, MultipartFile file) throws IOException, ServletException {
        FileAction fileAction = new FileAction();
        fileAction.uploadFile(folder, file);
    }

    public String redirect(BoxItem.Info item) {
        return new ItemAction().getLink(item);
    }

}
