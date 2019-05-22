package com.btyer.dao;

        import com.btyer.model.AyUserAddress;
        import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/10 14:13
 */
@Repository
public interface AyUserAddressDao {

    AyUserAddress findById(Integer id);
}
