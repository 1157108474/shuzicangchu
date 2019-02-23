package com.zthc.ewms.system.print.service;

import com.zthc.ewms.system.print.dao.PrintDao;
import com.zthc.ewms.system.print.entity.guard.Print;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrintService {

    @Resource(name="printDao")
    public PrintDao dao;
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Print> getPrints(Integer[] ids) {
        return this.dao.getPrints(ids);

    }
}
