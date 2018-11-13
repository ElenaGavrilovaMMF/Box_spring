package by.iba.box.action;

import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

@NoArgsConstructor
public class FileAction {
    private final ArrayList<BoxFile> BOX_FILES = new ArrayList();

    public void addBoxFile(BoxFile file) {
        BOX_FILES.add(file);
    }

    public ArrayList<BoxFile> getListFiles() {
        return BOX_FILES;
    }

    public void uploadFile(BoxFolder folder, MultipartFile file) throws IOException, ServletException {
        InputStream inputStream = file.getInputStream();
        folder.uploadFile(inputStream, file.getOriginalFilename());
        inputStream.close();
    }

    public void downloadFile (BoxFile boxFile, HttpServletResponse resp) throws IOException {
        URL downloadURL = boxFile.getDownloadURL();
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition","attachment; filename="+boxFile.getInfo().getName());
        resp.getWriter().write(downloadURL.getPath());
    }
}
