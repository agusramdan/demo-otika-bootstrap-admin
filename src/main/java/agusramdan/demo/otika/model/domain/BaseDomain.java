package agusramdan.demo.otika.model.domain;

import java.io.Serializable;

public interface BaseDomain <ID extends Serializable>{

    ID getId();
    void setId(ID id);

}
