package com.ejemplo.repository;

import com.ejemplo.vo.UsuarioVO;
import java.util.List;
import java.util.UUID;

public interface UsuarioRepository {
    
    List<UsuarioVO> listarUsuarios();
    
    void createUsuario(UsuarioVO usuarioVO);
    
    UsuarioVO getUsuario(UUID id);
    
    UsuarioVO updateUsuario(UsuarioVO usuarioVO);
    
    void deleteUsuario(UUID id);
    
    List<UsuarioVO> validarExistenciaMail(String mail);
}
