package by.iba.box.action;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Getter
@Setter
public class ItemAction {
    private FolderAction folderAction;
    private FileAction fileAction;

    @Autowired
    public ItemAction(FolderAction folderAction, FileAction fileAction) {
        this.folderAction = folderAction;
        this.fileAction = fileAction;
    }
}
