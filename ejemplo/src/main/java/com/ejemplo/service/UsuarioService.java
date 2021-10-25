package com.ejemplo.service;

import com.ejemplo.dto.UsuarioDTO;
import com.ejemplo.repository.PhoneRepository;
import com.ejemplo.repository.UsuarioRepository;
import com.ejemplo.util.CommonUtil;
import com.ejemplo.util.TokenUtil;
import com.ejemplo.vo.PhoneVO;
import com.ejemplo.vo.UsuarioVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private Environment env;

    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class);

    public Map<Boolean, UsuarioDTO> createUsuario(UsuarioVO usuarioVO) {
        Map<Boolean, UsuarioDTO> resultado = new LinkedHashMap<>();

        try {
            UUID id = UUID.randomUUID();
            usuarioVO.setId(id);
            usuarioVO.setToken(TokenUtil.generarToken(id));
            usuarioVO.setCreated(new Date());
            usuarioVO.setModified(new Date());
            usuarioVO.setLast_login(new Date());
            usuarioVO.setIsactive(Boolean.TRUE);

            for (PhoneVO phoneVO : usuarioVO.getPhones()) {
                phoneVO.setId(UUID.randomUUID());
                phoneVO.setIdUsuario(usuarioVO.getId());
            }

            usuarioRepository.createUsuario(usuarioVO);

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuarioVO.getId());
            usuarioDTO.setCreated(usuarioVO.getCreated());
            usuarioDTO.setModified(usuarioVO.getModified());
            usuarioDTO.setLast_login(usuarioVO.getLast_login());
            usuarioDTO.setIsactive(usuarioVO.getIsactive());
            usuarioDTO.setToken(usuarioVO.getToken());
            resultado.put(Boolean.FALSE, usuarioDTO);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error al crear usuario - Error: " + e.getMessage());
            resultado.put(Boolean.TRUE, null);
        }

        return resultado;
    }

    public Map<Boolean, UsuarioDTO> updateUsuario(UsuarioVO usuarioVO) {
        Map<Boolean, UsuarioDTO> resultado = new LinkedHashMap<>();

        try {
            usuarioVO.setModified(new Date());
            usuarioRepository.updateUsuario(usuarioVO);

            phoneRepository.deleteAllPhonesByIdUsuario(usuarioVO.getId());

            for (PhoneVO phoneVO : usuarioVO.getPhones()) {
                phoneVO.setId(UUID.randomUUID());
                phoneVO.setIdUsuario(usuarioVO.getId());

                phoneRepository.createPhone(phoneVO);
            }

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuarioVO.getId());
            usuarioDTO.setCreated(usuarioVO.getCreated());
            usuarioDTO.setModified(usuarioVO.getModified());
            usuarioDTO.setLast_login(usuarioVO.getLast_login());
            usuarioDTO.setIsactive(usuarioVO.getIsactive());
            usuarioDTO.setToken(usuarioVO.getToken());
            resultado.put(Boolean.FALSE, usuarioDTO);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error al actualizar usuario - Error: " + e.getMessage());
            resultado.put(Boolean.TRUE, null);
        }

        return resultado;
    }

    public Boolean deleteUsuario(UUID id) {
        Boolean resultado = true;

        try {
            //phoneRepository.deleteAllPhonesByIdUsuario(id);
            usuarioRepository.deleteUsuario(id);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error al eliminar usuario - Error: " + e.getMessage());
            resultado = false;
        }

        return resultado;
    }

    public Boolean validarExistenciaMail(String mail) {
        List<UsuarioVO> listado = usuarioRepository.validarExistenciaMail(mail);
        return listado.isEmpty();
    }

    public List<UsuarioVO> listarUsuarios() {
        List<UsuarioVO> listaUsuariosVO = new ArrayList<>();
        try {
            listaUsuariosVO = usuarioRepository.listarUsuarios();
            for (UsuarioVO usuarioVO : listaUsuariosVO) {
                List<PhoneVO> listaPhoneVO = phoneRepository.getPhonesByIdUsuario(usuarioVO.getId());
                usuarioVO.setPhones(listaPhoneVO);
            }
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error al obtener listado de usuarios - Error: " + e.getMessage());
        }

        return listaUsuariosVO;
    }

    public UsuarioVO getUsuario(UUID id) {
        UsuarioVO usuarioVO = null;
        try {
            usuarioVO = usuarioRepository.getUsuario(id);
            List<PhoneVO> listaPhoneVO = phoneRepository.getPhonesByIdUsuario(id);
            usuarioVO.setPhones(listaPhoneVO);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error al obtener usuario - Error: " + e.getMessage());
        }

        return usuarioVO;
    }

    public Boolean emailValidator(String email) {
        String value = env.getProperty("expresion.regular.correo");
        return CommonUtil.validator(email, value);
    }

    public Boolean passValidator(String pass) {
        String value = env.getProperty("expresion.regular.password");
        return CommonUtil.validator(pass, value);
    }
}
