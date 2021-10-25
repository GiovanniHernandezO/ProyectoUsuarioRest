package com.ejemplo.controller;

import com.ejemplo.dto.MensajeErrorDTO;
import com.ejemplo.dto.UsuarioDTO;
import com.ejemplo.service.UsuarioService;
import com.ejemplo.vo.UsuarioVO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    private static final Logger LOGGER = Logger.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createUsuario(@RequestBody String jsonSolicitud) {
        try {
            if (jsonSolicitud == null || jsonSolicitud.isEmpty()) {
                String mensaje = "Error: data no encontrada";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }

            Gson gson = new Gson();
            UsuarioVO usuario = gson.fromJson(jsonSolicitud, UsuarioVO.class);

            //validar correo con expresion regular en application.properties
            String mail = usuario.getEmail();
            if (!usuarioService.emailValidator(mail)) {
                String mensaje = "Error: correo no válido";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }

            if (!usuarioService.validarExistenciaMail(mail)) {
                String mensaje = "Error: El correo ya registrado";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }

            //validar password con expresion regular en application.properties
            String pass = usuario.getPassword();
            if (usuarioService.passValidator(pass)) {
                String mensaje = "Error: password no válida";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }

            Map<Boolean, UsuarioDTO> resultado = usuarioService.createUsuario(usuario);

            Boolean error = false;
            UsuarioDTO usuarioDTO = null;

            for (Map.Entry<Boolean, UsuarioDTO> mapa : resultado.entrySet()) {
                error = mapa.getKey();
                usuarioDTO = mapa.getValue();
            }

            if (error) {
                String mensaje = "Error: no ha sido posible crear usuario";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }
            
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } catch (JsonSyntaxException e) {
            String mensaje = "Error: Ha ocurrido un error al crear usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            String mensaje = "Error: Ha ocurrido un error al obtener usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUsuario(@RequestBody String idUsuario) {
        try {
            if (idUsuario == null || idUsuario.isEmpty()) {
                String mensaje = "Error: identificador no encontrado";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }
            
            Gson gson = new Gson();
            UsuarioVO usuarioVO = gson.fromJson(idUsuario, UsuarioVO.class);
            
            UUID id = usuarioVO.getId();
            UsuarioVO usuario = usuarioService.getUsuario(id);
            if (usuario == null) {
                String mensaje = "Error: usuario no encontrado en el sistema";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }

            Boolean resultado = usuarioService.deleteUsuario(id);
            if (!resultado) {
                String mensaje = "Error: ha ocurrido un error al eliminar usuario";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>(new MensajeErrorDTO("Usuario eliminado correctamente"), HttpStatus.OK);
        } catch (JsonSyntaxException e) {
            String mensaje = "Error: Ha ocurrido un error al eliminar usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            String mensaje = "Error: Ha ocurrido un error al obtener usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUsuario(@RequestBody String jsonSolicitud) {
        try {
            if (jsonSolicitud == null || jsonSolicitud.isEmpty()) {
                String mensaje = "Error: data no encontrada";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }

            Gson gson = new Gson();
            UsuarioVO usuario = gson.fromJson(jsonSolicitud, UsuarioVO.class);

            //validar correo con expresion regular en application.properties
            String mail = usuario.getEmail();
            if (!usuarioService.emailValidator(mail)) {
                String mensaje = "Error: correo no válido";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }

            if (usuarioService.validarExistenciaMail(mail)) {
                String mensaje = "Error: correo ya existe en el sistema";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }

            //validar password con expresion regular en application.properties
            String pass = usuario.getPassword();
            if (!usuarioService.passValidator(pass)) {
                String mensaje = "Error: password no válida";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }

            Map<Boolean, UsuarioDTO> resultado = usuarioService.createUsuario(usuario);

            Boolean error = false;
            UsuarioDTO usuarioDTO = null;

            for (Map.Entry<Boolean, UsuarioDTO> mapa : resultado.entrySet()) {
                error = mapa.getKey();
                usuarioDTO = mapa.getValue();
            }

            if (error) {
                String mensaje = "Error: no ha sido posible actualizar usuario";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } catch (JsonSyntaxException e) {
            String mensaje = "Error: Ha ocurrido un error al actualizar usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            String mensaje = "Error: Ha ocurrido un error al obtener usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsuario(@RequestBody String idUsuario) {
        try {
            if (idUsuario == null || idUsuario.isEmpty()) {
                String mensaje = "Error: identificador no encontrado";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.BAD_REQUEST);
            }
            
            Gson gson = new Gson();
            UsuarioVO usuarioVO = gson.fromJson(idUsuario, UsuarioVO.class);
            
            UUID id = usuarioVO.getId();
            usuarioVO = usuarioService.getUsuario(id);
            if (usuarioVO == null) {
                String mensaje = "Error: usuario no encontrado en el sistema";
                LOGGER.info(mensaje);
                return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>(usuarioVO, HttpStatus.OK);
        } catch (JsonSyntaxException e) {
            String mensaje = "Error: Ha ocurrido un error al obtener usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            String mensaje = "Error: Ha ocurrido un error al obtener usuario";
            LOGGER.error(mensaje + " - " + e.getMessage());
            return new ResponseEntity<>(new MensajeErrorDTO(mensaje), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
