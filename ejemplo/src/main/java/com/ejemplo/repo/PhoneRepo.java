package com.ejemplo.repo;

import com.ejemplo.vo.PhoneVO;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<PhoneVO, UUID> {
    
    @Query("delete from PhoneVO u where u.idUsuario = ?1")
    void deleteAllPhonesByIdUsuario(UUID idUsuario);
    
    @Query("select u from PhoneVO u where u.idUsuario = ?1")
    List<PhoneVO> getListPhonesByIdUsuario(UUID idUsuario);
}
