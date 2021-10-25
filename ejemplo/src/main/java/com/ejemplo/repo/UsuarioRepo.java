package com.ejemplo.repo;

import com.ejemplo.vo.UsuarioVO;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<UsuarioVO, UUID> {

    @Query("select u from UsuarioVO u where u.email like %?1")
    List<UsuarioVO> validarExistenciaMail(String mail);
}
