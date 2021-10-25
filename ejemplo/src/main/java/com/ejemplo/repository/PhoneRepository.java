package com.ejemplo.repository;

import com.ejemplo.vo.PhoneVO;
import java.util.List;
import java.util.UUID;

public interface PhoneRepository {
    
    void deleteAllPhonesByIdUsuario(UUID idUsuario);
    
    void createPhone(PhoneVO phoneVO);
    
    List<PhoneVO> getPhonesByIdUsuario(UUID idUsuario);
}
