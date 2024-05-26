package com.fti.inxhinierisofti.service;

import com.fti.softwareing.model.Minor;
import com.fti.softwareing.service.repository.MinorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinorService {
    @Autowired
    private MinorDao minorDao;

    public List<Minor> getAll(){
        return minorDao.findAll();
    }
}
