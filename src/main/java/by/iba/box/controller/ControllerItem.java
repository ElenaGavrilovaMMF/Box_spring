package by.iba.box.controller;

import by.iba.box.action.FileAction;
import by.iba.box.action.ItemAction;
import by.iba.box.parser.ParserFolder;
import by.iba.box.service.BeanUtil;
import by.iba.box.service.Redirector;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@SessionAttributes("api")
@Controller
public class ControllerItem {
    private static final String TOKEN = "FsOZV9Q0HqC4Bb640lWfoNutHJKDlGRZ";
    private static final String ROOT_FOLDER_PARENT = "0";

    @ModelAttribute("api")
    public BoxAPIConnection api() {
        return new BoxAPIConnection(TOKEN);
    }

    @GetMapping("/")
    public String openPage(Model model) {
        BoxFolder rootFolder = BoxFolder.getRootFolder(api());
        for (BoxItem.Info child : rootFolder.getChildren()) {
            BeanUtil.getBean(ParserFolder.class).parse(child, api());
        }
        ItemAction itemAction = BeanUtil.getBean(Redirector.class).redirect(rootFolder, api());
        model.addAttribute("items", itemAction);
        model.addAttribute("idFolderCurrent", ROOT_FOLDER_PARENT);

        return "profile";
    }

    @GetMapping("/folder")
    public String openFolder(Model model, @RequestParam("id") String id) {
        BoxFolder folder = new BoxFolder(api(),id);
        ItemAction itemAction = BeanUtil.getBean(Redirector.class).redirect(folder, api());
        model.addAttribute("items", itemAction);
        if (id.equals(ROOT_FOLDER_PARENT)) {
            model.addAttribute("folderParent", null);
        } else {
            model.addAttribute("folderParent", folder.getInfo().getParent().getID());
        }
        model.addAttribute("idFolderCurrent", id);
        return "profile";
    }

    //TODO
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String downloadFile(HttpServletResponse resp, @RequestParam("idFile") String id) throws IOException {
        BoxFile file = new BoxFile(api(), id);
        BeanUtil.getBean(Redirector.class).redirect(file, resp);
        return "profile";
    }

    //TODO
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadFile(HttpServletRequest req, Model model, @RequestParam("id") String id) throws IOException, ServletException {
        BoxFolder folder = new BoxFolder(api(), id);
        BeanUtil.getBean(FileAction.class).uploadFile(req, folder);
        openFolder(model, id);
    }

}


