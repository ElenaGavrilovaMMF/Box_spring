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
}
