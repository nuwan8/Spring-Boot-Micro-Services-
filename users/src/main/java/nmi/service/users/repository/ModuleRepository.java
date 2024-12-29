package nmi.service.users.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nmi.service.users.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer>  {
    
}
