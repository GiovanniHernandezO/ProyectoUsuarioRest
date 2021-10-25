package com.ejemplo.repository.impl;

import com.ejemplo.repo.PhoneRepo;
import com.ejemplo.repository.PhoneRepository;
import com.ejemplo.vo.PhoneVO;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneRepositoryImpl implements PhoneRepository {
    
    @Autowired
    private PhoneRepo phoneRepo;

    @Override
    public void deleteAllPhonesByIdUsuario(UUID idUsuario) {
        phoneRepo.deleteAllPhonesByIdUsuario(idUsuario);
    }

    @Override
    public void createPhone(PhoneVO phoneVO) {
        phoneRepo.save(phoneVO);
    }

    @Override
    public List<PhoneVO> getPhonesByIdUsuario(UUID idUsuario) {
        return phoneRepo.getListPhonesByIdUsuario(idUsuario);
    }
}