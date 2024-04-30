package ma.emsi.host.repositories;

import ma.emsi.host.entities.patients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<patients,Long> {
    //pour chercher
  Page<patients> findByNomContaining(String keyword, Pageable pageable);
  @Query("select page from patients page where page.nom like :x")
    Page<patients> chercher(@Param("x") String keyword, Pageable pageable);
}
