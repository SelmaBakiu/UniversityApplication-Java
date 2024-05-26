package com.fti.inxhinierisofti.service;
import com.fti.softwareing.model.Major;
import com.fti.softwareing.service.repository.MajorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorDao majorDao;

    public List<Major> getAll(){
        return majorDao.findAll();
    }
}

