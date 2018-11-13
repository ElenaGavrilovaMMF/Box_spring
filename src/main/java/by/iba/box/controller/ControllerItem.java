package by.iba.box.controller;

import by.iba.box.action.ItemAction;
import by.iba.box.entity.TypeItem;
import by.iba.box.service.Redirector;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


@MultipartConfig
@Controller
public class ControllerItem {
    private static final String TOKEN = "WE4XGIpwKPeoOquVu6SyYRhEXs4ffcR6";
    private static final BoxAPIConnection BOX_API_CONNECTION = new BoxAPIConnection(TOKEN);
    private static final String ROOT_FOLDER_PARENT = "0";

    @Autowired
    Redirector redirector;

    @ModelAttribute
    public void addType(Model model) {
        model.addAttribute("types", TypeItem.values());
    }

    @GetMapping("/")
    public String openPage(Model model) {
        BoxFolder rootFolder = BoxFolder.getRootFolder(BOX_API_CONNECTION);
        ItemAction itemAction = redirector.redirect(rootFolder);
        model.addAttribute("items", itemAction);
        model.addAttribute("folderCurrent", ROOT_FOLDER_PARENT);

        return "profile";
    }

    @GetMapping("/folder")
    public String openFolder(Model model, @RequestParam("id") String id) {
        BoxFolder folder = new BoxFolder(BOX_API_CONNECTION, id);
        ItemAction itemAction = redirector.redirect(folder);
        model.addAttribute("items", itemAction);
        if (id.equals(ROOT_FOLDER_PARENT)) {
            model.addAttribute("folderParent", null);
        } else {
            model.addAttribute("folderParent", folder.getInfo().getParent().getID());
        }
        model.addAttribute("folderCurrent", id);

        return "profile";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("type") String type, @RequestParam("searchTerm") String searchTerm) throws IOException {
        ItemAction itemAction = redirector.redirect(searchTerm, type, BOX_API_CONNECTION);
        model.addAttribute("items", itemAction);
        model.addAttribute("search", searchTerm);

        return "search-result";
    }

    @GetMapping("/file")
    public void downloadFile(HttpServletResponse resp, @RequestParam("idFile") String id) throws IOException {
        BoxFile file = new BoxFile(BOX_API_CONNECTION, id);
        redirector.redirect(file, resp);
    }

    //TODO (дублированые файлы)
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("idFolder") String idFolder, Model model) throws IOException, ServletException {
        BoxFolder boxFolder = new BoxFolder(BOX_API_CONNECTION, idFolder);
        redirector.redirect(boxFolder,file);

        ItemAction itemAction = redirector.redirect(boxFolder);
        model.addAttribute("items", itemAction);
        if (idFolder.equals(ROOT_FOLDER_PARENT)) {
            model.addAttribute("folderParent", null);
        } else {
            model.addAttribute("folderParent", boxFolder.getInfo().getParent().getID());
        }
        model.addAttribute("folderCurrent", idFolder);

        return "profile";
    }
}




