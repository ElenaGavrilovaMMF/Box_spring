package by.iba.box.controller;

import by.iba.box.action.FolderAction;
import by.iba.box.action.ItemAction;
import by.iba.box.entity.TypeItem;
import by.iba.box.service.Redirector;
import com.box.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;


@MultipartConfig
@Controller
public class ControllerItem {
    private static final String TOKEN = "4FH1hWz5Xo0HUWoC3acxOxB9FGj9Iwze";
    // private static final BoxAPIConnection api = new BoxAPIConnection(TOKEN);

    private static final String ROOT_FOLDER_PARENT = "0";

    @Autowired
    Redirector redirector;

    @Autowired
    BoxAPIConnection api;

    @ModelAttribute
    public void addType(Model model) {
        model.addAttribute("types", TypeItem.values());
        model.addAttribute("errorFile", null);
    }

    @GetMapping("/")
    public String openPage(Model model) {
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        api.asUser("6126420254");
        ItemAction itemAction = redirector.redirect(rootFolder);
        model.addAttribute("items", itemAction);
        model.addAttribute("folderCurrent", ROOT_FOLDER_PARENT);

        return "profile";
    }

    @GetMapping("/folder")
    public String openFolder(Model model, @RequestParam("id") String id) {
        BoxFolder folder = new BoxFolder(api, id);
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
        ItemAction itemAction = redirector.redirect(searchTerm, type, api);
        model.addAttribute("items", itemAction);
        model.addAttribute("search", searchTerm);

        return "search-result";
    }

    @GetMapping("/file")
    public void downloadFile(HttpServletResponse resp, @RequestParam("idFile") String id) throws IOException {
        BoxFile file = new BoxFile(api, id);
        redirector.redirect(file, resp);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idFolder") String idFolder, Model model) throws IOException, ServletException {
        BoxFolder boxFolder = new BoxFolder(api, idFolder);
        int flag = 0;
        for (BoxItem.Info child : boxFolder.getChildren()) {
            if (Objects.equals(file.getOriginalFilename(), child.getName())) {
                flag++;
            }
        }
        if (flag == 0) {
            redirector.redirect(boxFolder, file);
        } else {
            model.addAttribute("errorFile", "File with the same name does not exist.");
        }
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

    @PostMapping("/link")
    public String link(@RequestParam("id") String id, @RequestParam("idFolderCurrent") String idFolderCurrent, Model model) {
        BoxItem.Info info;
        if (new FolderAction().isFolder(id)) {
            info = new BoxFolder(api, id).getInfo();
        } else {
            info = new BoxFile(api, id).getInfo();
        }
        String link = redirector.redirect(info);
        model.addAttribute("link", link);
        BoxFolder folder = new BoxFolder(api, idFolderCurrent);
        model.addAttribute("items", redirector.redirect(folder));
        if (idFolderCurrent.equals(ROOT_FOLDER_PARENT)) {
            model.addAttribute("folderParent", null);
        } else {
            model.addAttribute("folderParent", folder.getInfo().getParent().getID());
        }
        model.addAttribute("folderCurrent", idFolderCurrent);

        return "profile";
    }

}




