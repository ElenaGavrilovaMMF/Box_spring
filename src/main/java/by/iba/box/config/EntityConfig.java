package by.iba.box.config;

import by.iba.box.action.FileAction;
import by.iba.box.action.FolderAction;
import by.iba.box.action.ItemAction;
import by.iba.box.parser.ParserFolder;
import by.iba.box.service.Redirector;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EntityConfig {
//
//    @Bean
//    @Scope("singleton")
//    public RepositoryFolder repositoryFolders(){
//        return RepositoryFolder.getInstance();
//    }

    @Bean
    @Scope("prototype")
    public FolderAction actionFolder(){
        return new FolderAction();
    }

    @Bean
    @Scope("prototype")
    public FileAction actionFile(){
        return new FileAction();
    }

    @Bean
    @Scope("prototype")
    public ItemAction actionItem(){
        return new ItemAction(actionFolder(),actionFile());
    }

    @Bean
    public ParserFolder parserFolder(){
        return new ParserFolder();
    }

    @Bean
    public Redirector showRedirect(){
        return new Redirector();
    }
}
