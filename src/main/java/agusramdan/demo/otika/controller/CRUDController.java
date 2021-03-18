package agusramdan.demo.otika.controller;

import agusramdan.demo.otika.component.NotFoundException;
import agusramdan.demo.otika.model.domain.BaseDomain;
import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Optional;


public abstract class CRUDController<T extends BaseDomain<ID>, ID extends Serializable> {

    protected String domainName = "domain";
    protected String page="menu/sub_menu";
    protected String redirectList  ;
    protected String pageList  ;
    protected String pageForm  ;
    protected String pageFormAdd  ;
    protected String pageFormEdit  ;
    protected String pageShow ;

    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract T prepareAdd();
    protected T prepareEdit(ID id){
        return getRepository().findById(id).orElseThrow(()->new NotFoundException("ID : "+id));
    }
    protected T prepareShow(ID id){
        return getRepository().findById(id).orElseThrow(()->new NotFoundException("ID : "+id));
    }

    @PostConstruct
    protected void pageSetup(){
        pageList= (pageList!=null)?pageList:page+"/list";
        pageForm = (pageForm!=null)?pageForm: page+"/form";
        pageFormAdd = (pageFormAdd!=null)?pageFormAdd: page+"/form";;
        pageFormEdit = (pageFormEdit!=null)?pageFormEdit: page+"/form";;
        pageShow = (pageShow!=null)?pageShow:page+"/show";
        redirectList = (redirectList!=null)?redirectList: "redirect:/"+pageList;
    }

    @GetMapping
    public String index() {
        return redirectList;
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("list", getRepository().findAll());
        return pageList;
    }

    @GetMapping("/add")
    public String add(Model model, final RedirectAttributes ra) {
        model.addAttribute(domainName, prepareAdd());
        return pageFormAdd;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ID id, final RedirectAttributes ra) {

        getRepository().deleteById(id);
        ra.addFlashAttribute("successFlash","Deleted "+id);
        return redirectList;

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ID id, Model model, final RedirectAttributes ra) {

        model.addAttribute(domainName, prepareEdit(id));

        return pageFormEdit;

    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable ID id, Model model, final RedirectAttributes ra) {
        model.addAttribute(domainName, prepareShow(id));
        return pageShow;
    }

    @PostMapping(value = "/save")
    public String save(T data, final RedirectAttributes ra) {
        val save = getRepository().save(data);
        ra.addFlashAttribute("successFlash", "Data Saved.");
        return redirectList;

    }


}
