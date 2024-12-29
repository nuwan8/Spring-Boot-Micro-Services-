package nmi.service.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nmi.service.users.repository.ModuleRepository;
import nmi.service.users.model.Module;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllModules(){

        List<Module> list = moduleRepository.findAll();

        return list;
    }
    
}
