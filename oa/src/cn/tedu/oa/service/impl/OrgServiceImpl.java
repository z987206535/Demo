package cn.tedu.oa.service.impl;

import java.util.List;

import cn.tedu.oa.dao.IOrgDAO;
import cn.tedu.oa.dao.impl.OrgMySQLDaoImpl;
import cn.tedu.oa.domain.Org;
import cn.tedu.oa.service.IOrgService;
/**
 * 机构管理业务类 
 * @author pc
 */
public class OrgServiceImpl implements IOrgService {
    private IOrgDAO dao = new OrgMySQLDaoImpl();
	@Override
	public List<Org> findOrgAllByPageNo(int pageNo, int pid) {
		return dao.findOrgByPageNo(pageNo, pid);
	}
	@Override
	public long findOrgTotalPage(int pid) {
		return dao.findOrgTotalPage(pid);
	}//14:55--15:00
	@Override
	public int getPPid(int pid) {
		return dao.getPPid(pid);
	}
	@Override
	public boolean deleteOrgById(int id) {
		//1:判断机构是否存在
		if(dao.findById(id)==null){
			return false;
		}
		//2:机构是否有子机构存在
		if(dao.isChildren(id)){
			return false;
		}
		//3:删除
		dao.delete(id);
		return true;
	}
	@Override
	public void addOrg(Org org) {
		dao.save(org);
	}
	@Override
	public List<Org> findAll() {
		return dao.findAll();
	}
}
