package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenrs
 * @Description
 * @create 2019-07-23 15:24
 **/
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到那个对象中，where类名=label.getId()
             * @param query 封装的都是查询关键字，比如group by order by 等
             * @param cb 用来封装条件对象的，如果直接返回null,表示不需要任何条件
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(label.getLabelname())){
                    // 等同于 where labelname like '%xxxx%'
                    list.add(cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
                }
                if(StringUtils.isNotBlank(label.getLabelname())){
                    // 等同于 where state = '2'
                    list.add(cb.equal(root.get("state").as(String.class), label.getState()));
                }

                // new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                // 把list转成array
                return cb.and(list.toArray(parr));
            }
        });
    }

    public Page<Label> findQuery(Label label, int page, int size) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到那个对象中，where类名=label.getId()
             * @param query 封装的都是查询关键字，比如group by order by 等
             * @param cb 用来封装条件对象的，如果直接返回null,表示不需要任何条件
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(label.getLabelname())){
                    // 等同于 where labelname like '%xxxx%'
                    list.add(cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
                }
                if(StringUtils.isNotBlank(label.getLabelname())){
                    // 等同于 where state = '2'
                    list.add(cb.equal(root.get("state").as(String.class), label.getState()));
                }

                // new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                // 把list转成array
                return cb.and(list.toArray(parr));
            }
        }, PageRequest.of(page -1, size));
    }
}
