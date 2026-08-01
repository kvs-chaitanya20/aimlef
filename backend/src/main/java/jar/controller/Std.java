package jar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jar.model.Student;
import jar.repo.StudentRepo;


@RestController
@RequestMapping("/std")
public class Std {

    @GetMapping()
    Map<Object, Object> m1() {
        Map<Object, Object> res = new HashMap<>();
        res.put("api", "welcome to get api");
        res.put("status",200);
        res.put("student data",all());
        return res;

    }
    @Autowired
    StudentRepo db;
    @PostMapping()
    Map<Object, Object> m2(@RequestBody Student d) {
        Map<Object, Object> res = new HashMap<>();
        String x=d.getName();
        String y=d.getEmail();
        String z=d.getIp();
        Student entity=new Student();
        entity.setName(x);
        entity.setEmail(y);
        entity.setIp(z);
        db.save(entity);
        res.put("api", "welcome to post api");
        res.put("status",201);
        res.put("name",x);
        res.put("email",y);
        return res;

    }
    @PutMapping("/{id}")
    Map<Object, Object> m3(@PathVariable Long id,@RequestBody Student d) {
        Map<Object, Object> res = new HashMap<>();
        Student entity=db.findById(id).orElse(null);
        if(entity==null){
            res.put("api", "welcome to put api");
            res.put("status",404);
            res.put("message","student not found");
            return res;
        }
        entity.setName(d.getName());
        entity.setEmail(d.getEmail());
        entity.setIp(d.getIp());
        db.save(entity);
        res.put("api", "welcome to put api");
        res.put("status",200);
        res.put("message","student updated successfully");
        res.put("student",entity);
        return res;
    }
    @DeleteMapping("/{id}")
Map<Object, Object> m4(@PathVariable Long id) {

    Map<Object, Object> res = new HashMap<>();

    Student entity = db.findById(id).orElse(null);

    if(entity == null){
        res.put("api", "welcome to delete api");
        res.put("status",404);
        res.put("message","student not found");
        return res;
    }

    db.delete(entity);

    res.put("api", "welcome to delete api");
    res.put("status",200);
    res.put("message","student deleted successfully");

    return res;
}

    



    List<Student> all(){
        return db.findAll();
    }
}