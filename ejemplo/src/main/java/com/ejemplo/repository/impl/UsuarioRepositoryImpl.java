package com.ejemplo.repository.impl;

import com.ejemplo.repo.UsuarioRepo;
import com.ejemplo.repository.UsuarioRepository;
import com.ejemplo.vo.UsuarioVO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public List<UsuarioVO> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public void createUsuario(UsuarioVO usuarioVO) {
        usuarioRepo.save(usuarioVO);
    }

    @Override
    public UsuarioVO getUsuario(UUID id) {
        Optional<UsuarioVO> usuario = usuarioRepo.findById(id);
        return usuario.get();
    }

    @Override
    public UsuarioVO updateUsuario(UsuarioVO usuarioVO) {
        return usuarioRepo.save(usuarioVO);
    }

    @Override
    public void deleteUsuario(UUID id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public List<UsuarioVO> validarExistenciaMail(String mail) {
        return usuarioRepo.validarExistenciaMail(mail);
    }
}
