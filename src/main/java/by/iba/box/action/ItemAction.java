package by.iba.box.action;

import com.box.sdk.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class ItemAction {

    private FolderAction folderAction;

    private FileAction fileAction;

    private static final int LIMIT = 200;
    private static final int OFFSET = 0;
    private static final String REGEX = "(\\s)*";

    public ItemAction(FolderAction folderAction, FileAction fileAction) {
        this.fileAction = fileAction;
        this.folderAction = folderAction;
    }

    public PartialCollection<BoxItem.Info> searchItem( BoxAPIConnection api, String searchTerm, String type) {
        BoxSearchParameters bsp = new BoxSearchParameters();
        if (isEmptySearch(type)) {
            bsp.setType(type);
        }
        PartialCollection<BoxItem.Info> resultSearch;
        if (isEmptySearch(searchTerm)) {
            bsp.setQuery(searchTerm);
            BoxSearch bs = new BoxSearch(api);
            resultSearch = bs.searchRange(OFFSET, LIMIT, bsp);
        } else {
            resultSearch = null;
        }
        return resultSearch;
    }

    public boolean isEmptySearch(String testString){
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(testString);
        return !m.matches();
    }

    public String getLink(BoxItem.Info item) {
        BoxSharedLink.Access access = BoxSharedLink.Access.OPEN;
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
        permissions.setCanDownload(true);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 15);
        Date unshareDate = c.getTime();
        BoxSharedLink sharedLink;
        if (new FolderAction().isFolder(item)) {
            sharedLink = new BoxFolder(item.getResource().getAPI(), item.getID()).getInfo().getResource().createSharedLink(access, unshareDate, permissions);

        } else {
            sharedLink = new BoxFile(item.getResource().getAPI(), item.getID()).getInfo().getResource().createSharedLink(access, unshareDate, permissions);
        }
        return sharedLink.getURL();
    }
}
